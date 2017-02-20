const app = angular.module('GameDemo', []);

app.controller('DemoController', function ($scope, DemoService) {
    $scope.up = function up() {
        console.log('up works!');
    }
})