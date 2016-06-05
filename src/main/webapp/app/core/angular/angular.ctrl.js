/**
 * Created by nishi on 2016-06-05.
 */
var angularMod = angular.module('angularMod', []);

angularMod
    .controller('angularCtrl', ['$rootScope', '$scope', '$location', function ($rootScope, $scope, $location) {
        if ($rootScope.authenticated) {
            $location.path("/angular");
            $scope.error = false;
        } else {
            $location.path("/home");
            $scope.error = true;
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
            templateUrl: 'app/core/angular/views/owndirectives/start-tmp.html'
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
            templateUrl: 'app/core/angular/views/owndirectives/events-tmp.html',
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
            templateUrl: 'app/core/angular/views/owndirectives/events-tmp2.html',
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
            templateUrl: 'app/core/angular/views/owndirectives/rem-person.html',
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
    // Decorating - first example
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
    })
    // Decorating - second example
    .controller('decoratorCtrl', ['$scope', function ($scope) {
        $scope.click = function () {
            console.log('click');
        }
    }])
    .config(function ($provide) {
        $provide.decorator('ngClickDirective', function ($delegate) {

            var org = $delegate[0].compile;
            $delegate[0].compile = function (element, attrs, transclude) {
                if(attrs.track !== undefined) {
                    element.bind('click', function () {
                        console.log('Tracking!');
                    });
                }
                return org(element, attrs, transclude);
            };

            return $delegate;
        })
    })
    // Decorating - third example
    .controller('transcludeCtrl', ['$scope', function ($scope) {

    }])
    .directive('vpFrame', function () {
        return {
            templateUrl: 'app/core/angular/views/owndirectives/transclude-tmp.html',
            // transclude: true,
            replace: true
        }
    })
    // Rating system
    .controller('vpCtrl', ['$scope', function ($scope) {
        console.log($scope);
    }])
    .directive('eventsEvaluation', function () {
        return {
            restrict: "E",
            scope: {
                text: "@",
            },
            templateUrl: "app/core/angular/views/owndirectives/evaluation-tmp.html",
            replace: true,
            controller: function ($scope) {
                console.log($scope);
                $scope.number = 5;
                $scope.increase = function () {
                    $scope.number++;
                };
                $scope.reduce = function () {
                    $scope.number--;
                };
            }
        }
    })