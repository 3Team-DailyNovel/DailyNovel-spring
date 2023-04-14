//일기 검색 창 스크립트 코드입니다.
//dom 조작 js 나중에 다 Vue로 교체해야함
//선유진

//메뉴 하나만 펼칠수 있게 해주는DOM조작

//정렬 기준 버튼 전체 가져오기(가장 큰 틀)
const navMenu = document.querySelector(".option-container");
//실제 메뉴들 DOM
const optionBox = navMenu.children;
const btnTitle = navMenu.querySelectorAll(".title");
const modalBtn = document.querySelector("#openBtn");
//========================================================
//정렬 버튼 이벤트(모달제외 버튼은 여기서 동작 /
//  이전 버튼 최대화 된거 제거는 여기서 함) == 코드 개떡같으니 읽지 않는걸 추천
// 개떡같은 이유 -> HTML 구조가 이상해서 DOM 조작의 통일성을 가지지 못함
// 일반버튼 (option-box -> select 로 내려가서 동작하게 만들었음)
// 모달버튼 (option-box의 하위구조가 아님 html 구조가 저 멀리 떨어져있음) -> 따로 처리해야함
for(let a of optionBox){
 //클릭이벤트
  a.onclick = function() {
    //이전에 눌렀던 메뉴 hidden 으로 숨기기
    if(navMenu.querySelector(".select:not(.hidden)")){
    let prevClickedBtn =  navMenu.querySelector(".select:not(.hidden)")
      prevClickedBtn.classList.add("hidden");
      if(prevClickedBtn.parentElement===a){
        prevClickedBtn.classList.add("hidden");
        return;
      }
      
    }
    if(a===modalBtn){
      //모달 동작
      open();
      return;
    }
    ; //모달창 나타나게 하는건 얘가 담당하지 않음 (하게 만들순 있지만 귀찮음 -> 기존에 동작하는 코드가 있기 때문) 
    //모달창 제외하고 나머지 버튼의 list는 여기서 뜨게 함
    a.querySelector(".select").classList.remove("hidden");
  }




}
// 모달창 JS(모달은 여기서 동작함)
const open = () => {
  document.querySelector(".list-modal").classList.remove("hidden");
  // console.log(document.querySelector(".list-modal"));
}

const close = () => {
  document.querySelector(".list-modal").classList.add("hidden");
}


calModalBg = document.querySelector(".list-modal .bg").onclick=function(){
  modalBtn.textContent = "날짜";
  modalBtn.dataset.regDate = "";
  //  exceQuery();
  renewList();
  close();
}
//모달창 js 끝!
//========================================================

// -> 하나만 펼쳐지게 동작하는거 완료

//========================================================

// 1. option 클릭했을때, title의 data-id 와 value가 변경되도록
const optionList =navMenu.querySelectorAll(".select .option");
for(let op of optionList){
  op.onclick= function() {
    // console.log(op.textContent , op.dataset.id);

    opClickedBtn = op.parentElement.previousElementSibling;
    opClickedBtn.textContent=op.textContent;
    opClickedBtn.dataset.id=op.dataset.id;
    
    // btnTitle
    // modalBtn
    if(opClickedBtn.textContent=="선택안함"){
      opClickedBtn.textContent=op.dataset.default;
    }
    // exceQuery();
    renewList();
    //  console.log(query);
  }
}
// 2. calendar의 list 눌렀을 때, 날짜의 reg-date 와 value가 변경되도록

const calendarDate = document.querySelector("#calendar-date");
const yearMonth =  document.querySelector("#date-config");
calendar.onclick=function(e) {

  //커서 얻고
  let cursor = e.target;
  //누른게 LI가 아니라면 안함!
//  console.log(cursor);
  if(cursor.tagName != 'SPAN')return;
  // console.log("클릭!");
  for(let a of cursor.classList){
    if( a=='inactive') return;
  }
  //모달버튼 
	// console.log(cursor.textContent);
  // modalBtn.textContent = `${yearMonth.dataset.regDate}-${cursor.textContent}`;
  modalBtn.dataset.regDate = `${yearMonth.dataset.regDate}-${String(cursor.textContent).padStart(2,"0")}`
  //  exceQuery();
  renewList();
  close();
};


let exceQuery=function(){

  //각각 버튼의 dataset 에서 data를 받아온다.
  let tid = btnTitle[0].dataset.id;
  let fid = btnTitle[1].dataset.id;
  let wid = btnTitle[2].dataset.id;
  let regDate = modalBtn.dataset.regDate;
  // console.log(modalBtn);
  // console.log(regDate);
  let queryString =""; 
  queryString += tid?`&tid=${tid}`:"";
  queryString += fid?`&fid=${fid}`:"";
  queryString += wid?`&wid=${wid}`:"";
  queryString += regDate?`&reg_date=${regDate}`:"";
  // console.log(queryString);
  // let regDatequery=new URLSearchParams(window.location.search).get('reg_date');
//  queryString += new URLSearchParams(window.location.search).get('reg_date')?`&reg_date=${regDate}`:"";
  // console.log(`wid=${wid} // fid=${fid} // tid=${tid}`);
  // let query = `?wid=${wid?wid:""}&fid=${fid?fid:""}&tid=${tid?tid:""}&reg_date=${regDate?regDate:""}`;
  // console.log(new URLSearchParams(window.location.search).get('reg_date'));
  //======== 실제 url로 이동하는 코드 ========
    // if(queryString != "")
    //   location.href = (`./list?${queryString}`);
    //   else
    //   location.href = (`./list`);
  
};


let renewList=function(){

  //Dom 선언
  const ListDOM = document.querySelector("#diaryList");
  //각각 버튼의 dataset 에서 data를 받아온다.
  let tid = btnTitle[0].dataset.id;
  let fid = btnTitle[1].dataset.id;
  let wid = btnTitle[2].dataset.id;
  let regDate = modalBtn.dataset.regDate;
  let backgroundY= "#c8c000"; //노랑
  let backgroundG = "#69bc80" //초록
  // background-color: #ffff77; // 노랑   color-bg-yellow-1
  // background-color: #9aefb0; // 초록   color-bg-green-1
  let templateStyle = btnTitle[0].style
  let feelingStyle = btnTitle[1].style
  let weatherStyle = btnTitle[2].style
  let dateStyle = modalBtn.style
  console.log()
  tid?templateStyle.backgroundColor=backgroundY:templateStyle.backgroundColor=backgroundG
  fid?feelingStyle.backgroundColor=backgroundY:feelingStyle.backgroundColor=backgroundG
  wid?weatherStyle.backgroundColor=backgroundY:weatherStyle.backgroundColor=backgroundG
  regDate?dateStyle.backgroundColor=backgroundY:dateStyle.backgroundColor=backgroundG
  //GET 방식 AJAX이기 때문에 쿼리스트링 제작
  let queryString =""; 
  queryString += tid?`&tid=${tid}`:"";
  queryString += fid?`&fid=${fid}`:"";
  queryString += wid?`&wid=${wid}`:"";
  queryString += regDate?`&regDate=${regDate}`:"";
  // console.log(queryString);
  //Ajax를 활용하여 데이터 받아오기
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };
  // /diarys/getList?tid=1&wid=1&fid=1&regDate=2023-04-11

  let UserDiaryList;
  let diaryindex

  fetch(`/diarys/getList?${queryString}`, requestOptions)
    .then(response => response.json())
    .then(result => {
      UserDiaryList=result
      diaryindex = UserDiaryList.length;
      // console.log(UserDiaryList);
        // console.log("있는겨");
      //제대로 값을 받아왔다면 화면에 뿌리기(list 개수만큼)
      let htmlString = "";
    if(diaryindex != 0){
      // console.log("일기있음");
      for(let diary of UserDiaryList){
        htmlString+=`
        <li class="list-box" data-id = ${diary.id}>
        <div class="list-date lc-center h2 font-weight-bolder mgt-2">${formatDate(new Date(diary.regDate))}</div>
        <div class="list-title mgt-3">${diary.title}</div>
        <div class="content mgt-2">${diary.content}</div>
        </li>
        `
      }
    }
    else{
      //일기가 있을 때
      // console.log("일기없음");
        htmlString =`	        	
          <li class="list-box">
            <div class="list-date lc-center h2 font-weight-bolder mgt-2">${modalBtn.dataset.regDate}</div>
            <div class="content mgt-5 lc-center h1" > 일기가 없어요!</div>
          </li>`
    }   
      ListDOM.innerHTML = htmlString;  
      
        //다이어리에 클릭 이벤트 붙이기
      const Dlist = ListDOM.querySelectorAll("li")
      if(diaryindex){
        for(let diary of Dlist)
          diary.onclick= () =>{window.location.href=`/member/diary/detail?diaryId=${diary.dataset.id}`}
      }
    })
    .catch(error => {console.log(error);
    });

}

//날짜 포맷팅
function formatDate(date) {
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();

  // 필요한 경우, 2자리 숫자로 맞춰주는 코드
  month = month < 10 ? '0' + month : month;
  day = day < 10 ? '0' + day : day;
  // 출력할 형식 지정
  var formattedDate = year + '-' + month + '-' + day;
  
  return formattedDate;
}