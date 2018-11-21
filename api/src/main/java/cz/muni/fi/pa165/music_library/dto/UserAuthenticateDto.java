package cz.muni.fi.pa165.music_library.dto;

/**
 * @author Klara Minsterova
 */

public class UserAuthenticateDto {

    private Long userId;

    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
