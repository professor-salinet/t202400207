var h1Titulo1 = document.createElement("h1");
h1Titulo1.innerText = "Título 1";

document.body.appendChild(h1Titulo1);

var labelRotulo = document.createElement("label");
labelRotulo.innerText = "Rótulo";

document.body.appendChild(labelRotulo);

// Criar o emlemento div
var divContainer = document.createElement("div");
divContainer.innerText = "Container";
divContainer.dataset.id = "divContainer";
divContainer.id = "divContainer";
divContainer.title = "divContainer";
divContainer.dir = "divContainer";

let letVar = "letVar";
console.log(letVar);

function funcao() {
    console.log(letVar);
}

divContainer.onclick = funcao;

document.body.appendChild(divContainer);
