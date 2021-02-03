package entety.accounts;

import java.util.Objects;

public class Transaction {
    private int id;
    private Account accountTo;
    private Account accountFrom;
    private double remittance;

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public double getRemittance() {
        return remittance;
    }

    public void setRemittance(double remittance) {
        this.remittance = remittance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction: " + id + "</br>" +
                "Account from: " + accountFrom + "</br>" +
                "Account to: " + accountTo + "</br>" +
                "Remittance: " + remittance + "</br>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
