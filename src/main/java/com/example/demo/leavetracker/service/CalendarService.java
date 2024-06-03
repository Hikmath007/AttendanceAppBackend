package com.example.demo.leavetracker.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.demo.leavetracker.dto.CalendarDateDto;

@Service
public class CalendarService {

	public List<CalendarDateDto> getCalendarDates(LocalDate startDate) {
        List<CalendarDateDto> calendarDates = new ArrayList<>();
        LocalDate date = startDate;

        while (date.getMonthValue() == startDate.getMonthValue()) {
            String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            int year = date.getYear();
            int dayNo=date.getDayOfMonth();
       
            calendarDates.add(new CalendarDateDto(date, dayOfWeek, month, year,dayNo));
            date = date.plusDays(1);
        }

        return calendarDates;
    }

	

}
