package mod1.com.mybanck.domain;

public class OverdraftExeption extends Exception {
    private double deficit;

    public double getDeficit() {
        return deficit;
    }

    public OverdraftExeption(double deficit, String masseg) {
        super(masseg);
        this.deficit = deficit;
    }

}
