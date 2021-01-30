package controllers;

import dao.repositories.implementations.accounts.AccountRepositoryImpl;
import dao.repositories.implementations.ConsumerRepositoryImpl;
import dao.repositories.interfaces.AccountRepository;
import dao.repositories.interfaces.ConsumerRepository;
import dao.services.implementations.accounts.AccountServiceImpl;
import dao.services.implementations.ConsumerServiceImp;
import dao.services.interfaces.AccountService;
import dao.services.interfaces.ConsumerService;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Get;
import service.dispatcher.annotations.Post;
import entety.Consumer;
import entety.accounts.Account;
import exceptions.BadVerification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controler("/")
public class ControlerMainOperations {
    private final ConsumerRepository consumerRepository = new ConsumerRepositoryImpl();
    private final ConsumerService consumerService = new ConsumerServiceImp(consumerRepository);
    private final AccountRepository<Account> accountRepository = new AccountRepositoryImpl();
    private final AccountService<Account> accountService = new AccountServiceImpl(accountRepository);

    @Get("login")
    String login(HttpServletRequest request) throws BadVerification {
        Consumer consumer = consumerService.findConsumerByEmail(request.getParameter("email"));
        String passReq = request.getParameter("password");
        HttpSession session = request.getSession();
        if (consumer != null && consumer.getPassword().equals(passReq)){
            session.setAttribute("consumer",consumer);
            return "cabinet.jsp";
        }
        session.setAttribute("err","Bad verification");
        return "error.jsp";
    }

    @Post("authorization")
    String authorization(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String pass = request.getParameter("firstPass");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        Consumer consumer = null;
        try {
            consumer = new Consumer.CunsumerBild(firstName, secondName).setPass(pass).setEmail(email).setNumber(phoneNumber).build();
        } catch (BadVerification badVerification) {
            session.setAttribute("err", "Bad verification");
            badVerification.printStackTrace();
            return "error.jsp";
        }
        consumerService.addConsumer(consumer);
        session.setAttribute("consumer",consumer);
        return "cabinet.jsp";
    }

    @Post("check_date")
    String checkDate(HttpServletRequest request){
        HttpSession session = request.getSession();
        String suma = request.getParameter("suma");
        String thisAccString = request.getParameter("this_bank_acc");
        String nameAccTo = request.getParameter("to_bank_acc");

        Account toAccount = accountService.findAccountByBankAccount(Long.parseLong(nameAccTo));
        Account thisAccount = accountService.findAccountByBankAccount(Long.parseLong(thisAccString));

        if (thisAccount!=null && toAccount!=null && thisAccount.getBalance() >= Double.parseDouble(suma)){
            Consumer consumerForwarded = consumerService.findConsumerByBankAcc(toAccount.getBankAccount());
            session.setAttribute("toAccount", toAccount);
            session.setAttribute("thisAccount",thisAccount);
            session.setAttribute("suma",suma);
            session.setAttribute("consumerForwarded",consumerForwarded);
            return "sent_money.jsp";
        }
        session.setAttribute("err", "Dad checkDate");
        return"error.jsp";
    }

    @Post("sent_money")
    String sentMoney(HttpServletRequest request){
        HttpSession session = request.getSession();
        Account toAccount = (Account) session.getAttribute("toAccount");
        Account thisAccount = (Account) session.getAttribute("thisAccount");
        double suma = Double.parseDouble((String)session.getAttribute("suma"));
        if(accountService.transaction(thisAccount,toAccount,suma)){
            session.removeAttribute("toAccount");
            session.removeAttribute("thisAccount");
            session.removeAttribute("suma");
            session.removeAttribute("consumerForwarded");
            return"cabinet.jsp";
        }
        session.setAttribute("err","Bad sent money");
        return "error.jsp";
    }

}
