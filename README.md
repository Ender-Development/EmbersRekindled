<img src="project_logo.png" align="left" width="180px"/>

# Embers - Unofficial Extended Life

[![License](https://img.shields.io/github/license/Ender-Development/Embers-Extended-Life.svg?label=License)](LICENSE)
[![Versions](https://img.shields.io/curseforge/game-versions/936489?logo=curseforge&label=Game%20Version)](https://www.curseforge.com/minecraft/mc-mods/embers-extended-life)
[![Downloads](https://img.shields.io/curseforge/dt/936489?logo=curseforge&label=Downloads)](https://www.curseforge.com/minecraft/mc-mods/embers-extended-life)

*A dwarven magic mod*
• [CurseForge](https://curseforge.com/minecraft/mc-mods/embers-extended-life)
• [Changelog](CHANGELOG.md)
• [Bugtracker](https://github.com/Ender-Development/Embers-Extended-Life/issues)

<br />

Embers is best described as a dwarven magic mod. It features a smattering of magical and technical content, from staples such as ore doubling and item transport to alchemy and magical ray guns. All centered around the core mechanic of Ember, a limited form of power that you must extract from the world's core near bedrock.

## Builds
You can download the latest files here on [curseforge](https://curseforge.com/minecraft/mc-mods/embers-extended-life).

## Changes

Current Changes to Embers Rekindled v1.19:
- added a config option to display the exact amount of Ash needed in an Alchemy recipe
- registered Heat Coil in HEI as a smelting catalyst
- switched to [RetroFuturaGradle](https://github.com/GTNewHorizons/RetroFuturaGradle)
- updated dependencies to maintained forks
- fixed alchemy tablet breaking after 10 days of existing
- fixed a dupe bug with the melter and acceleration mods
- reworked config system, allow config changes in-game, a LOT more config options
- added a config option to register all WIP items and blocks (useful for pack makers)

Planned Changes:
- native [GroovyScript](https://github.com/CleanroomMC/GroovyScript) support
- allow addition of custom seeds
- merging of various Embers addons, while:
  - keeping the ability to disable them
  - fixing various bugs, that were never fixed in the original addons
  - remove the confusing dependency hell

## [Ender-Development](https://github.com/Ender-Development)

Our Team currently includes:
- `_MasterEnderman_` - Project-Manager, Developer
- `Klebestreifen` - Developer

You can contact us on our [Discord](https://discord.gg/JF7x2vG).

## Contributing
Feel free to contribute to the project. We are always happy about pull requests.
If you want to help us, you can find potential tasks in the [issue tracker](https://github.com/Ender-Development/Embers-Extended-Life/issues).
Of course, you can also create new issues if you find a bug or have a suggestion for a new feature.
Should you have any questions, feel free to ask us on [Discord](https://discord.gg/JF7x2vG).

## Partnership with Akliz

> It's a pleasure to be partnered with Akliz. Besides being a fantastic server provider, which makes it incredibly easy to setup a server of your choice, they help me to push myself and the quality of my projects to the next level. Furthermore you can click on the banner below to get a discount. :')

<a href="https://www.akliz.net/enderman"><img src="https://github.com/MasterEnderman/Zerblands-Remastered/raw/master/Akliz_Partner.png" align="center"/></a>

If you aren't located in the [US](https://www.akliz.net/enderman), Akliz now offers servers in:

- [Europe](https://www.akliz.net/enderman-eu)
- [Oceania](https://www.akliz.net/enderman-oce)

## Maven Dependency
We use GitHub packages. If you need our mod as a dependency:

```groovy
repositories {
    maven {
        name "GitHubPackages.Ender-Development.Embers-Extended-Life"
        url "https://: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@maven.pkg.github.com/Ender-Development/Embers-Extended-Life"
    }
}

dependencies {
    implementation "teamroots.embers:embers_extended_life:1.20.0:dev"
}
```

See https://github.com/Ender-Development/Embers-Extended-Life/packages/
