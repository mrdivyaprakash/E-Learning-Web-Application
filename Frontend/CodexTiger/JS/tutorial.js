const urlParams = new URLSearchParams(window.location.search);
const slug = urlParams.get("slug");

// added to store current tutorial in session management
if (slug) {

    sessionStorage.setItem("currentTutorial", slug);

} else {

    slug = sessionStorage.getItem("currentTutorial");

}

// till here 

let topics = [];
let currentIndex = 0;

// async function loadTutorial() {

//     if (!slug) {
//         alert("No tutorial selected!");
//         return;
//     }

//     const tutorialRes = await fetch(`http://localhost:8080/api/tutorial/${slug}`);

//     if (!tutorialRes.ok) {
//         alert("Tutorial not found");
//         return;
//     }

//     const tutorial = await tutorialRes.json();

//     const topicsRes = await fetch(`http://localhost:8080/api/topics/${tutorial.id}`);

//     if (!topicsRes.ok) {
//         alert("No topics found");
//         return;
//     }

//     topics = await topicsRes.json();

//     if (!Array.isArray(topics)) {
//         console.error("Topics is not array:", topics);
//         return;
//     }

//     const topicList = document.getElementById("topicList");
//     topicList.innerHTML = "";

//     topics.forEach((topic, index) => {
//         const li = document.createElement("li");
//         li.innerText = topic.title;
//         li.onclick = () => loadTopic(index);
//         topicList.appendChild(li);
//     });

//     if (topics.length > 0) {
//         loadTopic(0);
//     }
// }

async function loadTutorial() {

    const serverError = document.getElementById("serverError");
    const nextBtn = document.getElementById("nextBtn");
    const prevBtn = document.getElementById("prevBtn");
    const quizBtn = document.getElementById("quizBtn");
    const videoBtn = document.getElementById("videoBtn");

    try {

        if (!slug) {
            serverError.innerText = "No tutorial selected";
            return;
        }

        const tutorialRes = await fetch(`http://localhost:8080/api/tutorial/${slug}`);

        if (!tutorialRes.ok) {
            serverError.innerText = "Tutorial not found";
            hideButtons();
            return;
        }

        const tutorial = await tutorialRes.json();

        const topicsRes = await fetch(`http://localhost:8080/api/topics/${tutorial.id}`);

        if (!topicsRes.ok) {
            serverError.innerText = "Unable to load topics";
            hideButtons();
            return;
        }

        topics = await topicsRes.json();

        if (!Array.isArray(topics)) {
            serverError.innerText = "Invalid topic data";
            hideButtons();
            return;
        }

        const topicList = document.getElementById("topicList");
        topicList.innerHTML = "";

        topics.forEach((topic, index) => {

            const li = document.createElement("li");
            li.innerText = topic.title;
            li.onclick = () => loadTopic(index);

            topicList.appendChild(li);
        });

        // if (topics.length > 0) {
        //     showButtons();
        //     loadTopic(0);
        // }

        // for topic store in session management
if (topics.length > 0) {

    showButtons();

    const savedIndex =
        sessionStorage.getItem("currentTopicIndex");

    if (savedIndex !== null) {

        loadTopic(parseInt(savedIndex));

    } else {

        loadTopic(0);

    }
}
// till here



    }
    catch (error) {

        console.error(error);

        serverError.innerText = "Server unavailable. Please try again later.";

        hideButtons();

        setTimeout(() => {
            serverError.innerText = "";
        }, 3000);
    }
}

function hideButtons() {

    document.getElementById("nextBtn").style.display = "none";
    document.getElementById("prevBtn").style.display = "none";
    document.getElementById("quizBtn").style.display = "none";
    document.getElementById("videoBtn").style.display = "none";

}
function showButtons() {

    document.getElementById("nextBtn").style.display = "inline-block";
    document.getElementById("prevBtn").style.display = "inline-block";
    document.getElementById("quizBtn").style.display = "inline-block";
    document.getElementById("videoBtn").style.display = "inline-block";

}

function loadTopic(index) {
    currentIndex = index;

    // added to store current topic in session management
    sessionStorage.setItem("currentTopicIndex", index);
    // till here
    const topic = topics[index];

    document.getElementById("topicTitle").innerText = topic.title;
    document.getElementById("topicContent").innerHTML = topic.content;

    // added for quiz to know from which topic user came
  document.getElementById("quizBtn").onclick = () => {

    sessionStorage.setItem("currentQuizTopic", topic.id);

    window.location.href = `quiz.html?topicId=${topic.id}`;

};
// till here

    document.getElementById("videoBtn").onclick = () => {
    if (topic.videoUrl) {
        window.open(topic.videoUrl, "_blank");
    } else {
        alert("No video available for this topic");
    }
};
}

document.getElementById("nextBtn").onclick = () => {
    if(currentIndex < topics.length - 1) loadTopic(currentIndex + 1);
};

document.getElementById("prevBtn").onclick = () => {
    if(currentIndex > 0) loadTopic(currentIndex - 1);
};

loadTutorial();
