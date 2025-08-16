@echo off
echo Cleaning and rebuilding Disaster Management App...

echo.
echo Step 1: Cleaning project...
call gradlew.bat clean

echo.
echo Step 2: Refreshing dependencies...
call gradlew.bat --refresh-dependencies

echo.
echo Step 3: Building project...
call gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✅ Build successful! The kapt metadata issue has been resolved.
    echo.
    echo To run the app:
    echo   gradlew.bat installDebug
) else (
    echo.
    echo ❌ Build failed. Check the error messages above.
    echo.
    echo Common solutions:
    echo   1. Make sure you have JDK 17 or 21 installed
    echo   2. Try using Android Studio's embedded JDK
    echo   3. Check that all dependencies are compatible
)

echo.
pause
