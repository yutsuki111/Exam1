package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// HttpSession session = req.getSession(); // 本来のログイン処理
		// Teacher teacher = (Teacher)session.getAttribute("user");

		// 【テスト用】仮の学校情報を設定
		School school = new School();
		school.setCd("oom");

		// 1. 入学年度のプルダウン用リストを作成（今年から10年前まで）
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		// 2. クラス番号のプルダウン用リストをDBから取得
		ClassNumDao classNumDao = new ClassNumDao();
		List<String> list = classNumDao.filter(school);

		// 3. 画面（JSP）に渡すデータをリクエストにセット
		req.setAttribute("ent_year_set", entYearSet); // 入学年度の選択肢
		req.setAttribute("class_num_set", list);      // クラスの選択肢

		// 4. 学生登録画面（JSP）へフォワード
		req.getRequestDispatcher("student_create.jsp").forward(req, res);
	}
}