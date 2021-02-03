package entety;

import entety.accounts.Account;
import exceptions.BadVerification;
import loger.BadLog;
import loger.GoodLog;
import service.Verification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * Cunsumers Main class, have in itself class Builder.
 * Builder - help to create.
 * */
public class Consumer implements Serializable{
    private static final long serialVersionUID = 1L;
    private final List<Account> accounts = new ArrayList<>();

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private String password;


    private Consumer(ConsumerBuild bild) {
        this.id = bild.id;
        this.firstName = bild.firstName;
        this.lastName = bild.lastName;
        this.phoneNumber = bild.phoneNumber;
        this.address = bild.address;
        this.email = bild.email;
        this.password = bild.password;
        GoodLog.getInstance().log("Create Consumer.");
    }

    public Account getAccount(long accNo){
        for (Account a: accounts){
            if (a.getBankAccount() == accNo)
                return a;
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

    public String getPhoneNumber() {
        if (phoneNumber != null){
            GoodLog.getInstance().log("Get Phone Number.");
            return phoneNumber;
        }
        BadLog.getInstance().log("Phone Number dont initialisation.");
        return "";
    }

    public String getAddress(){
        if (address != null){
            GoodLog.getInstance().log("Get adress.");
            return address;
        }
        BadLog.getInstance().log("Adress dont initialisation.");
        return "";
    }

    public String getEmail(){
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

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return  "ID: " + id + "</br>" +
                "Consumer: " + firstName + " " + lastName + "</br>" +
                "email: " + email + "</br>" +
                "phone: " + phoneNumber + "</br>" +
                "address: " + address + "</br>" +
                "Accounts" + accounts + "</br></br>" +
                "<hr></hr>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return getId() == consumer.getId() &&
                getEmail().equals(consumer.getEmail()) &&
                getPassword().equals(consumer.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword());
    }

    /**
     * Class  bilder for cunsumer.
     * */
    public static class ConsumerBuild {
        private int id;
        private String phoneNumber;
        private String address;
        private String email;
        private String firstName;
        private String lastName;
        private String password;

        public ConsumerBuild(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;

        }

        public ConsumerBuild setNumber(String phonNumber) throws BadVerification {
            if (Verification.verifyPhoneNumber(phonNumber)){
                this.phoneNumber = phonNumber;
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + "Add phone number.");
                return this;
            }
            BadLog.getInstance().log("Number dont set, bad number.");
            throw new BadVerification("Number dont set, bad number.");
        }

        public ConsumerBuild setAddress(String address) {
            this.address = address;
            GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add adress.");
            return this;
        }

        public ConsumerBuild setEmail(String email) throws BadVerification {
            if (Verification.verifyEmail(email)){
                this.email = email;
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add email.");
                return this;
            }
            BadLog.getInstance().log("Email dont set, bad email.");
            throw new BadVerification("Email dont set, bad email.");
        }

        public ConsumerBuild setPass(String password) throws BadVerification {
            if (Verification.verifyPassword(password)){
                this.password = password;
                GoodLog.getInstance().log(this.firstName + " " + this.lastName + " add password.");
                return this;
            }
            BadLog.getInstance().log("Password dont set, bad password.");
            throw new BadVerification("Password dont set, bad password.");
        }
        public ConsumerBuild setId(int id){
            this.id = id;
            return this;
        }

        public Consumer build(){
            GoodLog.getInstance().log("Return consumer build.");
            return new Consumer(this);
        }
    }
}
