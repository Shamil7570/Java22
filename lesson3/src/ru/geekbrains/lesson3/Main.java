package ru.geekbrains.lesson3;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String[] auto = {"BMW", "AUDI", "MERSEDES", "BUGGATI", "LEXUS", "BMW", "OPEL", "OPEL", "FERRARI", "LADA"};
        HashMap<String, Integer> machine = new HashMap<>();
        for (String o : auto) {
            machine.put(o, machine.getOrDefault(o, 0) + 1);
        }
        System.out.println(machine);

        Phonebook book = new Phonebook();
        book.addContact("Roma", "363487");
        book.addContact("Rita", "870977");
        book.addContact("Kola", "544456");
        book.addContact("Roma", "877765");
        book.addContact("Sena", "877737");
        book.addContact("Sena", "877733");

        // ищем по имени
        book.findAndPrint("Roma");
        book.findAndPrint("Sena");
        book.findAndPrint("Kola");

    }


}
