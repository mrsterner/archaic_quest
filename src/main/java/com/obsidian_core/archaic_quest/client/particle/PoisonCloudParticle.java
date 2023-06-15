package com.obsidian_core.archaic_quest.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

/** Modified copy-paste of {@link CampfireSmokeParticle} */
public class PoisonCloudParticle extends SpriteBillboardParticle {

    PoisonCloudParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z);
        scale(3.0F);
        setBoundingBoxSpacing(0.25F, 0.25F);
        maxAge = random.nextInt(50) + 80;

        gravityStrength = 3.0E-6F;
        velocityX = xSpeed;
        velocityY = ySpeed + (double)(random.nextFloat() / 500.0F);
        velocityZ = zSpeed;
    }

    @Override
    public void tick() {
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;

        if (age++ < maxAge && !(alpha <= 0.0F)) {
            prevPosX += (random.nextFloat() / 5000.0F * (float)(random.nextBoolean() ? 1 : -1));
            prevPosZ += (random.nextFloat() / 5000.0F * (float)(random.nextBoolean() ? 1 : -1));

            if (world.getBlockState(new BlockPos(x, (y + 0.35) - 1, z)).isAir()) {
                prevPosY -= gravityStrength;
            }
            else {
                prevPosY = 0.0D;
            }
            move(prevPosX, prevPosY, prevPosZ);

            if (age >= maxAge - 60 && alpha > 0.01F) {
                alpha -= 0.015F;
            }
        }
        else {
            markDead();
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            PoisonCloudParticle particle = new PoisonCloudParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            particle.setAlpha(0.9F);
            particle.setSprite(sprites);
            return particle;
        }
    }
}
