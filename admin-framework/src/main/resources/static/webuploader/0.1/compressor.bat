@echo off
rem author thinkgem@163.com
echo Compressor JS and CSS?
pause
cd %~dp0

set COMP_PATH=..\..\..\..\..\..\bin\compressor

call %COMP_PATH%\compressor.bat attachment\css\webuploader.extend.css
call %COMP_PATH%\compressor.bat attachment\js\webuploader.extend.js

echo.
echo Compressor Success
ping 127.0.0.1 -n 10 >nul
rem pause
echo on