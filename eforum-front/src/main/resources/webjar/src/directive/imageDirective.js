var app = require('../app');
var tpl = require('../templates/image.html');

app.directive('imageDirective',function(){
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
	} 
});


app.directive('enlargePic',function(){//enlargePic指令名称，写在需要用到的地方img中即可实现放大图片  
    return{    
        restrict: "AE",    
        link: function(scope,elem){    
            elem.bind('click',function($event){    
                var img = $event.srcElement || $event.target;    
                angular.element(document.querySelector(".mask"))[0].style.display = "block";    
                angular.element(document.querySelector(".bigPic"))[0].src = img.src;    
            })    
        }    
    }    
});

app.directive('closePic',function(){    
    return{    
        restrict: "AE",    
        link: function(scope,elem){    
            elem.bind('click',function($event){    
                angular.element(document.querySelector(".mask"))[0].style.display = "none";    
            })    
        }    
    }    
});