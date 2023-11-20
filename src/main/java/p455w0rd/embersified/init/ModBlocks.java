package p455w0rd.embersified.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import p455w0rd.embersified.blocks.BlockEmitter;
import p455w0rd.embersified.blocks.BlockReceptor;
import teamroots.embers.block.IBlock;
import teamroots.embers.block.IModeledBlock;

/**
 * @author p455w0rd
 *
 */
public class ModBlocks {

	public static final Block EMITTER = new BlockEmitter().setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(0.6f);
	public static final Block RECEPTOR = new BlockReceptor().setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(0.6f);

	private static final Block[] BLOCK_ARRAY = new Block[] {
			EMITTER, RECEPTOR
	};

	public static final Block[] getArray() {
		return BLOCK_ARRAY;
	}

	public static final Item[] getItemBlockArray() {
		Item[] itemBlockArray = new Item[getArray().length];
		for (int i = 0; i < itemBlockArray.length; i++) {
			itemBlockArray[i] = ((IBlock) getArray()[i]).getItemBlock();
		}
		return itemBlockArray;
	}

	public static final void registerModels() {
		for (Block block : getArray()) {
			if (block instanceof IModeledBlock) {
				((IModeledBlock) block).initModel();
			}
		}
	}

}
