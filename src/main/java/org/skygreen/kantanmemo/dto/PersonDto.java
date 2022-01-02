package org.skygreen.kantanmemo.dto;

import org.skygreen.kantanmemo.entity.Person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PersonDto implements Serializable {
    private final Long id;
    private final String name;
    private final WordlistDto currentWordlist;
    private final Map<WordlistDto, Integer> progress;

    public PersonDto(Long id, String name, WordlistDto currentWordlist, Map<WordlistDto, Integer> progress) {
        this.id = id;
        this.name = name;
        this.currentWordlist = currentWordlist;
        this.progress = progress;
    }

    public static PersonDto personToDto(Person person) {
        if (person == null) {
            return null;
        }
        var progress = new HashMap<WordlistDto, Integer>();
        for (var key : person.getProgress().keySet()) {
            progress.put(WordlistDto.wordlistToDto(key), person.getProgress().get(key));
        }
        return new PersonDto(person.getId(), person.getName(), WordlistDto.wordlistToDto(person.getCurrentWordlist()), progress);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WordlistDto getCurrentWordlist() {
        return currentWordlist;
    }

    public Map<WordlistDto, Integer> getProgress() {
        return progress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto entity = (PersonDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.currentWordlist, entity.currentWordlist) &&
                Objects.equals(this.progress, entity.progress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, currentWordlist, progress);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "currentWordlist = " + currentWordlist + ", " +
                "progress = " + progress + ")";
    }
}
