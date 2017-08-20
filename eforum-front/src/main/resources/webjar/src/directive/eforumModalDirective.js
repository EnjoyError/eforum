var app = require('../app');
var efmp = require('../templates/eforumModalPage.html');

app.directive('eforumModal', function() {
    return {
        restrict: 'E',
        template: efmp,
        replace: true
    }
});