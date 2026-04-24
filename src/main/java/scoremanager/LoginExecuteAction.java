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
		// リクエストデータを取得
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		// 初期化
		Teacher teacher = null;
		// インスタンス化
		TeacherDao tDao = new TeacherDao();
		// loginメソッドを呼び出し
		teacher = tDao.login(id,password);
		// ログイン失敗時
		if(teacher.getName() == null) {
			// リクエストデータにセット
			req.setAttribute("errortext", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
			// login.jspにフォア―ド
			req.setAttribute("id", id);
			req.setAttribute("password", password);
			req.getRequestDispatcher("login.jsp").forward(req, res);
			return;
		// 成功時
		}else {
			// フラグをtrueに
			teacher.setAuthenticated(true);
			// セッション所得
			HttpSession session = req.getSession();
			// セッションのリクエストデータにセット
			session.setAttribute("user", teacher);
		}
		
		// リダイレクト
		res.sendRedirect("main/Menu.action");
		
	}
}
