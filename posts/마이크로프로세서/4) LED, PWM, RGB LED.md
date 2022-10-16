# 4) LED, PWM, RGB LED

## LED 개요

LED는 Light Emitting Diode의 약자로서 빛을 내는 다이오드 입니다. LED는 고효율, 반영구적 수명이라는 특성을 가지고 있어 다양한 곳에서 많이 사용됩니다.

### LED의 고효율

LED가 고효율인 이유를 일반 형광등과 비교해보겠습니다. 대개 형광등의 소비전력은 35W입니다. 이에 비해 LED는 0.375W(5V $\times$ 0.015A)의 소비 전력을 가지고 있습니다. 이처럼 형광등 하나에 들어가는 전력으로 LED를 약 467개 사용할 수 있다는 점에서 고효율입니다. 형광등과 같은 밝기를 가지는 LED의 소비 전력은 15W입니다.

### LED 빛의 파장

LED의 색마다 파장이 다릅니다. 무지개 순서는 빛의 파장의 내림차순과 같습니다.

<p align="center"><img src="../../images/마이크로프로세서/4) LED, PWM, RGB LED-Untitled.png"></p>

빨간색 LED는 650nm의 파장을, 초록색 LED는 550nm의 파장을, 파란색 LED는 450nm의 파장을 가지고 있습니다.

### LED의 연결

<p align="center"><img src="../../images/마이크로프로세서/4) LED, PWM, RGB LED-Untitled 1.png"></p>

LED 기호는 위처럼 표기합니다. Anode는 +극을, Cathode는 -극을 나타냅니다.

LED를 연결할 땐 반드시 저항을 사용해야합니다. 저항을 사용하지 않고 연결할 경우 LED가 버틸 수 없는 전류가 흘러 LED 소자가 탈 수 있습니다. 우리가 사용할 LED의 적정 전류는 15mA이므로 옴의 법칙에 따라 330 $\Omega$ 저항을 사용해야합니다.

또한, 저항을 연결할 때 반드시 직렬로 연결해야합니다. 전류는 저항이 적은 쪽으로 흐르는 성질이 있습니다. 따라서, 저항을 병렬로 연결할 경우 저항이 없는 쪽으로 전류가 흐르므로 저항이 없는 것과 같습니다.

<aside>
💡 5V = 330 $\Omega\ \times$ 15mA

</aside>

**예제**

LED A와 B를 3번 핀, 5번 핀에 연결한 후 서로 번갈아 점등하는 코드

```arduino
const int ledA = 3;
const int ledB = 5;
void setup()
{
		// ledA와 ledB의 입출력 모드를 출력을 설정
    pinMode(ledA, OUTPUT);
    pinMode(ledB, OUTPUT);
}
void loop()
{
		// LED A를 켜고 B를 끈다.
    digitalWrite(ledA, HIGH);
    digitalWrite(ledB, LOW);
    delay(100);
		
		// LED B를 켜고 A를 끈다.
    digitalWrite(ledA, LOW);
    digitalWrite(ledB, HIGH);
    delay(100);
}
```

## PWM

LED를 단순히 켜고 끄는 것이 아닌, LED의 밝기를 조절(Dimming)하려면 어떻게 해야할까요? Arduino에서 지원하는 PWM 기능을 사용하면 됩니다.

PWM이란 Pulse Width Modulation의 약자로서 Arduino에서 디지털 신호를 통해 아날로그 신호를 만드는 것을 말합니다.

<p align="center"><img src="../../images/마이크로프로세서/4) LED, PWM, RGB LED-Untitled 2.png"></p>

Arduino가 출력하는 HIGH의 간격을 조절하여 디지털 신호의 세기를 줄여 마치 아날로그 신호인 것처럼 전압을 줄 수 있습니다.

### 사용법

Arduino의 PWM 핀(3, 5, 6, 9, 10, 11)에 연결한 후 analogWrite()로 PWM을 사용할 수 있습니다. digitalWrite에 입력되는 값이 0(LOW)과 1(HIGH)인 반면, analogWrite()의 값은 0~255까지 입력할 수 있습니다.

digitalWrite()의 경우 pinMode()를 통해 사용하는 핀의 입출력 모드를 지정해야하지만, analogWrite()의 경우 analogWrite()안에 pinMode(Pin, OUTPUT)가 내장되어 있어 입출력 모드 지정 없이 사용할 수 있습니다.

**예제**

두 LED가 서로 반대로 밝아지고 어두워지는 코드

```arduino
const int ledA = 3; // LED A를 3번핀에 연결
const int ledB = 5; // LED B를 5번핀에 연결

int brightness = 0; //밝기를 조절하기 위한 변수
int increment = 1;  //밝기 변수 증감을 위한 변수

void setup()
{
    // analogWrite 핀에는 별도의 설정이 불필요하다.
}
void loop()
{
    analogWrite(ledA, brightness);       // LED A 밝기 조절
    analogWrite(ledB, 255 - brightness); // LED B 밝기 조절
    brightness = brightness + increment; // 밝기 조절
    if ((brightness >= 255) || (brightness <= 0))
        increment = -increment; // 밝기 변수 증감 방향 변경
    delay(10);                  // 0.01 초간 지연
}
```

## RGB LED

3개의 LED를 조합하여 다양한 색을 나타낼 수 있습니다.

<p align="center"><img src="../../images/마이크로프로세서/4) LED, PWM, RGB LED-Untitled 3.png"></p>

이때, 3개의 LED에 각각 +극과 -극을 연결하면 총 6개의 선이 필요합니다. 그러나 위 사진처럼 3개의 +극이 하나의 -극을 공유하도록 할 수 있습니다. 이렇게 여러 개의 +극이 하나의 -극을 공유하는 것을 공통의 Cathode를 갖는다 하여 **Common cathode**라고 합니다. 반대의 경우 Common anode라고 합니다.

**예제**

RGB LED의 색을 계속 바꾸는 코드

```arduino
const int RedLed = 3;   // R를 3번핀에 연결
const int GreenLed = 5; // G를 5번핀에 연결
const int BlueLed = 6;  // B를 6번핀에 연결

void ledOutput(int Red, int Green, int Blue) // 사용자 정의 함수
{
    analogWrite(RedLed, Red);
    analogWrite(GreenLed, Green);
    analogWrite(BlueLed, Blue);
}

void setup()
{
    ledOutput(255, 0, 0);
    delay(1000);
    ledOutput(0, 255, 0);
    delay(1000);
    ledOutput(0, 0, 255);
    delay(1000);
}
void loop()
{
    for (int i = 0; i <= 255; ++i)
    {
        ledOutput(255, i, 0);
        delay(10);
    }
    for (int i = 0; i <= 255; ++i)
    {
        ledOutput(0, 255, i);
        delay(10);
    }
    for (int i = 0; i <= 255; ++i)
    {
        ledOutput(i, 0, 255);
        delay(10);
    }
    for (int i = 0; i <= 255; ++i)
    {
        ledOutput(i, 255, 255);
        delay(10);
    }
    for (int i = 0; i <= 255; ++i)
    {
        ledOutput(255, i, 255);
        delay(10);
    }
    for (int i = 0; i <= 255; ++i)
    {
        ledOutput(255, 255, i);
        delay(10);
    }
}
```