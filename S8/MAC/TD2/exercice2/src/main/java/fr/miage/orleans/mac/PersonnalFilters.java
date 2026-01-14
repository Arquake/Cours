package fr.miage.orleans.mac;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PersonnalFilters {

    private Set<Style> wantedStyles;
    private Set<Integer> unwantedDays;

    public PersonnalFilters() {
        this.wantedStyles = new HashSet<>();
        this.unwantedDays = new HashSet<>();
    }

    public Set<Style> getWantedStyles() {
        return wantedStyles;
    }

    public void addWantedStyles(Style wantedStyles) {
        this.wantedStyles.add(wantedStyles);
    }

    public Set<Integer> getUnwantedDays() {
        return unwantedDays;
    }

    public void addUnwantedDays(Integer unwantedDays) {
        this.unwantedDays.add(unwantedDays);
    }
}
