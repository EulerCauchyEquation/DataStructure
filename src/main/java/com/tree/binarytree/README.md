# Binary Tree

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.04.20

# Description

Binary Tree는 Degree가 최대 2인 트리로, 삽입, 삭제, 검색이 비교적 효율적인 자료구조이다.   배열의 장점과 연결리스트의 장점을 가지고 있다.  비선형 자료구조로,  계층적으로 연관돼 있을 때 주로 사용한다.

---

time - complexity : O( log N )  // worst : O(N) <br>
종류 : 완전 이진 트리, 포화 이진 트리, 균형 이진 트리..

---

### Tree의 종류

Complete Binary Tree ( 완전 이진 트리 )

<img src="/doc/tree/binarytree/complete_binary_tree.png"> 

Binary Tree는 보통 링크드 방식으로 구성된다.  그런데, Complete Binary Tree부터는 index를 갖춘 1차원 배열로도 표현이 가능하다. 

```
parent = n;
left child = n * 2 + 1
right child = n * 2 + 2
```

Full Binary Tree ( 포화 이진 트리 )

<img src="/doc/tree/binarytree/full_binary_tree.png"> 

Balanced Binary Tree ( 균형 이진 트리 )

<img src="/doc/tree/binarytree/balanced_binary_tree.png"> 

단말노드끼리 레벨 차이가 1 이하인 Tree ( AVL Tree )

```
특징
탐색시간 최소화 목적
균형 인수가 1이하 ( |왼쪽 서브 트리 높이 - 오른쪽 서브트리 높이 | <= 1 )
```