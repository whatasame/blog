# #015 MIPS Single cycle CPU - ALU control (optional)

이번 글에선 MIPS Single cycle CPU에서 ALU control이 ALU 연산을 결정하는 방법에 대해 짧게 알아보겠습니다.

## ALU 연산 종류가 결정되는 과정

지난 글에서 완성한 MIPS Single cycle CPU의 논리 회로는 아래와 같습니다. (Jump 제외)

<p align="center"><img src="../../images/Computer architecture/%23015%20MIPS%20Single%20cycle%20CPU%20-%20ALU%20control%20%28optional/Untitled.png"></p>

ALU Control은 Control unit이 보내는 ALUOp와 명령어의 funct 비트 값을 통해 ALU를 제어합니다.

다시 말하면 Control unit은 Main control unit, ALU Control은 Sub control unit이라 할 수 있습니다.

MIPS ISA에서 명령어의 종류는 총 3가지(산술 논리, 메모리 참조, 흐름 제어)이므로 ALUOp는 2비트로 명령어 종류를 구분할 수 있습니다.

즉, ALU control은 Control unit이 보내는 2비트와 명령어의 funct 6비트를 합쳐 총 8비트로 ALU 연산을 결정합니다.

이를 진리표로 나타내면 아래와 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23015%20MIPS%20Single%20cycle%20CPU%20-%20ALU%20control%20%28optional/Untitled%201.png"></p>

ALUOp(2bit) + funct(6bit) → ALU control(4bit)가 되는 것입니다.

이를 논리회로로 나타내면 다음과 같습니다.

<p align="center"><img src="../../images/Computer architecture/%23015%20MIPS%20Single%20cycle%20CPU%20-%20ALU%20control%20%28optional/Untitled%202.png"></p>
