package ir.ac.kntu;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.StartingMenu;
import ir.ac.kntu.recourses.*;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.storage.PrePrintedStrings;
import ir.ac.kntu.storage.QuestionBank;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.Date;
import ir.ac.kntu.recourses.userTypes.Admin;
import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.recourses.userTypes.Guest;

public class Main {

    private static User loggedInUser;

    public static void main(String[] args) {

        start();

    }

    public static void start(){

        //Base Info
        addStartingInfo();

        Menu menu = StartingMenu.getInstance();

        while (true){

            menu.printMenu();

            menu = menu.handleMenu();

            System.out.println(PrePrintedStrings.END_MENU);

        }

    }

    public static void setLoggedInUser(User setLoggedInUser) {
        loggedInUser = setLoggedInUser;
    }

    public static Customer getCustomer(){
        return (Customer) loggedInUser;
    }

    public static Admin getAdmin(){
        return (Admin) loggedInUser;
    }

    public static Guest getGuest(){
        return (Guest) loggedInUser;
    }

    public static void addStartingInfo(){
        User user1 = new Admin("amirali","amirali","amirali.ghaedi","123","0025319064","09303811221");
        User user2 = new Customer("ahmad","ahmad","ahmad.rezai","123","0025319064","09303811221");
        User user3 = new Customer("reza","reza","reza.sediqi","123","0025319064","09303811221");
        User user4 = new Customer("sara","sara","sara.nasiri","123","0025319064","09303811221");

        UsersStorage.addUser(user1);
        UsersStorage.addUser(user2);
        UsersStorage.addUser(user3);
        UsersStorage.addUser(user4);

        Class class1 = new Class("class1","kntu","amirali",1400);
        Class class2 = new Class("class2","kntu","amirali",1404);

        ClassesStorage.addClass(class1);
        ClassesStorage.addClass(class2);

        Question question1 = new Question("soal1","soal_aval", QuestionDifficulty.HARD, QuestionType.TEST,"gozine1");
        Question question2 = new Question("soal2","soal_dovom", QuestionDifficulty.EASY, QuestionType.SHORT_ANSWER,"gozine2");
        Question question3 = new Question("soal3","soal_sevom", QuestionDifficulty.INSANE, QuestionType.FILL_THE_BLANK,"gozine3");

        QuestionBank.addQuestionToBank(question1);
        QuestionBank.addQuestionToBank(question2);
        QuestionBank.addQuestionToBank(question3);

        Date date1 = new Date(1401,2,5);
        Date date2 = new Date(1401,3,5);

        Practice practice1 = new Practice("practice1","newPractice1",date1,date2,0.5,2);
        Practice practice2 = new Practice("practice2","newPractice2",date1,date2,0.5,2);

        practice1.setStatus(PracticeStatus.ACCESSIBLE);

        practice1.addNewQuestion(question1);
        practice1.addNewQuestion(question2);

        class1.makeNewPractice(practice1);

        class1.register(user2);
        class1.register(user3);

    }
}