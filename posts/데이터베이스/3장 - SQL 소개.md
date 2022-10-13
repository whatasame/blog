# 3장 - SQL 소개

## SQL이란?

SQL은 Structured Query Language의 약자로서 Query라는 질의어를 통해 관계형 데이터베이스에 정보를 저장하고 처리하기 위한 프로그래밍 언어입니다. 

✅ **특징**

- DDL, DML, DCL 기능을 모두 지원하는 완전한 데이터 접근·조작 언어이다.
- 관계형 DBMS를 위해 정의되었다.
- 관계형 DBMS의 산업 표준이다.
- 비절차적 언어이다.
    
    SQL은 C와 Java와 같이 반복문과 같은 기능이 없고 Jupiter Notebook처럼 한 줄씩 순서 상관없이 실행 가능합니다.
    

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 소개-Untitled.png"></p>

DBMS에 전체 데이터가 저장되어있고 SQL을 통해 데이터의 일부 Set을 가져오는 형태로 사용됩니다.

## SQL 기능

SQL은 DDL, DML, DCL 기능을 모두 지원하는 완전한 데이터 접근·조작 언어입니다. SQL이 지원하는 DDL, DML, DCL 기능은 다음과 같습니다.

### Data Definition Language

DDL은 테이블 단위 Query로 구성됩니다.

- CREATE : 새로운 테이블, 인덱스, 뷰를 정의한다.
- DROP : 테이블, 인덱스, 뷰를 삭제한다.
- ALTER : 테이블 구조(Table Schema)를 변경한다.

### Data Manipulation Language

DML은 레코드 단위 Query로 구성됩니다.

- SELECT : 관계형 질의 기능을 수행한다.
- INSERT : 테이블에 새로운 행(레코드)를 삽입한다.
- UPDATE : 테이블에 있는 레코드의 값을 변경한다.
- DELETE : 테이블에 있는 레코드를 삭제한다.

### Data Control Language

DCL은 인증, 트랜잭션 관리, 무결성에 관련된 Query로 구성됩니다.

- 인증
    
    테이블에 대한 접근 권한 지정 Query입니다. 
    
    - GRANT : 유저에게 테이블 접근 권한을 줍니다.
    - REVOKE : 유저에게 테이블 접근 권한을 회수합니다.
- 트랜잭션 관리
    
    Query로 발생한 결과를 DBMS에 반영하거나 반영한 것을 되돌리는 Query입니다.
    
    - COMMIT : 현재까지 처리한 데이터를 DBMS에 반영합니다.
    - ROLLBACK : 직전 Commit을 취소하고 이전 상태로 되돌립니다.
- 무결성
    - TRIGGER :
    - ASSERTION :

## SQL에서 사용되는 용어

- Table : 행과 열로 구성되는 2차원 데이터
- Row : 테이블의 가로 단위로서 하나의 레코드가 하나의 행이 된다.
- Column : 테이블의 세로 단위로서 하나의 필드가 하나의 열이 된다.
- Data Value : 테이블에서 가장 작은 데이터 단위로서 행과 열의 교차로 가리킬 수 있다.
- Domain : Column 단위 데이터에서 실제 데이터들의 집합
- Primary Key : 테이블의 모든 행에 대하여 값이 다른 열. 테이블에서 underscore로 표시
- Foreign Key : 한 테이블의 필드(attribute) 중 다른 테이블의 행(record)을 식별할 수 있는 키

<aside>
💡 Primary Key의 경우 underscore로 표시하고 Foreign Key*는 asterisk로 표시합니다.

</aside>

<aside>
💡 (Row, Record, Tuple), (Column, Field, Attribute)는 서로 같은 것을 뜻합니다.

</aside>

### Sample Database Schema

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 소개-Untitled 1.png"></p>

student 테이블은 학번을 주키로 갖고 학과번호, 지도교수를 외래키로 갖습니다.