@echo off
REM Minecraft Anchor Client - Build Script for Windows
REM This script builds the mod JAR file locally

echo.
echo ========================================
echo Minecraft Anchor Client Build Script
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH!
    echo Please install Java 21 from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo Java found! Starting build...
echo.

REM Run gradle wrapper
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
