(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
const app = angular.module('GameApp', [/**'luegg.directives'*//**'ngScrollGlue'*/]);

app.controller('EventController', function ($scope, GameService, $timeout) {
    // What is the purpose of this controller? To populate the "events" box with info from the back end that is
    // related to gameplay info. This should happen automatically.
    $scope.startText = "Please enter your name below."

    // $scope.startText = "You're standing in the center of a room with four walls surrounding you. How would you like to proceed?";

    $scope.events = GameService.returnEvents();

    /**
     * When $scope.events changes, we need to scroll down to the bottom.
     * 
     * $watchCollection is actually getting called before the changes to 
     * the array occur, so we need to use $timeout to put off the scrolling
     * until RIGHT AFTER the collection is updated.
     */
    $scope.$watchCollection('events', function () {
        $timeout(function () {
            let evtBox = document.querySelector(".eventBox");
            evtBox.scrollTop = evtBox.scrollHeight + 100;
        }, 0);
    });

    // $scope.$apply(function () {
    //         $scope.events = GameService.returnEvents();
    //         console.log("events in the EventController: " + $scope.events);
    //     });


})

app.controller('CursorController', function ($scope, GameService) {
    //The purpose of this controller is to control the functions related to the "cursor"

    $scope.up = function () {
        console.log('up working!');
        GameService.move('up');
    }

    $scope.left = function () {
        GameService.move('left');
    }

    $scope.right = function () {
        GameService.move('right');
    }

    $scope.down = function () {
        GameService.move('down');
    }

});

app.controller('CommandController', function ($scope, GameService) {
    //The purpose of this controller is to handle validation of user input before submitting it
    $scope.terminal = "";
    $scope.objSelect = "";
    $scope.prompt = "Input action";
    $scope.objPrompt = "Input item";
    let check = new RegExp('^[a-zA-Z][a-zA-Z][a-zA-Z]+');
    let choice = document.querySelector('.choice');


    $scope.command = function () {
        // if (isAction() && isItem()) {
        // GameService.action(entry, thing)
        // }

        function isAction() {
            let lower = $scope.terminal.toLowerCase();
            let entry = lower.trim();
            console.log(entry);
            if (check.test(entry) === true) {
                if (entry === "take") {
                    console.log("it's take!");
                    return true
                } else if (entry === "search") {
                    console.log("it's search!")
                    return true
                } else if (entry === "use") {
                    console.log("it's use!")
                    return true
                } else if (entry === "help") {
                    console.log("it's help!")
                    return true
                } else {
                    console.log("it's not a command...")
                    return false
                }
            } else {
                console.log("Sorry, that's not a valid command.");
                return false
            };


        };

        function isItem() {
            let lower = $scope.objSelect.toLowerCase();
            let entry = lower.trim();
            if (check.test(entry) === true) {
                return true
            };
        };

        function send() {
            let actLower = $scope.terminal.toLowerCase();
            let action = actLower.trim();
            let itemLower = $scope.objSelect.toLowerCase();
            let item = itemLower.trim();
            GameService.action(action, item);
            $scope.terminal = "";
            $scope.objSelect = "";
        };

        if (isAction() && isItem()) {
            console.log("Run command thinger!");
            send();
        };
        // A BIG ANNOYING THING: the bottom of the element is partially hidden by the top of the div under it.
    };

    // if ( check.test($scope.terminal) === true) {
    //     let lower = $scope.terminal.toLowerCase();
    //     let entry = lower.trim();
    //     console.log(entry);
    //     if (entry === "take") {
    //         $scope.objPrompt = "Take what?"
    //         GameService.action($scope.terminal, $scope.objSelect)
    //             console.log("command is running");
    //         // type = $scope.terminal;
    //         // value = $scope.objSelect;
    //         $scope.terminal = "";
    //         $scope.objSelect = "";
    //         let results = GameService.returnChats();
    //         console.log(results);
    //         $scope.objPrompt = "Input item";

    //             // $scope.$apply(function () {
    //             //     $scope.chats = GameService.returnEvents();
    //             // });
    //             //current hurdle: after the item is submitted, the textbox does not clear and reset the placeholder
    //             //I think my current method is not ideally suited for this application.
    //     } else if ($scope.terminal === "use") {
    //         type = $scope.terminal
    //     } else if ($scope.terminal === "search") {
    //         type = $scope.terminal
    //     } else if ($scope.terminal === "inventory") {
    //         type = $scope.terminal
    //     }
    // console.log($scope.terminal);
    // let splitter = $scope.terminal.split(" ")
    // console.log(splitter);


    // if ($scope.terminal === 'use') {
    //     GameService.action('use', 'something');
    // } else if ($scope.terminal === 'take') {
    //     GameService.action('take', 'something');
    // } else if ($scope.terminal === 'search') {
    //     GameService.action('search')
    // }


    // if ('house' === 'cat' || 'boat' || 'car') {

    // }
    //  if ($scope.terminal !== "use" || $scope.terminal !== "take" || $scope.terminal !== "search" || $scope.terminal !== "inventory") {
    //     console.log("Quit being stupid.")
    // } else {
    //     console.log($scope.terminal);
    //     GameService.action($scope.terminal);
    // }
    // }

});

app.controller('ChatController', function ($scope, GameService, $timeout) {
    // The purpose of this controller is to manage the functions of the chat section.
    //New chats should be populated automatically without direct intervention

    $scope.submitUser = function () {
        // console.log($scope.name);
        // let user = $scope.name;
        // angular.copy(user, GameService.newUser);
        // GameService.newUser = $scope.name;
        // console.log(GameService.newUser);
        GameService.connect($scope.name, function () {
            console.log("connect running!");
            GameService.login();
            GameService.returnEvents();

            // Weird angular-magic. The $apply function tells angular that something is changing
            // in the function that is going to be of interest to templates.
            // 'Apply these updates to the template when they're done.'
            $scope.$apply(function () {
                $scope.chats = GameService.returnChats();
            });
        });
    }
    $scope.chats = GameService.returnChats();

    $scope.$watchCollection('chats', function () {
        $timeout(function () {
            let chatty = document.querySelector(".chatBody");
            chatty.scrollTop = chatty.scrollHeight;
        }, 0);
    });

    $scope.addChat = function () {
        // console.log($scope.chatBox);
        GameService.chat($scope.chatBox);
        $scope.chatBox = "";
        
        // this one isn't scrolling properly for some reason.
    }

    //when the heroku endpoint for chats becomes available, create a new connect function for chats and uncomment 
    //this:
    // GameService.connect(function () {
    //     // Weird angular-magic. The $apply function tells angular that something is changing
    //     // in the function that is going to be of interest to templates.
    //     // 'Apply these updates to the template when they're done.'
    //     $scope.$apply(function () {
    //         $scope.chats = GameService.returnEvents();
    //     });
    // });
})

app.factory('GameService', function ($http) {
    const events = [];
    const chats = [];
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
            const toad = new SockJS('https://fathomless-bastion-47154.herokuapp.com/gamesock');
            // const toad = new SockJS('http://192.168.1.22:8080/gamesock');
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
                    //assign the response to a variable called 'info'
                    // console.log(response);

                    if (info.type === "chat") {
                        chats.push(info);
                    } else if (info.type === "event") {
                        events.push(info.value);
                    };
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
            console.log(action + ' and ' + target + ' are coming through the action function!');
            let command = {
                type: action,
                value: target
            };
            client.send('/game/user-input', {}, JSON.stringify(command));
        },
        chat: function (msg) {
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

        returnChats: function () {
            console.log(chats);
            return chats
        },

        login: function () {
            let login = {
                type: "event",
                value: "You have successfully logged in",
            }
            console.log(login);
            events.push(login);
            // returnEvents();
        },
    }
});
},{}]},{},[1]);
