# Minecraft Anchor Client

A powerful PvP client mod for Minecraft 1.21.1 built with Fabric.

## Features

### Safe Anchor Module
- Automatically detects nearby respawn anchors
- Searches for glowstone in the vicinity
- Detonates anchors with a single glowstone
- Highly optimized for competitive PvP scenarios

### Auto Crystal Module
- Automatically detects valid end crystal placement locations
- Calculates damage potential for each position
- Places crystals strategically on solid blocks
- Auto-detonates for maximum efficiency

### Click GUI
- Beautiful draggable UI interface
- Toggle modules on/off with a click
- Press **R-Shift** to open/close the GUI
- Real-time status indicators

## Installation

1. Install Fabric Loader for Minecraft 1.21.1
2. Place the `.jar` file in your `mods` folder
3. Launch Minecraft with the Fabric profile
4. Press **R-Shift** to open the GUI and toggle modules

## Building

```bash
./gradlew build
```

The compiled mod will be in `build/libs/anchor-client-1.0.0.jar`

## Usage

- Press **R-Shift** to open the Click GUI
- Click on module names to enable/disable them
- Modules will operate automatically when enabled
- Green indicator = Module enabled
- Red indicator = Module disabled

## License

MIT License - See LICENSE file for details
