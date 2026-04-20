package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		// セッションを取得
		HttpSession session = req.getSession();
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		// パラメータ取得
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		// インスタンス化
		Subject subject = new Subject();
		SubjectDao subDao = new SubjectDao();
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		// 科目コードが3文字より大きいとき
		if(cd.length() > 3 ) {
			// リクエストにデータをセット
			req.setAttribute("lengherrortext", "科目コードは3文字で入力してください");
			// ubject_create.jspにフォア―ド
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		// 重複がないかgetメソッドを呼び出し
		subject = subDao.get(cd, school);
		// 重複してる時
		if(subject != null) {
			// リクエストにデータをセット
			req.setAttribute("duperrortext", "科目コードが重複しています");
			// ubject_create.jspにフォア―ド
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		// インスタンス化
		subject = new Subject();
		// インスタンスにそれぞれの値セット
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);
		// saveメソッドを呼び出し（追加）
		subDao.save(subject);
		
		// subject_create_done.jspにフォア―ド
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
		
	}
}
