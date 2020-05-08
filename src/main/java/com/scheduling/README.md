# Operating System..

운영체제는 컴퓨터 하드웨어와 사용자 간의 매개체 역할을 하는 시스템 소프트웨어이다.
운영체제가 없다면,  한정된 자원 안에서 사용자 요청에 대해 개판으로 관리될 것이다.

따라서, 운영체제가 이 중간에서 관리를 한다.

<img src="/doc/scheduling/picture1.png">

<br>

목적.

---
1. 컴퓨터 시스템을 편리하게 (사용자 눈에는 모두 끊김없이 원할하게) 사용하도록 인터페이스 제공
2. 하드웨어 자원을 효율적으로 관리(자원 할당, 해제)
---

그러니 한정된 자원으로 최대한 성능 끌어내기 위해서는 CPU를 효율적으로 사용해야 한다. 그래서, Job Scheduling이라는 개념을 도입한다.

<br>

이슈.
---
준비완료 (Ready) 상태에 있는 프로세스들 중 어떤 것을 할당할 것인가에 문제<br>
처리율 최대화와 반환 시간의 최소화
---

이때 프로세스라는 단어가 등장한다.<br>
프로그램은 하드 디스크나 CD-ROM 같은 저장 매체에 저장되어 있다. 프로그램을 실행하기 위해서는 주기억장치에 LOAD해야 하는데, 이때 실행하는 프로그램을 Process라 한다.

Process : CPU가 수행하는 작업 단위

<br>

### 프로스세 메모리 구성

코드 영역(실행 명령어), 데이터 영역(전역, static 변수), Heap 영역(동적 할당), stack 영역(지역 변수)로 구성된다.

<img src="/doc/scheduling/picture2.png">

<br>


### 프로세스 상태

<img src="/doc/scheduling/picture3.png">

<br>

dispatch : ready -> run
timer runout : run -> ready
block : run -> waiting
wake-up : waiting -> ready


<br>

### Job Scheduling

보다 효율적인 자원 활용을 위해 어떤 작업을 메모리에 올리고 어떤 작업에게 자원을 할당할 것인가.


