const app = require("./app");
const PORT = 8080;

// Starting server.
app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
