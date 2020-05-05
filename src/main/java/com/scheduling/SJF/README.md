# SJF Scheduling

* Language : Java
* IDE : Intellij IDEA
* 날짜 : 20.05.05

## Description

SJF (Shortest Job First) 스케줄링은 non-preemptive 방식으로, CPU burst time이 가장 짧은 프로세스부터 수행한다.   

SJF는 FCFS의 단점인 평균 대기 시간이 긴 점을 보완하기 위해 나온 솔루션이다.  따라서, FCFS보다 평균 대기 시간이 짧다.

SJF의 문제는 Burst time을 알고 있어야 스케줄링이 가능하므로, 현실적으로 적용할 수가 없다는 점이다.  프로세스가 얼마만큼의 CPU burst를 가지는지 미리 알 수 있어야  적용이 가능하다.

<br>

### SJF 특징

---
1. FCFS 보다 평균 대기 시간 짧음
2. 실제로 적용하기 힘든 모델
---