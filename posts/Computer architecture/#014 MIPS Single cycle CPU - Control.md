# #014 MIPS Single cycle CPU - Control

이번 글에서는 지난 글에서 만든 Single cycle CPU에 Control을 추가하여 완전한 Single cycle CPU를 만들어 보겠습니다.

## MIPS ISA 분석

이제 ISA가 CPU 구조를 결정한다는 것을 이해할 수 있을 것입니다.

예를 들어, 명령어의 opcode가 6bit에서 10bit가 될 경우 Decode 단계에서 Instruction memory와 Control Unit이 연결된 선이 6개에서 10개로 늘어나야합니다.

<p align="center"><img src="../../images/Computer architecture/%23014%20MIPS%20Single%20cycle%20CPU%20-%20Control/Untitled.png"></p>

다행히도 MIPS ISA는 명령어가 3가지뿐이고 공통된 범위를 가집니다. 

예를들어, MIPS ISA에서 상위 6비트는 항상 opcode 정보를 가지고 있습니다.

## 제어 신호를 포함한 Single cycle CPU 설계

이러한 MIPS ISA를 바탕으로 Single cycle CPU의 제어 신호를 설계해보겠습니다.

### RegDst, ALU Control

<p align="center"><img src="../../images/Computer architecture/%23014%20MIPS%20Single%20cycle%20CPU%20-%20Control/Untitled%201.png"></p>

명령어 전체(31~0)은 각각 rs(25~21), rd(20~16), rt(15~11), offset(15~0), funct(5~0) 등으로 나뉘어 모듈에 전달됩니다.

- rs : 산술 논리 명령어에선 피연산자, 메모리 참조 명령어에서는 베이스 주소에 해당
- rt : 산술 논리 명령어에선 피연산자, 메모리 참조 명령어에서는 Load 된 데이터가 써지는 레지스터 혹은 Store할 데이터가 위치한 레지스터에 해당
- rd : 산술 논리 명령어에서 ALU 연산 결과 값이 저장되는 레지스터에 해당
- offset: 메모리 참조 명령어에서 베이스 주소로부터 offset만큼 떨어진 target address를 구하는데 사용되거나 흐름 제어 명령어에서 PC + 4 + offset에 사용됨
- funct : ALU 연산의 종류를 결정하는 값

`RegDst 제어 신호`는 산술 논리 명령어의 rd와 메모리 참조 명령어에서 rt의 위치를 결정하는데 사용됩니다.

산술 논리 명령어의 경우 1의 신호를 보내 rd 값에 해당하는 레지스터 번호가 Write Addr에 저장되도록 합니다.

Load의 경우 0의 신호를 보내 Write Addr로 rt 값에 해당하는 레지스터 번호가 저장됩니다.

> Store의 경우 제어 신호에 상관없이 Read Addr2에 rt 값에 해당하는 레지스터 번호가 저장됩니다.
> 

ALU 연산의 종류를 결정하는 `ALUOp 제어 신호`의 경우 모든 산술 논리 명령어의 opcode는 0이기 때문에 funct에 해당하는 5~0 비트의 정보를 읽고 ALU 연산 종류를 결정합니다.

### Control unit

지금까지 배운 모든 제어 신호는 Control unit이라는 논리 회로 모듈에 의해 관리됩니다. Decode 단계에서 명령어의 opcode를 Control unit에 전달했던 것이 기억날 것입니다. Control unit은 입력받은 opcode를 분석하고 해당 명령어가 필요로하는 제어 신호를 전달합니다.

Control unit이 포함된 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23014%20MIPS%20Single%20cycle%20CPU%20-%20Control/Untitled%202.png"></p>

매우 복잡해보이지만 놀랍게도 이전 글에서 모든 신호를 다뤘습니다. 

- ALUOp : 현재 명령어의 종류
- Branch : 현재 명령어가 Branch임을 나타냄
- PCSrc
    - 0 : PC + 4 값을 PC에 업데이트 (Branch 하지 않음)
    - 1 : PC + 4 + offset 값을 PC에 업데이트 (Branch)
- MemRead : Load 명령어가 Data memory의 값을 읽을 때 사용
- MemWrite : Store 명령어가 레지스터의 값을 Data memory에 쓸 때 사용
- MemtoReg
    - 1 : Load 명령어가 Data memory에서 읽은 값을 레지스터에 쓸 때 사용
    - 0 : 산술 논리 명령어의 ALU 연산 값을 레지스터에 쓸 때 사용
- ALUSrc
    - 0 : 산술 논리 명령어일 경우 ALU 피연산자에 rt에 해당하는 레지스터의 값을 선택
    - 1 : 메모리 참조 명령일 경우 ALU 피연산자에 offset 값을 선택
- ALU control : 산술 논리 명령어의 종류에 따라 ALU 연산을 결정 (e.g. add, sub, and, or, slt)
    
    → jump를 제외한 모든 명령어는 ALU Control 신호가 필요함
    
    > 산술 논리 명령어는 당연히 ALU를 사용하고, 메모리 참조 명령어는 target address를 구할 때 사용합니다. Branch 명령어는 비교 연산을 할 때 ALU를 사용하고 Branch 주소를 구할 땐 Adder를 사용합니다. 또, Branch 명령어는 ALU의 zero를 사용할 수 있습니다.
    > 
- RegDst
    - 0 : Load 명령어일 때 Data memory로부터 읽은 값을 저장할 레지스터 번호 rt를 선택함
    - 1 : 산술 논리 명령어일 때 ALU 연산 결과 값을 저장할 레지스터 번호 rd를 선택함
- RegWrite
    - Load 명령어일 때, Data memory로부터 읽은 값을 rt 레지스터에 쓸 수 있도록 신호를 보냄
    - 산술 논리 명령어일 때, ALU 연산 결과 값을 rd 레지스터에 쓸 수 있도록 신호를 보냄
    

### Jump

마지막으로 흐름 제어 명령어 `jump`를 추가한 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23014%20MIPS%20Single%20cycle%20CPU%20-%20Control/Untitled%203.png"></p>

jump는 unconditional branch로서 무조건 분기하는 명령어이므로 항상 PC 값을 jump 주소로 업데이트하게 됩니다.

이제 우리는 거의 완전한 Single cycle CPU를 완성했습니다!!!

> 거의 완전하다고 한 이유는 optional한 모듈은 다루지 않았기 때문입니다.
>