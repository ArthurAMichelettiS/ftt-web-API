/**
 * 
 */


function createNewsCards(nData) {

  for (i in nData) {

    var card = document.createElement("div"); //Card
    card.setAttribute("class", "card");

    var data = document.createElement("div"); //Date text
    data.setAttribute("class", "data");

    var time = document.createElement("time"); //Creation date of repository

    data.innerHTML = "CRIADO EM:"
    data.appendChild(time)

    var name = document.createElement("h2"); //Repository name

    var description = document.createElement("div"); //Repository description
    description.setAttribute("class", "info");

    var language = document.createElement("div"); //Language name
    language.setAttribute("class", "tags");

    time.innerText = nData[i].user-color;

    name.innerText = nData[i].user-id;
    description.innerText = nData[i].user-name;
    language.innerText = nData[i].user-email;

    // Append the text to cards
    card.appendChild(data);
    card.appendChild(name);
    card.appendChild(description);
    card.appendChild(language);

    document.getElementById("container").appendChild(card);

  }
}