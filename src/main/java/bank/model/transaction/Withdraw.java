package bank.model.transaction;

import bank.model.account.Account;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WITHDRAW")
public class Withdraw extends Transaction{
//    public Withdraw(Account account, Double value) {
//        super(account, value);
//    }

    public Withdraw(Double value) {
        super(value);
    }

    @Override
    public boolean performTransaction() {
        return false;
    }
}
