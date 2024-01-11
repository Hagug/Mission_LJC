프로젝트 실행 안내

1. InteliJ에서 db.sqlite라는 이름으로 sqlite Data Source를 생성.

2. MyBoardApplication.java를 실행.

3. http://localhost:8080/boards로 접속.

4. 두 번째 이후 실행부터는 application.yaml 파일의 ddl-auto를 update로 변경.


필수 요구사항 구현 방식

![DB 관계도](게시판DB구성도.jpg)
![게시판DB구성도](https://github.com/Hagug/Mission_LJC/assets/107788950/9dcbc9c2-c57d-4814-963b-bc1b21925ca2)

1. 게시판 기능
   -관련 파일 : /entity/Board, BoardRepository, BoardController, BoardService, /templates/board/
   -게시판 데이터 생성 : boardRepository.count()가 0일 때(최초 1회) 만 데이터 생성.(BoardService)
   -게시판 목록 : 타임리프의 each로 출력.(/board/home.html)
   -게시글 목록 : 타임리프의 each로 출력. 최신 글을 위로 오도록 함.(/board/open.html)
   -게시글 작성 : 작성 버튼 클릭 시 작성 페이지로 연결. form을 작성해 게시글 작성.(/board/write.html)
   -게시글 작성 : form에 빈 칸이 있으면 작성이 불가능 하도록 설정.(input 태그의 required 속성)

2. 게시글 기능
   -관련 파일 : /entity/Atricle, AtricleRepository, AtricleController, AtricleService
   -게시글 조회 : 게시글 목록에서 게시글을 클릭하면 게시글의 id 링크로 전달해 게시글 조회.(/board/open.html -> AtricleController -> /article/read.html)
   -게시글 수정 : 게시글 작성과 거의 유사하지만 비밀번호 확인 작업이 추가.(/article/update.html)
   -게시글 삭제 : 올바른 비밀번호를 입력한 후 삭제 버튼을 누르면 게시글이 삭제.(/article/read.html)
   -비밀번호 확인 : 자바스크립트로 비밀번호의 동일 여부를 검사하는 함수를 작성하고, form 태그의 onsubmit 속성을 이용해 form 제출 전 작성한 함수를 실행하도록 함.

3. 댓글 기능
   -관련 파일 : /entity/Comment, CommentRepository, AtricleController, AtricleService
   -댓글 조회 : 게시글 조회 페이지 하단에서 조회 가능.(/article/read.html)
   -댓글 등록 : 별도의 작성페이지는 없지만 게시글 작성과 유사한 방식.(/article/read.html)
   -댓글 삭제 : 올바른 비밀번호를 입력한 후 삭제 버튼을 누르면 댓글이 삭제.(/article/read.html)
   -비밀번호 확인 : 비밀번호 확인 작업을 html 내부에서 하지않고, Controller에서 진행.
   

어려웠던 점
댓글 삭제 시 비밀번호 확인 과정이 가장 어려웠다. 
게시글 수정/삭제는 다음과 같이 진행한다.
html 내부에서 자바스크립트 함수로 비밀번호 확인 -> 일치하면 게시글 수정/삭제.
게시글 수정/삭제는 수정/삭제의 대상에 대한 정보가 html파일로 넘어와 있어서 즉각적인 비밀번호 확인이 가능했다.

반면, 댓글의 경우 댓글의 정보를 List 형태로 가지고 있기 때문에 여러 댓글들 중 어떤 댓글의 삭제 버튼이 눌렸는지 html 내부에서 특정하기 어려웠다.
따라서 삭제하려는 댓글의 id를 링크에 담아 Controller로 넘기고, Controller에서 받은 id의 댓글의 비밀번호를 가져와 확인하도록 했다.
확인 이후에는 삭제 여부를 사용자가 알 수 있도록 PrintWriter 객체를 생성해 안내 메시지를 출력하도록 했다.
