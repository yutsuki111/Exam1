package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
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

        // 結果画面
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}