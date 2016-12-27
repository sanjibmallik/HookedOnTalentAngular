'use strict'

var userModule = angular.module('hot.userController',[])
    .controller('createNewUserCtrl',function($scope,$http){

 $scope.newUser = {};


        $http({
            method : 'GET',
            url : 'getGroupsName/'
        }).then(function successCallback(response) {

                $scope.roles=response.data;
                console.log($scope.roles);

            },function errorCallback(response) {
              /* console.log(response.statusText);*/
            });


        $scope.showTSDetailsDiv=false;

            $scope.update = function() {
            if($scope.newUser.selectRoles=="TechnicalScreener"){

                $scope.showTSDetailsDiv=true;
            }else{
                $scope.showTSDetailsDiv=false;
            }


        }


    });
