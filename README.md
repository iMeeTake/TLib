# TLib – Take's Lib

**TLib (Take's Lib)** is a modular and developer-friendly library for Minecraft Fabric mods (1.21.1+).  
It provides not only fluent builders for items and tools, but also a growing collection of powerful utilities for client-side rendering, sound management, world interaction, and more.

Whether you're creating survival mechanics, atmosphere-driven features, or just registering your items faster — TLib is built to simplify and enhance your development workflow.

---

## ✨ Features

- 🧱 Fluent builders for:
  - General items
  - Tools (pickaxe, axe, shovel, hoe)
  - Weapons (swords)
  - Creative item groups (tabs)
- 🎮 Client-side utilities:
  - Access to world weather, time, biome and sky visibility (`TClientEnvironment`)
  - Manage one-shot and looped sounds (`TClientSoundManager`)
  - Work with camera position, view direction, FOV, and HUD visibility (`TClientRenderUtils`)
- 🔄 Unified registration with `TRegistry`
- ⚙️ Minimal setup — no need to call init methods manually
- 🧩 Designed for Minecraft 1.21.1 and above
- 🔁 Backward-compatible and version-stable APIs

---

## 📦 Gradle Setup (GitHub Maven)

Add the Maven repository to your `repositories` block:

```gradle
repositories {
	maven {
		name = "iMeeTake GitHub Maven"
		url = "https://raw.githubusercontent.com/iMeeTake/tlib-maven/main/"
	}
}

dependencies {
    modImplementation("com.imeetake:tlib:version") // version = 1.0.0-1.21.1, 1.2.3-1.21.5 etc.
}
