# #000 Pipeline Harzards

이번 글에는 파이프라인$^{Pipeline}$함으로써 생기는 문제에 대해 알아보도록 하겠습니다.

## 파이프라인을 하는 이유

우선, 파이프라인을 하는 이유에 대해 알아보도록 하겠습니다. 

파이프라인을 하는 이유는 컴퓨터 전체 성능이 향상되기 때문입니다.

<p align="center"><img src="../../images/Computer architecture/%23000%20Pipeline%20Harzards/Untitled.png"></p>

예를 들어, 하나의 사이클을 위 그림과 같이 5개의 사이클$^{cycle}$로 구분하고 10,000개의 명령어를 실행한다고 가정해보겠습니다. 

파이프라인이 없는$^{single\ cycle}$ CPU는 하나의 명령어를 마쳐야 다음 명령어를 처리할 수 있으므로 총 50,000개의 사이클이 필요합니다. 

이에 비해, 5단계 파이프라인 CPU는 동시에 여러 명령어를 나누어 처리할 수 있습니다. 파이프라인이 채울 때$^{Time\ to\ FILL}$와 비울 때$^{Time\ to\ DRAIN}$를 제외한 9992개의 명령어는 1 클럭 사이클당 1개씩 처리됩니다. 즉, 5배 빨라지는 것이죠.

## 파이프라인의 위험 요소

파이프라인을 활용하면 시스템 전체 성능이 향상되는 것은 맞습니다. 그러나 파이프라인이 정상적으로 동작하기 위해선 아래와 같은 위험 요소$^{hazard}$를 고려해야합니다.

- Structural hazards : 서로 다른 명령어가 동일한 하드웨어를 사용
- Data hazards : 데이터가 업데이트 되기 전에 읽는 것 → Register file과 관련
- Control hazards : 상태가 업데이트되기 전에 결정을 내리는 것 → Branch와 관련

사실, 이러한 위험 요소들은 단순하게 해결가능합니다. 해당 문제들이 발생하지 않을 때까지 기다리는 것인데요, 예를 들어 Data hazards는 데이터가 업데이트될 때까지 명령어를 실행하지 않으면 됩니다. 

그러나 이런 방법은 훌륭한 해결법이 되지 못합니다. 성능을 높히기 위해 파이프라인을 사용하는데 이에 발생하는 문제를 해결하기 위해 성능 저하를 일으키는 모순이 발생하기 때문입니다.

따라서, 파이프라인은 위와 같은 위험 요소를 감지$^{detect}$하고 해결$^{solve}$할 필요가 있습니다.

## Structural hazard

Structural hazard는 서로 다른 명령어가 동일한 하드웨어를 사용하는 것을 말합니다.

<p align="center"><img src="../../images/Computer architecture/%23000%20Pipeline%20Harzards/Untitled%201.png"></p>

파이프라인을 위 그림처럼 했다고 가정해봅시다. 이때 발생할 수 있는 Structure hazard는 아래와 같습니다.

- Memory access
- Register file access

### Memory access

Memory access는 서로 다른 명령어가 하나의 메모리를 동시에 사용하는 것을 말합니다.

<p align="center"><img src="../../images/Computer architecture/%23000%20Pipeline%20Harzards/Untitled%202.png"></p>

위 그림에서 하나의 메모리를 `lw`와 `Inst3`이 동시에 사용하려고 하고 있습니다. 그런데 `lw`는 메모리에서 데이터를 읽으려고 하고 있고, `Inst3`은 명령어를 읽으려고 하고 있습니다. 즉, 메모리에 동시에 접근하려고 하지만 서로 목적이 다른 것이죠.

이러한 Memory hazard는 메모리를 구분하여 저장하는 것으로 해결가능합니다. 하나의 메모리를 명령어만 저장하는 메모리와 데이터만 저장하는 메모리로 구분하는 것이죠

- Instruction memory$^{IM}$ :  명령어만 저장하는 CPU 캐시
- Data memory$^{DM}$ : 데이터만 저장하는 CPU 캐시

이렇게 CPU 캐시가 나누어져 있는 컴퓨터 구조를 하버드 구조$^{Harvard\ Structure}$라고 합니다. 현대에 사용되는 CPU의 L1 캐시가 나누어져 있는 이유도 파이프라인의 Structure Hazard를 해결하기 위한 것이죠.

### Register file access

Register file access는 서로 다른 명령어가 동시에 Register file에 접근하는 것을 말합니다.

<p align="center"><img src="../../images/Computer architecture/%23000%20Pipeline%20Harzards/Untitled%203.png"></p>

위 그림에서 `add $1`와 `add $2` 가 동시에 Register file에 접근하려고 하기 때문에 Structure hazard가 발생합니다.

그러나 이 문제는 쉽게 해결가능합니다. 파이프라인의 각 Step의 실행 시간이 다르기 때문입니다.

이번 글에서 사용하는 5단계 파이프라인 구조에선, Register file을 읽는 Step은 다른 Step에 비해 실행 시간이 짧습니다. 각각의 Step은 동일하게 1 Clock Cycle을 요구하지만 1 Clock Cycle의 시간은 Step 중 가장 오래걸리는 Step에 맞춰지기 때문입니다.

Register file에 접근하는 시간은 다른 Step에 비해 상대적으로 짧습니다. 전체 시간의 반에 해당하는 시간인데요, 따라서 처음 접근하는 Register를 뒤로 밀고, 나중에 접근하는 Register를 앞으로 밀면 Structure hazard가 발생해도 동시에 접근하는 문제가 발생하지 않습니다.