'use strict'

var userModule = angular.module('hot.userControllers',['ui.bootstrap'])
    .controller('createNewUserCtrl',function($scope,$http){

 $scope.newUser = {};
        //Error messages
        $scope.emailErrorMessage = "That emailId is taken. Try another";
        $scope.userNameErrorMessage = "That username is taken. Try another";
        $scope.contactErrorMessage = "That contact number is taken. Try another";

        //show hides
        $scope.emailExist = false;
        $scope.userNameExist = false;
        $scope.contactNumberExist = false;



        $scope.checkEmailExist = function(emailId){
           console.log(emailId);

        }

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
                    $scope.PostDataResponse = data;
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

            $http.get('userNameExist/'+ userName)
                .success(function (data, status, headers, config) {
                    $scope.PostDataResponse = data;
                    console.log(data);
                    $scope.emailExist = true;
                })
                .error(function (data, status, header, config){
                    $scope.ResponseDetails = "Data: " + data +
                        "<hr />status: " + status +
                        "<hr />headers: " + header +
                        "<hr />config: " + config;
                });
        };

    })


    .controller('viewAllUserCtrl',function($scope,$http){
        $scope.test='test';


/*

        $scope.tableRowExpanded = false;
        $scope.tableRowIndexCurrExpanded = "";
        $scope.tableRowIndexPrevExpanded = "";
        $scope.storeIdExpanded = "";
        $scope.dayDataCollapse = [true, true, true, true, true, true];

        $scope.dayDataCollapseFn = function () {
            for (var i = 0; storeDataModel.storedata.length - 1; i += 1) {
                $scope.dayDataCollapse.append('true');
            }
        };



        $scope.selectTableRow = function (index, storeId) {
            if ($scope.dayDataCollapse === 'undefined') {
                $scope.dayDataCollapse = $scope.dayDataCollapseFn();
            } else {

                if ($scope.tableRowExpanded === false && $scope.tableRowIndexCurrExpanded === "" && $scope.storeIdExpanded === "") {
                    $scope.tableRowIndexPrevExpanded = "";
                    $scope.tableRowExpanded = true;
                    $scope.tableRowIndexCurrExpanded = index;
                    $scope.storeIdExpanded = storeId;
                    $scope.dayDataCollapse[index] = false;
                } else if ($scope.tableRowExpanded === true) {
                    if ($scope.tableRowIndexCurrExpanded === index && $scope.storeIdExpanded === storeId) {
                        $scope.tableRowExpanded = false;
                        $scope.tableRowIndexCurrExpanded = "";
                        $scope.storeIdExpanded = "";
                        $scope.dayDataCollapse[index] = true;
                    } else {
                        $scope.tableRowIndexPrevExpanded = $scope.tableRowIndexCurrExpanded;
                        $scope.tableRowIndexCurrExpanded = index;
                        $scope.storeIdExpanded = storeId;
                        $scope.dayDataCollapse[$scope.tableRowIndexPrevExpanded] = true;
                        $scope.dayDataCollapse[$scope.tableRowIndexCurrExpanded] = false;
                    }
                }
            }
        };

        $scope.storeDataModel = {
            "metadata": {
                "storesInTotal": "25",
                "storesInRepresentation": "6"
            },
            "storedata": [{
                "store": {
                    "storeId": "1000",
                    "storeName": "Store 1",
                    "storePhone": "+46 31 1234567",
                    "storeAddress": "Avenyn 1",
                    "storeCity": "Gothenburg"
                },
                "data": {
                    "startDate": "2013-07-01",
                    "endDate": "2013-07-02",
                    "costTotal": "100000",
                    "salesTotal": "150000",
                    "revenueTotal": "50000",
                    "averageEmployees": "3.5",
                    "averageEmployeesHours": "26.5",
                    "dayData": [{
                        "date": "2013-07-01",
                        "cost": "50000",
                        "sales": "71000",
                        "revenue": "21000",
                        "employees": "3",
                        "employeesHoursSum": "24"
                    }, {
                        "date": "2013-07-02",
                        "cost": "50000",
                        "sales": "79000",
                        "revenue": "29000",
                        "employees": "4",
                        "employeesHoursSum": "29"
                    }]
                }
            }, {
                "store": {
                    "storeId": "2000",
                    "storeName": "Store 2",
                    "storePhone": "+46 8 9876543",
                    "storeAddress": "Drottninggatan 100",
                    "storeCity": "Stockholm"
                },
                "data": {
                    "startDate": "2013-07-01",
                    "endDate": "2013-07-02",
                    "costTotal": "170000",
                    "salesTotal": "250000",
                    "revenueTotal": "80000",
                    "averageEmployees": "4.5",
                    "averageEmployeesHours": "35",
                    "dayData": [{
                        "date": "2013-07-01",
                        "cost": "85000",
                        "sales": "120000",
                        "revenue": "35000",
                        "employees": "5",
                        "employeesHoursSum": "38"
                    }, {
                        "date": "2013-07-02",
                        "cost": "85000",
                        "sales": "130000",
                        "revenue": "45000",
                        "employees": "4",
                        "employeesHoursSum": "32"
                    }]
                }
            }, {
                "store": {
                    "storeId": "3000",
                    "storeName": "Store 3",
                    "storePhone": "+1 99 555-1234567",
                    "storeAddress": "Elm Street",
                    "storeCity": "New York"
                },
                "data": {
                    "startDate": "2013-07-01",
                    "endDate": "2013-07-02",
                    "costTotal": "2400000",
                    "salesTotal": "3800000",
                    "revenueTotal": "1400000",
                    "averageEmployees": "25.5",
                    "averageEmployeesHours": "42",
                    "dayData": [{
                        "date": "2013-07-01",
                        "cost": "1200000",
                        "sales": "1600000",
                        "revenue": "400000",
                        "employees": "23",
                        "employeesHoursSum": "41"
                    }, {
                        "date": "2013-07-02",
                        "cost": "1200000",
                        "sales": "2200000",
                        "revenue": "1000000",
                        "employees": "28",
                        "employeesHoursSum": "43"
                    }]
                }
            }, {
                "store": {
                    "storeId": "4000",
                    "storeName": "Store 4",
                    "storePhone": "0044 34 123-45678",
                    "storeAddress": "Churchill avenue",
                    "storeCity": "London"
                },
                "data": {
                    "startDate": "2013-07-01",
                    "endDate": "2013-07-02",
                    "costTotal": "1700000",
                    "salesTotal": "2300000",
                    "revenueTotal": "600000",
                    "averageEmployees": "13.0",
                    "averageEmployeesHours": "39",
                    "dayData": [{
                        "date": "2013-07-01",
                        "cost": "850000",
                        "sales": "1170000",
                        "revenue": "320000",
                        "employees": "14",
                        "employeesHoursSum": "39"
                    }, {
                        "date": "2013-07-02",
                        "cost": "850000",
                        "sales": "1130000",
                        "revenue": "280000",
                        "employees": "12",
                        "employeesHoursSum": "39"
                    }]
                }
            }, {
                "store": {
                    "storeId": "5000",
                    "storeName": "Store 5",
                    "storePhone": "+33 78 432-98765",
                    "storeAddress": "Le Big Mac Rue",
                    "storeCity": "Paris"
                },
                "data": {
                    "startDate": "2013-07-01",
                    "endDate": "2013-07-02",
                    "costTotal": "1900000",
                    "salesTotal": "2500000",
                    "revenueTotal": "600000",
                    "averageEmployees": "16.0",
                    "averageEmployeesHours": "37",
                    "dayData": [{
                        "date": "2013-07-01",
                        "cost": "950000",
                        "sales": "1280000",
                        "revenue": "330000",
                        "employees": "16",
                        "employeesHoursSum": "37"
                    }, {
                        "date": "2013-07-02",
                        "cost": "950000",
                        "sales": "1220000",
                        "revenue": "270000",
                        "employees": "16",
                        "employeesHoursSum": "37"
                    }]
                }
            }, {
                "store": {
                    "storeId": "6000",
                    "storeName": "Store 6",
                    "storePhone": "+49 54 7624214",
                    "storeAddress": "Bier strasse",
                    "storeCity": "Berlin"
                },
                "data": {
                    "startDate": "2013-07-01",
                    "endDate": "2013-07-02",
                    "costTotal": "1800000",
                    "salesTotal": "2200000",
                    "revenueTotal": "400000",
                    "averageEmployees": "11.0",
                    "averageEmployeesHours": "39",
                    "dayData": [{
                        "date": "2013-07-01",
                        "cost": "900000",
                        "sales": "1100000",
                        "revenue": "200000",
                        "employees": "12",
                        "employeesHoursSum": "39"
                    }, {
                        "date": "2013-07-02",
                        "cost": "900000",
                        "sales": "1100000",
                        "revenue": "200000",
                        "employees": "10",
                        "employeesHoursSum": "39"
                    }]
                }
            }],
            "_links": {
                "self": {
                    "href": "/storedata/between/2013-07-01/2013-07-02"
                }
            }
        };

*/




    });
