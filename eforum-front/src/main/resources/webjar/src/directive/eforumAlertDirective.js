var app = require('../app');
var efap = require('../templates/eforumAlertPage.html');

app.directive('eforumAlert', function() {
    return {
        restrict: 'E',
        template: efap,
        replace: true
    }
});