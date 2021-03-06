import AVLTree.AVLTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class AVLTreeTest {

    private AVLTree<Integer> avl = new AVLTree<Integer>();
    private LinkedList<Integer> list = new LinkedList<>();

    @Test
    public void insert(){
        avl.insert(5);
        avl.insert(10);
        avl.insert(-2);
        avl.insert(7);
        avl.insert(0);
        avl.insert(3);
        assert (avl.isBalanced());
        assert (avl.contains(5));
        assert (avl.contains(10));
        assert (avl.contains(-2));
        assert (avl.contains(7));
        assert (avl.contains(0));
        assert (avl.contains(3));
    }

    @Test
    public void checkBalance(){
        avl.clear();
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        assert (avl.isBalanced());
    }

    @Test
    public void delete(){
        avl.delete(10);
        assert (!avl.contains(10));
        avl.delete(0);
        assert (!avl.contains(0));
        assert (avl.isBalanced());
    }
    
    @Test
    public void checkBalance(){
        avl.clear();
        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);
        assert (avl.isBalanced());
    }

    @Test
    public void min(){
        avl.insert(5);
        avl.insert(10);
        avl.insert(-2);
        avl.insert(7);
        avl.insert(0);
        avl.insert(3);
        assert (avl.isBalanced());
        assert (avl.min() == -2);
    }

    @Test
    public void max(){
        avl.insert(5);
        avl.insert(10);
        avl.insert(-2);
        avl.insert(7);
        avl.insert(0);
        avl.insert(3);
        assert (avl.isBalanced());
        assert (avl.max() == 10);
    }

    @Test
    public void size(){
        avl.insert(11);
        avl.insert(12);
        avl.insert(13);
        avl.insert(-14);
        assert (avl.size() == 4);
        avl.delete(5);
        avl.delete(3);
        assert (avl.size() == 2);
        assert (avl.isBalanced());
    }

    @Test
    public void isEmpty(){
        assert (avl.isEmpty());
        avl.insert(10);
        avl.insert(11);
        avl.insert(12);
        avl.insert(13);
        assert (avl.isBalanced());
        assert (!avl.isEmpty());
        avl.delete(11);
        avl.delete(13);
        avl.delete(12);
        avl.delete(10);
        assert (avl.isBalanced());
        assert (avl.isEmpty());
    }

    @Test
    public void contains(){
        avl.insert(5);
        assert (avl.isBalanced());
        assert (avl.contains(5));
        avl.add(10);
        assert (avl.isBalanced());
        assert (avl.contains(10));
        avl.insert(11);
        assert (avl.isBalanced());
        assert (avl.contains(11));
        avl.add(-4);
        assert (avl.isBalanced());
        assert (avl.contains(-4));
    }

    @Test
    public void toArray(){
        avl.insert(5);
        avl.insert(10);
        avl.insert(-2);
        avl.insert(7);
        avl.insert(0);
        avl.insert(3);
        assert (avl.isBalanced());
        Object[] answer = avl.toArray();
        Object[] rightAnswer = {-2,0,3,5,7,10};
        Assert.assertArrayEquals(answer,rightAnswer);
    }

    @Test
    public void add(){
        assert (avl.add(5));
        assert (avl.add(10));
        assert (avl.add(19));
        assert (avl.add(50));
        assert (!avl.add(5));
        assert (!avl.add(19));
        assert (avl.isBalanced());
    }

    @Test
    public void remove(){
        avl.insert(5);
        avl.insert(6);
        avl.insert(0);
        avl.insert(-4);
        avl.insert(-6);
        assert (avl.isBalanced());
        assert (avl.remove(6));
        assert (avl.remove(-6));
        assert (avl.remove(0));
        assert (avl.remove(-4));
        assert (!avl.remove(-4));
    }

    @Test
    public void containsAll(){
        avl.insert(5);
        avl.insert(6);
        avl.insert(0);
        avl.insert(-4);
        avl.insert(-6);
        avl.insert(12);
        assert (avl.isBalanced());
        list.add(6);
        list.add(12);
        list.add(5);
        assert (avl.containsAll(list));
    }

    @Test
    public void addAll(){
        avl.insert(5);
        avl.insert(6);
        avl.insert(0);
        avl.insert(-4);
        avl.insert(-6);
        avl.insert(12);
        assert (avl.isBalanced());
        list.add(-1);
        list.add(-2);
        list.add(-3);
        assert (avl.addAll(list));
        assert (avl.contains(-1));
        assert (avl.contains(-2));
        assert (avl.contains(-3));
    }

    @Test
    public void removeAll(){
        avl.insert(5);
        avl.insert(6);
        avl.insert(0);
        avl.insert(-4);
        avl.insert(-6);
        avl.insert(12);
        assert (avl.isBalanced());
        list.add(6);
        list.add(-4);
        list.add(5);
        assert (avl.removeAll(list));
        assert (!avl.contains(6));
        assert (!avl.contains(-4));
        assert (!avl.contains(5));
    }

    @Test
    public void retainAll(){
        avl.insert(1);
        avl.insert(2);
        avl.insert(5);
        avl.insert(6);
        avl.insert(0);
        avl.insert(-4);
        avl.insert(-6);
        avl.insert(12);
        avl.insert(-1);
        assert (avl.isBalanced());
        list.add(2);
        list.add(0);
        list.add(12);
        list.add(-1);
        assert (avl.retainAll(list));
        assert (avl.contains(2));
        assert (avl.contains(0));
        assert (avl.contains(-1));
        assert (avl.contains(12));
        assert (!avl.contains(5));
        assert (!avl.contains(6));
    }

    @Test
    public void clear(){
        avl.clear();
        assert (avl.isBalanced());
        assert (avl.size() == 0);
        avl.insert(1);
        avl.insert(2);
        avl.insert(5);
        avl.insert(6);
        avl.insert(0);
        avl.insert(-4);
        avl.insert(-6);
        avl.insert(12);
        avl.insert(-1);
        assert (avl.isBalanced());
        avl.clear();
        assert (avl.size() == 0);
    }
}
