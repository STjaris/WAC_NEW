

function getal(event){
	console.log("yeet");
	ingevoerdGetal += event.target.value;
	console.log(ingevoerdGetal);
	document.querySelector("#display").innerHTML = ingevoerdGetal;
	

}

function teken(event){
	console.log("yeet2");
	operator = event.target.value;
	console.log(operator);

	berekening.push(ingevoerdGetal);
	berekening.push(operator);
	console.log(berekening);
	ingevoerdGetal = "";

}



function bereken(event){
	berekening.push(ingevoerdGetal);
	console.log("yeet3");
	var i = 0;
	var resultaat;
	var getal;

	console.log(berekening);

	while (i < berekening.length){
		console.log("test");
		if(berekening[1] == "+"){
			resultaat = parseInt(berekening[0]);
			getal = parseInt(berekening[2]);

			resultaat = resultaat + getal;
			console.log(resultaat);
			
		}
		else if (berekening[1] == "-"){
			resultaat = parseInt(berekening[0]);
			getal = parseInt(berekening[2]);

			resultaat = resultaat - getal;
			console.log(resultaat);
		}

		else if (berekening[1] == "/"){
			resultaat = parseInt(berekening[0]);
			getal = parseInt(berekening[2]);

			resultaat = resultaat / getal;
			console.log(resultaat);
		}
		else {
			resultaat = parseInt(berekening[0]);
			getal = parseInt(berekening[2]);

			resultaat = resultaat * getal;
			console.log(resultaat);
		}

		i = i+3;
	}
	document.querySelector("#display").innerHTML = resultaat;

}

function clear(){
	ingevoerdGetal = "";
	console.log(ingevoerdGetal);
	operator = "";
	getalDisplay = 0;
	berekening = [];
	console.log(berekening);
	document.querySelector("#display").innerHTML = getalDisplay;

}

var ingevoerdGetal = "";
var operator;
var getalDisplay = 0;
var berekening = [];


//getallen

for(var i =0; i< 10; i++){
	var btn = document.querySelector("#btn_" + i);
	btn.addEventListener("click", getal);
}




//operators
var btn_plus = document.querySelector("#btn_plus");
btn_plus.addEventListener("click", teken);

var btn_min = document.querySelector("#btn_min");
btn_min.addEventListener("click", teken);

var btn_div = document.querySelector("#btn_div");
btn_div.addEventListener("click", teken);

var btn_prod = document.querySelector("#btn_prod");
btn_prod.addEventListener("click", teken);

var btn_eq = document.querySelector("#btn_eq");
btn_eq.addEventListener("click", bereken);

var btn_clear = document.querySelector("#btn_clear");
btn_clear.addEventListener("click", clear);

