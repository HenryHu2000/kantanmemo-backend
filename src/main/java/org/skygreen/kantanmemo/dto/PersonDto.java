package org.skygreen.kantanmemo.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class PersonDto implements Serializable {
    private final Long id;
    private final String name;
    private final Map<Long, Integer> progress;
    private final UserSettingsDto userSettings;

    public PersonDto(Long id, String name, Map<Long, Integer> progress, UserSettingsDto userSettings) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.userSettings = userSettings;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Long, Integer> getProgress() {
        return progress;
    }

    public UserSettingsDto getUserSettings() {
        return userSettings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto entity = (PersonDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.progress, entity.progress) &&
                Objects.equals(this.userSettings, entity.userSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, progress, userSettings);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "progress = " + progress + ", " +
                "userSettings = " + userSettings + ")";
    }
}
