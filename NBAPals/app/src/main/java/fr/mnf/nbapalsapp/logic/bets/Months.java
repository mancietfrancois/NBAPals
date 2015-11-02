package fr.mnf.nbapalsapp.logic.bets;

/**
 * Created by Francois on 25/10/2015.
 */
public enum Months {

    OCTOBER("October", "2015-10", 0),
    NOVEMBER("November", "2015-11", 1),
    DECEMBER("December", "2015-12", 2),
    JANUARY("January", "2016-01", 3),
    FEBRUARY("February", "2016-02", 4),
    MARCH("March", "2016-03", 5),
    APRIL("April", "2016-04", 6),
    MAY("May", "2016-05", 7);

    public static final int MONTHS_NUMBER = 8;

    private String mMonthName;
    private String mMonthNumber;
    private int mTabNumber;

    Months(String name, String monthNumber, int tabNumber) {
        mMonthName = name;
        mMonthNumber = monthNumber;
        mTabNumber = tabNumber;
    }

    public String getMonthName() {
        return mMonthName;
    }

    public String getMonthNumber() {
        return mMonthNumber;
    }

    public int getTabNumber() {
        return mTabNumber;
    }

    public static Months findMonthFromName(String name) {
        for (Months m : Months.values()) {
            if (name.equals(m.getMonthName())) {
                return m;
            }
        }
        return null;
    }

    public static Months findMonthFromTabNumber(int tabNumber) {
        for (Months m : Months.values()) {
            if (tabNumber == m.getTabNumber()) {
                return m;
            }
        }
        return null;
    }

    public static Months findMonthFromNumber(String number) {
        for (Months m : Months.values()) {
            if (number.equals(m.getMonthNumber())) {
                return m;
            }
        }
        return null;
    }
}
