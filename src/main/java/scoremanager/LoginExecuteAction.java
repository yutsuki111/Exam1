package scoremanager;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		Teacher teacher = null;
		TeacherDao tDao = new TeacherDao();
		
		teacher = tDao.login(id,password);
//		if	文
		if(teacher.getName() == null) {
			req.setAttribute("errortext", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return;
			
		}else {
			teacher.setAuthenticated(true);
			HttpSession session = req.getSession();
			session.setAttribute("user", teacher);
		}
		
		
		res.sendRedirect("main/Menu.action");
		
	}
}
