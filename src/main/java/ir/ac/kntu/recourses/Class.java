package ir.ac.kntu.recourses;

import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.storage.UsersStorage;

import java.util.ArrayList;
import java.util.Objects;

public class Class {

    private ArrayList<User> registeredUsers;

    private ArrayList<Practice> practices;

    private String name;

    private String educationalInstitution;

    private String teacherName;

    private User teacher;

    private int academicYear;

    private boolean registrable;

    private String password = null;

    private String[] description;

    private Accessibility accessibility;

    private static int idCounter = 0;

    private final int id;

    public Class(String name, String educationalInstitution, String teacherName, int academicYear){
        this.name = name;
        this.educationalInstitution = educationalInstitution;
        this.teacherName = teacherName;
        this.teacher = UsersStorage.findByName(teacherName);
        this.academicYear = academicYear;
        registeredUsers = new ArrayList<>();
        this.registrable = true;
        practices = new ArrayList<>();
        this.accessibility = Accessibility.PUBLIC;
        idCounter++;
        id = idCounter;
        this.teacher = findTeacher();
    }

    private User findTeacher(){
        for (User user : UsersStorage.getUsers()){
            if (user.getName().equals(teacherName)){
                return user;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public User getTeacher() {
        return teacher;
    }

    public String getPassword() {
        return password;
    }

    public void setAccessibility(Accessibility accessibility) {
        this.accessibility = accessibility;
    }


    public String getTeacherName() {
        return teacherName;
    }

    public void register(User user){
        registeredUsers.add(user);
        for (Practice practice : practices){
            ArrayList<Question> copy = Question.cloneArray(practice.getQuestions());
            for (Question question : copy){
                question.makeWorkSpace(user);
            }
            practice.getWorkMap().put(user,copy);
        }
    }

    public boolean isRegistrable(){
        return registrable;
    }


    public void setDescription(String[] description) {
        this.description = description;
    }

    public void setRegistrable(boolean registrable) {
        this.registrable = registrable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Class aClass = (Class) o;
        return id == aClass.id && teacherName.equals(aClass.teacherName);
    }

    public boolean isInClass(User user){
        for (User user1 : registeredUsers){
            if (user.equals(user1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherName, id);
    }

    public String getName() {
        return name;
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public String[] getDescription() {
        return description;
    }

    public ArrayList<Practice> getPractices() {
        return practices;
    }

    public void setPractices(ArrayList<Practice> practices) {
        this.practices = practices;
    }

    public void removePractice(Practice practice){
        for (Practice search : practices){
            if (search.equals(practice)){
                practices.remove(practice);
                return;
            }
        }
        System.out.println("Sorry but this practice is not in this class");
    }

    public void addWorkSpace(User user){
        for (Practice practice : practices){
            practice.addWorkSpace(user);
        }
    }

    public ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void makeNewPractice(Practice practice){
        practice.setOwner(this);
        practice.makeWorkMap(this);
        practices.add(practice);
    }

    @Override
    public String toString(){
        return "Class name : " + name + " - " +
                "Teacher name : " + teacherName + " - " +
                "Academic Year : " + academicYear;
    }

    public void removeStudentFromClass(User student){
        for (User user : registeredUsers){
            if (user.equals(student)){
                registeredUsers.remove(student);
                return;
            }
        }
        System.out.println("Could not find this user in your class");
    }

    public void setTeacher(String userName){
        for (User user : registeredUsers){
            if (user.getUserName().equals(userName)){
                Customer temp = (Customer) teacher;
                temp.getOwnedClass().remove(this);
                teacher = user;
                teacherName = userName;
                temp = (Customer) teacher;
                ((Customer) teacher).getOwnedClass().add(this);
                return;
            }
        }
        System.out.println("Sorry but there is no user with that name in this class");
    }
}


