# #013 MIPS Single cycle CPU -  Datapath

이번 글에서는 저번 글에서 배운 MIPS CPU의 Fetch, Decode, Execute를 하나로 합쳐 하나의 datapath를 만들어보도록 하겠습니다.

## Single cycle design

Single cycle이란 Fetch, Decode, Execute의 과정이 하나의 Clock cylce에 진행되는 것을 말합니다. 각각의 세 단계가 하나의 Clock cycle에 동작하기 위해선 아래와 같은 것을 고려해야합니다.

- Datapath의 논리 회로 모듈은 중복될 수 있다.
    
    예를 들어, 흐름 제어 명령어인 Branch의 경우 rs 값과 rt 값을 비교하는 연산과 target address를 구하는 연산 2개가 필요합니다. 그러나, Single cycle에서 하나의 논리회로 모듈은 한 번만 사용할 수 있으므로 복잡도가 낮은 target address를 구하는 연산은 별도의 adder를 두어 처리합니다.
    
- 하나의 논리 회로 모듈에 여러 입력 값이 들어갈 때, 이를 선택할 Multiplexer가 필요하다.
    
    예를 들어, Branch 명령어를 처리할 경우 PC 값은 PC+4과  PC + 4 + offset 중에서 하나로 업데이트 되어야합니다. 이때, Multiplexer를 두어 rs와 rt의 비교 결과 값에 따라 두 값 중 하나를 선택합니다. 
    
- 논리 회로 모듈이 동작할 기준이 되는 Clock이 필요하다.
    
    Clock methodology를 이야기할 때, Clock 신호에 맞게 sequential logic에 접근하여 유효한 데이터를 읽고 쓴다고 하였습니다. 따라서, Single cycle CPU를 설계할 때도 sequential logic에 해당하는 논리 회로 모듈에 Clock 신호를 줘야합니다.
    
- 레지스터 파일, Data memory를 읽고 쓰는 제어 신호가 필요하다.
    
    명령어에 따라 레지스터 파일과 Data memory를 읽을 수도, 쓸 수도 있기 때문에 명령어의 의도대로 값을 읽고 쓸 수 있도록 제어 신호가 필요합니다.
    

## 모듈 합치기

지금까지 배운 Fetch, Decode, Execute 모듈을 하나로 합쳐보겠습니다.

### Multiplexer insertion

산술 논리 연산과 메모리 접근 연산에 필요한 datapath는 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23013%20MIPS%20Single%20cycle%20CPU%20-%20Datapath/Untitled.png"></p>

위 그림에서 빨간색 원과 파란색 원이 하나의 논리 회로 모듈에 여러 입력 값이 들어가게 됩니다. 따라서, 우리는 Multiplexer를 통해 하나의 값을 선택하여 논리 회로 모듈의 입력 값으로 제어해야합니다. 

<p align="center"><img src="../../images/Computer architecture/%23013%20MIPS%20Single%20cycle%20CPU%20-%20Datapath/Untitled%201.png"></p>

위 그림은 Multiplexer를 추가한 그림입니다. Multiplexer는 여러 입력 값 중에서 하나의 값을 선택하는 논리 회로 모듈입니다. 선택이라는 것을 하기 때문에 선택의 기준이 되는 별도의 제어 신호가 필요합니다.

제어 신호는 Decode 단계에서 opcode의 값이 Control Unit에 전달되어 결정됩니다.

- 빨간색 Multiplexer
    
    산술 논리 연산 명령어의 rt에 해당하는 레지스터의 값과 메모리 접근 명령어의 offset에 해당하는 값 중에서 하나를 선택합니다.
    
- 파란색 Multiplexer
    
    산술 논리 연산 명령어의 rs와 rt를 계산한 값과 메모리 접근 명령어 Load의 결과 값 중 하나를 선택합니다.
    

### Branch datapath

흐름 제어 명령어 Branch를 위해 필요한 datapath는 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23013%20MIPS%20Single%20cycle%20CPU%20-%20Datapath/Untitled%202.png"></p>

Branch의 결과로 PC 값은 PC+4과  PC + 4 + offset 중에서 하나로 업데이트 되어야합니다. 

여러 입력 값 중 하나의 값을 선택하여 PC에 업데이트해야 하므로 Multiplexer가 필요합니다. 이때, 선택의 기준은 PCSrc로서 rs와 rt의 비교 결과가 됩니다. 

예를 들어, `beq`의 경우 rs와 rt의 값이 다르면 PC + 4를 선택하는 신호를 Multiplexer로 보낼 것입니다.

### Clock distribution

Sequential logic에서 유효한 데이터를 읽고 쓰기 위해 Clock이 필요합니다.

<p align="center"><img src="../../images/Computer architecture/%23013%20MIPS%20Single%20cycle%20CPU%20-%20Datapath/Untitled%203.png"></p>

위 그림에서 Clock이 필요한 Sequential logic은 3가지입니다.

- PC
- Register file
- Data memory

각각의 모듈은 Clock 신호가 HIGH일 때 작동합니다. 예를 들어, 레지스터 파일에 RegWrite 신호가 HIGH일 지라도 Clock 신호가 없다면 레지스터 파일에 쓸 수 없습니다.

여기까지가 Single cycle MIPS CPU를 만드는데 있어 필요한 datapath입니다. 이제 다음 글에서는 Control을 추가하여 완전한 Single cycle MIPS CPU를 만들어보겠습니다.