# 5) Line Coding

## Line Coding이란

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled.png"></p>

서로 다른 기기의 데이터 통신은 위 그림처럼 진행된다. Sender의 Physical Layer에서 상위 레이어로부터 전달 받은 데이터(Frame)을 전기 신호(Signal)로 변환하여 전달합니다.

- Data link layer의 데이터 전송 단위를 Frame이라 부름
- Frame은 ETH, IP, TCP, Payload로 구성
- Physical layer는 Frame을 Bit로 변환하여 전송

데이터(Frame)과 전기 신호(Signal)은 디지털, 아날로그 2가지로 구분할 수 있습니다. 따라서 데이터를 전기 신호로 바꾸는 방법은 총 4가지가 있다. 

Line coding은 디지털 데이터를 디지털 신호로 바꾸는 인코딩 작업을 말한다. 추상적인 데이터를 실체가 있는 물리 신호로 어떻게 바꿀 수 있을까?

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 1.png"></p>

0과 1로 이루어진 Binary data는 전압차를 활용하여 전송할 수 있다. 

가장 간단하게 표현하면 1은 5V, 0은 0V로 변환하여 전송할 수 있다.

## Data element과 Signal Element

Data를 Signal로 인코딩하는 방법은 정말 많다. 

앞의 예처럼 1을 5V, 0은 0V로 변환할 수 있고 또 다른 방법으로 변환할 수 있다.

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 2.png"></p>

위 그림은 Data를 Signal로 인코딩하는 4가지 방법을 그래프로 표현한 것이다.

- Data element : 변환하는 데이터의 단위
- Signal element : 변환된 신호의 단위

<aside>
💡 element를 symbol로 표현하는 경우도 있다.

</aside>

각 그래프의 Element 사이의 비율(Ratio, r)을 표현하면 아래와 같다.

- A 그래프 : 1 Data element 당 1 Signal element → $r = 1$
- B 그래프 : 1 Data element 당 2 Signal element → $r = \frac{1}{2}$
- C 그래프 : 2 Data element 당 1 Signal element → $r = 2$
- D 그래프 : 4 Data element 당 3 Signal element → $r = \frac{4}{3}$

## 인코딩 고려사항

지금까지 디지털 데이터를 디지털 신호로 바꾸는 Line coding에 대해 간략하게 알아봤다. 인코딩 방식은 자유롭게 정할 수 있지만 인코딩 방법에 따라 발생하는 문제점을 고려할 필요가 있다.

- Bandwidth
    
    인코딩 규칙에 따라 Data element와 Signal element의 비율이 결정된다. 위 그래프에서 1개의 Data element에 대해 A 그래프는 Signal element가 1개, C 그래프는 Signal element가 2개 필요하다. 다시 말해 같은 데이터를 전송하는데 필요한 신호의 양이 C 인코딩이 A 인코딩보다 2배 더 많다. 
    
    Bandwidth를 왜 고려해야할까? 데이터 전송에 필요한 신호의 양이 많다는 것은 신호 전송량이 증가한다는 뜻이고 이는 감당할 넓은 회선이 필요하다는 것을 의미한다. 다시 말해 필요한 데이터 전송량을 Bandwidth라고 한다. 따라서 **Bandwidth는 좁을수록 좋다.**
    
- DC components
    
    Line coding에서 0과 1은 전압차로 표현된다. 0을 0V, 1을 5V로 변환하는 인코딩에서 11111을 보낸다고 생각해보자. 이 경우 1을 5개 보낼동안 전압이 5V에서 바뀌지 않고 유지된다. 이렇게 특정 전압에서 유지되는 것을 DC component라고 하며 이러한 인코딩 방식을 DC components가 발생할 수 있는 인코딩이라고 부른다.
    
    다시말해, DC component는 전압이 양수 또는 음수로 유지되는 것을 뜻한다. 
    
    <aside>
    💡 전압이 0V로 유지되는것은 DC component라 하지 않는다.
    
    </aside>
    
    DC Component 상태를 왜 고려해야할까? 일부 장비에서는 0Hz의 전압을 인식하지 못하는 경우가 있기 때문입니다. 따라서 **DC components는 없는 것이 좋습니다.**
    
- Synchronization
    
    11111과 같은 연속된 데이터를 데이터를 송수신하는 장치에선 어떻게 구분할까요? 데이터를 송수신하는 장치에는 오실레이터(Oscillator)라는 부품이 있어 일정 간격으로 전기 신호를 보냅니다. 오실레이터가 생성하는 전기 신호를 통해 데이터를 구분짓는 것이죠. 그런데 송신 장치와 수신 장치마다 있는 오실레이터가 항상 같은 간격으로 전기 신호를 보낸다는 보장을 할 수 있을까요?
    
    실제로 오실레이터는 항상 같은 간격의 전기 신호를 보장할 수 없습니다. 따라서 전기 신호의 변화를 보고 장치가 시간차를 보정하는 과정이 존재합니다. 
    
<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 3.png"></p>
    
    그러나 인코딩 방식에 따라 시간차를 보정할 수 없는 경우가 있습니다. 예를 들어 11111의 신호를 보낼 때 B 인코딩은 구분할 수 있는 반면 A 인코딩은 시작과 끝을 알 수 없어 보정할 수 없습니다. 보정할 수 없다는 것은 Synchronization 할 수 없다는 것과 동일합니다.
    
    따라서 **Synchronization 이슈가 없고 Synchronization을 지원하는 것이 좋습니다.**
    
    한편, Synchronization은 오셀레이터가 생성하는 전기 신호인 Clock을 스스로 보정한다고 하여 Self-clocking라고도 합니다.
    

위 3가지 인코딩 고려사항을 기억하며 대표적인 인코딩을 알아보겠습니다.

## Unipolar

Unipolar는 Uni-(하나의) 접두사에서 알 수 있듯이 0과 1을 표현하는 전압 값이 양수와 0의 값을 갖습니다.

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 4.png"></p>

위 그림은 Unipolar에서 0은 0V, 1은 5V로 변환된 예를 보여주고 있습니다.

**Unipolar의 특징**

- DC components가 발생
- Synchronization을 지원하지 않음

## Polar - NRZ

NRZ는 (Non Return Zero)라는 뜻입니다. 전압이 바뀔 때 0V가 되는 경우가 없다는 뜻입니다. 이는 RZ(Return Zero)와 대비되는 개념으로 RZ를 알아야 와닿는 개념이므로 생략하겠습니다.

NRZ에는 NRZ-L, NRZ-I 두가지 형태가 존재합니다.

### NRZ-L

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 5.png"></p>

NRZ-L(NRZ-Level)는 0과 1이 서로 반대의 전압으로 표현됩니다. 예를 들어 0이 -5V로 표현되면 1은 5V로 표현되고 반대로 0이 5V로 표현되면 1은 -5V로 표현됩니다. 

NRZ-L은 일반적인 Unipolar와 다른 점이 없나?

**NRZ-L의 특징**

- DC components가 발생
- Synchronization을 지원하지않음

### NRZ-I

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 6.png"></p>

NRZ-I(NRZ-Inverse)는 조금 독특합니다. 0은 현재 전압 유지, 1은 직전 전압의 반대로 표현합니다.

**NRZ-I의 특징**

- 0이 반복될 때 DC components가 발생
- 0이 반복될 때 Synchronization을 지원하지 않음

## Polar - RZ

RZ(Return Zero)는 전압이 바뀔 때 반드시 0V 상태로 Transition(전이)하는 인코딩입니다.

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 7.png"></p>

얘는 0과 1이 어떻게 되는지 모르겠는데

**RZ의 특징**

- DC components가 없음
- Synchronization 지원 (Self-clocking 지원)
- Data element : Signal element = 2로 Bandwidth가 넓음
- 전압의 형태가 3가지(양수/0/음수)로 존재하여 시스템 구현 시 복잡도 상승

## Polar - Manchester, Differential Manchester

### Manchester

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 8.png"></p>

Manchester은 NRZ-L과 RZ를 합친 인코딩 방식입니다.

0과 1은 서로 반대의 전압을 가지고 있는 NRZ-L의 특징을 가져왔기에 0과 1은 서로 반대 모양의 전압 그래프로 변환됩니다. 또한, 0V로의 전이를 거치는 RZ의 특징을 가지고 있습니다.

**Manchester의 특징**

- No DC components
- Synchronization 지원
- Data element : Signal element = 2로 Bandwidth가 넓음
- Bandwidth가 넓지만 근거리 통신(LAN)에서 사용

### Differential Manchester

Differential Manchester는 NRZ-I와 RZ를 합친 인코딩 방식입니다.

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 9.png"></p>

0은 전압 유지, 1은 직전 전압의 반대 전압으로 변환되는 NRZ-I의 특징을 가져왔기에 0은 이전 전압 그래프 반복, 1은 직전 전압 그래프의 반전으로 변환됩니다. 또한, 0V로 전이를 거치는 RZ의 특징을 가지고 있습니다.

**Differential Manchester의 특징**

- No DC components
- Synchronization 지원
- Data element : Signal element = 2로 Bandwidth가 넓음

그럼 RZ, Manches, Diff Manches의 차이는 뭔데? 오히려 Manches가 더 안좋은거 아닌가

## Bipolar

### AMI

AMI는 Alternate Mark Inversion의 약자로서 0을 0V로 표현하고 1을 직전 전압의 반대로 표현합니다.

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 10.png"></p>

**AMI의 특징**

- DC components가 없다
    
    0V가 유지되는 상황이 있지만 0V 유지는 DC components가 아님
    
- 0이 유지되는 상황에서 Synchronization 미지원
- 장거리 통신에 사용된다. 그러나 Synchronization 이슈가 있기 때문에 추가적인 인코딩 방식이 필요

### Pseudoternary

Pseudoternary는 AMI의 반대로서 0을 직전 전압의 반대로 표현하고 1을 0V로 표현합니다.

<p align="center"><img src="../../images/데이터통신/5) Line Coding-Untitled 11.png"></p>

- DC components가 없다
    
    0V가 유지되는 상황이 있지만 0V 유지는 DC components가 아님
    
- 0이 유지되는 상황에서 Synchronization 미지원