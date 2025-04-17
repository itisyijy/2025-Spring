document.addEventListener("DOMContentLoaded", fetchFirstClue);

let baseURL = "http://114.71.51.49:60003/";

function fetchFirstClue() {
  fetch(baseURL + "api/first-clue")
    .then((response) => response.json())
    .then((json) => {
      console.log(json);
      id("clue1").innerHTML = json["innerhtml"];
    })
    .catch((error) => console.log(error));
}

function getSecondClue() {}

async function fetchSecondClue() {}

function getThirddClue() {}

async function fetchThirdClue() {}

function handleFinalClue() {}

async function makeFinalSubmission() {}

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
