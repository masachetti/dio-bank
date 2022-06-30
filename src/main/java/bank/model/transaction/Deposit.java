package bank.model.transaction;

import bank.model.account.Account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("DEPOSIT")
public class Deposit extends Transaction {
    public Deposit() {
    }

    public Deposit(Account account, Double value) {
        super(account, value);
    }

}
