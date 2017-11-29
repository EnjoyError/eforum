var app = require('../app');
/**
 * 过滤服务，主要用于过滤和网络交互
 */
app.service('filterService', function ($cookies, notificationService) {

    /**
     * 会话过滤
     * @param result
     */
    this.sessionFilter = function (result) {
        if (!result) {
            return;
        }
        if (!result.data) {
            return;
        }
        if (result.data.code == "999") {
            $cookies.remove('username');
            $cookies.remove('userId');
            notificationService.pubNotification("SESSION_INVALID");
        }
    }

    /**
     * 执行过滤
     */
    this.doFilter = function (result) {
        this.sessionFilter(result);
    }
});