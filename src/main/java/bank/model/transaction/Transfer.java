package bank.model.transaction;

import bank.model.account.Account;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TRANSFER")
public class Transfer extends Transaction{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Account receiverAccount;

    public Transfer(Account account, Double value, Account receiverAccount) {
        super(account, value);
        this.receiverAccount = receiverAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }
}
