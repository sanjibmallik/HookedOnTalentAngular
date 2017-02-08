'use strict'

var hotRequirementControllers = angular.module('hot.requirementControllers',['ui.bootstrap','ngTable','ui.router', 'ngAnimate', 'toastr']);


hotRequirementControllers.controller('createNewRequirementCtrl',function($scope,$http,hotRequirementFactory){



    hotRequirementFactory.getAllClientDetails().success(function(response){
        console.log("client details");
        console.log(response);

    }).error(function(data, status, headers, config){

        });

    $scope.submitNewRequirement = function(){
            var jsonRequirement = {
            "clientDetails":{

                "clientName":$scope.newClient.clientName,
                "industry":$scope.newClient.industry,
                "engagementModel":$scope.newClient.engagementModel,
                "federalId":$scope.newClient.federalId,
                "faxNumber":$scope.newClient.faxNumber,
                "contactNumber":$scope.newClient.contactNumber,
                "alternateContact":$scope.newClient.alternateContact,
                "addressOne":$scope.newClient.addressOne,
                "addressTwo":$scope.newClient.addressTwo,
                "zipCode":$scope.newClient.zipCode,
                "city":$scope.newClient.city,
                "state":$scope.newClient.state,
                "country":$scope.newClient.country,
                "note":$scope.newClient.note


            },
            "clientContacts":{
                "userName":$scope.newClient.userName,
                "firstName":$scope.newClient.firstName,
                "contactFullName":$scope.newClient.contactFullName,
                "contactNumber":$scope.newClient.contactNumber,
                "alternateContact":$scope.newClient.alternateContact,
                "emailId":$scope.newClient.emailId,
                "faxNumber":$scope.newClient.faxNumber,
                "addressOne":$scope.newClient.addressOne,
                "addressTwo":$scope.newClient.addressTwo,
                "zipCode":$scope.newClient.zipCode,
                "city":$scope.newClient.city,
                "state":$scope.newClient.state,
                "country":$scope.newClient.country,
                "note":$scope.newClient.note,
                "sendUserEmail":$scope.newClient.sendUserEmail


            },
            "user":{
                "userName":$scope.newClient.userName,
                "firstName":$scope.newClient.firstName,
                "contactFullName":$scope.newClient.contactFullName,
                "contactNumber":$scope.newClient.contactNumber,
                "alternateContact":$scope.newClient.alternateContact,
                "emailId":$scope.newClient.emailId,
                "faxNumber":$scope.newClient.faxNumber,
                "addressOne":$scope.newClient.addressOne,
                "addressTwo":$scope.newClient.addressTwo,
                "zipCode":$scope.newClient.zipCode,
                "city":$scope.newClient.city,
                "state":$scope.newClient.state,
                "country":$scope.newClient.country,
                "note":$scope.newClient.note,
                "sendUserEmail":$scope.newClient.sendUserEmail

            }


        }

        console.log(jsonRequirement);

        $http.post('requirement/create', jsonRequirement)
            .success(function (data, status, headers, config) {
                console.log(data);

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
                    $scope.userNameExist = true
                    $scope.newUser.userName="";

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
                    $scope.newUser.emailId="";

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
                    $scope.newUser.contactNumber = "";

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




hotClientControllers.controller('viewAllRequirementsCtrl',function($scope,$rootScope,$http, $filter, NgTableParams){

    $rootScope.users = [];

    angular.element(document).ready(function(){$http({
        method : 'GET',
        url : 'requirements'
    }).then(function successCallback(response) {

            $rootScope.requirements=response.data;
            console.log( $rootScope.requirements);
            $rootScope.requirementsTable = new NgTableParams({
                page: 1,
                count: 10
            } , {
                total:  $rootScope.requirements.length,
                getData: function (params) {
                    $scope.data = $rootScope.requirements;
                    $scope.data = params.sorting() ? $filter('orderBy')($scope.requirements, params.orderBy()) : $scope.requirements;
                    $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
                    $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
                    return $scope.data;
                }


            });


        },function errorCallback(response) {
            console.log(response.statusText);
        });

    });



});








