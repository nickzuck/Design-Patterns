/*
You are given a Binary Tree and you are required to write a iterator for inorder, preorder and postorder traversal.
Customer can request the next traversal sequence for any of the traversal at any time.
 */
import java.util.Stack ;
import java.util.NoSuchElementException ;
import java.lang.Override ;
import java.lang.Exception ;

class TreeNode {
    int data ;
    TreeNode left, right ;

    TreeNode(int val){
        this.data = val ;
    }
}

interface Iterator {
    public boolean hasNext() ;
    public TreeNode next() ;
}


class PreOrderTraversal implements Iterator {
    Stack<TreeNode> stack = new Stack<>();

    PreOrderTraversal(TreeNode root){
        if(root != null){
            stack.push(root) ;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() ;
    }

    @Override
    public TreeNode next(){
        if (!hasNext()){
            throw new NoSuchElementException() ;
        }
        TreeNode node = stack.pop() ;
        if(node .right != null){
            stack.push(node .right) ;
        }
        if(node .left != null){
            stack.push(node .left) ;
        }
        return node ;
    }
}


class tree_traversal{
    public static void main(String[] args){

        TreeNode root = createTree() ;
        Iterator preOrderItr = new PreOrderTraversal(root) ;

        System.out.println("Doing pre order traversal") ;
        while(preOrderItr.hasNext()){
            System.out.println(preOrderItr.next().data) ;
        }
    }

    public static TreeNode createTree() {
        TreeNode root = new TreeNode(10) ;

        // left subtree
        root.left = new TreeNode(5) ;
        root.left.left = new TreeNode(3) ;
        root.left.right = new TreeNode(7) ;
        root.left.right.right = new TreeNode(9) ;

        //right subtree
        root.right = new TreeNode(20) ;
        root.right.right = new TreeNode(30) ;
        root.right.right.left = new TreeNode(25) ;

        return root ;
    }
}