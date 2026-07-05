package fafas.zap_mod.items;

import fafas.zap_mod.ZapMod;
import fafas.zap_mod.sounds.CustomModSounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

import static fafas.zap_mod.ZapMod.REPARA_ZAP;


public class ZapArmorClass {
    public static final int ZAP1_DURA = 500;
    public static final int ZAP2_DURA = 25000;

    public static final ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> ZAP_ARMOR_ASSET_KEY = ResourceKey
            .create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap_armor"));
    public static final ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> ZAP2_ARMOR_ASSET_KEY = ResourceKey
            .create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, "zap2_armor"));

    public static final net.minecraft.world.item.equipment.ArmorMaterial ZAP_INSTANCE = new net.minecraft.world.item.equipment.ArmorMaterial(
            ZAP1_DURA,
            Map.of(
                    ArmorType.HELMET, 5,
                    ArmorType.CHESTPLATE, 10,
                    ArmorType.LEGGINGS, 8,
                    ArmorType.BOOTS, 5
            ),
            30,
            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(CustomModSounds.ASSOBIO1),
            5.0f,
            0.15f,
            REPARA_ZAP,
            ZAP_ARMOR_ASSET_KEY
    );
    public static final net.minecraft.world.item.equipment.ArmorMaterial ZAP2_INSTANCE = new net.minecraft.world.item.equipment.ArmorMaterial(
            ZAP2_DURA,
            Map.of(
                    ArmorType.HELMET, 25,
                    ArmorType.CHESTPLATE, 100,
                    ArmorType.LEGGINGS,64,
                    ArmorType.BOOTS,25
            ),
            50,
            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(CustomModSounds.ASSOBIO2),
            25.0f,
            0.2f,
            REPARA_ZAP,
            ZAP2_ARMOR_ASSET_KEY
    );
}
