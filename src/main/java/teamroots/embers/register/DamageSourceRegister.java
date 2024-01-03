package teamroots.embers.register;

import net.minecraft.util.DamageSource;
import teamroots.embers.damage.DamageEmber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DamageSourceRegister implements IRegistrable<DamageSource> {
    public static final DamageSourceRegister INSTANCE = new DamageSourceRegister();

    private DamageSourceRegister() {
    }
    public static final DamageSource DAMAGE_EMBER = new DamageEmber();;

    private final static List<DamageSource> REGISTRY = new ArrayList<>(Collections.singletonList(
            DAMAGE_EMBER
    ));

    @Override
    public Collection<DamageSource> load() {
        return REGISTRY;
    }

    @Override
    public void register() {

    }

    @Override
    public void add(DamageSource registrable) {

    }
}
