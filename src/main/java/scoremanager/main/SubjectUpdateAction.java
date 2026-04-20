package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// パラメータ取得
		String cd = req.getParameter("cd");

		// セッションを取得
		HttpSession session = req.getSession(); 
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		
		// インスタンス化
		SubjectDao subDao = new SubjectDao();
		
		// getメソッドを呼び出し
		Subject subject = subDao.get(cd, school);

		

		// リクエストにデータをセット
		req.setAttribute("subject", subject);

		// subject_update.jspへフォワード
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}
