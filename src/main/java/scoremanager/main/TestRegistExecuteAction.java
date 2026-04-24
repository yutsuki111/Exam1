package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションと情報の準備
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // JSPのhidden項目から科目と回数を取得
        String subjectCd = req.getParameter("subject_cd");
        int num = Integer.parseInt(req.getParameter("num"));

        // 配列として送られてくる学生番号と点数を取得
        String[] studentNos = req.getParameterValues("student_no");
        String[] pointsStr = req.getParameterValues("point");

        // DAOの準備
        SubjectDao subDao = new SubjectDao();
        TestDao tDao = new TestDao();
        StudentDao sDao = new StudentDao();
        
        Subject subject = subDao.get(subjectCd, school);
        List<Test> tests = new ArrayList<>();

        // 送られてきた件数分、Testインスタンスを作成してリストに追加
        if (studentNos != null) {
            for (int i = 0; i < studentNos.length; i++) {
                Test test = new Test();
                test.setNo(num);
                test.setSubject(subject);
                test.setSchool(school);
                
                // 学生情報を取得してセット
                Student student = sDao.get(studentNos[i]);
                test.setStudent(student);

                // 点数の変換処理
                int point = -1; // 初期値は未入力
                if (pointsStr[i] != null && !pointsStr[i].isEmpty()) {
                    point = Integer.parseInt(pointsStr[i]);
                }
                test.setPoint(point);

                tests.add(test);
            }
        }

        // DAOを呼んで一括保存（INSERT　追加 or UPDATE　更新）
        tDao.save(tests);

        // 完了画面へフォワード（または一覧へリダイレクト）
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}