test();
let visit = new Object();
visit.cause = causeEl.value;
visit.solution = solutionEl.value;
visit.registrationId = registrationIdEl.value;
visit.cardNumber = cardNumberEl.value;
visit.date = dateEl.value;
visit.doctor = doctorEl.value;
async function visitinfo() {
  const causeEl = document.getElementById("cause");
  const registrationIdEl = document.getElementById("registrationId");
  const solutionEl = document.getElementById("solution");

  visit.cause = causeEl.value;
  visit.registrationId = registrationIdEl.value;
  visit.solution = solutionEl.value;
 
  if (
    causeEl.value == "" ||
    registrationIdEl.value == "" ||
    solutionEl.value == ""
   
  ) {
    alert("Заполните все поля");
  } else {
    let response = await fetch("http://localhost:1489/api/v3/visit/newVisit", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=utf-8",
      },
      body: JSON.stringify(visit),
    });

    causeEl.value = "";
    registrationIdEl.value = "";
    solutionEl.value = "";
   
   
  }
  notice();
  setTimeout(fixed, 2000);
 test();
  console.log(visit);
}

async function reginfo() {
    const cardNumberEl = document.getElementById("cardNumber");
    const dateEl = document.getElementById("date");
    const doctorEl = document.getElementById("doctor");
   
  
    visit.cardNumber = cardNumberEl.value;
    visit.date = dateEl.value;
    visit.doctor = doctorEl.value;
   
    if (
        cardNumberEl.value == "" ||
        doctorEl.value == ""
     
    ) {
      alert("Заполните все поля");
    } else {

      let response = await fetch("http://localhost:1489/api/v3/visit/new", {
        
      method: "POST",
        headers: {
          "Content-Type": "application/json;charset=utf-8",
        },
        body: JSON.stringify(visit),
      });
      if (response.ok) {
      cardNumberEl.value = "";
      dateEl.value = "";
      doctorEl.value = "";
     
     
    }
    else {
      aler();
      setTimeout(fixed, 5000);
     test();
    }
    notice();
    setTimeout(fixed, 2000);
   test();
    console.log(visit);
  }
  }

async function notice() {
  document.querySelector('.note').innerHTML = `<div class="notess">New post was added</div>`;
}

async function fixed() {
  document.querySelector('.note').innerHTML = (``);
}
async function aler() {
  document.querySelector('.note').innerHTML = `<div class="aler">Некорректные данные! Перепроверьте работает ли в этот день врач !</div>`;
}

async function wiwod(responset){

  if (responset.ok) {
    // если HTTP-статус в диапазоне 200-299
    // получаем тело ответа (см. про этот метод ниже)
    text = await responset.json();
    console.log(text);
    const moviesEI = document.querySelector(".visits");

    document.querySelector(".visits").innerHTML = "";
    text= text.reverse();
    text.forEach((visitInfo) => {

      
      const movieEl = document.createElement("div");
      movieEl.classList.add("visit");
      if (visitInfo.cause === null) {
        movieEl.innerHTML = `
        <div class="Inf">
          <p class="InfoP">Id регистрации: ${visitInfo.id}, Номер карты: ${visitInfo.patientCard}, 
          Дата: ${visitInfo.date}, Id доктора: ${visitInfo.doctorId}
           </p>
        </div>
         `;
      }
      else{
      movieEl.innerHTML = `
        <div class="Inf">
          <p class="InfoP">Id регистрации: ${visitInfo.id}, Номер карты: ${visitInfo.patientCard}, 
          Дата: ${visitInfo.date}, Id доктора: ${visitInfo.doctorId},
          Причина визита: ${visitInfo.cause}, Заключение доктора: ${visitInfo.solution}
           </p>
        </div>
         `;
      }
      moviesEI.appendChild(movieEl);
      
    });
  } else {
    alert("Ошибка HTTP: " + responset.status);
  }

}

async function test() {
  let text;
  let responset = await fetch("http://localhost:1489/api/v3/visit/list");

  wiwod(responset)
  console.log(text);
}

async function searhsId(){
  let text;
   const textS= document.getElementById("vsId").value;
   console.log(textS);
    let responset = await fetch(`http://localhost:1489/api/v3/visit?id=${textS}`);
    if (responset.ok) {

      text = await responset.json();
  
      const moviesEI = document.querySelector(".visits");
  
      document.querySelector(".visits").innerHTML = "";
      console.log(text);
     
        const movieEl = document.createElement("div");
        movieEl.classList.add("visit");
        if (text.cause === null) {
          movieEl.innerHTML = `
          <div class="Inf">
            <p class="InfoP">Id регистрации: ${text.id}, Номер карты: ${text.patientCard}, 
            Дата: ${text.date}, Id доктора: ${text.doctorId}
             </p>
          </div>
           `;
        }
        else{
        movieEl.innerHTML = `
          <div class="Inf">
            <p class="InfoP">Id регистрации: ${text.id}, Номер карты: ${text.patientCard}, 
            Дата: ${text.date}, Id доктора: ${text.doctorId},
            Причина визита: ${text.cause}, Заключение доктора: ${text.solution}
             </p>
          </div>
           `;
        }
        moviesEI.appendChild(movieEl);
        textS.value= "";
    } else {
      alert("Ошибка HTTP: " + responset.status + ", запись с таким id не найдена");
    }
  console.log(text);
    }