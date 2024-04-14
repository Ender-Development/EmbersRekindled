package teamroots.embers.mixin.embersifiedextended;

import embersified.init.ModBlocks;
import embersified.init.ModEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import teamroots.embers.Embers;
import teamroots.embers.register.BlockRegister;
import teamroots.embers.register.ItemRegister;

@Mixin(value = ModEvents.class, remap = false)
public class ModEventsMixin {

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "INVOKE", target = "Lembersified/init/ModBlocks;getArray()[Lnet/minecraft/block/Block;"))
    private static Block[] getArray() {
        return new Block[]{};
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_emitter:Lnet/minecraft/block/Block;"))
    private static void ember_emitter(Block value) {
        BlockRegister.INSTANCE.add(value);
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_receiver:Lnet/minecraft/block/Block;"))
    private static void ember_receiver(Block value) {
        BlockRegister.INSTANCE.add(value);
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_pulser:Lnet/minecraft/block/Block;"))
    private static void ember_pulser(Block value) {
        BlockRegister.INSTANCE.add(value);
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_funnel:Lnet/minecraft/block/Block;"))
    private static void ember_funnel(Block value) {
        BlockRegister.INSTANCE.add(value);
    }

    @Redirect(method = "onBlockRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;charger:Lnet/minecraft/block/Block;"))
    private static void charger(Block value) {
        BlockRegister.INSTANCE.add(value);
    }

    @Redirect(method = "onRecipeRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;plate_caminite:Lnet/minecraft/item/Item;"))
    private Item plate_caminite() {
        return ItemRegister.PLATE_CAMINITE;
    }

    @Redirect(method = "onRecipeRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;charger:Lnet/minecraft/block/Block;"))
    private Block charger() {
        return ModBlocks.CHARGER;
    }

    @Redirect(method = "onRecipeRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_receiver:Lnet/minecraft/block/Block;"))
    private Block ember_receiver() {
        return ModBlocks.RECEPTOR;
    }

    @Redirect(method = "onRecipeRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_emitter:Lnet/minecraft/block/Block;"))
    private Block ember_emitter() {
        return ModBlocks.EMITTER;
    }

    @Redirect(method = "onRecipeRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_pulser:Lnet/minecraft/block/Block;"))
    private Block ember_pulser() {
        return ModBlocks.EJECTOR;
    }

    @Redirect(method = "onRecipeRegistryReady", at = @At(value = "FIELD", target = "Lteamroots/embers/RegistryManager;ember_funnel:Lnet/minecraft/block/Block;"))
    private Block ember_funnel() {
        return ModBlocks.FUNNEL;
    }

    @ModifyArg(method = "getRL", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ResourceLocation;<init>(Ljava/lang/String;Ljava/lang/String;)V"), index = 0)
    private static String getRL(String value) {
        return Embers.MODID;
    }
}
