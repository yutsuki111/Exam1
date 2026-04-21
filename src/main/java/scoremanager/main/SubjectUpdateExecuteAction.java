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

		// リクエストパラメータの取得
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");

		// セッションを取得
		HttpSession session = req.getSession(); 
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		
		// インスタンス化
		Subject subject = new Subject();
		
		// 更新用データの作成
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);

		// DB更新実行
		SubjectDao subDao = new SubjectDao();
		// StudentDaoのsaveメソッドは、内部でgetして存在すればUPDATEを実行する仕様なのでこれだけでOK
		boolean result = subDao.save(subject);
		// フラグがない場合
		if(!result) {
			// リクエストデータにセット
			req.setAttribute("errortext", "科目が存在していません");
			req.setAttribute("subject", subject);
			// subject_update.jspにフォア―ド
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
		}
		// 5. 完了画面へフォワード
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}