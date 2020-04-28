# ArrayList

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.04.28

# Description

ArrayList 사실 Java 컬렉션 프레임워크로써, 배열리스트와 같다.   

time - complexity
```
insert :  O(N)
remove : O(N)
search : O(1)
contains : (N)
```

시간 복잡도를 보다시피, 대용량 데이터에서 조회를 하는데 유리하다. <br>
memory도 연속적인 공간으로 저장한다.
<br>
<br>


#### Java에서의 ArrayList

기본적으로 ArrayList는 List 인터페이스로 구현한 Collection이다. List는 저장 순서를 유지하고, 중복을 허용한다는 특징이 있다.

ArrayList와 비슷한 Vector가 있다. 이 둘의 차이는 Thread에 대해 동기화 차이이다.  Vector는 동기화 보장이 되고, ArrayList는 안 된다. 따라서, multi-thread 환경에서는 Vector가 필요하지만, Vector는 성능의 문제가 많아서 잘 사용하지 않는다. 

vector를 대체로 한 

---
Collections.synchronizedList(List<T> list);

---

를 사용하자