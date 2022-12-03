# 4) Data & Signal, Signal 그래프

## Data의 종류

데이터에는 Analog Data와 Digital Data가 있다.

### Analog Data, Digital Data

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled.png"></p>

- Analog Data : 연속적인 값을 가지는 데이터
- Digital Data : 이산적인 값을 가지는 데이터

## Signal의 종류

Data가 전송되기 위해선 물리적 전기 신호인 Signal로 변환되어야한다. Signal도 Data처럼 Analog Signal과 Digital Signal가 있다.

### Analog Signal, Digital Signal

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 1.png"></p>

- Analog Signal : 연속된 신호
- Digital Signal : 이산적인 신호

### Periodic, Non-periodic Signal

Signal(Digital, Analog 모두)은 Periodic Signal과 Non-periodic Signal로 나뉜다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 2.png"></p>

- Periodic Signal :  일정한 간격마다 동일한 패턴을 반복하는 Signal
- Non-periodic Signal : Periodic Signal이 아닌 Signal

## Analog Signal의 Sine wave

Analog signal은 Sine wave로 구성되어있다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 3.png"></p>

이러한 Sine waves는 3가지 값을 갖는다.

- 진폭(Amplitude)
- 주파수(Frequency)
- 위상(Phase)

### Amplitude

Signal 에서 특정 시점의 진동의 폭이다. 

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 4.png"></p>

- Peek Amplitude : Signal 에서 가장 큰 Amplitude

Signal A는 B보다 Peek Amplitude가 작다.

### Frequency, Period

Signal이 하나의 사이클에 필요한 시간을 Period라고 한다. 이때 Signal이 1초에 진행한 Period의 수를 Frequency라고 한다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 5.png"></p>

- Period : 하나의 사이클에 필요한 시간
- Frequency : 1초에 진행한 Period의 수

### Phase

Phase는 0초를 기준으로 할 때 Sine wave의 위치이다. 위치는 $2\pi(360\degree)$를 기준으로 한다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 6.png"></p>

- B는 A를 $\frac{1}{2}\pi(90\degree)$  shift 한 것이다.
- C는 A를 $\pi(180\degree)$  shift 한 것이다.
- D는 A를 $\frac{3}{4}\pi(270\degree)$  shift 한 것이다.

## Digital to Signal 변환

Data와 Signal은 모두 Analog와 Digital의 형태를 갖는다. 따라서 Data가 Signal로 변하는 경우의 수는 4가지이다. (Digital  → Digital, Digital → Analog, Analog → Digital, Analog → Analog)

- Encoder : Digital Signal을 만드는 장치
- Modem : Analog Signal을 만드는 장치
    
    Modem(**Mod**ulation + **Dem**odulation)
    

## Time domain & Frequency domain

Analog signal을 그래프로 표현하는 방법에는 2가지가 있다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 7.png"></p>

- Time domain : x축이 Time
- Frequency domain : x축이 Frequency

### Frequency domain을 쓰는 이유

여러 Sine wave가 섞인 Composite signal(복합 신호)를 표현하기 좋음

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 8.png"></p>

여러개의 Sine wave가 섞인 Composite signal의 모습

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 9.png"></p>

- A 그래프 : 위 그래프에서 각각의 Sine wave를 나타낸 것
- B 그래프 : Composite signal을 Frequency domain으로 나타낸 것

### Bandwidth

Analog signal을 Frequency domain으로 표현할 때 최저 Frequency와 최고 Frequency의 차를 Bandwidth라고 한다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 10.png"></p>

위 그래프에서 Bandwidth는 800hz이다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 11.png"></p>

위 그래프에서 Bandwidth는 4000Hz이다.

## Digital Signal

Digital Signal은 아래의 그래프로 표현할 수 있다.

<p align="center"><img src="../../images/데이터통신/4) Data & Signal, Signal 그래프-Untitled 12.png"></p>

- Bitrate : Sender가 초당 전송한 Bit의 수 (단위 : bps, bit per second)
- Throughput : Recevier가 초당 수신한 Bit의 수  (단위 : bps, bit per second)

Bit를 표현하는 Level을 높이면 Bitrate는 높아지지만 노이즈에 취약해진다. 

노이즈는 에너지가 열 에너지로 변환 되는 등의 이유로 송신할 때 Amplitude보다 수신할 때 Amplitude가 낮은 경우를 말한다. 

예를 들어 B 그래프에서 Level 4가 5V이고, Leve 3이 3V이라 하자. Sender가 5V로 11을 표현하였으나 노이즈가 발생하여 Receiver가 2.5V로 수신했다. 이 경우 Sender가 보낸 11이라는 데이터를 Receiver가 10로 잘못 인식한다.