function updateAuth(){

    const authLink = document.getElementById("auth-link");

    const token = localStorage.getItem("token");
    const name = localStorage.getItem("userName");

    if(token){

        authLink.innerHTML =
        `<a href="#" onclick="logout()">Logout</a>`;

    }else{

        authLink.innerHTML =
        `<a href="login.html">Login</a>`;

    }
}

function logout(){

    localStorage.removeItem("token");
    localStorage.removeItem("user");
    sessionStorage.clear();

    

    window.location.href="login.html";
}

