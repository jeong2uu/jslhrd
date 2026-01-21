package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.MemberDao;
import model.MemberDto;
import util.PasswordBcrypt;

public class MemberSave implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Dao하고 연동해서 insert처리 하는 구현부를 작성
		
		request.setCharacterEncoding("utf-8");
		String writer = request.getParameter("writer");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberDto dto = new MemberDto();
		dto.setEmail(email);
		dto.setWriter(writer);
		//패스워드 암호화 처리 필요
		String hashpassword = PasswordBcrypt.hashPassword(password);
		dto.setPassword(hashpassword);
		dto.setUserid(userid);
		dto.setPhone(phone);
		
		MemberDao dao = new MemberDao();
		dao.memberSave(dto);
		//new MemberDao().memberSave(dto);
		
		response.sendRedirect("/");		//maincontroller 실행됨.
		
	}

}

//1. 사용자가 "회원가입" 버튼 클릭   
//↓
//2. 브라우저가 "/mem/membersave.do" 요청 보냄   
//↓
//3. MemberController가 요청 받음   
//↓
//4. MemberController가 switch문으로 어떤 요청인지 확인   
//↓
//5. "/membersave.do"이면:   new MemberSave().doCommand(request, response); ← 호출!   
//↓
//6. MemberSave 클래스의 doCommand() 메서드 실행   
//↓
//7. 회원가입 처리 (데이터 받기, DB 저장 등)   
//↓
//8. 사용자에게 응답 보내기

