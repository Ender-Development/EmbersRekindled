package teamroots.embers.register;

import teamroots.embers.api.EmbersAPI;
import teamroots.embers.api.itemmod.ModifierBase;
import teamroots.embers.itemmod.*;

import java.util.Collection;

public class ItemModifierRegister implements IRegistrable<ModifierBase> {
    public static final ItemModifierRegister INSTANCE = new ItemModifierRegister();

    private ItemModifierRegister(){}

    @Override
    public Collection<ModifierBase> load() {
        return null;
    }

    @Override
    public void register() {
        EmbersAPI.CORE = new ModifierCore();
        EmbersAPI.SUPERHEATER = new ModifierSuperheater();
        EmbersAPI.JET_AUGMENT = new ModifierCinderJet();
        EmbersAPI.CASTER_ORB = new ModifierCasterOrb();
        EmbersAPI.RESONATING_BELL = new ModifierResonatingBell();
        EmbersAPI.BLASTING_CORE = new ModifierBlastingCore();
        EmbersAPI.FLAME_BARRIER = new ModifierFlameBarrier();
        EmbersAPI.ELDRITCH_INSIGNIA = new ModifierEldritchInsignia();
        EmbersAPI.INTELLIGENT_APPARATUS = new ModifierIntelligentApparatus();
        EmbersAPI.DIFFRACTION = new ModifierDiffraction();
        EmbersAPI.FOCAL_LENS = new ModifierFocalLens();
        EmbersAPI.TINKER_LENS = new ModifierTinkerLens("tinker_lens", false);
        EmbersAPI.ANTI_TINKER_LENS = new ModifierTinkerLens("anti_tinker_lens", true);
        EmbersAPI.SHIFTING_SCALES = new ModifierShiftingScales();
        EmbersAPI.WINDING_GEARS = new ModifierWindingGears();

        EmbersAPI.registerModifier(ItemRegister.ANCIENT_MOTIVE_CORE, EmbersAPI.CORE);
        EmbersAPI.registerModifier(ItemRegister.SUPERHEATER, EmbersAPI.SUPERHEATER);
        EmbersAPI.registerModifier(ItemRegister.JET_AUGMENT, EmbersAPI.JET_AUGMENT);
        EmbersAPI.registerModifier(ItemRegister.CASTER_ORB, EmbersAPI.CASTER_ORB);
        EmbersAPI.registerModifier(ItemRegister.RESONATING_BELL, EmbersAPI.RESONATING_BELL);
        EmbersAPI.registerModifier(ItemRegister.BLASTING_CORE, EmbersAPI.BLASTING_CORE);
        EmbersAPI.registerModifier(ItemRegister.FLAME_BARRIER, EmbersAPI.FLAME_BARRIER);
        EmbersAPI.registerModifier(ItemRegister.ELDRITCH_INSIGNIA, EmbersAPI.ELDRITCH_INSIGNIA);
        EmbersAPI.registerModifier(ItemRegister.INTELLIGENT_APPARATUS, EmbersAPI.INTELLIGENT_APPARATUS);
        EmbersAPI.registerModifier(ItemRegister.DIFFRACTION_BARREL, EmbersAPI.DIFFRACTION);
        EmbersAPI.registerModifier(ItemRegister.FOCAL_LENS, EmbersAPI.FOCAL_LENS);
        EmbersAPI.registerModifier(ItemRegister.TINKER_LENS, EmbersAPI.TINKER_LENS);
        EmbersAPI.registerModifier(ItemRegister.ANTI_TINKER_LENS, EmbersAPI.ANTI_TINKER_LENS);
        EmbersAPI.registerModifier(ItemRegister.SHIFTING_SCALES, EmbersAPI.SHIFTING_SCALES);
        EmbersAPI.registerModifier(ItemRegister.WINDING_GEARS, EmbersAPI.WINDING_GEARS);
    }
}
