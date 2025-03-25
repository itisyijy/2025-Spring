document.addEventListener("DOMContentLoaded", () => {
  const entry = document.querySelector("button");
  entry.addEventListener("click", function () {
    const date = document.querySelector("#date").value;
    const text = document.querySelector("#entry").value;

    if (document.querySelectorAll("#posts article").length < 3) {
      const article = document.createElement("article");
      const head3 = document.createElement("h3");
      const paragraph = document.createElement("p");
      head3.textContent = date;
      paragraph.textContent = text;
      article.appendChild(head3);
      article.appendChild(paragraph);
      document.querySelector("#posts").appendChild(article);
    }
  });
});
