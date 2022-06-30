package bank.model.transaction;

import bank.model.account.Account;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "tb_transactions")
@DiscriminatorColumn(name = "transaction_type")
abstract public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Account account;

    private Double value;


    public Transaction() {
    }

    public Transaction(Account account, Double value) {
        this.account = account;
        this.value = value;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected String getDescriptionString() {
        return "id=" + id + ", accountNumber=" + account.getAccountNumber() + ", value=" + value;
    }

    @Override
    public String toString() {
        return "Transaction{" + getDescriptionString() + '}';
    }
}
