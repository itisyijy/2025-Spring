// execute the code inside after HTMl parsing by browser.
document.addEventListener("DOMContentLoaded", () => {
  let mybutton = document.querySelector("#btn");
  mybutton.addEventListener("click", buttonClick);
});

// HTML parsed yet because <script> is above of <body>.
// So the selector cause error without "DOMContentLoaded".
//
// let mybutton = document.querySelector("#btn");
// mybutton.addEventListener("click", buttonClickMsg);

function buttonClick() {
  console.log("You clicked the button!");
  let list = document.getElementById("mylist");

  // adding new element
  let listitem = document.createElement("li");
  listitem.textContent = "new item";
  list.appendChild(listitem);

  // modifing element
  let head1 = document.getElementById("heading1");
  head1.style.color = "red";

  // hiding element
  let head2 = document.getElementById("heading2");
  head2.style.display = "none";
  return;
}
