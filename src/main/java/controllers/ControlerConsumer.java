package controllers;

import dao.repositories.implementations.ConsumerRepositoryImpl;
import dao.repositories.interfaces.ConsumerRepository;
import service.dispatcher.annotations.Controler;
import service.dispatcher.annotations.Get;

@Controler("/consumer")
public class ControlerConsumer {

    @Get("/id")
    public String getConsumerById(int id){
        ConsumerRepository repository = new ConsumerRepositoryImpl();
        System.out.println(repository.findConsumerById(id));
        return "index.jsp";
    }
}
