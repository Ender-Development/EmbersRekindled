
// Auto generated groovyscript example file
// MODS_LOADED: embers

log.info 'mod \'embers\' detected, running script'

// Alchemy:
// Convert input items into an output item on a Exchange Tablet.

mods.embers.alchemy.removeAspect('copper')
mods.embers.alchemy.removeByCenter(item('minecraft:wool'))
mods.embers.alchemy.removeByOutput(item('embers:ember_pipe'))
// mods.embers.alchemy.removeAll()

mods.embers.alchemy.recipeBuilder()
    .input(item('minecraft:clay'),item('minecraft:clay'),item('minecraft:clay'),item('minecraft:clay'))
    .output(item('minecraft:gravel'))
    .setAspect('dawnstone', 2, 17)
    .setAspect('glass', 1, 8)
    .register()

mods.embers.alchemy.recipeBuilder()
    .input(item('minecraft:gravel'),ore('dyeGreen'),ore('dyeGreen'),ore('dyeGreen'),item('minecraft:rotten_flesh'))
    .output(item('minecraft:grass'))
    .setAspect('iron', 2, 17)
    .setAspect('copper', 1, 8)
    .register()


mods.embers.alchemy.addAspect('glass',item('minecraft:glass'))
mods.embers.alchemy.addAspect('copper',item('minecraft:gold_ingot'))

mods.embers.alchemy.getAspect(item('embers:aspectus_iron'))

// Heat Coil:
// Convert an input item into an output item in a Heat Coil.

mods.embers.heat_coil.removeByInput(item('minecraft:iron_ore'))
mods.embers.heat_coil.removeByOutput(item('minecraft:iron_ingot'))
// mods.embers.heat_coil.removeByOutput(item('minecraft:glass'))
// mods.embers.heat_coil.removeAll()

mods.embers.heat_coil.recipeBuilder()
    .input(item('minecraft:clay'))
    .output(item('minecraft:gravel'))
    .register()

mods.embers.heat_coil.recipeBuilder()
    .input(item('minecraft:gravel'))
    .output(item('minecraft:grass'))
    .register()


// Melter:
// Converts an input item into an output fluidstack in a Melter with the ability to have a secondary output fluidstack by
// adding a Geologic Separator.

mods.embers.melter.removeByInput(item('minecraft:redstone_block'))
mods.embers.melter.removeByOutput(fluid('oil_soul'))
// mods.embers.melter.removeByOutput(fluid('iron'))
// mods.embers.melter.removeAll()

mods.embers.melter.recipeBuilder()
    .input(item('minecraft:clay'))
    .fluidOutput(fluid('water') * 100)
    .register()

mods.embers.melter.recipeBuilder()
    .input(item('minecraft:gravel'))
    .fluidOutput(fluid('lava') * 50, fluid('water') * 50)
    .register()


// Mixer:
// Converts up to 3 input fluidstacks into an output fluidstack in a Mixer.

mods.embers.mixer.removeByOutput(fluid('dawnstone'))
// mods.embers.mixer.removeAll()

mods.embers.mixer.recipeBuilder()
    .fluidInput(fluid('water') * 100, fluid('lava') * 100)
    .fluidOutput(fluid('dawnstone') * 100)
    .register()


// Stamper:
// Converts an input fluid into an output item with a provided stamp in a Stamper.

mods.embers.stamper.removeByInput(item('embers:shard_ember'))
mods.embers.stamper.removeByOutput(item('embers:plate_iron'))
// mods.embers.stamper.removeByOutput(item('embers:dust_ash'))
// mods.embers.stamper.removeAll()

mods.embers.stamper.recipeBuilder()
    .stamp(item('minecraft:clay'))
    .fluidInput(fluid('water') * 100)
    .output(item('minecraft:brick'))
    .register()

mods.embers.stamper.recipeBuilder()
    .input(item('minecraft:gravel'))
    .stamp(item('minecraft:flint'))
    .output(item('minecraft:glass'))
    .register()


