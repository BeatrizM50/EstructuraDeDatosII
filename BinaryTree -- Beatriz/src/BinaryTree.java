public class BinaryTree{
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void insertar(int value) {
        root = insertarRecorrido(root, value);
    }

    private Node insertarRecorrido(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (value < root.getValue()) {
            root.left = insertarRecorrido(root.getLeft(), value);
        } else if (value > root.value) {
            root.right = insertarRecorrido(root.getRight(), value);
        }

        return root;

    }

    public boolean buscarValor(int value){
        return buscarRecorrido(root,value);
    }

    private boolean buscarRecorrido(Node root, int value){
        if (root==null){
            return false;
        }

        if (value==root.getValue()){
            return true;
        }

        if (value < root.getValue()){
            return buscarRecorrido(root.getLeft(),value);
        } else{
            return buscarRecorrido(root.getRight(),value);
        }
    }

    public void EntreOrder(){
        RecorridoEntreOrder(root);
    }
    private void RecorridoEntreOrder(Node raiz){
        if (raiz !=null){
            RecorridoEntreOrder(raiz.getLeft());
            System.out.println(raiz.getValue()+" ");
            RecorridoEntreOrder(raiz.getRight());

        }
    }

    public void PreOrder() {
        RecorridoPreOrder(root);
    }

    private void RecorridoPreOrder(Node raiz) {
        if (raiz != null) {
            System.out.print(raiz.getValue() + " -> ");
            RecorridoPreOrder(raiz.getLeft());
            RecorridoPreOrder(raiz.getRight());
        }
    }

    public void PostOrder() {
        RecorridoPostOrder(root);
    }

    private void RecorridoPostOrder(Node raiz) {
        if (raiz != null) {
            RecorridoPostOrder(raiz.getLeft());
            RecorridoPostOrder(raiz.getRight());
            System.out.print(raiz.getValue() + " -> ");
        }
    }
}
