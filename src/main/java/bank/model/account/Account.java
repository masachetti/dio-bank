package bank.model.account;

import bank.model.client.Client;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
abstract public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "agency")
    private Integer agency;

    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Account() {
    }

    public Account(Integer agency, Integer accountNumber, Client client) {
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.client = client;
        this.balance = 0d;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id.equals(account.id) && agency.equals(account.agency) && accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agency, accountNumber);
    }

    protected String getDescriptionString() {
        return "id=" + id +
                ", agency=" + agency +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance;
    }

    @Override
    public String toString() {
        return "Account{" + getDescriptionString() + '}';
    }
}
