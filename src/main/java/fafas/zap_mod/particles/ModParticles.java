package fafas.zap_mod.particles;

import fafas.zap_mod.ZapMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles {
    private static SimpleParticleType registerParticle(String name, SimpleParticleType type){
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(ZapMod.MOD_ID, name), type);
    }
    public static final SimpleParticleType AURA1_PARTICLE = registerParticle("aura_1_particle", FabricParticleTypes.simple(true));
    public static final SimpleParticleType AURA2_PARTICLE_YEL = registerParticle("aura_2_particle_yel", FabricParticleTypes.simple(true));
    public static final SimpleParticleType AURA2_PARTICLE_GRE = registerParticle("aura_2_particle_gre", FabricParticleTypes.simple(true));


    public static void registerParticles(){

    }
}