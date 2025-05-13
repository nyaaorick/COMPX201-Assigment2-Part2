// DictionaryLookup.java
import java.io.IOException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DictionaryLookup {

    private DictionaryBST dictionary;
    private Scanner scanner;

    public DictionaryLookup() {
        this.dictionary = new DictionaryBST();
        this.scanner = new Scanner(System.in);
    }

    public void loadDictionaryFromFile(String filename) {
    try {
        String content = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        for (String block : content.split("(\\r?\\n[\\t ]*){2,}")) {
            String entry = block.trim(); // 去除块的首尾空白
            if (entry.isEmpty()) { // 如果块在trim后为空，则跳过
                continue;
            }
            int colonPos = entry.indexOf(':'); // 找到第一个冒号的位置
            if (colonPos > 0 && colonPos < entry.length() - 1) {
                String word = entry.substring(0, colonPos).trim();
                String definition = entry.substring(colonPos + 1).trim().replaceAll("\\s*\\r?\\n\\s*", " ");
                if (!word.isEmpty()) { // 确保提取的单词非空
                    dictionary.insert(word, definition);
                }
            }
        }
        System.out.println("词典加载完成。\n"); // 加载完成信息
    }
    catch (IOException e) {
    }
    //
    }

    //user gui
    public void displayMenu() {
        System.out.println("------------------------------------------");
        System.out.println("        (1) search word/phrase");
        System.out.println("        (2) print word/phrase and its definition");
        System.out.println("        (3) add word/phrase and its definition");
        System.out.println("        (4) remove word/phrase and its definition");
        System.out.println("        (5) print all words/phrases and their definitions");
        System.out.println("        (6) exit");
        System.out.println("------------------------------------------");
        System.out.print("\n try enter a number !: ");
    }

    /////
    /// user interaction
    public void run() {
        loadDictionaryFromFile("dictionary.txt"); 
        int choice;
        do {
            displayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        handleSearch();
                        break;
                    case 2:
                        handlePrintItem();
                        break;
                    case 3:
                        handleAddItem();
                        break;
                    case 4:
                        handleRemoveItem();
                        break;
                    case 5:
                        handlePrintAll();
                        break;
                    case 6:
                        System.out.println("BYE BYE");
                        break;
                    default:
                        System.out.println("U SHOULD ENTER A NUMBER BETWEEN 1 AND 6");
                }
            } catch (NumberFormatException e) {
                System.out.println("U SHOULD ENTER A NUMBER BETWEEN 1 AND 6");
                choice = 0;
            }

            if (choice != 6) {
                System.out.println("PRESS ENTER TO CONTINUE");
                scanner.nextLine(); 
            }

        } while (choice != 6);
        scanner.close();
    }
    ////
    ////
    private void handleSearch() {
        System.out.print("what word u want search?: ");
        String word = scanner.nextLine();
        boolean found = dictionary.search(word);
        System.out.println("word '" + word + "' in dict: " + found);
    }

    private void handlePrintItem() {
        System.out.print("what word u want print?: ");
        String word = scanner.nextLine();
        System.out.println(); 
        dictionary.printDictionaryItem(word);
    }

    private void handleAddItem() {
        System.out.println("adding word to dict ...");
        System.out.print("what word u want add?: ");
        String word = scanner.nextLine();
        System.out.print("what this word mean?: ");
        String definition = scanner.nextLine();
        dictionary.insert(word, definition);
        System.out.println("tq i know what this word mean now");
    }

    private void handleRemoveItem() {
        System.out.println("forgetting word from dict ...");
        System.out.print("what word u want me to forget?: ");
        String word = scanner.nextLine();
        dictionary.remove(word);
        System.out.println("tq i forgot what this word mean");
    }

    private void handlePrintAll() {
        System.out.println("printing everything i know ...\n");
        dictionary.printDictionary();
    }

    public static void main(String[] args) {
        DictionaryLookup app = new DictionaryLookup();
        app.run();
    }
}