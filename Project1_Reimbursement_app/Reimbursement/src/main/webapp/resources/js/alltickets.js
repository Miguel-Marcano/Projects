console.log("in manager js file");

window.onload = function(){
	ajaxGetAllTickets();
	
	document
        .getElementById("statusSubmit")
        .addEventListener('click', getStatus);
}

function ajaxGetAllTickets(){
	console.log("starting the ajax request");
	
	
	
	fetch('http://localhost:8080/Reimbursement/manager/home/json')
		.then(function(daResponse){
					const convertedResponse = daResponse.json();
					return convertedResponse;
				})
		.then(function(daSecondResponse){
					console.log(daSecondResponse);
					ourDOMManipulation(daSecondResponse);
				})
}

function getStatus(){
    console.log("Status Submit has been clicked!!!");
    
    let statusId = document.getElementById('inlineFormCustomSelect').value;
    console.log(statusId);
    
    fetch('http://localhost:8080/Reimbursement/manager/home/json')
		.then(function(daResponse){
					const convertedResponse = daResponse.json();
					return convertedResponse;
				})
		.then(function(daSecondResponse){
					console.log("After click");
					console.log(daSecondResponse);
					deleteTable(daSecondResponse);
					statusTable(daSecondResponse, statusId);
				})
    
}

function deleteTable(statusJSON){
	let count = 0;
	var rowCount = document.getElementById('TicketTable').rows.length;
	console.log(rowCount);
	while(count < rowCount-1){
		document.getElementById("TicketTable").deleteRow(1);
		count++;
	}
}

function statusTable(staJSON, type){
	console.log("Inside the status Table function");
	//console.log(staJSON);
	//console.log(type);
	const statusType = [];
	for(let i = 0; i< staJSON.length; i++){
		if(staJSON[i].status === type){
			statusType.push(staJSON[i]);
		}
	}
	console.log(statusType);
	ourDOMManipulation(statusType);
}

function ourDOMManipulation(ourJSON){
	
	for(let i = 0; i< ourJSON.length; i++){
		////////////CREATE ELEMENTS DYNAMICALLY////////////////
		
		//step1: creating our new element
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");
		
		let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		let newTD5 = document.createElement("td");
		let newTD6 = document.createElement("td");
		let newTD7 = document.createElement("td");
		
		//step 2: populate creations
		newTH.setAttribute("scope", "row");
		let myTextH = document.createTextNode(ourJSON[i].rei_id);
		let myTextD1 = document.createTextNode(ourJSON[i].amount);
		let myTextD2 = document.createTextNode(ourJSON[i].sub_time);
		let myTextD3 = document.createTextNode(ourJSON[i].res_time);
		let myTextD4 = document.createTextNode(ourJSON[i].description);
		let myTextD5 = document.createTextNode(ourJSON[i].receipt);
		let myTextD6 = document.createTextNode(ourJSON[i].status);
		let myTextD7 = document.createTextNode(ourJSON[i].type);
		
		//all appending
		newTH.appendChild(myTextH);
		newTD1.appendChild(myTextD1);
		newTD2.appendChild(myTextD2);
		newTD3.appendChild(myTextD3);
		newTD4.appendChild(myTextD4);
		newTD5.appendChild(myTextD5);
		newTD6.appendChild(myTextD6);
		newTD7.appendChild(myTextD7);
		
		newTR.appendChild(newTH);
		newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);
		newTR.appendChild(newTD5);
		newTR.appendChild(newTD6);
		newTR.appendChild(newTD7);
		
		let newSelection = document.querySelector("#managerTicketsTable");
		newSelection.appendChild(newTR);
	}
}