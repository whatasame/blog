# #000 ARP, Broadcast, Unicast, Multicast

이번 글에서는 ARP에 대해 알아보도록 하겠습니다.

## MAC Address Review

여러 기기가 연결되어 있는 네트워크에서 각 기기들이 하나의 공유 매체$^{shared\ medium}$로 데이터를 전송하면 데이터가 충돌하게 됩니다. 데이터가 충돌하게되면 데이터를 받는 기기에서는 어느 데이터를 수신해야하는지 모릅니다. 이러한 문제를 해결하기 위해  데이터를 받는 사람을 구분하는 MAC Address$^{Medium\ Access\ Control}$가 만들어졌습니다.

MAC Address는 48bit로 표현된 값으로 구분되고 NIC$^{Network\ Interface\ Card}$의 ROM$^{Read\ Only\ Memory}$에 새겨집니다$^{burn}$.

> 흔히 CD를 굽는다고 할 때 굽다의 영어 표현이 burn입니다.
> 

MAC Address는 기기별로 고유한 값을 가지고 있어야하기 때문에 IEEE$^{I-Triple-E}$에서 관리합니다. 통신 기기를 만드는 회사는 IEEE로부터 MAC Address를 구매하여 사용합니다.

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled.png"></p>

MAC Address의 48bit 중 상위 24bit가 제조사를 구분하는데 사용되고, 하위 24bit가 해당 제조사의 일련번호를 나타내는데 사용됩니다.

## ARP

MAC Address를 이용하면 데이터의 충돌이 발생할 때 원하는 기기에 데이터를 전송할 수 있습니다. 그런데 데이터를 받는 기기의 MAC Address는 어떻게 알아낼까요? 예를 들어, 새로운 컴퓨터를 구매해서 네트워크에 연결하면 네트워크의 기존 기기들의 MAC Address에 대한 정보를 어떻게 알아낼까요?

당연하게도, 새로운 기기는 다른 기기의 MAC Address를 모릅니다. 대신, ARP라는 프로토콜을 사용하여 MAC Address를 알아냅니다.

### ARP Table

우선 ARP Table에 대해 알아보도록 하겠습니다. ARP$^{Address\ Resolution\ Protocol}$는 ARP Table을 통해 MAC Address를 관리합니다. ARP Table의 구조는 아래와 같습니다.

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled%201.png"></p>

네트워크의 모든 노드들은 자신만의 ARP Table을 가지고 있습니다. 각 노드들은 ARP Table로 다른 노드의 MAC Address를 저장하고 관리합니다. 

이때 TTL$^{Time\ To\ Live}$는 정보를 저장하는 시간을 말합니다. 일반적으로 TTL은 20분으로 사용합니다. 따라서, 20분이 지나면 해당 노드의 정보는 ARP Table에서 삭제됩니다.

### ARP에서 MAC Address를 알아내는 방법

> MAC Address를 구하기 위해 IP Address를 알아야합니다. IP Address는 다른 프로토콜로 알아낼 수 있습니다. 따라서, IP Address를 알고있는 상태로 가정하겠습니다.
> 

아래와 같은 네트워크에서 **A가 B에게 데이터를 전송**하고자 합니다.

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled%202.png"></p>

현재 A의 ARP Table은 아래와 같습니다.

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled%203.png"></p>

A는 데이터를 받는 노드 B의 MAC Address를 모르는 상태입니다. 이때, A 노드는 B 노드의 MAC Address를 알기 위해 네트워크의 모든 노드에게 **ARP frame**을 전송합니다$^{logical\ broadcast}$.

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled%204.png"></p>

ARP frame의 Target MAC Address는 `FF-FF-FF-FF-FF-FF`입니다. 이는 사전 정의된 MAC Address로서 `FF-FF-FF-FF-FF-FF`를 수신하는 노드들은 해당 frame이 ARP frame임을 알게 됩니다.

ARP frame을 수신한 노드는 Target IP Address를 보고 자신에게 온 ARP frame인지 확인합니다. IP Address가 맞지 않는 다른 노드들은 ARP frame을 버리고, `137.196.7.14`에 해당하는 노드 B는 ARP frame에 응답합니다. 

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled%205.png"></p>

B 노드는 A 노드가 보낸 ARP frame의 Target MAC Address 내용을 자신의 MAC Address로 채운 후 A에게 보냅니다.

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled%206.png"></p>

B 노드가 보낸 응답 frame을 A 노드가 받으면 A 노드는 자신의 ARP Table에 B 노드의 정보를 기록합니다.

## ARP frame

ARP frame은 노드가 다른 노드의 MAC Address를 알기 위해 보내는 frame입니다. 일반적인 frame은 Application Layer으로부터 캡슐화되어 생성된 것이지만, **ARP frame은 DataLink Layer에서 생성**된다는 점을 기억해야합니다.

ARP frame은 아래처럼 구성됩니다.

<p align="center"><img src="../../images/Data Communications/%23000%20ARP%2C%20Broadcast%2C%20Unicast%2C%20Multicast/Untitled%207.png"></p>

- Destination MAC : 찾으려는 노드의 MAC Address → 초기 값은 `FF-FF-FF-FF-FF-FF`
- Source MAC : ARP Frame을 보낸 노드의 MAC Address
- Type :
- ARP
    - Hardware Type
    - Protocol Type
    - Source MAC
    - Target MAC
- Padding : Frame의 최소 크기를 위해 자리만 차지하는 값
- CRC : Frame 에러 체크

## Broadcast, Unicast, Multicast

ARP에서 MAC Address를 얻기 위해 ARP frame을 보낼 때, 네트워크의 모든 노드에게 ARP frame을 보내는 것을 Logical Broadcast라고 하였습니다. 이러한 신호 전달 방식에 대해 알아보겠습니다.

### Broadcast

Broadcast란 네트워크 전체에 신호를 보내는 것을 말합니다. 이러한 Broadcast는 2가지로 나뉩니다.

- Physical Broadcast : 물리적인 현상으로 네트워크 전체에 신호를 보내는 경우
- Logical Broadcast : 논리적인 의도에 의해 네트워크 전체에 신호를 보내는 경우

### Unicast

Unicast란 네트워크의 특정 한 노드에게 신호를 보내는 것을 말합니다.

### Multicast

Multicast는 네트워크의 특정 여러 노드에게 신호를 보내는 것을 말합니다.

수십 명의 사람이 있는 강의실의 칠판 앞에서 크게 이야기하는 경우를 예로 들어보겠습니다.

1. 혹시 여기 홍길동 학생 있나요? 
    
    의도를 가지고 강의실$^{network}$에 있는 모든 학생$^{node}$에게 질문$^{frame}$을 한 것이므로 **Logical Broadcast**에 해당합니다.
    
2. 홍길동 학생, 이 문제 대답해보세요
    
    강의실$^{network}$에 있는 한 학생$^{node}$에게 질문$^{frame}$을 한 것이므로 **Unicast**에 해당합니다.
    
3. 첫 번째 줄에 앉은 학생들은 일어서주세요
    
    강의실$^{network}$에 있는 여러 학생$^{node}$에게 질문$^{frame}$을 한 것이므로 **Multicast**에 해당합니다.
    

그렇다면 Physical Broadcast는 어떤 것일까요? 1, 2, 3번 모두 Physical Broadcast입니다. 왜냐하면 강의실 앞에서 크게 이야기를 했기 때문에 모든 학생들$^{node}$들은 자신에게 관련이 있든 없든 소리를 들었기 때문입니다.

이러한 Physical Broadcast는 유선망에서 신호를 보낼 때, 물리적으로 같은 선$^{shared\ medium}$을 쓰는 모든 노드가 해당 신호를 수신하는 것을 말합니다.