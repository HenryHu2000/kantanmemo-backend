package org.skygreen.kantanmemo.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "word_learning_data")
public class WordLearningData {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Word word;
    private int familiarity;
    private Calendar lastSeen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getFamiliarity() {
        return familiarity;
    }

    public void setFamiliarity(int familiarity) {
        this.familiarity = familiarity;
    }

    public Calendar getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Calendar lastSeen) {
        this.lastSeen = lastSeen;
    }
}