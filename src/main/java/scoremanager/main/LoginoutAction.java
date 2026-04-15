package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginoutAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		req.getRequestDispatcher("main/logout.jsp").forward(req, res);
	}
}
