function initpage() {
    fetch("https://ipapi.co/json/")
        .then(function (response) {
            return response.json();
        })
        .then(function (myJson) {
            console.log(myJson);

            console.log(myJson.latitude);
            console.log(myJson.longitude);

            var code = myJson.country;
            var country = myJson.country_name;
            var region = myJson.region;
            var city = myJson.city;
            var postal = myJson.postal;
            var lat = myJson.latitude;
            var lon = myJson.longitude;
            var ip = myJson.ip;

            document.querySelector("#code").innerHTML = code;
            document.querySelector("#land").innerHTML = country;
            document.querySelector("#regio").innerHTML = region;
            document.querySelector("#stad").innerHTML = city;
            // document.querySelector("#stad").addEventListener("click",
            // function() {
            // showWeather(lat,lon, city);
            // });
            document.querySelector("#postcode").innerHTML = postal;
            document.querySelector("#latitude").innerHTML = lat;
            document.querySelector("#longitude").innerHTML = lon;
            document.querySelector("#ip").innerHTML = ip;


            showWeather(myJson.latitude, myJson.longitude, city);
            loadCountries();

            document.querySelector('#opslaanBtn').addEventListener('click', function () {
                var formData = new FormData(document.querySelector('#edit'));
                console.log(formData);
                var encData = new URLSearchParams(formData.entries());
                console.log(encData);

                fetch("restservices/countries", { method: "POST",
                 body: encData,
                headers : {'Authorization': 'Bearer ' + window.sessionStorage.getItem('myJWT')} })
                    .then(function (response) {
                        return response.json();
                    })
                    .then(function (myJson) {
                        console.log(myJson);

                        var elementen = [formData.get('invoerland'), formData.get('invoerCap'), formData.get('invoerRegio'), formData.get('invoerlandOpp'), formData.get('invoerlandPop')]

                        for (var i = 0; i < elementen.length; i++) {
                            var td = document.createElement("td");
                            td.setAttribute("id", 'Land' + country.Code);
                            td.setAttribute("class", "row");



                            var btnDelete = document.createElement('button');
                            btnDelete.setAttribute('name', 'Delete');
                            btnDelete.setAttribute('type', 'button');
                            btnDelete.setAttribute('id', 'delete' + country.Code);
                            btnDelete.innerHTML = "Delete";

                            btnDelete.addEventListener('click', function () {

                                fetch("restservices/countries/" + country.Code, { method: 'DELETE',
                                headers : {'Authorization': 'Bearer ' + window.sessionStorage.getItem('myJWT')}
                             })
                                    .then(function (response) {
                                        if (response.ok) // response-status = 200 OK
                                            console.log("Country deleted!");
                                        else if (response.status == 404)
                                            console.log("Country not found!");
                                        else console.log("Cannot delete country!");



                                    })

                                    var rowDelete = document.querySelector('#' + id);
                                    document.querySelector('#tableMain').removeChild(rowDelete);

                            });


                            var btnEdit = document.createElement('button');
                            btnEdit.setAttribute('name', 'Edit');
                            btnEdit.setAttribute('type', 'button');
                            btnEdit.setAttribute('id', 'edit' + country.Code);
                            btnEdit.innerHTML = "Edit";

                            btnEdit.addEventListener('click', function () {
                                fetch("restservices/countries/" + country.Code)
                                    .then(function (response) {
                                        return response.json();
                                    })
                                    .then(function () {


                                        var idCode = document.getElementById('invoerCode');
                                        var idLand = document.getElementById('invoerland');
                                        var idCap = document.getElementById('invoerCap');
                                        var idRegio = document.getElementById('invoerRegio');
                                        var idOpp = document.getElementById('invoerlandOpp');
                                        var idPop = document.getElementById('invoerlandPop');

                                        idCode.value = country.Code;
                                        idLand.value = country.Name;
                                        idCap.value = country.Capital;
                                        idRegio.value = country.Region;
                                        idOpp.value = country.Surface;
                                        idPop.value = country.Population;

                                    })
                            })

                            var txt = document.createTextNode(elementen[i]);


                            td.appendChild(txt);
                            document.querySelector('#tableMain').appendChild(td);
                        }

                    });

            })

            document.querySelector('#editBtn').addEventListener('click', function () {
                var id = document.querySelector('#invoerCode').value;
                var formData = new FormData(document.querySelector('#edit'));
                console.log(formData);
                var encData = new URLSearchParams(formData.entries());
                console.log(encData);

                fetch("restservices/countries/" + id, { method: "PUT", body: encData, 
                headers : {'Authorization': 'Bearer ' + window.sessionStorage.getItem('myJWT')}
             })
                    .then(function (response) {
                        return response.json();
                    })
                    .then(function (myJson) {
                        console.log(myJson);
                        console.log(id);
                        var rowDelete = document.querySelector('#' + id);
                        document.querySelector('#tableMain').removeChild(rowDelete);

                        var tr = document.createElement("tr");
                        tr.setAttribute('id', country.Code);
                        var elementen = [formData.get('invoerland'), formData.get('invoerCap'), formData.get('invoerRegio'), formData.get('invoerlandOpp'), formData.get('invoerlandPop')]

                        for (var i = 0; i < elementen.length; i++) {
                            var td = document.createElement("td");
                            td.setAttribute("id", 'Land' + country.Code);
                            td.setAttribute("class", "row");



                            var btnDelete = document.createElement('button');
                            btnDelete.setAttribute('name', 'Delete');
                            btnDelete.setAttribute('type', 'button');
                            btnDelete.setAttribute('id', 'delete' + country.Code);
                            btnDelete.innerHTML = "Delete";

                            btnDelete.addEventListener('click', function () {

                                fetch("restservices/countries/" + country.Code, { method: 'DELETE' })
                                    .then(function (response) {
                                        if (response.ok) // response-status = 200 OK
                                            console.log("Country deleted!");
                                        else if (response.status == 404)
                                            console.log("Country not found!");
                                        else console.log("Cannot delete country!");



                                    })

                                var rowDelete = document.querySelector('#' + id);
                                document.querySelector('#tableMain').removeChild(rowDelete);

                            });


                            var btnEdit = document.createElement('button');
                            btnEdit.setAttribute('name', 'Edit');
                            btnEdit.setAttribute('type', 'button');
                            btnEdit.setAttribute('id', 'edit' + country.Code);
                            btnEdit.innerHTML = "Edit";

                            btnEdit.addEventListener('click', function () {
                                fetch("restservices/countries/" + country.Code)
                                    .then(function (response) {
                                        return response.json();
                                    })
                                    .then(function () {


                                        var idCode = document.getElementById('invoerCode');
                                        var idLand = document.getElementById('invoerland');
                                        var idCap = document.getElementById('invoerCap');
                                        var idRegio = document.getElementById('invoerRegio');
                                        var idOpp = document.getElementById('invoerlandOpp');
                                        var idPop = document.getElementById('invoerlandPop');

                                        idCode.value = country.Code;
                                        idLand.value = country.Name;
                                        idCap.value = country.Capital;
                                        idRegio.value = country.Region;
                                        idOpp.value = country.Surface;
                                        idPop.value = country.Population;

                                    })
                            })

                            var txt = document.createTextNode(elementen[i]);


                            td.appendChild(txt);
                            document.querySelector('#tableMain').appendChild(td);


                        }

                    });

            })
        })
}


function showWeather(latitude, longitude, city) {

    if (window.sessionStorage.getItem("city") != null && tijdVerschil(tijd, cityOpslag.tijd) == true) {
        var cityOpslag = JSON.parse(window.sessionStorage.getItem(city));

        for (var i = 0; i < cityOpslag.length; i++) {
            console.log("text2");
            for (key in cityOpslag) {
                var val = cityOpslag[key];
                document.querySelector("#" + val).innerHTML = cityOpslag[i];
                console.log(window.sessionStorage);
            }
        }
    } else {


        fetch("https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=ab468d403c74846fa41ba197fc09afb4")
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                var temp = myJson.main.temp;
                var lucht = myJson.main.humidity;
                var speed = myJson.wind.speed;
                var richting = myJson.wind.deg;

                console.log("temp: " + temp);
                console.log("lucht: " + lucht);
                console.log("snelheid: " + speed);
                console.log("richting: " + richting);

                document.querySelector("#city").innerHTML = city;
                document.querySelector("#temp").innerHTML = temp;
                document.querySelector("#lucht").innerHTML = lucht;
                document.querySelector("#windsnelheid").innerHTML = speed;
                document.querySelector("#windrichting").innerHTML = richting;

                var today = new Date;
                var tijd = today.getHours() + ":" + today.getMinutes();
                console.log(tijd);
                var cityOpslag = { "city": city, "temp": temp, "lucht": lucht, "speed": speed, "richting": richting, "tijd": tijd }

                window.sessionStorage.setItem(city, JSON.stringify(cityOpslag));
                console.log(window.sessionStorage);

                // storage(cityOpslag);




            });
    }

}

// function storage(cityOpslag){
// console.log("text");
// for(var i =0; i < cityOpslag; i++){
// console.log("text2");
// for (key in cityOpslag){
// var val = cityOpslag[key];
// document.querySelector("#" + val).innerHTML = cityOpslag[i];
// console.log(window.sessionStorage);
// }
// }
// }

function tijdVerschil(tijd, tijd2) {
    var tijd = tijd.split(":");
    var tijd2 = tijd2.split(":");

    var verschil = false;

    if (tijd - tijd2 >= 10) {
        verschil = false;
    }
    else {
        verschil = true;
    }

    return verschil;
}


function loadCountries() {

    fetch("http://localhost:4711/firstapp/restservices/countries")
        .then(function (response) {
            return response.json();
        })
        .then(function (countries) {
            for (let country of countries) {
                var tr = document.createElement("tr");
                tr.setAttribute('id', country.Code);
                var elementen = [country.Name, country.Capital, country.Region, country.Surface, country.Population]

                for (var i = 0; i < elementen.length; i++) {
                    var td = document.createElement("td");
                    td.setAttribute("id", 'Land' + country.Code);
                    td.setAttribute("class", "row");



                    var btnDelete = document.createElement('button');
                    btnDelete.setAttribute('name', 'Delete');
                    btnDelete.setAttribute('type', 'button');
                    btnDelete.setAttribute('id', 'delete' + country.Code);
                    btnDelete.innerHTML = "Delete";

                    btnDelete.addEventListener('click', function () {

                        fetch("restservices/countries/" + country.Code, { method: 'DELETE', 
                        headers : {'Authorization': 'Bearer ' + window.sessionStorage.getItem('myJWT')}
                     })
                            .then(function (response) {
                                if (response.ok) // response-status = 200 OK
                                    console.log("Country deleted!");
                                else if (response.status == 404)
                                    console.log("Country not found!");
                                else console.log("Cannot delete country!");



                            })
                            var rowDelete = document.querySelector('#' + country.Code);
                            document.querySelector('#tableMain').removeChild(rowDelete);
                    });


                    var btnEdit = document.createElement('button');
                    btnEdit.setAttribute('name', 'Edit');
                    btnEdit.setAttribute('type', 'button');
                    btnEdit.setAttribute('id', 'edit' + country.Code);
                    btnEdit.innerHTML = "Edit";

                    btnEdit.addEventListener('click', function () {
                        fetch("restservices/countries/" + country.Code)
                            .then(function (response) {
                                return response.json();
                            })
                            .then(function () {


                                var idCode = document.getElementById('invoerCode');
                                var idLand = document.getElementById('invoerland');
                                var idCap = document.getElementById('invoerCap');
                                var idRegio = document.getElementById('invoerRegio');
                                var idOpp = document.getElementById('invoerlandOpp');
                                var idPop = document.getElementById('invoerlandPop');

                                idCode.value = country.Code;
                                idLand.value = country.Name;
                                idCap.value = country.Capital;
                                idRegio.value = country.Region;
                                idOpp.value = country.Surface;
                                idPop.value = country.Population;

                            })
                    })

                    var txt = document.createTextNode(elementen[i]);


                    td.appendChild(txt);
                    tr.appendChild(td);
                    // tr.appendChild(btnDelete);
                }
                document.querySelector("table").appendChild(tr);
                tr.appendChild(btnDelete);
                tr.appendChild(btnEdit);


                tr.addEventListener("click", function () {
                    showWeather(country.Lat, country.Lng, country.Capital);
                });
            }
        })
}

function lokaalWeer() {
    fetch("https://ipapi.co/json/")
        .then(function (response) {
            return response.json();
        })
        .then(function (myJson) {
            showWeather(myJson.latitude, myJson.longitude, myJson.city);
        })

}


function login() {
    console.log("logInBtn clicked!");
    document.getElementById("myForm").style.display = "block";

}
document.querySelector("#logInBtn").addEventListener('click', login);


function btnLogin() {
    console.log("btnLogIn clicked!");

    var formData = new FormData(document.querySelector('#form-login'));
    console.log(formData);
    var encData = new URLSearchParams(formData.entries());
    console.log(encData);

    fetch("restservices/authentication", { method: "POST", body: encData })
        .then(function (response) {
            if (response.ok){ 
                console.log("Login succes!");
                return response.json();
            } else throw "Wrong username/password!";
        })
        .then(function (myJson) {
            window.sessionStorage.setItem('myJWT', myJson.JWT);

            console.log(sessionStorage);
            formClose();
        })
        .catch(function(error){
            console.log(error);
        })

}
document.querySelector("#btnLogIn").addEventListener('click', btnLogin);

function formClose() {
    console.log("logInCancelBtn clicked!");
    document.getElementById("myForm").style.display = "none";
}
document.querySelector("#logInCancelBtn").addEventListener('click', formClose);



// function opslaan(){
// document.querySelector().addEventListener('click', function(){
// var formData = new FormData(document.querySelector('#editInfo'));
// console.log(formData);
// var encData = new URLSearchParams(formData.entries());
// console.log(encData);

// fetch("restservices/countries"), {method: "POST", body: encData}
// .then(response => response.json())
// .then(function(myJson){
// console.log(myJson);
// initpage();
// });

// })

// }






