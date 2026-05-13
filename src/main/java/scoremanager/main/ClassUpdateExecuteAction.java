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
		// DB更新実行
		ClassNumDao numDao = new ClassNumDao();
		// 重複がないかgetメソッドを呼び出し
		ClassNum check = numDao.get(class_num, school);
		// 重複してる時
		if(check != null && !class_num.equals(old_class_num)) {
			// リクエストにデータをセット
			req.setAttribute("errortext", "クラス名が重複しています");
			num.setClass_num(class_num);
			req.setAttribute("class_num", num);
			// ubject_create.jspにフォア―ド
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
			return;
		}
		// 更新用データの作成
		num.setClass_num(old_class_num);
		num.setSchool(school);
		
		boolean result = numDao.save(num, class_num);
		
		// フラグがない場合
		if(!result) {
			// リクエストデータにセット
			req.setAttribute("errortext", "クラスが存在していません");
			req.setAttribute("class_num", num);
			// class_update.jspにフォア―ド
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
			return;
		}
		// 5. 完了画面へフォワード
		req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
	}
}