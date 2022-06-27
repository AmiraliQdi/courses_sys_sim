package ir.ac.kntu.menu;

public class QuestionBankMenuAdmin implements Menu{

    private static final QuestionBankMenuAdmin instance = new QuestionBankMenuAdmin();

    private QuestionBankMenuAdmin(){

    }

    public static QuestionBankMenuAdmin getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {

    }

    @Override
    public Menu handleMenu() {
        return null;
    }
}
