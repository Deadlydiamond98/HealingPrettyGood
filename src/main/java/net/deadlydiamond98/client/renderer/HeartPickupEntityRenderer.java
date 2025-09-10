package net.deadlydiamond98.client.renderer;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.entities.HeartPickupEntity;
import net.deadlydiamond98.misc.HealPGoodConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.time.LocalDate;
import java.time.Month;

public class HeartPickupEntityRenderer extends EntityRenderer<HeartPickupEntity> {

    private static final Identifier NUMBER = new Identifier(HealingPrettyGood.MOD_ID, "textures/entity/heart/heart_number.png");

    private static int frame = 0;
    private static int frontAlpha = 0;

    private static boolean isChristmas = false;
    private static boolean isPride = false;
    private static boolean isFunny = false;

    public HeartPickupEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.15f;
        this.shadowOpacity = 0.75f;

        if (HealPGoodConfig.Main.seasonalHearts) {
            LocalDate date = LocalDate.now();
            isFunny = date.getMonth() == Month.APRIL && date.getDayOfMonth() == 1;
            isChristmas = date.getMonth() == Month.DECEMBER;
            isPride = date.getMonth() == Month.JUNE;
        }
    }

    @Override
    protected int getBlockLight(HeartPickupEntity entity, BlockPos blockPos) {
        return MathHelper.clamp(super.getBlockLight(entity, blockPos) + 7, 0, 15);
    }

    @Override
    public void render(HeartPickupEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
        matrixStack.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(camera.getYaw()));

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getItemEntityTranslucentCull(getTexture(entity)));
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();

        double bob = (Math.sin(entity.age / 10.0) * 0.1 + 0.1) / 4;
        matrixStack.translate(-0.125 - 0.0078125, bob, 0);

        matrixStack.scale(0.25f, 0.25f, 1);

        if (++frontAlpha >= 275) {
            frontAlpha = 0;
            frame = (frame + 1) % getFrames();
        }

        float textureHeight = 1 / (float)getFrames();
        float minV = textureHeight * frame;
        float maxV = minV + textureHeight;

        renderFace(vertexConsumer, matrix4f, matrix3f, 255, minV, maxV, 0, i);
        renderFace(vertexConsumer, matrix4f, matrix3f, Math.min(frontAlpha, 255), minV + textureHeight, maxV + textureHeight, -0.0001f, i);

        float numTextureHeight = 1 / 9.0f;
        float numMinV = numTextureHeight * (entity.getHealAmount() - 1);
        float numMaxV = numMinV + numTextureHeight;

        HealingPrettyGood.LOGGER.info("Heart Amount is: {}", entity.getHealAmount());

        VertexConsumer vertexConsumerNumber = vertexConsumerProvider.getBuffer(RenderLayer.getItemEntityTranslucentCull(NUMBER));
        renderFace(vertexConsumerNumber, matrix4f, matrix3f, 255, numMinV, numMaxV, -0.0002f, i);

        matrixStack.pop();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static void renderFace(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, int alpha, float minV, float maxV, float z, int i) {
        vertex(vertexConsumer, positionMatrix, normalMatrix, 1, 0, z, alpha, 0, maxV, i);
        vertex(vertexConsumer, positionMatrix, normalMatrix, 0, 0, z, alpha, 1, maxV, i);
        vertex(vertexConsumer, positionMatrix, normalMatrix, 0, 1, z, alpha, 1, minV, i);
        vertex(vertexConsumer, positionMatrix, normalMatrix, 1, 1, z, alpha, 0, minV, i);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float z, int alpha, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, z)
                .color(255, 255, 255, alpha)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(normalMatrix, 0.0f, 1.0f, 0.0f)
                .next();
    }

    private int getFrames() {
        return isPride ? 20 : 2;
    }

    @Override
    public Identifier getTexture(HeartPickupEntity entity) {
        if (isFunny) {
            return getTexture("april");
        } else if (isPride) {
            return getTexture("pride");
        } else if (isChristmas) {
            return getTexture("christmas");
        }
        return getTexture("regular");
    }

    private Identifier getTexture(String name) {
        return new Identifier(HealingPrettyGood.MOD_ID, "textures/entity/heart/" + name + ".png");
    }
}
