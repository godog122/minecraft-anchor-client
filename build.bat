@echo off
REM Minecraft Anchor Client - Build Script for Windows
REM This script builds the mod JAR file locally

echo.
echo ========================================
echo Minecraft Anchor Client Build Script
echo ========================================
echo.

REM Check if gradlew.bat exists
if not exist "gradlew.bat" (
    echo ERROR: gradlew.bat not found!
    echo Make sure you are in the project root directory.
    pause
    exit /b 1
)

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH!
    echo Please install Java 21 from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo Cleaning previous builds...
call gradlew.bat clean

echo.
echo Building Anchor Client Mod...
call gradlew.bat build

if errorlevel 1 (
    echo.
    echo ERROR: Build failed!
    pause
    exit /b 1
)

echo.
echo ========================================
echo Build completed successfully!
echo ========================================
echo.
echo Your JAR file is located at:
echo build/libs/anchor-client-1.0.0.jar
echo.
echo Next steps:
echo 1. Copy the JAR to your mods folder:
echo    %%APPDATA%%.minecraft\mods\
echo 2. Launch Minecraft with Fabric Loader
echo 3. Press R-Shift to open the GUI
echo.
pause
