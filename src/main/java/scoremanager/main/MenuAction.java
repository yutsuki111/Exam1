package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class MenuAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{	    
		// menu.jspにフォア―ド
		req.getRequestDispatcher("menu.jsp").forward(req, res);
	}
}
