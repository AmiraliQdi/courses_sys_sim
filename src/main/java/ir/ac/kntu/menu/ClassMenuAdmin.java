package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Accessibility;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.storage.PrePrintedStrings;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class ClassMenuAdmin implements Menu{

    private static final ClassMenuAdmin instance = new ClassMenuAdmin();

    public static ClassMenuAdmin getInstance() {
        return instance;
    }

    private ClassMenuAdmin(){

    }

    @Override
    public void printMenu() {
        OPTIONS.add("See all classes");
        OPTIONS.add("Make new class");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "See_all_classes" :
                seeAllClasses();
                Class targetClass = chooseClass();
                Main.getAdmin().setManagingClass(targetClass);
                return classSettings();
            case "Make_new_class" :
                Class newClass = makeNewClass();
                ClassesStorage.addClass(newClass);
            case "Back" :
                return AdminMenu.getInstance();
            default:
                System.out.println("Wrong input");
                return ClassMenuAdmin.getInstance();
        }
    }

    private Class makeNewClass(){
        System.out.println("Enter class name : ");
        String name = ScannerWrapper.getInstance().next();
        System.out.println("Enter Educational Institution name : ");
        String educationalInstitution = ScannerWrapper.getInstance().next();
        System.out.println("Enter academic year of new class : ");
        int academicYear = ScannerWrapper.getInstance().nextInt();
        return new Class(name,educationalInstitution,Main.getAdmin().getName(),academicYear);
    }

    private void seeAllClasses(){
        for (Class targetClass : ClassesStorage.getClasses()){
            System.out.println(targetClass.getName() + " | teacher :" + targetClass.getTeacherName());
        }
    }

    private Class chooseClass(){
        System.out.println("Enter class name to edit");
        String input = ScannerWrapper.getInstance().next();
        Class targetClass = ClassesStorage.findClassByName(input);
        if (targetClass == null){
            return null;
        }
        return targetClass;
    }

    private void tempPrint(){
        System.out.println("=================================================");
        System.out.println();
        System.out.println("1)Registrable : " + Main.getAdmin().getManagingClass().isRegistrable());
        System.out.println("2)Privacy " + Main.getAdmin().getManagingClass().getAccessibility());
        System.out.println("4)Set new teacher");
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
    }

    private Menu classSettings(){
        tempPrint();
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Registrable":
                System.out.println("1)ON");
                System.out.println("2)OFF");
                input = ScannerWrapper.getInstance().next();
                if (input.equals("1")) {
                    Main.getAdmin().getManagingClass().setRegistrable(true);
                    System.out.println("Class registrable set to true");
                } else if (input.equals("2")) {
                    Main.getAdmin().getManagingClass().setRegistrable(false);
                    System.out.println("Class registrable set to false");
                } else {
                    System.out.println("Wrong input!");
                    return ClassMenuAdmin.getInstance();
                }
                break;
            case "Privacy":
                return privacy(input);
            case "Set_new_teacher" :
                System.out.println("Enter new teacher name from joined users");
                System.out.println(Main.getAdmin().getManagingClass().getRegisteredUsers());
                Main.getAdmin().getManagingClass().setTeacher(ScannerWrapper.getInstance().next());
                return ClassMenuAdmin.getInstance();
            default:
                return ClassMenuAdmin.getInstance();
        }
        return ClassMenuAdmin.getInstance();
    }

    private Menu privacy(String input){
        System.out.println("1)PUBLIC");
        System.out.println("2)PRIVATE");
        input = ScannerWrapper.getInstance().next();
        if (input.equals("1")) {
            Main.getAdmin().getManagingClass().setAccessibility(Accessibility.PUBLIC);
            System.out.println("Set accessibility to public");
        } else if (input.equals("2")) {
            Main.getAdmin().getManagingClass().setAccessibility(Accessibility.PRIVATE);
            System.out.println("Set accessibility to private");
        } else {
            System.out.println("Wrong input!");
            return ClassMenuAdmin.getInstance();
        }
        return ClassMenuAdmin.getInstance();
    }
}
