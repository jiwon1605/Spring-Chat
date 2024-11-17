document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("signup-button").addEventListener("click", function (event) {
        //회원가입 버튼 처리
        event.preventDefault();
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        //회원가입 server 처리
        fetch('/api/signup', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ username, password })
        })
            .then(response => response.text())
            .then(alert)
            .catch(error => console.error('Error:', error));
    });

    //로그인 버튼 처리
    document.getElementById("login-button").addEventListener("click", function () {
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        //로그인 server 처리
        //로그인 server 처리
        fetch('/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ username, password })
        })
            .then(response => response.text())
            .then(message => {
                alert(message); // 로그인 성공 시 메시지 팝업

                // 로그인 성공 시 welcome 페이지로 리디렉션
                if (message === "로그인 성공!") {
                    localStorage.setItem("currentUser", username); // 현재 사용자 이름 저장
                    window.location.href = 'chat.html'; // '/welcome'으로 리디렉션
                }
            })
            .catch(error => console.error('Error:', error));
    });


});
