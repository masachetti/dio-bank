import bank.Bank;
import bank.model.account.Account;
import bank.model.account.CheckingAccount;
import bank.model.account.SavingAccount;
import bank.model.client.Client;
import bank.model.client.Corporation;
import bank.model.client.Individual;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Individual ip1 = new Individual("Joao", "123.245.111-30");
        Individual ip2 = new Individual("Maria", "123.222.666-00");
        Corporation le1 = new Corporation("Satt", "65.889.594/0001-89");
        Corporation le2 = new Corporation("Mercadinho Sao Joao", "75.027.815/0001-63");
        CheckingAccount ca = new CheckingAccount(1001, 101, le1);
        SavingAccount sa = new SavingAccount(1001, 102, le1);

//        le1.setAccounts(new HashSet<Account>(){{
//            add(ca);
//            add(sa);
//        }});


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Bank-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Bank bank = new Bank(entityManager);
        bank.addNewClient(ip1);
        bank.addNewClient(ip2);
        bank.addNewClient(le1);
        bank.addNewClient(le2);
        bank.assignAccountToClient(ip1, ca);
        bank.assignAccountToClient(ip1, sa);

        bank.showAllClients();
        bank.showAllAccounts();

        entityManager.close();
        entityManagerFactory.close();
    }
}
