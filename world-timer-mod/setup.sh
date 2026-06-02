#!/bin/bash
# Setup script for World Timer Mod
# Downloads the Gradle wrapper and prepares the project for development.

set -e

echo "============================================"
echo "  World Timer Mod - Project Setup"
echo "============================================"
echo ""

GRADLE_VERSION="9.4"
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

echo "[1/3] Checking Java..."
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed."
    echo "  Please install Java 25 or higher from: https://adoptium.net/download/"
    exit 1
fi
echo "  Java found: $(java -version 2>&1 | head -1)"

echo "[2/3] Downloading Gradle wrapper JAR..."
WRAPPER_JAR="gradle/wrapper/gradle-wrapper.jar"
if [ -f "$WRAPPER_JAR" ]; then
    echo "  Wrapper JAR already exists, skipping."
else
    if curl -fSL -o "$WRAPPER_JAR" \
       "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-wrapper.jar" 2>/dev/null; then
        echo "  Downloaded successfully."
    else
        echo "  WARNING: Could not auto-download wrapper JAR."
        echo "  Manual steps:"
        echo "    1. Install Gradle: https://gradle.org/install/"
        echo "    2. Run: gradle wrapper --gradle-version ${GRADLE_VERSION}"
        echo ""
    fi
fi

echo "[3/3] Making gradlew executable..."
chmod +x gradlew 2>/dev/null || true

echo ""
echo "============================================"
echo "  Setup complete!"
echo "============================================"
echo ""
echo "To build:  ./gradlew build"
echo "Output:    build/libs/worldtimer-1.0.0.jar"
