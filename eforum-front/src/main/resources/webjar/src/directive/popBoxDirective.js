var app = require('../app');
var popBox = require('../templates/popBoxPage.html');

app.directive('popBoxDirective',function(){
	return {
		restrict: 'E',
		template: popBox,
		replace: true,
	} 
});

app.directive('closeForPopBox',function(util){
    return{    
        restrict: "AE",
        link: function(scope,elem){    
            elem.bind('click',function($event){
                util.closePopBox();
            })
        }    
    }    
});