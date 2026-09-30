package fafas.zap_mod.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;


public class AuraParticle1 extends SingleQuadParticle {
    private SpriteSet sprites;

    private AuraParticle1(ClientLevel level, double x, double y, double z, double u, double v, double w, SpriteSet sprites) {
        super(level, x, y, z, u, v, w, sprites.get(level.getRandom()));
        this.sprites = sprites;
        this.scale(10.0f);
        this.lifetime = 220;
        this.setSpriteFromAge(sprites);
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
        
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected SingleQuadParticle.Layer getLayer(){
        return Layer.TRANSLUCENT;
    }


    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites){
            this.sprites = sprites;
        }
    @Override
    public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double u, double v, double w, RandomSource random){
            AuraParticle1 particle = new AuraParticle1(level, x, y, z, u, v, w, this.sprites);
            return particle;
    }
    }
}
