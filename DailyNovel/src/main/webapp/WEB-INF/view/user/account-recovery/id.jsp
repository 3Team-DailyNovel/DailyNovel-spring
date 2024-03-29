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
    <title>계정찾기</title>
</head>
<body>
    <div class="container-1" >
        <header class="header">
            <div class="main-logo mgt-5"><p>DailyNovel</p></div>
        </header >
        <main class="main">
            <form class="lc-vertical-alignment" action="history.go(0)" method="post">
                <input  class="box-large input-boder-none mgt-5" type="text"  placeholder="전화번호를 입력하세요"/>
                <input type="submit" class="box-large  mgt-5 input-boder-none color-bg-green-2 color-white-2 " value="인증번호 발송"/>
            </form>
            <form class="lc-vertical-alignment" action="history.go(0)" method="post">
                <input  class="box-large  input-boder-none mgt-6 " type="text"  placeholder="발급 받은 인증번호를 입력하세요"/>
                <input type="submit" class="btn2  mgt-5 input-boder-none color-bg-green-2 color-white-2 " value="인증번호 확인"/>
            </form>
        </main>
    </div>
</body>
</html>