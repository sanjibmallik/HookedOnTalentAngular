/**
 * Created by AL1451 on 2/8/17.
 */
'use strict'

angular.module('hot.requirementFactory',['ui.bootstrap','ngTable'])
    .factory('hotRequirementFactory',function($http){
        var hotRequirementFactory = {};

        hotRequirementFactory.getAllClientDetails = function(){
           return $http({
               method : 'GET',
               url : 'clients'
           });

        };

        hotRequirementFactory.createRequirement= function(requirement){
            return $http.post('requirement/create',requirement);

        }

        hotRequirementFactory.getAllRequirements = function(){
            return $http({
                method : 'GET',
                url : 'requirements'
            });

        }



        return hotRequirementFactory;






    });
