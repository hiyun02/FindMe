<h1>2023 K-Paas Tech 부분 대상 작품</h1>

<br>
<h2>프로젝트 구성도</h2>
<img width="80%" src="https://github.com/wonjibkim/Exception/assets/101384372/30e3ca1c-e4cf-4584-a7e0-2dd065bec984"/>
<br>
● 플랫폼: 모든 서비스는 K-PaaS 상에서 운영되어 높은 가용성과 확장성 제공  <br>
● ORM과 Spring JPA 사용: 객체 관계 매핑(ORM)을 통해 데이터베이스와 객체 지향 프로그래밍 언어 간의 호환성 개선. Spring JPA를 사용하여 데이터 액세스 계층을 쉽고 효율적으로 관리
 <br>
● Reactive Database 접근: 제안하는 서비스는 비동기적 데이터 처리를 지원하는 Reactive DB를 손쉽게 통합할 수 있도록 설계
 <br>
● 다양한 서비스 구성: 총 11개의 서비스로 구성되어 있으며, 6개의 주요 서비스 (Face_Service, Api_Service, Alarm_Service, Chat_Service, Notice_Service, User_Service), 1개의 Page_Service, 통신을 위한 2개의 서비스(API Gateway, Eureka Server), 환경설정 관리를 위한 Spring Config Server, 서비스 관리를 위한 App Manager가 포함  
 <br>
● 독립적 처리와 저장: 각 서비스는 독립적으로 처리하고 저장  
 <br>
● 단순화된 데이터베이스 관계: 테이블간 관계는 최대한 단순하게 구성하여 데이터베이스 의존도 낮춤  
 <br>
● 서비스별 독립 데이터베이스: 각 서비스별 독립적인 데이터베이스를 생성하여 서비스와 1:1 매칭  
 <br>
● Spring Boot와 Embedded WAS: 마이크로 서비스는 Spring Boot와 내장된 웹 애플리케이션 서버(Embedded WAS)를 사용하여 별도의 설정 없이 바로 실행 가능  
 <br>
● 데이터베이스 활용: 얼굴 분석 정보와 실종자 및 회원 정보는 MySQL 데이터베이스에 저장 및 관리되며, 채팅 기능을 위해서는 RedisDB 사용 


<br>
<br>
<h2>시연 동영상 유튜브 링크 </h2>
https://www.youtube.com/watch?v=K1rNKIbVjp4



<html xmlns="http://www.w3.org/TR/REC-html40" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word"><head><!--[if !mso]>
<style>
v\:* {behavior:url(#default#vml);}
o\:* {behavior:url(#default#vml);}
w\:* {behavior:url(#default#vml);}
.shape {behavior:url(#default#vml);}
</style>
<![endif]
--><title></title><style type="text/css"><!--p.0
{mso-style-name:"바탕글";line-height:160%;margin-left:0pt;margin-right:0pt;text-indent:0pt;margin-top:0pt;margin-bottom:0pt;text-align:justify;word-break:break-hangul;layout-grid-mode:both;vertical-align:baseline;mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;mso-ascii-font-family:함초롬바탕;mso-ascii-font-family:함초롬바탕;mso-font-width:100%;letter-spacing:0pt;mso-text-raise:0pt;font-size:10.0pt;color:#000000;mso-font-kerning:0pt;}
@list l1
{mso-list-type:hybrid;
mso-style-name:"@list l1";}
@list l0:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l0:level1";}
@list l2
{mso-list-type:hybrid;
mso-style-name:"@list l2";}
@list l2:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l2:level1";}
@list l3
{mso-list-type:hybrid;
mso-style-name:"@list l3";}
@list l3:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l3:level1";}
@list l4
{mso-list-type:hybrid;
mso-style-name:"@list l4";}
@list l4:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l4:level1";}
@list l5
{mso-list-type:hybrid;
mso-style-name:"@list l5";}
@list l5:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l5:level1";}
@list l6
{mso-list-type:hybrid;
mso-style-name:"@list l6";}
@list l6:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l6:level1";}
@list l7
{mso-list-type:hybrid;
mso-style-name:"@list l7";}
@list l7:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l7:level1";}
@list l8
{mso-list-type:hybrid;
mso-style-name:"@list l8";}
@list l8:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l8:level1";}
@list l9
{mso-list-type:hybrid;
mso-style-name:"@list l9";}
@list l9:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l9:level1";}
@list l10
{mso-list-type:hybrid;
mso-style-name:"@list l10";}
@list l10:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l10:level1";}
@list l11
{mso-list-type:hybrid;
mso-style-name:"@list l11";}
@list l11:level1
{mso-level-number-format:bullet; 
mso-level-text:;
mso-list-type:hybrid;
margin-left:0pt;
mso-level-number-position:left;
text-indent:0pt;
font-family:Wingdings;
mso-level-suffix:space;
mso-style-name:"@list l11:level1";}
--></style></head><body><!--StartFragment--><p class="0" style="mso-pagination:none;text-autospace:none;mso-padding-alt:0pt 0pt 0pt 0pt;">
구분 | 상세 내용
-- | --
웹 배포 서버 | K-PaaS
개발 언어 | Java, Python, JavaScript, HTML5
개발 툴 | IntelliJ, PyCharm, Visual Studio Code
주요 개발 프레임워크 | MSA에 적합한 개발 및 운영 환경을 제공하는 Spring Boot Framework 적용ORM 구현을 위한 Spring Data JPAAPI Gateway 구현을 위한 Spring Cloud Gateway 적용API Gateway의 접근제어 구현을 위한 Spring Cloud Security 적용API Gateway 라우팅 기능 구현을 위한 Spring Cloud WebFlux 적용사용자 접근 권한 제어를 위한 Spring Security 적용서비스 디스커버리 및 로드밸런싱을 위해 Eureka Server, Eureka Client 적용AppManger에 각 서버의 Spring Actuator를 통해 Graceful Shutdown 적용 LLM 기반의 실종자 데이터 분석 결과 제공을 위한 LangChain 적용파이썬 기반의 LangChain 서비스 제공을 위한 Flask 적용
데이터베이스 | 서비스별 독립적인 데이터베이스를 생성하여 서비스와 1:1 매칭얼굴 분석 정보 저장 및 관리 위한 MySQL 사용실종자 및 회원 정보 저장 및 관리를 위한 MySQL 사용채팅을 위한 RedisDB 사용PWA(Progressive Web Apps)을 위한 Manifest와 ServiceWork 사용벡터 형식의 실종자 데이터 저장을 위한 ChromaDB
API | 얼굴 이미지 분석 정보 저장 및 나이 예측, 유사도 정보 분석 기능 제공을 위한 SK NUGU facecan Open APIObject Storage 사용을 위한 S3 Bucket API경찰청 제공 실시간 실종자 APIPush 알림을 위한 flarelane API실종자 데이터 임베딩 및 실종 데이터 분석 질의응답을 위한 OpenAI API
라이브러리 | Spring Open Feign Client, JSON Web Token, Thymeleaf, JQuery, Lombok,Websoket,OpenAIEmbeddings, RecursiveCharacterTextSplitter, RetrievalQA
형상관리 도구 | Git

</p><div id='hwpEditorBoardContent' class='hwp_editor_board_content' data-hjsonver='1.0' data-jsonlen='27314'><!--[data-hwpjson]{
"documentPr": {
"di": "",
"dp": {
"dn": "test.hwp",
"ta": 1,
"d1": 5,
"d2": 1,
"dv": 0,
"dr": 1,
"do": 1,
"vj": "1.0",
"an": "Hancom Office Hangul",
"av": "9, 6, 1, 10097",
"ao": "WIN",
"ab": "32",
"ar": "LE",
"as": "Windows_Unknown_Version"
},
"dis": false,
"ds": {
"ti": "",
"la": "ko",
"cr": "",
"su": "",
"de": "",
"cd": "2024-01-03T19:08:01.252Z",
"md": "1601-01-01T09:00:00.000Z",
"pd": "1601-01-01T09:00:00.000Z",
"ke": ""
}
},
"dh": {
"do": {
"pa": 1,
"fo": 1,
"en": 1,
"pi": 1,
"tb": 1,
"eq": 1
},
"fo": [ ],
"cd": {
"tp": 0,
"lc": {
"af": false,
"ui": false,
"fu": false,
"dn": false,
"ul": false,
"el": false,
"at": false,
"tq": false,
"da": false,
"dw": false,
"dj": false,
"bc": false,
"bu": false,
"al": false,
"ab": false,
"ap": false,
"an": false,
"aa": false,
"ds": false,
"de": false,
"as": false,
"cp": false,
"ao": false,
"et": false,
"ay": false,
"am": false,
"a1": false,
"bt": false,
"av": false,
"dh": false,
"dp": false,
"d1": false,
"mf": false,
"bl": false,
"ag": false,
"dg": false,
"ae": false,
"df": false,
"do": false,
"dl": false,
"di": false,
"d2": false,
"d3": false,
"ob": false,
"d4": false,
"ev": false,
"d5": false,
"d6": false,
"a2": false,
"dc": false
}
},
"ld": {
"pa": "",
"pi": true,
"fo": false
}
},
"bf": {
"01DA3E2CBC0B9610000001A4": {
"id": 1,
"td": false,
"sh": false,
"st": 0,
"sc": 0,
"si": false,
"bt": 0,
"bi": false,
"cl": 0,
"bc": false,
"lt": 0,
"lw": 0,
"lc": 0,
"rt": 0,
"rw": 0,
"rc": 0,
"tt": 0,
"tw": 0,
"tc": 0,
"bbt": 0,
"bbw": 0,
"bbc": 0,
"dt": 1,
"dw": 0,
"dc": 0,
"fi": { }
},
"01DA3E2CBC0B9610000001A5": {
"id": 2,
"td": false,
"sh": false,
"st": 0,
"sc": 0,
"si": false,
"bt": 0,
"bi": false,
"cl": 0,
"bc": false,
"lt": 0,
"lw": 0,
"lc": 0,
"rt": 0,
"rw": 0,
"rc": 0,
"tt": 0,
"tw": 0,
"tc": 0,
"bbt": 0,
"bbw": 0,
"bbc": 0,
"dt": 1,
"dw": 0,
"dc": 0,
"fi": {
"wb": {
"fc": 4294967295,
"hc": 4278190080,
"al": 0,
"hs": -1
}
}
},
"01DA3E2CBC0B9610000001A6": {
"id": 3,
"td": false,
"sh": false,
"st": 0,
"sc": 0,
"si": false,
"bt": 0,
"bi": false,
"cl": 0,
"bc": false,
"lt": 1,
"lw": 1,
"lc": 0,
"rt": 1,
"rw": 1,
"rc": 0,
"tt": 1,
"tw": 1,
"tc": 0,
"bbt": 1,
"bbw": 1,
"bbc": 0,
"dt": 1,
"dw": 0,
"dc": 0,
"fi": { }
}
},
"cp": {
"01DA3E2CBC0B9610000001A7": {
"id": 0,
"he": 1000,
"tc": 0,
"sc": 4294967295,
"uf": false,
"uk": false,
"sm": 0,
"bf": "01DA3E2CBC0B9610000001A4",
"f1": "한컴바탕",
"t1": 1,
"f2": "한컴바탕",
"t2": 1,
"f3": "함초롬바탕",
"t3": 1,
"f4": "한컴바탕",
"t4": 1,
"f5": "한컴바탕",
"t5": 1,
"f6": "한컴바탕",
"t6": 1,
"f7": "한컴바탕",
"t7": 1,
"r1": 100,
"r2": 100,
"r3": 100,
"r4": 100,
"r5": 100,
"r6": 100,
"r7": 100,
"s1": 0,
"s2": 0,
"s3": 0,
"s4": 0,
"s5": 0,
"s6": 0,
"s7": 0,
"e1": 100,
"e2": 100,
"e3": 100,
"e4": 100,
"e5": 100,
"e6": 100,
"e7": 100,
"o1": 0,
"o2": 0,
"o3": 0,
"o4": 0,
"o5": 0,
"o6": 0,
"o7": 0,
"it": false,
"bo": false,
"ut": 0,
"us": 1,
"uc": 0,
"st": false,
"ss": 1,
"so": 0,
"ot": 0,
"ht": 0,
"hc": 0,
"hx": 0,
"hy": 0,
"em": false,
"en": false,
"su": false,
"sb": false
},
"01DA3E2CBC0B9610000001A8": {
"id": 1,
"he": 1000,
"tc": 0,
"sc": 4294967295,
"uf": false,
"uk": false,
"sm": 0,
"bf": "01DA3E2CBC0B9610000001A5",
"f1": "함초롬바탕",
"t1": 1,
"f2": "함초롬바탕",
"t2": 1,
"f3": "함초롬바탕",
"t3": 1,
"f4": "함초롬바탕",
"t4": 1,
"f5": "함초롬바탕",
"t5": 1,
"f6": "함초롬바탕",
"t6": 1,
"f7": "함초롬바탕",
"t7": 1,
"r1": 100,
"r2": 100,
"r3": 100,
"r4": 100,
"r5": 100,
"r6": 100,
"r7": 100,
"s1": 0,
"s2": 0,
"s3": 0,
"s4": 0,
"s5": 0,
"s6": 0,
"s7": 0,
"e1": 100,
"e2": 100,
"e3": 100,
"e4": 100,
"e5": 100,
"e6": 100,
"e7": 100,
"o1": 0,
"o2": 0,
"o3": 0,
"o4": 0,
"o5": 0,
"o6": 0,
"o7": 0,
"it": false,
"bo": false,
"ut": 0,
"us": 1,
"uc": 0,
"st": true,
"ss": 16,
"so": 0,
"ot": 0,
"ht": 0,
"hc": 0,
"hx": 0,
"hy": 0,
"em": false,
"en": false,
"su": false,
"sb": false
},
"01DA3E2CBC0B9610000001A9": {
"id": 2,
"he": 1000,
"tc": 0,
"sc": 4294967295,
"uf": false,
"uk": false,
"sm": 0,
"bf": "01DA3E2CBC0B9610000001A5",
"f1": "함초롬돋움",
"t1": 1,
"f2": "함초롬돋움",
"t2": 1,
"f3": "함초롬돋움",
"t3": 1,
"f4": "함초롬돋움",
"t4": 1,
"f5": "함초롬돋움",
"t5": 1,
"f6": "함초롬돋움",
"t6": 1,
"f7": "함초롬돋움",
"t7": 1,
"r1": 100,
"r2": 100,
"r3": 100,
"r4": 100,
"r5": 100,
"r6": 100,
"r7": 100,
"s1": 0,
"s2": 0,
"s3": 0,
"s4": 0,
"s5": 0,
"s6": 0,
"s7": 0,
"e1": 100,
"e2": 100,
"e3": 100,
"e4": 100,
"e5": 100,
"e6": 100,
"e7": 100,
"o1": 0,
"o2": 0,
"o3": 0,
"o4": 0,
"o5": 0,
"o6": 0,
"o7": 0,
"it": false,
"bo": false,
"ut": 0,
"us": 1,
"uc": 0,
"st": true,
"ss": 16,
"so": 0,
"ot": 0,
"ht": 0,
"hc": 0,
"hx": 0,
"hy": 0,
"em": false,
"en": false,
"su": false,
"sb": false
}
},
"tp": {
"01DA3E2CBC0B9610000001AA": {
"id": 0,
"al": false,
"ar": false,
"tp": [ ]
}
},
"nu": { },
"bu": {
"01DA3E2CBC0B9610000001AB": {
"id": 1,
"ch": "",
"ui": false,
"im": { },
"ph": {
"le": 0,
"al": 0,
"ui": false,
"ai": true,
"wa": 0,
"tt": 0,
"to": 50,
"cp": ""
}
}
},
"pp": {
"01DA3E2CBC0B9610000001AC": {
"id": 0,
"ah": 0,
"av": 0,
"ht": 0,
"hi": "",
"hl": 0,
"tp": "01DA3E2CBC0B9610000001AA",
"kb": 0,
"kn": true,
"ko": false,
"kk": false,
"kl": false,
"kp": false,
"kw": 0,
"co": 0,
"fl": false,
"st": true,
"sl": false,
"ae": false,
"aa": false,
"mi": 0,
"ml": 0,
"mr": 0,
"mp": 0,
"mn": 0,
"lt": 0,
"lv": 160,
"bf": "01DA3E2CBC0B9610000001A4",
"bl": 0,
"br": 0,
"bt": 0,
"bb": 0,
"bc": false,
"bi": false
},
"01DA3E2CBC0B9610000001AD": {
"id": 1,
"ah": 0,
"av": 0,
"ht": 0,
"hi": "",
"hl": 0,
"tp": "01DA3E2CBC0B9610000001AA",
"kb": 0,
"kn": true,
"ko": false,
"kk": false,
"kl": false,
"kp": false,
"kw": 0,
"co": 0,
"fl": false,
"st": true,
"sl": false,
"ae": false,
"aa": false,
"mi": 0,
"ml": 0,
"mr": 0,
"mp": 0,
"mn": 0,
"lt": 0,
"lv": 160,
"bf": "01DA3E2CBC0B9610000001A5",
"bl": 0,
"br": 0,
"bt": 0,
"bb": 0,
"bc": false,
"bi": false
},
"01DA3E2CBC0B9610000001AE": {
"id": 2,
"ah": 3,
"av": 0,
"ht": 0,
"hi": "",
"hl": 0,
"tp": "01DA3E2CBC0B9610000001AA",
"kb": 0,
"kn": false,
"ko": false,
"kk": false,
"kl": false,
"kp": false,
"kw": 0,
"co": 0,
"fl": false,
"st": true,
"sl": false,
"ae": false,
"aa": false,
"mi": 0,
"ml": 0,
"mr": 0,
"mp": 0,
"mn": 0,
"lt": 0,
"lv": 160,
"bf": "01DA3E2CBC0B9610000001A5",
"bl": 0,
"br": 0,
"bt": 0,
"bb": 0,
"bc": false,
"bi": false
},
"01DA3E2CBC0B9610000001AF": {
"id": 3,
"ah": 0,
"av": 0,
"ht": 3,
"hi": "01DA3E2CBC0B9610000001AB",
"hl": 0,
"tp": "01DA3E2CBC0B9610000001AA",
"kb": 0,
"kn": true,
"ko": false,
"kk": false,
"kl": false,
"kp": false,
"kw": 0,
"co": 0,
"fl": false,
"st": true,
"sl": false,
"ae": false,
"aa": false,
"mi": 0,
"ml": 0,
"mr": 0,
"mp": 0,
"mn": 0,
"lt": 0,
"lv": 160,
"bf": "01DA3E2CBC0B9610000001A5",
"bl": 0,
"br": 0,
"bt": 0,
"bb": 0,
"bc": false,
"bi": false
}
},
"st": {
"01DA3E2CBC0B9610000001B0": {
"id": 0,
"ty": 0,
"na": "바탕글",
"en": "Normal",
"pp": "01DA3E2CBC0B9610000001AD",
"cp": "01DA3E2CBC0B9610000001A8",
"ns": "01DA3E2CBC0B9610000001B0",
"li": 1042,
"lf": false
}
},
"mp": { },
"ro": {
"hp": "01DA3E2CBC0B96100000016B",
"01DA3E2CBC0B96100000016B": {
"np": "",
"id": 0,
"pp": "01DA3E2CBC0B9610000001AC",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 3,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A7",
"ch": [
{
"cc": 2,
"ci": 1936024420,
"co": "01DA3E2CBC0B961000000168"
}
,
{
"cc": 2,
"ci": 1668246628,
"co": "01DA3E2CBC0B961000000169"
}
,
{
"cc": 11,
"ci": 1952607264,
"co": "01DA3E2CBC0B96100000016A"
}
,
{
"t": ""
}
]
}
]
}
},
"sl": {
"01DA3E2CBC0B96100000016C": {
"co": "01DA3E2CBC0B96100000016A",
"id": 1,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 0,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000016D"
},
"01DA3E2CBC0B96100000016D": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "구분"
}
]
}
]
}
,
"01DA3E2CBC0B96100000016E": {
"co": "01DA3E2CBC0B96100000016A",
"id": 2,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 0,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000016F"
},
"01DA3E2CBC0B96100000016F": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AD",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "상세 내용"
}
]
}
]
}
,
"01DA3E2CBC0B961000000170": {
"co": "01DA3E2CBC0B96100000016A",
"id": 3,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 1,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000171"
},
"01DA3E2CBC0B961000000171": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "웹 배포 서버"
}
]
}
]
}
,
"01DA3E2CBC0B961000000172": {
"co": "01DA3E2CBC0B96100000016A",
"id": 4,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 1,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000173"
},
"01DA3E2CBC0B961000000173": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AD",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "K-PaaS"
}
]
}
]
}
,
"01DA3E2CBC0B961000000174": {
"co": "01DA3E2CBC0B96100000016A",
"id": 5,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 2,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000175"
},
"01DA3E2CBC0B961000000175": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "개발 언어"
}
]
}
]
}
,
"01DA3E2CBC0B961000000176": {
"co": "01DA3E2CBC0B96100000016A",
"id": 6,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 2,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000177"
},
"01DA3E2CBC0B961000000177": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AD",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "Java, Python, JavaScript, HTML5"
}
]
}
]
}
,
"01DA3E2CBC0B961000000178": {
"co": "01DA3E2CBC0B96100000016A",
"id": 7,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 3,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000179"
},
"01DA3E2CBC0B961000000179": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "개발 툴"
}
]
}
]
}
,
"01DA3E2CBC0B96100000017A": {
"co": "01DA3E2CBC0B96100000016A",
"id": 8,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 3,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000017B"
},
"01DA3E2CBC0B96100000017B": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AD",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "IntelliJ, PyCharm, Visual Studio Code"
}
]
}
]
}
,
"01DA3E2CBC0B96100000017C": {
"co": "01DA3E2CBC0B96100000016A",
"id": 9,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 4,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 21897,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000017D"
},
"01DA3E2CBC0B96100000017D": {
"np": "01DA3E2CBC0B96100000017E",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "주요 개발 "
}
]
}
]
},
"01DA3E2CBC0B96100000017E": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "프레임워크"
}
]
}
]
}
,
"01DA3E2CBC0B96100000017F": {
"co": "01DA3E2CBC0B96100000016A",
"id": 10,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 4,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 21897,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000180"
},
"01DA3E2CBC0B961000000180": {
"np": "01DA3E2CBC0B961000000181",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "MSA에 적합한 개발 및 운영 환경을 제공하는 Spring Boot Framework 적용"
}
]
}
]
},
"01DA3E2CBC0B961000000181": {
"np": "01DA3E2CBC0B961000000182",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "ORM 구현을 위한 Spring Data JPA"
}
]
}
]
},
"01DA3E2CBC0B961000000182": {
"np": "01DA3E2CBC0B961000000183",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "API Gateway 구현을 위한 Spring Cloud Gateway 적용"
}
]
}
]
},
"01DA3E2CBC0B961000000183": {
"np": "01DA3E2CBC0B961000000184",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "API Gateway의 접근제어 구현을 위한 Spring Cloud Security 적용"
}
]
}
]
},
"01DA3E2CBC0B961000000184": {
"np": "01DA3E2CBC0B961000000185",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "API Gateway 라우팅 기능 구현을 위한 Spring Cloud WebFlux 적용"
}
]
}
]
},
"01DA3E2CBC0B961000000185": {
"np": "01DA3E2CBC0B961000000186",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "사용자 접근 권한 제어를 위한 Spring Security 적용"
}
]
}
]
},
"01DA3E2CBC0B961000000186": {
"np": "01DA3E2CBC0B961000000187",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "서비스 디스커버리 및 로드밸런싱을 위해 Eureka Server, Eureka Client 적용"
}
]
}
]
},
"01DA3E2CBC0B961000000187": {
"np": "01DA3E2CBC0B961000000188",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "AppManger에 각 서버의 Spring Actuator를 통해 Graceful Shutdown 적용 "
}
]
}
]
},
"01DA3E2CBC0B961000000188": {
"np": "01DA3E2CBC0B961000000189",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "LLM 기반의 실종자 데이터 분석 결과 제공을 위한 LangChain 적용"
}
]
}
]
},
"01DA3E2CBC0B961000000189": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "파이썬 기반의 LangChain 서비스 제공을 위한 Flask 적용"
}
]
}
]
}
,
"01DA3E2CBC0B96100000018A": {
"co": "01DA3E2CBC0B96100000016A",
"id": 11,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 5,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 10697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000018B"
},
"01DA3E2CBC0B96100000018B": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "데이터베이스"
}
]
}
]
}
,
"01DA3E2CBC0B96100000018C": {
"co": "01DA3E2CBC0B96100000016A",
"id": 12,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 5,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 10697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000018D"
},
"01DA3E2CBC0B96100000018D": {
"np": "01DA3E2CBC0B96100000018E",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "서비스별 독립적인 데이터베이스를 생성하여 서비스와 1:1 매칭"
}
]
}
]
},
"01DA3E2CBC0B96100000018E": {
"np": "01DA3E2CBC0B96100000018F",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "얼굴 분석 정보 저장 및 관리 위한 MySQL 사용"
}
]
}
]
},
"01DA3E2CBC0B96100000018F": {
"np": "01DA3E2CBC0B961000000190",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "실종자 및 회원 정보 저장 및 관리를 위한 MySQL 사용"
}
]
}
]
},
"01DA3E2CBC0B961000000190": {
"np": "01DA3E2CBC0B961000000191",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "채팅을 위한 RedisDB 사용"
}
]
}
]
},
"01DA3E2CBC0B961000000191": {
"np": "01DA3E2CBC0B961000000192",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "PWA(Progressive Web Apps)을 위한 Manifest와 ServiceWork 사용"
}
]
}
]
},
"01DA3E2CBC0B961000000192": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "벡터 형식의 실종자 데이터 저장을 위한 ChromaDB"
}
]
}
]
}
,
"01DA3E2CBC0B961000000193": {
"co": "01DA3E2CBC0B96100000016A",
"id": 13,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 6,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 10697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000194"
},
"01DA3E2CBC0B961000000194": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "API"
}
]
}
]
}
,
"01DA3E2CBC0B961000000195": {
"co": "01DA3E2CBC0B96100000016A",
"id": 14,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 6,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 10697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B961000000196"
},
"01DA3E2CBC0B961000000196": {
"np": "01DA3E2CBC0B961000000197",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "얼굴 이미지 분석 정보 저장 및 나이 예측, 유사도 정보 분석 기능 제공을 위한 SK NUGU facecan Open API"
}
]
}
]
},
"01DA3E2CBC0B961000000197": {
"np": "01DA3E2CBC0B961000000198",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "Object Storage 사용을 위한 S3 Bucket API"
}
]
}
]
},
"01DA3E2CBC0B961000000198": {
"np": "01DA3E2CBC0B961000000199",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "경찰청 제공 실시간 실종자 API"
}
]
}
]
},
"01DA3E2CBC0B961000000199": {
"np": "01DA3E2CBC0B96100000019A",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "Push 알림을 위한 flarelane API"
}
]
}
]
},
"01DA3E2CBC0B96100000019A": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AF",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "실종자 데이터 임베딩 및 실종 데이터 분석 질의응답을 위한 OpenAI API"
}
]
}
]
}
,
"01DA3E2CBC0B96100000019B": {
"co": "01DA3E2CBC0B96100000016A",
"id": 15,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 7,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 5897,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000019C"
},
"01DA3E2CBC0B96100000019C": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "라이브러리"
}
]
}
]
}
,
"01DA3E2CBC0B96100000019D": {
"co": "01DA3E2CBC0B96100000016A",
"id": 16,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 7,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 5897,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B96100000019E"
},
"01DA3E2CBC0B96100000019E": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AD",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "Spring Open Feign Client, JSON Web Token, Thymeleaf, JQuery, Lombok,Websoket,OpenAIEmbeddings, RecursiveCharacterTextSplitter, RetrievalQA"
}
]
}
]
}
,
"01DA3E2CBC0B96100000019F": {
"co": "01DA3E2CBC0B96100000016A",
"id": 17,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 0,
"ar": 8,
"sc": 1,
"sr": 1,
"sw": 7115,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B9610000001A0"
},
"01DA3E2CBC0B9610000001A0": {
"np": "01DA3E2CBC0B9610000001A1",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "형상관리 "
}
]
}
]
},
"01DA3E2CBC0B9610000001A1": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AE",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "도구 "
}
]
}
]
}
,
"01DA3E2CBC0B9610000001A2": {
"co": "01DA3E2CBC0B96100000016A",
"id": 18,
"td": 0,
"lw": 0,
"va": 1,
"ll": "",
"ln": "",
"tc": {
"he": false,
"hm": false,
"pr": false,
"ed": false,
"di": false,
"bf": "01DA3E2CBC0B9610000001A6",
"ac": 1,
"ar": 8,
"sc": 1,
"sr": 1,
"sw": 40509,
"sh": 2697,
"ml": 510,
"mr": 510,
"mt": 141,
"mb": 141
},
"hp": "01DA3E2CBC0B9610000001A3"
},
"01DA3E2CBC0B9610000001A3": {
"np": "",
"id": -2147483648,
"pp": "01DA3E2CBC0B9610000001AD",
"si": "01DA3E2CBC0B9610000001B0",
"bf": 0,
"ru": [
{
"cp": "01DA3E2CBC0B9610000001A9",
"ch": [
{
"t": "Git"
}
]
}
]
}
},
"cs": {
"01DA3E2CBC0B961000000168": {
"cc": 2,
"ci": 1936024420,
"td": 0,
"tv": false,
"sc": 1134,
"ts": 8000,
"ms": "",
"os": "",
"gl": 0,
"gc": 0,
"gw": false,
"ns": 0,
"np": 0,
"ni": 0,
"nt": 0,
"ne": 0,
"hh": false,
"hf": false,
"hm": false,
"fb": false,
"hb": false,
"fi": false,
"hi": false,
"hp": false,
"he": false,
"sl": false,
"lr": 0,
"lc": 0,
"ld": 0,
"ls": 0,
"pp": {
"ls": false,
"wi": 59528,
"he": 84188,
"gt": 0,
"ml": 8504,
"mr": 8504,
"mt": 5668,
"mb": 4252,
"mh": 4252,
"mf": 4252,
"mg": 0
},
"fn": {
"at": 0,
"au": "",
"ap": "",
"ac": ")",
"as": false,
"ll": -1,
"lt": 1,
"lw": 1,
"lc": 0,
"sa": 850,
"sb": 567,
"st": 283
,
"nt": 0,
"nn": 1,
"pp": 0,
"pb": false
},
"en": {
"at": 0,
"au": "",
"ap": "",
"ac": ")",
"as": false,
"ll": 0,
"lt": 1,
"lw": 1,
"lc": 0,
"sa": 850,
"sb": 567,
"st": 0
,
"nt": 0,
"nn": 1,
"pp": 0,
"pb": false
},
"pb": [
{
"ty": 0,
"bf": "01DA3E2CBC0B9610000001A4",
"tb": true,
"hi": false,
"fi": false,
"fa": 0,
"ol": 1417,
"or": 1417,
"ot": 1417,
"ob": 1417
},
{
"ty": 1,
"bf": "01DA3E2CBC0B9610000001A4",
"tb": true,
"hi": false,
"fi": false,
"fa": 0,
"ol": 1417,
"or": 1417,
"ot": 1417,
"ob": 1417
},
{
"ty": 2,
"bf": "01DA3E2CBC0B9610000001A4",
"tb": true,
"hi": false,
"fi": false,
"fa": 0,
"ol": 1417,
"or": 1417,
"ot": 1417,
"ob": 1417
}
],
"mp": [ ]
}
,
"01DA3E2CBC0B961000000169": {
"cc": 2,
"ci": 1668246628,
"ty": 0,
"la": 0,
"co": 1,
"ss": true,
"sg": 0,
"lt": 0,
"lw": 0,
"lc": 0,
"cs": [ ]
}
,
"01DA3E2CBC0B96100000016A": {
"cc": 11,
"ci": 1952607264,
"id": 1417251061,
"zo": 0,
"nt": 2,
"tw": 1,
"tf": 0,
"lo": false,
"swi": 47624,
"she": 62858,
"swr": 4,
"shr": 2,
"spr": false,
"pta": true,
"pal": false,
"pvr": 2,
"phr": 3,
"pva": 0,
"ph1": 0,
"pvo": 0,
"ph2": 0,
"pfw": true,
"pao": false,
"pha": false,
"ole": 141,
"ori": 141,
"oto": 141,
"obo": 141,
"ca": { },
"sc": ""
,
"pb": 2,
"rh": true,
"na": false,
"ho": false,
"if": true,
"sa": false,
"rc": 9,
"cco": 2,
"cs": 0,
"bf": "01DA3E2CBC0B9610000001A6",
"ile": 510,
"iri": 510,
"ito": 141,
"ibo": 141,
"cl": [ ],
"tr": [
[
{
"so": "01DA3E2CBC0B96100000016C",
"li": 1
},
{
"so": "01DA3E2CBC0B96100000016E",
"li": 2
}
],
[
{
"so": "01DA3E2CBC0B961000000170",
"li": 3
},
{
"so": "01DA3E2CBC0B961000000172",
"li": 4
}
],
[
{
"so": "01DA3E2CBC0B961000000174",
"li": 5
},
{
"so": "01DA3E2CBC0B961000000176",
"li": 6
}
],
[
{
"so": "01DA3E2CBC0B961000000178",
"li": 7
},
{
"so": "01DA3E2CBC0B96100000017A",
"li": 8
}
],
[
{
"so": "01DA3E2CBC0B96100000017C",
"li": 9
},
{
"so": "01DA3E2CBC0B96100000017F",
"li": 10
}
],
[
{
"so": "01DA3E2CBC0B96100000018A",
"li": 11
},
{
"so": "01DA3E2CBC0B96100000018C",
"li": 12
}
],
[
{
"so": "01DA3E2CBC0B961000000193",
"li": 13
},
{
"so": "01DA3E2CBC0B961000000195",
"li": 14
}
],
[
{
"so": "01DA3E2CBC0B96100000019B",
"li": 15
},
{
"so": "01DA3E2CBC0B96100000019D",
"li": 16
}
],
[
{
"so": "01DA3E2CBC0B96100000019F",
"li": 17
},
{
"so": "01DA3E2CBC0B9610000001A2",
"li": 18
}
]
]
}
},
"bi": [ ],
"bidt": { }
}--></div><!--EndFragment--></body></html>

