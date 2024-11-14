document.addEventListener("DOMContentLoaded", () => {
const container = document.getElementById("gameContainer");

let boxes = prompt("Enter the number of boxes: ");
let targetNum = prompt("Enter any random number: ");

let maxAttempts = 5;
let attempts = 0;

for(let i=1;i<=boxes;i++){
 const box = document.createElement("div");
 box.classList.add("box");
 box.innerText = i;
 box.addEventListener("click", () => checkGuess(i, box));
 gameContainer.appendChild(box);
}


function checkGuess(guess, boxElement){
 if (attempts >= maxAttempts) return; 
 attempts++;
 if(guess<targetNum){
  boxElement.classList.add("yellow");
 } else if(guess > targetNum){
  boxElement.classList.add("red");
 } else{
  boxElement.classList.add("green");
  alert("You won the game!");
  disableAllBoxes();
  return;
 }

 if (attempts >= maxAttempts) {
  alert("You've reached the maximum number of attempts! Game over.");
  disableAllBoxes();
}
}

function disableAllBoxes() {
 const boxes = document.querySelectorAll(".box");
 boxes.forEach(box => box.style.pointerEvents = "none");
}

});
