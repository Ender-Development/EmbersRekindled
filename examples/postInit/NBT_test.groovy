// mods.embers.alchemy.removeAll()
mods.embers.alchemy.recipeBuilder()
    .input(
            item('minefantasyreforged:plate').withNbt(['mf_custom_materials': ['main_material': 'dawnstone']]),
            item('minefantasyreforged:plate').withNbt(['mf_custom_materials': ['main_material': 'iron']])
    )
    .output(item('minecraft:anvil'))
    .setAspect('dawnstone', 1, 3)
    .register()