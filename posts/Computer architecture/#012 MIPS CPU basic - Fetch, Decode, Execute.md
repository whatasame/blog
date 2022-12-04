# #012 MIPS CPU basic - Fetch, Decode, Execute

이번 글에서는 MIPS의 CPU의 기초 단계인 Fetch, Decode, Execute에 대해 알아보도록 하겠습니다.

## MIPS CPU 구조

MIPS CPU의 구조를 먼저 보면 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled.png"></p>

MIPS CPU는 5개의 유닛(4개의 Sequential logic, 1개의 Combinational logic)으로 구성됩니다.

- Sequential logic (PC, Instruction Memory, Register File, Data Memory)
    
    값을 저장하고 있는 논리 회로 장치로서 저장하고 있는 값에 따라 내보내는 출력 값이 다름
    
    - PC$^{Program\ Counter}$ : 다음에 실행될 명령어의 주소를 가지고 있는 **레지스터**
    - IM$^{Instruction\ Memory}$ : 전체 명령어 정보를 가지고 있는 메모리 혹은 캐시
    - Register file : 레지스터의 집합체
    - DM$^{Data\ memory}$ : 명령어 정보를 제외한 모든 정보를 가지고 있는 메모리 혹은 캐시
- Combinational logic (ALU)
    
    입력 값을 처리하여 출력 값을 내보내는 논리회로 장치
    
    - ALU$^{Arithmetic\ and\ Logical\ Unit}$ : 덧셈, 뺄셈 같은 **산술연산**과 OR, AND, NOR 같은 **논리연산**을 계산하는 회로

## MIPS CPU에서 명령어

### 사용되는 명령어들

이번 글에서 사용되는 명령어는 MIPS ISA 중에서 일부 명령어$^{subset}$만 사용합니다. 

- 산술 논리$^{aritmetic/logical}$: add, sub, and, or, slt
- 메모리 참조$^{memroy\ reference}$ : lw, sw
- 흐름 제어$^{control\ flow}$ : beq, j

이것들의 동작 과정을 이해한다면 다른 명령어를 이해하는 것은 어렵지 않을 것입니다.

또한, ISA에서 명령어를 배울 땐 R type, I type, J type과 같이 구분하였지만 MIPS CPU를 이해하기 위해서는 위와 같이 명령어를 산술 논리, 메모리 참조, 흐름 제어로 구분하는 것이 좋습니다.

### 명령어 처리 과정

MIPS CPU에서 명령어를 처리하는 과정은 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%201.png"></p>

1. PC$^{Program Counter}$가 Instruction Memory에 접근하고 명령어를 가져온다.
2. 레지스터 번호를 이용하여 레지스터 파일에 접근, 해당 번호의 레지스터의 값을 읽는다.
3. 명령어를 처리한다.

명령어는 명령어 주소를 가지고 Instruction Memory에 접근하고 명령어 정보를 가져오지만, 레지스터는 레지스터 번호를 가지고 레지스터 파일에 접근하여 레지스터 값을 가져온다는 것에 주의해야합니다.

즉, 명령어는 명령어 주소가 필요하고, 레지스터는 레지스터 번호가 필요합니다.

MIPS CPU에서 사용되는 레지스터의 번호는 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%202.png"></p>

### ALU를 사용하는 명령어

MIPS ISA에서 j 명령어$^{jump}$를 제외한 모든 명령어는 ALU를 사용합니다.

- 산술 논리 → 덧셈과 같은 산술 연산에 사용
- 메모리 참조  → 접근할 메모리 주소를 계산하는 데 사용
- 흐름 제어  → Branch 여부와 Branch 주소를 계산

> beq와 같은 흐름 제어 명령어는 Branch 여부와 Branch 주소를 계산해야하므로 한 번의 사이클 안에서 ALU를 두 번 사용해야하는 문제점이 있습니다. 이를 해결하기 위해 Branch 주소 계산은 별도의 Adder를 두어 처리합니다.
> 

## 명령어 Fetch

명령어 처리 과정의 Fetch는 두 가지 작업을 처리합니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%203.png"></p>

- IM$^{Instruction\ Memory}$에서 PC$^{Program\ Counter}$에 해당하는 명령어 읽기
    
    IM에 접근하여 PC에 저장된 메모리 주소와 일치하는 주소의 명령어를 읽음
    
- PC의 값을 PC + 4로 업데이트
    
    Adder를 사용하여 PC 값을 PC + 4로 업데이트합니다. (32bit 환경이므로 4byte)
    

CPU는 Datapath와 Control로 이루어져있다고 했습니다. 일반적으로 데이터를 쓰고 읽을 땐 해당 논리 회로 모듈에 제어 신호를 보내 데이터를 읽고 쓰게합니다.

그러나, PC의 경우 매 Clock cycle마다 써지므로 PC에 값을 쓰는 것은 Write control 신호가 필요 없습니다.

또한, Instruction memory도 매 Clock cycle마다 읽히므로 IM의 값을 읽는 것은 Read control 신호가 필요 없습니다.  

즉, Fetch에서 일어나는 일은 매 Clock cycle마다 수행되므로 별도의 Control이 필요없습니다.

## 명령어 Decode

명령어 처리 과정의 Decode는 두 가지 작업을 처리합니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%204.png"></p>

- Fetch에서 읽은 명령어에서 opcode와 function field에 대한 bit를 Control Unit에 전달
    
    명령어를 bit 단위로 구분하여 opcode, function field 값을 구분합니다. 
    
    <p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%205.png"></p>
    
    다른 ISA일 경우 읽어야하는 bit 자리 수가 다를 것입니다. 그렇기 때문에 ISA가 CPU 구조를 결정한다고 말하는 것입니다. opcode가 상위 10bit라면 이에 맞게 wire의 수를 6개에서 10개로 늘려야할 것입니다.
    

- Fetch에서 읽은 명령어의 레지스터 번호에 맞는 레지스터 값을 레지스터 파일로부터 읽음
    
    위의 R type 명령어 그림에서 rs와 rt에 해당하는 비트를 읽습니다. 이후, 레지스터 파일에 접근하여 rs와 rt가 가진 번호와 같은 레지스터의 값을 읽어 내보냅니다.
    

이러한 과정은 매 Clock cycle마다 이루어지므로 Fetch처럼 별도의 Control 신호가 필요하지 않습니다.

## 명령어 Execute - 산술 논리

산술 연산 명령어(`add`, `sub`, `slt`, `and`, `or`)는 두 가지 작업을 처리합니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%206.png"></p>

- Decode에서 읽은 rs와 rt의 값을 ALU에서 연산
- ALU의 연산 결과 값을 레지스터 파일에 저장(쓰기)

지금까지 별도의 Control 신호가 없었던 Fetch와 Decode와 달리 **Execute는 Control 신호가 필요**합니다. 

- ALU control
    
    우선 ALU가 어떤 연산을 할지 제어해야합니다. 모든 R type은 opcode가 0이므로 function field를 통해 ALU의 연산을 결정합니다.
    
    <aside>
    💡 ALU는 다양한 산술논리연산을 할 수 있으므로 연산 종류를 결정하기 위해 ALU Control 신호가 필요합니다.
    
    </aside>
    

- RegWrite
    
    ALU 연산이 끝나면 연산 결과가 Register file로 돌아옵니다$^{feedback}$. ALU 연산 결과는 레지스터 파일의 Write Data 포트에, ALU 연산 결과가 저장될 레지스터 번호는 Write Addr에 해당합니다.
    
    RegWrite 신호가 있다면 Write Addr에 해당하는 레지스터에 Write data의 값을 씁니다.
    
    <aside>
    💡 레지스터 파일에 값을 쓰지 않는 경우(e.g. Store)도 있기 때문에 RegWrite 신호가 필요합니다.
    
    </aside>
    

## 명령어 Execute - 메모리 참조

메모리 참조 명령어(`lw`, `st`)는 세 가지 작업을 처리합니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%207.png"></p>

- 명령어의 16bit offset을 32bit offset으로 **부호 확장$^{sign\ extension}$**
- Base 주소과 offset 값을 더해 메모리 주소를 계산
- 명령어 실행
    - `load` : DM의 값을 Register file에 저장
    - `store` : Register file 값을 DM에 저장

아래 예시 명령어를 통해 처리 흐름을 짚어보겠습니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%208.png"></p>

- `lw`의 경우
    
    → 19번 레지스터에 저장된 메모리 주소에서 +8만큼 떨어진 메모리 주소의 값을 8번 레지스터에 저장
    
    1. Decode 단계에서 얻은 `rs`에 해당하는 레지스터($s3, 19)에서 베이스 주소 값을 읽습니다.
        
        e.g. $\tt{base\ address =\ }0x10fd0300$
        
    2. Decode 단계에서 얻은 `offset` 값(8)을 부호 확장합니다.
        
        e.g. $\tt{offset =\ }0x00000008$
        
    3. 베이스 주소 값과 ALU에서 부호확장한 `offset` 값을 더합니다.
    $\tt base\ addr + offset=target\ addr$
        
        e.g. $\tt0x10fd0300+0x00000008=0x10fd0308$
        
    4. DM에서 ALU 연산 값$^{Address}$에 해당하는 메모리 값을 읽습니다.
    5. DM에서 읽은 값$\tt^{레지스터파일의\ Write\ data}$을 `rt`에 해당하는 레지스터$\tt^{레지스터파일의\ Write\ Addr}$에 저장합니다.
    
- `sw`의 경우
    
    → 8번 레지스터의 값을 19번 레지스터에 저장된 메모리 주소에서 +32만큼 떨어진 메모리 주소에 저장
    
    1. Decode 단계에서 얻은 `rs`에 해당하는 레지스터($s3, 19)에서 베이스 주소 값을 읽습니다.
        
        e.g. $\tt{base\ address =\ }0x10fd0300$
        
    2. Decode 단계에서 얻은 `offset` 값(32)을 부호 확장합니다.
        
        e.g. $\tt{offset =\ }0x00000020$
        
    3. ALU에서 부호확장한 `offset` 값(32)과 베이스 주소 값을 더합니다.
    $\tt base\ addr + offset=target\ addr$
        
        e.g. $\tt0x10fd0300+0x00000020=0x10fd0320$
        
    4. `rt`에 해당하는 레지스터의 값$\tt^{DM의\ Write\ data}$을 DM에서 ALU 연산 값에 해당하는 메모리$\tt^{DM의\ Address}$에 저장합니다.

## 명령어 Execute - 흐름 제어

### Branch

`beq`와 같은 조건부 분기 conditional jump 명령어는 두 가지 작업을 처리합니다.

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%209.png"></p>

- 레지스터 파일에서 `rs`에 해당하는 레지스터의 값과 `rt`에 해당하는 레지스터의 값을 읽어 비교
- 부호 확장과 Word offset만큼 Shift left된 `offset`값과 `PC`와 더해 분기$^{branch}$할 주소$^{target\ address}$를 계산
    
    > Word offset은 Branch의 범위를 늘리기 위해 하위 2bit를 생략한 것을 복구하는 것을 말합니다. 32비트 환경에서 메모리의 주소는 4씩 증가하기 때문입니다.
    > 
    
    Branch에서 target address를 구할 때 사용되는 base address는 updated PC 값입니다.
    

beq를 기준으로 ALU에서 rs와 rt의 값을 비교한 후 동일하다면 update PC + offset을, 다르다면 update PC로 PC 값을 업데이트합니다. 두 값 중 하나를 선택하기 위해 Multiplexer가 필요합니다.

### jump

`j`는 두 가지 작업을 처리합니다.

> J type 명령어(`j`)는 **ALU를 사용하지 않습니다**. 또한 무조건 분기$^{unconditional\ branch}$이므로 **Register file에 접근도 하지 않습니다**.
> 

<p align="center"><img src="../../images/Computer architecture/%23012%20MIPS%20CPU%20basic%20-%20Fetch%2C%20Decode%2C%20Execute/Untitled%2010.png"></p>

- `26bit offset` 을 2bit left shift하여 `28bit offset`으로 확장
    
    → Branch 범위를 늘리기 위해 마지막 2 bit 00을 생략한 것을 복구(Word offset)
    
- 명령어의 하위 28bit와 PC의 상위 4bit를 합쳐 새로운 32bit PC값을 만듦

jump 또한 jump가 발생할 때 PC+4가 아닌 jump address로 PC를 업데이트해야하므로 PC+4와 jump address 두 값 중 하나를 선택하는 Multiplexer가 필요합니다.