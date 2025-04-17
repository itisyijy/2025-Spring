document.addEventListener("DOMContentLoaded", fetchFirstClue);

let baseURL = "http://114.71.51.49:60003/";
let json_output;

function fetchFirstClue() {
  fetch(baseURL + "api/first-clue")
    .then((response) => response.json())
    .then((json) => {
      console.log(json);
      json_output = json;
      const containerClue1 = id("clue1");
      containerClue1.innerHTML = json["innerhtml"];
      const clue1Button = id("boton1");
      clue1Button.addEventListener("click", fetchSecondClue);
    })
    .catch((error) => console.log(error));
}

function getSecondClue() {
  let secondClue = "";
  json_output["clueArray"].forEach((element) => {
    let extract = element[element.length % 5];
    secondClue += extract;
  });
  return "api/" + secondClue;
}

async function fetchSecondClue() {
  try {
    let secondAPI = await getSecondClue();
    let response = await fetch(baseURL + secondAPI);
    let json = await response.json();
    console.log(json);
    json_output = json;
    const containerClue2 = id("clue2");
    containerClue2.innerHTML = json["innerhtml"];
    const clue2Button = id("boton2");
    clue2Button.addEventListener("click", () => {
      json["boxes"].forEach((element) => {
        let box = document.createElement("div");
        box.textContent = element["text"];
        box.id = element["id"];
        id("key-container").appendChild(box);
      });
      id("key-container").style.display = "flex";
      fetchThirdClue();
    });
  } catch (error) {
    console.log(error);
  }
}

function getThirddClue() {
  return "api/" + "crud" + "200" + "rest" + "ssr";
}

async function fetchThirdClue() {
  try {
    let thirdAPI = await getThirddClue();
    let response = await fetch(baseURL + thirdAPI);
    let json = await response.json();
    console.log(json);
    json_output = json;
    const containerClue3 = id("clue3");
    containerClue3.innerHTML = json["innerhtml"];
    const clue3Button = id("boton3");
    clue3Button.addEventListener("click", makeFinalSubmission);
  } catch (error) {
    console.log(error);
  }
}

function handleFinalClue() {
  let finalClue = "callbackhell";
  json_output = {
    name: "Jeong-yun Lee",
    studentId: "21102052",
  };
  return "api/" + finalClue;
}

async function makeFinalSubmission() {
  try {
    let finalAPI = await handleFinalClue();
    const response = await fetch(baseURL + finalAPI, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(json_output),
    });
    console.log(json_output);
    const responseCheck = await statusCheck(response);
    const result = await responseCheck.json();
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
