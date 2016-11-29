package sample;

import java.util.ArrayList;

public class Word {
    private String name;
    private String meaning;
    private Date enteredDate;

    public Word(String args) {
        ArrayList<Integer> delim = new ArrayList<>();
        for (int i = 0; i < args.length(); i++)
            if (args.charAt(i) == '■')
                delim.add(i);
        setName(args.substring(0, delim.get(0)));
        setMeaning(args.substring(delim.get(0) + 1, delim.get(1)));
        setEnteredDate(new Date(args.substring(delim.get(1) + 1, args.length())));
    }

    public Word(String name, String meaning, Date enteredDate) {
        this.name = name;
        this.meaning = meaning;
        this.enteredDate = enteredDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + "■" + meaning + "■" + enteredDate + "\n";
    }
}
