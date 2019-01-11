package cz.muni.fi.pa165.music_library.dto;

/**
 * @author Klara Minsterova
 */

public class UserAuthenticateDto {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAuthenticateDto)) return false;

        UserAuthenticateDto userAuthenticateDto = (UserAuthenticateDto) o;

        if (!getEmail().equals(userAuthenticateDto.getEmail())) return false;
        return getPassword() == userAuthenticateDto.getPassword();
    }

    @Override
    public int hashCode() {
        int result = getPassword().hashCode();
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
