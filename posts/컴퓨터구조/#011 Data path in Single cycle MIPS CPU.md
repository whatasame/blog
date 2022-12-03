# #011 Data path in Single cycle MIPS CPU

## MIPS CPU 구조

MIPS CPU의 구조를 먼저 보면 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled.png"></p>

MIPS CPU는 **5개의 유닛(PC, IM, Register file, ALU, DM)**으로 이루어져있습니다. 

- PC$^{Program\ Counter}$ : 다음에 실행될 명령어의 주소를 가지고 있는 **레지스터**

- IM$^{Instruction\ Memory}$ : 전체 명령어 정보를 가지고 있는 메모리 혹은 캐시
- Register file : 레지스터의 집합체
- ALU$^{Arithmetic\ and\ Logical\ Unit}$ : 덧셈, 뺄셈 같은 **산술연산**과 OR, AND, NOR 같은 **논리연산**을 계산하는 회로
- DM$^{Data\ memory}$ : 명령어 정보를 제외한 모든 정보를 가지고 있는 메모리 혹은 캐시

> Register file은 Register의 집합체를 통칭하는 말이며, SRAM은 Static RAM의 줄임말입니다. Register라는 말은 보통 D-FF(D Filp-Flop)과 같은 간단한 로직 형태의 저장 장치를 의미하며, 어떤 소자의 형태를 의미하지는 않습니다. 따라서, Register file은 D-FF의 합쳐진 형태로 나타낼 수도, SRAM으로 나타낼 수도, 혹은 특별한 형태의 소자를 사용할 수도 있습니다. ([http://babyworm.net/archives/312](http://babyworm.net/archives/312))
> 

각 유닛들은 2가지로 구분할 수 있습니다.

- Sequential logic : 상태를 저장하고 있는 유닛 → PC, IM, Register file, DM
    
    예를 들어, PC는 다음에 실행할 명령어 정보를 가지고 있고, IM은 전체 명령어 정보를, DM은 데이터 정보를, Register file은 레지스터의 정보를 가지고 잇습니다.
    
- Combinational logic : 데이터를 계산하는 유닛 → ALU
    
    예를 들어, ALU는 Register file의 데이터를 계산한 값을 반환합니다.
    

### Clock

위에서 언급한 5개의 유닛은 Clock이라는 전기 신호에 맞춰 작동합니다. 1 Clock 마다 각각의 유닛이 작동합니다. 따라서, PC → IM → Register file → ALU → DM에 필요한 클럭 수는 5입니다.

나중에 배울 파이프라인 CPU에서는 각각의 유닛이 자기 차례를 기다리지 않고 동시에 작동하게 됩니다.

clocking methodologies?

## MIPS CPU에서 명령어

### 사용되는 명령어들

이번 글에서 사용되는 명령어는 MIPS ISA 중에서 일부 명령어$^{subset}$만 사용합니다. 

- 산술논리$^{aritmetic/logical}$: add, sub, and, or, slt
- 메모리 참조$^{memroy\ reference}$ : lw, sw
- 흐름 제어$^{control\ flow}$ : beq, j

이것들의 동작 과정을 이해한다면 다른 명령어를 이해하는 것은 어렵지 않을 것입니다.

### 명령어 처리 과정

MIPS CPU에서  명령어를 처리하는 과정을 짧게 설명하면 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%201.png"></p>

1. PC$^{Program\ Counter}$가 가리키는 명령어를 IM$^{Instruction Memory}$로부터 가져온다$^{fetch}$.
2. 명령어를 해석하고$^{decode}$ 레지스터 파일에 접근하여 명령어에 필요한 레지스터 번호에 맞는 레지스터의 값을 읽는다.
3. 명령어 종류에 맞는 연산을 한다$^{execute}$. (아래의 과정 중 일부를 하지 않는 명령어도 있음)
    1. ALU를 사용하여 계산
    2. DM$^{Data\ Memory}$에 접근하여 값을 불러오거나 저장
    3. 다음 명령어를 실행(PC = PC + 4)하거나 이동(PC = Branch target address)

### ALU를 사용하는 명령어

MIPS ISA에서 j 명령어$^{jump}$를 제외한 모든 명령어는 ALU를 사용합니다.

- 산술 논리 → 덧셈과 같은 산술 연산에 사용
- 메모리 접근  → 접근할 메모리 주소를 계산하는 데 사용
- 흐름 제어  → Branch 여부와 Branch 주소를 계산

> beq와 같은 흐름 제어 명령어는 beq 명령어를 계산하는 것과 branch할 주소를 계산하는 것으로 총 2번의 계산이 필요합니다. 그런데 하나의 clock에서는 ALU를 한 번만 사용할 수 있습니다. ALU를 2개 구현해야할까요? 사실, branch할 주소를 계산하는 것은 ALU로 하기엔 너무 간단한 연산이므로 별도의 adder를 두어 계산합니다.
> 

> j 명령어는 ALU가 필요없습니다. 이는 나중에 알아보도록 하겠습니다.
> 

## 명령어 Fetch

명령어 처리 과정에서 Fetch는 두 가지 작업을 해야합니다.

- IM$^{Instruction\ Memory}$에서 PC$^{Program\ Counter}$에 해당하는 명령어 읽기
- PC의 값을 PC + 4로 업데이트

위 작업을 수행하기 위해 필요한 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%202.png"></p>

PC의 값을 PC+4로 업데이트하기 위해 Adder가 필요합니다. 이때 PC + 4의 값을 PC에 쓰는 것에 대한 Control 신호가 필요할까요? PC는 매 Clock cycle마다 업데이트됩니다. 따라서 별도의 쓰기 신호를 줄 필요가 없습니다.

또한, PC 값으로 IM을 읽는 것에 대한 Control 신호가 필요할까요? IM은 매 Clock cycle마다 읽혀집니다. 따라서 별도의 읽기 신호를 줄 필요가 없습니다.

즉, Fetch에서 일어나는 일은 매 Clock cycle마다 수행되므로 별도의 Control이 필요없습니다.

## 명령어 Decode

명령어 처리 과정에서 Decode는 두 가지 작업을 해야합니다.

- Fetch에서 읽은 명령어에서 opcode와 functionfield에 대한 bit를 Control Unit에 전달
- Fetch에서 읽은 명령어에서 필요한 Register를 Register file에서 값을 가져옴

위 작업을 수행하기 위해 필요한 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%203.png"></p>

우선, Fetch 단계에서 가져온 Instruction을 Bit 단위로 구분하여 opcode, function field 값을 Control Unit에 전달합니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%204.png"></p>

- opcode : 31bit ~ 26bit
- function field : 5bit ~ 0bit

이후, Fetch 단계에서 가져온 Instruction에서 rs와 rt에 해당하는 레지스터를 레지스터 파일에서 가져옵니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%205.png"></p>

이때, 레지스터 파일에서 해당 레지스터를 찾는 주소는 레지스터의 번호가 됩니다. 예를 들어, 명령어의 rs에 $s1에 해당하는 17이 적혀있을 경우 레지스터 파일에서 17번째 레지스터 값을 읽습니다.

즉, **레지스터 번호는 레지스터 파일의 주소와 같습니다**.

## 명령어 Execute - R type

R type 명령어(`add`, `sub`, `slt`, `and`, `or`)는 두 가지 작업을 해야합니다.

- rs와 rt에 있는 값에 대해 연산을 수행함
- 연산의 결과를 Register file에서 rd에 해당하는 레지스터에 저장함

위 작업을 수행하기 위해 필요한 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%206.png"></p>

별도의 Control 신호가 없었던 Fetch와 Decode와 달리 **Execute는 Control 신호가 필요**합니다. 

- ALU control
- RegWrite

우선 ALU가 어떤 연산을 할지 제어해야합니다. 모든 R type은 opcode가 0이므로 function field를 통해 ALU의 연산을 결정합니다. 즉, **ALU control 신호는 function field에 따라 결정**됩니다.

<aside>
💡 ALU는 다양한 산술논리연산을 할 수 있으므로 어떤 연산을 할 지 결정하기 위해 ALU Control 신호가 필요하다고 생각하면됩니다.

</aside>

ALU 연산이 끝나면 연산 결과가 Register file로 돌아옵니다$^{feedback}$. 연산 결과는 Regstier file의 Write data에, 결과가 저장될 Register는 Write addr에 해당합니다. 이후 RegWrite 신호를 통해 Write Data가 Write Addr에 써집니다. R type 명령어는 명령어 결과가 레지스터에 저장되지만 beq와 같은 명령어는 레지스터에 쓸 일이 없으므로 별도의 RegWrite 신호를 관리하는 것입니다. 즉, **RegWrite는 명령어 종류에 따라 결정**됩니다.

<aside>
💡 Register file은 모든 Clock cycle마다 써지는 것이 아니기 때문에 RegWrite 신호가 필요하다고 생각하면 됩니다.

</aside>

### R type - slt

한편, `slt`는 조금 다르게 작동합니다. 

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%207.png"></p>

`slt`는 rs($s0)이 rt($s1)보다 작은 경우 rd($t0)을 1로 변경하는 명령어입니다.

## 명령어 Execute - I type

### Load, Store

I type 명령어(`lw`, `st`)는 세 가지 작업을 해야합니다.

- 명령어의 `16bit offset`을 `32bit offset`으로 **부호 확장$^{sign\ extension}$**
- Base 주소과 offset 값을 더해 메모리 주소를 계산
- 명령어 실행
    - `load` : DM의 값을 Register file에 저장
    - `store` : Register file 값을 DM에 저장

위 작업을 수행하기 위해 필요한 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%208.png"></p>

아래 예시 명령어를 통해 처리 흐름을 짚어보겠습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%209.png"></p>

- `lw`의 경우
    1. Decode 단계에서 얻은 `rs`에 해당하는 레지스터($s3, 19)에서 베이스 주소 값을 읽습니다.
    2. Decode 단계에서 얻은 `offset` 값(8)을 부호 확장합니다.
    3. ALU에서 부호확장한 `offset` 값(8)과 베이스 주소 값을 더합니다.
    $\tt base\ addr + offset=target\ addr$$\tt0x10fd0300+0x00000008=0x10fd0308$
    4. DM에서 ALU 연산 값$^{Address}$에 해당하는 메모리 값을 읽습니다.
    5.  **DM에서 읽은 값$^{Write\ data}$을 `rd`에 해당하는 레지스터$^{Write\ Addr}$에 저장**합니다.
    
- `sw`의 경우
    1. Decode 단계에서 얻은 `rs`에 해당하는 레지스터($s3, 19)에서 베이스 주소 값을 읽습니다.
    2. Decode 단계에서 얻은 `offset` 값(8)을 부호 확장합니다.
    3. ALU에서 부호확장한 `offset` 값(8)과 베이스 주소 값을 더합니다.
    $\tt base\ addr + offset=target\ addr$$\tt0x10fd0300+0x00000008=0x10fd0308$
    4. `rd`에 해당하는 레지스터의 값$^{Read\ data\ 2,\ Write\ data}$을 DM에서 ALU 연산 값$^{Address}$에 해당하는 메모리에 저장합니다.

요약하면 `load` 은 DM의 값을 Register file에 저장하고, `store` 는 Register file 값을 DM에 저장합니다.

### Branch

같은 I type이지만 Branch 명령어는 lw와 sw과 달리 DM에 접근하지 않습니다. 또한, `rs`와 `rt`는 비교를 위한 값이지 Base 주소가 아닙니다. **Branch에서 Base 주소는 PC 값이 됩니다.**

Branch 명령어 (`beq`)는 세 가지 작업을 해야합니다.

- 명령어의 `16bit offset`을 `32bit offset`으로 **부호 확장$^{sign\ extension}$**
- `16bit offset`을 2bit left shift
    
    → Branch 범위를 늘리기 위해 마지막 2 bit 00을 생략한 것을 복구(Branch 글 참고)
    
- `PC 값`$^{base\ addr}$과 `offset`을 Adder에서 더함

<aside>
💡 이때, PC 값은 PC + 4로 업데이트된 PC 값입니다. 왜냐하면 beq 명령어를 Fetch할 때 PC 값은 이미 업데이트 되어있기 때문입니다.

</aside>

위 작업을 수행하기 위해 필요한 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%2010.png"></p>

그림 좌측에 회색 처리되어 있는 부분이 PC가 업데이트 되어있다는 것을 의미합니다.

위 그림은 Branch를 할 때만 고려합니다. `beq`에서 `rs`와 `rt`가 달라 Branch 하지 않는 경우는 뒤에서 알아보도록 합니다.

## 명령어 Execute - J type

J type 명령어(`j`)는 **ALU를 사용하지 않습니다**. 또한 무조건 분기$^{unconditional\ branch}$이므로 **Register file에 접근도 하지 않습니다**.

J type 명령어(`j`)는 두 가지 작업을 해야합니다. 

- `26bit offset` 을 2bit left shift하여 `28bit offset`으로 확장
    
    → Branch 범위를 늘리기 위해 마지막 2 bit 00을 생략한 것을 복구(Branch 글 참고)
    
    → 명령어(32bit)에서 shift하는 것이므로 별도의 확장$^{extension}$이 필요없음
    
- 명령어의 하위 28bit와 PC의 상위 4bit를 합쳐 새로운 32bit PC값을 만듦

위 작업을 수행하기 위해 필요한 논리 회로는 아래와 같습니다.

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%2011.png"></p>

여기선 Jump할 주소를 계산하는 방법에 대해서만 고려합니다. PC가 Jump할 주소로 업데이트되는 것은 뒤에서 알아보도록합니다.

## 모듈 합치기

지금까지 배운 Fetch, Decode, Execute를 할 수 있는 각 모듈을 하나로 합쳐보겠습니다.

모듈을 합치기 위해 다음을 고려해야합니다.

- Mux

<p align="center"><img src="../../images/컴퓨터구조/%23011%20Data%20path%20in%20Single%20cycle%20MIPS%20CPU/Untitled%2012.png"></p>

이번 글에서는 간단한 MIPS CPU의 Single cycle를 위한 Data path와 간단한 Control에 대해 알아보았습니다. 다음 글에서는 제대로된 Control을 추가하여 완전한 MIPS CPU를 만들어보겠습니다.