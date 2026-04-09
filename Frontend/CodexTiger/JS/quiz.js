const params = new URLSearchParams(window.location.search);
const topicId = params.get("topicId");

async function loadQuiz() {

    const res = await fetch(`http://localhost:8080/api/quiz/topic/${topicId}`);
    const questions = await res.json();

    const container = document.getElementById("quizContainer");

    questions.forEach(q => {
        container.innerHTML += `
            <div>
                <p>${q.question}</p>
                <label><input type="radio" name="${q.id}" value="A"> ${q.optionA}</label><br>
                <label><input type="radio" name="${q.id}" value="B"> ${q.optionB}</label><br>
                <label><input type="radio" name="${q.id}" value="C"> ${q.optionC}</label><br>
                <label><input type="radio" name="${q.id}" value="D"> ${q.optionD}</label><br>
            </div><hr>
        `;
    });
}

loadQuiz();
