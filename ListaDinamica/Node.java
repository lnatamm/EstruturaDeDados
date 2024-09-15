package ListaDinamica;

public class Node <Object>{

    private Object element;
    private Node <Object> next;

    public Node () {
        this.element = null;
        this.next = null;
    }
    public Node (Object element) {
        this.element = element;
        this.next = null;
    }
    public Object getElement(){
        return this.element;
    }
    public Node <Object> getNext(){
        return this.next;
    }
    public void setElement(Object element){
        this.element = element;
    }
    public void setNext(Node <Object> next){
        this.next = next;
    }

}