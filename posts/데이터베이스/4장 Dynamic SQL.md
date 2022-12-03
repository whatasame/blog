# 4장 Dynamic SQL

Embedded SQL은 SQL 질의가 컴파일 시점에 완성됩니다. 따라서 SQL이 수정되면 전체 프로그램을 다시 컴파일 해야합니다. 또, Embedded SQL은 다른 종류의 컴퓨터 혹은 다른 프로그래밍 언어 간의 호환성이 없습니다.

## Dynamic SQL

이러한 Embedded SQL의 문제점을 해결한 것이 Dynamic SQL입니다. Dynamic SQL은 **런타임에 SQL을 구성하고 질의**를 요청하는 **API로 구성**하여 Embedded SQL이 갖는 문제점을 해결하였습니다.

Dynamic SQL과 Embedded SQL을 정리하면 아래와 같습니다.

- Embedded SQL
    - SQL이 수정되면 전체 프로그램을 다시 컴파일해야함(Static SQL)
    - 다른 환경, 다른 프로그래밍 언어 간의 호환성이 없음
- Dynamic SQL
    - 런타임에 SQL이 사용되므로 SQL 수정에 따른 재컴파일이 발생하지 않음
    - API로 동작하므로 다른 환경, 다른 프로그래밍 언어에서도 사용가능

Dynamic SQL의 동작 과정은 아래와 같습니다.

1. DB server에 연결하여 Session을 생성
2. DB server에 SQL 명령어를 전송
3. SQL 결과를 차례대로 프로그램 변수에 Fetch
4. DB server와 연결 종료하여 Session을 해제

대표적인 Dynamic SQL에는 ODBC와 JDBC가 있습니다. ODBC는 Microsoft 환경에서 C에 특화된 Dynamic SQL이고 이번시간에는 Java database connectivity인 JDBC에 대해 알아보도록 하겠습니다.

### JDBC

JDBC는 DBMS와 통신을 위한 Java API입니다.

 

**JDBC 사용법(Oracle 기준)**

1. JDBC 드라이버 로딩
    
    DBMS마다 제공하는 JDBC 드라이버를 로딩합니다. 
    
    ```java
    Class.forName("oracle.jdbc.driver.OracleDriver");
    ```
    
2. DB 연결
    
    DB server에 연결하여 Session을 생성합니다.
    
    ```java
    Connection conn = DriverManager.getConnection(
    	"jdbc:oracle:thin:@//localhost:1521/sepdb1"
    );
    ```
    
3. Statement 생성
    
    **Statement**
    
    Statement Class는 데이터 베이스 연결로부터 SQL문을 수행할 수 있도록 하는 클래스 입니다.
    
    ```java
    Statment stmt = conn.createStatement();
    ```
    
    Statment 클래스에서 SQL을 실행하는 메서드는 2가지가 있습니다.
    
    - executeQuery()
        
        SELECT 문을 사용할 때 사용
        
        ResultSet(SELECT의 결과)가 반환. 
        
        Cursor 형식으로 반환되어 .next()를 통해 다음 레코드를 참조
        
    - executeUpdate()
        
        DML 문을 사용할 때 반환
        
        처리된 데이터 수를 반환
        
    
    **PreparedStatement Class**
    
    PreparedStatement는 Statement Class와 달리 SQL 문을 미리 준비하여 선언한 후 변수 데이터를 ?로 표시하고 메서드를 통해 ?의 값을 설정하는 방식입니다.
    
    Statement보다 구조적이고 편리해 권장하는 방법입니다.
    
    ```java
    PreparedStatement query = conn.prepareStatement(
    		"select sid, sname, deptno from student where deptno = ?"
    )
    ```
    
4. Statement에 맞는 SQL 작성
    - Statement Class
        
        <p align="center"><img src="../../images/데이터베이스/4장 Dynamic SQL-Untitled.png"></p>
        
        Statement Class로 생성한 객체 stmt에 대하여 executeUpdate와 executeQuery를 실행합니다. executeQuery로 반환된 ResultSet를 next()를 통해 커서로 사용합니다.
        
        ResultSet의 속성을 참조할 때 속성의 이름으로 참조할 수도 있고 n번째 순서 값으로 참조할 수도 있습니다.
        
    - Prepared Statement Class
        
        <p align="center"><img src="../../images/데이터베이스/4장 Dynamic SQL-Untitled 1.png"></p>
        
        SQL문에서 필요한 변수 데이터를 ?로 표시한 문자열을 String 변수 sql에 저장한 후 Prepared Statement Class로 생성한 객체 pstmt에 sql 값으로 객체를 생성합니다.
        
        이후 setInt()를 통해 n번째 ?에 원하는 변수 값을 지정한 후 executeUpdate(), executeQuery()를 사용합니다. setInt()처럼 입력하는 데이터에 맞는 데이터 타입을 지정해야합니다.