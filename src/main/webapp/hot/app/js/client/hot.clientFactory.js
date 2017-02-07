/**
 * Created by AL1451 on 2/7/17.
 */
'use strict'

angular.module('hot.clientFactory',['ui.bootstrap','ui.router'])
.factory('clientFactory',function($http){

    var hotClientFactory = {};
        hotClientFactory.createClient = function(jsonClient) {
        return $http.post('client/create', jsonClient);

    };


        hotClientFactory.saveSetting = function(settings) {
        return $http.put('settings/update/',settings);
    };
    return hotClientFactory;



});