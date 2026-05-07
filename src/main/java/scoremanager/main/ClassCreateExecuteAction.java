package scoremanager.main;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		// セッションを取得
		HttpSession session = req.getSession();
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		// パラメータ取得
		String num = req.getParameter("class_num");
		// インスタンス化
		ClassNum class_num = new ClassNum();
		ClassNumDao numDao = new ClassNumDao();
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		
		// 重複がないかgetメソッドを呼び出し
		class_num = numDao.get(num, school);
		// 重複してる時
		if(class_num != null) {
			// リクエストにデータをセット
			req.setAttribute("errors", "クラス名が重複しています");
			// ubject_create.jspにフォア―ド
			req.getRequestDispatcher("class_create.jsp").forward(req, res);
			return;
		}
		// インスタンス化
		class_num = new ClassNum();
		// インスタンスにそれぞれの値セット
		class_num.setClass_num(num);
		class_num.setSchool(school); 
		// saveメソッドを呼び出し（追加）
		numDao.save(class_num);
		
		// subject_create_done.jspにフォア―ド
		req.getRequestDispatcher("class_create_done.jsp").forward(req, res);
		
	}
}
