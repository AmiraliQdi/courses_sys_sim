package ir.ac.kntu.menu;

import ir.ac.kntu.storage.PrePrintedStrings;

import java.util.ArrayList;

public interface Menu {

    ArrayList<String> options = new ArrayList<>();

    default void clearOptions(){
        options.clear();
    }

    void printMenu();

    default void printInteractMenu(){
        System.out.println(PrePrintedStrings.START_MENU);
        for (int i = 1;i<=options.size();i++){
            System.out.println(i+")"+options.get(i-1));
        }
        System.out.println(PrePrintedStrings.END_MENU);
        clearOptions();
    }

    default void printInfoMenu(Object o){
        System.out.println(o.toString());
        clearOptions();
    }

    Menu handleMenu();

}
