'use strict'

var hotSettingControllers = angular.module('hot.settingControllers',['ui.bootstrap','ui.router']);



hotSettingControllers.controller('settingController',function($scope,$http,$state){



    angular.element(document).ready(function(){$http({
        method : 'GET',
        url : 'settings'
    }).then(function successCallback(response) {

            $scope.settings=response.data;
            console.log($scope.settings);

            $scope.saveSetting

        },function errorCallback(response) {
            console.log(response.statusText);
        });

    });

    $scope.saveSetting = function(){
        console.log("setting page");


        $http.put('settings/update/',$scope.settings)
            .success(function (data, status, headers, config) {
                console.log(data);
                $state.go($state.current, {}, {reload: true});
            })
            .error(function (data, status, header, config) {
            });


    }





});

