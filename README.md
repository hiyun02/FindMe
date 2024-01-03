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
<h2>개발 환경 </h2>
![image](https://github.com/wonjibkim/Exception/assets/101384372/13471615-c83a-4349-8179-25ad88c86877)

<br>
<br>
<h2>시연 동영상 유튜브 링크 </h2>
https://www.youtube.com/watch?v=K1rNKIbVjp4



