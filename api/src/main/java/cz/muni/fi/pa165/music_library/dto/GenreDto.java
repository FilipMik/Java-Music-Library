package cz.muni.fi.pa165.music_library.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GenreDto {
    @JsonProperty("hard")
    HARD_BASS("hard"),
    @JsonProperty("house")
    HOUSE("house"),
    @JsonProperty("techno")
    TECHNO("techno"),
    @JsonProperty("pop")
    POP("pop"),
    @JsonProperty("rock")
    ROCK("rock"),
    @JsonProperty("metal")
    METAL("metal"),
    @JsonProperty("country")
    COUNTRY("country"),
    @JsonProperty("indie")
    INDIE("indie");

    private String value;

    private GenreDto(String value) {
        this.value = value;
    }

    @JsonCreator
    public static GenreDto fromValue(String value) {
        for (GenreDto genre : values()) {
            if (genre.value.equalsIgnoreCase(value)) {
                return genre;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

    @JsonValue
    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }
}
