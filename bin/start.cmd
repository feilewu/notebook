@echo off
set CURRENT_PATH=%~dp0

echo %CURRENT_PATH%

set CLASSPATH=%CURRENT_PATH%..\lib

java -cp %CLASSPATH%\* tech.taole.notebook.app.Main