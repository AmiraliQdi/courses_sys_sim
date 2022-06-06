package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.PracticeStatus;
import ir.ac.kntu.util.ScannerWrapper;

public class StudentMenu implements Menu{

    private static StudentMenu instance = new StudentMenu();

    private StudentMenu(){
    }

    public static StudentMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        //Hard_coded
        System.out.println("=================================================");
        System.out.println();
        for (Class target : Main.getCustomer().getJoinedClass()){
            System.out.println(target);
        }
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
    }

    @Override
    public Menu handleMenu() {
        System.out.println("Enter the class name you want to enter :");
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")){
            return ClassMenu.getInstance();
        }
        Class targetClass = selectClass(input);
        if (targetClass == null){
            return StudentMenu.getInstance();
        }
        Main.getCustomer().setManagingClass(targetClass);
        System.out.println("Class practices : ");
        showPractices(targetClass);
        System.out.println("Select practice");
        input = ScannerWrapper.getInstance().next();
        Practice targetPractice = findPractice(input,targetClass);
        if (targetPractice == null){
            System.out.println("Wrong input");
            return StudentMenu.getInstance();
        }
        Main.getCustomer().setCurrentPractice(targetPractice);
        return WorkSpaceMenu.getInstance();
    }

    private Practice findPractice(String name,Class targetClass){
        for (Practice practice : targetClass.getPractices()){
            if (practice.getName().equals(name)){
                if (practice.getStatus() == PracticeStatus.ACCESSIBLE){
                    return practice;
                } else if (practice.getStatus() == PracticeStatus.PASSED){
                    System.out.println("Sending time has passed!");
                    return null;
                }

            }
        }
        return null;
    }

    private Class findClass(String name){
        for (Class search : Main.getCustomer().getJoinedClass()){
            if (search.getName().equals(name)){
                return search;
            }
        }
        return null;
    }

    private void showPractices(Class targetClass){
        for (Practice practice : targetClass.getPractices()){
            if (practice.getStatus() == PracticeStatus.ACCESSIBLE){
                System.out.println(practice);
            } else if (practice.getStatus() == PracticeStatus.PASSED){
                System.out.println(practice + "/Sending time passed");
            }
        }
    }


    private Class selectClass(String input){
        if (findClass(input) == null){
            System.out.println("Wrong name");
            return null;
        }
        return findClass(input);
    }
}
