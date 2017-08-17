var app = require('../app');
var editPasswordTml = require('../templates/editPasswordPage.html');

app.directive('editPasswordDirective',function(){
    return {
        restrict: 'E',
        template: editPasswordTml,
        replace: true
    }
});

