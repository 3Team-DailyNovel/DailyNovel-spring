<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>메인화면</title>
    
    <link rel="stylesheet" type="text/css" href="/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="/css/button.css" />
    <link rel="stylesheet" type="text/css" href="/css/box.css" />
    <link rel="stylesheet" type="text/css" href="/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="/css/util.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <link rel="stylesheet" type="text/css" href="/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/css/component-mobile.css" />

  </head>
  <body">
    <img src="/img/DiaryMainPage/flower_1.jpg" class="main-bg">
    <div class="container-1">
      <!-- 헤더 -->
      <header class="header horizon-center">
        <div class="monthly-gauge mgt-4 horizon-center">
          <h1 class="h5">Food for thought</h1>
          <!-- 문장이 아닐 경우에는 p 금지 -->
          <!-- 제목: h 문장: p 목록 표 폼 -->
          <div class="mgt-2">
            <progress value="60" max="100" class="pg-bar" id="pg-bar"></progress>
          </div>
        </div>
      </header>
      <!-- 메인 -->
      <main class="main main-pg-2Layers mgt-3 pdb-4">
        <div class="welcome horizon-center hi-txt" id="add-hello">
          <div>000님</div>
          <!-- <div class="mgt-2">즐거운 하루였나요?</div> -->
        </div>

        <!-- 달력 -->
        <div class="calender">
          <div class="header" id="calendar-header">
            <div class="prev-month" id="prev-btn">&#60;</div>
            <div class="h3 lc-center" id="date-config"></div>
            <div class="next-month" id="next-btn">&#62;</div>
          </div>

          <div class="main">
            <ul class="day mgt-4">
              <li>SUN</li>
              <li>MON</li>
              <li>TUE</li>
              <li>WED</li>
              <li>THU</li>
              <li>FIR</li>
              <li>SAT</li>
            </ul>

            <ul class="date" id="calendar-date"></ul>
          </div>
        </div>
        <!-- 일기 미리보기
            <article class="diary-preview-box border-r-1 shadow-2">
                <div class="h2">제목</div>
                <div class="h4">내용 어쩌고 한 2줄하고...</div>
                <div>...</div>
            </article> -->
            <article class="preview-slide slide_wrap border-r-2 shadow-1">
                <div class="item lc-center">
                <div class="icon-plus" onClick="location.href='/html/member/diary/register.html'"></div>
                <!-- <div class="ic-add-diary"></div> -->
                </div>
                <div class="item item-diary">
                <div class="h2">2023년 3월 9일</div>
                <div>
                    이거 진짜 3줄 이후로 안나옴?? 이거 진짜 3줄 이후로 안나옴?? 이거 진짜 3줄 이후로 안나옴?? 이거 진짜 3줄
                    이후로 안나옴?? 이거 진짜 3줄 이후로 안나옴?? 이거 진짜 3줄 이후로 안나옴??
                </div>
                </div>
                <div class="item">세번째</div>
                <div class="item">네번째</div>
                <div class="item">다섯번째</div>
                <div class="prev-btn btn">◀</div>
                <div class="next-btn btn">▶</div>
                <ul class="pagination"></ul>
            </article>

        </main>
        <!-- 푸터 -->
        <footer class="footer ">
            <div class="container-nav color-white-1">
                
                <div class="nav-1 lc-center">
                    <a href="../index.html" class="lc-vertical-alignment">
                    <div class="nav-icon bookmark-btn"></div>
                    <div class="h6 mgt-2">모아보기</div>
                </a>
                </div>
                
                <div class="nav-2 lc-center">
                    <a href="../index.html" class="lc-vertical-alignment">
                        <div class=" nav-icon speech-bubble-btn"></div>
                        <div class="h6 mgt-2">커뮤니티</div>
                    </a>
                </div>

                <div class="nav-main lc-center">
                    <a href="../index.html" class="lc-vertical-alignment">
                        <div class="nav-icon book-btn"></div>
                        <div class="h6 mgt-2">메인화면</div>
                    </a>
                </div>
    
                <div class="nav-4 lc-center">
                    <a href="" class="lc-vertical-alignment" >
                    <div class="nav-icon  hourglass-btn"></div>
                    <div class="h6 mgt-2">돌아보기</div>
                </a>
                </div>
                <div class="nav-5 lc-center">
                    <a href="./settings/main.html" class="lc-vertical-alignment">
                    <div class="nav-icon gear-btn"></div>
                    <div class="h6 mgt-2">설정하기</div>
                </a>
                </div>
            </div>

        </footer>
    </div>
    <script src="/js/calendar.js"></script>
    <script src="/js/slider.js"></script>
    <script src="/js/mainLetter.js"></script>
</body>
</html>
