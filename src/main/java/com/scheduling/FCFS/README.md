# FCFS Scheduling

* Language : Java
* IDE : Intellij IDEA
* 날짜 : 20.05.05

## Description

FCFS (First-Come First-Served) 스케줄링은 non-preemptive 방식으로, 대기 큐에 도착한 순서대로 할당 받으며, 프로세스가 중앙처리장치에 차지하면 완료될 때까지 CPU를 점유한다.

FCFS는 모든 프로세스가 동일하게 취급되나, 대화식 사용자들에게는 이 방식이 적합하지 않다.  그것은 빠른 응답을 보장하지 않기 때문이다.

또한, FCFS는 앞 프로세스가 끝날 때까지 매우 긴 시간을 기다리게 된다.

<br>

### FCFS 특징

---
1. 모든 프로세스 동일 취급
2. 대화식 운영체제에는 부적합
3. convoy effect 발생 (호위 효과)
---