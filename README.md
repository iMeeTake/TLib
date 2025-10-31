# Welcome to TLib

**TLib** (Take's Lib) is a utility-focused library for **Minecraft Fabric mods (1.21+)**.

It provides powerful tools to make your mod cleaner, simpler, and more maintainable.  
Whether you're dealing with particles, sound, rendering, or other systems — TLib has you covered.

---

## 🎯 What you can do with TLib

With TLib, you can easily:

- 🎇 Spawn and control particles (static, dynamic, shaped, directional)
- 🧠 Register custom particle factories
- 🔉 Play one-time or looped sounds
- 🎥 Access camera view, FOV, and player perspective
- 🌦️ Query biome, weather, night/day cycle
- and much more

> 🧩 **TLib is under active development and new utilities are added regularly.**

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
```

---

## 📖 Learn more in the Wiki!

Check out the full documentation on GitHub:
👉 [TLib Wiki](https://github.com/iMeeTake/TLib/wiki)
