var app = require('../app');

app.service('userService', function ($http, restService) {

    this.login = function (name, password, rememberMe, successCallBack) {
        restService.post('/login', {
            name: name,
            password: password,
            rememberMe: rememberMe
        }, successCallBack);
    };

    this.logout = function (successCallBack) {
        restService.post('/logout', null, successCallBack);
    };

    this.addUser = function (name, email, password, successCallBack) {
        restService.post('/user', {
            name: name,
            email: email,
            password: password
        }, successCallBack);
    };

    this.findUser = function (userId, successCallBack) {
        restService.post('/user/findUser', {
            userId: userId
        }, successCallBack);
    };

    /**
     * 查询用户是否被禁言了
     * @param userId
     * @param successCallBack
     */
    this.userIsJy = function (userId, successCallBack) {
        restService.post('/user/userIsJy', {
            id: userId
        }, successCallBack);
    };

    /**
     * 禁言或者解禁用户
     * @param userId
     * @param jy 1禁言，0解禁
     * @param successCallBack
     */
    this.shutupOrReleaseUser = function (userId, jy, successCallBack) {
        restService.post('/user/shutupOrReleaseUser', {
            userId: userId,
            isJy: jy
        }, successCallBack);
    }
});