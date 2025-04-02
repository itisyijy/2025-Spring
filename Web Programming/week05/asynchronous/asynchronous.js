// Callback Function
const doSomething = (cb) => {
  console.log("Doing something...");
  cb();
};

// typeof(nextStep) == 'function'
// typeof(nextStep()) == 'undefined'
const nextStep = () => {
  console.log("Callback called");
};

// doSomething(nextStep()); // -> TYPE ERROR! nextStep() is undefined.
doSomething(nextStep);

// ---------------------------------------------------------------------------

// setTimeOut()
// Callback: init
document.addEventListener("DOMContentLoaded", init);
function init() {
  // Callback: delayedMessage
  id("demo-btn").addEventListener("click", delayedMessage);

  // setInterval()
  repeatedMessage();
}

function delayedMessage() {
  id("output-text").textContent = "It's gonna be legend...wait for it...";
  // Callback: sayHello
  setTimeout(sayHello, 3000); // 3 sec
}

function sayHello() {
  // called when the timer goes off
  id("output-text").textContent = "dary... Legendary!";
}

// ---------------------------------------------------------------------------

// setInterval()
let timerId = null; // stores ID of interval timer
function repeatedMessage() {
  timerId = setInterval(sayAnnyeong, 1000);
}

function sayAnnyeong() {
  id("timer-text").textContent += "안녕!";
}

// ---------------------------------------------------------------------------

// Handy shortcut functions
function id(id) {
  return document.getElementById(id);
}

function qs(selector) {
  return document.querySelector(selector);
}

function qsa(selector) {
  return document.querySelectorAll(selector);
}
