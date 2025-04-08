//starter code for pokedex exercise Web Programming ITM

document.addEventListener("DOMContentLoaded", () => {
  const btnFetch = qs("button");
  btnFetch.onclick = fetchPokemon;
});

/**
 * Fetches Pokémon data and
 */
async function fetchPokemon() {
  let name = id("pokemon-name").value.toLowerCase().trim(); // make lower case, remove spaces
  try {
    const url = `https://pokeapi.co/api/v2/pokemon/${name}`;
    let response = await fetch(url);
    await statusCheck(response);
    response = await response.json();
    await addPokemonToPokedex(response);
  } catch (error) {
    console.log(error);
    alert("Could not find that Pokemon.");
  }
}

/**
 * adds a card to the DOM (pokedex)
 */
async function addPokemonToPokedex(pokemonData) {
  let card = createPokemonCard(pokemonData);
  id("pokedex").appendChild(card);
  id("pokemon-name").value = ""; // clear input section
  checkNumDisplayed();
}

/**
 * check the status of the response from fetch
 */
async function statusCheck(res) {
  if (!res.ok) {
    throw new Error(await res.text());
  }
  return res;
}

/**
 * Creates and returns a Pokémon card element
 */
function createPokemonCard(data) {
  let card = document.createElement("article");
  let title = document.createElement("h1");
  let content = document.createElement("p");
  let img = document.createElement("img");

  // Set content
  title.textContent = "Name: " + data.name;
  content.textContent = `Height: ${data.height} | Weight: ${
    data.weight
  } | Types: ${data.types.map((t) => t.type.name).join(", ")}`;
  img.src = data.sprites.front_default;
  img.alt = data.name;
  img.width = 100;

  // Build card
  card.appendChild(img);
  card.appendChild(title);
  card.appendChild(content);

  // Style and behavior
  card.classList.add("pokemon-card");
  card.addEventListener("dblclick", removePokemon);

  return card;
}

/**
 * Removes selected Pokémon card
 */
function removePokemon() {
  this.remove(); // "this" refers to the article element
  checkNumDisplayed();
}

/**
 * Limits to 4 Pokémon, disables input & button if needed
 */
function checkNumDisplayed() {
  let cards = qsa(".pokemon-card");
  let num = cards.length;
  let button = qs("button");
  let input = id("pokemon-name");

  if (num >= 4) {
    button.disabled = true;
    input.disabled = true;
  } else {
    button.disabled = false;
    input.disabled = false;
  }
}

/**
 * Helper functions
 */
function id(id) {
  return document.getElementById(id);
}

function qs(selector) {
  return document.querySelector(selector);
}

function qsa(query) {
  return document.querySelectorAll(query);
}
