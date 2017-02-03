'use strict'

var hotUserControllers = angular.module('hot.userControllers',['ui.bootstrap','ngTable','ui.router','ngImgCrop','ngFileUpload', 'ngAnimate', 'toastr']);


hotUserControllers.controller('createNewUserCtrl',function($scope,$http,$state,Upload){

/*To disable button start*/
    $scope.isDisabled = false;
    $scope.disableClick = function(valid) {
        if(valid && !$scope.isDisabled) {
            $scope.isDisabled = true;
        }
        return false;
    }
    /*To disable button end*/



    /*Image crop start*/
    $scope.imageHide=false;
    $scope.myImage='';
    $scope.myCroppedImage='';

    var handleFileSelect=function(evt) {
        var file=evt.currentTarget.files[0];
        var reader = new FileReader();
        reader.onload = function (evt) {
            $scope.$apply(function($scope){
                $scope.myImage=evt.target.result;
            });
        };
        reader.readAsDataURL(file);
    };
    angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);
    /*Image crop end*/

    $scope.allSkills = [{}];

    $scope.addNewRow = function() {
        var newItemNo =  $scope.allSkills.length+1;
        $scope.allSkills.push({});
    };


    $scope.removeRow = function() {
        if($scope.allSkills.length>1){
        var lastItem =  $scope.allSkills.length-1;
        $scope.allSkills.splice(lastItem);
        }
    };


    $scope.newUser = {};
        //Error messages
        $scope.emailErrorMessage = "email Id already exist";
        $scope.userNameErrorMessage = "username already exist";
        $scope.contactNumberErrorMessage = "contact number already exist";

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
                    "expectedPayRange":$scope.newUser.expectedPayRangeFrom+"-"+$scope.newUser.expectedPayRangeTo+"-"+$scope.newUser.expectedPayRangeCurrency


                },

                "technicalScreenerSkills":{
                   "primarySkills":$scope.userPrimarySkills,
                    "secondarySkills":$scope.userSecodarySkills

                }/*,
                "userProfile":$scope.profile,
                "userImage":$scope.myCroppedImage*/
            }


            console.log(jsonUser);

            $http.post('user/create', jsonUser)
                .success(function (data, status, headers, config) {
                    console.log(data);
                    console.log(status);
                    if(status==201){
                      $state.go('app.users-Display-Users', {});
                    }

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





   hotUserControllers.controller('viewAllUserCtrl',function($scope,$rootScope,$http, $filter, NgTableParams,$state, toastr){
        $scope.test='test';
       $rootScope.users = [];
       $scope.showUser="all";

       $scope.activeMenu = 'Home';

     angular.element(document).ready(function(){

         $http({
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
                     $scope.data = params.sorting() ? $filter('orderBy')($scope.users, params.orderBy()) : $scope.users;
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

           $rootScope.userPrimarySkills = [];
           $rootScope.userSecodarySkills = [];


           for(var i=0;$rootScope.responseData.technicalScreenerDetailsDSkillsSet.length>i;i++){


               if($rootScope.responseData.technicalScreenerDetailsDSkillsSet[i].skillType=="PrimarySkill"){

                   $rootScope.userPrimarySkills.push($scope.responseData.technicalScreenerDetailsDSkillsSet[i]);

               }else{

                   $rootScope.userSecodarySkills.push($scope.responseData.technicalScreenerDetailsDSkillsSet[i])

               }
           }

          if($rootScope.responseData.role!="TechnicalScreener"){

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
                   "country":$rootScope.responseData.country
                  },
               "technicalScreenerSkills":{


               }
              }

           console.log(jsonUser);
           $rootScope.currentModal.dismiss();

             $http.put('user/update/',jsonUser)
               .success(function (data, status, headers, config) {
                   console.log(data);
                     toastr.success('Hello world!', 'Toastr fun!');
                   $state.go($state.current, {}, {reload: true});
               })
               .error(function (data, status, header, config) {
               });


          }else{

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
                   "expectedPayRange":$rootScope.responseData.expectedPayRangeFrom+"-"+$rootScope.responseData.expectedPayRangeTo+"-"+$rootScope.responseData.expectedPayRangeCurrency

               },

               "technicalScreenerSkills":{
                   "primarySkills":$rootScope.userPrimarySkills,
                   "secondarySkills":$rootScope.userSecodarySkills
                        }
                 }

           console.log(jsonUser);
           $rootScope.currentModal.dismiss();
            $http.put('user/update/',jsonUser)
               .success(function (data, status, headers, config) {
                   console.log(data);
                   $state.go($state.current, {}, {reload: true});

               })
               .error(function (data, status, header, config) {
               });

          }

       }






       $rootScope.enableDisableUser = function(userId,status){
           $http.put('user/status/'+userId+'/'+status)
               .success(function (data, status, headers, config) {
                   console.log(data);
                   $state.go($state.current, {}, {reload: true});
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
           if($rootScope.responseData.technicalScreenerDetailsDSkillsSet.length>1) {
               var lastItem = $rootScope.responseData.technicalScreenerDetailsDSkillsSet.length - 1;
               $rootScope.responseData.technicalScreenerDetailsDSkillsSet.splice(lastItem);
           }
       };





   });
