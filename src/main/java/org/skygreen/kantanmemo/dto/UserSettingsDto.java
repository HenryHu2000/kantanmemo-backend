package org.skygreen.kantanmemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class UserSettingsDto implements Serializable {
    private final Long currentWordlistId;

    public UserSettingsDto(@JsonProperty("currentWordlistId") Long currentWordlistId) {
        this.currentWordlistId = currentWordlistId;
    }

    public Long getCurrentWordlistId() {
        return currentWordlistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettingsDto entity = (UserSettingsDto) o;
        return Objects.equals(this.currentWordlistId, entity.currentWordlistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentWordlistId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "currentWordlistId = " + currentWordlistId + ")";
    }
}
