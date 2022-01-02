package org.skygreen.kantanmemo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wordlist")
public class Wordlist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private List<Word> words;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

}