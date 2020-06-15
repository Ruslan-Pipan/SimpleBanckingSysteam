package mod1.com.mybanck.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Cunsumers Main class, have in itself class Builder.
 * Builder - help to create.
 * */
public class Cunsumers {
    private List<Account> accounts = new ArrayList<>();
    private LocalDate date;

    private String firstName;
    private String lastName;
    private String phonNumber;
    private String adress;
    private String email;

    private int custumerNumber;
    private static int custumerNumberBase = 1000;

    private Cunsumers(Cunsumer bild) {
        this.firstName = bild.firstName;
        this.lastName = bild.lastName;
        this.phonNumber = bild.phonNumber;
        this.adress = bild.adress;
        this.email = bild.email;
        this.custumerNumber = custumerNumberBase+1;
    }

    public Account getAccount(int accNo){
        if (accNo < accounts.size()){
            return accounts.get(accNo);
        }
        return null;
    }

    public void addAccount(Account acc){
        accounts.add(acc);
    }

    /**
     * Class  bilder for cunsumer.
     * */
    public static class Cunsumer{
        private String phonNumber;
        private String adress;
        private String email;
        private String firstName;
        private String lastName;

        public Cunsumer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;

        }

        public Cunsumer setNumber(String phonNumber) throws BadVerification {
            if (Verification.verifyPhoneNumber(phonNumber)){
                this.phonNumber = phonNumber;
                return this;
            }
            throw new BadVerification("Bad Number");
        }

        public Cunsumer setAdress(String adress) {
            this.adress = adress;
            return this;
        }

        public Cunsumer setEmail(String email) throws BadVerification {
            if (Verification.verifyEmail(email)){
                this.email = email;
                return this;
            }
            throw new BadVerification("Bad Email");
        }

        public Cunsumers build(){
            return new Cunsumers(this);
        }

    }

    @Override
    public String toString() {
        return "Cunsumers{" +
                "accounts=" + accounts +
                ", date=" + date +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number='" + phonNumber + '\'' +
                ", adress='" + adress + '\'' +
                ", custumerNumber=" + custumerNumber +
                '}';
    }
}
