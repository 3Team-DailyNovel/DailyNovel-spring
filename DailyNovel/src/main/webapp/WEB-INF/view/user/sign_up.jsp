<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Niconne&display=swap" rel="stylesheet">
    <!-- style -->
    <link rel="stylesheet" type="text/css" href="/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="/css/button.css" />
    <link rel="stylesheet" type="text/css" href="/css/box.css" />
    <link rel="stylesheet" type="text/css" href="/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="/css/util.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <link rel="stylesheet" type="text/css" href="/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/css/component-mobile.css" />

    <title>회원가입</title>

</head>
    <body>
        <div class="container-1">
            <header class="header">
                <div class="main-logo mgt-5"><p>DailyNovel</p></div>
            </header >
            <main class="main">
                <h1 class="d-none">회원가입</h1>
                <div class="container-form">
                    <form class="form-signup" id="sign-up-form">

                        <div>
                            <label for="email" class="mgt-5 ib label-txt">이메일</label>
                            <input class="input-1 box-large  mgt-1" type="text" placeholder="이메일을 입력해주세요." id="email">
                            <div class="mgt-2">알맞은 이메일 형식입니다.</div>
                            <div class="certify-form mgt-3">
                                <div class="user-data"><input class="input" type="text" placeholder="인증번호" id ="email-check"></div>
                                <a class="link lc-center " href="">인증번호 전송</a>
                            </div>
                            
                        </div>  
                        <div>
                            <label for="pwd" class="mgt-5 ib label-txt">비밀번호</label>
                            <input class="input-1 box-large  mgt-1" type="text" placeholder="비밀번호를 입력해주세요." id="pwd">
                        </div>
                        <div class="mgt-2">알맞은 이메일 형식입니다.</div>

                        <div>
                            <label for="pwd-check" class="mgt-3 ib label-txt">비밀번호 확인</label>
                            <input class="input-1 box-large  mgt-1" type="text" placeholder="비밀번호 확인" id="pwd-check">
                        </div>
                        <div class="mgt-2">알맞은 이메일 형식입니다.</div>

                        <div>
                            <label for="nic-name" class="mgt-5 ib label-txt">별명</label>
                            <input class="input-1 box-large  mgt-1" type="text" placeholder="별명(중복 불가)" id="nic-name">
                        </div>
                        <div class="mgt-2">알맞은 이메일 형식입니다.</div>

                        <div class="mgt-3">
                            <label for="" class="mgt-5">전화번호</label>
                            <input class="input-1 box-large  mgt-1" type="text" placeholder="전화번호를 입력해주세요." id="phone-num">
                            

                            <div class="certify-form mgt-3">
                                <div class="user-data"><input class="input" type="text" placeholder="인증번호" id="phone-check"></div>
                                <a class="link lc-center " href="">인증번호 전송</a>
                            </div>
                        </div>
                        
                        <input class="btn2 input-btn color-bg-green-2 color-white-2 shadow-2 mgt-5" type="submit" value="가입하기">
                    </form>
                </div>
            </main>
            <!-- <footer class="footer"></footer> -->
        </div>
        <script src="/js/valCheckSignup.js"></script>
    </body>
</html>