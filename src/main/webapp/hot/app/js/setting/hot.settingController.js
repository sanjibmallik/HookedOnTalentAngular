'use strict'

var hotSettingControllers = angular.module('hot.settingControllers',['ui.bootstrap','ui.router']);



hotSettingControllers.controller('settingController',function($scope,$http,$state,hotSettingFactory){



    angular.element(document).ready(function(){



        hotSettingFactory.getSettings().then(function() {

            $scope.settings = hotSettingFactory.data();

            console.log($scope.settings);
        });


    });




    $scope.saveSetting = function() {

           hotSettingFactory.saveSetting($scope.settings).then(function() {
               $state.go($state.current, {}, {reload: true});
               $scope.settings = hotSettingFactory.data();
            console.log($scope.settings);
        });
    };





});

