package app.log.model;

import java.util.Arrays;
import java.util.Optional;

public enum SeverityType {
    C("Critical"),
    W("Warning"),
    I("Information");

    private String dscr;

   SeverityType(String dscr) {
        this.dscr = dscr;
    }

    public String getDscr() {
        return this.dscr;
    }

    public static Optional<SeverityType> fromCode(String dscr) {
        return Arrays.stream(values())
          .filter(item -> item.dscr.equalsIgnoreCase(dscr))
          .findFirst();
    }
  }
  