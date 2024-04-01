package Exercise2;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class TreeHeightPrinter {

    // helper method to compute the height of a subtree rooted at the given node
    private int postorderPrintAndComputeHeight(TreeNode node) {
        if (node == null) {
            return 0; // return 0 for null to indicate the absence of a subtree
        }
        
        // if both left and right are null it is a leaf node
        if (node.left == null && node.right == null) {
            System.out.println("Node Value: " + node.val + ", Subtree Height: 0");
            return 0;
        }
        
        int leftHeight = postorderPrintAndComputeHeight(node.left);
        int rightHeight = postorderPrintAndComputeHeight(node.right);
        
        int nodeHeight = 1 + Math.max(leftHeight, rightHeight);
        
        // print the nodes value and its subtree height
        System.out.println("Node Value: " + node.val + ", Subtree Height: " + nodeHeight);
        
        return nodeHeight;
    }
    
    // method to start the postorder traversal and height computation
    public void printAllNodeHeights(TreeNode root) {
        postorderPrintAndComputeHeight(root);
    }

    public static void main(String[] args) {
        // example usage
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);
        
        TreeHeightPrinter printer = new TreeHeightPrinter();
        printer.printAllNodeHeights(root);
    }
}
