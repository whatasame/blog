# #016 MIPS CPU Pipeline basic

이번 시간부터는 지난 글에서 완성한 MIPS Single cycle CPU를 파이프라인화 하는 것에 대해 알아보도록 하겠습니다.

## Critical path

Single cycle에서 각 단계를 처리하는 시간을 구하면 아래와 같습니다.

- Instruction memory 접근 → 4ns
- 레지스터파일 접근 → 2ns
- ALU 연산 → 1ns
- Data memory 접근 → 4ns
- Write back → 1ns

그런데 명령어에 따라 5단계를 모두 거치는 것이 있는가 하면, 그렇지 않은 명령어도 있습니다. 

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled.png"></p>

위 표를 보면 Load 명령어가 모든 단계를 거치기 때문에 가장 오래 걸린다는 것을 알 수 있습니다.

지금까지 우리가 설계한 Single cycle CPU는 하나의 Clock cycle에서 모든 단계를 거칩니다. 명령어가 5단계가 필요하지 않더라도 말이죠. 즉, Clock cycle time은 load의 시간보다 짧을 수 없습니다.

예를 들어, Clock cycle time을 10으로 잡는다면 12가 필요한 load 명령어를 절대로 처리할 수 없을 겁니다.

위의 load와 같은 것을 Critical path라고 합니다. 

Critical path는 어떤 작업을 끝내기 위한 가장 짧은 경로입니다. 

즉, Clock cycle time을 Critical path인 load보다 짧게 설정할 수 없다는 것이죠.

## Single cycle의 장단점

### 단점

바로 위에서 알아봤듯이, Critical path로 인해 더 빨리 처리할 수 있는 명령어도 기다려야합니다.

다시 말해, 낭비$^{waste}$가 발생하는 것이죠

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled%201.png"></p>

Critical path의 Load의 시간으로 Clock cycle이 정해지므로 DM에 접근하지 않는 ALU 연산은 남는 시간이 발생합니다.

### 장점

그래도 Singl cycle은 뒤에서 배울 파이프라인에 비해 이해하기도 쉽고, 구현하기도 쉽다는 장점이 있습니다.

## 파이프라인

Single cycle을 더 빠르게 위해 고안된 개념이 바로 파이프라인입니다.

파이프라인은 하나의 Clock cycle에 모든 단계를 한 번에 수행하지 않고 Clock cycle마다 하나의 단계를 수행하는 것을 말합니다.

> 참고로 Superscalar라는 개념도 있는데, 이는 명령어를 한 번에 여러 개 가져와서 처리하는 것을 말합니다. 이를 위해 복수 개의 모듈이 필요합니다. (e.g. 4개 명령어 → ALU 4개)
> 

### 예시

Single cycle에서는 하나의 명령어가 한 번에 처리되므로 처리된 모듈은 명령어가 끝날 때까지 아무것도 하지 않습니다.

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled%202.png"></p>

위 그림은 Single cycle CPU를 빨래로 비유한 그림입니다.

4개의 빨래를 하는데 총 8시간이 걸렸습니다.

파이프라인 CPU에서는 하나의 명령어가 단계별로 처리되므로 처리된 모듈은 다음 명령어가 오면 바로 처리합니다.

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled%203.png"></p>

위 그림은 파이프라인 CPU를 빨래로 비유한 그림입니다.

4개의 빨래를 하는데 3.5시간이 걸렸습니다.

### 파이프라인의 성능 향상 정도

파이프라인의 각 단계를 stage 혹은 step이라고 합니다.

이때, 파이프라인의 성능 향상 정도는 stage 개수만큼 빨라집니다.

예를 들어, 5단계 파이프라인을 할 경우 기존의 Single cycle CPU보다 5배 빨라지는 것과 같습니다.

## 5단계 파이프라인

### 용어

MIPS Single cycle CPU를 5단계 파이프라인하면 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled%204.png"></p>

- IF : Instruction Fetch
- ID : Decode Instruction
- EX : Execution Instruction
- MEM : Data memory access
- WB : Writeback

### 단계별 소요 시간

Single cycle CPU에서 각 단계별로 소요되는 시간이 달랐습니다.

각 단계별로 소요되는 시간은 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled%205.png"></p>

위 그림을 보면 메모리를 접근하는 IF와 MEM 단계가 200ps로 가장 느린 것을 알 수 있습니다.

Single cycle CPU에서는 가장 느린 명령어가 Critical path가 되어 Clock cycle time에 영향을 주었습니다.

그러나, 파이프라인 CPU에서는 가장 느린 Step이 Critical path가 되어 Clock cycle time에 영향을 줍니다.

### Single cycle과 비교

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled%206.png"></p>

사실 파이프라인을 하면 명령어 하나를 처리하는데 시간은 늘어납니다.

위 그림을 보면 첫 번째 명령어를 실행하는 시간이 Single cycle CPU는 800ps이지만 파이프라인 CPU는 1000ps가 걸리는 것을 확인할 수 있습니다.

그러면 파이프라인은 잘못된 것일까요?

당연히 아닙니다. 그림에서 볼 수 있듯 Single cycle CPU는 하나의 명령어가 끝날 때까지 처리가 끝난 모듈은 아무것도 하지 않지만 파이프라인 CPU는 다음 명령어를 처리하여 항상 동작합니다.

따라서, 파이프라인 CPU에서는 첫 번째 명령어가 처리 되는 시간은 늘어날 지 몰라도 두 번째 명령어가 처리되는 시간부터는 압도적으로 빠르게 됩니다.

예를 들어, Single cycle CPU는 첫 번째 명령어가 800ps, 두 번째 명령어는 1600ps에 끝나지만 파이프라인 CPU는 첫 번째 명령어는 1000ps, 두 번째 명령어는 1200ps, 그 이후 명령어는 1400ps, 1600ps, … 에 끝납니다.

## 파이프라인의 맹점

### Latency

그러나, 파이프라인은 결국 명령어 하나를 기준으로 봤을 때 처리 시간은 줄어들지 않습니다. 오히려 파이프라인을 하면서 생기는 overhead로 인해 더 증가하게 되죠.

### Throughput

그럼에도 파이프라인을 사용하는 것은 명령어 하나가 아닌 시스템 전체로 봤을 때 성능이 증가하기 때문입니다.

예를 들어 10개의 명령어를 처리한다고 했을 때, Single cycle CPU는 8000ps가 필요한 반면, 파이프라인 CPU는 2800ps가 필요합니다. 

만약 명령어가 더 많아지면 파이프라인의 속도는 파이프라인 단계에 비례합니다.

### 결론

즉, 파이프라인은 Throughput은 증가시키나 Latency는 줄어들지 않습니다.

## 그래도 waste는 있다

이 글의 처음에서 Single cycle CPU의 문제점으로 Critical path를 언급하며 waste가 발생한다고 하였습니다. 파이프라인 CPU도 이를 해결하진 못합니다.

<p align="center"><img src="../../images/Computer architecture/%23016%20MIPS%20CPU%20Pipeline%20basic/Untitled%207.png"></p>

위 그림처럼 Store 명령어는 동일하게 waste가 발생합니다.

그래도 파이프라인을 함으로써 전체적인 Throughput을 올려 이러한 waste를 극복하는 것이 파이프라인을 하는 가장 큰 이유입니다. 

## 파이프라인의 단계를 나누는 방법

그럼 파이프라인의 단계를 어떻게 나눌까요? 정답은 각 단계에 소요되는 시간을 균등하게 나누는 것입니다.

파이프라인 CPU에서 Clock cycle은 가장 긴 단계로 정해지기 때문에 단계별 시간이 균등하지 않고 한 쪽이 길어지면 Clock cycle이 긴 단계에 맞춰지므로 그만큼 낭비가 발생하게 됩니다.