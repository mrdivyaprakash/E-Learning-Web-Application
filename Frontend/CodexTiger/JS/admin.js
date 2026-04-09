const baseUrl = "http://localhost:8080/api/admin";

async function addTutorial() {

    const data = {
        title: document.getElementById("tutorialTitle").value,
        slug: document.getElementById("tutorialSlug").value
    };

    await fetch(`${baseUrl}/tutorial`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    alert("Tutorial Added");
}

async function addTopic() {

    const tutorialId = document.getElementById("tutorialId").value;

    const data = {
        title: document.getElementById("topicTitle").value,
        content: document.getElementById("topicContent").value,
        videoUrl: document.getElementById("videoUrl").value,
        topicOrder: document.getElementById("topicOrder").value
    };

    await fetch(`${baseUrl}/topic/${tutorialId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    alert("Topic Added");
}

async function addQuiz() {

    const topicId = document.getElementById("topicIdQuiz").value;

    const data = {
        question: document.getElementById("question").value,
        optionA: document.getElementById("optionA").value,
        optionB: document.getElementById("optionB").value,
        optionC: document.getElementById("optionC").value,
        optionD: document.getElementById("optionD").value,
        correctAnswer: document.getElementById("correctAnswer").value
    };

    await fetch(`${baseUrl}/quiz/${topicId}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    alert("Quiz Question Added");
}
