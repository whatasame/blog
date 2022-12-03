# 4) 수학적 SW 비용 산정 기법 - COCOMO

## COCOMO란

COCOMO 기법은 COnstructive COst MOdel의 약자로서 **LOC를 통해 프로젝트의 전체 기간**을 예측하는 기법입니다. LOC를 통해 비용을 산정하므로 Execution 단계에서 확실해지는 LOC로 비용을 산정한다는 모순점이 여전히 있습니다.

### COCOMO 전체 흐름

1. LOC를 예상한다.
2. **프로그램 유형에 따른 가중치**로 **보정**하여 M/M을 구한다.
    
    <p align="center"><img src="../../images/소프트웨어공학/4) 수학적 SW 비용 산정 기법 - COCOMO-Untitled.png"></p>
    
    프로젝트 유형에 따라 복잡도와 규모가 다르므로 LOC에 대해 프로그램 유형별로 보정을 합니다.
    
    - 단순형$(size\leq50\ KDSI)$
        
        복잡도와 난이도가 비교적 높지 않은 업무용 소프트웨어
        
    - 중간형$(size\leq300\ KDSI)$
        
        규모나 복잡도가 중간급 정도. 운영체제, DBMS가 여기에 해당
        
    - 내장형$(size\geq300\ KDSI)$
        
        자동화 기기, 전자 제품과 같이 **하드웨어에 밀접하게 관련있는 임베디드** 소프트웨어
        
        **위에 해당하지 않더라도 300KDSI가 넘으면 내장형**에 해당
        
    
    <p align="center"><img src="../../images/소프트웨어공학/4) 수학적 SW 비용 산정 기법 - COCOMO-Untitled 1.png"></p>
    
    <aside>
    💡 M/M(Man Months) = P/M(Person Months)
    
    </aside>
    
3. 보정된 M/M에 추가 특성을 고려한 **노력 조정 수치로 보정**하여 P/M을 구한다.
    
    <p align="center"><img src="../../images/소프트웨어공학/4) 수학적 SW 비용 산정 기법 - COCOMO-Untitled 2.png"></p>
    
    보정된 결과인 M/M(P/M)은 프로그램의 특성만 고려한 것이지 프로그램의 특성은 고려하지 않습니다. 따라서 프로그램의 특성을 고려한 노력 조정 수치(EAF, Effort Adjustment Factor)로 한 번 더 보정해야합니다.
    
    $$
    노력\ 조정\ 수치(\rm EAF) = 특성의\ 승수\ 값을\ 모두\ 곱한\ 값
    $$
    
    ❓ 예제
    
    > 개발 하려는 소프트웨어에 대하여 다음 특성을 갖는다면 EAF는 어떻게 되는가?
    > 
    - 신뢰도가 높다.
    - 복잡도가 매우 복잡이다.
    - SW 기술을 거의 사용하지 않는다.
    - 개발 일정이 매우 촉박하다.
    - 다른 요소들은 보통이다.
    
    <p align="center"><img src="../../images/소프트웨어공학/4) 수학적 SW 비용 산정 기법 - COCOMO-Untitled 3.png"></p>
    
    $$
    EAF = 1.15\times1.30\times1.24\times1.10\ (\times1)=2.04
    $$
    
    이렇게 EAF를 구했으면 전 단계에서 구한 값에 보정해주면 됩니다.
    
    <p align="center"><img src="../../images/소프트웨어공학/4) 수학적 SW 비용 산정 기법 - COCOMO-Untitled 4.png"></p>
    
    > 개발하려는 소프트웨어의 KDSI가 60이고, 소프트웨어는 Semidetached(중간형)이며, 노력 조정 수치(EAF)가 2.04라면 노력(E)?
    > 
    
    $$
    PM = 3.0\times(60)^{1.12}\times2.04=600.179
    $$
    
4. P/M을 통해 **총 개발 기간**을 구한다.
    
    위 과정까지 최종으로 구한 노력(P/M)으로 개발 기간(TDEV, Total DEVelopment time)를 구할 수 있습니다.
    
    <p align="center"><img src="../../images/소프트웨어공학/4) 수학적 SW 비용 산정 기법 - COCOMO-Untitled 5.png"></p>
    
    <p align="center"><img src="../../images/소프트웨어공학/4) 수학적 SW 비용 산정 기법 - COCOMO-Untitled 6.png"></p>
    
    > 개발하려는 소프트웨어의 KDSI가 60이고, 소프트웨어는 Semidetached(중간형)이며, 노력 조정 수치(EAF)가 2.04라면 EAF는 600.179이다. 총 개발 기간은 어떻게 되는가?
    > 
    
    $$
    TDEV=2.5\times(600.179)^{0.35}= 23.461
    $$
    
    즉, 이 프로젝트의 총 개발 기간(P/M)은 23.461개월이 됩니다. 이 말은 23명이 있으면 약 1개월만에 프로젝트를 마칠 수 있다는 뜻이죠.
    
    ### COCOMO의 단점