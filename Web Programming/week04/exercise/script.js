document.addEventListener("DOMContentLoaded", () => {
  // [redundancy]
  //   let red = document.getElementById("red");
  //   red.addEventListener("click", makeRed);
  //   let blue = document.getElementById("blue");
  //   blue.addEventListener("click", makeBlue);
  //   let green = document.getElementById("green");
  //   green.addEventListener("click", makeGreen);

  // better approach
  let buttons = document.querySelectorAll("button");
  buttons.forEach(function (button) {
    button.onclick = function () {
      let h1 = document.querySelector("h1");
      h1.style.color = button.id;
    };
  });
});

// [redundancy]
// function makeRed() {
//   let h1 = document.querySelector("h1");
//   h1.style.color = "red";
// }

// function makeBlue() {
//   let h1 = document.querySelector("h1");
//   h1.style.color = "blue";
// }

// function makeGreen() {
//   let h1 = document.querySelector("h1");
//   h1.style.color = "green";
// }
