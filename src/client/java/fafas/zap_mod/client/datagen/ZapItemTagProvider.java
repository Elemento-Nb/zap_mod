package fafas.zap_mod.client.datagen;

import fafas.zap_mod.ZapMod;
import fafas.zap_mod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagEntry;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static fafas.zap_mod.ZapMod.REPAIRS_ZAP;

public class ZapItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ZapItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
}
    @Override
    protected void addTags(HolderLookup.@NotNull Provider wrapperLookup){
        getOrCreateRawBuilder(REPAIRS_ZAP)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.WHATS_APP)));
        getOrCreateRawBuilder(ItemTags.SWORDS)
                .addElement(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_SWORD));
        getOrCreateRawBuilder(ItemTags.AXES)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_AXE)));
        getOrCreateRawBuilder(ItemTags.PICKAXES)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_PICKAXE)));
        getOrCreateRawBuilder(ItemTags.SHOVELS)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_SHOVEL)));
        getOrCreateRawBuilder(ItemTags.HEAD_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_HELMET)));
        getOrCreateRawBuilder(ItemTags.CHEST_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_CHESTPLATE)));
        getOrCreateRawBuilder(ItemTags.LEG_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_LEGGINGS)));
        getOrCreateRawBuilder(ItemTags.FOOT_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_BOOTS)));
        getOrCreateRawBuilder(ItemTags.SWORDS)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_SWORD)));
        getOrCreateRawBuilder(ItemTags.AXES)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_AXE)));
        getOrCreateRawBuilder(ItemTags.PICKAXES)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_PICKAXE)));
        getOrCreateRawBuilder(ItemTags.SHOVELS)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_SHOVEL)));
        getOrCreateRawBuilder(ItemTags.HEAD_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_HELMET)));
        getOrCreateRawBuilder(ItemTags.CHEST_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_CHESTPLATE)));
        getOrCreateRawBuilder(ItemTags.LEG_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_LEGGINGS)));
        getOrCreateRawBuilder(ItemTags.FOOT_ARMOR)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP2_BOOTS)));
    }
}