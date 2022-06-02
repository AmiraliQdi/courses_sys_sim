package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class TeacherMenu implements Menu{

    private static TeacherMenu instance = new TeacherMenu();

    private TeacherMenu(){
    }

    public static TeacherMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        //Hard_coded
        System.out.println("=================================================");
        System.out.println();
        for (Class search : ClassesStorage.classOfUserTeacher(Main.loggedInUser)){
            System.out.println(search);
        }
        if (Main.loggedInUser.getOwnedClass().size() == 0){
            System.out.println("You do not own any class!");
        }
        System.out.println();
        System.out.println("Create new class");
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
    }

    @Override
    public Menu handleMenu() {
        Main.loggedInUser.setOwnedClass(ClassesStorage.classOfUserTeacher(Main.loggedInUser));
        setTeacherClasses();
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")) {
            return UserMenu.getInstance();
        } else if (input.equals("Create_new_class")){
            Class newClass = makeNewClass();
            ClassesStorage.addClass(newClass);
            return TeacherMenu.getInstance();
        } else {
            for (Class search : Main.loggedInUser.getOwnedClass()){
                if (search.getName().equals(input)){
                    Main.loggedInUser.setManagingClass(search);
                    return ManegingClass.getInstance();
                }
            }
            System.out.println("Wrong input");
            return TeacherMenu.getInstance();
        }

    }

    private void setTeacherClasses(){
        ArrayList<Class> ownedClass = ClassesStorage.classOfUserTeacher(Main.loggedInUser);
        Main.loggedInUser.setOwnedClass(ownedClass);
    }

    private Class makeNewClass(){
        System.out.println("Enter class name : ");
        String name = ScannerWrapper.getInstance().next();
        System.out.println("Enter Educational Institution name : ");
        String educationalInstitution = ScannerWrapper.getInstance().next();
        System.out.println("Enter academic year of new class : ");
        int academicYear = ScannerWrapper.getInstance().nextInt();
        return new Class(name,educationalInstitution,Main.loggedInUser.getName(),academicYear);
    }
}
