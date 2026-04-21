package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;


public class TestListSubjectDao extends Dao {

	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		
		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(
				    "select t.*, s.name as student_name " +
				    "from test t " +
				    "left join student s on s.no = t.student_no " +
				    "where s.ent_year = ? " +
				    "and s.class_num = ? " +
				    "and t.subject_cd = ? " +
				    "and t.school_cd = ?"
				);

			// プリペアードステートメントにバインド
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setString(4, school.getCd());
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
			
			list = postFilter(rSet);
		}catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}

	public List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        while (rSet.next()) {
            TestListSubject test = new TestListSubject();

            School school = new School();
            school.setCd(rSet.getString("school_cd"));
            
            test.setClassNum(rSet.getString("class_num"));
            test.setEntYear(rSet.getInt("ent_year"));
            test.putPoint(rSet.getInt("no"), rSet.getInt("point"));
            test.setStudentName(rSet.getString("name"));
            test.setStudentNo(rSet.getString("student_no"));

            list.add(test);
        }

        return list;
		
	}

}