# SCR Algorithm

* Language : Java
* IDE : Intellij IDEA
* 날짜 : 20.05.07

# Description

SCR (Second Chance Replacement)는 LRU 유사 알고리즘으로써, LRU 알고리즘과 다르게 H/W 지원 없이 Reference-Bit를 이용하여 구현한 알고리즘입니다.  기반은 FIFO 알고리즘을 기반으로 합니다.  페이지 부재 시 FIFO방식으로 도는데, Reference-Bit가 1이면 한 번더 기회를 주고 다음 페이지를 찾습니다. 

<img src="/doc/page/SCR/SCR.png">

2차기회를 얻은 페이지의 Reference-Bit는 0이 되고,  페이지를 한 번이라도 참조하면 Reference-Bit는 1이 됩니다.

<br>

### SCR 특징

---
1. LRU 유사 알고리즘, 참조 비트로 구현
2. LRU와 근접한 성능
---