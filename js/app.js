const app = angular.module('GameApp', [/**'luegg.directives'*//**'ngScrollGlue'*/]);

app.controller('EventController', function ($scope, DemoService) {
    // What is the purpose of this controller? To populate the "events" box with info from the back end that is
    // related to gameplay info. This should happen automatically.
    $scope.startText = "You're standing in the center of a room with four walls surrounding you. How would you like to proceed?";
    
    // $scope.$apply(function () {
    //         $scope.events = DemoService.returnEvents();
    //         console.log("events in the EventController: " + $scope.events);
    //     });
    
    
})

app.controller('CursorController', function ($scope, DemoService) {
    //The purpose of this controller is to control the functions related to the "cursor"

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
    $scope.prompt = "Input action"
    $scope.command = function () {
        let type = "";
        let value = "";
        if ($scope.terminal === "take") {
            type = $scope.terminal
            $scope.terminal = "";
            $scope.prompt = "Take which item?"
            let btn = document.querySelector(".select");
            btn.addEventListener('click', function() {
                value = $scope.terminal
                console.log(type, value);
                console.log($scope.prompt);
                $scope.prompt = "Input action";
                return $scope.terminal = "";
                //current hurdle: after the item is submitted, the textbox does not clear and reset the placeholder
                //I think my current method is not ideally suited for this application.
            });
        } else if ($scope.terminal === "use") {
            type = $scope.terminal
        } else if ($scope.terminal === "search") {
            type = $scope.terminal
        } else if ($scope.terminal === "inventory") {
            type = $scope.terminal
        }
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
    
    $scope.submitUser = function () {
        // console.log($scope.name);
        // let user = $scope.name;
        // angular.copy(user, DemoService.newUser);
        // DemoService.newUser = $scope.name;
        // console.log(DemoService.newUser);
        DemoService.connect($scope.name, function () {
            console.log("connect running!")
        // Weird angular-magic. The $apply function tells angular that something is changing
        // in the function that is going to be of interest to templates.
        // 'Apply these updates to the template when they're done.'
            $scope.$apply(function () {
                $scope.chats = DemoService.returnEvents();
            });
        });
    }
    
    $scope.addChat = function () {
        // console.log($scope.chatBox);
        DemoService.chat($scope.chatBox);
        $scope.chatBox = "";

        // $scope.chats = DemoService.returnEvents();
    }

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
    let newUser = "";

    // const toad = new SockJS('http://192.168.1.22:8080/gamesock');
    // let client = null;

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
        connect: function (name, cb) {
            // console.log(person);
            // const toad = new SockJS('https://fathomless-bastion-47154.herokuapp.com/gamesock');
            const toad = new SockJS('http://192.168.1.22:8080/gamesock');
            client = Stomp.over(toad);

            client.connect({
                name: name,
            }, function () {
                console.log('connected woohooo');
                let urlparts = toad._transport.url.split("/");
                currentUser = urlparts[urlparts.length - 2];

                // /user/[sessionId]/
                client.subscribe('/user/' + currentUser + '/', response => {
                    //subscribes to the url laid out in the first parameter.
                    //second parameter is an arrow function with response as the parameter
                    const info = JSON.parse(response.body);
                    //assign the response to a variable called 'msg'
                    // console.log(response);
                    // console.log(info.content);
                    events.push(info);
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
                value: direction,
            };
            client.send('/game/user-input', {}, JSON.stringify(movement));
        },
        action: function (action, target) {
            let command = {
                type: action,
                value: target
            };
            client.send('/game/user-input', {}, JSON.stringify(command));
        },
        chat: function(msg) {
            let chat = {
                type: 'chat',
                value: msg
            }

            client.send('/game/user-input', {}, JSON.stringify(chat));
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