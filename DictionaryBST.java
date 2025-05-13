// File: DictionaryBST.java

public class DictionaryBST {
    
    public class Node {
        public String word;       // 单词/短语
        public String definition; // 定义
        public Node left;   // 左子节点
        public Node right;  // 右子节点

       public Node(String word, String definition) {
         this.word = word;
         this.definition = definition;
         this.left = null;
         this.right = null;
    }
    }
    // Root of the BST
    public Node root;

    // Public constructor
    public DictionaryBST() {
        this.root = null;
    }

    // Public insert method
    public void insert(String word, String definition) {
        this.root = insertRec(this.root, word, definition);
    }

    public Node insertRec(Node node, String word,String definition) {
        // If the current node is null, create a new node
        // and return it
        if (node == null) return new Node(word, definition);
        int cmp = word.compareTo(node.word);

        if (cmp < 0) node.left = insertRec(node.left, word, definition);
        else if (cmp > 0) node.right = insertRec(node.right, word, definition);
        else node.definition = definition;
        return node;
    }

     public boolean search(String word) {
        return searchRec(this.root, word);
    }

    //searchseachseach
    private boolean searchRec(Node node, String s) {
        if (node == null) return false;
        int cmp = s.compareTo(node.word);
        if (cmp < 0) return searchRec(node.left, s);
        else if (cmp > 0) return searchRec(node.right, s);
        else return true; // Found
    }

    ///////////////////////
    // Public remove method
    public void remove(String s) {
        root = removeRec(root, s);
    }
    //remove remove remove
    private Node removeRec(Node node, String s) {
        if (node == null) return null;

        int cmp = s.compareTo(node.word);
        if (cmp < 0) node.left = removeRec(node.left, s);
        else if (cmp > 0) node.right = removeRec(node.right, s);
        else { // Found
            // Node with only one child or no child
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            // 有两个子节点: 获取中序后继 (右子树中的最小值)
            // 注意：这里需要同时更新 word 和 definition
            Node successor = findMinNode(node.right); // 获取整个后继节点
            node.word = successor.word;
            node.definition = successor.definition; // **新增：同时复制定义**

            // 删除中序后继
            node.right = removeRec(node.right, successor.word); // 使用后
        }
        return node;
    }
    ///////////////////////////


    //private method to find the minimum value in a subtree
    // Find minimum value in subtree
    private Node findMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }



    // Recursive in-order print
    public void printDictionary() {
        printDictionaryRecursive(this.root);
    }
    private void printDictionaryRecursive(Node node) {
        if (node != null) {
            printDictionaryRecursive(node.left);
            System.out.println(node.word);
            System.out.println(node.definition);
            System.out.println(); // 添加一个空行以便区分条目，根据示例输出调整
            printDictionaryRecursive(node.right);
        }
    }

    ////////////////////////
    public void printDictionaryItem(String s) {
        Node node = findNodeRec(this.root, s); // 新增一个辅助方法来查找并返回节点
        if (node != null) {
            System.out.println(node.word);
            System.out.println(node.definition);
        } else {
            System.out.println("词条 '" + s + "' 在词典中未找到。");
        }
    }
    private Node findNodeRec(Node current, String s) {
        if (current == null) {
            return null; // 未找到
        }
        int compareResult = s.compareTo(current.word);
        if (compareResult == 0) {
            return current; // 找到，返回当前节点
        }
        if (compareResult < 0) {
            return findNodeRec(current.left, s); // 在左子树中查找
        } else {
            return findNodeRec(current.right, s); // 在右子树中查找
        }
    }
    /////////////////////
//
}


  




