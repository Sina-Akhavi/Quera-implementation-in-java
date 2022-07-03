package ir.ac.kntu.logic;

public class Date {
    private int day;

    private int month;

    private int year;

    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    public Date() {
        day = 1;
        month = 1;
        year = 1401;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day >= 1) {
            this.day = day;
        } else {
            System.out.println("Wrong input!!");
        }

    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month >= 1) {
            this.month = month;
        } else {
            System.out.println("Wrong input!!");
        }

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year >= 1) {
            this.year = year;
        } else {
            System.out.println("Wrong input!!");
        }
    }

    public Date clone() {
        int day = getDay();
        int month = getMonth();
        int year = getYear();

        return new Date(day, month, year);
    }

    public int compare(Date date) {
        if (date.equals(this)) {
            return 0;
        }
        if (year > date.year) {
            return 1;
        }
        if (year < date.year) {
            return -1;
        }
        if (month > date.month) {
            return 1;
        }
        if (month < date.month) {
            return -1;
        }
        if (day > date.day) {
            return 1;
        }
        return -1;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day;
        result = prime * result + month;
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Date other = (Date) obj;
        if (day != other.day) {
            return false;
        }
        if (month != other.month) {
            return false;
        }
        if (year != other.year) {
            return false;
        }
        return true;
    }

    public String toString() {
        return year + " / " + month + " / " + day;
    }

}
