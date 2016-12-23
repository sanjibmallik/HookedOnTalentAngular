
hotMainModule.config(function($stateProvider, $urlRouterProvider){

 $urlRouterProvider.otherwise('/login');


    $stateProvider
        .state('login', {
            url:'/login',
            templateUrl: 'app/components/login/login.html'
           /* controller: 'loginCtrl'*/
            
           
        })
    .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'app/components/login/dashboard.html'
        /* controller: 'loginCtrl'*/


    });
       
});


