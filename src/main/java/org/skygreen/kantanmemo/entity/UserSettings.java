package org.skygreen.kantanmemo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserSettings {
    @ManyToOne
    private Wordlist currentWordlist;
    private Integer dailyNewWordNum;
    private Integer dailyRevisingWordNum;

    public Wordlist getCurrentWordlist() {
        return currentWordlist;
    }

    public void setCurrentWordlist(Wordlist currentWordlist) {
        this.currentWordlist = currentWordlist;
    }

    public int getDailyNewWordNum() {
        return dailyNewWordNum;
    }

    public void setDailyNewWordNum(int dailyNewWordNum) {
        this.dailyNewWordNum = dailyNewWordNum;
    }

    public int getDailyRevisingWordNum() {
        return dailyRevisingWordNum;
    }

    public void setDailyRevisingWordNum(int dailyRevisingWordNum) {
        this.dailyRevisingWordNum = dailyRevisingWordNum;
    }
}