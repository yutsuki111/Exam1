package scoremanager.main;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// リクエストパラメータの取得
		String class_num = req.getParameter("class_num");
		String old_class_num = req.getParameter("old_class_num");

		// セッションを取得
		HttpSession session = req.getSession(); 
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		
		// インスタンス化
		ClassNum num = new ClassNum();
		
		// 更新用データの作成
		num.setClass_num(class_num);
		num.setSchool(school);
		
		// DB更新実行
		ClassNumDao numDao = new ClassNumDao();
		// StudentDaoのsaveメソッドは、内部でgetして存在すればUPDATEを実行する仕様なのでこれだけでOK
		boolean result = numDao.save(num, old_class_num);
		// フラグがない場合
		if(!result) {
			// リクエストデータにセット
			req.setAttribute("errortext", "クラスが存在していません");
			req.setAttribute("num", num);
			// subject_update.jspにフォア―ド
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
			return;
		}
		// 5. 完了画面へフォワード
		req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
	}
}