package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.TestListStudent;


public class TestListStudentDao extends Dao {

	public List<TestListStudent> filter(Student student) throws Exception {
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		
		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from test where student_no = ?");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, student.getNo());
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

	public List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		SubjectDao subDao = new SubjectDao();
		// リザルトセットを全件走査
		while (rSet.next()) {
			TestListStudent test= new TestListStudent();
			School school = new School();
			school.setCd(rSet.getString("school_cd"));
			String cd = rSet.getString("subject_cd");
			Subject subject = subDao.get(cd,school);
			
			
			test.setSubjectName(subject.getName());
			test.setSubjectCd(rSet.getString("subject_cd"));
			test.setNum(rSet.getInt("no"));
			test.setPoint(rSet.getInt("point"));
			list.add(test);
		}
		return list;
		
		
	}

}