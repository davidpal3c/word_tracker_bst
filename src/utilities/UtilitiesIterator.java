/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author user
 */
public class UtilitiesIterator<E> implements Iterator {
    private final Iterator<E> wrappedIterator;

    public UtilitiesIterator(Iterator<E> iterator) {
        this.wrappedIterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return wrappedIterator.hasNext();
    }

    @Override
    public E next() {
        return wrappedIterator.next();
    }
}
