package org.skygreen.kantanmemo.dto;

import org.skygreen.kantanmemo.entity.Wordlist;

import java.io.Serializable;
import java.util.Objects;

public class WordlistDto implements Serializable {
    private final Long id;
    private final String name;

    public WordlistDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static WordlistDto wordlistToDto(Wordlist wordlist) {
        if (wordlist == null) {
            return null;
        }
        return new WordlistDto(wordlist.getId(), wordlist.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordlistDto entity = (WordlistDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}
