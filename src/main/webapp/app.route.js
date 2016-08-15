/**
 * Created by nishi on 2016-06-05.
 */
var appRoute = angular.module('appRoute', []);

appRoute
    .config(['$stateProvider', '$urlRouterProvider', '$httpProvider', function ($stateProvider, $urlRouterProvider, $httpProvider) {

        $urlRouterProvider.otherwise('home');

        $stateProvider
        // HOME
            .state('home', {
                url: '/home',
                templateUrl: 'home.html',
                controller: 'homeCtrl'
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
                        templateUrl: 'app/core/login/login.view.html'
                    },
                    'columnOne@login': {
                        template: 'First column!'
                    },
                    'columnTwo@login': {
                        template: 'Second column!'
                    }
                },
                controller: 'navigationCtrl'
            })
            // ANGULAR
            .state('angular', {
                url: '/angular',
                templateUrl: 'app/core/angular/views/angular.html',
                controller: 'angularCtrl'
            })
                // BASICS
                .state('angular.basics', {
                    url: '/basics',
                    templateUrl: 'app/core/angular/views/basics/angular-basics.html'
                })
                    .state('angular.basics.firstapp', {
                        url: '/firstapp',
                        templateUrl: 'app/core/angular/views/basics/angular-basics-firstapp.html'
                    })
                    .state('angular.basics.scope', {
                        url: '/scope',
                        templateUrl: 'app/core/angular/views/basics/angular-basics-scope.html'
                    })
                    .state('angular.basics.di', {
                        url: '/di',
                        templateUrl: 'app/core/angular/views/basics/angular-basics-di.html'
                    })
                    .state('angular.basics.di.1', {
                        url: '/1',
                        templateUrl: 'app/core/angular/views/basics/angular-basics-di-1.html'
                    })
                    .state('angular.basics.di.2', {
                        url: '/2',
                        templateUrl: 'app/core/angular/views/basics/angular-basics-di-2.html'
                    })
                    .state('angular.basics.databinding', {
                        url: '/databinding',
                        templateUrl: 'app/core/angular/views/basics/angular-basics-databinding.html'
                    })
                    .state('angular.basics.expressions', {
                        url: '/expressions',
                        templateUrl: 'app/core/angular/views/basics/angular-basics-expressions.html'
                    })
                // DIRECTIVES
                .state('angular.directives', {
                    url: '/directives',
                    templateUrl: 'app/core/angular/views/directives/angular-directives.html'
                })
                    .state('angular.directives.a', {
                        url: '/a',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-a.html'
                    })
                    .state('angular.directives.form', {
                        url: '/form',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-form.html'
                    })
                    .state('angular.directives.ngbind', {
                        url: '/ngbind',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-ngbind.html'
                    })
                    .state('angular.directives.ngcloak', {
                        url: '/ngcloak',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-ngcloak.html'
                    })
                    .state('angular.directives.ngchange', {
                        url: '/ngchange',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-ngchange.html'
                    })
                    .state('angular.directives.ngrepeat', {
                        url: '/ngrepeat',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-ngrepeat.html'
                    })
                    .state('angular.directives.ngmouse', {
                        url: '/ngmouse',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-ngmouse.html'
                    })
                    .state('angular.directives.ngscript', {
                        url: '/ngscript',
                        templateUrl: 'app/core/angular/views/directives/angular-directives-ngscript.html'
                    })
                // OWN DIRECTIVES
                .state('angular.owndirectives', {
                    url: '/owndirectives',
                    templateUrl: "app/core/angular/views/owndirectives/angular-owndirectives.html"
                })
            //SELENIUM
            .state('selenium', {
                url: '/selenium',
                templateUrl: 'app/core/selenium/views/selenium.html',
                controller: 'seleniumCtrl'
            })
                // LOCATING ELEMENTS
                .state('selenium.locating', {
                    url: '/locating',
                    templateUrl: 'app/core/selenium/views/locating/selenium-locating.html'
                })
                    .state('selenium.locating.introduction', {
                        url: '/introduction',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-introduction.html'
                    })
                    .state('selenium.locating.id', {
                        url: '/id',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-id.html'
                    })
                    .state('selenium.locating.name', {
                        url: '/name',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating.name.html'
                    })
                    .state('selenium.locating.class', {
                        url: '/class',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating.class.html'
                    })
                    .state('selenium.locating.linktext', {
                        url: '/linktext',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-linktext.html'
                    })
                    .state('selenium.locating.findelements', {
                        url: '/findelements',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-findelements.html'
                    })
                    .state('selenium.locating.partialtext', {
                        url: '/partialtext',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-partialtext.html'
                    })
                    .state('selenium.locating.tagname', {
                        url: '/tagname',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-tagname.html'
                    })
                    .state('selenium.locating.css', {
                        url: '/css',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-css.html'
                    })
                    .state('selenium.locating.xpath', {
                        url: '/xpath',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-xpath.html'
                    })
                    .state('selenium.locating.text', {
                        url: '/text',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-text.html'
                    })
                    .state('selenium.locating.advancedcss', {
                        url: '/advancedcss',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-advancedcss.html'
                    })
                    .state('selenium.locating.jquery', {
                        url: '/jquery',
                        templateUrl: 'app/core/selenium/views/locating/selenium-locating-jquery.html'
                    })

            // SPRING
            .state('spring', {
                url: '/spring',
                templateUrl: 'app/core/spring/views/spring/spring.html',
                controller: 'springCtrl'
            })
                .state('spring.products', {
                    url: '/products',
                    templateUrl: 'app/core/spring/views/spring/spring-products.html',
                    controller: 'productsCtrl'
                })
                .state('spring.customers', {
                    url: '/customers',
                    templateUrl: 'app/core/spring/views/spring/spring-customers.html',
                    controller: 'customersCtrl'
                });

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }]);