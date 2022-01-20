package com.jeremyantoine.speedjumper.utilitaire;

import com.jeremyantoine.speedjumper.observateurs.Sujet;

import java.util.*;

/**
 * Liste Observable
 * @param <E>
 */
public class ListeObservable<E> extends Sujet implements List<E> {
    private List<E> liste;

    public ListeObservable(List<E> liste) {
        this.liste = liste;
    }

    @Override
    public int size() {
        return liste.size();
    }

    @Override
    public boolean isEmpty() {
        return liste.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return liste.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return liste.iterator();
    }

    @Override
    public Object[] toArray() {
        return liste.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        notifier();
        return liste.add(e);
    }

    @Override
    public boolean remove(Object o) {
        notifier();
        return liste.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return liste.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        notifier();
        return liste.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        notifier();
        return liste.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        notifier();
        return liste.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        notifier();
        return liste.retainAll(c);
    }

    @Override
    public void clear() {
        notifier();
        liste.clear();
    }

    @Override
    public E get(int index) {
        return liste.get(index);
    }

    @Override
    public E set(int index, E element) {
        return liste.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        notifier();
        liste.add(index, element);
    }

    @Override
    public E remove(int index) {
        notifier();
        return liste.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return liste.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return liste.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return liste.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return liste.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return liste.subList(fromIndex, toIndex);
    }
}
