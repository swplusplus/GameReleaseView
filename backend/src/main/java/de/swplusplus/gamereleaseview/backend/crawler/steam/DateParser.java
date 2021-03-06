package de.swplusplus.gamereleaseview.backend.crawler.steam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
class DateParser {
    private final int COMING_SOON_MONTH = 12 * 5;

    private final Logger logger = LoggerFactory.getLogger(DateParser.class);

    Pair<Date, Date> parseDate(String strDate, boolean isFlaggedComingSoon) throws ParseException {

        // This is the official steam date format, and the only one allowed to be in the past (actually released).
        try {
            Date d = new SimpleDateFormat("d MMM, yyyy", Locale.US).parse(strDate);
            return Pair.of(d, d);
        } catch (ParseException e) {
            // ignore
        }

        try {
            strDate = strDate.trim();

            List<String> singleDateFormats = new ArrayList<>() {{
                add("d MMM. yyyy");
                add("d MMM yyyy");
                add("d. MMM, yyyy");
                add("MMM, d\'th\', yyyy");
                add("MMM yyyy");
                add("MMM - yyyy");
                add("yyyy");
            }};
            for (String format : singleDateFormats) {
                try {
                    Date d = new SimpleDateFormat(format, Locale.US).parse(strDate);
                    if (d.after(new Date())) {
                        return Pair.of(d, d);
                    }
                } catch (ParseException e) {
                    // ignore
                }
            }



            if (strDate.toLowerCase().matches("q[1234].*\\d{4}.*")) {
                Map<String, Integer> startDates = new HashMap<>() {{
                    put("q1", 0);
                    put("q2", 3);
                    put("q3", 6);
                    put("q4", 9);
                }};
                try {
                    Date d1 = new SimpleDateFormat("yyyy").parse(strDate.substring(3));
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTime(d1);
                    calendar.add(Calendar.MONTH, startDates.get(strDate.toLowerCase().substring(0, 2)));
                    d1 = calendar.getTime();
                    calendar.add(Calendar.MONTH, 3);
                    Date d2 = calendar.getTime();
                    // check that we are still in the future
                    if (d2.after(new Date())) {
                        return Pair.of(d1, d2);
                    }
                } catch (ParseException e) {
                    // ignore
                }
            }

            if (isComingSoonButUnknown(strDate, isFlaggedComingSoon)) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.clear(Calendar.HOUR);
                calendar.clear(Calendar.MINUTE);
                calendar.clear(Calendar.SECOND);
                calendar.clear(Calendar.MILLISECOND);
                Date d1 = calendar.getTime();
                calendar.add(Calendar.MONTH, COMING_SOON_MONTH);
                Date d2 = calendar.getTime();
                return Pair.of(d1, d2);
            }

        } catch (Exception e) {
            // ignore
        }
        throw new ParseException("unhandled date format: " + strDate, 0);
    }

    public boolean isComingSoonButUnknown(String strDate, boolean isFlaggedComingSoon) {
        final List<String> blacklistedPhrases = new ArrayList<>() {{
            add("2018");
            add("2017");
            add("2016");
            add("2015");
            add("2014");
            add("2013");
            add("2012");
            add("2011");
            add("2010");
        }};

        if (isFlaggedComingSoon) {
            boolean blacklisted = false;
            for (String ph : blacklistedPhrases) {
                if (strDate.toLowerCase().contains(ph)) {
                    blacklisted = true;
                    break;
                }
            }
            return !blacklisted;
        }
        return false;
    }
}
