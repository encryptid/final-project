window.addEventListener('load', function () {
    console.log('ready to rock');

    // let toad = new SockJS('http://192.168.1.22:8080');
    let toad = new SockJS('https://fathomless-bastion-47154.herokuapp.com/gamesock');
    let client = Stomp.over(toad);

    client.connect({}, frame => {
        console.log('Now Connected');
    })

    // toad.onopen = function() {
    // console.log('open');
    // toad.send('MESSAGE SEND!');
    // console.log('sent')
    // };
    // toad.onmessage = function(e) {
    //     console.log('message', e.data);
    // };

    // toad.onclose = function() {
    //     console.log('close');
    // };

    let up = document.querySelector('#up');
    let left = document.querySelector('#left');
    let right = document.querySelector('#right');
    let down = document.querySelector('#down');
    let box = document.querySelector('input');

    up.addEventListener('click', function () {
        console.log('move up');
    });
    left.addEventListener('click', function () {
        console.log('move left');
    });
    right.addEventListener('click', function () {
        console.log('move right');
    });
    down.addEventListener('click', function () {
        console.log('move down');
    });

    box.addEventListener('keyup', function () {
        const keyname = event.key;
        if (keyname === "Enter") {
            client.send(box.value);
            console.log("performing " + box.value + "...");
        }
    });

});



// Ben's stuff
// var stompClient = null;

// function setConnected(connected) {
//     $("#connect").prop("disabled", connected);
//     $("#disconnect").prop("disabled", !connected);
//     if (connected) {
//         $("#conversation").show();
//     }
//     else {
//         $("#conversation").hide();
//     }
//     $("#greetings").html("");
// }

// function connect() {
//     var socket = new SockJS('/gs-guide-websocket');
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//         setConnected(true);
//         console.log('Connected: ' + frame);
//     });
// }



function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    //let channel = $("#channel").val();

    for (var x in stompClient.subscriptions) {
        stompClient.unsubscribe(x);
    }

    stompClient.subscribe('/topic/main', function (greeting) {
        showGreeting(JSON.parse(greeting.body).content);
    });

    stompClient.send("/app/hello/", {}, JSON.stringify({ name: $("#name").val() }));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

// $(function () {
//     $("form").on('submit', function (e) {
//         e.preventDefault();
//     });
//     $( "#connect" ).click(function() { connect(); });
//     $( "#disconnect" ).click(function() { disconnect(); });
//     $( "#send" ).click(function() { sendName(); });
// });