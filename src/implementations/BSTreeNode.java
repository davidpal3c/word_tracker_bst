/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import java.io.Serializable;
import utilities.BSTreeADT;
import utilities.Iterator;

/**
 *
 * @author mailt
 */
public class BSTreeNode<E> implements Serializable {
    
    private E element;
    private BSTreeNode<E> left;
    private BSTreeNode<E> right;

    public BSTreeNode(E value) {
        /*
        if (element == null) {
            throw new IllegalArgumentException("Node element cannot be null");
        }
        
        this.element = value;
        left=right=null;
        */
        this.element = value;
        left=right=null;
        
    }

    public E getElement() {
        return element;
    }

    public void setValue(E value) {
        this.element = element;
    }

    public BSTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    public BSTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }
    public boolean isLeaf(){
        return (left == null && right == null);
    }
    public boolean hasLeft(){
        return !(left==null);
    }
    public boolean hasRight(){
        return !(right==null);
    }

    @Override
    public String toString() {
        return element + ", left=" + left + ", right=" + right ;
    }
    
}
