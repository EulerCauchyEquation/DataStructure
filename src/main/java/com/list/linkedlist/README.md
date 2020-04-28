# LinkedList

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.04.28

# Description

ArrayList에 비해 데이터 추가, 삭제가 유리하다.  그렇지만 ArrayList보다 더 많은 메모리를 소비한다.<br>  
접근이 용이하지 않음(순차접근만 가능)

time - complexity
```
insert :  O(N)
remove : O(N)
search : O(1)
contains : (N)
```

Linked List는 한 가지 오류가 있다.  데이터 삽입/삭제 시 그 데이터를 찾아야 하는데 데이터를 찾는데 시간 복잡도가 O(N)이 걸린다.   따라서, 삽입/삭제는 O(1) 이지만, 추가적인 O(N) 시간이 발생한다.
