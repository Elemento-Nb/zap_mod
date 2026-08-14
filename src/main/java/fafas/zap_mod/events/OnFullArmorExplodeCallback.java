package fafas.zap_mod.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.Position;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public interface OnFullArmorExplodeCallback {
    Event<OnFullArmorExplodeCallback> EVENT = EventFactory.createArrayBacked(OnFullArmorExplodeCallback.class,
             (listeners) -> (player, pos) -> {
                for (OnFullArmorExplodeCallback listener : listeners){
                 InteractionResult result = listener.interact(player, pos);
                 if (result != InteractionResult.PASS){
                    return result;
                 }
             }
            return InteractionResult.PASS;
        }
    );
    InteractionResult interact(Player player, Vec3 pos);
}
