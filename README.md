외부 환경 변수 설정
  - 노션 링크 : https://www.notion.so/Spring-Boot-eb93f4dcf43a44cb86e0ae98f9919175
  - 방법 : 
       1. 자바 클래스 위에 @PropertySource 선언("properties 파일이름(resources 디렉토리 아래)")
       2.   @Autowired
            Environment env;
            필드 선언
       3.  env.getProperty("properties에 선언된 필드명") => String 형태로 반환됩니다.