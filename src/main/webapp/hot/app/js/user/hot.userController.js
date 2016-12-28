'use strict'

var userModule = angular.module('hot.userControllers',[])
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


        $scope.submitNewUser = function(){
            console.log($scope.newUser);
        }

        $scope.resetUserForm = function(){
            $scope.newUser={};
        }


    })


    .controller('viewAllUserCtrl',function($scope,$http){
        $scope.test='test';

        $scope.toggleDetail = function($index) {
            //$scope.isVisible = $scope.isVisible == 0 ? true : false;
            $scope.activePosition = $scope.activePosition == $index ? -1 : $index;
        };

    });
