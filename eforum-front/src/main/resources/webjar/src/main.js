// 加载css
require('./assets/css/bootstrap.css');
require('./assets/css/eforum.css');
require('./lib/crop/ng-img-crop.css');
require('./assets/css/angular-strap.libs.min.css');
require('./lib/plugin/bootstrap-switch/css/bootstrap3/bootstrap-switch.css');
require('./lib/summer/summernote.css');


//引入公共js
require('./lib/bootstrap/js/bootstrap');
require('./lib/plugin/bootstrap-switch/js/bootstrap-switch');
require('./lib/summer/summer');
require('./lib/summer/summernote-zh-CN');

// 启动angular应用
require('./app');

require('./service/globalService');
require('./service/articleService');
require('./service/userService');
require('./service/replyService');
require('./service/fileService');
require('./service/personalInformationService');
require('./service/filterService');
require('./service/restService');
require('./service/notificationService');
require('./service/operationArticleService');
require('./service/messageService')



// 加载angular自定义指令
require('./directive/headerDirective');
require('./directive/footerDirective');
require('./directive/articleItemDirective');
require('./directive/sidebarDirective');
require('./directive/replyDirective');
require('./directive/imageDirective');
require('./directive/editPasswordDirective');
require('./directive/editPersonalInformationDirective');
require('./directive/popBoxDirective');
require('./directive/eforumAlertDirective');
require('./directive/eforumModalDirective');

// 加载angular控制器
require('./controller/appController');
require('./controller/headerController');
require('./controller/aboutController')
require('./controller/loginController');
require('./controller/regController');
require('./controller/articleController');
require('./controller/personalInformationController');
require('./controller/editPersonalInformationController');
require('./controller/editPasswordController');
require('./controller/headPortraitController');
require('./controller/articleListController');
require('./controller/pubArticleController');
require('./controller/replyController');
require('./controller/operationArticleController');
require('./controller/userInfoController');
require('./controller/operationUserController');
require('./controller/editMessageController');

// 帮助工具
require('./util/commonUtil');

// 加载绑定工具
require('./bind');