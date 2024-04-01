package Exercise1;

// class that represents node in tree
class TreeNode {
    int val; 
    TreeNode left; 
    TreeNode right; 
    TreeNode parent; 
    
    // sets value 
    TreeNode(int x) {
        val = x;
    }
}

// contains methods to find the next node in different tree traversals
public class BinaryTreeTraversal {

    // finds the next node in a preorder traversal from given node p
	public TreeNode preorderNext(TreeNode p) {
        // if theres a left child thats the next node in the traversal
        if (p.left != null) {
            return p.left;
        }
        
        // if theres no left child but there is a right child then thats the next ndoe
        else if (p.right != null) {
            return p.right;
        } 
        // if node is a leaf go back up the tree to find the next node
        else {
            TreeNode parent = p.parent;
            // cont moving up the tree until we find a node that is the left child of its parent
            // and the parent has a right child That right child is the next node.
            while (parent != null && (parent.right == p || parent.right == null)) {
                p = parent;
                parent = parent.parent;
            }
            // if the parent is null, p was the last node in preorder so return null
            return parent == null ? null : parent.right;
        }
    }

    // finds the next node in an inorder traversal from a given node p
    public TreeNode inorderNext(TreeNode p) {
        // if there is a right child the next node is the leftmost node in the right subtree
        if (p.right != null) {
            TreeNode next = p.right;
            while (next.left != null) {
                next = next.left;
            }
            return next;
        } 
        // if there is no right child move up the tree until we find a node that is not
        // the right child of its parent. The parent is the next node in inorder
        else {
            TreeNode parent = p.parent;
            while (parent != null && p == parent.right) {
                p = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    // finds the next node in a postorder traversal from a given node p
    public TreeNode postorderNext(TreeNode p) {
        // If the node is the root, there is no next node in postorder.
        if (p.parent == null) return null;
        // ff p is a right child or a left child with no sibling the parent is the next node
        if (p.parent.right == p || p.parent.right == null) {
            return p.parent;
        } 
        // if p is a left child with a right sibling, the next node is the leftmost leaf
        // in the right siblings subtree
        else {
            return findLeftmost(p.parent.right);
        }
    }

    // helper method to find the leftmost leaf of a given subtree
    private TreeNode findLeftmost(TreeNode node) {
        while (node.left != null || node.right != null) {
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    public static void main(String[] args) {
        // example
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.parent = root;
        root.right.parent = root;

        BinaryTreeTraversal tree = new BinaryTreeTraversal();

        // Assuming we want to find the next node in preorder for node with value 2
        TreeNode preorderNextNode = tree.preorderNext(root.left);
        System.out.println("Preorder next of 2: " + (preorderNextNode != null ? preorderNextNode.val : "null"));

        // Assuming we want to find the next node in inorder for node with value 2
        TreeNode inorderNextNode = tree.inorderNext(root.left);
        System.out.println("Inorder next of 2: " + (inorderNextNode != null ? inorderNextNode.val : "null"));

        // Assuming we want to find the next node in postorder for node with value 2
        TreeNode postorderNextNode = tree.postorderNext(root.left);
        System.out.println("Postorder next of 2: " + (postorderNextNode != null ? postorderNextNode.val : "null"));
    }
}
