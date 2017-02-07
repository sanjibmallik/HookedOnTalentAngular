'user strict'
angular.module('hot.settingFactory',['ui.bootstrap','ui.router'])
.factory('hotSettingFactory', function($http){

    var hotSettingFactory = {};
    hotSettingFactory.getSettings = function() {
       return $http({
                 method : 'GET',
                 url : 'settings'
               });

    };


    hotSettingFactory.saveSetting = function(settings) {
           return $http.put('settings/update/',settings);
    };
 return hotSettingFactory;
});