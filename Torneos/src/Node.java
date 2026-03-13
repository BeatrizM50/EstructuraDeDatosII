public class Node {
        Partido value;
        Node left;
        Node right;

        public Node(Partido  value){
            this.value =value;
            this.left=null;
            this.right=null;
        }



        public Partido getValue() {
            return value;
        }

        public void setValue(Partido value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }



