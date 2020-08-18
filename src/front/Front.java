package front;

import db.consumer.insert.Insert;
import db.consumer.select.SekectAll;
import db.consumer.select.Select;
import db.consumer.select.SelectByID;
import db.consumer.select.SelectByName;
import mod1.com.mybanck.domain.Consumer;
import mod1.com.mybanck.domain.bankException.BadVerification;

import java.util.Scanner;

public class Front {

    private static boolean flag = true;

    public static void main(String[] args) throws BadVerification {

        Scanner scanner = new Scanner(System.in);
        String value;
        printMenu();

        while (flag){
            value = scanner.nextLine();

            switch (value){
                case "0":
                    flag = false;
                    break;
                case "1":
                    Select select = new SekectAll();
                    select.select();
                    break;
                case "2":
                    Consumer con = createConsumer();
                    Insert insert = new Insert();
                    insert.insert(con);
                    break;
                case "3":
                    enteringId();
                    break;
                case "4":
                    eteringAName();
                    break;
                default:
                    System.out.println("Bad value");
                    break;
            }
        }
    }


    private static void printMenu(){
        System.out.println(
                "0: for EXITE.\n" +
                "1: Select Consumer.\n" +
                "2: Insert Consumer.\n" +
                "3: Select Consumer by id.\n" +
                "4: Select Consumer by First name, Last name."
        );
    }

    private static Consumer createConsumer() throws BadVerification {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPrint Name: ");
        String first_name = scanner.nextLine();

        System.out.print("Print Last Name: ");
        String last_name = scanner.nextLine();

        System.out.print("Print Email: ");
        String email = scanner.nextLine();

        System.out.print("Print Password: ");
        String password = scanner.nextLine();

        System.out.print("Print Phone number: ");
        String phone_number = scanner.nextLine();

        System.out.print("Print Adress: ");
        String adress = scanner.nextLine();

        return new Consumer.CunsumerBild(first_name,last_name).setNumber(phone_number).setEmail(email).setAdress(adress).setPass(password).build();
    }

    private static void eteringAName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        Select select = new SelectByName(name,lastName);
        select.select();
    }

    private static void enteringId(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        Select select = new SelectByID(id);
        select.select();
    }

}
