package test;

import java.util.ArrayList;
import java.util.List;

public class Tasks {

        //простая переменная.
    int a = 10;

        //Метод добавления чисел
    static int plus(int a, int b){
        int result=a+b;
        return result;
    }

        //Превращаем объект в число
    private static int objectToInt(Object o){
        return Integer.parseInt(String.valueOf(o));
    }

        //Метод добавления в список
    static List<String> list = new ArrayList<>();
    static void add(String name){
        list.add(name);
    }

        //Вывод списка в консоль
    static void printList(List<String> list){
        String names = "";
        for (int i=0; i<list.size(); i++){
            names=names+" "+list.get(i);
        }
        System.out.println(names);
    }

        //Удаление из списка по имени
    static void removeFromList(String name){
        list.remove(name);
    }

        //Использование switch. Выводить текст с числами: 1,2,3. С другими числами вывести ошибку через блок try catch.
    static void usingNumber(int number){
        switch (number) {
            case (1):
                System.out.println("One");
                break;
            case (2):
                System.out.println("Two");
                break;
            case (3):
                System.out.println("three");
                break;
            default:
                try {
                    throw new Exception("Number doesn't exist");
                }catch (Exception e){
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        Tasks tasks = new Tasks();
        System.out.println(tasks.a);
        System.out.println(plus(4,3));
        System.out.println(plus(objectToInt(4), 4));
        add("Jeffrys");
        add("Victor");
        add("Leonardo");
        printList(list);
        removeFromList("Leonardo");
        usingNumber(1);
        usingNumber(2);
        usingNumber(3);
        usingNumber(4);
    }
}
