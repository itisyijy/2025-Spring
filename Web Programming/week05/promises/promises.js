// Promises
fetch("https://pokeapi.co/api/v2/pokemon/pikachu")
  .then((response) => response.json())
  .then((json) => console.log(json))
  .catch((error) => console.log(error));

// ---------------------------------------------------------------------------

// Creating Promises
function setTimeoutPromise(time) {
  function executorFunction(resolve, reject) {
    setTimeout(function () {
      resolve();
    }, time);
  }

  return new Promise(executorFunction);
}

console.log("Before setTimeoutPromise");
setTimeoutPromise(1000).then(function () {
  console.log("one second later");
});
console.log("After setTimeoutPromise");
