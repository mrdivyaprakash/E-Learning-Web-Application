// const params = new URLSearchParams(window.location.search);
// const topicId = params.get("topicId");

// async function loadQuiz() {

//     const res = await fetch(`http://localhost:8080/api/quiz/topic/${topicId}`);
//     const questions = await res.json();

//     const container = document.getElementById("quizContainer");

//     questions.forEach(q => {
//         container.innerHTML += `
//             <div>
//                 <p>${q.question}</p>
//                 <label><input type="radio" name="${q.id}" value="A"> ${q.optionA}</label><br>
//                 <label><input type="radio" name="${q.id}" value="B"> ${q.optionB}</label><br>
//                 <label><input type="radio" name="${q.id}" value="C"> ${q.optionC}</label><br>
//                 <label><input type="radio" name="${q.id}" value="D"> ${q.optionD}</label><br>
//             </div><hr>
//         `;
//     });
// }

// loadQuiz();






const params = new URLSearchParams(window.location.search);

let topicId = params.get("topicId");

/* restore topic if refresh */

if (!topicId) {

    topicId = sessionStorage.getItem("currentQuizTopic");

}

let questions = [];

async function loadQuiz() {

    const res =
        await fetch(`http://localhost:8080/api/quiz/topic/${topicId}`);

    questions = await res.json();

    const container =
        document.getElementById("quizContainer");

    container.innerHTML = "";

    questions.forEach((q, index) => {

        const savedAnswer =
            sessionStorage.getItem("quiz_" + q.id);

        container.innerHTML += `

            <div class="quiz-card">

                <p><b>Q${index + 1}.</b> ${q.question}</p>

                <label>
                <input type="radio" name="${q.id}" value="A"
                ${savedAnswer === "A" ? "checked" : ""}>
                ${q.optionA}
                </label><br>

                <label>
                <input type="radio" name="${q.id}" value="B"
                ${savedAnswer === "B" ? "checked" : ""}>
                ${q.optionB}
                </label><br>

                <label>
                <input type="radio" name="${q.id}" value="C"
                ${savedAnswer === "C" ? "checked" : ""}>
                ${q.optionC}
                </label><br>

                <label>
                <input type="radio" name="${q.id}" value="D"
                ${savedAnswer === "D" ? "checked" : ""}>
                ${q.optionD}
                </label>

            </div>

            <hr>

        `;
    });

    saveAnswers();
}

/* save answers automatically */

function saveAnswers() {

    const radios =
        document.querySelectorAll("input[type=radio]");

    radios.forEach(radio => {

        radio.addEventListener("change", function () {

            sessionStorage.setItem(
                "quiz_" + this.name,
                this.value
            );

        });

    });

}

/* submit quiz */

document.getElementById("submitQuiz")
.onclick = function () {

    let score = 0;

    questions.forEach(q => {

        const saved =
            sessionStorage.getItem("quiz_" + q.id);

        if (saved === q.correctAnswer) {

            score++;

        }

    });

    const result =
        document.getElementById("quizResult");

    result.innerHTML =
        `<h3>Your Score: ${score} / ${questions.length}</h3>`;

    sessionStorage.setItem("quizScore", score);

};

loadQuiz();