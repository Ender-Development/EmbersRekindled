
// Auto generated groovyscript example file
// MODS_LOADED: embers

log.info 'mod \'embers\' detected, running script'

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


