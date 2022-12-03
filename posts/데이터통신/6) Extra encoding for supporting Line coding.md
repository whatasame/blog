# 6) Extra encoding for supporting Line coding

앞서 소개한 Line coding 중 일부 문제가 있는 인코딩을 보조해주는 인코딩이 있습니다. 이러한 보조 인코딩은 혼자서 사용되지 않고 특정 Line coding과 함께 사용됩니다.

이번 글에선 보조 인코딩에는 NRZ-I를 보조하는 Block coding과 AMI를 보조하는 Scrambling을 알아보도록 하겠습니다.

## Block coding

Block coding은 NRZ-I를 보조하는 인코딩입니다.

<p align="center"><img src="../../images/데이터통신/6) Extra encoding for supporting Line coding-Untitled.png"></p>

NRZ-I는 0을 이전 전압 유지, 1은 이전 전압의 반대 전압으로 표현합니다. NRZ-I 인코딩에선 0이 반복될 때 DC component와 Synchronization 이슈가 발생합니다. Block coding은 NRZ-I의 Synchronizaition 이슈를 해결할 수 있는 보조 인코딩입니다.

<p align="center"><img src="../../images/데이터통신/6) Extra encoding for supporting Line coding-Untitled 1.png"></p>

lock coding은 여러 개의 bit를 묶은 Block을 활용하는 인코딩입니다. Block을 사용하기 때문에 mB/nB coding이라고 불립니다. m개 bit가 있는 Block을 n개의 비트가 있는 Block으로 확장하는 인코딩입니다. 예를 들어 Block coding에서 가장 대표적인 4B/5B coding은 4bit block을 5bit block으로 확장하는 인코딩입니다.

<p align="center"><img src="../../images/데이터통신/6) Extra encoding for supporting Line coding-Untitled 2.png"></p>

4B/5B coding에선 전체 Digital data를 4bit씩 묶어 5bit로 확장 인코딩한 후 이를 NRZ-I로 표현합니다. 4B/5B coding을 사용하면 같은 전압이 최대 3번 유지됩니다. 같은 전압이 3번 유지되는 것은 synchronization에 문제가 없다고 합니다. 따라서 NRZ-I의 문제점인 0이 반복될 때 발생하는 Synchronization 이슈를 해결할 수 있습니다.

하지만, DC component 이슈는 여전히 남아있습니다. 그러나 이는 DC component가 발생하지 않는 좋은 장비를 사용하면 해결되는 문제이기 때문에 Bandwidth가 낮아 효율이 좋은 NRZ-I + Blockcoding을 사용하는 것입니다.

## Scrambling

Scrambling은 AMI 인코딩을 보조하는 인코딩입니다.

<p align="center"><img src="../../images/데이터통신/6) Extra encoding for supporting Line coding-Untitled 3.png"></p>

AMI는 0을 0V, 1을 최근 전압의 반대로 표현합니다. 따라서, 0이 반복될 때 Synchronization 이슈가 발생합니다. Scrambling은 AMI 인코딩의 0이 반복될 때 발생하는 Synchronizaition 이슈를 해결하는 인코딩입니다.

Scrambling 인코딩에는 B8ZS와 HDB3이 있습니다. 이번 글에서는 B8ZS에 대해 알아보도록 하겠습니다.

<p align="center"><img src="../../images/데이터통신/6) Extra encoding for supporting Line coding-Untitled 4.png"></p>

Scrambling - B8ZS는 0이 8번 반복되는 Digital data를 처리합니다. 00000000인 데이터를 000VB0VB로 표현합니다.

- V(Violation) : AMI 규칙을 위반합니다. 즉, 최근 전압을 그대로 표현합니다.
- B(Bipolar) : AMI 규칙대로 진행합니다. 즉, 최근 전압의 반대로 표현합니다.

같은 Digital data 1 0000 0000에 대해 A 그래프는 최근 전압 1이 양수 전압이었으므로 AMI에 따르면 음수로 표현해야하지만 양수로 표현(Violation)합니다. B 그래프는 최근 전압 1이 음수 전압이므로 AMI 규칙에 따르면 양수로 표현해야하지만 음수로 표현(Violation)합니다.

이렇게 Scrambling을 사용하면 AMI의 Synchronization을 해결하게 되고, AMI는 Bandwidth도 좁고 DC component, Synchronization 이슈가 없는 완벽한 인코딩이 됩니다.

그런데 왜 B8ZS에서는 V와 B같은 특수 인코딩을 사용하는 것일까요? 그 이유는 정상적인 00010010과 000VB0VB를 구분하기 위해서입니다. V과 B는 AMI의 1과 0 규칙에 정확히 반대하는 규칙을 가지고 있기 때문에 주어진 신호가 00010010인지 000VB0VB인지 구분할 수 있는 것입니다.

### 기타 고려사항

> V 신호를 2번이나 하는 이유?
> 

노이즈 등의 외부 요인 간섭에 영향을 받지 않도록 안전하게 2번 V를 합니다.

> 0이 7번이면 Synchronization 이슈가 발생하지 않나?
> 

통상적으로 0이 8번 반복될 때 Synchronization 이슈가 발생한다고 합니다.

> 0이 8번 나올 지 어떻게 아나? 0이 4~7번 나오고 다시 1이 나올 수도 있지 않나?
> 

B8ZS는 Digital signal 전체를 확인한 후 모든 00000000을 000VB0VB로 바꾼 후 전체 변경된 값을 AMI로 보내는 것입니다.