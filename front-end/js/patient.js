test();
let patient = new Object();
patient.name = nameEl.value;
patient.surname = surnameEl.value;
patient.age = ageEl.value;
patient.patientCard = patientCardEl.value;
async function myinfo() {
  const nameEl = document.getElementById("name");
  const surnameEl = document.getElementById("surname");
  const ageEl = document.getElementById("age");
  const patientCardEl = document.getElementById("patientCard");

  patient.name = nameEl.value;
  patient.surname = surnameEl.value;
  patient.age = ageEl.value;
  patient.patientCard = patientCardEl.value;
  if (
    nameEl.value == "" ||
    surnameEl.value == "" ||
    ageEl.value == "" ||
    patientCardEl.value == ""
  ) {
    alert("Заполните все поля");
  } 
  else {
    let response = await fetch("http://localhost:1489/api/v3/patient/new", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=utf-8",
      },
      body: JSON.stringify(patient),
    });

    nameEl.value = "";
    surnameEl.value = "";
    ageEl.value = "";
    patientCardEl.value = "";
   
    notice();
    setTimeout(fixed, 2000);
   test();
  }
  console.log(patient);
}
async function update() {
  const nameEl = document.getElementById("name");
  const surnameEl = document.getElementById("surname");
  const ageEl = document.getElementById("age");
  const patientCardEl = document.getElementById("patientCard");

  patient.name = nameEl.value;
  patient.surname = surnameEl.value;
  patient.age = ageEl.value;
  patient.patientCard = patientCardEl.value;
  if (
    nameEl.value == "" ||
    surnameEl.value == "" ||
    ageEl.value == "" ||
    patientCardEl.value == ""
  ) {
    alert("Заполните все поля");
  } else {
    let response = await fetch("http://localhost:1489/api/v3/patient", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json;charset=utf-8",
      },
      body: JSON.stringify(patient),
    });

    nameEl.value = "";
    surnameEl.value = "";
    ageEl.value = "";
    patientCardEl.value = "";
   
    noticeUpd();
    setTimeout(fixed, 2000);
   test();
  }
  console.log(patient);
}
async function notice() {
  document.querySelector('.note').innerHTML = `<div class="notess">New post was added</div>`;
}
async function delnotice() {
  document.querySelector('.note').innerHTML = `<div class="delnot">Post deleted</div>`;
}
async function fixed() {
  document.querySelector('.note').innerHTML = (``);
}
async function noticeUpd() {
  document.querySelector('.note').innerHTML = `<div class="notessUpd">Post Update</div>`;
}

async function wiwod(responset){

  if (responset.ok) {
    text = await responset.json();

    const moviesEI = document.querySelector(".patients");

    document.querySelector(".patients").innerHTML = "";
    console.log(text);
   
      const movieEl = document.createElement("div");
      movieEl.classList.add("patient");
      movieEl.innerHTML = `
        <div class="Info">
          <p class="InfoP">Имя: ${text.name}, Фамилия: ${text.surname}, Возраст: ${text.age},Номер карты: ${text.cardNumber} 
           <input type="button" value="delete" onclick="deletePatient(${text.cardNumber})"></p>
        </div>
         `;
      moviesEI.appendChild(movieEl);
     
  } else {
    alert("Ошибка: " + responset.status + ", пациент не найден!" );
  }

}

async function test() {
  let text;
  let responset = await fetch("http://localhost:1489/api/v3/patient/list");

  if (responset.ok) {
    // если HTTP-статус в диапазоне 200-299
    // получаем тело ответа (см. про этот метод ниже)
    text = await responset.json();

    const moviesEI = document.querySelector(".patients");

    document.querySelector(".patients").innerHTML = "";
    text= text.reverse();
    text.forEach((patientInfo) => {

      if(patientInfo.deleted == false ){
      const movieEl = document.createElement("div");
      movieEl.classList.add("patient");
      movieEl.innerHTML = `
        <div class="Info">
          <p class="InfoP">Имя: ${patientInfo.name}, Фамилия: ${patientInfo.surname}, Возраст: ${patientInfo.age}, Номер карты: ${patientInfo.cardNumber}
           <input type="button" value="Delete" class="btnn" onclick="deletePatient(${patientInfo.cardNumber})">
           </p>
        </div>
         `;
      moviesEI.appendChild(movieEl);
      }
    });
  } else {
    alert("Ошибка HTTP: " + responset.status);
  }
  console.log(text);
}

async function deletePatient(cardNumber) {
  let di = '';
  di = cardNumber;
  console.log(di);
  let responset = await fetch(`http://localhost:1489/api/v3/patient?cardNumber=${di}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(patient),
  });

  if (responset.ok) {
    delnotice();
    setTimeout(fixed, 2000);
    test();
    text = await responset.json();
    console.log(text);
  } else {
    alert("Ошибка HTTP: " + responset.status);
  }
  
}


 async function searhsCard(){
  let text;
   let textS= document.getElementById("patCard").value;
   console.log(textS);
    let responset = await fetch(`http://localhost:1489/api/v3/patient/getByCardNumber?cardNumber=${textS}`);
    wiwod(responset);
    textS.value = "";
  console.log(text);
    }
    async function searhsId(){
      let text;
       let textS= document.getElementById("patId").value;
       console.log(textS);
        let responset = await fetch(`http://localhost:1489/api/v3/patient?id=${textS}`);
        wiwod(responset)
      console.log(text);
        }
    
        async function searhsCause(){
          let text;
           let textS= document.getElementById("patCause").value;
           console.log(textS);
            let responset = await fetch(`http://localhost:1489/api/v3/patient/getListWithCause?cause=${textS}`);
            if (responset.ok) {
              // если HTTP-статус в диапазоне 200-299
              // получаем тело ответа (см. про этот метод ниже)
              text = await responset.json();
          
              const moviesEI = document.querySelector(".patients");
          
              document.querySelector(".patients").innerHTML = "";
              text= text.reverse();
              text.forEach((patientInfo) => {
          
                if(patientInfo.deleted == false ){
                const movieEl = document.createElement("div");
                movieEl.classList.add("patient");
                movieEl.innerHTML = `
                  <div class="Inf">
                    <p class="InfoP">Имя: ${patientInfo.name}, Фамилия: ${patientInfo.surname}, Возраст: ${patientInfo.age}, Номер карты: ${patientInfo.cardNumber}
                     <input type="button" value="Delete" class="btnn" onclick="deletePatient(${patientInfo.cardNumber})">
                     </p>
                  </div>
                   `;
                moviesEI.appendChild(movieEl);
                }
              });
            console.log(text);
              }
            }
   
            async function searhsNS(){
              let text;
               let textN= document.getElementById("nameS").value;
               let textS= document.getElementById("surnameS").value;
               console.log(textS);
                let responset = await fetch(`http://localhost:1489/api/v3/patient/getByNameAndSurname?name=${textN}&surname=${textS}`);
                if (responset.ok) {
                  // если HTTP-статус в диапазоне 200-299
                  // получаем тело ответа (см. про этот метод ниже)
                  text = await responset.json();
              
                  const moviesEI = document.querySelector(".patients");
              
                  document.querySelector(".patients").innerHTML = "";
                  text= text.reverse();
                  text.forEach((patientInfo) => {
              
                    if(patientInfo.deleted == false ){
                    const movieEl = document.createElement("div");
                    movieEl.classList.add("patient");
                    movieEl.innerHTML = `
                      <div class="Inf">
                        <p class="InfoP">Имя: ${patientInfo.name}, Фамилия: ${patientInfo.surname}, Возраст: ${patientInfo.age}, Номер карты: ${patientInfo.cardNumber}
                         <input type="button" value="Delete" class="btnn" onclick="deletePatient(${patientInfo.cardNumber})">
                         </p>
                      </div>
                       `;
                    moviesEI.appendChild(movieEl);
                    }
                  });
                console.log(text);
                  }
                }

 