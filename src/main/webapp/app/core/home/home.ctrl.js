/**
 * Created by nishi on 2016-06-05.
 */
var homeMod = angular.module('homeMod', []);

homeMod.controller('homeCtrl', ['$rootScope', '$scope', '$http', function ($rootScope, $scope, $http) {
    if($rootScope.authenticated) {
        $http.get('/greeting/').success(function (data) {
            $scope.greeting = data;
        })
    }
}])