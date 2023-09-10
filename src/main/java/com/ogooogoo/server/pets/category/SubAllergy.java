package com.ogooogoo.server.pets.category;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public enum SubAllergy implements List<SubAllergy> {

    계란, 뼈간식, 멸치, 우유, 치즈;

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
    public Iterator<SubAllergy> iterator() {
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
    public boolean add(SubAllergy subAllergy) {
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
    public boolean addAll(Collection<? extends SubAllergy> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends SubAllergy> c) {
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
    public SubAllergy get(int index) {
        return null;
    }

    @Override
    public SubAllergy set(int index, SubAllergy element) {
        return null;
    }

    @Override
    public void add(int index, SubAllergy element) {

    }

    @Override
    public SubAllergy remove(int index) {
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
    public ListIterator<SubAllergy> listIterator() {
        return null;
    }

    @Override
    public ListIterator<SubAllergy> listIterator(int index) {
        return null;
    }

    @Override
    public List<SubAllergy> subList(int fromIndex, int toIndex) {
        return null;
    }
}
