package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    /**
     * 指定された条件で学生と成績を検索する
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        // 学生を主軸に、指定科目の成績をLEFT JOINで取得するSQL
        String sql = "SELECT s.NO, s.NAME, s.ENT_YEAR, s.CLASS_NUM, t.NO AS TEST_NO, t.POINT " +
                     "FROM STUDENT s " +
                     "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO AND t.SUBJECT_CD = ? " +
                     "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND s.SCHOOL_CD = ? " +
                     "ORDER BY s.NO ASC, t.NO ASC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, school.getCd());
            ResultSet rSet = statement.executeQuery();

            Map<String, TestListSubject> map = new HashMap<>();

            while (rSet.next()) {
                String no = rSet.getString("no");
                TestListSubject tls = map.get(no);

                if (tls == null) {
                    tls = new TestListSubject();
                    tls.setNo(no);
                    tls.setName(rSet.getString("name"));
                    tls.setEntYear(rSet.getInt("ent_year"));
                    tls.setClassNum(rSet.getString("class_num"));
                    //検索条件のSubjectから科目名を取得してセットする
                    tls.setSubjectName(subject.getName());
                    
                    map.put(no, tls);
                    list.add(tls);
                }

                // 回数(TEST_NO)に応じた点数をセット
                int testNo = rSet.getInt("test_no");
                int point = rSet.getInt("point");
                if (testNo > 0) {
                    tls.putPoint(testNo, point);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }
}