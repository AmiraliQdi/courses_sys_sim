package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class TournamentMainPracticeEdit implements Menu{

    private static TournamentMainPracticeEdit instance = new TournamentMainPracticeEdit();

    public static TournamentMainPracticeEdit getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {

        System.out.println();
        OPTIONS.add("Add question");
        OPTIONS.add("See questions");
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
            case "Marks" :
                printMarks();
            case "Back":
                return ManagingTournament.getInstance();
            case "Add_question":
                Main.getAdmin().setLastMenu(TournamentMainPracticeEdit.getInstance());
                return AddQuestionAdminMenu.getInstance();
            case "See_questions":
                printQuestions();
                Question targetQuestion = chooseQuestion();
                if (targetQuestion == null){
                    return TournamentMainPracticeEdit.getInstance();
                } else {
                    return questionEditing(targetQuestion);
                }
            default:
                System.out.println("Wrong input");
                return TournamentMainPracticeEdit.getInstance();
        }
    }

    private Menu seeLastAnswers() {
        printQuestions();
        Question targetQuestion = chooseQuestion();
        if (targetQuestion == null) {
            return TournamentMainPracticeEdit.getInstance();
        }
        System.out.println("Choose which user");
        for (User user : Main.getAdmin().getManagingTournament().getRegisteredUsers()) {
            System.out.println(user.getName());
        }
        User targetUser = UsersStorage.findByName(ScannerWrapper.getInstance().next());
        ArrayList<Question> targetArrayList = null;
        targetArrayList = Main.getAdmin().getCurrentPractice().getWorkMap().get(targetUser);
        for (Question question : targetArrayList) {
            if (question.equals(targetQuestion)) {
                System.out.println(question.getWorkSpace().getLastAnswer());
                System.out.println("Enter score : ");
                question.getWorkSpace().getLastAnswer().setScore(ScannerWrapper.getInstance().nextDouble());
                System.out.println("score setted to : " + question.getWorkSpace().getScore());
                return TournamentMainPracticeEdit.getInstance();
            }
        }
        System.out.println("Wrong input!");
        return TournamentMainPracticeEdit.getInstance();
    }

    private Menu questionEditing(Question targetQuestion){
        System.out.println();
        System.out.println(targetQuestion);
        printQuestionEditingMenu();
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Back":
                return TournamentMainPracticeEdit.getInstance();
            case "Edit_description":
                editDescription(targetQuestion, input);
                return TournamentMainPracticeEdit.getInstance();
            case "Add_answer":
                addAnswer(targetQuestion, input);
                return TournamentMainPracticeEdit.getInstance();
            case "Delete":
                delete(targetQuestion);
                return TournamentMainPracticeEdit.getInstance();
            case "Set_multiplier" :
                setMultiplier(targetQuestion);
                return TournamentMainPracticeEdit.getInstance();
            default:
                System.out.println("Wrong input");
                return TournamentMainPracticeEdit.getInstance();
        }
    }

    private void setMultiplier(Question targetQuestion){
        System.out.println("Enter new multiplier for question");
        Double newMultiplier = ScannerWrapper.getInstance().nextDouble();
        targetQuestion.setMultiplier(newMultiplier);
    }

    private void delete(Question targetQuestion){
        ArrayList<Question> result = Main.getAdmin().getCurrentPractice().getQuestions();
        result.remove(targetQuestion);
        Main.getAdmin().getCurrentPractice().setQuestions(result);
        Main.getAdmin().getCurrentPractice().makeWorkMap(Main.getAdmin().getManagingTournament());
    }

    private void addAnswer(Question targetQuestion,String input){
        System.out.println("Enter answer");
        input = ScannerWrapper.getInstance().next();
        targetQuestion.setAnswer(input);
        Main.getAdmin().getCurrentPractice().makeWorkMap(Main.getAdmin().getManagingTournament());
    }

    private void editDescription(Question targetQuestion,String input){
        System.out.println("Enter new description");
        input = ScannerWrapper.getInstance().next();
        targetQuestion.setDescription(input);
        System.out.println("updated!");
        Main.getAdmin().getCurrentPractice().makeWorkMap(Main.getAdmin().getManagingTournament());
    }

    public static void printMarks(){
        Main.getAdmin().getCurrentPractice().makeMarks();
        for (User user : Main.getAdmin().getCurrentPractice().getUserMarks().keySet()){
            System.out.println(user.getName() + " | " + Main.getAdmin().getCurrentPractice().getUserMarks().get(user) + " | " +
                    Main.getAdmin().getCurrentPractice().getSumMarks().get(user));
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
        if (Main.getAdmin().getCurrentPractice().getQuestions().size() == 0){
            System.out.println("This practice does not have any question");
        }
        for (Question question : Main.getAdmin().getCurrentPractice().getQuestions()){
            question.smallPrint("Teacher");
        }
    }

    private Question chooseQuestion(){
        System.out.println("Choose question : ");
        String input = ScannerWrapper.getInstance().next();
        for (Question question : Main.getAdmin().getCurrentPractice().getQuestions()){
            if (question.getName().equals(input)){
                return question;
            }
        }
        System.out.println("Wrong input!");
        return null;
    }
}
