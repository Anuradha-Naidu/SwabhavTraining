function addTask() {
  const input = document.querySelector(".inputtext");
  const taskText = input.value.trim();

  if (taskText === "") return;

 
  const li = document.createElement("li");
  li.className = "list-group-item d-flex justify-content-between align-items-center";
  li.innerText = taskText;

  
  const checkButton = document.createElement("button");
  checkButton.innerText = "Check";
  checkButton.className = "btn btn-success btn-sm ms-2";
  checkButton.onclick = function() {
      completedTask(li);
  };

  
  const deleteButton = document.createElement("button");
    deleteButton.className = "btn btn-danger btn-sm ms-2";
    deleteButton.innerHTML = '<i class="bi bi-trash"></i>'; // Correct Bootstrap icon class
    deleteButton.onclick = function() {
        deleteTask(li);
    };


  li.appendChild(checkButton);
  li.appendChild(deleteButton);


  document.getElementById("taskList").appendChild(li);

 
  input.value = '';
}

function completedTask(taskItem) {
  
  taskItem.querySelectorAll("button").forEach(btn => btn.remove());

  
  const removeButton = document.createElement("button");
  removeButton.innerText = "Remove";
  removeButton.className = "btn btn-warning btn-sm ms-2";
  removeButton.onclick = function() {
      removeTask(taskItem);
  };

  
  taskItem.appendChild(removeButton);

  
  document.getElementById("completedList").appendChild(taskItem);
}

function deleteTask(taskItem) {
  taskItem.remove();
}

function removeTask(taskItem) {
  taskItem.remove();
}



