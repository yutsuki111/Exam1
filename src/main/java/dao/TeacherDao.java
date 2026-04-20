package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao {
	public Teacher login(String id, String password) throws Exception{
		// 初期化
		Teacher teacher = new Teacher();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from teacher where id = ? and password = ?");
			statement.setString(1, id);
			statement.setString(2, password);
			// 実行
			ResultSet rSet = statement.executeQuery();
			
			// インスタンス化
			SchoolDao sDao = new SchoolDao();
			// リザルトセットが存在する場合
			if (rSet.next()) {
				// インスタンスにセット
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				teacher.setSchool(sDao.get(rSet.getString("school_cd")));
			}
		}catch (Exception e){
			throw e;
		}finally {
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
		return teacher;
		
	}
}
