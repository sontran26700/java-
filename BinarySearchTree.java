public class BinarySearchTree {
    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    // by inserting a value into the tree, we create a new tree, and return its reference
    private Node insert(int value, Node tree){
        if(tree == null){
            Node temp = new Node(value, null, null);
            return temp;
        }
        if(value < tree.value)
            tree.left = insert(value, tree.left);
        else if (value > tree.value)
            tree.right = insert(value, tree.right);
        return tree;
    }

    public void insert(int value){
        root = insert(value, root);
    }

    private void inOrderTraversal(Node tree){
        if (tree != null){
            inOrderTraversal(tree.left);
            System.out.println(tree.value);
            inOrderTraversal(tree.right);
        }
    }

    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    private void preOrderTraversal(Node tree){
        if (tree != null){
            System.out.println(tree.value);
            preOrderTraversal(tree.left);
            preOrderTraversal(tree.right);
        }
    }

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    // By deleting a Node which has the required value,
    // we create a new tree, and returns its reference
    private Node delete(int value, Node tree){
        if ( tree == null)
            System.out.println("Attempt to delete from an empty tree");
        if (value < tree.value)
            tree.left = delete(value, tree.left);
        else if (value > tree.value)
            tree.right = delete(value, tree.right);
        else{
            // tree does not have left subtree, we just return the right subtree
            if (tree.left == null)
                return tree.right;
            else if (tree.right == null)
                return tree.left;
            else{
                // tree has two children
                Node rm = rightmost(tree.left);
                tree.value = rm.value;
                tree.left = delete(rm.value, tree.left);
            }
        }
        return tree;
    }

    public void delete(int value){
        root = delete(value, root);
    }

    private Node rightmost(Node tree){
        Node temp = tree;
        while (temp.right != null)
            temp = temp.right;
        return temp;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        int[] values = new int[]{1, 5, 2, 7, 4};
        for (int i: values){
            tree.insert(i);
        }
        tree.inOrderTraversal();
        System.out.println("----");
        //tree.preOrderTraversal();

        tree.delete(5);
        tree.inOrderTraversal();

    }
}
