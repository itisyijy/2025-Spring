const sqlite3 = require("sqlite3");
const sqlite = require("sqlite");
const fs = require("fs");

const DB_NAME = "airline.db";

/**
 * Establishes a database connection to the database and returns the database object.
 * Any errors that occur should be caught in the function that calls this one.
 * @returns {Promise<sqlite3.Database>} - The database object for the connection.
 */

async function getDBConnection(dbFileName) {
  const db = await sqlite.open({
    filename: dbFileName,
    driver: sqlite3.Database,
  });
  console.log("Connection succesful.");
  return db;
}
// testing code
// getDBConnection(DB_NAME);

async function loadDB(dbFileName, sqlFileName) {
  try {
    let db = await getDBConnection(dbFileName);
    const sql = fs.readFileSync(sqlFileName, "utf8");
    const result = await db.exec(sql);

    console.log("Database loaded successfully.");
    console.log(result);

    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code
// loadDB(DB_NAME, "flights_schema.sql");

async function executeQuery() {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql = 'SELECT * FROM flights WHERE origin = "New York";';
    let result = await db.all(sql);
    console.log(result);
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code
// executeQuery();

//Practice yourself: Query all flights longer than 600 minutes
async function durationFilter(target) {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql = "SELECT * FROM flights WHERE duration > ?;";
    let result = await db.all(sql, [target]);
    console.log(result);
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code
// durationFilter(600);

//Practice yourself: Find all flights arriving in Paris
async function destinationFinder(destination) {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql = "SELECT * FROM flights WHERE destination = ?;";
    let result = await db.all(sql, [destination]);
    console.log(result);
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code
// destinationFinder("Paris");

//Practice yourself: List flights originating from New York, Seoul, or Paris
async function originFinder(destination) {
  try {
    let db = await getDBConnection(DB_NAME);

    const placeholders = destination.map(() => "?").join(", ");
    const sql = `SELECT * FROM flights WHERE destination IN (${placeholders});`;
    let result = await db.all(sql, destination);
    console.log(result);
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code
// originFinder(["New York", "Seoul", "Paris"]);

async function executeInsert() {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql = `INSERT INTO flights (origin, destination, duration) VALUES ("Incheon", "Monterrey", 960);`;
    let result = await db.exec(sql);
    console.log(result);
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code: exec() NO RETURN
// executeInsert();

async function runInsert() {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql =
      'INSERT INTO flights (origin, destination, duration) VALUES ("Guatemala", "Dallas", 180);';
    let result = await db.run(sql);
    console.log(result);
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code: run() YES RETURN
// runInsert();

// Practice yourself: Insert three new flights into the flights table
async function runInsert(origin, destination, duration) {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql = `INSERT INTO flights (origin, destination, duration) VALUES (?, ?, ?);`;
    let result = await db.run(sql, [origin, destination, duration]);
    console.log(result);
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// Practice yourself: Insert three new flights into the flights table
// // From Incheon to San Francisco, Incheon to Amsterdam, Paris to Rome
// runInsert("Incheon", "San Francisco", 635);
// runInsert("Incheon", "Amsterdam", 835);
// runInsert("Paris", "Rome", 125);

async function extractData() {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql = "SELECT * FROM flights;";
    let result = await db.all(sql);
    result.forEach((flight) => {
      console.log(
        `Flight ${flight.id}, from ${flight.origin} to ${flight.destination} takes ${flight.duration} minutes.`
      );
    });
    await db.close();
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
}
// testing code
// extractData();

// Practice yourself: Create an API endpoint /flights that returns a JSON listing all the flights
const express = require("express");
const app = express();
const PORT = 8080;

app.get("/flights", async function (req, res) {
  try {
    let db = await getDBConnection(DB_NAME);
    let result = await db.all("SELECT * FROM flights");
    await db.close();

    console.log("Endpoint /flights received a GET request.");
    res.json(result);
  } catch (err) {
    console.error("Failed to load database:", err.message);
  }
});

app.listen(PORT, function () {
  console.log(`Our first node app listening on port ${PORT}`);
});
