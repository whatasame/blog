# #021 Wireless LAN - basic

이번 글에선 무선 랜 환경에 대해 알아보도록 하겠습니다.

## 시작하기전

우리가 무선 인터넷이라고 흔히 사용하는 Wifi는 WLAN의 상표명입니다. 즉, 공식 명칭은 WLAN입니다.

> 이와 유사하게 우리가 알고 있는 4G는 LTE, 5G는 NR이 공식 명칭입니다.
> 

이번 글에서는 WLAN의 여러 버전 중 IEEE 802.11 버전을 다루도록 하겠습니다.

<p align="center"><img src="../../images/Data Communications/%23021%20Wireless%20LAN%20-%20basic/Untitled.png"></p>

무선 인터넷이 사용하는 주파수 영역을 ISM band$^{Industry-Science-Medical\ band}$라고 부릅니다. 단어에서 알 수 있듯이 해당 주파수 영역은 무선 인터넷 뿐만 아니라 다른 분야에서도 사용 중인 대역입니다.

## 무선 랜의 특징

물리적으로 연결된 유선 랜과 달리 공기를 통해 통신하는 무선랜의 특징은 다음과 같습니다.

- 약해지는 신호$^{decreased\ signal\ strength}$
    
    무선 랜은 멀리 전파될 수록 신호의 세기가 급격하게 약해집니다.
    
- 다른 시스템으로부터 간섭$^{interference\ from\ other\ sources}$
    
    무선 랜이 사용되는 2.4GHz를 다른 시스템에서도 사용하여 간섭이 발생합니다.
    
    예를 들어 전자레인지도 2.4GHz를 사용하는데, 당연하게도 무선 랜에서 작동되는 여러 프로토콜을 지키지 않아 무선 랜 통신을 방해합니다.
    
- 반사된 신호에 의한 전파$^{multipath\ propagation}$
    
    무선 신호가 전파되면 벽에 부딪히는 등의 이유로 신호가 반사될 수 있습니다. 이러한 상황에서 무선 신호를 수신하는 노드는 원본 신호를 수신할 뿐만 아니라 반사된 신호, 심지어 반사가 된 신호가 반사된 신호까지 받을 수 있습니다.
    

## 무선 랜의 문제

위와 같은 무선 랜의 특징으로 인해 Hidden terminal problem이라는 문제가 발생하게 됩니다.

**Hidden terminal problem은 같은 네트워크에 있는 노드가 서로의 존재를 인식하지 못하는 것**을 말합니다.

Hidden terminal problem은 무선 랜의 특징 중 약해지는 신호와 관련이 있습니다. 무선 신호가 멀리 전파될수록 신호의 세기가 급격하게 약해지므로 하나의 노드가 신호를 보낼 수 있는 범위가 있습니다. 이를 커버리지라고 합니다.

A-B-C 노드가 있을 때, A의 커버리지가 B는 포함하고 C는 포함하지 않을 때, 반대로 C의 커버리지가 B는 포함하고 A는 포함하지 않을 때 Hidden terminal problem이 발생하게 됩니다.

## 채널

무선 랜에선 주파수를 일정 범위로 분할하여 채널이라는 단위로 구분합니다.

<p align="center"><img src="../../images/Data Communications/%23021%20Wireless%20LAN%20-%20basic/Untitled%201.png"></p>

IEEE 802.11b WLAN의 주파수 영역은 2.4GHz이고, 22MHz씩 분할하여 채널로 구분합니다.

### 채널을 선택하는 방법

기본적으로, 채널이 겹치지 않도록 선택하는 것이 좋습니다. 채널이 겹친다는 것은 충돌이 발생할 수 있다는 뜻이고 이는 시스템의 성능을 저하시키기 때문입니다. 그러나, 네트워크에 노드들이 많아져 채널의 중복을 피할 수 없다면 어떻게 해야할까요?

<p align="center"><img src="../../images/Data Communications/%23021%20Wireless%20LAN%20-%20basic/Untitled%202.png"></p>

정답은 채널을 overlap하는 것입니다. 위 그림에서 1, 6, 11 채널을 이미 사용중이라고 가정하겠습니다. 주파수 대부분이 사용중이기 때문에 충돌을 피할 수 없습니다. 이때, 채널을 사용하는 가장 좋은 방법은 1, 6, 11을 사용하는 것입니다.

만약, 1과 6의 사이 채널인 3을 선택하면 어떻게 될까요? 이 경우, 1번 채널과 6번 채널 모두에서 충돌이 발생할 수 있습니다. 즉, 충돌이 발생할 채널이 2개인 것이죠.

이에 반해, 6번 채널에 overlap하면 충돌이 발생할 채널이 6번 채널 단 하나밖에 없습니다.

## 무선 랜의 모드

무선 랜 모드는 2가지로 구분할 수 있습니다.

- Infastructure mode
- Ad-hoc mode

### Infrastructure mode

Infrastructure mode는 네트워크에서 통신을 하기 위해 AP$^{Access\ Point}$라는 노드를 거쳐 통신하는 것을 말합니다. 

<p align="center"><img src="../../images/Data Communications/%23021%20Wireless%20LAN%20-%20basic/Untitled%203.png"></p>

각 AP들은 네트워크의 노드들의 무선 신호를 받아 유선으로 연결된 라우터에 전달하는 스위치 역할을 하는 장비입니다. 즉, AP는 한 쪽이 무선으로, 한 쪽은 유선으로 연결된 L2 장비입니다.

일반적으로 무선 랜 환경이라고함은 Infrastructure mode를 사용합니다.

### Ad-hoc mode

Ad-hoc mode는 AP를 거치지 않고 노드끼리 통신하는 것을 말합니다.

<p align="center"><img src="../../images/Data Communications/%23021%20Wireless%20LAN%20-%20basic/Untitled%204.png"></p>

즉, 통신에 참여하는 노드끼리 네트워크를 구성하는 것을 말합니다.

대표적인 예로 Wifi-direct, Airdrop 등이 있습니다.