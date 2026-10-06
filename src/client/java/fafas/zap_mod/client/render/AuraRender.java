package fafas.zap_mod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import fafas.zap_mod.ZapMod;
import fafas.zap_mod.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;



public class AuraRender extends RenderLayer<AvatarRenderState, PlayerModel>{

    //AvatarRenderState e PlayerModel é relativo ao player
    private static final Identifier TRECO = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "textures/particle/aura_1.png"); //define textura a ser usada

    public AuraRender(RenderLayerParent<?, ?> renderer) {
        super((RenderLayerParent<AvatarRenderState, PlayerModel>) renderer);
    }

    @Override
    public void submit(PoseStack matrices, SubmitNodeCollector collector, int lightCoords, AvatarRenderState state, float yRot, float xRot) {
        if (collector == null) return;

        Minecraft client = Minecraft.getInstance();
        if (client.level == null) return;

        matrices.pushPose();


        int framesTotais = 4;
        int frameTime = 3;

        int red0;
        int green0;
        int blue0;
        int opacity;
        boolean fullarmorzap2 =
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModItems.ZAP2_HELMET) &&
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP2_CHESTPLATE) &&
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP2_LEGGINGS) &&
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP2_BOOTS);
        boolean fullarmorzap =
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD).is(ModItems.ZAP_HELMET) &&
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).is(ModItems.ZAP_CHESTPLATE) &&
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).is(ModItems.ZAP_LEGGINGS) &&
            client.player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).is(ModItems.ZAP_BOOTS);
        if(fullarmorzap){
            red0 = 120;
            green0 = 255;
            blue0 = 150;
            opacity = 170;
        } else if (fullarmorzap2){
            red0 = 255;
            green0 = 255;
            blue0 = 100;
            opacity = 180;
        }
        else {
            red0 = 255;
            green0 = 255;
            blue0 = 255;
            opacity = 0;
        }

        long gameTime = client.level.getGameTime();
        int frameAtual = (int) ((gameTime/frameTime)% framesTotais);

        float alturaDoFrame = 1.0f / framesTotais; // seciona a imagem e mostra frame a frame
        float minV = frameAtual * alturaDoFrame;
        float maxV = minV + alturaDoFrame;

        RenderType renderType = RenderTypes.entityTranslucentEmissive(TRECO);

        collector.submitCustomGeometry(matrices, renderType, (pose, consumer) -> {

            consumer.addVertex(pose, (float) (-1.3), (float) (0.0 - 2.0), (float) (0.0)) //posição do vertice
                    .setColor(red0, green0, blue0, opacity) //vermelho verde azul e opacidade
                    .setUv(0.0f, minV) //sim (posição na textura a ser renderizada, provavelmente /: )
                    .setOverlay(OverlayTexture.NO_OVERLAY) //sim
                    .setLight(15728880) //sim
                    .setNormal(0.0f, 0.0f, 1.0f); //rotação se não me engano

            consumer.addVertex(pose, (float) (-1.3), (float) (1.5), (float) (0.0))
                    .setColor(red0, green0, blue0, opacity)
                    .setUv(0.0f, maxV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (1.3), (float) (1.5), (float) (0.0))
                    .setColor(red0, green0, blue0, opacity)
                    .setUv(1.0f, maxV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);

            consumer.addVertex(pose, (float) (1.3), (float) (0.0 - 2.0), (float) (0.0))
                    .setColor(red0, green0, blue0, opacity)
                    .setUv(1.0f, minV)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(15728880)
                    .setNormal(0.0f, 0.0f, 1.0f);


        }

  );
        matrices.popPose();


    }


}