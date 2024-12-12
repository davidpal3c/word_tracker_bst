/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appDomain;

import implementations.BSTree;
import implementations.BSTreeNode;
import implementations.IBTree;
import utilities.Iterator;
import java.io.IOException;



/**
 *
 * @author mailt
 */
public class Main {
        
    public static void main(String[] args) throws IllegalArgumentException {
        
        /*I got error null for initial  public BSTreeNode(E value) {}
        So I did temporary comment it
        */
//        BSTree<Integer> bst = new BSTree<>();
//        bst.add(50);
//        bst.add(30);
//        bst.add(70);
//        bst.add(20);
//        bst.add(40);
//        bst.add(60);
//        bst.add(80);
//        
//        BSTreeNode<Integer> root = new BSTreeNode<>(10);
//        root.setLeft(new BSTreeNode<>(5));
//        root.setRight(new BSTreeNode<>(15));
//        root.getLeft().setLeft(new BSTreeNode<>(2));
//        root.getLeft().setRight(new BSTreeNode<>(7));
//
//        // in-order iterator
//        IBTree<Integer> iterator = new IBTree<>(root);
//
//        // traverse tree (in-order)
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        
        
        
        // Start program
        
        if (args.length < 2) {
            System.err.println("Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po -f <output.txt>");
            return;
        }

        String inputFile = args[0];
        String option = args[1];
        String outputFile = null;
        
        if (args.length == 4 && args[2].equals("-f")) {
            outputFile = args[3];
        }                    

        // load file 
        BSTree<WordNode> wordTree = IOManager.loadRepository();
        
        // process file
        try {
            IOManager.processFile(inputFile, wordTree);
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
            return;
        }

        //save file
        IOManager.saveRepository(wordTree);

        
        // save and display report
        try {
            IOManager.generateReport(wordTree, option, outputFile);
        } catch (IOException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
        
    }
}
