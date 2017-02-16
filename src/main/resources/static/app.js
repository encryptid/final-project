window.addEventListener('load', () => {
    const sock = new SockJS('http://192.168.1.22:8080/gamesock');
    const client = Stomp.over(sock);

    client.connect({}, function (frame) {
        console.log('connected');

        /* What do we do when we hear back? */
        client.subscribe('/channel/lincoln', response => {
            const msg = JSON.parse(response.body)
            console.log('Chat: ' + msg.content);
        });

        const btn = document.querySelector('#send');
        const txt = document.querySelector('#message');

        btn.addEventListener('click', function () {
            const chat = {
                message: txt.value,
            };

            client.send('/app/hello/lincoln', {}, JSON.stringify(chat));
            txt.value = '';
        });
    });

});