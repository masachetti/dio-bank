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


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Bank-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Bank bank = new Bank(entityManager);
        bank.addNewClient(ip1);
        bank.addNewClient(ip2);
        bank.addNewClient(le1);
        bank.addNewClient(le2);
        bank.assignAccountToClient(ip1, ca);
        bank.assignAccountToClient(ip1, sa);
        bank.performDeposit(ca, 200);
        bank.performDeposit(sa, 300);
        bank.performWithdraw(ca, 105.5);
        bank.performTransfer(sa, 25, ca);

        bank.showAllClients();
        bank.showAllAccounts();
        bank.showAllTransactions();

        Client retClient = bank.getClientByCNPJ("75.027.815/0001-63");
        Client retClient2 = bank.getClientByCPF("123.245.111-30");
        System.out.println("Getting client by CNPJ: " + retClient);
        System.out.println("Getting client by CPF: " + retClient2);

        bank.showAccountTransactions(ca);

        entityManager.close();
        entityManagerFactory.close();
    }
}
