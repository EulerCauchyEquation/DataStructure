# AVL Tree

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.04.28

# Description

Heap은 AVL Tree는 자가 균형 트리중 하나로써, 2원 탐색 트리이다.  AVL Tree의 핵심은 balance factor이다.  balance factor는 트리의 균형을 잡아주는 키워드로,  balance factor가 1이하인 트리가 AVL Tree이다.

```
balance factor = | left sub tree의 height - right sub tree의 height |
balance factor <= 1
```

time - complexity 는 항상 log N을 보장한다.  균형 트리의 장점이라 할 수 있다.

```
space : O(N)
time - complexity : O( log N ) 
```

<img src="/doc/tree/avltree/time_complexity.png">

AVL Tree는 Balance Factor(BF)가 concept이라 하였다.  이때, BF가 2이상이면 균형을 잡기 위해 Rotate가 발생한다.  rotate는 single rotate와 double rotate가 있다.

<img src="/doc/tree/avltree/avl_tree.png">

#### Single Rotate

<img src="/doc/tree/avltree/singl_rotate.gif">

먼저 rotate는 BF가 2이상인 노드를 기준으로 실행된다.

기준 노드의 BF가 음수이면(=right sub tree의 height가 더 높다),  left rotate  <br> 
기준 노드의 BF가 양수이면, right rotate를 실행한다.

BF가 1보다 큰 노드를 기준으로 Rotate를 실행한다. 그러면 BF가 보정되고, 균형잡힌 트리가 된다.

<img src="/doc/tree/avltree/left_rotate.png">

#### Double Rotate

double rotate는 마찬가지로, single rotate상황에서

기준 노드의 BF가 음수이면서 오른쪽 자식 노드가 양수이면, RL Rotate ( Right-Left Rotate) <br>
기준 노드의 BF가 양수이면서 왼쪽 자식 노드가 음수이면, LR Rotate ( Left-Right Rotate)를 실행한다.

나머지 상황은 모두 Single Rotate라 생각하면 된다.



### Red - Black Tree와 AVL tree 비교

AVL은 balance가 타이트하게 잡기 때문에 삽입/삭제가 RBTree보다 느리다. <br> 
대신 타이트한 만큼 search는 RBTree보다 빠른 편이다.  그래서 사용하는 빠른 조회를 요구하는 트리에 사용된다. ( ex. database )
