# [HELOAY] 영화 리뷰 프로젝트

## 목차
- [개발 환경](#환경)
- [기술 세부 스택](#스택)
  + [spring boot](#spring)
  + [그 외](#그외)
- [주요내용](#내용)
- [패키징구조](#패키징)
- [다이어그램](#다이어)
- [유스케이스](#케이스)
- [API](#API)
- [실행화면](#실행)
- [핵심기능](#핵심)
  + [애플리케이션 아키텍처](#아키텍처)
  + [회원가입](#핵심0)
  + [로그인](#핵심1)
  + [비밀번호 암호화](#핵심2)
  + [페이징](#핵심3)
  + [영화 등록](#핵심4)
  + [리뷰](#핵심5)
- [고찰](#고찰)

## <div id="환경">개발 환경</div>

* Intellij IDEA Ultimate 2022.2.2
* Java 11
* Gradle 7.6
* Spring Boot 2.7.8

## <div id="스택">기술 세부 스택</div>

<div id="spring">Spring Boot</div>

* Spring Web
* Spring Data JPA
* Thymeleaf
* Spring Security
* MariaDB
* Lombok

<div id="그외">그 외</div>
* HTML / CSS
* Bootstrap 5.2.2

## <div id="내용">주요 내용</div>
* Spring Boot 계층형 아키텍처 기반 웹 어플리케이션 개발
* 스프링 프레임워크 기반 백엔드 구축
* JPA를 사용한  RDBMS 도메인 설계
* Spring Sercurity를 사용한 로그인

## <div id="패키징">패키징 구조</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219867188-714148f1-93d8-4ad1-bea1-93b8b3a22deb.PNG"/>

## <div id="다이어">ERD</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219865337-50b471ec-c067-4c38-89fb-6f3c993ad795.PNG"/>

## <div id="API">API 설계</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/224544627-97492ce1-4f8c-444b-98af-17a05a30846d.jpg"/>

## <div id="케이스">유스케이스</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219866916-fc65538d-1b9b-4285-b9d4-1befa64a48c2.PNG"/>

## <div id="실행">실행 화면</div>
### 1. 회원가입 & 로그인, 로그아웃 
#### 1.1 회원가입
<img width="60%" src="https://user-images.githubusercontent.com/106221717/219846402-fa585685-f387-4c4e-b1ff-d3829b623795.gif"/>

#### 1.2 회원가입 정보 미기입
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846436-c7916cdb-5fe4-4b19-8f4d-85347f970cd0.gif" />

#### 1.3 로그인
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846093-8db7b889-45d2-43cf-9f98-ebf0a679bb96.gif"/>

#### 1.4 로그아웃
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219866624-7d4dc976-d71a-4b31-b3c1-f8cd3a4e7962.gif" />


### 2. 영화 등록
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846715-5b4a9524-e4b1-4c49-9d7f-cb1603e6f6a9.gif"/>


### 3. 영화 수정, 삭제
#### 3.1 영화 수정
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846790-c0186a48-2461-4419-9d8f-f1dc57c043e1.gif"/>

#### 3.2 영화 삭제
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846811-c5ea14ef-71eb-4e5b-aed5-2193bdcf29a1.gif"/>


### 4. 리뷰
#### 4.1 리뷰 등록
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846838-c5041cff-2647-4d1b-b413-b17dd2f4efdf.gif"/>

#### 4.2 리뷰 삭제
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846868-31442508-7080-468e-afac-4e736f53bf71.gif"/>

### 5. 페이징 & 상세 페이지 이동
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219866594-880c4d98-ae3f-4281-a0de-a5fad3d1d165.gif"/>

## <div id="핵심">핵심 기능</div>
### <div id="아키텍처">애플리케이션 아키텍처</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219866727-7d2b9acc-ba88-4a00-adc5-c939d086becc.PNG"/>

계층형 구조 사용
controller, web: 웹 계층
service: 비즈니스 로직, 트랜잭션 처리
repository: JPA를 직접 사용하는 계층, 엔티티 매니저 사용
domain: 엔티티가 모여 있는 계층, 모든 계층에서 사용

### <div id="핵심0">회원가입</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219857679-6392ed05-4fcc-4c9e-abd2-863569e29d51.PNG"/>

 ```
<div class="mb-3">
<label th:for="username">아이디</label>
<input type="text" th:field="*{username}" class="form-control" placeholder="아이디를 입력하세요" th:class="${#fields.hasErrors('username')}? 'form-control fieldError' : 'form-control'">
<p th:if = "${#fields.hasErrors('username')}" th:errors="*{username}"></p>
</div>
```
* post요청으로 들어온 데이터를 컨트롤러에서 검증하여 예외 발생시 결과를 model에 담아서 폼으로 랜더링 한다. 에러발생시 해당 클래스 `class="form-control" placeholder="아이디를 입력하세요" th:class="${#fields.hasErrors('username')}? 'form-control fieldError' : 'form-control'">`를 에러필드로 변경하고 에러 내용을 출력한다(`th:errors="*{username}"`).


### <div id="핵심1">로그인</div>
#### 로그인 전
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219847960-c13f7e95-9bbb-4cc3-94f3-351f7784ff11.PNG"/>


#### 로그인(USER)
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219848093-9ecc1ae0-0abf-459e-94cd-cf5fdbf91522.PNG"/>

#### 로그인(ADMIN)
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219848139-c17c6f9a-4946-4bcd-8d14-76e4f3fe3a89.PNG"/>

```java
sec:authorize="hasRole('ROLE_ADMIN')" //영화 버튼
sec:authorize="isAnonymous()" //로그인, 회원가입
sec:authorize="isAuthenticated()" //로그아웃 버튼
```
* thymleaf + spring security를 사용하여 security을 고려한 뷰를 작성 isAnonymous() 로그인하지 않은 사용자에게 로그인, 회원가입 버튼 활성화 / isAuthenticated() 로그인 사용자에게 로그아웃 버튼 활성화 / hasRole('ROLE_ADMIN') ROLE_ADMIN 권한을 가진 사용자만 영화 등록 버튼이 활성화 되서 출력된다.


### <div id="핵심2">비밀번호 암호화</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219849008-1d971b08-7f8e-4bb6-8070-fadcfb894f5d.PNG"/>

* 스프링 시큐리티에서 지원하는 BCryptPasswordEncoder BCrypt 해싱 함수를 사용해서 패스워드를 암호화 DB 유출시에도 비밀번호 데이터를 확인하기 어렵게 생성 해준다. 

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219849278-c727d193-eef9-4223-97c4-e0b8d6222179.PNG"/>


### <div id="핵심3">페이징</div>

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219851541-f30e267d-e702-4df1-b004-1efd1844fa66.PNG"/>

* `http://localhost:8080/main/movie/list?page=0` 처럼 GET 방식으로 요청된 URL에서 page값을 가져오기 위해 @RequestParam(value="page", defaultValue="0") int page 매개변수 추가 page가 전달되지 않을시 디폴트 값0 이 되도록 설정

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219851556-b2725e11-a3a5-4a5e-bc68-2fd26299afcf.PNG"/>

* Pageable 객체 생성시 `PageRequest.of(page, 10)`에서 page는 조회할 페이지의 번호 10은 한페이지에 보여줄 게시물의 숫자를 의미한다. 이렇게 하면 데이터 전체 조회가 아닌 한 페이지 데이터를 조회하도록 쿼리각 변경된다.

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219851563-dc9e3058-5e19-4df4-bc90-ab523d6072ef.PNG"/>

* 이전 페이지가 없는 경우 "이전" 링크 비활성화(다음 페이지도 동일) 페이지 리스트를 루프 `th:each="page: ${#numbers.sequence(0, paging.totalPages-1)}` 돌면서 해당 페이지로 이동 할 수 있는 링크 생성 `th:classappend="${page == paging.number} ? 'active'" class="page-item"` 조건식이 참인 경우 클래스값을 class 속성에 추가한다.

### <div id="핵심4">영화 등록</div>
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219864247-e2d2b6aa-a1db-44d2-8505-fda54aae129a.PNG"/>

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219864177-ee73ad3e-3506-43df-9107-0470caaecc08.PNG"/>

* 사진 파일의 경우 UUID를 이용하여 파일 고유이름을 랜덤으로 생성하여 지정해준 뒤 MultipartFile 인터페이스를 통해 사진 파일업로드 resources\\static\\movieposters 폴더에 저장

### <div id="핵심5">리뷰</div>
#### 리뷰 등록 
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219853110-75e73782-4d4d-4a2b-876c-3374d4564241.PNG"/>

* 양방향 매핑을 통해 유저아이디, 영화아이디 리뷰에 저장 할 수 있게 엔티티 구성

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219854256-d7bc40ba-7a6c-4d8d-873b-8c529cbcedc3.png"/>

* 스프링시큐리티는 인증된 사용자 정보를 세션에 담아놓고 세션이 유지되는 동안 사용자 객체를 DB로 접근하는 방법 없이 바로 사용할 수 있도록 한다. SecurityContextHolder 내부의 SecurityContext에 Authentication 객체로 저장해두고 있는데 로그인 세션 정보를 애노테이션으로 간편하게 받을 수 있는 @AuthenticationPrincipal 사용하여 로그인 세션 정보를 받아와서 리뷰 테이블에 유저정보를 저장한다.

#### 리뷰 삭제
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219854335-cf4f3331-5320-4943-ae02-82f4c2f42e72.png"/>

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219854398-d612f3ff-8a0f-4d85-81c4-2b9083996eff.PNG"/>

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219854148-cea05396-35df-4201-a651-2aeb3a6b96db.PNG"/>

* 등록과 동일하게 유저 정보를 가져와 thymleaf th:if 문법을 이용해 현재 로그인된 사용자와 댓글 작성자가 같을때만 삭제 버튼 활성화 되도록 설정


## <div id="고찰">고찰</div>
* 지금까지 강의를 통해 배운 스프링 부트, 스프링 시큐리티, 스프링 JPA, Thymleaf 등 모든 기술을 사용해보려 최대한 노력했다. 강의를 들을 때만 해도 어느 정도 이해가 되고 있다고 생각했었다 하지만 개발을 진행하며 학습과 직접 응용하는 것은 전혀 다른 문제라는 생각이 먼저 들었던 것 같다. 이것저것 기능을 추가하다 보니 계획단계에서 구상한 기능을 모두 구현하지는 못했지만 추후 Querydsl를 이용해서 검색 기능, 리스트 정렬, 게시판, AWS를 이용한 배포를 통해 프로젝트 완성도를 높일 예정이다.

* 혼자서 프로젝트를 진행하는 만큼 시간과 노력을 많이 들여도 자주 벽에 부딪히는 상황이 찾아왔다. 첫 번째로 우선순위를 정하고 계획대로 개발을 진행해야 하는데 이것저것 상황에 맞춰서 개발하다 보니 그로 인한 여러 문제가 자꾸 발생하게 되어 일정이 자꾸 밀리게 되었다. 개발 시작 전 확실한 계획수립을 통해 문제 발생을 줄이는 개발 습관을 지녀야겠다는 생각이 들었다. 두 번째는 기능 구현의 난이도가 올라갈수록 노력한 만큼 결과가 나오는 것은 만족감이 있지만 문제가 자주 발생하여 내가 과연 올바른 방향으로 개발을 진행하고 있는가에 대한 확신이 사라지고 의심하게 되었다. 실제 현직에서 경험을 쌓고, 조언과 질문을 구할 사람이 주변에 많아지게 되면 다양한 시선과 조언을 통해 의심이 확신으로 바뀌게 되어 더 빠른 성장을 할 수 있을 것이다.
