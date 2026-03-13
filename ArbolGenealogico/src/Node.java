import java.util.LinkedList;
public class Node<E> {
        private E info;
        private LinkedList<Node<E>> children;

        public Node(E info){
            this.info=info;
            this.children=new LinkedList<>();
        }

        public Node(E info, LinkedList<Node<E>> children){
            this.info=info;
            this.children=children;
        }

        public E getInfo() {
            return info;
        }

        public void setInfo(E info) {
            this.info = info;
        }

        public LinkedList<Node<E>> getChildren() {
            return children;
        }

        public void setChildren(LinkedList<Node<E>> children) {
            this.children = children;
        }
    }

