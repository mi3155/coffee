//최소화될때 햄버거클릭시

const toggleBtn = document.querySelector('.navbar_togleBtn');
const menu = document.querySelector('.navibar_menu');
const icons = document.querySelector('.navibar_icons');

toggleBtn.addEventListener('click',() =>{

    menu.classList.toggle('active');
    icons.classList.toggle('active');

});


//로그인 클릭시

const loginevent = document.querySelector(".login");
const loginform = document.querySelector(".login_form");
const air = document.querySelector(".air");
function login(){
    loginform.className= "login_form";
    air.className = "air";
}

function winclose(){
    loginform.className = "login_form none";
    air.className = "air none";
}



loginevent.addEventListener('click', login);