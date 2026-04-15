package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1. パラメータ（学生番号）の取得
		// 一覧画面の「変更」リンクから送られてくる ?no=... を受け取る
		String no = req.getParameter("no");

		// 【テスト用】学校情報の固定
		School school = new School();
		school.setCd("oom");

		// 2. DBから現在の学生情報を取得
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(no);

		// 3. プルダウン用の選択肢を作成
		// 入学年度リスト（変更画面では表示のみですが、一応作成）
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		// クラス一覧
		ClassNumDao classNumDao = new ClassNumDao();
		List<String> classNumSet = classNumDao.filter(school);

		// 4. リクエストにデータをセット
		req.setAttribute("student", student);     // 現在の学生情報
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_num_set", classNumSet);

		// 5. 学生変更画面（JSP）へフォワード
		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}
}
