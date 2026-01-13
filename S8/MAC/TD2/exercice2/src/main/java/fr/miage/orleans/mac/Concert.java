package fr.miage.orleans.mac;

import java.time.LocalDateTime;

public class Concert {
    private String headline;
    private Style style;
    private LocalDateTime localDateTime;

    public Concert(String headline, Style style, LocalDateTime localDateTime) {
        this.headline = headline;
        this.style = style;
        this.localDateTime = localDateTime;
    }

    public Concert() {
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
