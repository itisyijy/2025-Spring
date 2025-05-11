const express = require("express"); // import 'express' module
const app = express();

const PORT = process.env.POST || 8080;

// http://localhost:8080/posts
app.get("/posts", function (req, res) {
  console.log("Endpoint /posts received a GET request.");
  res.type("text").send("Hello World");
});

// http://localhost:8080/hello
app.get("/hello", function (req, res) {
  console.log("Endpoint /hello received a GET request.");

  res.set("Content-Type", "text/plain");
  // res.type("text"); // Do the same thing.

  res.send("Hello World 2");
});

// http://localhost:8080/hello-json
app.get("/hello-json", function (req, res) {
  console.log("Endpoint /hello-json received a GET request.");

  res.set("Content-Type", "application/json");
  // res.type("json"); // Do the same thing.

  res.send({ msg: "Hello World with JSON" });
});

// http://localhost:8080/hello-json2
app.get("/hello-json2", function (req, res) {
  console.log("Endpoint /hello-json2 received a GET request.");

  // No need to set content type
  res.json({ msg: "Hello World with JSON" });
});

// http://localhost:8080/majors/ITM/courses/WebProg
app.get("/majors/:major/courses/:course", function (req, res) {
  res.type("text");
  res.send(
    "You sent a request for the major " +
      req.params.major +
      ", for the course " +
      req.params.course +
      " (Path)"
  );
});

// http://localhost:8080/courseInfo?major=ITM&course=WebProg
app.get("/courseInfo", function (req, res) {
  let major = req.query.major; // ITM
  let course = req.query.course; // WebProg
  res.send(
    "You sent a request for the major " +
      major +
      ", for the course " +
      course +
      " (Query)"
  );
  console.log(major + course);
  // do something with variables in the response
});

// Setting errors
// http://localhost:8080/cityInfo?state=Texas
app.get("/cityInfo", function (req, res) {
  let state = req.query.state;
  let city = req.query.city;
  if (!(state && city)) {
    res
      .status(400)
      .send("Error: Missing required city and state query parameters.");
  } else {
    res.send("You sent a request for " + city + ", " + state);
  }
});

// asynchronous function
app.listen(PORT, () => {
  // callback function
  console.log(`Our first node app listening on port ${PORT}`);
});
