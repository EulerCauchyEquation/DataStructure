# SSTF disk scheduling

* Language : Java
* IDE : Intellij IDEA
* 날짜 : 20.05.06

## Description

SSTF (Shortest Seek Time First)는 현재 헤드의 위치에 가장 가까운 요청을 먼저 서비스하는 기법이다.  SSTF는 SJF Scheduling 형태이기 때문에 어떤 요청에 대해서 기근 현상이 나타날 수 있다. 즉,  긴 대기시간을 가질 확률이 있다. 

구조상 사이드 쪽 트랙보다는 가운데 트랙이 더 많은 서비스를 먼저 받기 때문에 편차가 존재한다.  그렇지만, FCFS보다는 처리율이 높고 평균 응답 시간이 짧다.

<img src="/doc/disk/SSTF/SSTF.png">

<br>

### SSTF특징

---
1. FCFS보다 처리율이 높음
2. 특정 트랙에 대해 기근 현상이 나타날 수 있음
3. 사이드 쪽과 가운데의 편차가 존재함.
---