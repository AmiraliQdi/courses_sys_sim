package ir.ac.kntu.storage;

import ir.ac.kntu.recourses.Accessibility;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.util.ScannerWrapper;
import java.util.ArrayList;

public class ClassesStorage {

    private static ArrayList<Class> classes = new ArrayList<>();

    //REGISTER

    public static void register(Class targetClass, User registering){
        if (targetClass.isRegistrable()){
            if (targetClass.getAccessibility() == Accessibility.PUBLIC){
                targetClass.register(registering);
            } else {
                System.out.println("Enter password : ");
                String input = ScannerWrapper.getInstance().next();
                if (targetClass.getPassword().equals(input)){
                    targetClass.register(registering);
                }
            }
        } else {
            System.out.println("Sorry but this class is not registrable ,please contact your teacher to register in this clas");
        }
    }

    public static void registerWithPassword(String password,Class targetClass,User registering){
        if (targetClass.isRegistrable()){
            if (targetClass.getAccessibility() == Accessibility.PUBLIC){
                System.out.println("You don't need password to register in this class!");
            } else {
                if (targetClass.getPassword().equals(password)){
                    targetClass.register(registering);
                }
            }
        }
    }

    public static void registerByTeacher(Class targetClass,String email,User teacher){
        if (targetClass.getTeacherName().equals(teacher.getName())){
            targetClass.register(UsersStorage.findByEmail(email));
        } else {
            System.out.println("Sorry but you are not the teacher of this class!");
        }
    }

    private static boolean isAvailable(Class newClass){
        for (Class targetClass : classes){
            if (targetClass.equals(newClass)){
                return false;
            }
        }
        return true;
    }

    public static void removeClass(Class targetClass){
        for (Class search : classes){
            if (targetClass.equals(search)){
                classes.remove(targetClass);

                return;
            }
        }
        System.out.println("Targeted class is not in storage");
    }

    //SEARCH

    public static Class findClassByID(int id){
        for (Class search : classes){
            if (search.getId() == id){
                return search;
            }
        }
        System.out.println("Could not find any class with this ID");
        return null;
    }

    public static Class findClassByName(String name){
        for (Class search : classes){
            if (search.getName().equals(name)){
                return search;
            }
        }
        System.out.println("Could not found a class with this name");
        return null;
    }

    public static Class findByEI(String name){
        for (Class search : classes){
            if (search.getEducationalInstitution().equals(name)){
                return search;
            }
        }
        System.out.println("Could not found a class with this educational institutional name");
        return null;
    }

    public static ArrayList<Class> findByTeacher(User teacher){
        ArrayList<Class> result = new ArrayList<>();
        for (Class search : classes){
            if (search.getTeacher().equals(teacher)){
                result.add(search);
            }
        }
        if (result.size()== 0){
            System.out.println("Could not find any class with this teacher");
            return null;
        }
        return result;
    }

    public static ArrayList<Class> findByEducationalInstitution(String educationalInstitution){
        ArrayList<Class> result = new ArrayList<>();
        for (Class search : classes){
            if (search.getEducationalInstitution().equals(educationalInstitution)){
                result.add(search);
            }
        }
        if (result.size()==0){
            System.out.println("Could not find any class with this educationalInstitution name");
            return null;
        }
        return result;
    }

    public static void addClass(Class newClass){
        if (isAvailable(newClass)){
            classes.add(newClass);
        }
    }

    public static ArrayList<Class> classOfUser(User user){
        ArrayList<Class> result = new ArrayList<>();
        for (Class search : classes){
            if (search.isInClass(user)){
                result.add(search);
            }
        }
        return result;
    }

    public static ArrayList<Class> classOfUserTeacher(User user){
        ArrayList<Class> result = new ArrayList<>();
        for (Class search : classes){
            if (search.getTeacherName().equals(user.getName())){
                result.add(search);
            }
        }
        return result;
    }
}
