package net.deadlydiamond98.client.renderer;

import net.deadlydiamond98.HealingPrettyGood;
import net.deadlydiamond98.entities.HeartPickupEntity;
import net.deadlydiamond98.koalalib.util.ColorHelper;
import net.deadlydiamond98.util.HeartColorUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ExperienceOrbEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class HeartPickupEntityRenderer extends EntityRenderer<HeartPickupEntity> {

    private static final Identifier TEXTURE = new Identifier(HealingPrettyGood.MOD_ID, "textures/entity/heart.png");

    public HeartPickupEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.15f;
        this.shadowOpacity = 0.75f;
    }

    @Override
    protected int getBlockLight(HeartPickupEntity entity, BlockPos blockPos) {
        return MathHelper.clamp(super.getBlockLight(entity, blockPos) + 7, 0, 15);
    }

    @Override
    public void render(HeartPickupEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();

//        matrixStack.scale(-1, -1, 1);

//        Camera camera = MinecraftClient.getInstance().gameRenderer.getCamera();
//        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(camera.getYaw()));

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getItemEntityTranslucentCull(getTexture(entity)));
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();

        float minUV = 0;
        float maxUV = 1;

        int[] color = HeartColorUtil.getColorARGB();

        vertex(vertexConsumer, matrix4f, matrix3f, 1, -1, color[1], color[2], color[3], minUV, maxUV, i);
        vertex(vertexConsumer, matrix4f, matrix3f, -1, -1, color[1], color[2], color[3], maxUV, maxUV, i);
        vertex(vertexConsumer, matrix4f, matrix3f, -1, 1, color[1], color[2], color[3], maxUV, minUV, i);
        vertex(vertexConsumer, matrix4f, matrix3f, 1, 1, color[1], color[2], color[3], minUV, minUV, i);

        matrixStack.pop();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, int red, int green, int blue, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, 0.0f)
                .color(red, green, blue, 255)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(normalMatrix, 0.0f, 1.0f, 0.0f)
                .next();
    }

    @Override
    public Identifier getTexture(HeartPickupEntity entity) {
        return TEXTURE;
    }
}
