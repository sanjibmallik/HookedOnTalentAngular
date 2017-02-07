/**
 * Created by AL1451 on 2/7/17.
 */
'use strict'

var hotProfileControllers = angular.module('hot.profileControllers',['ui.bootstrap','ui.router','ngImgCrop','ngFileUpload', 'ngAnimate', 'toastr', 'hot.profileFactory' ]);


hotProfileControllers.controller('profileCtrl',function($scope,$http,$state,Upload){

    angular.element(document).ready(function(){

        $scope.loginUser = {
            "firstName":"Sanjib",
            "lastName":"Mallik",
            "userName":"Sanjib"

        };
    });

    $scope.resetLoginForm = function(){
        $scope.loginUser = {};

    };






});

