package com.ogooogoo.server.pets.category;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public enum MainAllergy implements List<MainAllergy> {

    소, 돼지,양, 닭, 칠면조, 오리, 연어, 참치, 삼치, 캥거루, 흑염소, 사슴;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<MainAllergy> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(MainAllergy mainAllergy) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends MainAllergy> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends MainAllergy> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public MainAllergy get(int index) {
        return null;
    }

    @Override
    public MainAllergy set(int index, MainAllergy element) {
        return null;
    }

    @Override
    public void add(int index, MainAllergy element) {

    }

    @Override
    public MainAllergy remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<MainAllergy> listIterator() {
        return null;
    }

    @Override
    public ListIterator<MainAllergy> listIterator(int index) {
        return null;
    }

    @Override
    public List<MainAllergy> subList(int fromIndex, int toIndex) {
        return null;
    }
}
