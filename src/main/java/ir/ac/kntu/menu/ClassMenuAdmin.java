package ir.ac.kntu.menu;

public class ClassMenuAdmin implements Menu{

    private static final ClassMenuAdmin instance = new ClassMenuAdmin();

    public static ClassMenuAdmin getInstance() {
        return instance;
    }

    private ClassMenuAdmin(){

    }

    @Override
    public void printMenu() {

    }

    @Override
    public Menu handleMenu() {
        return null;
    }
}
