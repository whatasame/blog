# #010 Data Link Layer - MAC

이번 글에서는 TCP/IP Protocol Model의 Data Link Layer에 대해 알아보도록 하겠습니다.

## Data Link Layer 소개

### 용어

Data Link Layer에서 사용하는 용어는 다음과 같습니다.

- Node : 호스트와 라우터와 같은 네트워크 장비 하나
- Link : 인접한 Node끼리 통신하는 통로
- Frame : Data Link Layer에서 처리하는 데이터의 단위. Network Layer에서 사용되는 Datagram을 캡슐화 한 것
- Channel :
- Station :

### 역할

Data Link Layer는 물리적으로 인접한 노드로 Datagram을 전송하는 역할을 담당합니다. 

- Network Layer → Physical Layer 관계
    
    Network Layer의 Datagram을 Data Link Layer에서 Frame으로 캡슐화하여 Physical Layer로 전송하는 역할을 합니다. 
    
- Physical Layer → Network Layer 관계
    
    Physicla Layer로부터 받은 Frame을 Datagram으로 역캡슐화하여 Network Layer로 전송하는 역할을 합니다.
    

우리가 배우는 MAC Protocol은 P→N 관계에서는 의미가 없는거 맞나?

### 구현

Data Link Layer는 모든 호스트마다 존재합니다. L2 장비인 라우터도 Data Link Layer를 가지고 있습니다.

이러한 Data Link Layer는 펌웨어와 OS에 나누어 구현됩니다.

<p align="center"><img src="../../images/데이터통신/%23010%20Data%20Link%20Layer%20-%20MAC/Untitled.png"></p>

- NIC$^{Network\ Interface\ Card}$의 펌웨어에 구현
- OS$^{Operating\ System}$에 소프트웨어로 구현

> NIC는 랜카드와 같은 하드웨어를 말합니다.
> 

> 펌웨어는 하드웨어에 포함된 소프트웨어를 말합니다.
> 

## Multiple Access Link

서로 다른 노드 간의 연결을 뜻하는 링크에는 두 종류가 있습니다.

- Point-to-point
    
    
- Broadcast

### Collision

그런데 여기서 주목해야할 점이 있습니다. Broadcast의 경우 여러 노드들이 하나의 공유 매체를 사용함으로써 동시에 둘 이상의 신호를 수신하게 됩니다. 이 경우 노드는 어떤 데이터를 받아야할 지 헷갈리게 됩니다. 이렇게 동시에 둘 이상의 신호를 받는 것을 **충돌**$^{collision}$이라고 합니다.

> 여기서 말하는 신호$^{signal}$은 Data Link가 보내는 Digital signal 혹은 Analog signal을 말합니다.
> 

## MAC Protocol

충돌이 발생하는 이유는 여러 노드들이 하나의 공유 매체를 사용하기때문입니다. 따라서, 이러한 충돌을 막고 원활한 통신을 하기 위해 노드들이 공유 매체에 접근하는 것을 제어할 필요가 있습니다.

노드들이 공유 매체에 접근하는 것을 제어하기 위한 프로토콜을 MAC 프로토콜$^{Medium\ Access\ Control\ Protocol}$ 이라고 합니다.

MAC 프로토콜은 각 노드의 MAC Address를 통해 공유 매체 접근을 제어합니다. 공유 매체를 사용하는 환경에서 하나의 노드가 신호를 보내면 다른 모든 노드가 신호를 수신합니다. 이때, 각 노드들은 신호의 MAC Address가 자신의 MAC Address와 일치하면 신호를 수신하고 그렇지 않으면 신호를 버립니다.

<p align="center"><img src="../../images/데이터통신/%23010%20Data%20Link%20Layer%20-%20MAC/Untitled%201.png"></p>

예를 들어 위처럼 bus topology를 갖는 네트워크가 있을 때, A 노드에서 D 노드로 신호를 보낸다고 가정하겠습니다. A 노드에서 보내는 신호는 B, C, D 노드가 모두 수신합니다. 그러나 신호를 수신한 B, C 노드는 신호의 MAC Address를 통해 자신에게 온 신호가 아님을 알고 신호를 버립니다. D 노드만이 신호를 수신하게 되는 것이죠 

<p align="center"><img src="../../images/데이터통신/%23010%20Data%20Link%20Layer%20-%20MAC/Untitled%202.png"></p>

이러한 MAC Address는 NIC마다 할당됩니다. 대부분의 컴퓨터는 NIC를 하나씩 가지고 있기 때문에 컴퓨터 한 대당 MAC Address가 할당된다고 생각할 수 있습니다. 그러나 이는 잘못된 생각입니다. 컴퓨터 단위가 아닌 **NIC 단위로 MAC Address가 할당**됩니다.

## MAC Protocol의 종류

충돌을 막기 위한 MAC Protocol은 세 종류가 있습니다.

- Channel partitioning
    
    전체 채널을 특정 기준으로 나누고 나눠진 채널을 노드들에게 할당함
    
- Taking turns
    
    노드들은 각자의 턴에만 공유 매체를 사용하여 신호를 전송할 수 있음
    
- Random access
    
    채널을 나누지 않고 충돌을 허락하되, 충돌을 복구하는 작업을 수행하여 정상적인 전송이 가능하게함
    

## Channel partitioning

Channel partitioning은 전채 채널을 특별한 기준으로 나누고 각각의 노드들이 나눠진 특정 채널만 공유 매체를 사용하는 MAC Protocol입니다. 

Channel partitioning는 **세 가지 종류**가 있습니다.

- TDMA : 시간$^{time\ slot}$을 기준으로 채널을 나눔 → 2G에 사용
- FDMA : 주파수$^{frequency}$를 기준으로 채널을 나눔 → 4G, 5G에 사용
- CDMA : 코드$^{code}$를 기준으로 채널을 나눔 → 3G에 사용

> CDMA는 이 글에서 다루지 않도록 하겠습니다.
> 

### TDMA

TDMA$^{Time\ Division\ Multiple\ Acess}$은 전체 채널을 시간으로 나눠 각 노드들이 정해진 시간 동안 신호를 전송하는 MAC Protocol입니다.

각각의 Station은 고정된 길이의 슬롯$^{time\ slot}$을 받습니다. 이후, 각자의 시간이 되면 공유 매체를 통해 신호를 전송할 수 있습니다.

> 슬롯 길이는 공유 매체를 통해 Frame을 전송할 수 있는 시간을 말합니다.
> 

예를 들어, 6개의 노드가 있을 때 1, 3, 4번 노드가 Frame을 전송하고 2, 5, 6번 노드는 아무것도 하지 않는 상황을 가정해보겠습니다. (슬롯의 길이는 10초)

<p align="center"><img src="../../images/데이터통신/%23010%20Data%20Link%20Layer%20-%20MAC/Untitled%203.png"></p>

TDMA에서는 위 그림처럼 통신을 하게됩니다. 전체 60초에서 1, 3, 4번이 통신하는 30초만 통신을 하고 나머지 30초는 아무 통신도 하지 않는 상태입니다.

**즉, 통신을 하지 않는 노드가 있을 때 시간을 낭비하게 됩니다.**

### FDMA

FDMA$^{Frequency\ Division\ multiple\ access}$는 전채 채널을 주파수로 나눠 각 노드들이 정해진 주파수에서 신호를 전송하는 MAC Protocol입니다.

각각의 Station은 고정된 주파수 영역을 할당받습니다. 이후, 각 노드들은 자신이 할당받은 주파수 영역을 통해 신호를 보내게 됩니다.

예를 들어, 6개의 노드가 있을 때 1, 3, 4번 노드는 Frame을 전송하고 2, 5, 6번 노드는 아무것도 하지 않는 상황을 가정해보겠습니다.

<p align="center"><img src="../../images/데이터통신/%23010%20Data%20Link%20Layer%20-%20MAC/Untitled%204.png"></p>

FDMA에서는 위 그림처럼 통신을 하게됩니다. 전체 주파수 중에서 통신을 하는 1, 3, 4번 대역 주파수만 사용되고 2, 5, 6번 노드의 대역 주파수는 사용되지 않습니다.

**즉, 통신을 하지 않는 노드가 있을 때 주파수를 낭비하게 됩니다.**

> 주파수 대역폭이 높을수록 데이터 전송 속도는 빨라지게 됩니다. 예를 들어, 8차선 고속도로의 도로를 전부 사용하는 것과 1, 3, 4차선만 사용하는 경우을 상상해보겠습니다. 당연히 전자가 교통 상황이 더 좋을 것입니다.
> 

### Channel partitioning 요약

**👍 장점**

- Collision이 발생하지 않는다.

**👎 단점**

- 시간 혹은 주파수와 같은 자원 낭비가 발생

## Taking turns

Taking turns은 턴$^{turn}$이라는 개념이 존재합니다. 각 노드들은 자신의 턴이 됐을 때 신호를 전송할 수 있습니다. Taking turns MAC Protocol은 노드에게 턴을 주는 방법에 따라 2가지로 구분할 수 있습니다.

- Polling
- Token passing

### Polling

Polling은 **별도의 마스터 노드**가 존재하고 마스터 노드가 각 노드들에 대해 **턴을 제공**하는 프로토콜입니다.

<p align="center"><img src="../../images/데이터통신/%23010%20Data%20Link%20Layer%20-%20MAC/Untitled%205.png"></p>

마스터 노드가 poll 신호를 각 노드에 보내 턴을 제공합니다. 이때, poll 신호를 받은 노드가 신호를 보내야하는 경우 신호를 보내게 하고, 신호를 보내지 않아도 된다면 바로 다음 노드로 poll 신호를 보내 턴을 변경합니다.

Polling 방식을 사용하면 Channel partioning과 달리 자원의 낭비도 없으며 Collision도 발생하지 않습니다. 그러나 Poll 신호도 일종의 신호이기 때문에 poll 신호를 보내는 시간에 실제 신호를 보낼 수 있으므로 overhead가 발생하게 됩니다. 또한, 턴을 바꾸는 시간에 해당하는 지연 시간도 발생하게 됩니다. 또한, Polling 방식에는 결정적인 문제점이 있습니다 마스터 노드가 고장이 나는 경우 Polling을 사용하는 시스템 전체가 사용할 수 없게 됩니다.

👍 **장점**

- 시간 혹은 주파수와 같은 자원 낭비가 발생하지 않는다.
- Collision이 발생하지 않는다.

👎 **단점**

- poll 신호를 보냄으로써 overhead 발생
- 노드의 턴이 바뀌는 latency 발생
- 마스터 노드에 문제가 생기는 경우 시스템 전체에 영향을 미침

### Token passing

Token passing은

## Random Access Protocols

Random access protocol은 채널을 나누지 않고 충돌을 허락하되, 충돌을 복구하는 작업을 수행하여 정상적인 전송이 가능하게하는 프로토콜입니다. 

따라서, Random access protocol이 정상적으로 작동하기 위해서는 다음과 같은 기능이 필요합니다.

- 충돌 감지
- 충돌 복구

이러한 기능을 제공하는 수준에 따라 세 가지로 나눌 수 있습니다.

- CSMA → 현재 사용하지 않음
- CSMA/CD → Ethernet에서 사용
- CSMA/CA → Wifi에서 사용

### CSMA

CSMA$^{Carrier\ Sense Multiple\ Access}$는 전송하기 전에 다른 전송이 있는지 확인하는 프로토콜입니다. 신호를 전송하기 전에 현재 공유 매체에 진행 중인 전송의 여부에 따라 다른 동작을 합니다.

- 진행 중인 전송이 없을 때 : 전체 Frame을 전송
- 진행 중인 전송이 있을 때 : 전송을 지연

아래 그림을 통해 CSMA에 대해 알아보겠습니다.

<p align="center"><img src="../../images/데이터통신/%23010%20Data%20Link%20Layer%20-%20MAC/Untitled%206.png"></p>

B 노드가 전송을 시작할 때, 현재 진행중인 전송이 없으므로 전송을 시작합니다. 이때, 신호가 전송되는 물리적인 속도가 있기 때문에 전파 지연$^{propagation\ delay}$이 발생하게 됩니다. 

위 그림에선 B 노드가 전송을 시작했지만 전파 지연으로 인해 D 노드가 전송을 시작할 땐 아직 B 노드의 신호를 받지 못한 상황입니다. 따라서, D 노드는 현재 진행 중인 전송이 없다고 생각하고 전송을 시작하게 됩니다. 결국 충돌이 발생하게 됩니다.

CSMA에는 충돌 감지 기능만 있을 뿐 충돌 복구 기능은 없으므로 충돌된 신호들은 버려지게$^{wasted}$ 됩니다.