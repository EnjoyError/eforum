// 加载css
require('./css/bootstrap.css');
require('./css/eforum.css');

// 加载angular模块
require('angular');
require('angular-route');

// 启动angular应用
require('./js/app');

// 加载angular的自定义指令
require('./js/directive/headerDirective');
require('./js/directive/footerDirective');
require('./js/directive/articleItemDirective');
require('./js/directive/sidebarDirective');

// 加载angular的控制器
require('./js/controller/mainController');
require('./js/controller/aboutController')
require('./js/controller/loginController');
require('./js/controller/regController');