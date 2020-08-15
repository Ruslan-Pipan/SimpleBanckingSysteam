package mod1.com.mybanck.domain;

import loger.BadLog;
import loger.GoodLog;
import mod1.com.mybanck.domain.accounts.Account;
import mod1.com.mybanck.domain.bankException.BadVerification;
import mod1.com.mybanck.domain.bankException.DontInitialisation;
import sun.security.util.Password;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Cunsumers Main class, have in itself class Builder.
 * Builder - help to create.
 * */
public class Consumer implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Account> accounts = new ArrayList<>();
    private LocalDate date;

    private String firstName;
    private String lastName;
    private String phonNumber;
    private String adress;
    private String email;
    private String password;

    private int custumerNumber;
    private static int custumerNumberBase = 1000;

    private Consumer(CunsumerBild bild) {
        this.firstName = bild.firstName;
        this.lastName = bild.lastName;
        this.phonNumber = bild.phonNumber;
        this.adress = bild.adress;
        this.email = bild.email;
        this.password = bild.password;
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
        return "";
    }

    public String getAdress() throws DontInitialisation {
        if (adress!= null){
            GoodLog.getInstance().log("Get adress.");
            return adress;
        }
        BadLog.getInstance().log("Adress dont initialisation.");
        return "";
    }

    public String getEmail() throws DontInitialisation {
        if (email!= null){
            GoodLog.getInstance().log("Get email.");
            return email;
        }
        BadLog.getInstance().log("Email dont initialisation.");
        return "";
    }

    public String getPassword() {
        if (password!= null){
            GoodLog.getInstance().log("Get email.");
            return password;
        }
        BadLog.getInstance().log("Email dont initialisation.");
        return "";
    }

    /**
     * Class  bilder for cunsumer.
     * */
    public static class CunsumerBild {
        private String phonNumber;
        private String adress;
        private String email;
        private String firstName;
        private String lastName;
        private String password;

        public CunsumerBild(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;

        }

        public CunsumerBild setNumber(String phonNumber) throws BadVerification {
            if (Verification.verifyPhoneNumber(phonNumber)){
                this.phonNumber = phonNumber;
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + "Add phone number.");
                return this;
            }
            BadLog.getInstance().log("Number dont set, bad number.");
            throw new BadVerification("Number dont set, bad number.");
        }

        public CunsumerBild setAdress(String adress) {
            this.adress = adress;
            GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add adress.");
            return this;
        }

        public CunsumerBild setEmail(String email) throws BadVerification {
            if (Verification.verifyEmail(email)){
                this.email = email;
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add email.");
                return this;
            }
            BadLog.getInstance().log("Email dont set, bad email.");
            throw new BadVerification("Email dont set, bad email.");
        }

        public CunsumerBild setPass(String password) throws BadVerification {
            if (Verification.verifyPassword(password)){
                this.password = password;
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add password.");
                return this;
            }
            BadLog.getInstance().log("Password dont set, bad password.");
            throw new BadVerification("Password dont set, bad password.");
        }

        public Consumer build(){
            GoodLog.getInstance().log("Return consumer build.");
            return new Consumer(this);
        }

    }

    @Override
    public String toString() {
        return "Consumer{" +
                "accounts=" + accounts +
                ", date=" + date +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phonNumber='" + phonNumber + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", custumerNumber=" + custumerNumber +
                '}';
    }
}
