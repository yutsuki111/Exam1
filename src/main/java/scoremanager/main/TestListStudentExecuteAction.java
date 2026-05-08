package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// ★追加：School, Subject, ClassNumDao, SubjectDao のインポート
import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // ★追加：ログイン中の先生の所属校を取得（クラスや科目の絞り込みに必要）
        School school = teacher.getSchool();

        String studentNo = req.getParameter("f4"); // 学生番号

        // 学生情報を取得
        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);

        if (student != null) {
            // 成績リストを取得
            TestListStudentDao tlsDao = new TestListStudentDao();
            List<TestListStudent> list = tlsDao.filter(student);

            req.setAttribute("student", student);
            req.setAttribute("test_list", list);
        } else {
            req.setAttribute("error", "学生情報が存在しませんでした");
        }

        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        
        // 1. 入学年度のプルダウンリスト作成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        
        // DAOの準備
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();
        
        // 2. クラス一覧を取得
        List<String> classList = classNumDao.filter(school);
        // 3. 科目データを取得
        List<Subject> subjects = subDao.filter(school);
        
        // JSPへ渡すデータをセット
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("subjects", subjects);
        
        // 検索した学生番号を画面の入力枠に残すためのセット
        req.setAttribute("f4", studentNo);
        // ========================================================

        // 結果画面
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}