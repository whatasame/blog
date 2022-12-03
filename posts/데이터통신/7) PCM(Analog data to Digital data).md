# 7) PCM(Analog data to Digital data)

이전까지 Digital signal을 Digital data로 바꾸는 Line coding에 대해 알아봤습니다. 그렇다면 Analog data는 어떻게 전송될까요?

## PCM

Analog data의 전송은 Analog data 상태로 이루어지는 것이 아니라 Digital data로 변환된 후 전송됩니다. 이렇게 Analog data를 Digital data로 변환하는 것을 PCM(Pulse code modulation)이라고 합니다.

Analog data의 값은 연속적이고 특정 범위 안에 속하는 값의 수가 무한이라는 특징을 가지고 있습니다. 따라서 PCM은 연속적이고 무한이라는 Analog data의 특징을 제거할 필요가 있습니다.

PCM의 과정은 3단계로 이루어집니다.

- Sampling
- Quantization
- Binary encoding

### Sampling

Sampling은 Analog data의 연속적인 특징을 제거하는 작업입니다. 

<p align="center"><img src="../../images/데이터통신/7) PCM(Analog data to Digital data)-Untitled.png"></p>

위 그림과 같이 Analog data를 일정 간격의 시간을 두고 자릅니다. Analog data의 특정 값을 표현할 수 있어 연속적인 성질이 없어집니다.

⭐ **Nyquist theorem**

Sampling을 할 때 일정 시간 간격은 어떻게 정할까요? Nyquist theorem에 의하면 **시간 간격은 Analog data의 Frequency의 2배**가 되어야한다고 합니다. 

<p align="center"><img src="../../images/데이터통신/7) PCM(Analog data to Digital data)-Untitled 1.png"></p>

구분하는 시간 간격을 너무 작게 잡으면 생성되는 Digital data 양이 많아져 전송량이 많아지는 Oversampling이 발생합니다. 또, 시간 간격을 너무 크게 잡으면 손실이 많아져 원본 Analog data를 표현할 수 있는 정확도가 떨어집니다. 

그러나 Sampling은 Analog data의 연속적인 성질만 없앨 뿐, 특정 범위 내 값의 수가 무한하다는 점은 해결하지 못합니다. 예를 들어, 크기가 큰 Analog data가 올 경우 일정 시간 간격으로 구분한 데이터의 경우의 수가 매우 많을 것이기 때문입니다. 이러한 경우의 수를 bit로 표현한다고 하면 그만큼 복잡도가 높아집니다. 따라서 Analog data의 특정 범위 내 값의 수가 무한이라는 성질도 제거할 필요가 있습니다.

### Quantization

Quantization은 Analog data의 특정 범위 내 값의 수가 무한이라는 특징을 제거하는 작업입니다.

<p align="center"><img src="../../images/데이터통신/7) PCM(Analog data to Digital data)-Untitled 2.png"></p>

Quantization은 Analog data를 일정한 Amplitude 간격으로 나눕니다. Analog data에서 Max amplitude와 Min amplitude의 차를 N개의 간격으로 나누면 Quantization의 간격이 됩니다.

위 그림에서는 Max Amplitude를 20V, Min amplitude를 -20V로 잡고 간격을 8개로 나누었습니다. 따라서 간격의 크기는 $\frac{40}{8} = 5V$가 됩니다.

- Quantization Level = 8
- Max amplitude = 20V
- Min amplitude = -20V

이후, 각 간격에 속하는 값(Quantization value)들은 간격의 중앙 값이 됩니다. 예를 들어, 위 그래프의 16.2와 19.7는 3D(15V)~4D(20V) 간격에 속하므로 두 값 모두 17.5V로 표현됩니다.

### Binary encoding

 Sampling을 통해 일정 시간 간격으로 나누고, Quantization을 통해 일정 Amplitude 간격으로 나눠 Ananlog data를 Digital data로 표현할 수 있습니다.

Binary encoding에서는 Quantization에서 구한 간격에 번호를 붙여 해당 데이터를 표현합니다. 예를 들어, 위 그래프에서 16.2는 7번째 간격인 3D~4D에 속하므로 7로 표현됩니다. -11.3은 1번째 간격인 -2D~-3D에 속하므로 1로 표현됩니다.

Quantization Level이 8이므로 각 간격을 구분하기 위해 필요한 비트 수는 3bit입니다. 따라서 16.2는 $111_{(2)}$, -11.3은 $001_{(2)}$로 인코딩됩니다. 

Quantization에서 간격을 좁게 할 수록(More quantization level) 다양한 값을 나타낼 수 있어 손실이 사라지겠지만 그만큼 값을 표현하는데 필요한 bit수가 늘어나므로 높은 Bitrate가 필요하게 됩니다.

### Nonlinear PCM

지금까지 배운 PCM 기법은 간격의 일정한 Linear PCM입니다. Linear PCM에서는 자주 등장하는 값과 자주 등장하지 않는 값들이 동일한 간격으로 표현됩니다.

<p align="center"><img src="../../images/데이터통신/7) PCM(Analog data to Digital data)-Untitled 3.png"></p>

효율성을 고려할 때, 자주 등장하지 않는 값들은 넓은 간격으로 표현하고, 자주 등장하는 값들은 좁은 간격으로 표현할 수 있습니다. 이러한 방법을 Nonlinear PCM이라고 합니다.

### 예제

Q) 

사람의 목소리는 0Hz ~ 4000Hz로 표현할 수 있다. sample을 구분하는 bit가 8 bit일 때 요구되는 Bitrate는 몇인가?

A) 

$$
Sampling\ rate = 4000\times2=8000\ samples/s\\Bit\ rate = 8000\times8=64,000bps =64kbps 
$$