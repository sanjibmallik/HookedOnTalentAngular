
hotMainModule.config(function($stateProvider, $urlRouterProvider){

 $urlRouterProvider.otherwise('/login');


    $stateProvider
        .state('login', {
            url:'/login',
            templateUrl: 'app/components/login/login.html'
           /* controller: 'loginCtrl'*/
            
           
        });
       
});


