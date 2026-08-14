package fafas.zap_mod.sounds;

import fafas.zap_mod.ZapMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class CustomModSounds {
    private CustomModSounds() {
    }
    public static final Identifier ZAP_WHISTLE = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_whistle");
    public static final Identifier ZAP2_WHISTLE = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap2_whistle");
    public static final Identifier ZAP_MUSIC_ID = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_music");
    public static final SoundEvent WHISTLE1 = SoundEvent.createVariableRangeEvent(ZAP_WHISTLE);
    public static final SoundEvent WHISTLE2 = SoundEvent.createVariableRangeEvent(ZAP2_WHISTLE);
    public static final SoundEvent ZAP_MUSIC = SoundEvent.createVariableRangeEvent(ZAP_MUSIC_ID);
    public static void  registerSounds(){
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP_WHISTLE, WHISTLE1);
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP2_WHISTLE, WHISTLE2);
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP_MUSIC_ID, ZAP_MUSIC);
    }
}