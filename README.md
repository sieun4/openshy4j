# openshy4

Rapa Projcet


## 9월 1일 업데이트 내용
개요

일단 제가 실습한 CRUD 게시판 구조를 업로드했습니다.
글 쓴 방식이 가독성이 떨어지면 의견주세요!


프로젝트 설정값 설명

- JDK 1.8 (호환성을 고려해 버젼 최소화)
- SpringBoot 2.1.7 (많이 쓰이는 안정적 버젼)
- gredle 6.3 (일부로 낮은버젼 쓸 필요성이 없는 듯하여 Default를 사용)

직접 하셔야하는 것

- IntelliJ 설정 >
1. jdk 8로 세팅합니다.
사용해야하는 SDK(JDK8) 파일 첨부 :
https://www.dropbox.com/s/yslt0rit4cnfmb5/jdk-8u261-windows-i586.exe?dl=0
JDK 세팅법 : 
https://m.blog.naver.com/PostView.nhn?blogId=writer0713&logNo=221547796029&proxyReferer=https:%2F%2Fwww.google.com%2F
(글 안에 유튜브 영상이 설명 잘 되어있습니다.)

2. 플러그인을 설치합니다.
IntelliJ에서 Ctrl+Shift+A 후에 plugins 실행,
Lombok과 Mustache를 설치해줍니다
(둘 다 검색 후 최상단에 있음, IntelliJ restart 필요)


구조와 흐름

- src/main/java/com/openshy4j 디렉토리와 그 안에 domain, service, web 디렉토리와 파일(back-end)들을생성

- src/main/resources 에 화면 구성 파일(front-end)들을 생성.

- .../web/IndexController는 resource 안의 파일(머스테치파일)을 매핑해줍니다.

- .../web/PostsApiContorller는 화면(머스테치파일)에서 클릭 이벤트로 발생된
 자바스크립트(.../resources/static/js/app/index.js)의 요청을
 수행하기 위해 .../domain/service/posts/PostsService 와 매핑해줍니다.

- 위 service 는 .../web/dto에 있는 dto 객체들의 형식으로 JPA의 메소드(PostsRepository)를 사용하여 요청된 CRUD를
DB에 수행합니다.

- .../resources/static/js/app/index.js는 요청의 성공/실패에 따라 메세지를 띄워줍니다.


실행

- .../openshy4j/openshy4jApplication을 실행합니다.
- http://localhost:8080 으로 접속해서 사용해봅니다.
- http://localhost:8080/h2-console/ 에서
JDBC URL : jdbc:h2:mem:testdb 로 접속해서 쿼리를 날려 DB 내용을 확인할 수 있습니다.
(h2는 테스트용이며 App을 restart하면 저장 내용이 사라집니다. )

