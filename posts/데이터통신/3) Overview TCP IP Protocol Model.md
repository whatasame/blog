# 3) Overview TCP/IP Protocol Model

## TCP/IP Protocol Model

네트워크의 데이터 통신을 Layering하면 아래와 같다.

<p align="center"><img src="../../images/데이터통신/3) Overview TCP IP Protocol Model-Untitled.png"></p>

데이터 통신에 필요한 과정을 5개의 Layer로 분리했다. 이렇게 데이터 통신 과정을 5개의 Layer로 분리한 것을 **TCP/IP Protocol Model**이라 한다.

### Application Layer

- 서비스에 맞는 Protocol의 집합
    
    Web을 위한 HTTP, 이메일을 위한 SMTP, 파일 전송을 위한 FTP, IP주소를 도메인 이름으로 바꾸는 DNS 등 서비스에 맞는 Procotol이 모여있는 레이어이다.
    

### Transport Layer

- Reliable transmission
    
    데이터의 손상, 누락 등을 방지하여 정상적인 데이터를 보장한다.
    
- Flow control
    
    송·수신하는 데이터가 알맞은 Application으로 송·수신 될 수 있도록 제어한다.
    
- Congestion control
    
    데이터 통신량이 많을 때 Router가 정상적인 작동을 할 수 있도록 제어한다.
    

### Network Layer

- Multi-hop 데이터 전송
    
    네트워크에서 데이터 전송은 Sender-Switch-Router-Switch-Receiver의 단계로 이루어지는데,  Router 단계에서 여러 Router를 거치는 Multi-hop 데이터 전송(Routing)을 담당한다.
    

### Data Link Layer

- one-hop 데이터 전송
    
    직접 연결된 2개의 노드에 대한 데이터 전송을 담당한다. 특히, 장치-스위치 관계를 담당한다.
    

### Physical Layer

- Encoding/Decoding
    
    0과 1로 이루어진 패킷을 물리적인 전기 신호로 Encoding하여 Switch로 전송한다. 반대로, 수신한 전기 신호를 0과 1로 이루어진 패킷으로 Decoding하여 Data Link Layer로 전송한다.
    
- 물리적인 스펙을 정의
    
    RJ-45와 같은 랜선의 각각의 핀에 역할을 정의한다.
    

## 네트워크 데이터 통신의 과정

<p align="center"><img src="../../images/데이터통신/3) Overview TCP IP Protocol Model-Untitled 1.png"></p>

위 그림은 Host(Sender)에서 Naver에서 검색 버튼을 눌러 검색 결과를 Naver 서버(Receiver)에 요청하는 과정을 나타낸 것이다.

### 각 레이어별 데이터 단위

- Application Layer → Message
- Transport Layer → Segment
- Network Layer → Datagram(Packet)
- Data Link Layer → Frame

<aside>
💡 Network Layer의 데이터 단위인 Packet은 데이터 통신에서 범용적으로 쓰인다. 예를 들어 Transport Layer를 이야기하고 있을 때 Packet을 언급하면 Segment를 가리키는 것이다.

</aside>

### Switch

위 그림에서 Switch의 레이어는 Physical Layer와 Data Link Layer 밖에 존재하지 않는다. Switch는 Local 단위에서 데이터 통신을 담당하기 때문에 Network Layer 이상의 레이어가 필요하지 않다. Switch는 레이어를 2개 가지고 있기 때문에 L2 Device라고 부르기도 한다.

### Router

위 그림에서 Router의 레이어는 Physical Layer와 Data Link Layer 밖에 존재하지 않는다. Router는 Global 단위에서 데이터 통신을 담당하기 때문에 Switch의 레이어에 Network Layer가 추가되었다. 그러나 Transport Layer 이상의 레이어는 필요하지 않다.

Router는 레이어를 3개 가지고 있기 때문에 L3 Device라고 부르기도 한다.