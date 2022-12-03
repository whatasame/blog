# 11) make

make 시스템은 컴파일 자동화 시스템입니다. make를 활용하면 헤더 파일, 소스 파일, 목적 파일, 실행 파일의 관계를 체계적으로 관리할 수 있습니다. 또한 프로그램을 수정할 때 프로그램 전체를 재컴파일 하는 것이 아닌 필요한 파일만 다시 컴파일 하여 실행 파일을 재생성할 수도 있습니다.

### Makefile

실행 파일을 만들기 위해 필요한 파일들과 만드는 방법을 기술해놓은 파일입니다. make 시스템은 Makefile을 보고 파일 간의 관계를 파악하여 실행 파일을 생성합니다.

### make 사용법

```bash
$ make [-f 메이크파일]
```

-f 옵션을 통해 원하는 Makefile을 지정해줄 수 있습니다. 만약 옵션을 지정해주지 않고 make를 사용하면 기본 make 파일인 Makefile, makefile을 사용합니다.

### Makefile의 구성

Makefile은 기본적으로 아래와 같은 형식을 갖습니다.

```makefile
대상리스트: 의존리스트
		명령리스트 # Indent(\t)를 하지 않을 시 missing separator 오류 발생
```

예를 들어, 아래와 같은 계층 구조를 가진 프로그램이 있다고 생각해봅시다.

<p align="center"><img src="../../images/시스템프로그래밍및보안/11) make-Untitled.png"></p>

main이라는 실행파일을 만들기 위해 main.o, copy.o가 필요하고, main.o는 main.c, copy.h가 필요하며 copy.o는 copy.c copy.h가 필요합니다. 이런 계층 구조를 갖는 프로그램을 makefile로 표기하면 아래와 같습니다.

```makefile
main: main.o copy.o
		gcc -o main main.o copy.o
main.o: main.c copy.h
		gcc -c main.c
copy.o: copy.c copy.h
		gcc -c copy.c
```

make에서 목표 프로그램이 다수일 때 별도의 label을 지정해주지 않으면 가장 위에 정의되어있는 프로그램만 처리합니다. 즉, 위 파일에서는 main label부터 실행하는 것이죠.

### 수정된 파일만 컴파일

바로 위의 프로그램에서 copy.c를 수정하면 copy.c와 관련되어 있는 copy.o와 main만 영향을 받을 뿐 main.o는 영향을 받지 않습니다. make 시스템을 활용하면 관련있는 파일만 재컴파일만 하면 됩니다.

<p align="center"><img src="../../images/시스템프로그래밍및보안/11) make-Untitled 1.png"></p>

처음 make를 실행할 때 결과입니다.

<p align="center"><img src="../../images/시스템프로그래밍및보안/11) make-Untitled 2.png"></p>

아무것도 수정하지 않고 다시 make를 실행하면 make 대상 파일의 변화가 없어 아무것도 하지 않습니다.

<p align="center"><img src="../../images/시스템프로그래밍및보안/11) make-Untitled 3.png"></p>

touch 명령어로 copy.c의 수정 날짜(mtime)를 변경 후 make를 실행하면 copy.c와 관련된 파일만 재컴파일합니다.

### Label vs Target List

- Target List : 의존 리스트가 있는 것
    
    ```makefile
    main: main.o copy.o
    		gcc -o linkedlist.out
    ```
    
- Label : 의존 리스트가 없는 것
    
    ```makefile
    clean:
    		rm *.o
    ```
    

### 사용자 정의 Macro

makefile에 반복적으로 사용되는 내용을 Macro로 선언하여 재사용할 수 있습니다. 

Macro는 아래와 같이 선언하고 사용합니다.

```makefile
# 선언
TARGET=main
OBJS=main.o\ # 여러 행을 사용할 땐 \를 사용
		 copy.o

# 사용
${TARGET}: ${OBJS}
		gcc -o main 
```

- 선언 : =를 포함하는 하나의 문장
    - 여러 행을 사용할 땐 \를 사용
    - 중복된 정의는 마지막 정의를 사용
    - 정의되지 않은 매크로는 null 문자로 치환
    - 매크로 정의시 이전에 정의된 매크로를 사용 가능
        
        ```makefile
        OBJ1=main.o
        OBJ2=node.o
        OBJS=${OBJ1} ${OBJ2}
        ```
        
- 사용 : ${Macro}

### 사전 정의 매크로

한편, make 시스템 내부적으로 미리 정의되어 있는 매크로도 있습니다. 이러한 매크로는 사용자 정의 매크로로 사용할 수 없습니다.

사전 정의 매크로를 확인하는 명령어는 아래와 같습니다.

```bash
$ make -p
```

대표적인 사전 정의 매크로는 아래와 같습니다.

- CC : C 컴파일러를 지정한다. e.g. CC=gcc
- CFLAGS : 컴파일 옵션을 지정한다. e.g. CFLAGS=-c

### 자동 매크로(내부 매크로)

make 시스템에는 make -p로 확인할 수 없는 자동 매크로가 있습니다. 자동 매크로는 특별한 의미를 가진 매크로이며 내부적으로 정의되어 있어 의미의 수정이 불가능합니다.

자동 매크로는 아래와 같습니다.

- $@ : 현재 목표 파일 이름
    
    ```makefile
    OBJS=main.o sub1.o sub2.o
    maketest: ${OBJS}
    	gcc -o $@ ${OBJS} # gcc -o maketest ${OBJS}와 같습니다. 
    ```
    
- $* : 목표 파일 이름에서 확장자를 제외한 이름
    
    확장자 규칙에서 “$*.c”와 같이 사용합니다.
    
    ```makefile
    main.o: main.c io.h
    	# main.c에서 확장자를 제외한 이름인 main에 해당
    	gcc -c $*.c # gcc -c main.c와 같습니다.
    ```
    
- $< : 의존 관계가 있는 파일 중 첫 번째 파일의 이름
    
    ```makefile
    main.o: main.c io.h
    	gcc -c $< # gcc -c main.c와 같습니다.
    ```
    

### 확장자 규칙 매크로

make 시스템에서는 필요한 오브젝트 파일이 존재하지않으면 이를 생성하기 위해 .c 파일 혹은 .s 파일을 찾습니다. 이 과정에서 확장자 규칙에 의해 make는 파일들간의 확장자를 자동으로 인식해서 필요한 작업을 수행합니다.

만약 확장자 규칙에 따른 행동을 명시적으로 지정해주고싶으면 확장자 규칙 매크로를 사용하면 됩니다.

사용법 예시는 아래와 같습니다.

```makefile
.SUFFIXES: .c.o
.c.o:
		# 수행할 명령
```

위 makefile은 make 시스템이 필요한 .o을 발견하지 못할 경우 .c 파일을 찾아 .c.o label을 수행합니다. 

응용 예시는 아래와 같습니다.

```makefile
.SUFFIXES: .c .o
CC=gcc
CFLAGS=-c
TARGET=makeResult
OBJS = main.o copy.o

.c.o:
		${CC} ${CFLAGS} $< -o $@

${TARGET}: ${OBJS}
		${CC} -o $@ ${OBJS}

```

### 매크로 응용 예시

<p align="center"><img src="../../images/시스템프로그래밍및보안/11) make-Untitled 4.png"></p>

- 1번 : Makefile 정석
- 2번 : gcc -c main.c과 같이 Make 시스템이 이미 알고 있는 명령어를 생략
- 3번 : 확장자 규칙 매크로를 활용하여 Make 시스템이 이미 알고 있는 명령어를 명시적으로 지정

### gccmakedep

어떤 파일의 의존 관계를 자동으로 조사해서 Makefile의 뒷부분에 붙여주는 유틸리티입니다.

```bash
$ gcc -M *.c # gccmakedep과 같은 역할을 수행합니다.
```