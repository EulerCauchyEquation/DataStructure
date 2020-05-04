# FIFO Page Replacement Algorithm

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.05.04

## Description 

FIFO 방식의 페이지 교체 알고리즘이다.   사용 횟수와 상관없이 페이지 부재 시 가장 먼저 들어온 페이지를 교체한다.

FIFO 방식은 가장 효율이 안 좋은 방식으로, 벨레이디의 모순 현상이 발생할 수 있다. 이는 페이지 프레임 수가 많으면 페이지 부재가 줄어드는게 일반적인데, 프레임 수를 증가시켜도 페이지 부재가 늘어나는 현상이 발생할 수 있다.

<img src="/doc/page/FIFO/FIFO.png">

<br>

### FIFO 특징

---
1. 활발하게 사용 중인 페이지를 교체할 가능성이 높다.
2. 벨레이디의 모순 현상
--- 

