package org.skygreen.kantanmemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class UserSettingsDto implements Serializable {
    private final Long currentWordlistId;
    private final int dailyNewWordNum;
    private final int dailyRevisingWordNum;

    public UserSettingsDto(@JsonProperty("currentWordlistId") Long currentWordlistId,
                           @JsonProperty("dailyNewWordNum") int dailyNewWordNum,
                           @JsonProperty("dailyRevisingWordNum") int dailyRevisingWordNum) {
        this.currentWordlistId = currentWordlistId;
        this.dailyNewWordNum = dailyNewWordNum;
        this.dailyRevisingWordNum = dailyRevisingWordNum;
    }

    public Long getCurrentWordlistId() {
        return currentWordlistId;
    }

    public int getDailyNewWordNum() {
        return dailyNewWordNum;
    }

    public int getDailyRevisingWordNum() {
        return dailyRevisingWordNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettingsDto entity = (UserSettingsDto) o;
        return Objects.equals(this.currentWordlistId, entity.currentWordlistId) && Objects.equals(this.dailyNewWordNum, entity.dailyNewWordNum) && Objects.equals(this.dailyRevisingWordNum, entity.dailyRevisingWordNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentWordlistId, dailyNewWordNum, dailyRevisingWordNum);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "currentWordlistId = " + currentWordlistId + ", " + "dailyNewWordNum = " + dailyNewWordNum + ", " + "dailyRevisingWordNum = " + dailyRevisingWordNum + ")";
    }
}
