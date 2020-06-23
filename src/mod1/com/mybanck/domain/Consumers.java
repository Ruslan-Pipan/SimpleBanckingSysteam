package mod1.com.mybanck.domain;

import loger.BadLog;
import loger.GoodLog;
import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.bankException.BadVerification;
import mod1.com.mybanck.domain.bankException.DontInitialisation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Cunsumers Main class, have in itself class Builder.
 * Builder - help to create.
 * */
public class Consumers implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Account> accounts = new ArrayList<>();
    private LocalDate date;

    private String firstName;
    private String lastName;
    private String phonNumber;
    private String adress;
    private String email;

    private int custumerNumber;
    private static int custumerNumberBase = 1000;

    private Consumers(Cunsumer bild) {
        this.firstName = bild.firstName;
        this.lastName = bild.lastName;
        this.phonNumber = bild.phonNumber;
        this.adress = bild.adress;
        this.email = bild.email;
        this.custumerNumber = custumerNumberBase+1;
        GoodLog.getInstance().log("Create Consumer.");
    }

    public Account getAccount(int accNo){
        if (accNo < accounts.size()){
            GoodLog.getInstance().log("Get account Consumers.");
            return accounts.get(accNo);
        }
        return null;
    }

    public void addAccount(Account acc){
        GoodLog.getInstance().log("Add Account in Consumer.");
        accounts.add(acc);
    }

    public String getFirstName() {
        GoodLog.getInstance().log("Get First Name.");
        return firstName;
    }

    public String getLastName() {
        GoodLog.getInstance().log("Get Last Name.");
        return lastName;
    }

    public String getPhoneNumber() throws DontInitialisation {
        if (phonNumber!= null){
            GoodLog.getInstance().log("Get Phone Number.");
            return phonNumber;
        }
        BadLog.getInstance().log("Phone Number dont initialisation.");
        throw new DontInitialisation("Phone Number dont initialisation.");
    }

    public String getAdress() throws DontInitialisation {
        if (adress!= null){
            GoodLog.getInstance().log("Get adress.");
            return adress;
        }
        BadLog.getInstance().log("Adress dont initialisation.");
        throw new DontInitialisation("Adress dont initialisation.");
    }

    public String getEmail() throws DontInitialisation {
        if (email!= null){
            GoodLog.getInstance().log("Get email.");
            return email;
        }
        BadLog.getInstance().log("Email dont initialisation.");
        throw new DontInitialisation("Email dont initialisation.");
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
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + "Add phone number.");
                return this;
            }
            BadLog.getInstance().log("Number dont set, bad number.");
            throw new BadVerification("Number dont set, bad number.");
        }

        public Cunsumer setAdress(String adress) {
            this.adress = adress;
            GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add adress.");
            return this;
        }

        public Cunsumer setEmail(String email) throws BadVerification {
            if (Verification.verifyEmail(email)){
                this.email = email;
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add email.");
                return this;
            }
            BadLog.getInstance().log("Email dont set, bad email.");
            throw new BadVerification("Email dont set, bad email.");
        }

        public Consumers build(){
            GoodLog.getInstance().log("Return consumer build.");
            return new Consumers(this);
        }

    }

    @Override
    public String toString() {
        GoodLog.getInstance().log("Consumer toString.");
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
