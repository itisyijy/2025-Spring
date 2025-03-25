document.addEventListener("DOMContentLoaded", () => {
  let count = 0;
  let bugs = document.querySelectorAll("#bug-container img");
  bugs.forEach(function (bug) {
    bug.onclick = function () {
      bug.src = "bug-whacked.png";
      if (!bug.classList.contains("whacked")) {
        bug.classList.add("whacked");
        document.querySelector("#score").textContent = ++count;
      }
      if (count == bugs.length) {
        document.querySelector("#game p").textContent =
          "all bugs have been whacked";
      }
    };
  });
});
