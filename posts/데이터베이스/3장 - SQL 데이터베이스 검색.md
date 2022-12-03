# 3장 - SQL 데이터베이스 검색

이 글은 Oracle SQL을 기준으로 작성되었습니다.

## SELECT

SELECT는 테이블에서 특정 열의 데이터를 검색할 때 사용하는 Query입니다.

### 기본적인 SQL SELECT 문법

기본적인 SELECT 사용법은 아래와 같습니다.

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 데이터베이스 검색-Untitled.png"></p>

- **SELECT** : 찾고자 하는 데이터의 Column 이름
    
    <aside>
    💡 DISTINCT : 중복 제거, alias : column의 이름을 재지정
    
    </aside>
    
- **FROM** : 찾고자 하는 테이블 이름
- WHERE : 조건 필터
- ORDER BY : 정렬 기준

어느 테이블에서 어떤 정보를 검색할 것인지가 기본이므로 SELECT Query를 사용할 땐 FROM 키워드는 반드시 있어야 합니다.

<aside>
💡 Oracle SQL에서 키워드와 검색 데이터 이름은 대소문자를 구분하지 않습니다.

</aside>

### 모든 필드 출력

SELECT *을 통해 대상 테이블의 모든 열을 출력할 수 있습니다.

```sql
SELECT * -- All columns
FROM student;
```

**📋 실행 결과**

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 데이터베이스 검색-Untitled 1.png"></p>

### 특정 필드 출력

대상 테이블의 특정 열을 지정하여 해당 열의 데이터를 출력할 수 있습니다.

```sql
SELECT sid, sname
FROM student;
```

**📋 실행 결과**

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 데이터베이스 검색-Untitled 2.png"></p>

### 도메인 중복 제거

DISTINCT 키워드를 통해 지정한 열에 대하여 같은 값을 가지는 레코드가 있으면 중복 레코드를 제거하고 하나의 레코드만 출력할 수 있습니다.

```sql
SELECT DISTINCT deptno
FROM student;
```

위 Query는 관계 대수에서 $\prod_{deptno}{\rm(Student)}$로 표현할 수 있습니다.

**📋 실행 결과**

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 데이터베이스 검색-Untitled 3.png"></p>

SELECT의 대상이 되는 모든 열에 대하여 값이 중복되는 레코드의 중복을 제거하고 하나의 레코드만 출력되었습니다.

### 특정 필드의 이름을 변경하여 출력

특정 필드의 이름을 변경하여 alias(가명, 별명)를 지정하여 출력할 수 있습니다.

```sql
--deptno 필드를 department 필드로 이름을 바꿔 출력
SELECT sid, sname, deptno department
FROM student
```

**📋 실행 결과**

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 데이터베이스 검색-Untitled 4.png"></p>

deptno열이 department열로 바뀌어 출력되었습니다.

### 필드끼리 결합하여 출력

|| 연산자를 사용하여 SELECT한 필드의 값을 단순하게 이어 출력할 수 있습니다.

```sql
--sid 필드와 sname 필드의 데이터 값을 이은 후 student 필드로 이름을 바꿔 출력
SELECT sid||sname student
FROM student;
```

**📋 실행 결과**

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 데이터베이스 검색-Untitled 5.png"></p>

|| 연산자는 필드의 값을 단순하게 이어줍니다. 필드의 수가 3개 이상인 경우에도 동일하게 작동합니다.

### 정렬 출력

ORDER BY 연산을 추가하여 SELECT Query 결과를 특정 필드를 기준으로 정렬할 수 있습니다.

```sql
SELECT sid, sname, deptno, grade
FROM student
ORDER BY deptno; --default : Ascending
```

**📋 실행 결과**

<p align="center"><img src="../../images/데이터베이스/3장 - SQL 데이터베이스 검색-Untitled 6.png"></p>

기본적으로 ORDER BY 연산은 결과를 기준 필드의 오름차순으로 출력합니다. DESC 키워드를 추가하여 내림차순으로 출력할 수 있습니다.

```sql
SELECT sid, sname, deptno, grade
FROM student
ORDER BY deptno DESC;
```