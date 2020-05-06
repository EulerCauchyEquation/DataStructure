# C-LOOK Disk Scheduling

* Language : Java
* IDE : Intellij IDA
* 날짜 : 20.05.06

## Description

C-LOOK 스케줄링은 LOOK 알고리즘 방식에서 마지막 트랙에 다다르면 방향이 바뀌는게 아니라 방향을 유지하며 다시 처음으로 이동하여 서비스하는 기법이다. 

C-LOOK 알고리즘은 사이드와 중앙간의 편차를 줄이기 위해 더 균등화한 방식이다.

<img src="/doc/disk/CLOOK/CLOOK.png">

<br>

### C-LOOK 특징

---
1. 사이드와 중앙 간의 서비스 편차를 완화
2. 항상 바깥쪽에서 안쪽으로 이동
---