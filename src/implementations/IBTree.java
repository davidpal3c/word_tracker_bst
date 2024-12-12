/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import java.util.Stack;
/**
 *
 * @author mailt
 */
public class IBTree<E> implements Iterator<E> {
    
    private Stack<BSTreeNode<E>> stack;
//    private BSTreeNode<E> root;
    

    
    public IBTree(BSTreeNode<E> root) {
        stack = new Stack<>();
        pushLeftNodes(root);
    }
    
    private void pushLeftNodes(BSTreeNode<E> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
        }
    }
    
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree");
            }

        BSTreeNode<E> current = stack.pop();

        if (current.getRight() != null) {
            pushLeftNodes(current.getRight());
        }
        return current.getElement();
    }

    
    
}
