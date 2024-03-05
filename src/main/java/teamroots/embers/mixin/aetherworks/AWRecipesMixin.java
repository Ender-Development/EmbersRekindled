package teamroots.embers.mixin.aetherworks;

import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.config.ConfigMaterial;
import teamroots.embers.register.FluidRegister;
import teamroots.embers.register.ItemRegister;
import v0id.aw.common.recipe.AWRecipes;

import static teamroots.embers.recipe.RecipeRegistry.getItemStackFromOreDict;

@Mixin(value = AWRecipes.class, remap = false)
public class AWRecipesMixin {
    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;shard_ember:Lnet/minecraft/item/Item;"))
    private static Item shard_embers() {
        return ItemRegister.SHARD_EMBER;
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;blasting_core:Lnet/minecraft/item/Item;"))
    private static Item blasting_core() {
        return ItemRegister.BLASTING_CORE;
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;crystal_ember:Lnet/minecraft/item/Item;"))
    private static Item crystal_ember() {
        return ItemRegister.CRYSTAL_EMBER;
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/ConfigManager;enableBronze:Z"))
    private static boolean enableBronze() {
        return ConfigMaterial.BRONZE.isNotOff();
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;eldritch_insignia:Lnet/minecraft/item/Item;"))
    private static Item eldritch_insignia() {
        return ItemRegister.ELDRITCH_INSIGNIA;
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ingot_bronze:Lnet/minecraft/item/Item;"))
    private static Item ingot_bronze() {
        return getItemStackFromOreDict("ingotBronze",1).getItem();
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ingot_dawnstone:Lnet/minecraft/item/Item;"))
    private static Item ingot_dawnstone() {
        return getItemStackFromOreDict("ingotDawnstone",1).getItem();
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;jet_augment:Lnet/minecraft/item/Item;"))
    private static Item jet_augment() {
        return ItemRegister.JET_AUGMENT;
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;plate_bronze:Lnet/minecraft/item/Item;"))
    private static Item plate_bronze() {
        return getItemStackFromOreDict("plateBronze",1).getItem();
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;plate_dawnstone:Lnet/minecraft/item/Item;"))
    private static Item plate_dawnstone() {
        return getItemStackFromOreDict("plateDawnstone",1).getItem();
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;plate_lead:Lnet/minecraft/item/Item;"))
    private static Item plate_lead() {
        return getItemStackFromOreDict("plateLead",1).getItem();
    }

    @Redirect(method = "onRegisterRecipes", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;fluid_molten_electrum:Lnet/minecraftforge/fluids/Fluid;"))
    private static Fluid fluid_molten_electrum() {
        return FluidRegister.FLUID_MOLTEN_ELECTRUM;
    }
}
