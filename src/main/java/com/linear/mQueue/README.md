# Queue

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.04.29

# Description

queue는 stack과 반대로, FIFO(First-In First-Out)방식이다.  데이터를 꺼내는 쪽을 front, 입력하는 쪽을 rear이라 한다.

<img src="/doc/linear/queue/queue.png">

Queue 는 3가지 구현 방식이 있다.
```
배열 방식(이동 큐)
원형 큐
연결 리스트 큐
```
<br>
<br>

#### 이동 큐 

배열 방식은 push시 rear이 증가하고 데이터를 채우고 <br>
pop시 front가 증가하면서 front자리에 데이터가 비워진다.

<img src="/doc/linear/queue/moveQueue.png">
 
문제는 stack over flow의 위험이 있는 것이 단점이다.  해서, 데이터를 꺼낼때마다 한칸씩 앞으로 모두 이동하는 방안이 있기는 하지만, 이동 시간이 든다.


<br>
<br>

#### 원형 큐

이동 큐의 문제점을 고안한 방식이 원형 큐이다.

<img src="/doc/linear/queue/circularQueue.png">

하지만, 원형 큐 또한 stack overflow 의 위험이 있다.

<br>
<br>

#### 연결 리스트 큐 

연결 리스트 큐는 연결 리스트 기반으로 구현한 방식이다.  원형 큐의 문제점은 해결했지만,  이전보다 추가 메모리가 더 든다.