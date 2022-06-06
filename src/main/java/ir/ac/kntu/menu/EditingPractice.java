package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class EditingPractice implements Menu{

    private static  EditingPractice instance = new EditingPractice();

    public static EditingPractice getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {

        System.out.println();
        OPTIONS.add("Add question");
        OPTIONS.add("See questions");
        OPTIONS.add("Practice settings");
        OPTIONS.add("See last answers");
        OPTIONS.add("Marks");
        OPTIONS.add("Back");
        printInteractMenu();

    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input){
            case "See_last_answers" :
                return seeLastAnswers();
            case "Practice_settings" :
                Main.getCustomer().setLastMenu(EditingPractice.getInstance());
                return PracticeSetting.getInstance();
            case "Marks" :
                printMarks();
            case "Back":
                return ManegingClass.getInstance();
            case "Add_question":
                Main.getCustomer().setLastMenu(EditingPractice.getInstance());
                return AddQuestionMenu.getInstance();
            case "See_questions":
                printQuestions();
                Question targetQuestion = chooseQuestion();
                if (targetQuestion == null){
                    return EditingPractice.getInstance();
                } else {
                    return questionEditing(targetQuestion);
                }
            default:
                System.out.println("Wrong input");
                return EditingPractice.getInstance();
        }
    }

    private Menu seeLastAnswers() {
        printQuestions();
        Question targetQuestion = chooseQuestion();
        if (targetQuestion == null) {
            return EditingPractice.getInstance();
        }
        System.out.println("Choose which user");
        for (User user : Main.getCustomer().getManagingClass().getRegisteredUsers()) {
            System.out.println(user.getName());
        }
        User targetUser = UsersStorage.findByName(ScannerWrapper.getInstance().next());
        ArrayList<Question> targetArrayList = null;
        targetArrayList = Main.getCustomer().getCurrentPractice().getWorkMap().get(targetUser);
        for (Question question : targetArrayList) {
            if (question.equals(targetQuestion)) {
                System.out.println(question.getWorkSpace().getLastAnswer());
                System.out.println("Enter score : ");
                question.getWorkSpace().setScore(ScannerWrapper.getInstance().nextDouble());
                return EditingPractice.getInstance();
            }
        }
        System.out.println("Wrong input!");
        return EditingPractice.getInstance();
    }

    private Menu questionEditing(Question targetQuestion){
        System.out.println();
        System.out.println(targetQuestion);
        printQuestionEditingMenu();
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Back":
                return EditingPractice.getInstance();
            case "Edit_description":
                editDescription(targetQuestion, input);
                return EditingPractice.getInstance();
            case "Add_answer":
                addAnswer(targetQuestion, input);
                return EditingPractice.getInstance();
            case "Delete":
                delete(targetQuestion);
                return PracticeSetting.getInstance();
            case "Set_multiplier" :
                setMultiplier(targetQuestion);
                return PracticeSetting.getInstance();
            default:
                System.out.println("Wrong input");
                return EditingPractice.getInstance();
        }
    }

    private void setMultiplier(Question targetQuestion){
        System.out.println("Enter new multiplier for question");
        Double newMultiplier = ScannerWrapper.getInstance().nextDouble();
        targetQuestion.setMultiplier(newMultiplier);
    }

    private void delete(Question targetQuestion){
        ArrayList<Question> result = Main.getCustomer().getCurrentPractice().getQuestions();
        result.remove(targetQuestion);
        Main.getCustomer().getCurrentPractice().setQuestions(result);
        Main.getCustomer().getCurrentPractice().makeWorkMap(Main.getCustomer().getManagingClass());
    }

    private void addAnswer(Question targetQuestion,String input){
        System.out.println("Enter answer");
        input = ScannerWrapper.getInstance().next();
        targetQuestion.setAnswer(input);
        Main.getCustomer().getCurrentPractice().makeWorkMap(Main.getCustomer().getManagingClass());
    }

    private void editDescription(Question targetQuestion,String input){
        System.out.println("Enter new description");
        input = ScannerWrapper.getInstance().next();
        targetQuestion.setDescription(input);
        System.out.println("updated!");
        Main.getCustomer().getCurrentPractice().makeWorkMap(Main.getCustomer().getManagingClass());
    }

    public static void printMarks(){
        Main.getCustomer().getCurrentPractice().makeMarks();
        for (User user : Main.getCustomer().getCurrentPractice().getUserMarks().keySet()){
            System.out.println(user.getName() + " | " + Main.getCustomer().getCurrentPractice().getUserMarks().get(user) + " | " +
                    Main.getCustomer().getCurrentPractice().getSumMarks().get(user));
        }
    }

    private void printQuestionEditingMenu(){
        System.out.println("=================================================");
        System.out.println();
        System.out.println("Edit description");
        System.out.println("Add answer");
        System.out.println("Set multiplier");
        System.out.println("Delete");
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
    }

    private void printQuestions(){
        if (Main.getCustomer().getCurrentPractice().getQuestions().size() == 0){
            System.out.println("This practice does not have any question");
        }
        for (Question question : Main.getCustomer().getCurrentPractice().getQuestions()){
            question.smallPrint("Teacher");
        }
    }

    private Question chooseQuestion(){
        System.out.println("Choose question : ");
        String input = ScannerWrapper.getInstance().next();
        for (Question question : Main.getCustomer().getCurrentPractice().getQuestions()){
            if (question.getName().equals(input)){
                return question;
            }
        }
        System.out.println("Wrong input!");
        return null;
    }
}
