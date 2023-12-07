package teamroots.embers.register;

import net.minecraft.block.material.Material;
import teamroots.embers.block.MaterialUnpushable;

import java.util.Collection;

public class MaterialRegister implements IRegistrable<Material> {
    public static final MaterialRegister INSTANCE = new MaterialRegister();

    private MaterialRegister(){}
    public static final Material UNPUSHABLE = new MaterialUnpushable();

    @Override
    public Collection<Material> load() {
        return null;
    }

    @Override
    public void register() {

    }
}
