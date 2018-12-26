package cz.muni.fi.pa165.music_library.data.entities;

public enum UserLevel {
    BasicUser("BasicUser"),
    Admin("Admin");

    private String userLevel;

    UserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getLevel() {
        return userLevel;
    }
}
