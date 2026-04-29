

document.getElementById("loginForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch("http://localhost:8080/user/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })

// .then(res => { return res.text(); // 👈 important change
//     }) 
//  .then(text => { console.log("Raw Response:", text); 
//     const data = JSON.parse(text); // convert manually if


    .then(res => res.json())
    .then(data => {
        console.log(" Response:", data);

        

        if (data.message === "Success") {
            const token = data.data.token;
        const user = data.data.user;

            // store token
        localStorage.setItem("token", token);

        // store user
        localStorage.setItem("user", JSON.stringify(user)); 
           
// alert("Login Successfull"+data.user.name)
            window.location.href = "index.html";
        } else {
            const errorDiv = document.getElementById("error-msg");
    errorDiv.style.display = "block";   // 👈 show it
    errorDiv.innerText = data.message;

      // clear input fields
    document.getElementById("email").value = "";
document.getElementById("password").value = "";
 // error message for 3 seconds
 setTimeout(() => {
        errorDiv.style.display = "none";
    }, 3000);

        }
    })
    .catch(error => {

    console.log("Error:", error);

    const errorDiv = document.getElementById("error-msg");

    errorDiv.style.display = "block";
    errorDiv.innerText = "Server connection failed";

    setTimeout(() => {
        errorDiv.style.display = "none";
    }, 3000);
});
});
