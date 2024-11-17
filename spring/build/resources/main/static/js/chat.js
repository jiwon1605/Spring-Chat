document.addEventListener("DOMContentLoaded", function () {
    // 로컬 스토리지에서 현재 사용자와 닉네임 가져오기
    const currentUser = localStorage.getItem("currentUser");
    let nickname = localStorage.getItem("nickname") || '닉네임을 설정해주세요';

    // currentUser를 HTML 요소에 표시
    const currentUserElement = document.getElementById("currentUser");
    if (currentUserElement) {
        currentUserElement.textContent = currentUser;
    }

    // sender의 기본값을 currentUser로 설정
    const senderInput = document.getElementById('sender');
    if (senderInput && currentUser) {
        senderInput.value = currentUser;
    }

    // sender의 값이 변경되면 localStorage에 저장
    senderInput.addEventListener('input', function() {
        const senderValue = senderInput.value.trim();
        localStorage.setItem("sender", senderValue); // localStorage에 저장
    });
});
