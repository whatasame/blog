# #010 Implement MIPS CPU Basic

이번 글에선 MIPS CPU를 배우기 전에 알아야하는 것들에 대해 알아보도록 하겠습니다.

### CPU 성능을 결정 짓는 요소

CPU 성능을 결정 짓는 요소는 다음과 같습니다.

- 명령어 개수$^{Instrcution\ count}$
- 명령어 당 클럭수$^{Cycle\ Per\ Instruction}$와 클럭당 시간$^{Cycle\ time}$

명령어 개수는 명령어 구조$^{Instruction\ Structure\ Architecture}$와 컴파일러의 최적화에 의해 결정되고, 명령어당 클럭 수와 클럭당 시간은 CPU 구조에 의해 결정됩니다.

### 논리 설계 기초

컴퓨터의 데이터는 0과 1로 구성되어있고 이는 전압으로 표현할 수 있습니다. 0은 저전압으로, 1은 고전압으로 표현됩니다. 이때, 하나의 비트는 하나의 전선으로 표현합니다. 예를 들어 32bit 데이터를 사용하려면 32개의 선이 필요합니다.

논리회로는 두 종류로 구분할 수 있습니다.

- 조합 요소$^{Combinational\ element}$
    
    산술 연산을 하는 ALU나 데이터를 선택하는 Multiflex와 같이 데이터에 의해 동작
    
- 상태 요소$^{State\ elements}$
    
    정보를 저장
    

대부분의 연산은  상태로부터 데이터를 읽어 조합하여 연산한 후 다시 상태에 저장하는 식으로 이루어집니다.

<p align="center"><img src="../../images/컴퓨터구조/%23010%20Implement%20MIPS%20CPU%20Basic/Untitled.png"></p>

위 그림의 경우 Clcok cycle 동안 State 1에서 데이터를 읽어 연산을 한 후 State 2에 저장하는 것을 나타냅니다.

### 필수 논리 회로 장치

MIPS CPU를 이해하기 위해 필요한 기초 모듈이 있습니다.

- ALU : 덧셈, 뺄셈 같은 산술연산과 OR, AND, NOR 같은 논리연산을 계산하는 회로
- Multiflexer : 입력된 여러 비트 중 하나를 선택하여 출력 회로
- Latch, Flip-flop : 1비트를 저장하는 회로

### Split Memory Model

명령어와 데이터는 모두 메모리에 저장되어있습니다. 그런데 일반적으로 메모리에선 이 둘을 구분하여 저장하지 않습니다. 한편, 이 들을 구분하여 저장하는 메모리 구조도 있습니다. 이러한 구조를 Harvard Architecture라고 합니다.

- Von Neumann Architecture : CPU + 메모리
- Harvard Architecture : 메모리를 구분하여 사용
    - **Instruction memory**(Instruction cache, I$) : 메모리의 명령어(Text 영역)를 다루는 메모리
    - **Data memroy**(Data cache, D$) : 명령어를 제외한 데이터를 다루는 메모리

> CPU의 처리속도로 인한 병목 현상을 막기 위해 사용하는 아주 빠른 속도의 Memory를 Cache라고 합니다. 기호 $의 발음이 Cache와 같아 $로 표현하기도 합니다.
> 

### 사용되는 명령어들

MIPS ISA 중에서 일부 명령어$^{subset}$만 사용합니다. 

- 산술논리$^{aritmetic/logical}$: add, sub, and, or, slt
    - add
    - sub
    - and
    - or
    - slt
- 메모리 참조$^{memroy\ reference}$ : lw, sw
    - lw
    - sw
- 흐름 제어$^{control\ flow}$ : beq, j
    - beq
    - j

### 레지스터 파일 번호

<p align="center"><img src="../../images/컴퓨터구조/%23010%20Implement%20MIPS%20CPU%20Basic/Untitled%201.png"></p>

다음 글부터 MIPS CPU를 알아보도록 하겠습니다.