package org.skygreen.kantanmemo.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<WordLearningData> wordLearningDataList;
    @ElementCollection
    private Map<Wordlist, Integer> progress;
    @Embedded
    private UserSettings userSettings;

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

    public List<WordLearningData> getWordLearningDataList() {
        return wordLearningDataList;
    }

    public void setWordLearningDataList(List<WordLearningData> wordLearningDataList) {
        this.wordLearningDataList = wordLearningDataList;
    }

    public Map<Wordlist, Integer> getProgress() {
        return progress;
    }

    public void setProgress(Map<Wordlist, Integer> progress) {
        this.progress = progress;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

}
