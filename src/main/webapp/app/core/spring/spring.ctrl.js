/**
 * Created by nishi on 2016-06-05.
 */
var springMod = angular.module('springMod', []);

springMod    
    .controller('springCtrl', ['$rootScope', '$scope', '$http', '$location', function ($rootScope, $scope, $http, $location) {
        if ($rootScope.authenticated) {
            $location.path("/spring");
            $scope.error = false;
    
            $http.get('/greeting2').success(function (data) {
                $scope.model = data;
            })
    
        } else {
            $location.path("/home");
            $scope.error = true;
        }
    }])

    .controller('productsCtrl', ['$scope', '$http','$location','$window', function ($scope, $http, $location, $window) {

        $http.get('/products').success(function (data) {
            var products = [];
            for(var i=0; i<data.products.length; i++) {
                products[i] = data.products[i];
            }
            $scope.products = products;
        })

        $scope.ClickMeToShowAllProducts = function () {
            $http.get('/products/all').success(function (data) {
                var products = [];
                for(var i=0; i<data.products.length; i++) {
                    products[i] = data.products[i];
                }
                $scope.products = products;
            })
        }
        $scope.ClickMeToShowProductsByCategory = function (category) {
            $http.get('/products/'+category).success(function (data) {
                var products = [];
                for(var i=0; i<data.products.length; i++) {
                    products[i] = data.products[i];
                }
                $scope.products = products;
            })
        };

        $scope.ClickMeToRedirect = function () {
            $http.get('/order/P1234/2').success(function () {
                $http.get('/products/all').success(function (data) {
                    var products = [];
                    for(var i=0; i<data.products.length; i++) {
                        products[i] = data.products[i];
                    }
                    $scope.products = products;
                })
            });
        };

    }])

    .controller('customersCtrl', ['$scope','$http', function ($scope, $http) {
        $http.get('/customers').success(function (data) {
            $scope.customers = data.customers;
        })
    }])
