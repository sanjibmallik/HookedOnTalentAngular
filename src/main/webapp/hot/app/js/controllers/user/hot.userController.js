'use strict'

var userModule = angular.module('hot.userController',[])
    .controller('createNewUserCtrl',function($scope,$http){
      $scope.test="test";
        $scope.showTSDetailsDiv=false;

        $scope.selectRoles = [ {code: 1, name: 'Admin'}, {code: 2, name: 'Recruiter'},{code: 3, name: 'Technical Screener'},{code: 4, name: 'Client'}];
        $scope.update = function() {
            if($scope.item.code==3){

                $scope.showTSDetailsDiv=true;
            }else{
                $scope.showTSDetailsDiv=false;
            }


        }


    });
