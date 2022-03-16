//document.cookie = "Name= 값; value = 값; Expires = 값;"
// 날짜 추출 : date.getDate() , date.gethours(),years,등등
// 날짜지정 : date.setDate(새로운 날짜를 지정)
//var date = new Date(); //변수명 date에 지금시간이 저장됨
//
//date.setDate(date.getDate()+7);
//
//
//var setCookie = '';
////setCookie = setCookie + 'CookieName=ABC;';
//setCookie += 'CookieName=ABC;';
//setCookie += 'Expires=' + date.toUTCString();
//
//document.cookie = setCookie; //쿠키 설정,생성
//console.log(document.cookie);
//

//쿠키 생성
var myPopup = document.querySelector('.popup'),
    checkbox = document.querySelector('#popup'),
    popupClose = document.querySelector('.popupclose');


function setCookie(name, value, day){
    var date = new Date();
    date.setDate(date.getDate + day);
    var mycookie ='';
    //setCookie += 'CookieName=ABC;'; // CookieName = name, ABC = value
    mycookie += name + '=' + value+';';
    mycookie += 'Expires=' + date.toUTCString();
    document.cookie = mycookie;
}

//setCookie('ABC.com','Main',3);

//쿠키 삭제 -> 쿠키를 -1일전으로 생성 -> 기한만료로 삭제와 동일
function delCookie(name){
var date = new Date();
date.setDate(date.getDate()-1);

var setCookie = '';
//setCookie = setCookie + 'CookieName=ABC;';
setCookie += name+'=Main;';
setCookie += 'Expires=' + date.toUTCString();

document.cookie = setCookie; //쿠키 설정,생성

}

//쿠키 확인
function checkCookie(name){
    var cookies = document.cookie.split(';');
    console.log(cookies);
    var visited = false; // 방문 거짓


    for( var i in cookies){
        if(cookies[i].indexOf(name) > -1){ //만약 쿠키가 있다면
            visited =true; //방문한 적이 있다
        }
    }

    if(visited){ // visited값이 트루라면
        //재방문 - 팝업안보이게
        myPopup.style.display = 'none';
    }else{
        //신규 방문
        myPopup.style.display = 'block';
    }

}

checkCookie('ABC.com');

popupClose.addEventListener('click',function(){
    //a.checked true false알려줌
    if(checkbox.checked){
        //팝업을 다시 안보겠다. 팝업 닫고, 쿠키 생성
        setCookie('ABC.com','Main',1);
         myPopup.style.display = 'none';
    } else{
        //팝업을 계속 본다. 팝업 닫고, 쿠키 제거
         myPopup.style.display = 'none';
         delCookie('ABC.com');
    }
});