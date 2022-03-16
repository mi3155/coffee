new Swiper( '.swiper-container', {

	effect : 'fade', // 페이드 효과 사용

	loop : true, // 무한 반복
	pagination : { // 페이징 설정
		el : '.swiper-pagination',
		clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
	},

    autoplay: {
        delay : 4000,
        disableOnInteraction : false,
    }
});




//단품, 세트메뉴 li
const coffee = document.querySelector("#coffee");
const drink = document.querySelector("#drink");
const icecream = document.querySelector("#icecream")

//단품일때, 세트일때 ul를 none or 보이게
const coffeeMenu = document.querySelector("#coffee_menu");
const drinkMenu = document.querySelector("#drink_menu");
const icecreamMenu = document.querySelector("#icecream_menu")

//menuList li를 보이게끔, 그리고 버튼클릭시 none된 클래스들이 보이게
let menuList = document.querySelectorAll(".mainbody_ul li");
let menuList2 = document.querySelectorAll(".mainbody_ul2 li");
let menuList3 = document.querySelectorAll(".mainbody_ul3 li");

function coffeeClick(e){
   e.preventDefault();

   for(i=0; i<menuList.length; i++){
    menuList[i].className = "click";
}

coffee.className= "Orange";
drink.className= " ";
icecream.className= " ";

   coffeeMenu.className = "mcMenu";
   drinkMenu.className = "none";
   icecreamMenu.className = "none";

}


function drinkClick(e){
    e.preventDefault();

    for(i=0; i<menuList2.length; i++){
     menuList2[i].className = "click";
    }
	coffee.className= " ";
	drink.className= "Orange";
	icecream.className= " ";

	coffeeMenu.className = "none";
	drinkMenu.className = "mcMenu";
	icecreamMenu.className = "none";
}

function icecreamClick(e){
    e.preventDefault();

    for(i=0; i<menuList3.length; i++){
     menuList3[i].className = "click";
    }

	coffee.className= " ";
	drink.className= " ";
	icecream.className= "Orange";

	coffeeMenu.className = "none";
	drinkMenu.className = "none";
	icecreamMenu.className = "mcMenu";

}
coffee.addEventListener('click', coffeeClick);
drink.addEventListener('click', drinkClick);
icecream.addEventListener('click', icecreamClick);





