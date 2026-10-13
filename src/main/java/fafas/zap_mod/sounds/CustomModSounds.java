package fafas.zap_mod.sounds;

import fafas.zap_mod.ZapMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class CustomModSounds {
    private CustomModSounds() {
    }
    public static final Identifier ZAP_WHISTLE_ID = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_whistle");
    public static final Identifier ZAP2_WHISTLE_ID = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap2_whistle");
    public static final Identifier ZAP_MUSIC_ID = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_music");
    public static final Identifier CALL_ID = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "call_sound");
    public static final SoundEvent WHISTLE1 = SoundEvent.createVariableRangeEvent(ZAP_WHISTLE_ID);
    public static final SoundEvent WHISTLE2 = SoundEvent.createVariableRangeEvent(ZAP2_WHISTLE_ID);
    public static final SoundEvent ZAP_MUSIC = SoundEvent.createVariableRangeEvent(ZAP_MUSIC_ID);
    public static final SoundEvent CALL_SONG = SoundEvent.createVariableRangeEvent(CALL_ID);
    public static void  registerSounds(){
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP_WHISTLE_ID, WHISTLE1);
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP2_WHISTLE_ID, WHISTLE2);
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP_MUSIC_ID, ZAP_MUSIC);
        Registry.register(BuiltInRegistries.SOUND_EVENT, CALL_ID, CALL_SONG);
    }
}