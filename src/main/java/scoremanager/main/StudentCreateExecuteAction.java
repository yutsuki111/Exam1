package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1. リクエストパラメータの取得
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String entYearStr = req.getParameter("ent_year");
		String classNum = req.getParameter("class_num");

		// 型変換用の準備
		int entYear = 0;
		if (entYearStr != null && !entYearStr.isEmpty()) {
			entYear = Integer.parseInt(entYearStr);
		}

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();

		// 2. 準備
		StudentDao sDao = new StudentDao();
		Map<String, String> errors = new HashMap<>();

		// 3. ビジネスロジック：重複チェック (設計書 image_47b9e7.png 対応)
		Student existingStudent = sDao.get(no);
		if (entYearStr.equals("0")){
			// すでに学生番号が存在する場合
			errors.put("f1", "入学年度を選択してください");
			req.setAttribute("errors", errors);
			
			// 登録画面へ戻るために、Actionを再実行（または直接JSPへ。ここではAction経由を推奨）
			// 入力値を保持させるためにリクエストにセット
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("ent_year", entYear);
			req.setAttribute("class_num", classNum);
			
			// StudentCreate.actionへフォワードして、プルダウンを再取得させる
			req.getRequestDispatcher("StudentCreate.action").forward(req, res);
			return;
		}
		
		if (existingStudent != null) {
			// すでに学生番号が存在する場合
			errors.put("no", "学生番号が重複しています");
			req.setAttribute("errors", errors);
			
			// 登録画面へ戻るために、Actionを再実行（または直接JSPへ。ここではAction経由を推奨）
			// 入力値を保持させるためにリクエストにセット
			req.setAttribute("no", no);
			req.setAttribute("name", name);
			req.setAttribute("ent_year", entYear);
			req.setAttribute("class_num", classNum);
			
			// StudentCreate.actionへフォワードして、プルダウンを再取得させる
			req.getRequestDispatcher("StudentCreate.action").forward(req, res);
			return;
		}

		// 4. 保存処理（重複がなければ実行）
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(true); // 新規登録時はデフォルトで「在学中」
		student.setSchool(school);

		sDao.save(student);

		// 5. 完了画面へフォワード
		req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
	}
}
