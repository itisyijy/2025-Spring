// Error
const somethingWrong = (cb) => {
  const error = new Error("Something went wrong");
  cb(error, null);
};

somethingWrong((error, result) => {
  if (error) {
    console.log("There was an error");
    return;
  }
  console.log("Everything went well");
});

// ---------------------------------------------------------------------------

// No Error
const everythingOk = (cb) => {
  const result = "It worked!";
  cb(null, result);
};
everythingOk((error, result) => {
  if (error) {
    console.log("There was an error");
    return;
  }
  console.log(result);
  console.log("Everything went well");
});