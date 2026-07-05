package fafas.zap_mod.sounds;

import fafas.zap_mod.ZapMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class CustomModSounds {
    private CustomModSounds() {
    }
    public static final Identifier ZAP_ASSOBIO = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_assobio");
    public static final Identifier ZAP2_ASSOBIO = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap2_assobio");
    public static final Identifier ZAP_MUSIC_ID = Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_music");
    public static final net.minecraft.sounds.SoundEvent ASSOBIO1 = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(ZAP_ASSOBIO);
    public static final net.minecraft.sounds.SoundEvent ASSOBIO2 = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(ZAP2_ASSOBIO);
    public static final net.minecraft.sounds.SoundEvent ZAP_MUSIC = net.minecraft.sounds.SoundEvent.createVariableRangeEvent(ZAP_MUSIC_ID);
    public static void  registerSounds(){
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP_ASSOBIO, ASSOBIO1);
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP2_ASSOBIO, ASSOBIO2);
        Registry.register(BuiltInRegistries.SOUND_EVENT, ZAP_MUSIC_ID, ZAP_MUSIC);
    }
}