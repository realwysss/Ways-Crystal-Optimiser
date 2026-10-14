<p align="center"><img src="src/main/resources/icon.png" width="128"></p>

# ways crystal optimiser

client side fabric mod for minecraft 1.21.11 that makes crystal pvp faster. it works while you hold an end crystal.

- the use cooldown is lowered to a random number between your min and max ticks (1 and 2 by default)
- the attack cooldown is reset
- if your crosshair is on a block but there is an end crystal in your line within 4.5 blocks, your hit goes to the crystal

## settings

open mod menu, find ways crystal optimiser and click config. there is an on switch and min and max ticks sliders from 0 to 4. careful, putting both at 0 can get you flagged by anticheats, and some servers ban stuff like this anyway.

## install

you need fabric loader, and mod menu with cloth config for the settings screen. put the jar from the releases page in your mods folder.

## build

`./gradlew build`, the jar ends up in build/libs.

cc0, do what you want with it.
