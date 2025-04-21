document.addEventListener("DOMContentLoaded", fetchFirstClue);

let baseURL = "http://114.71.51.49:60003/";

function fetchFirstClue() {
  fetch(baseURL + "api/first-clue")
    .then((response) => response.json())
    .then((json) => {
      console.log(json);
      const containerClue1 = id("clue1");
      containerClue1.innerHTML = json["innerhtml"];
      getSecondClue(json.clueArray);
    })
    .catch((error) => console.log(error));
}

function getSecondClue(clueArray) {
  let nextAPI = baseURL + "api/";
  const clue1Button = id("boton1");
  clue1Button.addEventListener("click", () => {
    let nextAPICode = "";
    clueArray.forEach((coffee) => {
      let index = coffee.length % 5;
      nextAPICode += coffee[index];
    });
    nextAPI += nextAPICode;
    fetchSecondClue(nextAPI).then(
      console.log("fetch second clue successfully")
    );
  });
}

async function fetchSecondClue(api) {
  try {
    let response = await fetch(api);
    response = await statusCheck(response);
    let json = await response.json();
    console.log(json);
    const containerClue2 = id("clue2");
    containerClue2.innerHTML = json["innerhtml"];
    getThirdClue(json["boxes"]);
  } catch (error) {
    console.log(error);
  }
}

function getThirdClue(boxes) {
  const clue2Button = id("boton2");
  clue2Button.addEventListener("click", () => {
    if (id("key-container").childElementCount == 0) {
      const keyContainer = id("key-container");
      keyContainer.style.display = "flex";
      keyContainer.style.flexDirection = "row";

      boxes.forEach((box) => {
        let clue = document.createElement("div");
        clue.textContent = box["text"];
        clue.id = box["id"];
        clue.classList.add("clue2");
        keyContainer.appendChild(clue);
      });
      let nextAPI = baseURL + "api/" + "crud" + "200" + "rest" + "ssr";
      fetchThirdClue(nextAPI).then(
        console.log("fetch third clue successfully")
      );
    }
  });
}

async function fetchThirdClue(api) {
  try {
    let response = await fetch(api);
    response = await statusCheck(response);
    let data = await response.json();
    console.log(data);
    const containerClue3 = id("clue3");
    containerClue3.innerHTML = data["innerhtml"];
    handleFinalClue();
  } catch (error) {
    console.log(error);
  }
}

function handleFinalClue() {
  const clue3Button = id("boton3");
  clue3Button.addEventListener("click", () => {
    console.log("button3");
    makeFinalSubmission().then(
      console.log("make final submission successfully")
    );
  });
}

async function makeFinalSubmission() {
  let myData = {
    name: "Jeong-yun Lee",
    studentId: "21102052",
  };

  let api = baseURL + "api/" + "callbackhell";
  try {
    let response = await fetch(api, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(myData),
    });
    console.log(myData);
    response = await statusCheck(response);
    const result = await response.json();
    console.log("Success", result);
  } catch (error) {
    console.error("Error", error);
  }
}

//helper functions for implementing your solution

async function statusCheck(res) {
  if (!res.ok) {
    throw new Error(await res.text());
  }
  return res;
}

function id(id) {
  return document.getElementById(id);
}

function qs(selector) {
  return document.querySelector(selector);
}

function qsa(selector) {
  return document.querySelectorAll(selector);
}
