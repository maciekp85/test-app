/**
 * Created by nishi on 2016-05-21.
 */
angular.module('app', ['ui.router']);

angular.module('app')
    .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise('home');

        $stateProvider
            // HOME
            .state('home', {
                url: '/home',
                templateUrl: 'home.html',
                controller: 'home'
            })
                .state('home.list', {
                    url: '/list',

                    template: '<ul id="home-list.html"><li ng-repeat="n in names">{{n}}</li></ul>',
                    controller: function ($scope) {
                        $scope.names = ['Ania', 'Ola', 'Ewa'];
                    }
                })
                .state('home.text', {
                    url: '/text',
                    template: 'Examplary text!'
                })
            // LOGIN
            .state('login', {
                url: '/login',
                views: {
                    '': {
                        templateUrl: 'views/login.html'
                    },
                    'columnOne@login': {
                        template: 'First column!'
                    },
                    'columnTwo@login': {
                        template: 'Second column!'
                    }
                },
                controller: 'navigation'
            })
            // ANGULAR
            .state('angular', {
                url: '/angular',
                templateUrl: 'views/angular/angular.html',
                controller: 'angular'
            })
                // BASICS
                .state('angular.basics', {
                    url: '/basics',
                    templateUrl: 'views/angular/basics/angular-basics.html'
                })
                    .state('angular.basics.firstapp', {
                        url: '/firstapp',
                        templateUrl: 'views/angular/basics/angular-basics-firstapp.html'
                    })
                    .state('angular.basics.scope', {
                        url: '/scope',
                        templateUrl: 'views/angular/basics/angular-basics-scope.html'
                    })
                    .state('angular.basics.di', {
                        url: '/di',
                        templateUrl: 'views/angular/basics/angular-basics-di.html'
                    })
                    .state('angular.basics.di.1', {
                        url: '/1',
                        templateUrl: 'views/angular/basics/angular-basics-di-1.html'
                    })
                    .state('angular.basics.di.2', {
                        url: '/2',
                        templateUrl: 'views/angular/basics/angular-basics-di-2.html'
                    })
                    .state('angular.basics.databinding', {
                        url: '/databinding',
                        templateUrl: 'views/angular/basics/angular-basics-databinding.html'
                    })
                    .state('angular.basics.expressions', {
                        url: '/expressions',
                        templateUrl: 'views/angular/basics/angular-basics-expressions.html'
                    });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }])
    .controller('navigation',['$rootScope','$scope','$http', '$location', function($rootScope, $scope, $http, $location) {

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
    .controller('angular', ['$rootScope', '$scope', '$location', function ($rootScope, $scope, $location) {
        if ($rootScope.authenticated) {
            $location.path("/angular");
            $scope.error = false;
        } else {
            $location.path("/home");
            $scope.error = true;
        }
    }])
    .controller('home', ['$scope', '$http', function ($scope, $http) {
        $http.get('/greeting/').success(function (data) {
            $scope.greeting = data;
        })
    }])
    // Scope
    .controller('OneCtrl', ['$scope', '$rootScope', function ($scope, $rootScope) {
        $scope.oneText = "One text 1";
        console.log($rootScope);
        console.log($scope);
    }])
    .controller('TwoCtrl', ['$scope', function ($scope) {
        $scope.twoText = "Two text 1";
        console.log($scope);
    }])
    .controller('ThreeCtrl', ['$scope', function ($scope) {
        $scope.threeText = "Three text 1";
        console.log($scope);
    }])
    .controller('NestedCtrl', ['$scope', function ($scope) {
        // $scope.threeText = "Nested text 1";
        $scope.threeText = "Nested text 1";
        console.log($scope);
    }])
    // Dependency injection - example 1
    .controller('DiCtrl', ['$rootScope', '$scope', 'DiFac0', 'alarm', function ($scope, $rootScope, DiFac0, alarm) {
        $scope.DiFac0 = DiFac0;
        alarm.call($scope.DiFac0[0].name);
    }])
    .factory('DiFac0', ['DiFac1', 'DiFac2', function (DiFac1, DiFac2) {
        return [
            {'id': '1', 'name':'Ala'},
            {'id': '2', 'name':'Ola'}, DiFac1, DiFac2];
    }])
    .factory('DiFac1', function () {
        return {'id': '3', 'name':'Adam'};
    })
    .factory('DiFac2', function () {
        return {'id': '4', 'name':'Arek'};
    })
    .factory('alarm', ['$window', function ($window) {
        return {
            call: function (text) {
                $window.alert(text);
            }
        };
    }])

    // Dependency injection - example 2
    .value('MyNumber', 2)
    .value('MyArray', [1,2,3,4,5,6,7,8,9])
    .value('MyDecimal', 2.567)

    .factory('MathFactory', function () {
        var factory = {};
        factory.multiply = function (a, b) {
            return a*b;
        }
        factory.max = function (numbers) {
            return Math.max.apply(null, numbers);
        }
        factory.min = function (numbers) {
            return Math.min.apply(null, numbers);
        }
        factory.round = function (number) {
            return Math.round(number);
        }
        return factory;
    })
    .service("MathService", ['MathFactory', function (MathFactory) {
        this.square = function (a) {
            return MathFactory.multiply(a, a);
        }
        this.max = function (numbers) {
            return MathFactory.max(numbers);
        }
        this.min = function (numbers) {
            return MathFactory.min(numbers);
        }
        this.round = function (number) {
            return MathFactory.round(number);
        }
    }])
    .controller('MathCtrl', ['$scope', 'MathService', 'MyNumber', 'MyArray', 'MyDecimal', function ($scope, MathService, MyNumber, MyArray, MyDecimal) {
        $scope.number = MyNumber;
        $scope.result = MathService.square($scope.number);

        $scope.myArray = MyArray;
        $scope.min = MathService.min($scope.myArray);
        $scope.max = MathService.max($scope.myArray);

        $scope.myDecimal = MyDecimal;
        $scope.round = MathService.round($scope.myDecimal);

        $scope.square = function () {
            $scope.result = MathService.square($scope.number);
        }
    }])
    // Data binding
    .controller('DbCtrl', ['$scope', function ($scope) {
        var i = 0;
        var colors = ['red','blue','green','black'];


        $scope.changeColor = function (clickEvent) {
            console.log(clickEvent);
            $scope.color = colors[i % colors.length];
            i++;

            $scope.hiddenMessage = "";

            $scope.x = clickEvent.originalEvent.x;
            $scope.y = clickEvent.originalEvent.y;
            $scope.timeStamp = clickEvent.timeStamp;

            if(clickEvent.altKey) {
                $scope.hiddenMessage = "Magical, hidden text. Press the button without alt key to hide it again.";
            }
        }
    }])
    // Expressions
    .controller('ExpCtrl', ['$scope', function ($scope) {
        $scope.items = [1,2,3,4,5,6,7,8,9]
        $scope.number = 2;
        $scope.text = "Exemplary text";

        $scope.testFunc = function (i) {
            return i*5;
        }
    }]);