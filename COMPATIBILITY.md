# Compatability
I used Mixins to make Embers Unofficial Extended Life compatible with as many mods as possible, while also fixing a few issues I encountered during my testing. Here is a changelog for the compatibility mixins:

## [Aetherium Ashen Armor](https://www.curseforge.com/minecraft/mc-mods/aetherium-ashen-armor)
- redirect all calls from the old `RegistryManager` to `ItemRegiser`

## [Aetherworks](https://www.curseforge.com/minecraft/mc-mods/aetherworks)
- redirect all calls from the old `RegistryManager` to `ItemRegiser` and `BlockRegiser`
- use OreDicts instead of single items in some recipes

## [Embers' Construct](https://www.curseforge.com/minecraft/mc-mods/embersconstruct)
- redirect all calls from the old `RegistryManager` to `ItemRegiser` and `BlockRegiser`
- it now uses my config fluid ratios when creating its recipes
- fixed a bug where the Bloom would drop ghost items when broken, after processing a recipe

## [Embersified](https://www.curseforge.com/minecraft/mc-mods/embersified)
- redirect all calls from the old `RegistryManager` to `ItemRegiser`
- now respects my config options for the emitter and receiver

## [JAOPCA](https://www.curseforge.com/minecraft/mc-mods/jaopca)
- redirect all calls from the old `RegistryManager` to `ItemRegiser`
- it now uses my config fluid ratios when creating its recipes

## [Mystical Gears](https://www.curseforge.com/minecraft/mc-mods/mystical-gears)
- redirect all calls from the old `RegistryManager` to `ItemRegiser`
- now respects my config options 

## [Mystical Mechanics](https://www.curseforge.com/minecraft/mc-mods/mysticalmechanics)
- most of the compatibility is done on Embers side

## [Ore Stone Variants](https://www.curseforge.com/minecraft/mc-mods/ore-stone-variants)
- hijacked its config system to respect my config options if a metal is disabled

## [Planar Artifice(TC6 Addon)](https://www.curseforge.com/minecraft/mc-mods/planar-artifice)
- redirect all calls from the old `RegistryManager` to `ItemRegiser`

## [Polymancy (TC6 Addon)](https://www.curseforge.com/minecraft/mc-mods/polymancy)
- redirect all calls from the old `RegistryManager` to `ItemRegiser` and `BlockRegiser`

## [Thaumic Periphery (TC6 Addon)](https://www.curseforge.com/minecraft/mc-mods/thaumic-periphery)
- redirect all calls from the old `RegistryManager` to `ItemRegiser`
- it now uses my config fluid ratios when creating its recipes

## [Tinkering with Embers](https://www.curseforge.com/minecraft/mc-mods/tinkering-with-embers)
- works out of the box, but I highly recommend using Embers' Construct instead
