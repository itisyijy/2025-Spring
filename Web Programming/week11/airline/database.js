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

async function executeInsert() {
  try {
    let db = await getDBConnection(DB_NAME);

    const sql =
      'INSERT INTO flights (origin, destination, duration) VALUES ("Incheon", "Monterrey", 960);';
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
