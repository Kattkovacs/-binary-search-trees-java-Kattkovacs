package com.codecool.javabst;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            numbers.add(i * 2 + 5);
        }

        com.codecool.javabst.BinarySearchTree myTree = com.codecool.javabst.BinarySearchTree.build(numbers);

        // write some test code here
        System.out.println(myTree.search(7)); // should be true
        System.out.println(myTree.search(55)); // should be true
        System.out.println(myTree.search(34535)); // should be false

        myTree.add(98);
        myTree.add(3);
        myTree.add(76);

        myTree.remove(81);
        myTree.remove(65);
        myTree.remove(7);
        myTree.remove(55);
        System.out.println("done");
    }
}