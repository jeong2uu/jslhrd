package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BlogAiTranslate;
import service.BlogAiWrite;
import service.BlogDelete;
import service.BlogSelectAll;
import service.BlogUpdate;
import service.BlogView;
import service.BlogWrite;


@WebServlet("/port/*")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,  //2MB 메모리 또는 임시폴더에 잠깐 저장
		maxFileSize = 1024 * 1024 * 10,  //10MB 파일 1개당 최댘기
		maxRequestSize = 1024 * 1024 * 50 //50MB 폼 전체 합산 크기 파일 여러개+텍스트 합쳐서 50MB까지 허용.
		)
//@MultipartConfig Servlet에서 파일 업로드를 가능하게 하는 설정
//이거 없으면 request.getPart()에러가 난다.
// 한개의 서블릿으로 여러 요청을
public class BlogController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BlogController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/portfolio/list.jsp");
		//dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String uri = request.getRequestURI();  // localhost:8088/port/list.do  요청한 url전체를 구한다.
//		System.out.println("uri : " + uri);
//		String commend = uri.substring(uri.lastIndexOf("/")+1);  //list.do
//		System.out.println("commend : " + commend);
		
		String uri = request.getPathInfo();
		System.out.println("uri : " + uri);
		
		String page = null;
		
		switch(uri) {
		case "/list.do":
			new BlogSelectAll().doCommand(request, response);
			page = "/portfolio/list.jsp";
//			RequestDispatcher rd1 = request.getRequestDispatcher("/portfolio/list.jsp");
//			rd1.forward(request, response);
			break;
		case "/write.do":
			page = "/portfolio/write.jsp";
//			RequestDispatcher rd2 = request.getRequestDispatcher("/portfolio/write.jsp");
//			rd2.forward(request, response);
			break;
		case "/aiWrite.do":
			new BlogAiWrite().doCommand(request, response);
			break;
		case "/aiTranslate.do":
			new BlogAiTranslate().doCommand(request, response);
			break;
		case "/writepro.do":
			new BlogWrite().doCommand(request, response);
			break;
		case "/view.do":
			new BlogView().doCommand(request, response);
			page = "/portfolio/view.jsp";
//			RequestDispatcher rd3 = request.getRequestDispatcher("/portfolio/view.jsp");
//			rd3.forward(request, response);
			break;
		case "/update.do":
			new BlogView().doCommand(request, response);
			page="/portfolio/update.jsp";
			System.out.println("포트폴리오 수정 처리");
			break;
		case "/updatepro.do":
			new BlogUpdate().doCommand(request, response);
			break;
		case "/delete.do":
			new BlogDelete().doCommand(request, response);
			
			System.out.println("포트폴리오 삭제 처리");
			break;
		default:
			System.out.println("잘못된 요청입니다.");
			break;
		}
		if(page != null) {
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

}

//	1. 사용자가 브라우저에서 글 작성 후 "저장" 버튼 클릭 
//	↓
//	2. BlogController가 "/port/writepro.do" 요청 받음   
//	↓
//	3. BlogController → BlogWrite.doCommand() 호출   
//	↓
//	4. BlogWrite가 사용자 입력 받아서 BlogDto 상자에 넣음   
//	↓
//	5. BlogWrite → BlogDao.blogInsert(dto) 호출   
//	↓
//	6. BlogDao가 DBManager에게 연결 열쇠 요청   
//	↓
//	7. DBManager가 데이터베이스에 연결   
//	↓
//	8. BlogDao가 SQL 문 준비하고 실행   
//	↓
//	9. 데이터베이스에 블로그 글이 저장됨! ✅   
//	↓
//	10. BlogController가 사용자에게 "저장 완료!" 알림
