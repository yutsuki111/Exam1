package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1. リクエストパラメータの取得
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");

		HttpSession session = req.getSession(); 
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		School school = teacher.getSchool();
		Subject subject = new Subject();
		
		// 3. 更新用データの作成
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);

		// 4. DB更新実行
		SubjectDao subDao = new SubjectDao();
		// StudentDaoのsaveメソッドは、内部でgetして存在すればUPDATEを実行する仕様なのでこれだけでOK
		boolean result = subDao.save(subject);
		if(!result) {
			req.setAttribute("errortext", "科目が存在していません");
			req.setAttribute("subject", subject);
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
		}
		// 5. 完了画面へフォワード
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}