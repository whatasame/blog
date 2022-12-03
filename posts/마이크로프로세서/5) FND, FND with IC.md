# 5) FND, FND with IC

## FND

FND는 Flexible Numeric Display로서 여러 개의 LED로 하나의 숫자를 표현하는 장치입니다. 주로 7-Segment Display로 불립니다. 

<p align="center"><img src="../../images/마이크로프로세서/5) FND, FND with IC-Untitled.png"></p>

FND에는 8개의 LED가 있습니다. 각각의 LED에는 +극과 -극이 필요하기에 총 16개의 선이 필요합니다. 그러나 RGB LED와 같이 여러 개의 +극이 -극을 공유하도록 설계하면 선의 개수를 줄일 수 있습니다. FND에서는 8개의 +극이 2개의 -극을 공유합니다. 이러한 FND를 Cathode FND라 하고, +극을 공유하는 경우 Anode FND라고 부릅니다.

### FND로 숫자 표현하기

<p align="center"><img src="../../images/마이크로프로세서/5) FND, FND with IC-Untitled 1.png"></p>

FND의 각각의 핀에 HIGH와 LOW를 줌으로써 숫자를 표현할 수 있습니다.

**예제**

FND의 숫자를 0부터 9까지 표시하는 코드

```arduino
// 0~9까지 LED 표시를 위한 상수 설정 - dot, g, f, e, d, c, b, a 순서
const byte number[10] = {
		// B = Binary
    B00111111, // 0 
    B00000110, // 1
    B01011011, // 2
    B01001111, // 3
    B01100110, // 4
    B01101101, // 5
    B01111101, // 6
    B00000111, // 7
    B01111111, // 8
    B01101111, // 9
};

void setup()
{
    // 2~9번 핀을 a b c d e f g dot 의 순서로 사용한다. 출력모드로 초기화 시킨다.
    for (int i = 2; i <= 9; ++i)
        pinMode(i, OUTPUT);
    digitalWrite(9, LOW); // 점은 표시하지 않는다
}
void loop()
{
    // k값을 0~9로 변화시킨다.
    for (int k = 0; k <= 9; ++k)
    {
        fndDisplay(k); // k값을 출력한다
        delay(1000);
    };
}

void fndDisplay(int displayValue) // FND 점등 사용자 정의 함수
{
    boolean bitValue;
    for (int i = 2; i <= 9; ++i)
    {
        // 2~9번핀에 모두 LOW 신호를 줘서 소등시킨다
        digitalWrite(i, LOW);
    };
    for (int i = 0; i <= 7; ++i)
    {
        // number 상수의 하나의 비트값을 읽는다
        bitValue = bitRead(number[displayValue], i);
        // 앞서 읽은 비트값을 2~9번핀에 출력시킨다
        digitalWrite(i + 2, bitValue);
    };
}
```

## FND with IC

Common cathode를 활용하여 16개의 핀을 8개의 핀으로 줄였지만 14개의 디지털 입출력 핀을 가지고 있는 Arduino에겐 여전히 많은 숫자입니다. I2C LED처럼 FND도 IC를 활용하여 사용하는 핀의 개수를 줄일 수 있습니다.

FND를 표시하는데 사용되는 핀의 개수는 8개입니다. 즉, 8가지의 경우의 수를 가지므로 이는 3bit로 표현할 수 있습니다. 

<p align="center"><img src="../../images/마이크로프로세서/5) FND, FND with IC-Untitled 2.png"></p>

74595 IC의 DS, SHCP, STCP와 Arduino를 연결하면 3개의 디지털 입출력 핀으로 FND를 컨트롤 할 수 있습니다. 이때 shiftOut()이라는 함수가 필요합니다.

**예제**

IC를 활용하여 FND의 숫자를 0부터 9까지 표시하는 코드

```arduino
// 0~9까지 LED 표시를 위한 상수 설정
const byte number[10] = {
    B00111111, // 0
    B00000110, // 1
    B01011011, // 2
    B01001111, // 3
    B01100110, // 4
    B01101101, // 5
    B01111101, // 6
    B00000111, // 7
    B01111111, // 8
    B01101111, // 9
};
int ds = 2;   // 74595 의 DS핀을 Arduino 의 2 번 핀에 연결
int shcp = 3; // 74595의 STCP핀을 Arduino의 3 번 핀에 연결
int stcp = 4; // 74595의 SHSP핀을 Arduino의 4 번 핀에 연결
void setup()
{
    // 2~9번핀을 출력으로 초기화 시킨다
    for (int i = 2; i <= 9; ++i)
    {
        pinMode(i, OUTPUT);
    };
    digitalWrite(9, LOW); // 점은 표시하지 않는다
}
void loop()
{
    // k값을 0~9로 변화시킨다.
    for (int k = 0; k <= 9; ++k)
    {
        fndDisplay74595(k); // k값을 출력한다
        delay(1000);        // 1초간 지연시킨다.
    };
}
// LED 점등
void fndDisplay74595(int displayValue)
{
    // STCP에 LOW 신호를 보내서 74595로 데이터전송을 시작한다.
    digitalWrite(stcp, LOW);
    // shiftOut 명령어로 74595에 출력을 보낸다.
    shiftOut(ds, shcp, MSBFIRST, number[displayValue]);
    // STCP에 HIGH 신호를 보내서 74595로 데이터전송을 종료한다.
    digitalWrite(stcp, HIGH);
}
```

순차적인 데이터인 Serial 데이터를 병렬 데이터인 Parallel 데이터로 보내기 위해 **shiftOut()을 사용**합니다. MSBFIRST는 비트 데이터의 앞자리부터 보낸다는 뜻입니다.