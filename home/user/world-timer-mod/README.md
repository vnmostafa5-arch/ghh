# World Timer Mod

A Fabric mod for **Minecraft 26.1.2** that tracks and displays the total time spent in a world.

## Features

- ⏱ Tracks time spent in each world using server ticks
- 💾 Saves time persistently — accumulates across sessions  
- 📊 Displays total time on the HUD in gold text (top-left corner)
- 🔄 Continues from where you left off when you rejoin the world

## Requirements

| Requirement | Version |
|---|---|
| Minecraft | 26.1.2 |
| Fabric Loader | 0.18.5+ |
| Fabric API | 0.150.0+26.1.2 |
| Java | 25+ |

## Building Locally

```bash
# 1. Clone the repo
git clone https://github.com/vnmostafa5-arch/world-timer-mod.git
cd world-timer-mod

# 2. Setup (downloads gradle wrapper)
chmod +x setup.sh
./setup.sh

# 3. Build
./gradlew build
```

The mod JAR will be in `build/libs/worldtimer-1.0.0.jar`.

## GitHub Actions

This project uses GitHub Actions to automatically build the mod on every push and pull request.

The built artifact is available in the **Actions** tab → select a workflow run → download `worldtimer-mod`.

## How It Works

1. When you open a world, the timer starts counting
2. Time is displayed on the HUD as `⏱ HH:MM:SS`
3. When you leave the world, the time is saved
4. Next time you open the same world, the timer continues from the saved time

## License

MIT
