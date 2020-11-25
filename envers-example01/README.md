# envers-example01
## 소개
spring-data-envers의 기본 작동 원리에 대해 설명하고자 작성한 코드입니다.
## 참고사항
이번 예제에서는 API 요청과 응답을 처리하기 위해 사용하는 클래스와, 실제 JPA 엔티티로 사용하는 클래스를 분리 사용하고 있습니다. 이에 응답 본문의 데이터 구조가 엔티티와는 다를 수 있습니다.

## 예제에 사용한 엔티티
- Employee.java [파일보기](src/main/java/org/iceflower/study/envers/employee/Employee.java)
- Audit.java [파일보기](src/main/java/org/iceflower/study/envers/Audit.java)
## DB에 실제로 생성된 테이블 구조
![테이블 구조](https://user-images.githubusercontent.com/4101612/100188838-6ab24080-2f2e-11eb-8eb3-306081df60cc.png)
## 엔티티에 새로운 정보를 추가했을 때
1. 아래와 같은 내용으로 신규 정보를 추가했습니다.
![데이터 신규추가](https://user-images.githubusercontent.com/4101612/100188992-c11f7f00-2f2e-11eb-9d4b-a4d141d6e038.png)
2. 변경이력 확인 결과, 아래와 같이, 엔티티 신규생성 이력이 기록되었음을 알 수 있습니다.
![데이터 신규추가 이력 확인](https://user-images.githubusercontent.com/4101612/100189079-e90ee280-2f2e-11eb-8a69-bbf6c07d9b5d.png)
## 엔티티의 기존 정보를 수정했을 때
1. 신규 추가했던 엔티티의 정보를 아래와 같이 수정해보겠습니다.
![데이터 수정](https://user-images.githubusercontent.com/4101612/100189848-77379880-2f30-11eb-98a5-13a31449988f.png)
2. 변경이력 확인 결과, 아래와 같이 신규 추가 이력에 더하여, 기존 엔티티를 수정한 이력이 추가되었음을 알 수 있습니다.
![데이터 수정 이력 확인](https://user-images.githubusercontent.com/4101612/100189876-874f7800-2f30-11eb-9506-d809f3add15a.png)
## 엔티티의 기존 정보를 삭제했을 때
1. 마지막으로, 엔티티를 삭제해보도록 하겠습니다.
![데이터 삭제](https://user-images.githubusercontent.com/4101612/100189973-afd77200-2f30-11eb-8a8c-e2755a634224.png)
2. 변경이력 확인 결과, 아래와 같이 신규 추가 이력, 수정 이력에 더하여 삭제 이력까지 추가되었음을 알 수 있습니다.
![데이터 삭제 이력 확인](https://user-images.githubusercontent.com/4101612/100189978-b2d26280-2f30-11eb-8926-fe8e940b0a99.png)