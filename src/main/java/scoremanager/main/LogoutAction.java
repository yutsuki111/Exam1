package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		// セッションをfalseに（ログイン解除）
		HttpSession session = req.getSession(false);
		// セッションの破棄
		if (session != null) {
			session.invalidate();
		}
		// logout.jspにフォア―ド
		req.getRequestDispatcher("logout.jsp").forward(req, res);
	}
}
