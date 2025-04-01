// async
function sillyExample1() {
  return "look at what I return regular";
}

// return 'Promise'
async function sillyExample2() {
  return "look at what I return async";
}

console.log(sillyExample1());
console.log(sillyExample2());

// ---------------------------------------------------------------------------

// await
function newPromise(time) {
  return new Promise((resolve, reject) => {
    setTimeout(resolve, time, "wow");
  });
}

// Example 1: Using .then() to handle the resolved value
let example1 = newPromise(2000);
example1.then((result) => {
  console.log(result + " that was simple (promise)");
});

// Example 2: Using await (inside an async function)
async function runExample2(time) {
  let example2 = await newPromise(time);
  console.log(example2 + " that was simple (async/await)");
}
runExample2(4000);
