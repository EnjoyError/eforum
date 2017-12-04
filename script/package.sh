echo ------------eforum 打包脚本 start----------------- 
echo 正在移除前端缓存文件...
rm -rf ../eforum-front/src/main/resources/public/dist/
echo 前端缓存文件移除完成
echo 执行webpack命令....
cd ../eforum-front/src/main/resources/webjar/
webpack
echo
echo webpack命令执行完成
cd ../../../../../
pwd
echo 正在执行maven打包命令...
mvn package
echo maven打包完成
cd script/
echo
echo ------------eforum 打包脚本end--------------------
