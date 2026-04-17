package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザー情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        // ログイン中の先生の所属校を取得
        School school = teacher.getSchool();
        // ローカル変数の準備
        List<Subject> subjects = null; 
        LocalDate todaysDate = LocalDate.now(); 
        int year = todaysDate.getYear(); 
         
        ClassNumDao classNumDao = new ClassNumDao(); 
        SubjectDao subDao = new SubjectDao(); 
        
        // 入学年度のプルダウンリスト作成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // クラス一覧を取得（先生の所属校に紐づくもの）
        List<String> list = classNumDao.filter(school);
        // 科目データを取得
        subjects = subDao.filter(school);
       

        // JSPへ渡すデータをセット
        req.setAttribute("subjects", subjects);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        // 学生一覧画面へフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}