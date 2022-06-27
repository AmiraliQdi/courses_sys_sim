package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Accessibility;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.PracticeStatus;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.Date;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class ManegingClass implements Menu{

    private static ManegingClass instance = new ManegingClass();

    private ManegingClass(){
    }

    public static ManegingClass getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("Students");
        OPTIONS.add("Practices");
        OPTIONS.add("Class settings");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input){
            case "Students" :
                return studentsMenu();
            case "Practices" :
                return practices();
            case "Class_settings" :
                return classSettings();
            case "Back":
                return TeacherMenu.getInstance();
            default:
                System.out.println("Wrong input!");
                return ManegingClass.getInstance();
        }
    }

    private Menu classSettings(){
        tempPrint();
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Delete_this_class" :
                ArrayList<Class> ownedClass = Main.getCustomer().getOwnedClass();
                ownedClass.remove(Main.getCustomer().getManagingClass());
                Main.getCustomer().setOwnedClass(ownedClass);
                ClassesStorage.removeClass(Main.getCustomer().getManagingClass());
                return TeacherMenu.getInstance();
            case "Registrable":
                System.out.println("1)ON");
                System.out.println("2)OFF");
                input = ScannerWrapper.getInstance().next();
                if (input.equals("1")) {
                    Main.getCustomer().getManagingClass().setRegistrable(true);
                    System.out.println("Class registrable set to true");
                } else if (input.equals("2")) {
                    Main.getCustomer().getManagingClass().setRegistrable(false);
                    System.out.println("Class registrable set to false");
                } else {
                    System.out.println("Wrong input!");
                    return ManegingClass.getInstance();
                }
                break;
            case "Privacy":
                return privacy(input);
            case "Set_new_teacher" :
                System.out.println("Enter new teacher name from joined users");
                System.out.println(Main.getCustomer().getManagingClass().getRegisteredUsers());
                Main.getCustomer().getManagingClass().setTeacher(ScannerWrapper.getInstance().next());
                return ManegingClass.getInstance();
            default:
                return ManegingClass.getInstance();
        }
        return ManegingClass.getInstance();
    }

    private Menu privacy(String input){
        System.out.println("1)PUBLIC");
        System.out.println("2)PRIVATE");
        input = ScannerWrapper.getInstance().next();
        if (input.equals("1")) {
            Main.getCustomer().getManagingClass().setAccessibility(Accessibility.PUBLIC);
            System.out.println("Set accessibility to public");
        } else if (input.equals("2")) {
            Main.getCustomer().getManagingClass().setAccessibility(Accessibility.PRIVATE);
            System.out.println("Set accessibility to private");
        } else {
            System.out.println("Wrong input!");
            return ManegingClass.getInstance();
        }
        return ManegingClass.getInstance();
    }

    private void tempPrint(){
        System.out.println("=================================================");
        System.out.println();
        System.out.println("1)Registrable : " + Main.getCustomer().getManagingClass().isRegistrable());
        System.out.println("2)Privacy " + Main.getCustomer().getManagingClass().getAccessibility());
        System.out.println("3)Delete this class");
        System.out.println("4)Set new teacher");
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
    }

    private Menu practices(){
        System.out.println("=================================================");
        System.out.println();
        System.out.println("Add new practice");
        System.out.println("See practices");
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");

        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Add_new_practice")){
            newPractice();
        } else if (input.equals("See_practices")){
            printPractices();
            Practice selectedPractice = choosePractice();
            if (selectedPractice == null){
                return ManegingClass.getInstance();
            } else {
                Main.getCustomer().setCurrentPractice(selectedPractice);
                Main.getCustomer().setLastMenu(ManegingClass.getInstance());
                return EditingPractice.getInstance();
            }
        } else if (input.equals("Back")){
            return ManegingClass.getInstance();
        } else {
            System.out.println("Wrong input");
        }
        return ManegingClass.getInstance();
    }

    private Practice choosePractice(){
        System.out.println("Choose practice");
        String name = ScannerWrapper.getInstance().next();
        for (Practice practice : Main.getCustomer().getManagingClass().getPractices()){
            if (practice.getName().equals(name)){
                return practice;
            }
        }
        System.out.println("Wrong name");
        return null;
    }

    private void printPractices(){
        System.out.println();
        for (Practice practice : Main.getCustomer().getManagingClass().getPractices()){
            System.out.println(practice);
        }
        System.out.println();
    }

    private void newPractice(){
        System.out.println("Enter new practice name :");
        String name = ScannerWrapper.getInstance().next();
        System.out.println("Enter description : ");
        String description = ScannerWrapper.getInstance().next();
        System.out.println("Enter starting date and ending date (example : 1401/2/5-1401/2/7");
        String dates = ScannerWrapper.getInstance().next();
        Date startingDate = null;
        Date endingDate = null;
        ArrayList<Date> dates1= makeDates(dates);
        startingDate = dates1.get(0);
        endingDate = dates1.get(1);
        PracticeStatus practiceStatus = setStatus();
        System.out.println("Enter delay time  multiplier : ");
        double delay = ScannerWrapper.getInstance().nextDouble();
        System.out.println("Enter delay time : ");
        int delayTime = ScannerWrapper.getInstance().nextInt();
        Practice newPractice = new Practice(name,description,startingDate,endingDate,delay,delayTime);
        newPractice.setStatus(practiceStatus);
        newPractice.setOwner(Main.getCustomer().getManagingClass());
        Main.getCustomer().getManagingClass().makeNewPractice(newPractice);
    }

    private static PracticeStatus setStatus(){
        System.out.println("Choose practice status : ");
        System.out.println("1)ACCESSIBLE");
        System.out.println("2)TESTING");
        System.out.println("3)HIDDEN");
        int input = ScannerWrapper.getInstance().nextInt();
        switch (input){
            case 1 :
                return PracticeStatus.ACCESSIBLE;
            case 2 :
                return PracticeStatus.TESTING;
            case 3 :
                return PracticeStatus.HIDDEN;
            default:
                System.out.println("Wrong input");
                return PracticeStatus.NA;
        }
    }

    private ArrayList<Date> makeDates(String dates){
        dates = dates.replaceAll(" ","");
        String[] eachDate = dates.split("-");
        if (eachDate[0] == null || eachDate[1] == null){
            System.out.println("Wrong input");
        }
        String startDate = eachDate[0];
        String endDate = eachDate[1];
        String[] numbers = startDate.split("/");
        int year = Integer.parseInt(numbers[0]);
        int month = Integer.parseInt(numbers[1]);
        int day = Integer.parseInt(numbers[2]);
        String[] numbers1 = endDate.split("/");
        int year1 = Integer.parseInt(numbers1[0]);
        int month1 = Integer.parseInt(numbers1[1]);
        int day1 = Integer.parseInt(numbers1[2]);
        Date start = new Date(year,month,day);
        Date end = new Date(year1,month1,day1);
        ArrayList<Date> resultDates = new ArrayList<>();
        resultDates.add(start);
        resultDates.add(end);
        return resultDates;
    }

    private Menu studentsMenu(){
        System.out.println("=================================================");
        System.out.println();
        System.out.println("Add new student");
        System.out.println("Remove a student");
        System.out.println("Show students");
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Add_new_student")){
            addNewStudentToClass();
            return ManegingClass.getInstance();
        } else if (input.equals("Back")){
            return ManegingClass.getInstance();
        } else if (input.equals("Show_students")){
            for (User user : Main.getCustomer().getManagingClass().getRegisteredUsers()){
                System.out.println(user);
            }
            System.out.println();
            return ManegingClass.getInstance();
        } else if (input.equals("Remove_a_student")){
            removeStudentFromClass();
            return ManegingClass.getInstance();
        } else {
            System.out.println("Wrong input");
            return ManegingClass.getInstance();
        }
    }

    private void addNewStudentToClass(){
        System.out.println("Enter the student email or National number : ");
        String input = ScannerWrapper.getInstance().next();
        User search = UsersStorage.findByEmail(input);
        if (search == null){
            search = UsersStorage.findByNationalNumber(input);
        }
        if (search!=null){
            Main.getCustomer().getManagingClass().register(search);
            Main.getCustomer().getManagingClass().addWorkSpace(search);
        }
    }

    private void removeStudentFromClass(){
        System.out.println("Enter the student email or National number : ");
        String input = ScannerWrapper.getInstance().next();
        User search = UsersStorage.findByEmail(input);
        if (search == null){
            search = UsersStorage.findByNationalNumber(input);
        }
        if (search!= null){
            Main.getCustomer().getManagingClass().removeStudentFromClass(search);
        }
    }
}
