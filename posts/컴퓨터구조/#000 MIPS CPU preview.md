# #000 MIPS CPU preview

이번 글에선 MIPS CPU를 배우기 전에 알아야하는 것들에 대해 알아보도록 하겠습니다.

## CPU 성능을 결정 짓는 요소

CPU 성능을 결정 짓는 요소는 다음과 같습니다.

- 명령어 개수$^{Instrcution\ count}$
    
    하나의 동작에 필요한 명령어의 개수
    
    동일한 동작에 대해 CISC는 IC가 작고, RISC는 IC가 큼
    
    ISA$^{Instruction\ Structure\ Architecture}$와 컴파일러의 최적화에 의해 결정
    
- 명령어 당 클럭수$^{Cycle\ Per\ Instruction}$
    
    명령어 하나를 실행하는데 있어 필요한 Clock cycle의 수
    
    CISC는 IC가 작은 대신 CPI가 높고, RISC는 IC가 큰 대신 CPI가 낮음
    
    CPU 구조에 의해 결정됨
    
- 클럭당 시간$^{Cycle\ time}$
    
    Clock cycle 하나에 필요한 시간
    
    CPI가 작더라도 Cycle time이 길면 성능이 나쁨
    
    CPU 구조에 의해 결정됨
    

## 논리 회로

### 논리회로 기초

컴퓨터의 데이터는 0과 1로 구성되어있고 이는 전압으로 표현할 수 있습니다. 0은 저전압으로, 1은 고전압으로 표현됩니다. 

하나의 비트는 하나의 전선으로 표현합니다. 예를 들어 32bit 데이터를 사용하려면 32개의 선이 필요합니다.

논리 회로 장치들은 두 종류로 구분할 수 있습니다.

- 조합 요소$^{Combinational\ element}$
    
    입력 값의 연산을 하는 요소
    
    e.g. ALU, Multiflex
    
- 상태 요소$^{State\ elements}$
    
    현재 저장하고 있는 값에 따라 입력 값에 따른 출력 결과가 다른 요소
    
    e.g. 레지스터 파일(플립플롭으로 구성)
    

### 논리 회로 장치

MIPS CPU를 이해하기 위해 알아야할 논리 회로 장치들은 다음과 같습니다.

- ALU : 덧셈, 뺄셈 같은 산술연산과 OR, AND, NOR 같은 논리연산을 계산하는 회로
- Multiflexer : 입력된 여러 비트 중 하나를 선택하여 출력하는 회로
- Latch, Flip-flop : 1비트를 저장하는 회로

### Clock methodology

Clock methodology는 논리 회로에서 데이터를 읽고 쓰는 때를 결정하는 방법입니다. Clock methodology는 2가지로 구분할 수 있습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23000%20MIPS%20CPU%20preview/Untitled.png"></p>

- Level triggering
    
    전압이 HIGH일 때, 혹은 LOW 일 때 데이터를 읽고 씀
    
    e.g. Latch
    
- Edge triggering
    
    전압이 오를 때(Rising edge), 혹은 내려갈 때(Falling edge) 데이터를 읽고 씀
    
    e.g. Filp-flop
    

Edge triggering을 따르는 환경에서 데이터를 읽고 쓰는 과정은 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23000%20MIPS%20CPU%20preview/Untitled%201.png"></p>

위 그림은 Falling edge일 때, State element에서 데이터를 읽고 Combinational logic에서 데이터를 처리한 후 다시 Falling edge일 때 State element에 데이터를 쓰는 과정을 나타냅니다.

이러한 작업은 1개의 Clock cycle 동안 진행됩니다.

## Split Memory Model

명령어와 데이터는 모두 메모리에 저장되어있습니다. 일반적으로 메모리에 이것들이 저장되면 이들을 구분하지 않습니다. 

그러나 이것들을 구분하여 저장하는 메모리 구조도 있습니다. Harvard Architecture라고 합니다.

- Von Neumann Architecture : CPU + 메모리
- Harvard Architecture : 메모리를 구분하여 사용
    - **Instruction memory**(Instruction cache, I$) : 메모리의 명령어(Text 영역)를 다루는 메모리
    - **Data memroy**(Data cache, D$) : 명령어를 제외한 데이터를 다루는 메모리

> CPU의 처리속도로 인한 병목 현상을 막기 위해 사용하는 아주 빠른 속도의 Memory를 Cache라고 합니다. 기호 $의 발음이 Cache와 같아 $로 표현하기도 합니다.
>