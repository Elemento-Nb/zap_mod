package fafas.zap_mod.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;


public class AuraParticle2Gre extends SingleQuadParticle {
    private SpriteSet sprites;
    private final float tremorIntensity;
    public float tremorCounter;

    private AuraParticle2Gre(ClientLevel level, double x, double y, double z, double u, double v, double w, SpriteSet sprites) {
        super(level, x, y, z, u, v, w, sprites.get(level.getRandom())); //Define a posição inicial, a velocidade e escolhe uma imagem inicial da textura do sprite sheet.
        this.sprites = sprites;
        this.scale(1f);
        this.lifetime = 38;
        this.setSpriteFromAge(sprites);
        this.xd = 0.0;
        this.yd = v;
        this.zd = 0.0;
        this.tremorIntensity = 0.08f;
        this.rCol = 0.2f;
        this.gCol = 1;
        this.bCol = 0.3f;


        
    }

    @Override
    public void tick() { //animação, potencialmente inutil
        super.tick();
        this.setSpriteFromAge(this.sprites);

        double tremedeiraX = (this.random.nextDouble() - 0.5f) * this.tremorIntensity;
        double tremedeiraZ = (this.random.nextDouble() - 0.5f) * this.tremorIntensity;

        if(tremorCounter%7 == 0){
            this.xd = tremedeiraX;
            this.zd = tremedeiraZ;
        }

        tremorCounter++;
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
            float rx = (random.nextFloat() - 0.5f) * 2;
            float rz = (random.nextFloat() - 0.5f) * 2;
            float vy = 0.1f;

            AuraParticle2Gre particle = new AuraParticle2Gre(level, x+rx, y, z+rz, u, vy, w, this.sprites);
            return particle;
    }
    }
}
