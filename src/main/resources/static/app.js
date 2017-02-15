window.addEventListener('load', () => {
    console.log('trying');

    const sock = new SockJS('/gamesock');
    const client = Stomp.over(sock);

    client.connect({}, frame => {
        console.log('connected!!!!!!!');
    });
});