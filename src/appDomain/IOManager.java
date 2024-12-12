/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appDomain;
import implementations.BSTree;
import implementations.BSTreeNode;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import utilities.Iterator;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author user
 */
public class IOManager {
    
    private static final String repositoryfile = "repository.ser";
    private static final long serialVersionUID = 1L;
    
    public static BSTree<WordNode> loadRepository() {
        
        File repositoryFile = new File(repositoryfile);
        if (!repositoryFile.exists()) {
            return new BSTree<>();
        }

        //deserialize bst (if it exists)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repositoryFile))) {
                                        
            return (BSTree<WordNode>) ois.readObject();                         // read and cast into BSTree
        } catch (IOException | ClassNotFoundException e) {
            
            System.err.println("There was an Error loading repository: " + e.getMessage());
            return new BSTree<>();
        }
    }

    
    public static void saveRepository(BSTree<WordNode> wordTree) {
        //serialize bst object
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repositoryfile))) {            
            oos.writeObject(wordTree);          
        } 
//        catch (NotSerializableException nse) {
//            System.err.println("Non serializable error: " + nse.getMessage());
//        } 
        catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving repository: " + e.getMessage());
        }
    }
    
    public static void processFile(String inputFile, BSTree<WordNode> wordTree) throws IOException {
        
        inputFile = "../res/" + inputFile;
        
//        Path filePath = Paths.get("../res/" + inputFile);
        
//        if (!Files.exists(filePath)) {
//            System.err.println("File does not exist: " + filePath.toString());
//        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int lineNumber = 1;

            
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");                                    // splits into words- ignoring punctuation
                for (String word : words) {
  
                    if (!word.isEmpty()) {
                        
                        word = word.replace("it's", "its").trim();
                        word = word.replace(",", "").trim();
                        word = word.replace(".", "").trim();
                        word = word.replace("!", "").trim();
                        
                        WordNode wordNode = new WordNode(word);
                        
                                                            //check if word in bst-wordtree adn get actual wordnode from it if existent :or null
                        BSTreeNode<WordNode> node = wordTree.search(wordNode);                      //get BSTreeNode
                        WordNode existingNode = (node != null) ? node.getElement() : null;         // xtract element

                        if (existingNode == null) {
                            wordNode.addOccurrence(inputFile, lineNumber);              //record first occurrence
                            wordTree.add(wordNode);
                        } else {
                            existingNode.addOccurrence(inputFile, lineNumber);
                        }
                    }
                }
                lineNumber++;
                
            }
        }
    }
    
    public static void generateReport(BSTree<WordNode> wordTree, String option, String outputFile) throws IOException {
        
        List<String> reportLines = new ArrayList<>();
        Iterator<WordNode> iterator = wordTree.inorderIterator();
        System.out.println("Writing "+option+" format");
        while (iterator.hasNext()) {
            
            WordNode wordNode = iterator.next();            
            switch (option) {
                case "-pf":
                    reportLines.add(wordNode.toStringFiles());
                    break;
                case "-pl":
                    reportLines.add(wordNode.toStringFilesAndLines());
                    break;
                case "-po":
                    reportLines.add(wordNode.toStringFilesLinesAndFrequency());
                    break;
                default:
                    System.err.println("Invalid option: " + option);
                    return;
            }
        }

        if (outputFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (String line : reportLines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } else {
            for (String line : reportLines) {
                System.out.println(line);
            }
        }
    }
}
