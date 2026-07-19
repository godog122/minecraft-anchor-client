@echo off
REM Minecraft Anchor Client - Build Script for Windows
REM This script builds the mod JAR file locally using Gradle

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

REM Run gradle directly using the wrapper from build.gradle
REM First, let's try to find gradle installation
where gradle >nul 2>&1
if %errorlevel% equ 0 (
    echo Using system Gradle installation...
    call gradle build
) else (
    echo Gradle not found in system PATH
    echo Attempting to download and use Gradle wrapper...
    
    REM Download gradle wrapper if not present
    if not exist "gradle\wrapper\gradle-wrapper.jar" (
        echo Downloading Gradle wrapper...
        powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; (New-Object System.Net.WebClient).DownloadFile('https://gradle-release-checksums.gradle.org/checksums/gradle-8.5-bin.zip', 'gradle-8.5-bin.zip'); Expand-Archive -Path 'gradle-8.5-bin.zip' -DestinationPath '.'; Move-Item -Path 'gradle-8.5\*' -Destination '.gradle' -Force; Remove-Item 'gradle-8.5-bin.zip'}"
    )
)

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
