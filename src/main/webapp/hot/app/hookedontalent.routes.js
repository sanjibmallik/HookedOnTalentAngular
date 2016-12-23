
hotMainModule.config(function($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, ASSETS){

 $urlRouterProvider.otherwise('/login');


    $stateProvider
        .state('login', {
            url:'/login',
            templateUrl: 'app/components/login/login.html'

            
           
        }).
        state('app', {
        abstract: true,
        url: '/app',
        templateUrl: appHelper.templatePath('layout/app-body'),
        controller: function($rootScope){
            $rootScope.isLoginPage        = false;
            $rootScope.isLightLoginPage   = false;
            $rootScope.isLockscreenPage   = false;
            $rootScope.isMainPage         = true;
        }
    }).

        // Dashboards
        state('app.dashboard', {
            url: '/dashboard',
            templateUrl: appHelper.templatePath('dashboard'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        }).

        state('app.users-Create-User', {
            url: '/users-Create-User',
            templateUrl: appHelper.templatePath('users/Create-User'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr,
                        ASSETS.core.bootstrap,
                        ASSETS.core.jQueryUI,
                        ASSETS.forms.jQueryValidate,
                        ASSETS.forms.inputmask,
                        ASSETS.forms.multiSelect,
                        ASSETS.forms.datepicker,
                        ASSETS.forms.selectboxit,
                        ASSETS.forms.formWizard
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        }).

        state('app.users-Display-Users', {
            url: '/users-Display-Users',
            templateUrl: appHelper.templatePath('users/Display-Users'),
            resolve: {
                resources: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxGlobalize,
                        ASSETS.extra.toastr
                    ]);
                },
                dxCharts: function($ocLazyLoad){
                    return $ocLazyLoad.load([
                        ASSETS.charts.dxCharts
                    ]);
                }
            }
        });
});



