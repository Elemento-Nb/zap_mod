package fafas.zap_mod.tags;

import fafas.zap_mod.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagEntry;

import java.util.concurrent.CompletableFuture;

import static fafas.zap_mod.ZapMod.REPARA_ZAP;

public class ZapItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ZapItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
}
    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup){
        getOrCreateRawBuilder(REPARA_ZAP)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.WHATS_APP)));
        getOrCreateRawBuilder(ItemTags.SWORDS)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_SWORD)));
        getOrCreateRawBuilder(ItemTags.AXES)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_AXE)));
        getOrCreateRawBuilder(ItemTags.PICKAXES)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_PICKAXE)));
        getOrCreateRawBuilder(ItemTags.SHOVELS)
                .add(TagEntry.element(BuiltInRegistries.ITEM.getKey(ModItems.ZAP_SHOVEL)));

    }
}