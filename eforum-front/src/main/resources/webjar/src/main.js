// 加载css
require('./assets/css/bootstrap.css');
require('./assets/css/eforum.css');

// 启动angular应用
require('./app');

require('./service/articleService');
require('./service/userService');

// 加载angular自定义指令
require('./directive/headerDirective');
require('./directive/footerDirective');
require('./directive/articleItemDirective');
require('./directive/sidebarDirective');

// 加载angular控制器
require('./controller/mainController');
require('./controller/aboutController')
require('./controller/loginController');
require('./controller/regController');
require('./controller/articleController');
require('./controller/messageController');
require('./controller/favoriteController');
require('./controller/passwordController');