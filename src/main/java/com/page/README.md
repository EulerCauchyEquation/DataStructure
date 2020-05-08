# Virtual Memory 

### Virtual Memory 배경

프로그램을 실행시키기 위해서는 프로그램이 메인 메모리인 RAM에 LOAD 되어야 한다. <br>
그런데, 여기서 문제가 생긴다.

이슈.

---
1. RAM의 크기 < 프로그램의 크기 일 때 실행 불가능 ( 메모리 부족 에러 )
2. 여러 프로그램을 실행할 때 문제 ( 메모리 부족 )
---

그래서 프로그래머는 Virutual Memory라는 개념을 생각했다.
Virutaul Memory : 보조 기억 장치인 H.D에 일부를 가져와 RAM의 부족한 부분을 채워 실행 가능하도록 만드는 기법.

<img src="/doc/page/picture1.png">

<br>

하지만, H.D와 RAM의 속도는 엄청나게 차이난다.  따라서, H.D를 온전히 RAM처럼 사용하게 되면 RAM Access 할 때랑 H.D Access 할 때랑 응답 속도 차이가 나서 끊기는 현상이 발생한다.

그래서 , Virtual Address라는 개념이 발생한다.

Virtual Address : RAM의 실제 주소를 가상 메모리에 저장해서 사용한다.  일명, 사상(Mapping)을 통해                                 저장한다.  사상 임무는 MMU가 담당

<img src="/doc/page/picture2.png">

<br>

이때, 사상을 Word나 Byte 단위로 관리를 하면 오버헤드 비용이 너무 크므로, Block 단위로 사상을 한다.
page : 같은 크기의 block 단위
segment : 서로 다른 크기의 block 단위

<img src="/doc/page/picture3.png">

<br>

page 단위로 관리하는 기법을 paging이라 하며, 보통 Demand-Paging 기법을 사용한다.  page 단위는 보통 4kbytes이며, 디스크 상에서 존재하는 페이지들을 paging file(=swap file)이라 부른다.  ( 디스크는 파일 시스템이기 때문에 File 형태로 관리된다.)

<img src="/doc/page/picture4.png">

<br>

### 요약

---
1. RAM의 부족한 메모리로 실행 불가능한 환경을 가능하게 만들기 위해서 탄생
2. 실제로는 Paging System으로 관리
---

<img src="/doc/page/picture5.png">