# 3) LCD

## LCD란?

LCD는 Liquid Crystal Display로서 백라이트에서 Liquid crystal panel에 빛을 비추어 화면을 출력하는 디스플레이이다.

<p align="center"><img src="../../images/마이크로프로세서/3) LCD-Untitled.png"></p>
Arduino에서 사용되는 LCD module은 문자를 8bit(1byte)로 구분한다. 이는 ASCII 코드의 단위와 같은데 특이하게도 ASCII 코드를 따르지 않고 자체 규칙을 사용한다. 신기하게도 일본어를 지원한다.

Arduino LCD에서 ‘V’는 Upper 4bits 0101, Lower 4bits 0100인 01010100로 변환된다.

## LCD module

Arduino에서 처리되는 정보를 Serial 통신을 통해 PC로 출력할 수 있다. 이와 비슷하게 PC와 연결하지 않고 Arduino 단독으로 사용할 때 Arduino의 정보를 출력하기 위해 LCD를 사용한다. 예를 들어, Arduino로 RC 카를 만들 때 RC 카의 속도를 LCD로 출력할 수 있다.

<p align="center"><img src="../../images/마이크로프로세서/3) LCD-Untitled 1.png"></p>
LCD module의 핀 기능은 아래와 같다. 이는 LCD module 종류에 따라 다를 수 있다.

- G : GND 연결 핀
- Vcc : 전원 연결 핀
- RS : Reset 핀
- E : Enable 핀
- D# : # 데이터 핀
    
    e.g. D1 → 1번 데이터 핀
    

### 가변 저항

<p align="center"><img src="../../images/마이크로프로세서/3) LCD-Untitled 2.png"></p>
LCD module에 가변 저항(potentiometer)를 연결함으로써 LCD의 밝기를 조절할 수 있다.

가변 저항의 값을 높이면 LCD의 밝기가 어두워지고, 낮추면 LCD의 밝기가 밝아진다.

## LCD module 사용법

LCD module을 사용하기 위해 Arduino에서 LiquidCrystal 라이브러리가 필요하다. LiquidCrystal에 정의된 주요 함수들은 다음과 같다.

- LiquidCrystal lcd(RS, E, D4, D5, D6, D7)
    
    LiquidCrystal 객체를 생성한다. 이때, Arduino에 매개 변수에 맞는 핀을 LCD module에 연결하고 해당 핀 번호를 매개 변수에 입력한다.
    
- lcd.begin(column, row)
    
    LCD module의 크기를 정의한다.
    
- lcd.clear()
    
    LCD module에 출력된 값을 모두 지우고 커서를 좌측 상단으로 옮긴다.
    
- lcd.home()
    
    LCD module의 커서를 가장 왼쪽으로 옮긴다.
    
- lcd.setCursor(column, row)
    
    LCD module의 커서를 row번째 줄, column번째로 옮긴다.
    
- lcd.print(data)
    
    현재 LCD module의 커서가 위치한 곳에 data를 출력한다.
    

### 주의해야할 점

- 커서 위치를 항상 생각해야한다. LCD의 내용은 현재 커서가 있는 곳에 출력된다.
- row, column 순서가 아닌 column, row 순서로 되어있다. 따라서 16x2 모듈을 사용할 때 2, 16과 같은 형식으로 사용해야한다.

### 예제

LCD 첫 줄에 “Arduino”를 출력하고 두 번째 줄에는 숫자를 출력하라. 숫자는 0부터 시작하여 100ms마다 1씩 증가한다.

소스 코드

```arduino
#include <LiquidCrystal.h> // LCD module 라이브러리

unsigned int time; // 전역 변수는 초기화하지 않으면 0으로 초기화

// LCD 객체 생성 LiquidCrytral(RS, E, D4, D5, D6, D7)
LiquidCrystal lcd(12, 11, 5, 4, 3, 2); // 실제로 해당 핀에 연결된 핀 번호를 입력
void setup()
{
    lcd.begin(16, 2);     // 16X2 LCD 설정
    lcd.print("Arduino"); // 첫 번째 줄에 Arduino 출력
}

void loop()
{
    lcd.setCursor(0, 1); // 두 번째 줄 첫 번째 칸으로 커서 이동
    lcd.print(time);     // time 변수값 출력
    ++time;              // time 1 증가
    delay(100);          // 100ms마다 반복
}
```

## LCD I2C

입출력 핀의 갯수가 제한된 Arduino에서 LCD에 사용되는 핀 갯수를 줄이기 위해 I2C를 사용한다.

### I2C란

I2C는 Inter Integrated Circuit의 약자로서 Serial 통신과 같이 통신 방식 중 하나이다.

I two C가 아닌 I square C라고 발음한다. TWI(Two-Wired Interface)라고 부르기도 한다.

<p align="center"><img src="../../images/마이크로프로세서/3) LCD-Untitled 3.png"></p>
I2C에는 2개의 데이터 Bus가 있다.

- SDA : Serial DAta
    
    데이터가 전송되는 Bus
    
- SCL : Serial Clock Line
    
    장치의 주소가 저장되는 주소
    

7이라는 데이터를 보낸다고 가정하자. SDA는 모든 장치에 7이라는 데이터를 보낸다. 그러나 모든 장치에 데이터가 전송되면 안되므로 SCL을 통해 SCL에 저장된 장치에만 데이터가 전송될 수 있도록 한다. SDA, SCL의 제어는 Micro-Controller Master가 담당하며 Arduino 환경에선 Arduino가 Micro-Controller Master가 된다.

LCD I2C는 SDA, SCL 2개의 데이터 Bus로 LCD를 제어할 수 있으므로 2개의 데이터 핀만으로 LCD를 제어할 수 있어 Arduino의 입출력 핀을 아낄 수 있다는 장점이 있다.

## LCD I2C 사용법

LCD I2C을 사용하기 위해 Arduino에서 LiquidCrystal_I2C 라이브러리가 필요하다. LiquidCrystal_I2C 에 정의된 주요 함수들은 다음과 같다.

- LiquidCrystal_I2C lcd(I2C addr, horizontal char, of vertical char)
    
    LCD I2C 객체를 생성한다.
    
    - I2C addr : LCD I2C module의 메모리 주소. 메모리 주소는 LCD I2C 모듈마다 다르다.
    - horizontal char : LCD의 가로 칸 수
    - vertical char : LCD의 세로 칸 수
- lcd.init()
    
    LCD를 초기화한다.
    
- lcd.clear()
    
    LCD에 출력된 값을 모두 지우고 커서를 좌측 상단으로 옮긴다.
    
- lcd.home()
    
    LCD의 커서를 가장 왼쪽으로 옮긴다.
    
- lcd.setCursor(column, row)
    
    LCD의 커서를 row번째 줄, column번째로 옮긴다.
    
- lcd.print(data)
    
    현재 LCD의 커서가 위치한 곳에 data를 출력한다.
    
- lcd.noBacklight()
    
    LCD의 백라이트를 끈다.
    
- lcd.backlight()
    
    LCD의 백라이트를 켠다.
    

### 예제

Serial 통신을 이용해 PC로부터 입력받은 데이터를 LCD에 출력하라.

소스 코드

```arduino
#include <Wire.h>                   // I2C 통신 라이브러리
#include <LiquidCrystal_I2C.h>      // I2C LCD 라리브러리
LiquidCrystal_I2C lcd(0x27, 16, 2); // PCF8574 모델의 LCD address:0x27, 16X2 LCD
void setup()
{
    Serial.begin(9600); // Serial 통신 속도 9600 설정

    lcd.init();      // LCD 초기화
    lcd.clear();     // LCD 출력 내용을 지운다.
    lcd.backlight(); // 백라이트를 켠다.

    // 첫 번째 줄에 Arduino LCD, 두 번째 줄에 Welcome을 출력
    lcd.setCursor(0, 0);
    lcd.print("Arduino LCD");
    delay(1000);

    lcd.setCursor(0, 1);
    lcd.print("Welcome");
    delay(250);

    // LCD 백라이트를 두 번 점멸
    lcd.noBacklight();
    delay(250);
    lcd.backlight();
    delay(250);
    lcd.noBacklight();
    delay(250);
    lcd.backlight();

    // Open Serial Monitor, Type to display 출력
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Open Serial Mntr");
    lcd.setCursor(0, 1);
    lcd.print("Type to display");
}
void loop()
{
    if (Serial.available()) // 시리얼 통신 수신 값이 있을때
    {
        delay(100);
        lcd.clear(); // 출력 내용 모두 삭제

        // 첫 번째 줄에 Message from PC 출력
        lcd.setCursor(0, 0);
        lcd.print("Message from PC");

        // 두 번째 줄에 PC에서 전송된 데이터를 출력
        lcd.setCursor(0, 1);
        while (Serial.available() > 0)
        {
            lcd.write(Serial.read());
        }
    }
}
```