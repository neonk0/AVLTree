package AVLTree;

import java.util.Collection;
import java.util.Iterator;

public class AVLTree<T extends Comparable<T>> implements Collection<T>{

    private Node<T> root;
    private int size;

    public AVLTree(){
        this.root = null;
        size = 0;
    }

    private int height(Node<T> x, Node<T> y){
        if (x == null && y == null) return 0;
        else if (x == null) return y.getH();
        else if (y == null) return x.getH();
        else return Math.max(x.getH(), y.getH());
    }

    private int balance(Node<T> x, Node<T> y){
        if (x == null && y == null) return 0;
        else if (x == null) return -y.getH();
        else if (y == null) return x.getH();
        else return x.getH() - y.getH();
    }

    private Node<T> min(Node<T> current){
        if (current.getLeft() == null) return current;
        return min(current.getLeft());
    }

    private Node<T> max(Node<T> current){
        if (current.getRight() == null) return current;
        return min(current.getRight());
    }

    private Node<T> insert(Node<T> current,T data,Node<T> parent) {
        if (current == null){
            size++;
            return new Node<T>(data, parent);
        }
        int compareResult = data.compareTo(current.getData());
        if (compareResult > 0) {
            current.setRight(insert(current.getRight(), data, current));
            current.setH(height(current.getLeft(), current.getRight()) + 1);
        } else if (compareResult < 0) {
            current.setLeft(insert(current.getLeft(), data, current));
            current.setH(height(current.getLeft(), current.getRight()) + 1);
        } else current.setData(data);
        current.setBalance(balance(current.getLeft(), current.getRight()));
        if (current.getBalance() == -2)
            current = leftRotation(current);
        else if (current.getBalance() == 2)
            current = rightRotation(current);
        return current;
    }

    public void insert(T data){
        root = insert(root,data,null);
    }

    private Node<T> delete(Node<T> current,T data){
        if (current == null) return null;
        int compareResult = data.compareTo(current.getData());
        if (compareResult > 0)
            current.setRight(delete(current.getRight(),data));
        else if (compareResult < 0)
            current.setLeft(delete(current.getLeft(),data));
        else{
            if (current.getRight() == null &&current.getLeft() == null)
                current = null;
            else if (current.getRight() == null){
                current.getLeft().setParent(current.getParent());
                current = current.getLeft();
            } else if (current.getLeft() == null){
                current.getRight().setParent(current.getParent());
                current = current.getRight();
            } else {
                if (current.getRight().getLeft() == null){
                    current.getRight().setLeft(current.getLeft());
                    current.getRight().setParent(current.getParent());
                    current.getRight().setParent(current.getParent());
                    current.getLeft().setParent(current.getRight());
                    current = current.getRight();
                } else {
                    Node<T> result = min(current.getRight());
                    current.setData(result.getData());
                    delete(current.getRight(),current.getData());
                }
            }
        }
        if (current != null){
            current.setH(height(current.getLeft(),current.getRight())+1);
            current.setBalance(balance(current.getLeft(),current.getRight()));
            if (current.getBalance() == -2)
                current = leftRotation(current);
            else if (current.getBalance() == 2)
                current = rightRotation(current);
        }
        return current;
    }

    public void delete(T data){
        root = delete(root, data);
        size--;
    }

    private Node<T> leftRotation(Node<T> current){
        if (current.getRight().getRight() == null && current.getRight().getLeft() != null){
            current.setRight(rightRotation(current.getRight()));
            current = leftRotation(current);
        } else if (current.getRight().getLeft() == null ||
                current.getRight().getLeft().getH() <= current.getRight().getRight().getH()){
            Node<T> node = current.getRight();
            node.setParent(current.getParent());
            current.setRight(node.getLeft());
            if (current.getRight() != null)
                current.getRight().setParent(current);
            current.setH(height(current.getLeft(),current.getRight())+1);
            current.setParent(node);
            current.setBalance(balance(current.getLeft(),current.getRight()));
            node.setLeft(current);
            current = node;
            current.setBalance(balance(current.getLeft(),current.getRight()));
            current.setH(height(current.getLeft(),current.getRight())+1);
        } else{
            current.setRight(rightRotation(current.getRight()));
            current = leftRotation(current);
        }
        return current;
    }

    private Node<T> rightRotation(Node<T> current){
        if (current.getLeft().getRight() != null &&
                current.getLeft().getLeft() == null){
            current.setLeft(leftRotation(current.getLeft()));
            current = rightRotation(current);
        } else if (current.getLeft().getRight() == null ||
                current.getLeft().getRight().getH() <= current.getLeft().getLeft().getH()){
            Node<T> node = current.getLeft();
            node.setParent(current.getParent());
            current.setLeft(node.getRight());
            if (current.getLeft() != null)
                current.getLeft().setParent(current);
            current.setH(height(current.getLeft(),current.getRight())+1);
            current.setParent(node);
            current.setBalance(balance(current.getLeft(),current.getRight()));
            node.setRight(current);
            current = node;
            current.setBalance(balance(current.getLeft(),current.getRight()));
            current.setH(height(current.getLeft(),current.getRight())+1);
        } else {
            current.setLeft(leftRotation(current.getLeft()));
            current = rightRotation(current);
        }
        return current;
    }

    private T find(Node<T> current, T data){
        if (current == null) return null;
        int compareResult = data.compareTo(current.getData());
        if (compareResult == 0)
            return current.getData();
        else if (compareResult > 0)
            return find(current.getRight(),data);
        else return find(current.getLeft(),data);
    }

    public T find(T data){
        return find(root,data);
    }

    private void print(Node<T> current, int level){
        if (current != null){
            print(current.getRight(),level+1);
            for (int i = 0; i < level; ++i)
                System.out.print("\t");
            System.out.println(current.getData());
            print(current.getLeft(),level+1);
        }
    }

    public void print(){
        print(root,0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0 ? true : false;
    }

    @Override
    public boolean contains(Object o) {
        return find((T)o) != null ? true : false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size ; i++) {

        }
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator i = c.iterator();
        for (int k = 0; k < c.size(); ++k)
            if (find((T)i.next()) == null)
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator i = c.iterator();
        for (int k = 0; k < c.size(); ++k) {
            add((T)i.next());
            if (!add((T)i.next()))
                return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator i = c.iterator();
        for (int k = 0; k < c.size(); ++k) {
            remove((T)i.next());
            if (!remove((T)i.next()))
                return false;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }
}