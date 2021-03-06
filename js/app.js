const app = angular.module('GameApp', [/**'luegg.directives'*//**'ngScrollGlue'*/]);

app.controller('EventController', function ($scope, DemoService) {
    // What is the purpose of this controller? To populate the "events" box with info from the back end that is
    // related to gameplay info. This should happen automatically.
    $scope.startText = "You're standing in the center of a room with four walls surrounding you. How would you like to proceed?";
    DemoService.connect(function () {
        console.log("connect running!")
        // Weird angular-magic. The $apply function tells angular that something is changing
        // in the function that is going to be of interest to templates.
        // 'Apply these updates to the template when they're done.'
        $scope.$apply(function () {
            $scope.events = DemoService.returnEvents();
            console.log("events in the EventController: " + $scope.events);
        });
    });
})

app.controller('CursorController', function ($scope, DemoService) {
    //The purpose of this controller is to control the functions related to the "cursor"
    // $scope.addThing = function () {
    //     $scope.chats = DemoService.returnEvents();
    //     console.log($scope.chats);
    // }

    $scope.up = function () {
        console.log('up working!');
        DemoService.move('up');
    }

    $scope.left = function () {
        DemoService.move('left');
    }

    $scope.right = function () {
        DemoService.move('right');
    }

    $scope.down = function () {
        DemoService.move('down');
    }

});

app.controller('CommandController', function ($scope, DemoService) {
    //The purpose of this controller is to handle validation of user input before submitting it
    $scope.terminal = "";
    $scope.prompt = "What would you like to do?"
    $scope.command = function () {

        // console.log($scope.terminal);
        // let splitter = $scope.terminal.split(" ")
        // console.log(splitter);


        // if ($scope.terminal === 'use') {
        //     DemoService.action('use', 'something');
        // } else if ($scope.terminal === 'take') {
        //     DemoService.action('take', 'something');
        // } else if ($scope.terminal === 'search') {
        //     DemoService.action('search')
        // }


        // if ('house' === 'cat' || 'boat' || 'car') {

        // }
        //  if ($scope.terminal !== "use" || $scope.terminal !== "take" || $scope.terminal !== "search" || $scope.terminal !== "inventory") {
        //     console.log("Quit being stupid.")
        // } else {
        //     console.log($scope.terminal);
        //     DemoService.action($scope.terminal);
        // }
    }

});

app.controller('ChatController', function ($scope, DemoService) {
    // The purpose of this controller is to manage the functions of the chat section.
    //New chats should be populated automatically without direct intervention
    // $scope.addChat = function () {
    //     $scope.chats = DemoService.returnEvents();
    //     console.log($scope.chats);
    // }

    //when the heroku endpoint for chats becomes available, create a new connect function for chats and uncomment 
    //this:
    // DemoService.connect(function () {
    //     // Weird angular-magic. The $apply function tells angular that something is changing
    //     // in the function that is going to be of interest to templates.
    //     // 'Apply these updates to the template when they're done.'
    //     $scope.$apply(function () {
    //         $scope.chats = DemoService.returnEvents();
    //     });
    // });
})

app.factory('DemoService', function ($http) {
    const events = [];

    // const toad = new SockJS('http://192.168.1.22:8080/gamesock');
    let client = null;

    // client.connect({}, function () {
    //     console.log('connected woohooo');

    //     client.subscribe('/channel/lincoln', response => {
    //         //subscribes to the url laid out in the first parameter.
    //         //second parameter is an arrow function with response as the parameter
    //         const info = JSON.parse(response.body)
    //         //assign the response to a variable called 'msg'
    //         console.log(response);
    //         console.log(info.content);
    //         events.push(info.content);
    //     });
    // });

    return {
        connect: function (cb) {
            const toad = new SockJS('https://fathomless-bastion-47154.herokuapp.com/gamesock');
            client = Stomp.over(toad);

            client.connect({}, function () {
                console.log('connected woohooo');

                client.subscribe('/channel/lincoln', response => {
                    //subscribes to the url laid out in the first parameter.
                    //second parameter is an arrow function with response as the parameter
                    const info = JSON.parse(response.body)
                    //assign the response to a variable called 'msg'
                    console.log(response);
                    console.log(info.content);
                    events.push(info.content);
                    // angular.copy()

                    // Reason: we need to do something every time a new message comes in, 
                    // specifically update the DOM.
                    cb();
                });
            });
        },
        move: function (direction) {
            // console.log(direction);
            let movement = {         //this is the key to everything. the variable doesn't matter, but
                type: 'move',       //type: and message: have to be there.
                message: direction,
            };
            client.send('/app/hello/lincoln', {}, JSON.stringify(movement));
        },
        action: function (action, target) {
            let command = {
                type: action,
                message: target
            };
            client.send('/app/hello/lincoln', {}, JSON.stringify(command));
        },

        returnEvents: function () {
            console.log(events);
            return events
        },

        addPrompt: function (thing) {
            console.log(thing);
            events.push(thing);
            returnEvents();
        }
    }
})


/** BEGIN OLD APP */
// window.addEventListener('load', function () {
//     console.log('ready to rock');

//     // let toad = new SockJS('http://192.168.1.22:8080');
//     let toad = new SockJS('https://fathomless-bastion-47154.herokuapp.com/gamesock');
//     let client = Stomp.over(toad);

//     client.connect({}, frame => {
//         console.log('Now Connected');
//     })

//     // toad.onopen = function() {
//     // console.log('open');
//     // toad.send('MESSAGE SEND!');
//     // console.log('sent')
//     // };
//     // toad.onmessage = function(e) {
//     //     console.log('message', e.data);
//     // };

//     // toad.onclose = function() {
//     //     console.log('close');
//     // };

//     let up = document.querySelector('#up');
//     let left = document.querySelector('#left');
//     let right = document.querySelector('#right');
//     let down = document.querySelector('#down');
//     let box = document.querySelector('input');

//     up.addEventListener('click', function () {
//         console.log('move up');
//     });
//     left.addEventListener('click', function () {
//         console.log('move left');
//     });
//     right.addEventListener('click', function () {
//         console.log('move right');
//     });
//     down.addEventListener('click', function () {
//         console.log('move down');
//     });

//     box.addEventListener('keyup', function () {
//         const keyname = event.key;
//         if (keyname === "Enter") {
//             client.send(box.value);
//             console.log("performing " + box.value + "...");
//         }
//     });

// });



// // Ben's stuff
// // var stompClient = null;

// // function setConnected(connected) {
// //     $("#connect").prop("disabled", connected);
// //     $("#disconnect").prop("disabled", !connected);
// //     if (connected) {
// //         $("#conversation").show();
// //     }
// //     else {
// //         $("#conversation").hide();
// //     }
// //     $("#greetings").html("");
// // }

// // function connect() {
// //     var socket = new SockJS('/gs-guide-websocket');
// //     stompClient = Stomp.over(socket);
// //     stompClient.connect({}, function (frame) {
// //         setConnected(true);
// //         console.log('Connected: ' + frame);
// //     });
// // }



// function disconnect() {
//     if (stompClient != null) {
//         stompClient.disconnect();
//     }
//     setConnected(false);
//     console.log("Disconnected");
// }

// function sendName() {
//     //let channel = $("#channel").val();

//     for (var x in stompClient.subscriptions) {
//         stompClient.unsubscribe(x);
//     }

//     stompClient.subscribe('/topic/main', function (greeting) {
//         showGreeting(JSON.parse(greeting.body).content);
//     });

//     stompClient.send("/app/hello/", {}, JSON.stringify({ name: $("#name").val() }));
// }

// function showGreeting(message) {
//     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// }

// // $(function () {
// //     $("form").on('submit', function (e) {
// //         e.preventDefault();
// //     });
// //     $( "#connect" ).click(function() { connect(); });
// //     $( "#disconnect" ).click(function() { disconnect(); });
// //     $( "#send" ).click(function() { sendName(); });
// // });