function updateAuth() {

    const authLink = document.getElementById("auth-link");
    const user = JSON.parse(localStorage.getItem("user"));

    if (!authLink) return; // safety check

    if (user) {

        authLink.innerHTML = `
            <a href="#" id="logout-btn">Logout</a>
        `;

        document.getElementById("logout-btn").addEventListener("click", function (e) {
            e.preventDefault();

            localStorage.removeItem("user");
            window.location.href = "index.html";
        });

    } else {

        authLink.innerHTML = `<a href="login.html">Log in</a>`;
    }
}
