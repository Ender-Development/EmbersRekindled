package teamroots.embers.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import teamroots.embers.config.ConfigClient;
import teamroots.embers.util.Misc;

public class ParticleStar extends Particle implements IEmberParticle{
	public float colorR = 0;
	public float colorG = 0;
	public float colorB = 0;
	public float rotScale = rand.nextFloat()*0.1f+0.05f;
	public float initScale = 0;
	public ResourceLocation texture = new ResourceLocation("embers:entity/particle_star");
	public ParticleStar(World worldIn, double x, double y, double z, double vx, double vy, double vz, float r, float g, float b, float scale, int lifetime) {
		super(worldIn, x,y,z,0,0,0);
		this.colorR = r;
		this.colorG = g;
		this.colorB = b;
		if (this.colorR > 1.0){
			this.colorR = this.colorR/255.0f;
		}
		if (this.colorG > 1.0){
			this.colorG = this.colorG/255.0f;
		}
		if (this.colorB > 1.0){
			this.colorB = this.colorB/255.0f;
		}
		this.setRBGColorF(colorR, colorG, colorB);
		this.particleMaxAge = lifetime;
		this.particleScale = scale;
		this.initScale = scale;
		this.motionX = vx;
		this.motionY = vy;
		this.motionZ = vz;
		this.particleAngle = 2.0f*(float)Math.PI;
	    TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
	    this.setParticleTexture(sprite);

		this.canCollide = ConfigClient.enableParticleCollisions;
	}
	/*
	@Override
	public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotX, float rotZ, float rotYZ, float rotXY, float rotXZ){
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
		super.renderParticle(buffer, entity, partialTicks, rotX, rotZ, rotYZ, rotXY, rotXZ);
	}*/

	@Override
	public int getBrightnessForRender(float pTicks){
		return 255;
	}

	@Override
	public boolean shouldDisableDepth(){
		return true;
	}

	@Override
	public int getFXLayer(){
		return 1;
	}

	@Override
	public void onUpdate(){
		super.onUpdate();
		if (Misc.random.nextInt(6) == 0){
			this.particleAge ++;
		}
		float lifeCoeff = (float)this.particleAge/(float)this.particleMaxAge;
		this.particleScale = initScale-initScale*lifeCoeff;
		this.particleAlpha = (1.0f-lifeCoeff)*(1.0f-lifeCoeff);
		this.prevParticleAngle = particleAngle;
		particleAngle += rotScale;
	}

	@Override
	public boolean alive() {
		return this.particleAge < this.particleMaxAge;
	}

	@Override
	public boolean isAdditive() {
		return true;
	}

	@Override
	public boolean renderThroughBlocks() {
		return false;
	}
}
