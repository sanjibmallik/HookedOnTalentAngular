'use strict'

var hotUserControllers = angular.module('hot.userControllers',['ui.bootstrap']);


hotUserControllers.controller('createNewUserCtrl',function($scope,$http){

 $scope.newUser = {};
        //Error messages
        $scope.emailErrorMessage = "That emailId is taken. Try another";
        $scope.userNameErrorMessage = "That username is taken. Try another";
        $scope.contactNumberErrorMessage = "That contact number is taken. Try another";

        //show hides
        $scope.emailExist = false;
        $scope.userNameExist = false;
        $scope.contactNumberExist = false;




        $http({
            method : 'GET',
            url : 'group/groupName/'
        }).then(function successCallback(response) {

                $scope.roles=response.data.groups;
                console.log($scope.roles);


            },function errorCallback(response) {
               console.log(response.statusText);
            });


        $scope.showTSDetailsDiv=false;

            $scope.update = function() {
            if($scope.newUser.role=="TechnicalScreener"){

                $scope.showTSDetailsDiv=true;
            }else{
                $scope.showTSDetailsDiv=false;
            }


        }


        $scope.submitNewUser = function(){

           var data = $scope.newUser;
            console.log(data);

            $http.post('createUser/', data)
                .success(function (data, status, headers, config) {

                })
                .error(function (data, status, header, config){
                    $scope.ResponseDetails = "Data: " + data +
                        "<hr />status: " + status +
                        "<hr />headers: " + header +
                        "<hr />config: " + config;
                });
        };



        $scope.resetUserForm = function(){
            $scope.newUser={};
        }




        $scope.checkUserExist = function(userName){
            $scope.userNameExist = false;

            $http.get('user/userName/'+ userName)
                .success(function (data, status, headers, config) {

                    console.log(data);
                    if(data.length==0){
                        $scope.userNameExist = false;
                    }else{
                        $scope.userNameExist = true;

                    }

                })
                .error(function (data, status, header, config){
                    $scope.ResponseDetails = "Data: " + data +
                        "<hr />status: " + status +
                        "<hr />headers: " + header +
                        "<hr />config: " + config;
                });
        };



    $scope.checkEmailExist = function(email){
        $scope.emailExist = false;
        console.log($scope.newUser.email);

        $http.get('user/emailId/'+ $scope.newUser.email)
            .success(function (data, status, headers, config) {

                console.log(data.length);
                if( data.length==0){
                    $scope.emailExist = false;
                }else{
                    $scope.emailExist = true;

                }

            })
            .error(function (data, status, header, config){
                $scope.ResponseDetails = "Data: " + data +
                    "<hr />status: " + status +
                    "<hr />headers: " + header +
                    "<hr />config: " + config;
            });
    };


    $scope.checkContactNumberExist = function(emailId){
        $scope.contactNumberExist = false;

        $http.get('user/emailId/'+ emailId)
            .success(function (data, status, headers, config) {

                console.log(data);
                $scope.contactNumberExist = true;
            })
            .error(function (data, status, header, config){
                $scope.ResponseDetails = "Data: " + data +
                    "<hr />status: " + status +
                    "<hr />headers: " + header +
                    "<hr />config: " + config;
            });
    };







});





   hotUserControllers.controller('viewAllUserCtrl',function($scope,$http){
        $scope.test='test';





    });
