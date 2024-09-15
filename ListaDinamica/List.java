package ListaDinamica;

public class List {
    private Node<Object> head;
    private int size;

    public List(){
        head = null;
        size = 0;
    }

    public Node<Object> getHead() {
        return head;
    }

    public void setHead(Node<Object> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(Object element){
        Node<Object> node = new Node<>(element);
        if(isEmpty()){
            head = node;
            return true;
        }
        Node<Object> last = head;
        while(last.getNext() != null){
            last = last.getNext();
        }
        last.setNext(node);
        return true;
    }

}
