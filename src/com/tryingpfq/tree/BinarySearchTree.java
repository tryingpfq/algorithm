package tryingpfq.tree;

public class BinarySearchTree {
    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            }else {
                return p;
            }
        }
        return null;
    }

    public void test(){
        boolean flag;
        if (true) {
            System.out.println();
        }
        if (false == true) {

        }
    }

    public void insert(int data) {
        Node node = new Node(data);
        if (tree == null) {
            tree = node;
            return;
        }

        Node p = node;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = node;
                    return;
                }
                p = p.right;
            }else{
                if (p.left == null) {
                    p.left = node;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data) {
        Node p = tree;
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (data < p.data) {
                p = p.left;
            }else{
                p = p.right;
            }
        }

        if (p == null) {
            return;
        }

        if (p.left != null && p.right != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minPP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        Node child;
        if (p.left != null) {

        }
    }

    public static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
