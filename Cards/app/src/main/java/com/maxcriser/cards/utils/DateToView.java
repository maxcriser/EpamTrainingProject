package com.maxcriser.cards.utils;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateToView {

    public static void setDateToCreditView(final TextView pView, final Calendar pCalendar) {
        final SimpleDateFormat dateFormat = getCreditDateFormat();
        pView.setText(dateFormat.format(pCalendar.getTime()));
    }

    public static void setDateToTicketView(final TextView pView, final Calendar pCalendar) {
        final SimpleDateFormat dateFormat = getTicketDateFormat();
        pView.setText(dateFormat.format(pCalendar.getTime()));
    }

    public static void setTimeToView(final TextView pView, final Calendar pCalendar) {
        final SimpleDateFormat timeFormat = getTicketTimeFormat();
        pView.setText(timeFormat.format(pCalendar.getTime()));
    }

    private static SimpleDateFormat getCreditDateFormat() {
        return new SimpleDateFormat("MM/yy", Locale.US);
    }

    public static SimpleDateFormat getTicketDateFormat() {
        return new SimpleDateFormat("d MMM yyyy", Locale.US);
    }

    public static SimpleDateFormat getTicketTimeFormat() {
        return new SimpleDateFormat("h:mm a", Locale.US);
    }
}
