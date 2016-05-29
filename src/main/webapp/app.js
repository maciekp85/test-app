/**
 * Created by nishi on 2016-05-21.
 */
angular.module('app', ['ui.router', 'ngSanitize']);

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
                    })
                // DIRECTIVES
                .state('angular.directives', {
                    url: '/directives',
                    templateUrl: 'views/angular/directives/angular-directives.html'
                })
                    .state('angular.directives.a', {
                        url: '/a',
                        templateUrl: 'views/angular/directives/angular-directives-a.html'
                    })
                    .state('angular.directives.form', {
                        url: '/form',
                        templateUrl: 'views/angular/directives/angular-directives-form.html'
                    })
                    .state('angular.directives.ngbind', {
                        url: '/ngbind',
                        templateUrl: 'views/angular/directives/angular-directives-ngbind.html'
                    })
                    .state('angular.directives.ngcloak', {
                        url: '/ngcloak',
                        templateUrl: 'views/angular/directives/angular-directives-ngcloak.html'
                    })
                    .state('angular.directives.ngchange', {
                        url: '/ngchange',
                        templateUrl: 'views/angular/directives/angular-directives-ngchange.html'
                    })
                    .state('angular.directives.ngrepeat', {
                        url: '/ngrepeat',
                        templateUrl: 'views/angular/directives/angular-directives-ngrepeat.html'
                    })
                    .state('angular.directives.ngmouse', {
                        url: '/ngmouse',
                        templateUrl: 'views/angular/directives/angular-directives-ngmouse.html'
                    })
                    .state('angular.directives.ngscript', {
                        url: '/ngscript',
                        templateUrl: 'views/angular/directives/angular-directives-ngscript.html'
                    })
                // OWN DIRECTIVES
                .state('angular.owndirectives', {
                    url: '/owndirectives',
                    templateUrl: "views/angular/owndirectives/angular-owndirectives.html"
                })
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
    .controller('home', ['$rootScope', '$scope', '$http', function ($rootScope, $scope, $http) {
        if($rootScope.authenticated) {
            $http.get('/greeting/').success(function (data) {
                $scope.greeting = data;
            })
        }
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
        };
        factory.max = function (numbers) {
            return Math.max.apply(null, numbers);
        };
        factory.min = function (numbers) {
            return Math.min.apply(null, numbers);
        };
        factory.round = function (number) {
            return Math.round(number);
        };
        return factory;
    })
    .service("MathService", ['MathFactory', function (MathFactory) {
        this.square = function (a) {
            return MathFactory.multiply(a, a);
        };
        this.max = function (numbers) {
            return MathFactory.max(numbers);
        };
        this.min = function (numbers) {
            return MathFactory.min(numbers);
        };
        this.round = function (number) {
            return MathFactory.round(number);
        };
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
    }])
    // NEW CHAPTER
    // Directives

    // Directive a
    .controller('ACtrl', ['$scope', function ($scope) {
        $scope.test = function () {
            $scope.message = "Test message";
        };
    }])
    // Directive form
    .controller('FormCtrl', ['$scope', function ($scope) {

    }])
    // Directive ng-bind
    .controller('ngBindCtrl', ['$scope', function ($scope) {
        $scope.testMessage1 = "Test messsage 1";
        $scope.testMessage2 = "Test message 2";
        $scope.testHtml = "<b>This is HTML</b>";
    }])
    // Directive change
    .controller('defaultCtrl', ['$scope', function ($scope) {
        $scope.focus = false;
        $scope.blur = false;
        $scope.tempText = [];

        $scope.change = function () {
            console.log($scope.text);
            $scope.tempText.push($scope.text);
        };
    }])
    // Directive ng-repeat
    .controller('ngRepeatCtrl',['$scope', function ($scope) {
        $scope.students = [
            {id: '1', name: 'Adam', lastName: 'Kowalski', city: 'Szczecin'},
            {id: '2', name: 'Ola', lastName: 'Kot', city: 'Warszawa'},
            {id: '3', name: 'Ania', lastName: 'Góral', city: 'Radom'},
            {id: '4', name: 'Ewa', lastName: 'Niemiec', city: 'Katowice'},
            {id: '5', name: 'Ula', lastName: 'Polak', city: 'Lublin'}
        ]
    }])
    // Directive ng-mouse
    .controller('mouseCtrl', ['$scope', function ($scope) {
        $scope.log = function (text) {
            console.log(text);
        };
    }])
    // Directive ng-script
    .controller('scriptCtrl', ["$scope", function ($scope) {
        $scope.defaultValue = "Text from default controller"
    }])
    .controller('aCtrl', ["$scope", function ($scope) {
        $scope.aValue = "Text from aCtrl controller";
    }])
    .controller('bCtrl', ["$scope", function ($scope) {
        $scope.bValue = "Text from bCtrl controller";
    }])
    .controller('cCtrl', ["$scope", function ($scope) {
        $scope.cValue = "Text from cCtrl controller";
    }])
    .controller('dCtrl', ["$scope", function ($scope) {
        $scope.dValue = "Text from dCtrl controller";
    }])

    // NEW CHAPTER
    // Own Directives
    .controller('ownDirectivesCtrl', ['$scope', function ($scope) {

    }])
    .controller('startCtrl', ['$scope', function ($scope) {
        $scope.myTable = [
            {name: 'Ala', city: 'Radom'},
            {name: 'Ola', city: 'Warszawa'},
            {name: 'Ania', city: 'Radom'},
            {name: 'Ewa', city: 'Warszawa'}
        ];
    }])
    // Start
    .directive('startDr', [function () {
      return {
          template: "Test <b>text</b>",
      }
    }])
    // Restrict
    .directive('startDr2', [function () {
        return {
            // template: "Test <b>text</b>",
            // restrict: 'AEC',
            restrict: 'M',
            link: function () {
                alert('M works')
            }
        }
    }])
    // Use built-in directives
    .directive('startDr3', [function () {
        return {
            template: '<div class="well"><ul><li ng-repeat="item in myTable">Name: {{item.name}}, city: {{item.city}}</li></ul></div>',
            controller: function ($scope) {
                $scope.add = function (name, city) {
                    $scope.myTable.push({'name':name, 'city': city});
                }
            }
        };
    }])
    .directive('tplDr', [function () {
        return {
            templateUrl: 'views/angular/owndirectives/start-tmp.html'
        };
    }])

    // Scope
    .controller('scopeCtrl', ['$scope', function ($scope) {
        $scope.table = [
            {name: 'Ala', city: 'Radom'},
            {name: 'Ola', city: 'Warszawa'},
            {name: 'Ania', city: 'Radom'},
            {name: 'Ewa', city: 'Warszawa'}
        ];
        $scope.table1 = [
            {name: 'Ala1', city: 'Radom'},
            {name: 'Ola1', city: 'Warszawa'},
            {name: 'Ania1', city: 'Radom'},
            {name: 'Ewa1', city: 'Warszawa'}
        ];
        $scope.table2 = [
            {name: 'Ala2', city: 'Radom'},
            {name: 'Ola2', city: 'Warszawa'},
            {name: 'Ania2', city: 'Radom'},
            {name: 'Ewa2', city: 'Warszawa'}
        ];
        console.log('controller', $scope);
    }])
    .directive('scopeDr1', [function () {
        return {
            scope: {table:'='},
            templateUrl: 'views/angular/owndirectives/events-tmp.html',
            controller: function ($scope) {
                $scope.add = function (name, city) {
                    $scope.table.push({'name':name, 'city': city});
                }

                console.log('scope from directive', $scope);
            }
        };
    }])
    .directive('scopeDr2', [function () {
        return {
            scope: {table:'='},
            templateUrl: 'views/angular/owndirectives/events-tmp2.html',
            controller: function ($scope) {
                $scope.add = function (name, city) {
                    $scope.table.push({'name':name, 'city': city});
                }

                $scope.removePerson = function (index) {
                    if(index>-1) {
                        $scope.table.splice(index, 1);
                    }
                }
                console.log('scope from directive', $scope);
            }
        };
    }])
    .directive('remPerson', function () {
        return {
            scope: {
                rem: '&method'
            },
            templateUrl: 'views/angular/owndirectives/rem-person.html',
            controller: function ($scope) {
                $scope.remove = function () {
                    $scope.rem();
                }
            }
        }
    })
    .controller('scopeCtrl3', ['$rootScope', '$scope', function ($rootScope, $scope) {
        console.log('$rootScope', $rootScope);
        console.log('$scope', $scope);
    }])
    .directive('scopeDr3', function () {
        return {
            // scope: {},
            scope: true,
            link: function (scope) {
                console.log('scopeDr3', scope);
            }
        }
    })
    .directive('scopeDr3Two', function () {
        return {
            // scope: {},
            scope: true,
            link: function (scope) {
                console.log('scopeDr3Two', scope);
            }
        }
    })
    // Overwriting functionalities
    .controller('newClickCtrl', ['$scope', function ($scope) {
        $scope.data={info: "Nobody doesn't click me yet"}
        $scope.changeInfo = function (param) {
            param.info = "It's working"
        }
    }])
    .directive('newClick', function ($parse) {
        return {
            link: function (scope, el, attrs) {
                var fn = $parse(attrs['newClick']);
                el.on('click', function () {
                    scope.$apply(function () {
                        fn(scope);
                    })
                })
            }
        }
    })
    // Isolation
    .controller('componentCtrl', ['$scope', function ($scope) {
        $scope.data1={info: "Information text!", selected: false}
    }])
    .directive('componentDir', function () {
        return {
            restrict: 'E',
            scope:{data1:'='},
            template: '<div change-color ng-class="{\'alert-success\':data1.selected, \'alert-danger\':!data1.selected}">{{data1.info}}</div>',
            replace: true,
        }
    })
    .directive('changeColor', function () {
        return {
            link: function (scope, el, attrs) {
                console.log('change-color start');
                el.on('click', function () {
                    console.log('change-color click');
                    scope.data1.selected = !scope.data1.selected;
                    scope.$apply();
                })
            }
        }
    })
    // Decorating
    .directive('vpOne', function () {
        return{
            replace: true,
            template: '<div>This is magical text</div>'
        }
    })
    .config(function ($provide) {
        $provide.decorator('vpOneDirective', function ($delegate) {
            var directive = $delegate[0];
            directive.restrict="AC";
            return $delegate;
        })
    });
