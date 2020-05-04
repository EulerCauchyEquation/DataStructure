# LRU Algorithm

* Language : Java
* IDE : Intellij IDEA
* 날짜 : 20.05.04

# Description

가장 오래 사용하지 않은 페이지를 교체하는 알고리즘이다. 

최적 알고리즘 (Optimal Algorithm)은 실제로 구현이 불가능하므로, 이를 대체한 방식이 LRU 알고리즘이다.
LRU 알고리즘은 Optimal 보다는 교체 횟수가 높지만, FIFO 보다는 효율적이다.

<img src="/doc/page/LRU/LRU.png">

<br>

### LRU 특징

---
1. 시간 지역성의 성질을 고려함 <br>
    : 최근 참조된 페이지가 가까운 미래에 다시 참조될 가능성이 높은 성질<br>
2. FIFO보다는 효율적
---
