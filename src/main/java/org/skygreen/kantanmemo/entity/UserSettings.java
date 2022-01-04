package org.skygreen.kantanmemo.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserSettings {
    @ManyToOne
    private Wordlist currentWordlist;

    public Wordlist getCurrentWordlist() {
        return currentWordlist;
    }

    public void setCurrentWordlist(Wordlist currentWordlist) {
        this.currentWordlist = currentWordlist;
    }
}