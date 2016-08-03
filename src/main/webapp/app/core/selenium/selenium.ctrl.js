/**
 * Created by nishi on 2016-06-19.
 */
var seleniumMod = angular.module('seleniumMod', []);

seleniumMod
    .controller('seleniumCtrl', ['$rootScope', '$scope', '$location', function ($rootScope, $scope, $location) {
        if($rootScope.authenticated) {
            $location.path("/selenium");
            $scope.error = false;
        } else {
            $location.path("/home");
            $scope.error = true;
        }
    }])
    .controller('byNameCtrl', ['$scope', function ($scope) {
        $scope.clickPreviousButton = function () {
            $scope.text = "Previous";
        }
        $scope.clickNextButton = function () {
            $scope.text = "Next";
        }
    }]);
