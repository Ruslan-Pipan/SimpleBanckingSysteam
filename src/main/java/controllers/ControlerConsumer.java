package controllers;

import dao.services.interfaces.AccountService;
import dao.services.interfaces.TransactionService;
import entety.Consumer;
import entety.accounts.Account;
import entety.accounts.CheckingAccount;
import entety.accounts.SavingAccount;
import entety.accounts.Transaction;
import dao.ServiceConstants;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static dao.ServiceConstants.ACCOUNT_SERVICE;
import static dao.ServiceConstants.CONSUMER_SERVICE;

@Controler("")
public class ControlerConsumer {

    @Post("/check_date")
    String checkDate(HttpServletRequest request){
        HttpSession session = request.getSession();
        String suma = request.getParameter("suma");
        String thisAccString = request.getParameter("this_bank_acc");
        String nameAccTo = request.getParameter("to_bank_acc");

        Consumer consumer = (Consumer) session.getAttribute("consumer");
        Account thisAccount = consumer.getAccount(Long.parseLong(thisAccString));

        Account toAccount = ACCOUNT_SERVICE.findAccountByBankAccount(Long.parseLong(nameAccTo));

        if (thisAccount!=null && toAccount!=null && thisAccount.getBalance() >= Double.parseDouble(suma)){
            Consumer consumerForwarded = CONSUMER_SERVICE.findConsumerByBankAcc(toAccount.getBankAccount());
            session.setAttribute("toAccount", toAccount);
            session.setAttribute("thisAccount",thisAccount);
            session.setAttribute("suma",suma);
            session.setAttribute("consumerForwarded",consumerForwarded);
            return "sent_money.jsp";
        }
        session.setAttribute("err", "Dad checkDate");
        return"error.jsp";
    }

    @Post("/sent_money")
    String sentMoney(HttpServletRequest request){
        HttpSession session = request.getSession();
        Account toAccount = (Account) session.getAttribute("toAccount");
        Account thisAccount = (Account) session.getAttribute("thisAccount");
        double suma = Double.parseDouble((String)session.getAttribute("suma"));
        if(ACCOUNT_SERVICE.transaction(thisAccount,toAccount,suma)){
            session.removeAttribute("toAccount");
            session.removeAttribute("thisAccount");
            session.removeAttribute("suma");
            session.removeAttribute("consumerForwarded");
            return"cabinet.jsp";
        }
        session.setAttribute("err","Bad sent money");
        return "error.jsp";
    }

    @Post("/getTransaction")
    String getTransactions(HttpServletRequest request){
        TransactionService transactionService = ServiceConstants.TRANSACTION_SERVICE;
        String idAccString = request.getParameter("idAcc");
        int idAcc = Integer.parseInt(idAccString);
        List<Transaction> transactions = transactionService.getLastSentTransactionById(idAcc);
        HttpSession session = request.getSession();
        session.setAttribute("transactions", transactions);
        return "cabinet.jsp";
    }

    @Post("/getCheckingAcc")
    String getCheckingAcc(HttpServletRequest request){
        AccountService<CheckingAccount> service = ServiceConstants.CHECKING_ACCOUNT_SERVICE;
        HttpSession session = request.getSession();
        Consumer consumer = (Consumer) session.getAttribute("consumer");
        List<CheckingAccount> checkingAccounts = service.findAccountsByConsumer(consumer);
        session.setAttribute("Accounts",checkingAccounts);
        return "typeAcc.jsp";
    }

    @Post("/getSavingAcc")
    String getSavingAcc(HttpServletRequest request){
        AccountService<SavingAccount> service = ServiceConstants.SAVING_ACCOUNT_SERVICE;
        HttpSession session = request.getSession();
        Consumer consumer = (Consumer) session.getAttribute("consumer");
        List<SavingAccount> savingAccounts = service.findAccountsByConsumer(consumer);
        session.setAttribute("Accounts",savingAccounts);
        return "typeAcc.jsp";
    }
}
