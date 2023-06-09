package com.onlineteer.ui.home;

public class HomeViewModel {
    String RoundText;
    String timingStart;

    public String getTimingEnd() {
        return timingEnd;
    }

    public HomeViewModel(String roundText, String timingStart, String timingEnd, String resulttiming) {
        RoundText = roundText;
        this.timingStart = timingStart;
        this.timingEnd = timingEnd;
        this.resulttiming = resulttiming;
    }

    String timingEnd;
    String resulttiming;

    public String getRoundText() {
        return RoundText;
    }

    public String getTimingStart() {
        return timingStart;
    }

    public String getResulttiming() {
        return resulttiming;
    }

}