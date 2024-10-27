![Plugin Icon](./img_plugin.png)

# Nether Teleport

A simply mechanic that makes the player teleport to the nether when he dies​

**Install:**
To install the plugin put the .jar in the plugin folder server and restart the server.

**Commands:**

```yml
/ntp setspawn # Update the coords of player respawn
/ntp reload # Reload the plugin configuration
/ntp version # displays te plugin version
```

**Config:**

```yml
# Nether Teleport by Spectrasonic
# Set the new coords of respawn in the nether, now spawns above the bedrock roof (change it or use /ntp setspawn)
nether_respawn:
    x: 0
    y: 128
    z: 0
```

---

## Compile to 1.20.X

in the `build.gradle` change the configs to:

```java
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")
// in this case we complie the plugin for 1.20.4, you can change it to the version you want of 1.20
}

def targetJavaVersion = 17
```

then update the `/src/main/resources/plugin.yml` to:

```yml
api-version: 1.20
```

and then run the `gradlew build` command to compile the plugin and follow the steps above to install it.

---

Nether Teleport is Code with ❤️ by [Spectrasonic](https://x.com/Spectrasonic117)
