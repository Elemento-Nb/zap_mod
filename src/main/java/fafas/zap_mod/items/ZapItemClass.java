package fafas.zap_mod.items;

import fafas.zap_mod.sounds.CustomModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.PlaySoundConsumeEffect;

import java.util.function.Consumer;

import static fafas.zap_mod.ZapMod.REPARA_ZAP;

public class ZapItemClass extends Item {
    public ZapItemClass(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(net.minecraft.world.item.ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.zap_mod.all.null"));
        textConsumer.accept(Component.translatable("item.zap_mod.zap.desc").withStyle(ChatFormatting.WHITE));
        textConsumer.accept(Component.translatable("item.zap_mod.all.null"));
        textConsumer.accept(Component.translatable("item.zap_mod.zap.desc1").withStyle(ChatFormatting.GREEN));
    }

    public static final Consumable ZAP_ITEM_CONSUMABLE = Consumables.defaultFood()
            .onConsume((new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 6000 * 20, 1), 1.0f))) //declares the class that applies effects when consumed     .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.STRENGTH,  6000 * 20,2), 1.0f)) //strength III for 6000 seconds 100% chance
            .onConsume((new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.RESISTANCE, 6000 * 20, 0), 1.0f)))
            .onConsume((new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.STRENGTH, 6000 * 20, 2), 1.0f)))
            .onConsume((new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 6000 * 20, 0), 1.0f)))
            .onConsume((new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 6000 * 20, 2), 1.0f)))
            .onConsume(new PlaySoundConsumeEffect(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(CustomModSounds.ASSOBIO1)))
            .build();

    public static final FoodProperties ZAP_ITEM_FOOD = new FoodProperties.Builder().nutrition(20).saturationModifier(20).build();
//            //^ declares the class that make the item eatable (hunger 20 = 1 Full bar, saturation 20 = 1 Full bar)


    public static final ToolMaterial ZAP_TOOL_MATERIAL = new ToolMaterial(
            ToolMaterial.NETHERITE.incorrectBlocksForDrops(),
            10000,
            12.0f,
            6.0f,
            20,
            REPARA_ZAP
    );

    public static final ToolMaterial ZAP2_TOOL_MATERIAL = new ToolMaterial(
            ToolMaterial.NETHERITE.incorrectBlocksForDrops(),
            100000,
            20.0f,
            36.0f,
            40,
            REPARA_ZAP
    );
    public static final Consumable ZAP2_ITEM_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.STRENGTH,  36000000 * 20,9), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 36000000 * 20,4), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.RESISTANCE, 36000000 * 20,2), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 36000000 * 20,1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HASTE, 36000000 * 20,4), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 36000000 * 20,0), 1.0f))
            .onConsume(new PlaySoundConsumeEffect(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(CustomModSounds.ASSOBIO2)))
            .build();

    public static final FoodProperties ZAP2_ITEM_FOOD = new FoodProperties.Builder().nutrition(400).saturationModifier(400).build();
}


