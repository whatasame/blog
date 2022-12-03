# 6) 잔상을 활용한 LED

이번 시간에는 Arduino의 디지털 입출력 핀보다 훨씬 많은 LED를 잔상을 활용하여 제어하는 방법에 대해 알아보도록 하겠습니다.

## 4-digit FND

<p align="center"><img src="../../images/마이크로프로세서/6) 잔상을 활용한 LED-Untitled.png"></p>

4자리의 FND를 표시하려면 8x4 = 32개의 디지털 입출력 핀이 필요합니다. 그러나 우리는 전기 신호의 속도가 인간의 눈이 인식할 수 있는 속도보다 훨씬 빠르다는 것을 이용하여 핀 8개만으로 4자리 FND를 표시할 수 있습니다.

방법은 간단합니다. 4자리를 표시할 때 1자리씩 표시하고 다른 자리로 옮겨 표시하면 됩니다. 이것을 매우 빠르게 하면 인간의 눈은 4자리의 FND가 한 번에 표시되는 것처럼 인식합니다.

사용 방법도 1자리 FND와 크게 차이나지 않습니다. 각각의 FND의 cathode 핀을 켜고 끔으로써 해당 자리의 FND를 켜고 끌 수 있습니다. 다만**, FND의 cathode 핀에 LOW를 주는 것이 해당 FND를 켠다**는 것을 명심해야합니다.

**예제**

1. FND 4자리 전부를 켜고 0000, 1111, … , 9999를 출력하는 코드
    - 2~9번 핀 : FND 개별의 LED를 켜고 끄는 핀
    - 10~13번 핀 : FND 1번, 2번, 3번, 4번 전체를 켜고 끄는 핀
    
    ```arduino
    // 0~9까지 LED 표시를 위한 상수
    const byte number[10] = {
        // dot gfedcba
        B00111111,    // 0
        B00000110,    // 1
        B01011011, // 2
        … B01111111, // 8
        B01101111,    // 9
    };
    
    // 표시할 숫자 변수
    int count = 0;
    
    void setup()
    {
        // 2~9번 핀을 a b c d e f g dot 의 순서로 사용한다.
        // 10~13번 핀을 Digit 1~4 의 순서로 사용한다.
        for (int i = 2; i <= 13; ++i)
        {
            pinMode(i, OUTPUT); // 2~13번핀을 출력으로 설정한다.
        };
        // 4 digit와 연결된 10~13번핀에 모두 LOW 신호를 줘서 점등시킨다
        for (int i = 10; i <= 13; ++i)
        {
            digitalWrite(i, LOW);
        };
    }
    
    void loop()
    {
        // count 변수값을 FND에 출력한다.
        fndDisplay(count);
        // count 변수값이 0~9의 범위를 갖도록한다.
        if (count >= 9)
            count = 0;
        else
            ++count;
        delay(1000);
    }
    
    // LED 켜는 루틴
    void fndDisplay(int displayValue)
    {
        // bitValue 변수를 선언한다.
        boolean bitValue;
        // 2~9번핀에 모두 LOW 신호를 줘서 소등시킨다.
        for (int i = 2; i <= 9; ++i)
        {
            digitalWrite(i, LOW);
        };
    
        for (int i = 0; i <= 7; ++i)
        {
            // number 상수의 하나의 비트값을 읽는다.
            bitValue = bitRead(number[displayValue], i);
            // 앞서 읽은 비트값을 2~9번핀에 출력시킨다.
            digitalWrite(i + 2, bitValue);
        };
    }
    ```
    

1. FND를 0000부터 9999까지 매 초마다 1씩 증가하는 코드
    - millis() : 타임 스탬프를 반환하는 함수
        
        delay()의 경우 지정된 시간을 쉬고 난 후 다른 코드를 처리하는 시간은 고려하지 않아 지정한 시간보다 더 많이 쉬는 경우가 있습니다. millis()를 이용하여 명령어를 시작하기 전에 타임 스탬프 값과 명령어를 실행한 후의 타임 스탬프 값을 저장한 후, 둘의 차이로 대기 시간을 정할 수 있습니다. 이렇게 대기 시간을 정하면 코드를 처리하는 시간까지 포함하여 대기하므로 원하는만큼 정확히 쉴 수 있는 장점이 있습니다.
        
    
    ```arduino
    const byte number[10] = {
        // 0~9까지 LED 표시를 위한 상수
        B00111111, // 0 
        … B01101111, // 9
    };
    
    const byte digitFND[4] = {13, 12, 11, 10}; // 4개의 digit에 연결된 핀 설정
    int count = 0; // 표시할 숫자 변수
    int value[4]; // 각 자릿수를 저장하기 위한 변수
    int digitSelect; // 4개의 digit에 각각 다른 숫자를 표시하기 위해 사용하는 변수
    
    // 시간을 측정하는데 사용되는 변수
    long sampleTime;
    int count5ms;
    
    void setup()
    {
        // 2~9번 핀을 a b c d e f g dot 의 순서로 사용한다.
        // 10~13번 핀을 Digit 1~4 의 순서로 사용한다.
        for (int i = 2; i <= 13; ++i)
        {
            pinMode(i, OUTPUT); // 2~13번핀을 출력으로 설정한다.
        }
        // 4 digit와 연결된 10~13번핀에 모두 HIGH 신호를 줘서 소등시킨다.
        for (int i = 10; i <= 13; ++i)
        {
            digitalWrite(i, HIGH);
        }
    }
    
    void loop()
    {
        sampleTime = millis(); // 현재 시간을 저장한다.
        // count 변수값을 FND에 출력한다.
        fndDisplay(digitSelect, value[digitSelect - 1]); // e.g. 1131 -> MSB순으로 1표시, 1표시, 3표시, 1표시
        ++digitSelect;
    
        if (digitSelect >= 5)
            digitSelect = 1;
    
        // count 변수값이 0~9의 범위를 갖도록한다.
        if (count >= 9999)
            count = 0;
        else
        {
            // 앞서 저장한 시간에서 현재까지의 시간이 5ms일 경우에 다음 명령어를 실행한다.
            while (millis() - sampleTime < 5)
                ++count5ms;
    
            if (count5ms > 200)
            { //
                // 5ms * 100 = 1s 가 되었을 때 count를 하나 올려준다
                ++count;
    
                // 변수를 각 자릿수로 나눈다. 만약 3512일 경우
                value[3] = count / 1000; // 3에 해당
                value[2] = (count - (value[3] * 1000)) / 100; // 5에 해당
                value[1] = (count - (value[3] * 1000) - (value[2] * 100)) / 10; // 1에 해당
                value[0] = count - (value[3] * 1000) - (value[2] * 100) - (value[1] * 10); // 2에 해당
                count5ms = 0;
            }
        }
    }
    
    // LED 켜는 루틴
    void fndDisplay(int digit, int displayValue)
    {
        // bitValue 변수를 선언한다.
        boolean bitValue;
        // 4 digit와 연결된 10~13번핀에 모두 HIGH 신호를 줘서 소등시킨다.
        for (int i = 1; i <= 4; ++i)
        {
            digitalWrite(digitFND[i - 1], HIGH);
        }
        // FND에 원하는 숫자를 표시한다.
        for (int i = 0; i <= 7; ++i)
        {
            // number 상수의 하나의 비트값을 읽는다.
            bitValue = bitRead(number[displayValue], i);
            // 앞서 읽은 비트값을 2~9번핀에 출력시킨다.
            digitalWrite(i + 2, bitValue);
        }
        // 4 digit중 표시를 원하는 digit만 켠다
        for (int i = 1; i <= 4; ++i)
        {
            // 표시하기 원하는 자릿수는 LOW신호를 주어 켜고 나머진 OFF시킨다.
            if (digit == i)
                digitalWrite(digitFND[i - 1], LOW);
            else
                digitalWrite(digitFND[i - 1], HIGH);
        }
    }
    ```
    

## Dot Matrix

8x8의 64개 LED로 이루어진 Dot Matrix도 잔상 효과를 이용하여 적은 수의 디지털 입출력 핀으로 제어할 수 있다.

<p align="center"><img src="../../images/마이크로프로세서/6) 잔상을 활용한 LED-Untitled 1.png"></p>

**Dot Matrix의 경우 행과 열의 극이 다르다.** 

- 행(Row) : anode → HIGH를 주면 점등, LOW를 주면 소등
- 열(Col) : cathode → LOW를 주면 점등, HIGH를 주면 점등

**예제**

1. 한 줄씩 점등하는 Dot Matrix 코드
    - 디지털 입력 핀의 개수가 부족하여 아날로그 입력 핀까지 사용하였다.
        - 디지털 입력 핀 번호 : 0~13(14개)
        - 아날로그 입력 핀 번호 : 14~19(A0~A5, 6개)
    - 하나의 행을 켠 후 그 행에 해당하는 모든 열을 켠다. 이후 다른 행에 대해서 반복.
    
    ```arduino
    const int colPins[] = {2, 3, 4, 5, 6, 7, 8, 9};         // C8, C7, C6, C5, C4, C3, C2, C1
    const int rowPins[] = {10, 11, 12, 15, 16, 17, 18, 19}; // R8, R7, R6, R5, R4, R3, R2, R1
    
    void setup()
    {
        for (int i = 0; i < 8; i++)
        {
            // 행을 출력으로 설정한다
            pinMode(colPins[i], OUTPUT);
            // 열을 출력으로 설정한다
            pinMode(rowPins[i], OUTPUT);
        }
    }
    
    void loop()
    {
        for (int column = 0; column < 8; column++)
        {
            // 행을 모두 초기화 한다
            colClear();
            // 현재의 행만 켠다
            digitalWrite(colPins[column], LOW);
    
            // 열을 하나씩 켠다
            for (int row = 0; row < 8; row++)
            {
                digitalWrite(rowPins[row], HIGH);
                delay(100);
            }
            // 열을 모두 초기화 한다
            rowClear();
        }
        rowClear(); // 모든 행을 반복했으면 열을 모두 소등한다
    }
    
    // 행을 모두 초기화하는 루틴
    void colClear()
    {
        for (int i = 0; i < 8; i++)
        {
            digitalWrite(colPins[i], HIGH);
        }
    }
    
    // 열을 모두 초기화하는 루틴
    void rowClear()
    {
        for (int i = 0; i < 8; i++)
        {
            digitalWrite(rowPins[i], LOW);
        }
    }
    ```
    
2. Dot Matrix를 활용하여 움직이는 화살표를 표시하는 코드
    
    <p align="center"><img src="../../images/마이크로프로세서/6) 잔상을 활용한 LED-Untitled 2.png"></p>
    
    ```arduino
    byte arrow1[] = { // 첫 번째 화살표
        B00000000,
        B00000000,
        B00011000,
        B00100100,
        B01011010,
        B10100101,
        B01000010,
        B10000001};
    byte arrow2[] = { // 두 번째 화살표
        B00000000,
        B00011000,
        B00100100,
        B01011010,
        B10100101,
        B01000010,
        B10000001,
        B00000000};
    byte arrow3[] = { //세 번째 화살표
        B00011000,
        B00100100,
        B01011010,
        B10100101,
        B01000010,
        B10000001,
        B00000000,
        B00000000};
    
    const int columnPins[] = {2, 3, 4, 5, 6, 7, 8, 9};      // 행에 연결된 핀
    const int rowPins[] = {10, 11, 12, 15, 16, 17, 18, 19}; // 열에 연결된 핀
    void setup()
    {
        for (int i = 0; i < 8; i++)
        {
            // 행 핀을 출력으로 설정
            pinMode(columnPins[i], OUTPUT);
            // 열 핀을 출력으로 설정
            pinMode(rowPins[i], OUTPUT);
            // 행 핀에 모두 HIGH를 넣어 모든 LED 를 소등한다
            digitalWrite(columnPins[i], HIGH);
        }
    }
    
    void loop()
    {
        showArrow(arrow1, 80); // 첫 번째 화살표 출력
        showArrow(arrow2, 80); // 두 번째 화살표 출력
        showArrow(arrow3, 80); // 세 번째 화살표 출력
        delay(300);            // 0.3초간 지연시킨다.
    }
    
    // 실제 Dot matrix를 출력하는 루틴. image엔 화살표 이미지 데이터가 입력되고 time엔 실행할 시간이 입력됨
    void showArrow(byte *image, unsigned long time)
    {
        // 현재 시간 타임스탬프
        unsigned long start = millis();
    
        // time ms동안 while문 내의 명령을 실행한다.
        while (start + time > millis())
        {
            for (int row = 0; row < 8; row++)
            {
                // 해당하는 행에 LED를 출력하기 위해 HIGH를 넣는다
                digitalWrite(rowPins[row], HIGH);
    
                for (int column = 0; column < 8; column++)
                {
                    // image 변수를 한 자리씩 확인한다. 
                    boolean pixel = bitRead(image[row], column);
                    if (pixel == 1) // 확인한 값이 1일 경우 해당 열의 LED를 점등한다.
                        digitalWrite(columnPins[column], LOW);
    
                    // 300 마이크로 초 만큼 지연시킨다 = LED를 300 마이크로 초 만큼 켜고 있는다.
                    delayMicroseconds(300);
    
                    // 점등시킨 LED를 소등한다
                    digitalWrite(columnPins[column], HIGH);
                }
    
                // 현재의 행에 LOW 신호를 출력하여 해당 열의 LED를 모두 소등한다
                digitalWrite(rowPins[row], LOW);
            }
        }
    }
    ```