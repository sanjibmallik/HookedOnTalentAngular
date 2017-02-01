'user strict'



angular.module('hot.settingFactory',['ui.bootstrap','ui.router'])

    // .factory('hotSettingFactory', function ($http,$state,$q) {
    //
    //     var deffered = $q.defer();
    //     var data = [];
    //
    //     var factory={};
    //
    //
    //    factory.saveSetting =function (settings) {
    //         console.log(settings);
    //
    //         $http.put('settings/update/',settings)
    //             .success(function (data, status, headers, config) {
    //                 console.log(data);
    //                 console.log("setting saved using factory");
    //                 $state.go($state.current, {}, {reload: true});
    //             })
    //             .error(function (data, status, header, config) {
    //
    //             });
    //
    //     },
    //
    //
    //
    //     factory.getSettings = function () {
    //         console.log("inside getsetting");
    //
    //         $http({
    //             method : 'GET',
    //             url : 'settings'
    //         }).then(function successCallback(response) {
    //
    //
    //
    //
    //
    //         },function errorCallback(response) {
    //             console.log(response.statusText);
    //         });
    //
    //
    //
    //
    //      }
    //
    //
    //
    //
    //
    //
    // return factory;factory

// });

.factory('hotSettingFactory', function($http, $q, $state){
    var deffered = $q.defer();
    var data = [];
    var hotSettingFactory = {};

    hotSettingFactory.getSettings = function() {
        $http({
                 method : 'GET',
                 url : 'settings'
               })
            .success(function (d) {
                data = d;
                console.log(d);
                deffered.resolve();
            });
        return deffered.promise;
    };


    hotSettingFactory.saveSetting = function(settings) {



            $http.put('settings/update/',settings)
                    .success(function (d) {
                        console.log("successfully saved from factory");
                        data = d;
                        console.log(d);
                        deffered.resolve();

                    })
                   /* .error(function (data, status, header, config) {

                    });*/

        return deffered.promise;
    };


    hotSettingFactory.data = function() {
        return data;
    };

    return hotSettingFactory;
});