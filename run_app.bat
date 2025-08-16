@echo off
echo Setting up environment for Disaster Management App...

REM Try to find Android Studio's embedded JDK
set ANDROID_STUDIO_JDK="C:\Users\%USERNAME%\AppData\Local\Android\Sdk\jbr\bin\java.exe"
if exist %ANDROID_STUDIO_JDK% (
    echo Found Android Studio JDK at: %ANDROID_STUDIO_JDK%
    set JAVA_HOME=C:\Users\%USERNAME%\AppData\Local\Android\Sdk\jbr
    set PATH=%JAVA_HOME%\bin;%PATH%
) else (
    echo Android Studio JDK not found. Please install JDK 17 or 21.
    echo You can download from: https://adoptium.net/
    pause
    exit /b 1
)

echo Building project...
call gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo Build successful! You can now run the app.
    echo To install on device: gradlew.bat installDebug
) else (
    echo Build failed. Check the error messages above.
)

pause
