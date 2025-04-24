# TLib – Take's Lib

**TLib (Take's Lib)** is a lightweight, developer-friendly library for Minecraft Fabric mods (1.21.1+).  
It provides easy builders and utilities for registering items, tools, weapons, and item groups. 
I have a lot of plans for its future development, so the features listed above are not all!

---

## ✨ Features

- 🧱 Fluent builders for:
  - General items
  - Tools (pickaxe, axe, shovel, hoe)
  - Weapons (swords)
  - Creative item groups (tabs)
- 🔄 Unified registration through `TRegistry`
- 🧩 Supports both Minecraft 1.21.1+
- 🧠 Consistent usage across versions
- 🔁 Works with static final fields — no need to call init methods manually

---

## 📦 Gradle Setup (Modrinth Maven)

Add the Modrinth Maven to your `repositories`:

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

## 📚 Documentation
See the [Wiki](https://github.com/iMeeTake/TLib/wiki) for full usage instructions.
