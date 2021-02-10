package controllers;

import dao.services.implementations.ConsumerServiceImp;
import dao.services.implementations.TransactionServiceImpl;
import dao.services.implementations.accounts.AccountServiceImpl;
import dao.services.implementations.accounts.CheckingAccountServiceImpl;
import dao.services.implementations.accounts.SavingAccountServiceImpl;
import dao.services.interfaces.AccountService;
import dao.services.interfaces.TransactionService;
import entety.Consumer;
import entety.accounts.Account;
import entety.accounts.CheckingAccount;
import entety.accounts.SavingAccount;
import entety.accounts.Transaction;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Post;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * The consumer the only one who can send their own money, and obtain cabinet pages.
 * This controller handles requests from the authorized consumer,
 * which can send own money to other accounts, and get own transactions.
 * If you want to add a new function for the consumer you must create a method that takes in parameters HttpServletRequest,
 * and will have annotation @Post or @Get, and if the method should forward should have string returns.
 * @author Ruslan Pipan
 * @version 1.2
 * */

@Controler
public class ControlerConsumer {
    /**
     * In order to send money, the consumer must enter the bank account and the amount of money the consumer who will be sent money.
     * After entered data consumer will forward to the page check data.
     * */
    @Post("sent_money")
    String sentMoney(HttpServletRequest request){
        HttpSession session = request.getSession();
        Account toAccount = (Account) session.getAttribute("toAccount");
        Account thisAccount = (Account) session.getAttribute("thisAccount");
        double suma = Double.parseDouble((String)session.getAttribute("suma"));
        if(AccountServiceImpl.getInstance().transaction(thisAccount,toAccount,suma)){
            session.removeAttribute("toAccount");
            session.removeAttribute("thisAccount");
            session.removeAttribute("suma");
            session.removeAttribute("consumerForwarded");
            return"cabinet.jsp";
        }
        session.setAttribute("err","Bad sent money");
        return "error.jsp";
    }

    /**
     * Before sending money consumer must check the date be entered, such as:
     * bank accounts, consumer initials to whom the transfer will be, the amount of money transfer.
     * If all data is valid then consumer can be sending their own money.
     * */
    @Post("check_date")
    String checkDate(HttpServletRequest request){
        HttpSession session = request.getSession();
        String suma = request.getParameter("suma");
        String thisAccString = request.getParameter("this_bank_acc");
        String nameAccTo = request.getParameter("to_bank_acc");

        Consumer consumer = (Consumer) session.getAttribute("consumer");
        Account thisAccount = consumer.getAccount(Long.parseLong(thisAccString));

        Account toAccount = AccountServiceImpl.getInstance().findAccountByBankAccount(Long.parseLong(nameAccTo));

        if (thisAccount!=null && toAccount!=null && thisAccount.getBalance() >= Double.parseDouble(suma)){
            Consumer consumerForwarded = ConsumerServiceImp.getInstance().findConsumerByBankAcc(toAccount.getBankAccount());
            session.setAttribute("toAccount", toAccount);
            session.setAttribute("thisAccount",thisAccount);
            session.setAttribute("suma",suma);
            session.setAttribute("consumerForwarded",consumerForwarded);
            return "sent_money.jsp";
        }
        session.setAttribute("err", "Dad checkDate");
        return"error.jsp";
    }


    /** Each consumer can obtain their own transactions.*/
    @Post("getTransaction")
    String getTransactions(HttpServletRequest request){
        TransactionService transactionService = TransactionServiceImpl.getInstance();
        String idAccString = request.getParameter("idAcc");
        int idAcc = Integer.parseInt(idAccString);
        List<Transaction> transactions = transactionService.getLastSentTransactionById(idAcc);
        HttpSession session = request.getSession();
        session.setAttribute("transactions", transactions);
        return "cabinet.jsp";
    }

    /** Each consumer can obtain their own Checking accounts.*/
    @Post("getCheckingAcc")
    String getCheckingAcc(HttpServletRequest request){
        AccountService<CheckingAccount> service = CheckingAccountServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Consumer consumer = (Consumer) session.getAttribute("consumer");
        List<CheckingAccount> checkingAccounts = service.findAccountsByConsumer(consumer);
        session.setAttribute("Accounts",checkingAccounts);
        return "typeAcc.jsp";
    }

    /** Each consumer can obtain their own Saving accounts.*/
    @Post("getSavingAcc")
    String getSavingAcc(HttpServletRequest request){
        AccountService<SavingAccount> service = SavingAccountServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Consumer consumer = (Consumer) session.getAttribute("consumer");
        List<SavingAccount> savingAccounts = service.findAccountsByConsumer(consumer);
        session.setAttribute("Accounts",savingAccounts);
        return "typeAcc.jsp";
    }
}
