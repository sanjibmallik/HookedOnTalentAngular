'use strict'

var hotSettingControllers = angular.module('hot.settingControllers',['ui.bootstrap','ui.router','toastr','ngAnimate']);



hotSettingControllers.controller('settingController',function($scope,$http,$state,hotSettingFactory){



    angular.element(document).ready(function(){

        hotSettingFactory.getSettings().success(function(response){
            console.log(response);
            $scope.settings = response;
        }).error(function(){

            });
    });


    $scope.saveSetting = function() {

        hotSettingFactory.saveSetting($scope.settings).success(function (response) {
                             toastr.success("Setting saved successfully");

                             $state.go($state.current, {}, {reload: true});
                             }).error(function(){
                             toastr.error("Setting not saved");

            });
    }
});






