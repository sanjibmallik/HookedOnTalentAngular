/**
 * Created by AL1451 on 2/7/17.
 */

'use strict'

angular.module('hot.candidateFactory',[])
.factory('hotCandidateFactory',function($http){
        var hotCandidateFactory = {};

        hotCandidateFactory.createCandidate = function(candidate){
            return $http.post('candidate/create/',candidate);
        };

        hotCandidateFactory.getAllCandidate = function(){
            return $http.get('candidates');
        };

        hotCandidateFactory.getCandidateById = function(id){
            return $http.get('candidate'+id);
        };

        hotCandidateFactory.getCandidateByEmail = function(email){
            return $http.get('candidate'+email);
        };

        hotCandidateFactory.getCandidateByEmail = function(contactNumber){
            return $http.get('candidate'+contactNumber);
        };

        hotCandidateFactory.updateCandidate = function(candidate){
            return $http.put('candidate',candidate);
        };

        hotCandidateFactory.getApprovedCandidates = function(requirementId){
            return $http.post('candidate/approved/',requirementId);
        };

        return hotCandidateFactory;



    });