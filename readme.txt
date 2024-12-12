Word Tracker Program

OVERVIEW

This program processes text files to find the occurrences of each unique word, tracks the line numbers where the word appears, and stores this information in a Binary Search Tree (BST). It can generate different reports based on the occurrences of words in the given files.



FEATURES

- Processes text files to track word occurrences and their line numbers.
- Supports three report formats:
  - `-pf`: List words found in files.
  - `-pl`: List words along with their line numbers.
  - `-po`: List words, line numbers, and their frequency of occurrences.
- Saves the processed data in a serialized repository for future use.


  
HOW IT WORKS

1. Load Repository: The program loads a previously saved repository of word occurrences from a serialized file (`repository.ser`). If the file doesn’t exist, a new empty BSTree is created.

2. Process Files: The program processes each file located in the "res/" folder of the program, extracting words and their line numbers, then adding them to the BST. Add your text files in this folder in order. 

3. Generate Report: Based on the user’s choice of report option (`-pf`, `-pl`, `-po`), the program generates a report either in the console or a specified output file.

4. Save Repository: The updated word occurrences are saved back to the serialized file after processing for persistence.



HOW TO USE

1. Run program: 
   - Locate the `/dist` folder inside the program folder on your terminal. This folder contains the WordTracker.jar file.
   - Run in the program on your terminal typing:

java -jar WordTracker.jar <input.txt> -pf/-pl/-po -f <output.txt>

   


NOTE: <input.txt> is the text file to parse, and <output.txt> the name of the output report file (use the `-f` flag before it).

(See below for further instructions for the different flags you can use to generate the report types)

NOTE: The program reads TXT files from the `../res/` directory. The repository is saved in `../dist/repository.ser`.

   
2. Generate Reports:

   - Use the `-pf`, `-pl`, or `-po` options to specify the report format.
   - Example command to generate a report:


     java -jar WordTracker.jar <input.txt> -pl -f output.txt

   - This will generate a report in the `output.txt` file with words and their line numbers.




