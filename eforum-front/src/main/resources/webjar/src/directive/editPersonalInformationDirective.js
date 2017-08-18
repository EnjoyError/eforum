var app = require('../app');
var editPIPTml = require('../templates/editPersonalInformationPage.html');

app.directive('editPersonalInformationDirective',function(){
    return {
        restrict: 'E',
        template: editPIPTml,
        replace: true
    }
});
