package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {

    private String subjectName;	// 科目名
    private String subjectCd;	// 科目コード
    private int num;			// 回数
    private int point;			// 得点
    /** ゲッター・セッター **/
    public String getSubjectName() {
    	return subjectName;
    }
    public String setSubjectName(String subjectName) {
    	return this.subjectName = subjectName;
    }
    public String getSubjectCd() {
    	return subjectCd;
    }
    public String setSubjectCd(String subjectCd) {
    	return this.subjectCd = subjectCd;
    }
    public int getNum() {
    	return num;
    }
    public int setNum(int num) {
    	return this.num = num;
    }
    
    public int getPoint() {
    	return point;
    }
    public int setPoint(int point) {
    	return this.point = point;
    }

}