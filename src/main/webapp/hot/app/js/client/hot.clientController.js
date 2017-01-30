'use strict'

var hotClientControllers = angular.module('hot.clientControllers',['ui.bootstrap','ngTable']);


hotClientControllers.controller('createNewClientCtrl',function($scope,$http){


    $scope.newClient = {};
    //Error messages
    $scope.emailErrorMessage = "email Id already exist";
    $scope.userNameErrorMessage = "username already exist";
    $scope.contactNumberErrorMessage = "contact number already exist";

    //show hides
    $scope.emailExist = false;
    $scope.userNameExist = false;
    $scope.contactNumberExist = false;




    $scope.submitNewClient = function(){



        var data = $scope.newClient;


        var jsonClient = {
            "clientContacts": {

                "addressOne": $scope.newClient.addressOne,
                "addressTwo": $scope.newClient.addressTwo,
                "alternateContact": $scope.newClient.alternateContact,
                "city": $scope.newClient.city,
                "contactFullName": $scope.newClient.firstName+$scope.newClient.lastName,
                "contactNumber": $scope.newClient.contactNumber,
                "country": $scope.newClient.country,
                "emailId": $scope.newClient.emailId,
                "faxNumber": $scope.newClient.faxNumber,
                "firstName": $scope.newClient.firstName,
                "lastName": $scope.newClient.lastName,

                "sendUserEmail": "No",
                "state": $scope.newClient.state,

                "userName": $scope.newClient.userName,
                "zipCode": $scope.newClient.zipCode
            },
            "clientDetails": {

                "addressOne": $scope.newClient.addressOne,
                "addressTwo": $scope.newClient.addressTwo,
                "alternateContact": $scope.newClient.alternateContact,
                "city": $scope.newClient.city,

                "clientName": $scope.newClient.clientName,
                "contactNumber": $scope.newClient.contactNumber,
                "country": $scope.newClient.country,

                "engagementModel": $scope.newClient.engagementModel,
                "faxNumber": $scope.newClient.faxNumber,
                "federalId": $scope.newClient.federalId,
                "industry": $scope.newClient.industry,

                "state": $scope.newClient.state,
                "websiteUrl": $scope.newClient.websiteUrl,
                "zipCode": $scope.newClient.zipCode
            },
            "user": {
                "addressOne": $scope.newClient.addressOne,
                "addressTwo": $scope.newClient.addressTwo,
                "alternateContact": $scope.newClient.alternateContact,
                "city": $scope.newClient.city,
                "contactNumber": $scope.newClient.contactNumber,
                "country": $scope.newClient.country,

                "emailId": $scope.newClient.emailId,

                "firstName": $scope.newClient.firstName,
                "lastName": $scope.newClient.lastName,
                "userName": $scope.newClient.userName,

                "role": "Client",
                "state": $scope.newClient.state,
                "zipCode": $scope.newClient.zipCode
            }
        }


        console.log(jsonClient);

        $http.post('client/create', jsonClient)
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




hotClientControllers.controller('viewAllClientCtrl',function($scope,$rootScope,$http, $filter, NgTableParams){


    angular.element(document).ready(function(){$http({
        method : 'GET',
        url : 'clients'
    }).then(function successCallback(response) {

            $rootScope.clients=response.data;
            console.log( $rootScope.clients);
            $rootScope.clientsTable = new NgTableParams({
                page: 1,
                count: 10
            } , {
                total:  $rootScope.clients.length,
                getData: function (params) {
                    $scope.data = $rootScope.clients;
                    $scope.data = params.sorting() ? $filter('orderBy')($scope.clients, params.orderBy()) : $scope.clients;
                    $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
                    $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
                    return $scope.data;
                }


            });


        },function errorCallback(response) {
            console.log(response.statusText);
        });

    });






    $rootScope.clientActivate = function (clientId) {

        console.log(clientId);
        $http.put('client/activate/'+clientId)
            .success(function (data, status, headers, config) {
                console.log(data);
                $state.go($state.current, {}, {reload: true});
            })
            .error(function (data, status, header, config) {
            });
        $rootScope.currentModal.dismiss();
    }




    $rootScope.updateClientContactDetails = function () {
        console.log("client contact detils");

    }




});








