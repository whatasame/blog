# #017 MIPS CPU Pipelining

이번 글에서는 MIPS CPU를 파이프라인화 하는 방법에 대해 알아보도록 하겠습니다.

## MIPS라서 다행이다

MIPS CPU는 다른 ISA보다 파이프라인하기가 쉽습니다. 바로 MIPS의 특징 덕분입니다.

### MIPS의 특징

- 모든 명령어의 길이가 32비트로 고정되어있다.
- 명령어의 종류가 3가지로 한정되어있다.
- 메모리 접근은 Load와 Store 명령어만 가능하다
- 명령어에서 쓰기는 단 한 번 일어난다.
- 피연산자가 정렬되어있어 Data memory에 한 번만 접근하면 된다.

## 파이프라인 레지스터

파이프라인은 각 단계별로 명령어를 처리하기 때문에 단계에서 단계로 정보를 넘겨주는 모듈이 필요합니다.

이를 파이프라인 레지스터 혹은 State register, Bar라고 부릅니다.

<p align="center"><img src="../../images/Computer architecture/%23017%20MIPS%20CPU%20Pipelining/Untitled.png"></p>

위 그림에서 노란색 Bar가 바로 State register입니다.

### 특징

- State register는 State 사이에 위치하여 State 간의 정보를 저장하고 전달한다.
- State register는 정보를 저장하고 있는 모듈이므로 Sequence logic에 해당한다.
    
    → 따라서, System clock을 연결하여 Clock에 의해 작동된다
    
- PC도 WB/IF의 State register로 취급된다
    - IF가 필요한 정보를 PC에서 전달하기 때문에 State에 필요한 정보를 주는 레지스터이므로 State register이다.
- 다른 State register와 달리 PC는 프로그래머가 읽고 쓸 수 있는 모듈이다
    
    > Programmer’s model은 프로그래머가 읽고 쓸 수 있는 모듈로서 PC나 레지스터 파일이 이에 해당한다.
    > 
    

## 파이프라인 Hazard

파이프라인 CPU는 각 모듈이 사이클마다 처리합니다. 그런데, CPU가 명령어를 처리할 때 이미 처리한 데이터가 필요한 경우가 있습니다.

<p align="center"><img src="../../images/Computer architecture/%23017%20MIPS%20CPU%20Pipelining/Untitled%201.png"></p>

위 그림의 초록색은 Load 명령어를 수행할 때 Data memory로부터 읽은 데이터를 저장할 레지스터의 정보가 나중에 사용되는 상황을 말합니다.

또, 위의 보라색은 Branch 명령어에서 Update PC 값을 PC에 저장하는 상황을 말합니다.

마지막으로 아래 보라색은 ALU 연산의 결과 값이 rd에 저장되는 상황을 말합니다.

이처럼 이미 처리한 데이터를 나중에 다시 사용해야하는 문제를 파이프라인 Hazard라고 합니다.

아래의 보라색 Hazard를 Data hazard, 위의 보라색 Hazard를 Control hazard라고 합니다.

이번 글에선 파이프라인 Hazard에 대해선 소개만 하고 다음 글에서 이야기하도록 하겠습니다.

## 파이프라인 제어 신호

파이프라인 레지스터가 각 단계별로 필요한 데이터를 저장하고 전달한다면 각 단계별로 필요한 제어 신호를 저장하는 레지스터도 별도로 필요합니다.

이러한 레지스터의 이름은 별도론 없지만 해당 레지스터는 각 단계별로 필요한 제어 신호를 저장하고 있다가 각 단계에서 필요한 제어신호를 전달합니다.

<p align="center"><img src="../../images/Computer architecture/%23017%20MIPS%20CPU%20Pipelining/Untitled%202.png"></p>

처음 ID 단계에서 Control Unit이 EX, MEM, WB에 필요한 제어 신호를 ID/EX 레지스터에 저장해서 보냅니다.

이후, EX 단계에 필요한 제어 신호를 보낸 후 남은 제어 신호를 EX/MEM 레지스터로 전달합니다. 이런 식으로 WB 단계에 필요한 제어신호까지 레지스터에 담아 전달합니다.

### Single cycle CPU와 비교

<p align="center"><img src="../../images/Computer architecture/%23017%20MIPS%20CPU%20Pipelining/Untitled%203.png"></p>

이에 반해 Single cycle CPU에서는 모든 단계가 한 번에 이루어지기 때문에 Control Unit이 바로 각 모듈에 신호를 전달하는 것을 볼 수 있습니다.

### RegDst의 위치

또한, rt와 rd를 정하는 MUX 또한 ID에서 EXE 단계로 이동한 것을 알 수 있는데요. 이는 MUX 모듈로 인한 지연을 막기 위해 ALU가 있는 EXE 단계로 옮긴 것입니다.

### 각 단계별로 필요한 제어 신호

<p align="center"><img src="../../images/Computer architecture/%23017%20MIPS%20CPU%20Pipelining/Untitled%204.png"></p>

위 그림은 실제로 제어 신호 레지스터에 저장되는 값을 나타낸 표입니다.

## 파이프라인 정리

### 특징

- Throughput은 증가하나 Latency는 줄어들지 않는다.
- 파이프라인함으로써 Clock cycle time이 가장 느린 명령어가 아닌 가장 느린 단계로 결정된다

### 장점

- Clock cycle time이 짧아져 Clock rate가 높아진다
- 파이프라인이 꽉 차게 되면 한 사이클마다 명령어가 처리되므로 CPI가 1이 된다
- CPU 모듈이 서로 다른 Clock cycle에서 한 번 이상 사용된다

### 단점

- 파이프라인 레지스터 등 별도의 모듈이 필요하다
- 제어가 힘들다
- 파이프라인 Hazard 등 추가로 고려할 것 발생한다.