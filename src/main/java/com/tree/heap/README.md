# Complete Binary Tree

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.04.25

# Description

Heap, Priority Queue

Heap은 최대값이나 최솟값을 계속해서 구할 때 사용하는 자료구조로써, 완전 이진 트리의 한 종류이다. heap은 Queue 이기 때문에 기본적으로 FIFO방식을 따르지만,  우선순위를 두어 순위가 높은 데이터를 먼저 처리하는 구조이다.


```
time - complexity : O( log N ) 
종류 : 완전 이진 트리
```

heap은 구현하는 방식은 연결리스트 기반과 배열 기반 방식이 있다.  이번에는 배열 기반으로 풀어보겠다.

```
parent = n;
left child = n * 2 + 1
right child = n * 2 + 2
```

<img src="/doc/tree/completebinarytree/completebinarytree.png">


삽입
```
1.  마지막 노드에 입력
2.  부모 노드와 비교하여 더 크면 교체
3.  교체가 안 될 때까지 다음 부모 노드 검색
```

<img src="/doc/tree/completebinarytree/insert.gif">

삭제
```
1.  루트 노드 pop 후 마지막 노드로 교체
2.  자식 노드들과 비교하며 우선순위가 더 높으면 교체
3.  교체가 안 될 때까지 다음 자식 노드 검색
```

<img src="/doc/tree/completebinarytree/remove.gif">