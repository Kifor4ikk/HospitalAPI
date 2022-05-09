test();
let doctor = {
name: "",
surname: "",
age: "",
quality: "",
workDays: [],
};

async function myinfo() {
  const nameEl = document.getElementById("name");
  const surnameEl = document.getElementById("surname");
  const ageEl = document.getElementById("age");
  const qualityEl = document.getElementById("quality");
  const workDaysEls = document.getElementsByClassName('check');

  doctor.name = nameEl.value;
  doctor.surname = surnameEl.value;
  doctor.age = ageEl.value;
  doctor.quality = qualityEl.value;
  doctor.deleted = false;

  var length = workDaysEls.length;
  var workDays = [];
  for (var i = 0; i < length; i++) {
    if (workDaysEls[i].checked) {
        var el = workDaysEls[i].nextSibling.nodeValue.substr(0, workDaysEls[i].nextSibling.nodeValue.indexOf('\n'));
        workDays.push(el);
        
    }
  }
  doctor.workDays = workDays;
  console.log(workDays);

  if (
    nameEl.value == "" ||
    surnameEl.value == "" ||
    ageEl.value == "" ||
    qualityEl.value == "" ||
    workDays.length == 0
  ) {
    alert("Заполните все поля");
    for (var i = 0; i < length; i++) {
        if (workDaysEls[i].checked) {
            workDaysEls[i].checked = false;
        }
      }
  } else {
    let response = await fetch("http://localhost:1489/api/v3/doctor/new", {
      method: "POST",
      headers: {
        "Content-Type": "application/json;charset=utf-8",
      },
      body: JSON.stringify(doctor),
    });

    nameEl.value = "";
    surnameEl.value = "";
    ageEl.value = "";
    qualityEl.value = "";
    for (var i = 0; i < length; i++) {
        if (workDaysEls[i].checked) {
            workDaysEls[i].checked = false;
        }
      }
      notice();
      setTimeout(fixed, 2000);
    test();
  }
  console.log(doctor);
}

async function test() {
  let text;
  let responset = await fetch("http://localhost:1489/api/v3/doctor/list");

  if (responset.ok) {
   
    // если HTTP-статус в диапазоне 200-299
    // получаем тело ответа (см. про этот метод ниже)
    text = await responset.json();

    const moviesEI = document.querySelector(".doctors");
    console.log(text);
    document.querySelector(".doctors").innerHTML = "";
    text= text.reverse();
    text.forEach((doctorInfo) => {

      if(doctorInfo.deleted == false ){
      const movieEl = document.createElement("div");
      movieEl.classList.add("doctor");
      movieEl.innerHTML = `
          <p class="InfoP">Id:${doctorInfo.id}, Имя: ${doctorInfo.name}, Фамилия: ${doctorInfo.surname}, Возраст: ${doctorInfo.age}, 
         Квалификация: ${doctorInfo.quality}, Рабочие дни: ${doctorInfo.workDays}
           <input type="button" value="delete" class="btnn" onclick="deleteDoctor(${doctorInfo.id})"></p>
 
         `;
      moviesEI.appendChild(movieEl);
      }
    });
  } else {
    alert("Ошибка HTTP: " + responset.status);
  }
  
}

async function quality (){
  let text;
  let responset = await fetch("http://localhost:1489/api/v3/doctor/quality");

  if (responset.ok) {
   
    // если HTTP-статус в диапазоне 200-299
    // получаем тело ответа (см. про этот метод ниже)
    text = await responset.json();
    console.log(text);
    const moviesEI = document.querySelector(".doctors");

    document.querySelector(".doctors").innerHTML = "";
    
      const movieEl = document.createElement("div");
      movieEl.classList.add("doctor");
      movieEl.innerHTML = `
          <p class="InfoP"> 
         Квалификации: ${ text }</p>
         `;
      moviesEI.appendChild(movieEl);
     
  } else {
    alert("Ошибка HTTP: " + responset.status);
  }
  
}
async function deleteDoctor(id) {
  let responset = await fetch(`http://localhost:1489/api/v3/doctor?id=${id}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
    body: JSON.stringify(doctor),
  });

  if (responset.ok) {
    alert("was deleted");
    test();
    text = await responset.json();
    console.log(text);
  } else {
    alert("Ошибка HTTP: " + responset.status);
  }
  
}

async function searhsId(){
  let text;
   let textS= document.getElementById("docId").value;
   console.log(textS);
    let responset = await fetch(`http://localhost:1489/api/v3/doctor?id=${textS}`);
    if (responset.ok) {
      text = await responset.json();
    
        const moviesEI = document.querySelector(".doctors");
    
        document.querySelector(".doctors").innerHTML = "";
        console.log(text);
       
          const movieEl = document.createElement("div");
          movieEl.classList.add("doctor");
          movieEl.innerHTML = `
            <div class="Info">
            <p class="InfoP">Id:${doctorInfo.id}, Имя: ${text.name}, Фамилия: ${text.surname}, Возраст: ${text.age}, Квалификация: ${text.quality}, Рабочие дни: ${text.workDays}
           <input type="button" value="delete" onclick="deleteDoctor(${text.id})"></p>
            </div>
             `;
          moviesEI.appendChild(movieEl);
         
  } else {
    alert("Ошибка HTTP: " + responset.status + ", доктор с такими данными не найден");
  }
  console.log(text);
    }
    async function searhsQuality(){
      let text;
       let textS= document.getElementById("docQuality").value;
       console.log(textS);
        let responset = await fetch(`http://localhost:1489/api/v3/doctor/allQualityDoctors?quality=${textS}`);
        if (responset.ok) {
          text = await responset.json();

          const moviesEI = document.querySelector(".doctors");
      
          document.querySelector(".doctors").innerHTML = "";
          text= text.reverse();
          text.forEach((doctorInfo) => {
      
            if(doctorInfo.deleted == false ){
            const movieEl = document.createElement("div");
            movieEl.classList.add("doctor");
            movieEl.innerHTML = `
                <p class="InfoP">Id:${doctorInfo.id}, Имя: ${doctorInfo.name}, Фамилия: ${doctorInfo.surname}, Возраст: ${doctorInfo.age}, 
               Квалификация: ${doctorInfo.quality}, Рабочие дни: ${doctorInfo.workDays}
                 <input type="button" value="delete" class="btnn" onclick="deleteDoctor(${doctorInfo.id})"></p>
       
               `;
            moviesEI.appendChild(movieEl);
            }
          });
      } else {
        alert("Ошибка HTTP: " + responset.status  + ", доктор с такими данными не найден");
      }
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

        async function update() {
          const nameEl = document.getElementById("name");
          const surnameEl = document.getElementById("surname");
          const ageEl = document.getElementById("age");
          const qualityEl = document.getElementById("quality");
          const workDaysEls = document.getElementsByClassName('check');
          let textS= document.getElementById("idU").value;
          doctor.name = nameEl.value;
          doctor.surname = surnameEl.value;
          doctor.age = ageEl.value;
          doctor.quality = qualityEl.value;
          doctor.deleted = false;
        
          var length = workDaysEls.length;
          var workDays = [];
          for (var i = 0; i < length; i++) {
            if (workDaysEls[i].checked) {
                var el = workDaysEls[i].nextSibling.nodeValue.substr(0, workDaysEls[i].nextSibling.nodeValue.indexOf('\n'));
                workDays.push(el);
                
            }
          }
          doctor.workDays = workDays;
          console.log(workDays);
          noticeUpd();
          fixed();
          if (
            nameEl.value == "" ||
            surnameEl.value == "" ||
            ageEl.value == "" ||
            qualityEl.value == "" ||
            workDays.length == 0
          ) {
            alert("Заполните все поля");
            for (var i = 0; i < length; i++) {
                if (workDaysEls[i].checked) {
                    workDaysEls[i].checked = false;
                }
              }
          } else {
            let response = await fetch(`http://localhost:1489/api/v3/doctor?id=${textS}`, {
              method: "PUT",
              headers: {
                "Content-Type": "application/json;charset=utf-8",
              },
              body: JSON.stringify(doctor),
            });
        
            nameEl.value = "";
            surnameEl.value = "";
            ageEl.value = "";
            qualityEl.value = "";
            for (var i = 0; i < length; i++) {
                if (workDaysEls[i].checked) {
                    workDaysEls[i].checked = false;
                }
              }
            test();
          }
          console.log(doctor);
        }

        async function searhsNS(){
          let text;
           let textN= document.getElementById("nameS").value;
           let textS= document.getElementById("surnameS").value;
           console.log(textS);
            let responset = await fetch(`http://localhost:1489/api/v3/doctor/nameAndSurname?name=${textN}&surname=${textS}`);
            if (responset.ok) {
              text = await responset.json();
    
              const moviesEI = document.querySelector(".doctors");
          
              document.querySelector(".doctors").innerHTML = "";
              text= text.reverse();
              text.forEach((doctorInfo) => {
          
                if(doctorInfo.deleted == false ){
                const movieEl = document.createElement("div");
                movieEl.classList.add("doctor");
                movieEl.innerHTML = `
                    <p class="InfoP">Id:${doctorInfo.id}, Имя: ${doctorInfo.name}, Фамилия: ${doctorInfo.surname}, Возраст: ${doctorInfo.age}, 
                   Квалификация: ${doctorInfo.quality}, Рабочие дни: ${doctorInfo.workDays}
                     <input type="button" value="delete" class="btnn" onclick="deleteDoctor(${doctorInfo.id})"></p>
           
                   `;
                moviesEI.appendChild(movieEl);
                }
              });
          } else {
            alert("Ошибка HTTP: " + responset.status  + ", доктор с такими данными не найден");
          }
            }