package org.shevliakov.carpatymilitaryaccounting.controller.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class ConvertDatesToYears {

  private ConvertDatesToYears() {
    throw new IllegalStateException("Utility class");
  }

  public static List<Integer> convert(List<Date> dates) {
    List<Integer> years = new ArrayList<>();
    for (Date date : dates) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      if (!years.contains(calendar.get(Calendar.YEAR))) {
        years.add(calendar.get(Calendar.YEAR));
      }
    }

    years.sort(Comparator.comparingInt(year -> year));
    return years;
  }

}
