# Hash (분리 체인법)

* Language : Java
* IDE : IntelliJ IDEA
* 날짜 : 20.04.29

# Description

### hash

hashing은 해시 함수로 계산된 키 값의 주소로 접근하는 것을 뜻한다.  해싱은 탐색 키로 저장돼 있는 테이블의 주소 위치를 계산한다.  이때 테이블은 해시 테이블하며, 해시 테이블을 탐색하는 것을 해싱이라 한다.

<img src="/doc/hash/chainhash/hash.png">

time - complexity
```
add : O(1)
remove : O(1)
search : O(1)
```

해시는 시간 복잡도가 모든 면에서 탁월하다.  하지만, 충돌(collision)이 많이 발생할 수록 탐색시간이 늘어난다.  (worst : O(N) )  

따라서, 해시의 이슈는 충돌을 적게해서 골고루 넣을 지가 중점 사항이다. <br>
충돌 문제를 완화시켜줄 방안으로 주소 개방법과 폐쇄 주소법이 있는데,  다음은 폐쇄 주소법 중 하나인 분리 체인법에 대해 소개한다.

<br>
<br>


### 분리 체인법 해시

분리 체인법은 Closed Addressing(폐쇄 주소법) 방식 중 하나이다. 해시 테이블은 포인터 주소를 갖는 1차원 배열 구조이고, 키값들을 연결 리스트로 처리하는 방식이다.

<img src="/doc/hash/chainhash/chain_hash.png"> 