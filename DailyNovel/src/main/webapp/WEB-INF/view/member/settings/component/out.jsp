<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원탈퇴/로그아웃</title>
    <link rel="stylesheet" href="../../../../res/css/reset.css">
    <link rel="stylesheet" href="../../../../res/css/layout.css">
    <link rel="stylesheet" href="../../../../res/css/navigation.css">
    <link rel="stylesheet" href="../../../../res/css/box.css">
    <link rel="stylesheet" href="../../../../res/css/button.css">
    <link rel="stylesheet" href="../../../../res/css/style.css">
    <link rel="stylesheet" href="../../../../res/css/icon.css">
    <link rel="stylesheet" href="../../../../res/css/component-mobile.css">
    <link rel="stylesheet" href="../../../../res/css/util.css">
    <!-- js -->
    <script src="../../../../res/js/setting.js"></script>
</head>
<body>
    <section class="container-1">
        <header class="header lc-center top-sticky">
            <div class="box-large color-bg-green-2 h1 mgt-3">회원탈퇴/로그아웃</div>
        </header>

        <div class="main lc-center flex-column">
            <a href="#" class="modalBtn"><div class="btn3 mgt-5 out">회원탈퇴</div></a>
            <div class="btn3 mgt-5">로그아웃</div>
            <div id="modal" class="modal">
                <div class="modal-content ">
                <span class="close">&times;</span>
                <p>탈퇴 하시나요?</p>
                <div style="display: flex;">
                    <button id="submitBtn" class="box-short font-s-box out">회원탈퇴</button>
                    <button id="cancelBtn" class="box-short font-s-box">취소</button>
                </div>
                </div>
            </div>
        </div>


        <div class="footer mgt-5 bottom-sticky">
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
                    <a href="../../main.html" class="lc-vertical-alignment">
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
                    <a href="../main.html" class="lc-vertical-alignment">
                        <div class="nav-icon gear-btn"></div>
                        <div class="h6 mgt-2">설정하기</div>
                    </a>
                </div>
            </div>
        </div>
        



    </section>

</body>
</html>