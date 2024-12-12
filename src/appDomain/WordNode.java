/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appDomain;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 *
 * @author user
 */
class WordNode implements Comparable<WordNode>, Serializable {  
    private final String word;
    private final Map<String, List<Integer>> occurrences;
    private static final long serialVersionUID = 1L;

    
    public WordNode(String word) {
        this.word = word;
        this.occurrences = new HashMap<>();
    }

    
    public void addOccurrence(String fileName, int lineNumber) {
        if (!occurrences.containsKey(fileName)) {
            occurrences.put(fileName, new ArrayList<>());
        }
        occurrences.get(fileName).add(lineNumber);
                
//      occurrences.computeIfAbsent(fileName, k -> new ArrayList<>()).add(lineNumber);
//
//       List<Integer> lines = occurrences.get(fileName);
//       if (lines == null) {
//           lines = new ArrayList<>();
//           occurrences.put(fileName, lines);
//       }

    }
    
    public int getTotalOccurrences() {       
                            //get total number of times the word appears in all files and lines
        int total = occurrences.values().stream().mapToInt(List::size).sum();
        
        return total;
    }

    @Override
    public int compareTo(WordNode other) {
        return this.word.compareTo(other.word);
    }

    public String toStringFiles() {
        return "Key : ===" + word + "===" + " found in file: " + occurrences.keySet();       //set view of the keys contained in map
    }

    public String toStringFilesAndLines() {
//        return"Key : ===" + word + "===" + " found in file: " + occurrences.keySet() 
//                + " on lines: " + occurrences.get(occurrences.keySet());
               
        StringBuilder sb = new StringBuilder();
        sb.append("Key : ===").append(word).append("=== found in file: ");

        for (Map.Entry<String, List<Integer>> entry : occurrences.entrySet()) {
            String fileName = entry.getKey(); 
            List<Integer> lineNumbers = entry.getValue(); 

            sb.append(fileName).append(" on lines: ");
            sb.append(lineNumbers.stream()
                    .map(String::valueOf) 
                    .collect(Collectors.joining(", "))); 
            sb.append(" ");
        }

        return sb.toString().trim(); // Remove any trailing spaces
    }
    

    public String toStringFilesLinesAndFrequency() {
        StringBuilder sb = new StringBuilder();
        sb.append("Key : ===").append(word).append("=== ")
          .append("number of entries: ").append(getTotalOccurrences()).append(" ");
        
        for (Map.Entry<String, List<Integer>> entry : occurrences.entrySet()) {
            
            String fileName = entry.getKey();
            List<Integer> lineNumbers = entry.getValue(); 
            
            sb.append("found in file: ").append(fileName).append(" on lines: ");
            sb.append(lineNumbers.stream().map(String::valueOf)
                    .collect(Collectors.joining(", "))); 
            sb.append(", "); 
        }              

        return sb.toString();
    }
}
