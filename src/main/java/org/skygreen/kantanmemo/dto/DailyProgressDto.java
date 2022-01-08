package org.skygreen.kantanmemo.dto;

import java.util.Objects;

public class DailyProgressDto {
    private final int remainingNum;
    private final int learningNum;
    private final int finishedNum;

    public DailyProgressDto(int remainingNum, int learningNum, int finishedNum) {
        this.remainingNum = remainingNum;
        this.learningNum = learningNum;
        this.finishedNum = finishedNum;
    }

    public int getRemainingNum() {
        return remainingNum;
    }

    public int getLearningNum() {
        return learningNum;
    }

    public int getFinishedNum() {
        return finishedNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyProgressDto that = (DailyProgressDto) o;
        return remainingNum == that.remainingNum && learningNum == that.learningNum && finishedNum == that.finishedNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainingNum, learningNum, finishedNum);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "remainingNum = " + remainingNum + ", " +
                "learningNum = " + learningNum + ", " +
                "finishedNum = " + finishedNum + ")";
    }
}
