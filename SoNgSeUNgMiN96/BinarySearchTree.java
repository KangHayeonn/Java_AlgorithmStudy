/*
    이진탐색트리입니다. 제네릭을 이용하여 타입을 여러가지로 받을 수 있습니다.
 */

class Node<E>{      //바이너리 서치트리의 노드구조입니다.
    E data;
    Node<E> left;
    Node<E> right;
    Node<E> up;

    public  Node(E data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.up = null;
    }

    public  Node(){
        this.data = null;
        this.left = null;
        this.right = null;
        this.up = null;
    }
}

public class BinarySearchTree<E> {

    Node<E> Root;   //Node의 루트.
    Node<E> Cursor;

    public BinarySearchTree(E data) {
        Root = new Node<E>(data);
        Root.data = data;
        Root.left = null;
        Root.right = null;
        Root.up = null;
    }

    public BinarySearchTree() {
        Root = new Node<E>();
        Root.left = null;
        Root.right = null;
        Root.up = null;
    }

    public void insertTree(E Data) {
        Cursor = Root;

        if (Cursor.data == null) {
            Cursor.data = Data;
            return;
        }

        while (true) {
            if (Integer.parseInt(Cursor.data.toString()) > Integer.parseInt(Data.toString())) {        //새로들어온게 적을때 레프트
                if (Cursor.left == null) {
                    Cursor.left = new Node<E>(Data);
                    Cursor.left.up = Cursor;
                    break;
                } else {
                    Cursor = Cursor.left;
                }
            } else {
                if (Cursor.right == null) {
                    Cursor.right = new Node<E>(Data);
                    Cursor.right.up = Cursor;
                    break;
                } else
                    Cursor = Cursor.right;
            }
        }
    }

    public E deleteMax() {
        E temp = null;
        Cursor = Root;

        if (Cursor.data == null)
            return null;

        if (Cursor.right == null) {
            if (Cursor.left == null) {     //루트의 자식이 하나도 없는경우.
                temp = Cursor.data;
                Cursor.data = null;
            } else {       //루트가 왼쪽노드만 있는경우
                temp = Cursor.data;
                Root = Root.left;
                Root.up =null;
            }
            return  temp;
        }

        while (true) {
            if (Cursor.right == null) {
                if (Cursor.left == null) {    //해당노드의 자식이없다면,
                    temp = Cursor.data;
                    Cursor.up.right = null;
                } else {  //왼쪽 자식이 있다면,
                    temp = Cursor.data;
                    Cursor.left.up = Cursor.up;
                    Cursor.up.right = Cursor.left;
                }
                return temp;
            } else
                Cursor = Cursor.right;
        }
    }

    public E viewMax() {
        Cursor = Root;
        while (true) {
            if (Cursor.right == null) {
                return Cursor.data;
            } else
                Cursor = Cursor.right;
        }
    }

    public E viewMin() {
        Cursor = Root;
        while (true) {
            if (Cursor.left == null) {
                return Cursor.data;
            } else
                Cursor = Cursor.left;
        }
    }

    public E deleteMin() {

        E temp = null;
        Cursor = Root;

        if (Cursor.data == null)
            return null;

        if (Cursor.left == null) {
            if (Cursor.right == null) {     //루트의 자식이 하나도 없는경우.
                temp = Cursor.data;
                Cursor.data = null;
            } else {       //루트가 오른쪽노드만 있는경우
                temp = Cursor.data;
                Root = Root.right;
                Root.up =null;
            }
            return temp;
        }

        while (true) {
            if (Cursor.left == null) {
                if (Cursor.right == null) {    //해당노드의 자식이없다면,
                    temp = Cursor.data;
                    Cursor.up.left = null;
                } else {  //오른쪽 자식이 있다면,
                    temp = Cursor.data;
                    Cursor.right.up = Cursor.up;
                    Cursor.up.left = Cursor.right;
                }
                return temp;
            } else
                Cursor = Cursor.left;
        }
    }
}
