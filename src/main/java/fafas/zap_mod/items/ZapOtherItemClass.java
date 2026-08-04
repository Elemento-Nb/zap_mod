package fafas.zap_mod.items;



import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


public class ZapOtherItemClass extends net.minecraft.world.item.Item {
    public ZapOtherItemClass(Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        double chance = random.nextDouble();
        if (attacker.getMainHandItem().is(ModItems.ZAP_SWORD)) {
            if (chance < 0.1) {
                if (!target.level().isClientSide()) {
                    target.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 40, 0));
                    target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 2));
                }
                if (!attacker.level().isClientSide()) {
                    attacker.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 30 * 20, 0));
                }
                super.postHurtEnemy(stack, target, attacker);
            }
        } else {
            if (chance < 0.1) {
                if (!target.level().isClientSide()) {
                    target.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 0));
                    target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 5));
                }
                if (!attacker.level().isClientSide()) {
                    attacker.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 30 * 20, 2));
                }
                super.postHurtEnemy(stack, target, attacker);
            }
        }
    }
}