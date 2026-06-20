// ---------- Scroll reveal ----------
document.addEventListener('DOMContentLoaded', () => {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('active');
            }
        });
    }, { threshold: 0.1 });

    document.querySelectorAll('.reveal').forEach(el => observer.observe(el));

    // Show chosen filename in the drop zone
    const fileInput = document.getElementById('pdfFile');
    const fileNameText = document.getElementById('fileNameText');
    if (fileInput) {
        fileInput.addEventListener('change', () => {
            if (fileInput.files && fileInput.files[0]) {
                fileNameText.textContent = fileInput.files[0].name;
            } else {
                fileNameText.textContent = 'Click to choose a PDF, or drag it here';
            }
        });
    }

    // Allow pressing Enter in the question box to ask
    const questionInput = document.getElementById('question');
    if (questionInput) {
        questionInput.addEventListener('keydown', (e) => {
            if (e.key === 'Enter') {
                askQuestion();
            }
        });
    }
});

// ---------- Backend calls (unchanged functionality) ----------

async function uploadPdf() {

    let file =
        document.getElementById("pdfFile").files[0];

    if (!file) {
        alert("Please select a PDF first");
        return;
    }

    let formData =
        new FormData();

    formData.append("file", file);

    let uploadBtn = document.getElementById("uploadBtn");
    let uploadBtnIcon = document.getElementById("uploadBtnIcon");
    let uploadBtnText = document.getElementById("uploadBtnText");

    let originalIconHTML = uploadBtnIcon.innerHTML;
    let originalText = uploadBtnText.textContent;

    uploadBtn.disabled = true;
    uploadBtnIcon.innerHTML = '<span class="btn-spinner"></span>';
    uploadBtnText.textContent = "Uploading...";

    try {

        let response =
            await fetch("/pdf/upload", {
                method: "POST",
                body: formData
            });

        let result =
            await response.text();

        alert(result);

    } catch (error) {

        alert("Upload failed");
        console.error(error);

    } finally {

        uploadBtn.disabled = false;
        uploadBtnIcon.innerHTML = originalIconHTML;
        uploadBtnText.textContent = originalText;
    }
}


async function askQuestion() {

    let question =
        document.getElementById("question").value;

    if (question.trim() === "") {
        alert("Enter a question");
        return;
    }

    let loader =
        document.getElementById("loader");

    let answerDiv =
        document.getElementById("answer");

    let askBtn = document.getElementById("askBtn");
    let askBtnIcon = document.getElementById("askBtnIcon");
    let originalIconHTML = askBtnIcon.innerHTML;

    askBtn.disabled = true;
    askBtnIcon.innerHTML = '<span class="btn-spinner"></span>';

    loader.style.display = "block";

    answerDiv.innerHTML =
        "<b>Thinking...</b>";

    try {

        let response =
            await fetch(
                `/chat?q=${encodeURIComponent(question)}`
            );

        let answer =
            await response.text();

        answerDiv.innerText =
            answer;

    } catch (error) {

        answerDiv.innerText =
            "Error getting response";

        console.error(error);

    } finally {

        loader.style.display = "none";
        askBtn.disabled = false;
        askBtnIcon.innerHTML = originalIconHTML;
    }
}


async function loadPdfList() {

    try {

        let response =
            await fetch("/pdf/list");

        let pdfs =
            await response.json();

        let pdfList =
            document.getElementById("pdfList");

        pdfList.style.display = "block";

        pdfList.innerHTML = "";

        if (pdfs.length === 0) {

            pdfList.innerHTML =
                "<li>No PDFs uploaded yet</li>";

            return;
        }

        pdfs.forEach(pdf => {

            let li =
                document.createElement("li");

            li.textContent = pdf;

            pdfList.appendChild(li);
        });

    } catch (error) {

        console.error(error);
    }
}

















//async function uploadPdf() {
//
//    let file =
//        document.getElementById("pdfFile").files[0];
//
//    if (!file) {
//        alert("Please select a PDF first");
//        return;
//    }
//
//    let formData =
//        new FormData();
//
//    formData.append("file", file);
//
//    try {
//
//        let response =
//            await fetch("/pdf/upload", {
//                method: "POST",
//                body: formData
//            });
//
//        let result =
//            await response.text();
//
//        alert(result);
//
//
//
//    } catch (error) {
//
//        alert("Upload failed");
//        console.error(error);
//    }
//}
//
//
//async function askQuestion() {
//
//    let question =
//        document.getElementById("question").value;
//
//    if (question.trim() === "") {
//        alert("Enter a question");
//        return;
//    }
//
//    let loader =
//        document.getElementById("loader");
//
//    let answerDiv =
//        document.getElementById("answer");
//
//    loader.style.display = "block";
//
//    answerDiv.innerHTML =
//        "<b>Thinking...</b>";
//
//    try {
//
//        let response =
//            await fetch(
//                `/chat?q=${encodeURIComponent(question)}`
//            );
//
//        let answer =
//            await response.text();
//
//        answerDiv.innerText =
//            answer;
//
//    } catch (error) {
//
//        answerDiv.innerText =
//            "Error getting response";
//
//        console.error(error);
//
//    } finally {
//
//        loader.style.display = "none";
//    }
//}
//
//
//async function loadPdfList() {
//
//    try {
//
//        let response =
//            await fetch("/pdf/list");
//
//        let pdfs =
//            await response.json();
//
//        let pdfList =
//            document.getElementById("pdfList");
//
//        pdfList.style.display = "block";
//
//        pdfList.innerHTML = "";
//
//        if (pdfs.length === 0) {
//
//            pdfList.innerHTML =
//                "<li>No PDFs uploaded yet</li>";
//
//            return;
//        }
//
//        pdfs.forEach(pdf => {
//
//            let li =
//                document.createElement("li");
//
//            li.textContent = pdf;
//
//            pdfList.appendChild(li);
//        });
//
//    } catch (error) {
//
//        console.error(error);
//    }
//}
//
//
