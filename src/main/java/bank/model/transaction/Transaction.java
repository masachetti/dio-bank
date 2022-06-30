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

//    private Account account;

    private Double value;

    abstract public boolean performTransaction();

//    public Transaction(Account account, Double value) {
//        this.account = account;
//        this.value = value;
//    }

    public Transaction(Double value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }

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
//        return id.equals(that.id) && account.equals(that.account) && value.equals(that.value);
        return id.equals(that.id) && value.equals(that.value);
    }

    @Override
//    public int hashCode() {
//        return Objects.hash(id, account, value);
//    }
    public int hashCode() {
        return Objects.hash(id, value);
    }

    protected String getDescriptionString() {
//        return "id=" + id + ", account=" + account + ", value=" + value;
        return "id=" + id + ", value=" + value;
    }

    @Override
    public String toString() {
        return "Transaction{" + getDescriptionString() + '}';
    }
}
