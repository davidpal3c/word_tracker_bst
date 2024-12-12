/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import java.util.ArrayList;
import utilities.BSTreeADT;
import utilities.Iterator;
import utilities.UtilitiesIterator;
//import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.io.Serializable;

/**
 *
 * @author mailt
 */
public class BSTree<E extends Comparable<E>> implements BSTreeADT<E>, Serializable {

    int size;
    int height;
    BSTreeNode<E> root;

    public BSTree() {
        size= 0 ;
        height=0;
        root = null;
    }

    public BSTree(BSTreeNode<E> root) {
        this.root = root;
        this.size = 1;
        this.height = 1;
    }
    
    @Override
    public BSTreeNode<E> getRoot() throws NullPointerException {
        if (root == null) {
            throw new NullPointerException("The tree is empty. There is no root node.");
        }
        return this.root;
    }

    @Override
    public int getHeight() {
//        return this.height;
        return getHeight(root);
    }
    
    public int getHeight(BSTreeNode<E> node) {
        if (node == null) 
            return 0;
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public void clear() {
        this.root=null;
        size= 0 ;
        height=0;
    }
    
    @Override
    public boolean contains(E entry) throws NullPointerException {
        if (entry == null) throw new NullPointerException("Entry cannot be null");
        return search(entry) != null;
    }

    
    @Override
    public BSTreeNode<E> search(E entry) throws NullPointerException {
        if (entry == null) throw new NullPointerException("Entry cannot be null");

//        BSTreeNode<E> current = root;
//        while (current != null) {
//            int comparison = entry.compareTo(current.getElement());
//            if (comparison == 0) return current;
//            current = comparison < 0 ? current.getLeft() : current.getRight();
//        }
//        return null;

        return searchNode(root, entry);
    }
        private BSTreeNode<E> searchNode(BSTreeNode<E> node, E entry) {       
        if (node == null || entry.equals(node.getElement())) return node;

        if (entry.compareTo(node.getElement()) < 0) 
            return searchNode(node.getLeft(), entry);
        
        return searchNode(node.getRight(), entry);
        
    }

        
    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null)
            throw new NullPointerException("New entry cannot be null");

        if (root == null) {
            root = new BSTreeNode<>(newEntry);
            size++;
            return true;
        }

        return addRecursive(root, newEntry);
    }

    private boolean addRecursive(BSTreeNode<E> node, E newEntry) {
        if (newEntry.compareTo(node.getElement()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } else {
                return addRecursive(node.getLeft(), newEntry);
            }
        } else if (newEntry.compareTo(node.getElement()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } else {
                return addRecursive(node.getRight(), newEntry);
            }
        }

    // when newEntry equals the node's element, it won't be added (no duplicates allowed)
    return false;
}
    
    @Override
    public BSTreeNode<E> removeMin() {
        if (isEmpty()) return null;

        if (root.getLeft() == null) {
            BSTreeNode<E> temp = root;
            root = root.getRight();
            size--;
            return temp;
        }

        return removeMin(root, null);
    }
    private BSTreeNode<E> removeMin(BSTreeNode<E> node, BSTreeNode<E> parent) {
        if (node.getLeft() == null) {
            if (parent != null) {
                parent.setLeft(node.getRight());
            }
            size--;
            return node;
        }
        return removeMin(node.getLeft(), node);
    }
    
    
    @Override
    public BSTreeNode<E> removeMax() {
        if (isEmpty()) return null;

        if (root.getRight() == null) {
            BSTreeNode<E> temp = root;
            root = root.getLeft();
            size--;
            return temp;
        }

        return removeMax(root, null);
    }        
    private BSTreeNode<E> removeMax(BSTreeNode<E> node, BSTreeNode<E> parent) {
        if (node.getRight() == null) {
            if (parent != null) {
                parent.setRight(node.getLeft());
            }
            size--;
            return node;
        }
        return removeMax(node.getRight(), node);
    }
    
    @Override
    public Iterator<E> inorderIterator() {
        
        List<E> result = new ArrayList<>();
        Stack<BSTreeNode<E>> stack = new Stack<>();
        BSTreeNode<E> current = root;

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            result.add(current.getElement());
            current = current.getRight();
        }
//        return new UtilitiesIterator<E>(result.iterator());
        return new IBTree<E>(root);
    }

    @Override
    public Iterator<E> preorderIterator() {
        
        List<E> result = new ArrayList<>();
        Stack<BSTreeNode<E>> stack = new Stack<>();

        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            BSTreeNode<E> current = stack.pop();
            result.add(current.getElement());

            if (current.getRight() != null) stack.push(current.getRight());
            if (current.getLeft() != null) stack.push(current.getLeft());
        }
        //return new UtilitiesIterator<E>((Iterator<E>) result.iterator());
        IBTree<E> iterator=new IBTree<>(root);
        return iterator;
    }

    @Override
    public Iterator<E> postorderIterator() {
        
        List<E> result = new ArrayList<>();
        Stack<BSTreeNode<E>> stack = new Stack<>();
        Stack<BSTreeNode<E>> output = new Stack<>();

        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            BSTreeNode<E> current = stack.pop();
            output.push(current);

            if (current.getLeft() != null) stack.push(current.getLeft());
            if (current.getRight() != null) stack.push(current.getRight());
        }
        while (!output.isEmpty()) {
            result.add(output.pop().getElement());
        }
        //return new UtilitiesIterator<E>((Iterator<E>) result.iterator());
        IBTree<E> iterator=new IBTree<>(root);
        return iterator;
        //return (Iterator<E>) result.iterator();
    }
    
    
//
//    @Override
//    public Iterator<E> inorderIterator() {
//        BSTree<E> bstree = new BSTree<E>();
//        inorderTraversal(root, bstree);
//        IBTree iterator = new IBTree(root);
//        return iterator;
//    }    
//    private void inorderTraversal(BSTreeNode<E> node, BSTree<E> bstree) {
//        if (node == null) return;
//        inorderTraversal(node.getLeft(), bstree);
//        bstree.add(node.getElement());
//        inorderTraversal(node.getRight(), bstree);
//    }
//    
//    
//    @Override
//    public Iterator<E> preorderIterator() {
//        List<E> result = new ArrayList<>();
//        preorderTraversal(root, result);
//        
//        return result.iterator();
//    }
//
//    private void preorderTraversal(BSTreeNode<E> node, List<E> result) {
//        if (node != null) {
//            result.add(node.getElement());
//            preorderTraversal(node.getLeft(), result);
//            preorderTraversal(node.getRight(), result);
//        }
//    }
//
//    @Override
//    public Iterator<E> postorderIterator() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public String toString() {
        return "BSTree{" + "size=" + size + ", height=" + height + ", root=" + root + '}';
    }
    
}
