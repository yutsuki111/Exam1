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
     * 指定条件（入学年度・クラス・科目）に合致する学生の成績一覧を取得
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        
        // 学生(STUDENT)を主軸に、試験結果(TEST)を外部結合するSQL
        // 試験を受けていない学生も表示させるために LEFT JOIN を使用
        String sql = "SELECT s.NO, s.NAME, s.ENT_YEAR, s.CLASS_NUM, t.NO AS TEST_NO, t.POINT " +
                     "FROM STUDENT s " +
                     "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO AND t.SUBJECT_CD = ? AND t.SCHOOL_CD = ? " +
                     "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND s.SCHOOL_CD = ? " +
                     "ORDER BY s.NO ASC, t.NO ASC";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, subject.getCd());
            statement.setString(2, school.getCd());
            statement.setInt(3, entYear);
            statement.setString(4, classNum);
            statement.setString(5, school.getCd());
            
            ResultSet rSet = statement.executeQuery();
            
            // 同一学生の複数回データをまとめるためのMap
            Map<String, TestListSubject> map = new HashMap<>();

            while (rSet.next()) {
                String studentNo = rSet.getString("no");
                TestListSubject tls = map.get(studentNo);

                // 該当学生のデータがMapに未登録なら新規作成
                if (tls == null) {
                    tls = new TestListSubject();
                    tls.setNo(studentNo);
                    tls.setName(rSet.getString("name"));
                    tls.setEntYear(rSet.getInt("ent_year"));
                    tls.setClassNum(rSet.getString("class_num"));
                    tls.setSubjectName(subject.getName());
                    
                    map.put(studentNo, tls);
                    list.add(tls); // 戻り値用のリストに追加
                }

                // 試験データ（回数と点数）があればBeanの内部Mapに格納
                int testNo = rSet.getInt("test_no");
                int point = rSet.getInt("point");
                if (testNo > 0) {
                    tls.putPoint(testNo, point);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
}