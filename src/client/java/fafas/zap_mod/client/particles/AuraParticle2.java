package fafas.zap_mod.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;


public class AuraParticle2 extends SingleQuadParticle {
    private SpriteSet sprites;

    private AuraParticle2(ClientLevel level, double x, double y, double z, double u, double v, double w, SpriteSet sprites) {
        super(level, x, y, z, u, v, w, sprites.get(level.getRandom())); //Define a posição inicial, a velocidade e escolhe uma imagem inicial da textura do sprite sheet.
        this.sprites = sprites;
        this.scale(0.7f);
        this.lifetime = 40;
        this.setSpriteFromAge(sprites);
        this.xd = 0.0;
        this.yd = v;
        this.zd = 0.0;


        
    }

    @Override
    public void tick() { //animação, potencialmente inutil
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected Layer getLayer(){
        return Layer.TRANSLUCENT;//define como parttcula transparente
    }


    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites){
            this.sprites = sprites;
        }
    @Override
    public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double u, double v, double w, RandomSource random){
            float rx = (random.nextFloat() - 0.5f);
            float rz = (random.nextFloat() - 0.5f);
            float vy = 0.1f;

            AuraParticle2 particle = new AuraParticle2(level, x+rx, y, z+rz, u, vy, w, this.sprites);
            return particle;
    }
    }
}
