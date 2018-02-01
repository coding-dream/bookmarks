@echo off
color 27

echo ========================== start server ==========================
D:
cd D:\Codes\JavaFX\bookmarks\build\dist
java -jar bookmarks.jar
pause

cd D:\Codes\JavaFX\bookmarks
git status
git add .
git commit -m "update db"
git push origin master
echo ========================== stop ==========================
pause
