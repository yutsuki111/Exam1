package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    /**
     * 成績登録画面用のフィルター
     * 入学年度、クラス、科目、回数を指定して成績リストを取得する
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        // SQLの考え方：
        // 指定された条件の「学生」をベースに、TESTテーブルに点数があれば取得（外部結合）
        String sql = "select s.no as student_no, s.name as student_name, s.ent_year, s.class_num, "
                   + "t.point "
                   + "from student s "
                   + "left join test t on s.no = t.student_no "
                   + "  and t.subject_cd = ? and t.no = ? and t.school_cd = ? "
                   + "where s.ent_year = ? and s.class_num = ? and s.school_cd = ? "
                   + "order by s.no asc";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, num);
            statement.setString(3, school.getCd());
            statement.setInt(4, entYear);
            statement.setString(5, classNum);
            statement.setString(6, school.getCd());

            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Test test = new Test();
                // 学生情報をセット
                Student student = new Student();
                student.setNo(rSet.getString("student_no"));
                student.setName(rSet.getString("student_name"));
                test.setStudent(student);
                
                // 点数をセット（データがない場合は -1 など未入力とわかる値にする工夫が必要）
                // ここではDBがnullなら一旦0または適切な初期値を入れる
                test.setPoint(rSet.getInt("point"));
                
                // その他情報をセット
                test.setSubject(subject);
                test.setNo(num);
                
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return list;
    }

    // TODO: saveメソッド（一括保存用）や deleteメソッドもここに実装していきます
}