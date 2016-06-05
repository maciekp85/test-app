/**
 * Created by nishi on 2016-06-05.
 */
var loginMod = angular.module('loginMod', []);

loginMod.controller('navigationCtrl',['$rootScope','$scope','$http', '$location', function($rootScope, $scope, $http, $location) {

    var authenticate = function(credentials, callback) {

        if(typeof callback != "function") {
            callback = false;
        }

        var headers = credentials ? { authorization : "Basic "
        + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers : headers}).success(function(data) {
            if (data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }).error(function() {
            $rootScope.authenticated = false;
            callback && callback();
        });
    };

    authenticate();
    $scope.credentials = {};

    $scope.login = function() {
        authenticate($scope.credentials, function() {
            if ($rootScope.authenticated) {
                $location.path("/home");
                $scope.error = false;
            } else {
                $location.path("/login");
                $scope.error = true;
            }
        });
    };
    $scope.logout = function() {
        $http.post('logout', {}).success(function() {
            $rootScope.authenticated = false;
            $location.path("/home");
        }).error(function() {
            $rootScope.authenticated = false;
            $location.path("/home");
        });
    };
}])