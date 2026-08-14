package fafas.zap_mod.events;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
public class ZapModEvents implements ModInitializer {

    @Override
    public void onInitialize() {
        OnFullArmorExplodeCallback.EVENT.register((player, pos) -> {
            if (!player.level().isClientSide()) {
                player.level().explode(
                        player,
                        player.getX(), player.getY(), player.getZ(),
                        7.0f,
                        Level.ExplosionInteraction.TNT);
            }
            return InteractionResult.SUCCESS;

        });
    }
}