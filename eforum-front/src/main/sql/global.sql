-- 帖子和回复图片存储位置
INSERT INTO global_param (g_key,g_value) values('IMAGE_DIR','/appData/eforum/images');
-- 匿名用户
INSERT INTO user (id,email,name,head_portrait_file_name) values('1','niming@yonghu.com','匿名用户','anonymous.png');
-- 用户头像图片存储位置
INSERT INTO global_param (g_key,g_value) values('HEAD_PORTRAIT_DIR','/appData/eforum/headPortrait');

-- 初始化用户
UPDATE USER SET BE_SHUTUP=0 WHERE BE_SHUTUP IS NULL;