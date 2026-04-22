package scoremanager.main;

import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


public class ClassListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザー情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // DAOを使ってクラス一覧を取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> list = cNumDao.filter(teacher.getSchool());

        // リクエストにクラス一覧をセット
        req.setAttribute("class_list", list);

        // JSPへフォワード
        req.getRequestDispatcher("class_list.jsp").forward(req, res);
    }
}