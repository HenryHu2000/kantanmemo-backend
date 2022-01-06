package org.skygreen.kantanmemo.data;

import org.skygreen.kantanmemo.entity.WordKnownType;
import org.skygreen.kantanmemo.entity.WordLearningData;

import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Deque;
import java.util.List;

public class LearningProcess {
    private static final int MAX_WORD_QUEUE_LENGTH = 20;
    private static final int MIN_FAMILIARITY = 0;
    private static final int MAX_FAMILIARITY = 5;

    private final Deque<WordLearningData> remainingWords;
    private final Deque<WordLearningData> learningQueue;
    private final Deque<WordLearningData> finishedWords;

    private boolean isTerminated;

    public LearningProcess(List<WordLearningData> learningList) {
        remainingWords = new ArrayDeque<>();
        learningQueue = new ArrayDeque<>();
        finishedWords = new ArrayDeque<>();

        if (!learningList.isEmpty()) {
            remainingWords.addAll(learningList);
            addNewWordToQueue(); // Add the first word to learningQueue
            isTerminated = false;
        } else {
            // Handle the empty learningList case
            isTerminated = true;
        }
    }

    public void proceed(boolean isKnown) {
        if (isTerminated) {
            return;
        }

        var isCurrentExist = handleCurrent(isKnown);
        if (!isCurrentExist) {
            addNewWordToQueue();
        }

        var isNextExist = handleNext();
        if (!isNextExist) {
            this.isTerminated = true;
        }
    }

    /**
     * @return false if there is no current word
     */
    private boolean handleCurrent(boolean isKnown) {
        WordLearningData currentWord = getCurrentWord();
        if (currentWord == null) {
            return false;
        }

        currentWord.setLastSeen(Calendar.getInstance());
        if (isKnown) {
            switch (currentWord.getWordKnownType()) {
                case KNOWN:
                case HALF_KNOWN:
                    currentWord.setWordKnownType(WordKnownType.KNOWN);
                    incrementFamiliarity(currentWord);
                    moveWordToFinished();
                    break;
                case UNKNOWN:
                    currentWord.setWordKnownType(WordKnownType.HALF_KNOWN);
                    moveWordToQueueBack();
                    break;
                default:
                    break;
            }
        } else {
            currentWord.setWordKnownType(WordKnownType.UNKNOWN);
            decrementFamiliarity(currentWord);
            moveWordToQueueBack();
        }
        return true;
    }

    private void incrementFamiliarity(WordLearningData currentWordData) {
        currentWordData.setFamiliarity(Math.min(currentWordData.getFamiliarity() + 1, MAX_FAMILIARITY));
    }

    private void decrementFamiliarity(WordLearningData currentWordData) {
        currentWordData.setFamiliarity(Math.max(currentWordData.getFamiliarity() - 1, MIN_FAMILIARITY));
    }

    private void moveWordToFinished() {
        finishedWords.add(learningQueue.remove());
    }

    private void moveWordToQueueBack() {
        learningQueue.add(learningQueue.remove());
    }

    /**
     * Add the next word to the queue if there is one.
     *
     * @return whether there is a next word
     */
    private boolean handleNext() {
        if (remainingWords.isEmpty() && learningQueue.isEmpty()) {
            return false;
        }
        if ((!remainingWords.isEmpty()) && learningQueue.size() < MAX_WORD_QUEUE_LENGTH) {
            addNewWordToQueue();
        }
        return true;
    }

    /**
     * Add a word from remainingWords to the front of the queue.
     */
    private boolean addNewWordToQueue() {
        if (!remainingWords.isEmpty()) {
            var wordLearningData = remainingWords.pop();
            wordLearningData.setWordKnownType(WordKnownType.HALF_KNOWN);
            learningQueue.addFirst(wordLearningData);
            return true;
        } else {
            return false;
        }
    }

    public WordLearningData getCurrentWord() {
        return learningQueue.peek();
    }

    public boolean isTerminated() {
        return isTerminated;
    }
}
