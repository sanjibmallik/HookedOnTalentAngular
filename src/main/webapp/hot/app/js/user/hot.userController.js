'use strict'

var hotUserControllers = angular.module('hot.userControllers',['ui.bootstrap','ngTable']);


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
                    "primarySkills":$scope.newUser.primarySkills,
                    "secondarySkills":$scope.newUser.secondarySkills,
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



       $rootScope.enableDisableUser = function(userId,status){
           $http.put('user/status/'+userId+'/'+status)
               .success(function (data, status, headers, config) {
                   console.log(data);
               })
               .error(function (data, status, header, config) {
               });
           $rootScope.currentModal.dismiss();

       }


      /* console.log($rootScope.users.length);*/

     /* $rootScope.users = [{"id":1,"firstName":"Philip","lastName":"Kim","emailId":"pkim0@mediafire.com","contactNumber":"Indonesia","userName":"29.107.35.8"},
           {"id":2,"firstName":"Judith","lastName":"Austin","email":"jaustin1@mapquest.com","country":"China","userName":"173.65.94.30"},
           {"id":3,"firstName":"Julie","lastName":"Wells","email":"jwells2@illinois.edu","country":"Finland","userName":"9.100.80.145"},
           {"id":4,"firstName":"Gloria","lastName":"Greene","email":"ggreene3@blogs.com","country":"Indonesia","userName":"69.115.85.157"},
           {"id":50,"firstName":"Andrea","lastName":"Greene","email":"agreene4@fda.gov","country":"Russia","userName":"128.72.13.52"}
       ];*/



     /*  $rootScope.usersTable = new NgTableParams({
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

*/
   });
