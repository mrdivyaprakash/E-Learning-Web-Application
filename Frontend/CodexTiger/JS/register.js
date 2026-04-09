document.getElementById("registerForm")
.addEventListener("submit", function (e) {

    e.preventDefault();

    const name = document.querySelector("input[name='name']").value;
    const email = document.querySelector("input[name='email']").value;
    const password = document.querySelector("input[name='password']").value;

    // Clear old errors
    document.getElementById("nameError").innerText = "";
    document.getElementById("emailError").innerText = "";
    document.getElementById("passwordError").innerText = "";

    fetch("http://localhost:8080/user/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }, 
        body: JSON.stringify({
            name: name,
            email: email,
            password: password
        })
    })
    .then(response => {

        if (!response.ok) {
            return response.json().then(errors => {
                throw errors;
            });
        }

        return response.json();
    })
    .then(data => {

        
        window.location.href = "login.html";

    })
    .catch(errors => {

         // 🔥 CASE 1: Validation errors (field-wise)
    
        // Show field errors
        if (errors.name) {
            document.getElementById("nameError").innerText = errors.name;
        }

        if (errors.email) {
            document.getElementById("emailError").innerText = errors.email;
        }

        if (errors.password) {
            document.getElementById("passwordError").innerText = errors.password;
        }
            
             // 🔥 CASE 2: Business error (like duplicate email)
     if (errors.message) {
        document.getElementById("emailError").innerText = errors.message;
    }

    // 🔥 Fallback
    else {
        alert("Something went wrong");
    }


    });

});
