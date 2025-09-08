package net.deadlydiamond98;

import net.deadlydiamond98.client.renderer.HeartPickupEntityRenderer;
import net.deadlydiamond98.entities.HealPGoodEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HealPGoodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(HealPGoodEntities.HEART_PICKUP_ENTITY, HeartPickupEntityRenderer::new);
    }
}
