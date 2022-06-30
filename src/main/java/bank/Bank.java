package bank;

import bank.model.account.Account;
import bank.model.account.CheckingAccount;
import bank.model.account.SavingAccount;
import bank.model.client.Client;
import bank.model.client.Corporation;
import bank.model.client.Individual;
import bank.model.transaction.Deposit;
import bank.model.transaction.Transaction;
import bank.model.transaction.Transfer;
import bank.model.transaction.Withdraw;
import utils.TablePrinter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bank {
    @PersistenceContext(unitName = "Bank-PU")
    private final EntityManager entityManager;

    public Bank(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void showAllClients() {
        String jpqlQuery = "select c from Client c";
        try {
            List<Client> clientList = entityManager.createQuery(jpqlQuery, Client.class).getResultList();
            List<String> header = Arrays.asList("Client Id", "Name", "CPF", "CNPJ");
            List<List<String>> rows = new ArrayList<>();
            clientList.forEach(client -> {
                String cpf = "Null";
                String cnpj = "Null";
                if (client instanceof Corporation) {
                    cnpj = ((Corporation) client).getCnpj();
                } else if (client instanceof Individual) {
                    cpf = ((Individual) client).getCpf();
                }
                List<String> row = Arrays.asList(client.getId().toString(), client.getName(), cpf, cnpj);
                rows.add(row);
            });
            TablePrinter.printTable(header, rows, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllAccounts() {
        String jpqlQuery = "select a from Account a";
        try {
            List<Account> accountList = entityManager.createQuery(jpqlQuery, Account.class).getResultList();
            List<String> header = Arrays.asList("Id", "Agency", "Account Number", "Account Type", "Balance", "Owner");
            List<List<String>> rows = new ArrayList<>();

            accountList.forEach(account -> {
                String accountType = "Null";
                if (account instanceof CheckingAccount) {
                    accountType = "Checking";
                } else if (account instanceof SavingAccount) {
                    accountType = "Saving";
                }
                List<String> row = Arrays.asList(
                        account.getId().toString(),
                        account.getAgency().toString(),
                        account.getAccountNumber().toString(),
                        accountType,
                        String.format("R$%.2f",account.getBalance()),
                        account.getClient().getName());
                rows.add(row);
            });
            TablePrinter.printTable(header, rows, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllTransactions() {
        String jpqlQuery = "select t from Transaction t";
        try {
            List<Transaction> transactionList = entityManager.createQuery(jpqlQuery, Transaction.class).getResultList();
            List<String> header = Arrays.asList("Id", "Operation", "Account N.", "Value", "Rec. Account N.");
            List<List<String>> rows = new ArrayList<>();

            transactionList.forEach(transaction -> {
                String transactionType = "Null";
                String recAccountNumberString = "Null";
                if (transaction instanceof Deposit) {
                    transactionType = "Deposit";
                } else if (transaction instanceof Withdraw) {
                    transactionType = "Withdraw";
                } else if (transaction instanceof Transfer) {
                    transactionType = "Transfer";
                    recAccountNumberString = ((Transfer) transaction).getReceiverAccount().getAccountNumber().toString();
                }
                List<String> row = Arrays.asList(
                        transaction.getId().toString(),
                        transactionType,
                        transaction.getAccount().getAccountNumber().toString(),
                        String.format("R$%.2f",transaction.getValue()),
                        recAccountNumberString);
                rows.add(row);
            });
            TablePrinter.printTable(header, rows, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    public void assignAccountToClient(Client client, Account account) {
        client.getAccounts().add(account);
        account.setClient(client);
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }

    public void performDeposit(Account account, double value) {
        Deposit deposit = new Deposit(account, value);
        double actualBalance = account.getBalance();
        account.setBalance(actualBalance + value);
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.persist(deposit);
        entityManager.getTransaction().commit();
    }

    public void performWithdraw(Account account, double value) {
        double actualBalance = account.getBalance();
        double newBalance = actualBalance - value;
        if (newBalance < 0) {
            return;
        }
        Withdraw withdraw = new Withdraw(account, value);
        account.setBalance(newBalance);
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.persist(withdraw);
        entityManager.getTransaction().commit();
    }

    public void performTransfer(Account account, double value, Account receiverAccount) {
        double actualBalance = account.getBalance();
        double newBalance = actualBalance - value;
        if (newBalance < 0) {
            return;
        }
        Transfer transfer = new Transfer(account, value, receiverAccount);
        account.setBalance(newBalance);
        double receiverBalance = receiverAccount.getBalance();
        double newReceiverBalance = receiverBalance + value;
        receiverAccount.setBalance(newReceiverBalance);
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.persist(receiverAccount);
        entityManager.persist(transfer);
        entityManager.getTransaction().commit();
    }
}
