package p455w0rd.embersified.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

/**
 * @author p455w0rd
 *
 */
public class ModIntegration {

	public static enum Mod {

			EMBERS("embers");

		String modid;

		Mod(String modid) {
			this.modid = modid;
		}

		public String getId() {
			return modid;
		}

		public boolean isLoaded() {
			return Loader.isModLoaded(getId());
		}

		public ResourceLocation getRegistryName() {
			return new ResourceLocation(ModGlobals.MODID, getId());
		}
	}

}
