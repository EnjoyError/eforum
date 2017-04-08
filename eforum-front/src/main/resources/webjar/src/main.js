// 加载css
require('./css/bootstrap.css');
require('./css/eforum.css');

// 启动angular应用
require('./js/app');

require('./js/service/articleService');

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