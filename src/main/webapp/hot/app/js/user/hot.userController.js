'use strict'

var hotUserControllers = angular.module('hot.userControllers',['ui.bootstrap','ngTable']);


hotUserControllers.controller('createNewUserCtrl',function($scope,$http){


    $scope.newUserPrimarySkills = [{id: 'primarySkill_1'}];

    $scope.addNewChoice = function() {
        var newItemNo =  $scope.newUserPrimarySkills.length+1;
        $scope.newUserPrimarySkills.push({'id':'primarySkill_'+newItemNo});
    };

    $scope.removeChoice = function() {
        var lastItem =  $scope.newUserPrimarySkills.length-1;
        $scope.newUserPrimarySkills.splice(lastItem);
    };


    $scope.newUserSecondarySkills = [{id: 'secondarySkill_1'}];

    $scope.addNewChoiceSec = function() {
        var newItemNoSec =  $scope.newUserSecondarySkills.length+1;
        $scope.newUserSecondarySkills.push({'id':'secondarySkill_1'+newItemNoSec});
    };

    $scope.removeChoiceSec = function() {
        var lastItemSec =  $scope.newUserSecondarySkills.length-1;
        $scope.newUserSecondarySkills.splice(lastItemSec);
    };



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

                $scope.roles=response.data;
                console.log($scope.roles);


            },function errorCallback(response) {
               console.log(response.statusText);
            });


        $scope.submitNewUser = function(){

           var data = $scope.newUser;


            var jsonUser = {
                "user":{
                    "firstName":$scope.newUser.firstName,
                    "lastName":$scope.newUser.lastName,
                    "userName":$scope.newUser.userName,
                    "emailId":$scope.newUser.emailId,
                    "contactNumber":$scope.newUser.contactNumber,
                    "role":$scope.newUser.role,
                    "alternateContact":$scope.newUser.alternateContact,
                    "addressOne":$scope.newUser.addressOne,
                    "addressTwo":$scope.newUser.addressTwo,
                    "zipCode":$scope.newUser.zipCode,
                    "city":$scope.newUser.city,
                    "state":$scope.newUser.state,
                    "country":$scope.newUser.country

                },

                "technicalScreenerSkills":{
                   "primarySkills": $scope.newUserPrimarySkills,
                    "secondarySkills": $scope.newUserSecondarySkills,
                    "expectedPayRange":$scope.newUser.expectedPayRange

                }
            }


            console.log(jsonUser);

            $http.post('user/create', jsonUser)
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



    $scope.checkEmailExist = function(emailId){
        $scope.emailExist = false;
        $http.get('user/emailId/'+ emailId)
            .success(function (data, status, headers, config) {


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


    $scope.checkContactNumberExist = function(){
        $scope.contactNumberExist = false;
        console.log($scope.newUser.contactNumber);

        $http.get('user/contactNumber/'+ $scope.newUser.contactNumber)
            .success(function (data, status, headers, config) {

                console.log(data);
                if(data.length==0){
                    $scope.contactNumberExist = false;
                }else{
                    $scope.contactNumberExist = true;

                }

            })
            .error(function (data, status, header, config){
                $scope.ResponseDetails = "Data: " + data +
                    "<hr />status: " + status +
                    "<hr />headers: " + header +
                    "<hr />config: " + config;
            });
    };






});





   hotUserControllers.controller('viewAllUserCtrl',function($scope,$rootScope,$http, $filter, NgTableParams){
        $scope.test='test';
       $rootScope.users = [];

     angular.element(document).ready(function(){$http({
         method : 'GET',
         url : 'users'
     }).then(function successCallback(response) {

             $rootScope.users=response.data;
             $rootScope.usersTable = new NgTableParams({
                 page: 1,
                 count: 10
             } , {
                 total:  $rootScope.users.length,
                 getData: function (params) {
                     $scope.data = $rootScope.users;
                     $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
                     $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
                     return $scope.data;
                 }


             });


         },function errorCallback(response) {
             console.log(response.statusText);
         });

     });

       $rootScope.resetUserPassword = function(userId){
           console.log(userId);
           $http.put('user/reset/'+userId)
               .success(function (data, status, headers, config) {
                   console.log(data);
               })
               .error(function (data, status, header, config) {
               });
           $rootScope.currentModal.dismiss();

       }


       $rootScope.updateUser= function(){
           console.log("updateUser function");
              var jsonUser = {
               "user":{
                   "firstName":$rootScope.responseData.firstName,
                   "lastName":$rootScope.responseData.lastName,
                   "userName":$rootScope.responseData.userName,
                   "emailId":$rootScope.responseData.emailId,
                   "contactNumber":$rootScope.responseData.contactNumber,
                   "role":$rootScope.responseData.role,
                   "alternateContact":$rootScope.responseData.alternateContact,
                   "addressOne":$rootScope.responseData.addressOne,
                   "addressTwo":$rootScope.responseData.addressTwo,
                   "zipCode":$rootScope.responseData.zipCode,
                   "city":$rootScope.responseData.city,
                   "state":$rootScope.responseData.state,
                   "country":$rootScope.responseData.country

               },

               "technicalScreenerSkills":{
                   "primarySkills":$rootScope.responseData.primarySkills,
                   "secondarySkills":$rootScope.responseData.secondarySkills,
                   "expectedPayRange":$rootScope.responseData.expectedPayRange

               }
           }



           $http.put('user/update/',jsonUser)
               .success(function (data, status, headers, config) {
                   console.log(data);
               })
               .error(function (data, status, header, config) {
               });
           $rootScope.currentModal.dismiss();

       }






       $rootScope.enableDisableUser = function(userId,status){
           $http.put('user/status/'+userId+'/'+status)
               .success(function (data, status, headers, config) {
                   console.log(data);
               })
               .error(function (data, status, header, config) {
               });
           $rootScope.currentModal.dismiss();

       }


   });
