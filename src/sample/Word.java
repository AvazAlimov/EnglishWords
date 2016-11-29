package sample;

public class Word {
    private String name;
    private String meaning;
    private Date enteredDate;

    public Word(){
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
}
