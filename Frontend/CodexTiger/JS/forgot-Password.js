const API_BASE = "http://localhost:8080/user";

let email = "";
let isOtpVerified = false;
let timerInterval;

// Steps
const step1 = document.getElementById("step1");
const step2 = document.getElementById("step2");
const step3 = document.getElementById("step3");

// Inputs
const emailInput = document.getElementById("email");
const otpInput = document.getElementById("otp");
const passwordInput = document.getElementById("password");

// Buttons
const sendBtn = document.getElementById("sendOtpBtn");
const verifyBtn = document.getElementById("verifyOtpBtn");
const resetBtn = document.getElementById("resetPasswordBtn");
const resendBtn = document.getElementById("resendOtpBtn");

// Message
const messageBox = document.getElementById("message");
const timerText = document.getElementById("timer");

// Activate first step
step1.classList.add("active");

function showMessage(msg, type){
messageBox.innerText = msg;
messageBox.className = "message " + type;
}

// TIMER (Industry feature)
function startTimer(seconds){
clearInterval(timerInterval);
resendBtn.style.display = "none";
let time = seconds;


timerInterval = setInterval(() => {
    timerText.innerText = `Resend OTP in ${time}s`;
    time--;

    if(time < 0){
        clearInterval(timerInterval);
        timerText.innerText = "You can request OTP again";
        resendBtn.style.display = "block";
    }
}, 1000);


}

// SEND OTP
sendBtn.addEventListener("click", async () => {


email = emailInput.value.trim();

if(!email){
    showMessage("Email is required", "error");
    return;
}

sendBtn.disabled = true;

try{
    const res = await fetch(`${API_BASE}/send-otp`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ email })
    });

    const result = await res.json();

    if(res.ok){
        showMessage(result.message, "success");
        step1.classList.remove("active");
        step2.classList.add("active");

        startTimer(60);
    } else {
        showMessage(result.message, "error");
    }

} catch(e){
    showMessage("Server error", "error");
}

sendBtn.disabled = false;


});

// RESEND OTP
resendBtn.addEventListener("click", async () => {

    resendBtn.disabled = true;

    try{
        const res = await fetch(`${API_BASE}/send-otp`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({ email })
        });

        const result = await res.json();

        if(res.ok){
            showMessage("OTP resent successfully", "success");

            startTimer(60); // 🔥 restart timer
        } else {
            showMessage(result.message, "error");
        }

    } catch(e){
        showMessage("Server error", "error");
    }

    resendBtn.disabled = false;
});

// VERIFY OTP
verifyBtn.addEventListener("click", async () => {


const otp = otpInput.value.trim();

if(!otp){
    showMessage("OTP required", "error");
    return;
}

verifyBtn.disabled = true;

try{
    const res = await fetch(`${API_BASE}/verify-otp`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ email, otp })
    });

    const result = await res.json();

    if(res.ok && result.status === "success"){
        showMessage("OTP verified", "success");

        isOtpVerified = true;

        step2.classList.remove("active");
        step3.classList.add("active");
    } else {
        showMessage(result.message, "error");
    }

} catch(e){
    showMessage("Server error", "error");
}

verifyBtn.disabled = false;


});

// RESET PASSWORD
resetBtn.addEventListener("click", async () => {


const password = passwordInput.value.trim();

if(!isOtpVerified){
    showMessage("Verify OTP first", "error");
    return;
}

if(!password){
    showMessage("Password required", "error");
    return;
}

resetBtn.disabled = true;

try{
    const res = await fetch(`${API_BASE}/reset-password`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ email, password })
    });

    const result = await res.json();

    if(res.ok){
        showMessage("Password updated successfully", "success");

        // Reset flow
        step3.classList.remove("active");
        step1.classList.add("active");

        emailInput.value = "";
        otpInput.value = "";
        passwordInput.value = "";

    } else {
        showMessage(result.message, "error");
    }

} catch(e){
    showMessage("Server error", "error");
}

resetBtn.disabled = false;


});







