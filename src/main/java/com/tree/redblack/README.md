# Red-Black Binary Tree

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.05.28

# Description

## Red-Black Tree

Red-Black tree는 자가 균형 이진 트리로써,  BST의 search, insert, delete 모든 연산이 O(log n)의 시간 복잡도를 가지는 트리이다.    최악의 경우에도 일정한 실행시간을 보장한다.  

레드 블랙 트리는 leaf node들은 자료를 가지고 있지 않는 상태이고,  각 노드들은 색상을 가지고 있다.  색상은 red 나 black을 가지고 있고, 이것을 가지고 자가 균형 트리를 유지한다.

<img src="/doc/tree/redblacktree/redblacktree.png">

<br>

### 정의 ( 특성.property )

---
1. 노드는 `Red` or `Black` 이다.
2. Root 는 항상 `Black` 이다.
3. 모든 리프 노드들(NIL : Null Leaf Node)은 `Black`이다.
4. `Red` 노드들의 자식들은 항상 `Black`이다. (No Double Red)
5. 모든 노드에 대해서 어떤 노드로부터 leaf node에 이르는 모든 경로에는 동일한 개수의 Black 노드가 있다.
---

항상 위 특성들을 모두 만족하는 트리이며, 위배되는 속성이 있다면 rebalance 가 발생한다.

<br>

### Rotation ( Lift and Right Rotation)

	* Insert, Delete 가 필요 시 발생한다.
	* 시간 복잡도는 O(1)이다.

<img src="/doc/tree/redblacktree/rotation.gif">

<br><br>

## Insert 

Red-Black tree의 insert는 BST랑 동일하게 삽입 후, RB-INSERT-FIX를 호출한다.  RB-INSERT-FIX는 tree의 특성이 위반될 때 일어나는데 그 상황은 다음과 같다.  (참고로 처음 노드는 Red이다.)

	* 처음 노드 입력 시 2번 특성 위반 ( root는 black이다. ) 
	* Red노드의 자식의 추가할 경우 4번 특성( No Double Red ) 위반


<br>

### 문제 정의
해결방안은 Restructuring 과 Recoloring으로 크게 2가지이며, 작게는 총 3가지이다.

	* Recoloring
	* Restructuring

<br>

#### case 1. Recoloring

<img src="/doc/tree/redblacktree/insert_recoloring.png">

새로운 노드(Z)의 부모가 `Red`이면서, 부모 형제(y)가 `Red`이면 Recoloring을 시도한다.

```
node.parent.color = RED
node.parent.right.color = RED
node.parent.parent.color = BLACK
node = node.parent.parent
```

Recoloring은 아직 위반의 여지가 남아있다. 다시 조상 노드의 상위 노드가 `Red`이면, 아직 double `red`가 일어날 수가 있으므로, 조상노드에서 다시 탐색을 한다.  

Recoloring은 최악일 때는, root가 나타날 때까지 발생할 수 있다.

<br>

#### case 2, 3. Restructuring

<img src="/doc/tree/redblacktree/insert_restructuring.png">

z의 부모 형제가 `Black`인 경우는 Restructuring을 한다. 이때, z가 부모의 오른쪽 자식이면 LR rotate를, 왼쪽 자식이면 right rotate를 한다.

```
if(node.parent.right == node){
    // case 2
    node = node.parent
    rotateLeft(node)
}
// case 3
node.parent.color = BLACK
node.parent.parent.color = RED
rotateRight(node.parent.parent)
```

Restructuring은 위반의 여지가 없으므로, 완료 즉시 종료된다.

<br>

### Insert의 pesudo code

```
RB-INSERT-FIXUP(T, z)
1  while color[p[z]] = RED
2    do if p[z] = left[p[p[z]]]
3        then y <- right[p[p[z]]]
4          if color[y] = RED
5            then color[p[z]] <- BLACK   >> CASE 1
6                 color[y] <- BLACK      >> CASE 1
7                 color[p[p[z]]] <- RED  >> CASE 1
8                 z <- p[p[z]]           >> CASE 1
9          else if z = right[p[z]]
10                then z <- p[z]         >> CASE 2
11                     LEFT-ROTATE(T, z) >> CASE 2
12              color[p[z]] <- BLACK     >> CASE 3
13              color[p[p[z]]] <- RED    >> CASE 3
14              RIGHT-ROTATE(T, p[p[z]]) >> CASE 3
15      else(same as then clause with "right" and "left" exchanged)
16 color[root[T]] <- BLACK
```

Recoloring후, 계속 검사 진행
Restructuring은 작업 후 종료

<br><br>

## Delete

Red-Black tree의 delete는 BST랑 동일하게 삭제 후, RB-DELETE-FIX를 호출할지 결정한다.  RB tree의 특성이 위반되는 상황은 다음과 같다. 

	* Remove 대상 노드가 단말 노드인데 Black인 경우, 5번 특성을 위반한다.
	* Remove 대상 노드가 자식이 2개인 노드라서, 후계자를 찾았는데 그 후계자의 자식 노드들은 없고 후계자의 색상이 Black인 경우, 5번 특성을 위반한다.



다른 상황도 있지만, 심각한 문제는 아니다.

	* Remove 대상 노드가 자식 2개라 successor를 골랐는데, successor가 red인 경우
	* Remove 대상 노드가 자식 2개라 successor를 골랐는데, successor의 자식이 있고 그 자식이 red인 경우



위 경우는 `red`를 `black`으로 바꾸어 주면 해결되므로 심각한 문제는 아니다.  사실, fix는 실제로 삭제되는 대상이 단말노드일 때만 하는 것으로 귀결된다.
 
<br>


### 문제 정의
해결방안은 총 4가지이다.  삭제대상

	* 삭제 대상인 x는 double black노드 이거나, NIL노드이다.
	* x의 형제노드(sibling)인 w는 반드시 존재한다.(NIL일 수 없다.)


<br>

#### case 1. x의 형제 노드 w가 `RED`인 경우

<img src="/doc/tree/redblacktree/delete_case1.png">

	* x는 여전히 double-blakc 노드이다.
	* 새로운 w는 black 노드이다. 이 경우 case 2, 3, 4로 넘어가게 된다.

<br>

#### case 2. x의 형제 노드 w가 `BLACK`인데, 자식 노드들이 모두 `BLACK`인 경우

<img src="/doc/tree/redblacktree/delete_case2.png">

x의 extra black과 w의 `black`을 부모한테 넘긴다.  그러면, w는 `Red`가 되며, BLACK 노드 개수는 유지된다. 

	* case 1번에서 왔다면, 부모가 Red이므로, Black으로 바꾸고 종료된다.
	* 부모가 Black이면, 부모를 기준으로 다시 탐색한다. 따라서, 최악은 Root까지 갈 수 있으며, root에 도달하면 종료된다.

<br>

#### case 3. w는 `Black`, W의 왼쪽 자식이 `RED`인 경우 (오른쪽 자식은 항상 `Black`)

<img src="/doc/tree/redblacktree/delete_case3.png">

case 3은 진행 후 case 4로 넘어간다.

<br>

#### case 4. w는 `Black`, W의 오른쪽 자식이 `RED`인 경우 (왼쪽 자식은 상관없음)

<img src="/doc/tree/redblacktree/delete_case4.png">

x의 extra-black을 제거하고 종료된다. double black노드가 없어졌음에도 불구하고, black depth가 유지된다.

<br>

### fixup pesudo code

```
RB-DELETE-FIXUP(T, x)
01 while x != root[T] and color[x] = BLACK
02   do if x = left[p[x]]
03        then w <- right[p[x]]
04          if color[w] = RED
05            then color[w] <- BLACK                                  // Case1
06                 color[p[x]] <- RED                                 // Case1
07                 LEFT-ROTATE(T, p[x])                               // Case1
08                 w <- right[p[x]]                                   // Case1
09          if color[left[w]] = BLACK and color[right[w]] = BLACK
10            then color[w] <- RED                                    // Case2
11                 x <- p[x]                                          // Case2
12          else if color[right[w]] = BLACK
13              then color[left[w]] <- BLACK                          // Case3
14                   color[w] <- RED                                  // Case3
15                   RIGHT-ROTATE(T, w)                               // Case3
16                   w <- right[p[x]]                                 // Case3
17            color[w] <- color[p[x]]                                 // Case4
18            color[p[x]] <- BLACK                                    // Case4
19            color[right[w]] <- BLACK                                // Case4
20            LEFT-ROTATE(T, p[x])                                    // Case4
21            x <- root[T]                                            // Case4
22        else (same as then clause with "right" and "left" exchanged)
23 color[x] <- BLACK
```

case 1 : case 1-> case 2, 3, 4 <br>
case 2 : case 2 -> (case 2-> ... case 1,2,3,4) <br> 
case 3 : case 3 -> case 4 <br>
case 4 : case 4

<br><br>

## 예시

```
Input : 2 1 5 9 7 8 3 4 6
```

<img src="/doc/tree/redblacktree/example_1.jpg">

<img src="/doc/tree/redblacktree/example_1_2.jpg">

<img src="/doc/tree/redblacktree/example_1_3.jpg">