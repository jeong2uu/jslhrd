package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BlogDao;
import model.BlogDto;
import model.Command;

public class BlogSelectAll implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BlogDao dao = new BlogDao();        // 1단계: BlogDao 객체 생성
		List<BlogDto> list = dao.getAll();   // 2단계: getAll() 메서드 호출
		
		request.setAttribute("list", list);
		
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		//공식: 출력하는 모든 값은 request 속성에 담아서 forward한다
		//request 속성에 담겨진 객체는 다른 페이지로 이동하면 사용할 수 없다.
		//session 속성과 request속성 차이점을 생각해 보자
	}

}
