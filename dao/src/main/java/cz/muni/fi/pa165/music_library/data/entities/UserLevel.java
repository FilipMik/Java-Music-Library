package cz.muni.fi.pa165.music_library.data.entities;

public enum UserLevel {
    BASIC_USER("BASIC_USER"),
    ADMIN("ADMIN");

    private String userLevel;

    UserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getLevel() {
        return userLevel;
    }
}
