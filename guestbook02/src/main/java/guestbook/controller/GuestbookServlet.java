package guestbook.controller;

import java.io.IOException;
import java.util.List;

import guestbook.dao.GuestBookDao;
import guestbook.vo.GuestBookVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("a");
		// 요청을 제어하는 코드
		if("add".equals(action)){
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			
			GuestBookVo vo = new GuestBookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			GuestBookDao dao = new GuestBookDao();
			dao.insert(vo);
			
			response.sendRedirect("/guestbook02/gb");
		
		} else if ("deleteform".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);			
		} else if ("delete".equals(action)) {
			Long id = Long.parseLong(request.getParameter("id"));
			String password = request.getParameter("password");
			
			new GuestBookDao().deleteByIdandPassword(id, password);
			
			response.sendRedirect("/guestbook02/gb");
		} else {
			// 내가 원하는 대로 데이터가 오지 않는 경우를 처리
			List<GuestBookVo> list = new GuestBookDao().findAll();
			// Dao를 통해서 받아온 list를 jsp에 전달하기 위해 request에 삽입
			request.setAttribute("list", list);
			// Request 제어권을 JSP쪽으로 넘김 
			// 1. Request를 넘길 수 있는 Dispatcher를 호출함
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			// 2. Forward 로 제어권을 JSP로 넘김
			rd.forward(request, response);
			// 제어권을 다 넘겼기 때문에 forward 뒤에는 코드를 넣지 않는게 좋음!!
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
