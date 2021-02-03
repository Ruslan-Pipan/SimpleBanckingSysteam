package controllers;

import entety.Consumer;
import exceptions.BadVerification;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Get;
import service.dispatcher.annotations.Post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static dao.ServiceConstants.CONSUMER_SERVICE;

@Controler("/")
public class ControlerPerson {
    @Get("login")
    String login(HttpServletRequest request) throws BadVerification {
        Consumer consumer = CONSUMER_SERVICE.findConsumerByEmail(request.getParameter("email"));
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
            consumer = new Consumer.ConsumerBuild(firstName, secondName).setPass(pass).setEmail(email).setNumber(phoneNumber).build();
        } catch (BadVerification badVerification) {
            session.setAttribute("err", "Bad verification");
            badVerification.printStackTrace();
            return "error.jsp";
        }
        CONSUMER_SERVICE.addConsumer(consumer);
        session.setAttribute("consumer",consumer);
        return "cabinet.jsp";
    }
}
