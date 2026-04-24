package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		HttpSession session = req.getSession(false);
	    if (session == null || session.getAttribute("user") == null) {
	    	req.getRequestDispatcher("login.jsp").forward(req, res);
	        
	        return;
	    }else {
	    	// login.jspにフォア―ド
	    	res.sendRedirect("main/Menu.action");
	    }
		
	}
}
