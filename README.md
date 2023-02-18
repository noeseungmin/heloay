# [HELOAY] 영화 리뷰 프로젝트

## 개발 환경

* Intellij IDEA Ultimate 2022.2.2
* Java 11
* Gradle 7.6
* Spring Boot 2.7.8

## 기술 세부 스택

Spring Boot

* Spring Web
* Spring Data JPA
* Thymeleaf
* Spring Security
* MariaDB
* Lombok

그 외
* HTML / CSS
* Bootstrap 5.2.2

## 주요 내용
* Spring Boot 계층형 아키텍처 기반 웹 어플리케이션 개발
* 스프링 프레임워크 기반 백엔드 구축
* JPA를 사용한  RDBMS 도메인 설계
* Spring Sercurity를 사용한 로그인

## 실행 화면
### 1. 회원가입 & 로그인, 로그아웃 
#### 1.1 회원가입
<img width="60%" src="https://user-images.githubusercontent.com/106221717/219846402-fa585685-f387-4c4e-b1ff-d3829b623795.gif"/>

#### 1.2 회원가입 정보 미기입
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846436-c7916cdb-5fe4-4b19-8f4d-85347f970cd0.gif" />

#### 1.3 로그인
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219846093-8db7b889-45d2-43cf-9f98-ebf0a679bb96.gif"/>

#### 1.4 로그아웃
<img width="80%" src="" />


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

### 5. 
<img width="80%" src=""/>

## 핵심 기능
### 로그인 
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


### 비밀번호 암호화
<img width="80%" src="https://user-images.githubusercontent.com/106221717/219849008-1d971b08-7f8e-4bb6-8070-fadcfb894f5d.PNG"/>

* 스프링 시큐리티에서 지원하는 BCryptPasswordEncoder BCrypt 해싱 함수를 사용해서 패스워드를 암호화 DB 유출시에도 비밀번호 데이터를 확인하기 어렵게 생성 해준다. 

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219849278-c727d193-eef9-4223-97c4-e0b8d6222179.PNG"/>


### 페이징

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219851541-f30e267d-e702-4df1-b004-1efd1844fa66.PNG"/>

* `http://localhost:8080/main/movie/list?page=0` 처럼 GET 방식으로 요청된 URL에서 page값을 가져오기 위해 @RequestParam(value="page", defaultValue="0") int page 매개변수 추가 page가 전달되지 않을시 디폴트 값0 이 되도록 설정

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219851556-b2725e11-a3a5-4a5e-bc68-2fd26299afcf.PNG"/>

* Pageable 객체 생성시 `PageRequest.of(page, 10)`에서 page는 조회할 페이지의 번호 10은 한페이지에 보여줄 게시물의 숫자를 의미한다. 이렇게 하면 데이터 전체 조회가 아닌 한 페이지 데이터를 조회하도록 쿼리각 변경된다.

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219851563-dc9e3058-5e19-4df4-bc90-ab523d6072ef.PNG"/>

* 이전 페이지가 없는 경우 "이전" 링크 비활성화(다음 페이지도 동일) 페이지 리스트를 루프 `th:each="page: ${#numbers.sequence(0, paging.totalPages-1)}` 돌면서 해당 페이지로 이동 할 수 있는 링크 생성 `th:classappend="${page == paging.number} ? 'active'" class="page-item"` 조건식이 참인 경우 클래스값을 class 속성에 추가한다.

### 영화

### 리뷰
<img width="80%" src="(https://user-images.githubusercontent.com/106221717/219852948-f4352b79-3903-4b80-979e-95716fceb11e.PNG"/>

<img width="80%" src="https://user-images.githubusercontent.com/106221717/219852929-ea2c304d-7e3c-4c86-8fce-f12efca8d7f1.PNG"/>

## 고찰
