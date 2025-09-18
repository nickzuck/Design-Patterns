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
            stack.push(node.right) ;
        }
        if(node .left != null){
            stack.push(node.left) ;
        }
        return node ;
    }
}

class PostOrderTraversal implements Iterator {
    Stack<TreeNode> stack = new Stack<>() ;

    PostOrderTraversal(TreeNode root){
        if(root != null){
            stack.push(root) ;
            stack.push(root) ;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.empty() ;
    }

    @Override
    public TreeNode next() {
        if(!hasNext()){
            throw new NoSuchElementException() ;
        }

        TreeNode node = stack.pop() ;
        if(!stack.empty() && stack.peek() == node){
            // We are seeing this node for the first time -> push children
            if(node.right != null){
                stack.push(node.right) ;
                stack.push(node.right) ;
            }
            if(node.left != null){
                stack.push(node.left) ;
                stack.push(node.left) ;
            }
            return next() ; // continue untl a node is ready to be returned
        } else {
            return node;
        }
    }
}

class InorderTraversal implements Iterator{
    Stack<TreeNode> stack = new Stack<>() ;
    TreeNode current = null ;

    InorderTraversal(TreeNode root){
        this.current = root ;
    }

    @Override
    public boolean hasNext() {
        return !stack.empty() || current != null;
    }

    @Override
    public TreeNode next(){
        if (!hasNext()) {
            throw new NoSuchElementException() ;
        }

        while(current != null){
            stack.push(current) ;
            current = current.left ;
        }

        TreeNode node = stack.pop() ;

        // Move pointer to the right subtree
        current = node.right ;

        return node ;
    }
}


class tree_traversal{
    public static void main(String[] args){

        TreeNode root = createTree() ;
        Iterator preOrderItr = new PreOrderTraversal(root) ;

        System.out.println("Doing pre order traversal") ;
        while(preOrderItr.hasNext()){
            System.out.printf("%d ", preOrderItr.next().data) ;
        }

        System.out.println("\nDoing post order traversal") ;
        Iterator postOrderItr = new PostOrderTraversal(root) ;
        while(postOrderItr.hasNext()){
            System.out.printf("%d ", postOrderItr.next().data) ;
        }

        System.out.println("\nDoing interorder traversal") ;
        Iterator inorderItr = new InorderTraversal(root);

        while(inorderItr.hasNext()){
            System.out.printf("%d ", inorderItr.next().data)  ;
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