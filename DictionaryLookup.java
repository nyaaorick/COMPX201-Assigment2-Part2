// DictionaryLookup.java
import java.io.IOException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets; // 推荐保留，确保编码正确性
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
        System.out.println("        (1) 在词典中搜索单词/短语");
        System.out.println("        (2) 打印给定单词/短语及其定义");
        System.out.println("        (3) 向词典中添加单词/短语和定义");
        System.out.println("        (4) 从词典中删除单词/短语和定义");
        System.out.println("        (5) 按字母顺序打印所有单词/短语及其定义");
        System.out.println("        (6) exit");
        System.out.println("------------------------------------------");
        System.out.print("\n请输入一个1到6之间的数字: ");
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
                        System.out.println("感谢使用，再见！");
                        break;
                    default:
                        System.out.println("无效的输入，请输入1到6之间的数字。");
                }
            } catch (NumberFormatException e) {
                System.out.println("无效的输入，请输入一个数字。");
                choice = 0; // 重置choice以继续循环
            }

            if (choice != 6) {
                System.out.println("\n按任意键继续...\n");
                scanner.nextLine(); // 等待用户按键
            }

        } while (choice != 6);
        scanner.close();
    }
    ////
    ////
    private void handleSearch() {
        System.out.print("请输入要搜索的单词/短语: ");
        String word = scanner.nextLine();
        boolean found = dictionary.search(word);
        System.out.println("单词/短语 '" + word + "' 存在于词典中: " + found);
    }

    private void handlePrintItem() {
        System.out.print("请输入要打印的单词/短语: ");
        String word = scanner.nextLine();
        System.out.println(); // 换行以匹配示例输出
        dictionary.printDictionaryItem(word);
    }

    private void handleAddItem() {
        System.out.println("正在向词典添加条目 ...");
        System.out.print("请输入单词/短语: ");
        String word = scanner.nextLine();
        System.out.print("谢谢，现在请输入定义: ");
        String definition = scanner.nextLine();
        dictionary.insert(word, definition); // 假设BST的insert能处理重复插入（例如，不插入或更新）
        System.out.println("谢谢，您的条目已添加到词典中");
    }

    private void handleRemoveItem() {
        System.out.println("正在从词典删除条目 ...");
        System.out.print("请输入要删除的单词/短语: ");
        String word = scanner.nextLine();
        // 你可能需要先检查词条是否存在，再尝试删除，或者让remove方法处理找不到的情况
        dictionary.remove(word);
        System.out.println("谢谢，该条目已被删除 (如果存在的话)");
    }

    private void handlePrintAll() {
        System.out.println("正在打印完整词典 ...\n");
        dictionary.printDictionary();
    }

    public static void main(String[] args) {
        DictionaryLookup app = new DictionaryLookup();
        app.run();
    }
}