
document.addEventListener("DOMContentLoaded",() => {
    const form = document.getElementById("question-setter");
    const answers = form.querySelectorAll(".question-option");
    const optionDiv = form.querySelector(".correct-ans-div");
    const labels = optionDiv.querySelectorAll("label");

    function addOptions(){
        const values = Array.from(answers).map(v => v.value.trim());

        const condition = values.every(v => v !== "");

        if(!condition){
            optionDiv.style.display = "none";
            return
        }
        optionDiv.style.display = "block";

        values.forEach((v,idx) => {
            const radio = labels[idx].querySelector("input");
            const text = labels[idx].querySelector("span:last-of-type");

            radio.value = v;
            text.textContent = v;
        });
    };

    answers.forEach(ans => 
        ans.addEventListener("input",addOptions)
    );


});