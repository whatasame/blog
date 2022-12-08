# #022 Wireless LAN - CSMA/CA

이번 글에선 무선 랜 환경에서 충돌을 해결하기 위해 사용하는 CSMA/CA 프로토콜에 대해 알아보도록 하겠습니다.

## CSMA/CD를 사용하지 못하는 이유

무선 랜은 CSMA/CD로 충돌을 해결할 수 없습니다. 그 이유는 무선 랜의 특징 때문입니다.

- 무선 신호는 전파 거리가 멀어질수록 신호가 급격하게 감소한다.
    
    CSMA/CD는 전송한 신호와 반사된 신호의 크기를 비교하여 충돌을 감지합니다. 그러나, 무선 신호의 물리적인 특성으로 인해 반사된 신호의 크기가 원본 신호의 크기보다 작기 때문에 현재 신호가 내가 보낸 신호가 반사된 것인지, 다른 신호와 겹쳐서 온 것인지 구분할 수 없습니다.
    
- 무선 랜 장비는 Half-duplex 장비이다.
    
    또한, 무선 랜 장비는 Half-duplex 장비이므로 송신과 수신 둘 중 하나만 가능합니다. 즉, 송신을 하면서 수신을 할 수 없기 때문에 CSMA/CD를 사용할 수 없는 것이죠.
    

## CSMA/CA

위와 같은 문제로 인해 무선 랜에서는 CSMA/CD가 아닌 CSMA/CA를 사용합니다.

즉, 무선 랜에서는 충돌을 감지하는 것$^{collison \ detection}$이 아닌 충돌을 회피하는 전략$^{collison\  avoidance}$을 택합니다.

### Carrier sense

CSMA/CA도 CSMA의 한 종류이므로 CSMA의 Carrier sense를 수행합니다. 다시 말해, 통신을 시작하기 전에 현재 통신이 진행중인지 확인하고 통신을 시작합니다.

그러나, 무선 랜의 Carrier sense는 유선 랜에서 사용하는 Carrier sense와 조금 다릅니다.

유선 랜의 경우 통신을 시작하기 전에 진행 중인 통신이 있는 지 확인하고 없다면$^{idle}$ 바로 통신을 시작합니다.

이에 반해, 무선 랜의 경우 여러 번 확인하고 통신을 시작합니다.

CSMA/CD에서는 충돌을 감지하면 전송을 멈추지만, CSMA/CA는 충돌을 감지할 수 없기 때문에 한 번 시작된 통신은 멈출 수 없기 때문입니다.

충돌이 발생하면 해당 패킷은 사용할 수 없습니다. CSMA/CD는 전송을 멈출 수 있지만, CSMA/CA는 멈출 수 없기 때문에 사용할 수 없는 패킷을 계속 보내는 시간 낭비가 발생하기 때문입니다.

이러한 이유로 인해 CSMA/CA의 Carrier sense는 보수적으로 여러 번 수행됩니다.

### ACK

또, 충돌을 감지할 수 없기 때문에 한 번 보낸 신호가 정말로 제대로 전송되었는 지 알 수 있는 방법이 없습니다.

무선 랜에서는 신호가 정상적으로 보내졌음을 알기 위해 ACK이라는 응답 신호를 사용합니다.

Sender가 데이터를 보내면 Receiver는 데이터를 잘 받았다는 ACK frame을 Sender에게 전송합니다. 

Sender는 Recevier로부터 ACK을 받으면 전송이 잘 되었음을 확인, 그렇지 않으면 전송이 되지 않았다고 판단하여 재전송합니다.

### Back-off

바로 위에서 Sender가 데이터를 보낸 후 Receiver로부터 ACK frame을 받지 못하면 재전송을 한다고 했습니다. CSMA/CA에서 재전송을 할 때, CSMA/CD와 같이 back-off라는 시간을 두고 재전송을 합니다.

이때, CSMA/CA에서 back-off 시간은 채널(매체)이 idle 상태일 때만 흘러갑니다. 다시 말해, 채널이 사용 중이라면$^{busy}$ back-off 시간은 흘러가지 않습니다.

이를 위해 CSMA/CA에서는 back-off가 흘러가는 시간에도 Carrier sense를 계속 해야합니다.

<p align="center"><img src="../../images/Data Communications/%23022%20Wireless%20LAN%20-%20CSMA%20CA/Untitled.png"></p>

### DIFS와 SIFS

CSMA/CA에는 DIFS와 SIFS라는 시간이 있습니다.

<p align="center"><img src="../../images/Data Communications/%23022%20Wireless%20LAN%20-%20CSMA%20CA/Untitled%201.png"></p>

- DIFS
    
    DIFS$^{Distributed\ Inter\ Frame\ Space}$는 Sender가 데이터를 보내기 전에 Carrier sense를 하는 시간입니다.
    
    위에서 말했듯, CSMA/CA에서 Carrier sense는 여러번 수행됩니다.
    
    802.11a을 기준으로 $34\mu s$입니다.
    
- SIFS
    
    SIFS$^{Short\ Inter\ Frame\ Space}$는 Sender로부터 데이터를 수신한 Receiver가 ACK frame을 준비하는 시간입니다.
    
    ACK frame을 준비하는 시간이란, Half-duplex인 무선 장비가 수신 모드에서 송신 모드로 변경하는 시간을 말합니다.
    
    802.11a을 기준으로 $16\mu s$입니다.