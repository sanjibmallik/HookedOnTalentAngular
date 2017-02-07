'use strict'

var hotCandidateControllers = angular.module('hot.candidateControllers',['ui.bootstrap','ngTable','ui.router','ngImgCrop','ngFileUpload', 'ngAnimate', 'toastr']);



hotCandidateControllers.controller('createCandidateCtrl',function($scope,$http,$state,Upload,hotCandidateFactory,toastr){




});

hotCandidateControllers.controller('viewCandidateCtrl',function($scope,$http,$state,Upload,hotCandidateFactory,toastr,NgTableParams){
    angular.element(document).ready(function(){
        hotCandidateFactory.getAllCandidate()
            .success(function(response){
                console.log(response);

                $scope.users=response;

                $scope.usersTable = new NgTableParams({
                    page: 1,
                    count: 10
                } , {
                    total:  $scope.users.length,
                    getData: function (params) {
                        $scope.data = $scope.users;
                        $scope.data = params.sorting() ? $filter('orderBy')($scope.users, params.orderBy()) : $scope.users;
                        $scope.data = params.filter() ? $filter('filter')($scope.data, params.filter()) : $scope.data;
                        $scope.data = $scope.data.slice((params.page() - 1) * params.count(), params.page() * params.count());
                        return $scope.data;
                    }


                });



            })
            .error(function(){
                toastr.error("Could not get candidates");

            });

    });




});

