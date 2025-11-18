function saveUser(userId, shopName) {
    localStorage.setItem("userId", userId);
    localStorage.setItem("shopName", shopName);
}

function getUserId() {
    return localStorage.getItem("userId");
}

function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}

function checkLogin() {
    if (!localStorage.getItem("userId")) {
        window.location.href = "login.html";
    }
}
