package com.obsidian_core.archaic_quest.client.particle;

import net.minecraft.client.multiplayer.ClientWorld;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;

/** Modified copy-paste of {@link CampfireSmokeParticle} */
public class PoisonCloudParticle extends TextureSheetParticle {

    PoisonCloudParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z);
        scale(3.0F);
        setSize(0.25F, 0.25F);
        lifetime = random.nextInt(50) + 80;

        gravity = 3.0E-6F;
        xd = xSpeed;
        yd = ySpeed + (double)(random.nextFloat() / 500.0F);
        zd = zSpeed;
    }

    @Override
    public void tick() {
        xo = x;
        yo = y;
        zo = z;

        if (age++ < lifetime && !(alpha <= 0.0F)) {
            xd += (random.nextFloat() / 5000.0F * (float)(random.nextBoolean() ? 1 : -1));
            zd += (random.nextFloat() / 5000.0F * (float)(random.nextBoolean() ? 1 : -1));

            if (world.getBlockState(new BlockPos(x, (y + 0.35) - 1, z)).isAir()) {
                yd -= gravity;
            }
            else {
                yd = 0.0D;
            }
            move(xd, yd, zd);

            if (age >= lifetime - 60 && alpha > 0.01F) {
                alpha -= 0.015F;
            }
        }
        else {
            remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet spriteSet) {
            sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            PoisonCloudParticle particle = new PoisonCloudParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.setAlpha(0.9F);
            particle.pickSprite(sprites);
            return particle;
        }
    }
}
