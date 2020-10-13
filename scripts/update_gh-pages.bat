@echo off
cd ..
git checkout gh-pages
git merge master
git push
git checkout master
