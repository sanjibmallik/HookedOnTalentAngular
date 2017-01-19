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

        $scope.userPrimarySkills = [];
        $scope.userSecodarySkills = [];


        for(var i=0;$scope.allSkills.length>i;i++){

            if($scope.allSkills[i].skillType=="PrimarySkill"){

                $scope.userPrimarySkills.push($scope.allSkills[i]);

            }else{

                $scope.userSecodarySkills.push($scope.allSkills[i])

            }
        }


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
                "country":$scope.newUser.country,
                "expectedPayRange":$scope.newUser.expectedPayRange


            },

            "technicalScreenerSkills":{
                "primarySkills":$scope.userPrimarySkills,
                "secondarySkills":$scope.userSecodarySkills

            }
        }


        console.log(jsonUser);

        $http.post('user/create', jsonUser)
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
    $scope.test='test';
    $rootScope.users = [];
    $scope.showUser="all";

    $scope.activeMenu = 'All';

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



    /*$rootScope.updateUser= function(){
        console.log("updateUser function");

        $rootScope.userPrimarySkills = [];
        $rootScope.userSecodarySkills = [];


        for(var i=0;$rootScope.responseData.technicalScreenerDetailsDSkillsSet.length>i;i++){


            if($rootScope.responseData.technicalScreenerDetailsDSkillsSet[i].skillType=="PrimarySkill"){

                $rootScope.userPrimarySkills.push($scope.responseData.technicalScreenerDetailsDSkillsSet[i]);

            }else{

                $rootScope.userSecodarySkills.push($scope.responseData.technicalScreenerDetailsDSkillsSet[i])

            }
        }



        var jsonUser = {
            "user":{
                "id":$rootScope.responseData.id,
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
                "country":$rootScope.responseData.country,
                "expectedPayRange":$rootScope.responseData.expectedPayRange

            },

            "technicalScreenerSkills":{
                "primarySkills":$rootScope.userPrimarySkills,
                "secondarySkills":$rootScope.userSecodarySkills


            }


        }

        console.log(jsonUser);



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
    $rootScope.addNewRow = function(){
        console.log($rootScope.responseData.technicalScreenerDetailsDSkillsSet.length);

        var newItemNo =  $rootScope.responseData.technicalScreenerDetailsDSkillsSet.length+1;
        $rootScope.responseData.technicalScreenerDetailsDSkillsSet.push({});
        console.log($rootScope.responseData.technicalScreenerDetailsDSkillsSet);
    };



    $rootScope.removeRow = function() {
        var lastItem =  $rootScope.responseData.technicalScreenerDetailsDSkillsSet.length-1;
        $rootScope.responseData.technicalScreenerDetailsDSkillsSet.splice(lastItem);
    };



    */


});








