package bean;

import java.io.Serializable;

/**
 * ユーザーの認証状態を保持するBean
 */
public class User implements Serializable {

    // 認証状態（true: 認証済み / false: 未認証）
    private boolean isAuthenticated;
    
    // 状態の確認
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    // 状態の更新（ログイン成功時に true をセットするなど）
    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
}