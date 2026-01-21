package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainController() {
        super();   //부모클래스의 생성자를 호출.(물론 적지않아도 컴파일러가 자동으로 매개변수 없는 부모 생성자를 호출
        			/*상속 관계에서 자식 클래스는 부모 클래스의 모든 기능을 사용할 수 있습니다. 
        			 * 하지만 부모 클래스가 제대로 초기화되지 않으면 자식 클래스도 제대로 작동할 수 없습니다. 
        			 * 따라서 생성자에서 부모 클래스의 생성자를 먼저 호출하여 부모 클래스의 초기화를 보장해야 합니다.*/
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
		//RequestDispatcher : 서버 내부에서 다른 페이지로 넘기는 도구
		//getRequestDispatcher("index.html") ：“다음 목적지는 index.html야” 라는 티켓을 끊는 느낌
		//브라우저가 index.html를 새로 요청하는 게 아니라, 서버 내부에서 index.html를 호출하는 거야.
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

