# URL 단축기

## 기술 스택
- Java, Spring Boot, MongoDB, Vue

## 제약 조건
- 단축 URL에는 숫자(0-9)와 영문자(a-z, A-Z)만 사용 가능

## 흐름도
![image](https://user-images.githubusercontent.com/50076031/132652540-2e42a95d-c17c-49c7-bc5a-e34f948ea7c3.png)

1. 입력으로 긴 URL을 받는다.
2. 데이터베이스 해당 URL이 존재하는지 조회한다.
3. 데이터베이스에 존재하면 requestCount++ 후 단축 URL을 가져온 후 클라이언트에게 반환
4. 데이터베이스에 없으면 유일한 ID를 생성하고, 데이터베이스의 기본키로 사용한다.
5. 62진법 변환을 적용해서 ID를 단축 URL로 만든다.
6. 새로 생성된 데이터를 데이터베이스 저장하고, 단축 URL을 클라이언트에게 반환 