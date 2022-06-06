package ir.ac.kntu.menu;

import ir.ac.kntu.storage.PrePrintedStrings;

import java.util.ArrayList;

public interface Menu {

    ArrayList<String> OPTIONS = new ArrayList<>();

    default void clearOPTIONS(){
        OPTIONS.clear();
    }

    void printMenu();

    default void printInteractMenu(){
        System.out.println(PrePrintedStrings.START_MENU);
        for (int i = 1;i<=OPTIONS.size();i++){
            System.out.println(i+")"+OPTIONS.get(i-1));
        }
        System.out.println(PrePrintedStrings.END_MENU);
        clearOPTIONS();
    }

    default void printInfoMenu(Object o){
        System.out.println(o.toString());
        clearOPTIONS();
    }

    Menu handleMenu();

}
