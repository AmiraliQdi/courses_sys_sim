package ir.ac.kntu.menu;

public class ManagingTournament implements Menu{

    private static final ManagingTournament instance = new ManagingTournament();

    private ManagingTournament(){

    }

    public static ManagingTournament getInstance() {
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
