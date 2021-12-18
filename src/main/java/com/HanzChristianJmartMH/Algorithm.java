package com.HanzChristianJmartMH;
import java.util.*;

/**
 * Merupakan class Algorithm
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class Algorithm {
    private Algorithm(){
    }

    /**
     * merupakan method untuk mendapatkan elemen sesuai dengan nilai parameter yang diberikan
     * @param array
     * @param value
     * @param <T>
     * @return memberikan list elemen sesuai dengan parameter
     */
    public static <T> List<T> collect (T[] array, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * merupakan method untuk mendapatkan elemen sesuai dengan nilai parameter yang diberikan
     * @param iterable
     * @param value
     * @param <T>
     * @return memberikan list elemen sesuai dengan parameter
     */
    public static <T> List<T> collect (Iterable<T> iterable, T value){
        Predicate<T> pred = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * merupakan method untuk mendapatkan elemen sesuai dengan nilai parameter yang diberikan
     * @param iterator
     * @param value
     * @param <T>
     * @return memberikan list elemen sesuai dengan parameter
     */
    public static<T> List<T> collect(Iterator<T> iterator, T value) {
        List<T> list = new ArrayList<T>();
        while(iterator.hasNext()) {
            T t = iterator.next();
            if(t.equals(value)) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * merupakan method untuk mendapatkan elemen sesuai dengan predicate yang melakukan pengecekkan kondisi
     * @param array
     * @param pred
     * @param <T>
     * @return list sesuai dengan parameter predicatenya
     */
    public static <T> List<T> collect (T[] array, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * merupakan method untuk mendapatkan elemen sesuai dengan predicate yang melakukan pengecekkan kondisi
     * @param iterable
     * @param pred
     * @param <T>
     * @return list sesuai dengan parameter predicatenya
     */
    public static <T> List<T> collect (Iterable<T>iterable, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * merupakan method untuk mendapatkan elemen sesuai dengan predicate yang melakukan pengecekkan kondisi
     * @param iterator
     * @param pred
     * @param <T>
     * @return list sesuai dengan parameter predicatenya
     */
    public static <T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    /**
     * merupakan method untuk menghitung banyaknya elemen sesuai dengan parameter value arraynya
     * @param array
     * @param value
     * @param <T>
     * @return banyaknya elemen sesuai dengan parameter value
     */
    public static <T> int count (T[] array, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                c++;
            }
        }
        return c;
    }

    /**
     * merupakan method untuk menghitung banyaknya elemen sesuai parameter valuenya
     * @param iterable
     * @param value
     * @param <T>
     * @return banyaknya elemen sesuai dengan parameter value
     */
    public static <T> int count (Iterable<T> iterable, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                c++;
            }
        }
        return c;
    }

    /**
     * merupakan method untuk menghitung banyaknya elemen sesuai parameter valuenya
     * @param iterator
     * @param value
     * @param <T>
     * @return banyaknya elemen sesuai dengan parameter value
     */
    public static <T> int count (Iterator<T> iterator, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                c++;
            }
        }
        return c;
    }

    /**
     * merupakan method untuk menghitung banyaknya elemen sesuai kondisi predicate arraynya
     * @param array
     * @param pred
     * @param <T>
     * @return banyaknya elemen sesuai kondisi predicate
     */
    public static <T> int count (T[] array, Predicate<T> pred){
        int c = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                c++;
            }
        }
        return c;
    }

    /**
     * merupakan method untuk menghitung banyaknya elemen sesuai kondisi predicate arraynya
     * @param iterable
     * @param pred
     * @param <T>
     * @return banyaknya elemen sesuai kondisi predicate
     */
    public static <T> int count (Iterable<T> iterable, Predicate<T> pred){
        int c = 0;
        for (T element : iterable) {
            if (pred.predicate(element)) {
                c++;
            }
        }
        return c;
    }

    /**
     * merupakan method untuk menghitung banyaknya elemen sesuai kondisi predicate arraynya
     * @param iterator
     * @param pred
     * @param <T>
     * @return banyaknya elemen sesuai kondisi predicate
     */
    public static <T> int count (Iterator<T> iterator, Predicate<T> pred){
        int c = 0;
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                c++;
            }
        }
        return c;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param array
     * @param value
     * @param <T>
     * @return merupakan boolean true ketika elemennya sama sesuai parameter, false ketika tidak
     */
    public static <T> boolean exists (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterable
     * @param value
     * @param <T>
     * @return merupakan boolean true ketika elemennya sama sesuai parameter, false ketika tidak
     */
    public static <T> boolean exists (Iterable<T> iterable, T value){
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterator
     * @param value
     * @param <T>
     * @return merupakan boolean true ketika elemennya sama sesuai parameter, false ketika tidak
     */
    public static <T> boolean exists (Iterator<T> iterator, T value){
        Predicate<T> pred = values -> (values == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param array
     * @param pred
     * @param <T>
     * @return merupakan boolean true ketika elemennya sama sesuai parameter, false ketika tidak
     */
    public static <T> boolean exists (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterable
     * @param pred
     * @param <T>
     * @return merupakan boolean true ketika elemennya sama sesuai parameter, false ketika tidak
     */
    public static <T> boolean exists (Iterable<T> iterable, Predicate<T> pred){
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterator
     * @param pred
     * @param <T>
     * @return merupakan boolean true ketika elemennya sama sesuai parameter, false ketika tidak
     */
    public static <T> boolean exists (Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param array
     * @param value
     * @param <T>
     * @return berupa object elemennya sesuai dengan parameter yang diberikan
     */
    public static <T> T find (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterable
     * @param value
     * @param <T>
     * @return berupa object elemennya sesuai dengan parameter yang diberikan
     */
    public static <T> T find  (Iterable<T> iterable, T value){
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterator
     * @param value
     * @param <T>
     * @return berupa object elemennya sesuai dengan parameter yang diberikan
     */
    public static <T> T find (Iterator<T> iterator, T value){
        Predicate<T> pred = values -> (values == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param array
     * @param pred
     * @param <T>
     * @return berupa object elemennya sesuai dengan parameter yang diberikan
     */
    public static <T> T find  (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterable
     * @param pred
     * @param <T>
     * @return berupa object elemennya sesuai dengan parameter yang diberikan
     */
    public static <T> T find  (Iterable<T> iterable, Predicate<T> pred){
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    /**
     * merupakan method untuk mencari elemen sesuai dengan value parameter
     * @param iterator
     * @param pred
     * @param <T>
     * @return berupa object elemennya sesuai dengan parameter yang diberikan
     */
    public static <T> T find  (Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada 2 object parameter
     * @param first
     * @param second
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(T first, T  second)
    {
        if(first.compareTo(second) > 0)
        {
            return first;
        }
        return second;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada keseluruhan array
     * @param array
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(T[] array){
        T max = null;
        if (array.length == 1) return array[0];
        else {
            for (int i = 1; i < array.length; i++) {
                if (max == null) max = array[i];
                else if (array[i].compareTo(max) > 0) {
                    max = array[i];
                }
            }
            return max;
        }
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada keseluruhan array
     * @param iterable
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T max = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (max == null) max = element;
            else if (element.compareTo(max) > 0) max = element;
        }
        return max;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada keseluruhan array
     * @param iterator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator){
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(element.compareTo(max) > 0)
            {
                max = element;
            }
        }
        return max;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada 2 objek dari Comperator
     * @param first
     * @param second
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator){
        int Compare = comparator.compare(first, second);
        if(Compare > 0)
        {
            return first;
        }
        else
        {
            return second;
        }
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada keseluruhan object dari Comperator
     * @param array
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator){
        T max = array[0];
        for(int i = 0; i < array.length; i++)
        {
            int Compare = comparator.compare(array[i], max);
            if(Compare > 0)
            {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada keseluruhan object dari Comperator
     * @param iterable
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, max);
            if(Compare > 0)
            {
                max = element;
            }
        }
        return max;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai maksimum pada keseluruhan object dari Comperator
     * @param iterator
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih besar
     */
    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T max = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, max);
            if(Compare > 0)
            {
                max = element;
            }
        }
        return max;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada 2 object
     * @param first
     * @param second
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(T  first, T  second)
    {
        if(first.compareTo(second) < 0)
        {
            return first;
        }
        return second;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada keseluruhan object
     * @param array
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(T[] array){
        T min = null;
        if (array.length == 1) return array[0];
        else {
            for (int i = 1; i < array.length; i++) {
                if (min == null) min = array[i];
                else if (array[i].compareTo(min) < 0) {
                    min = array[i];
                }
            }
            return min;
        }
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada keseluruhan object
     * @param iterable
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T min = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) min = element;
        }
        return min;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada keseluruhan object
     * @param iterator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator){
        T min = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(element.compareTo(min) < 0)
            {
                min = element;
            }
        }
        return min;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada 2 object Comperator
     * @param first
     * @param second
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator){
        int Compare = comparator.compare(first, second);
        if(Compare < 0)
        {
            return first;
        }
        else
        {
            return second;
        }
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada keseluruhan object Comperator
     * @param array
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator){
        T min = array[0];
        for(int i = 0; i < array.length; i++)
        {
            int Compare = comparator.compare(array[i], min);
            if(Compare < 0)
            {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada keseluruhan object Comperator
     * @param iterable
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T min = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, min);
            if(Compare < 0)
            {
                min = element;
            }
        }
        return min;
    }

    /**
     * merupakan method yang digunakan untuk mencari object sesuai dengan nilai minimum pada keseluruhan object Comperator
     * @param iterator
     * @param comparator
     * @param <T>
     * @return mendapatkan object dengan nilai yang lebih kecil
     */
    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T min = iterator.next();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            int Compare = comparator.compare(element, min);
            if(Compare < 0)
            {
                min = element;
            }
        }
        return min;
    }

    /**
     * merupakan method untuk paginate dan filter sesuai dengan parameter predicate
     * @param array
     * @param page
     * @param pageSize
     * @param pred
     * @param <T>
     * @return list object sesuai paginate dan filter yang dimasukkan pada parameter
     */
    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        for(T obj : array)
        {
            if(pred.predicate(obj))
            {
                list.add(obj);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    /**
     * merupakan method untuk paginate dan filter sesuai dengan parameter predicate
     * @param iterable
     * @param page
     * @param pageSize
     * @param pred
     * @param <T>
     * @return list object Iterable sesuai paginate dan filter yang dimasukkan pada parameter
     */
    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                list.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    /**
     * merupakan method untuk paginate dan filter sesuai dengan parameter predicate
     * @param iterator
     * @param page
     * @param pageSize
     * @param pred
     * @param <T>
     * @return list object Iterator sesuai paginate dan filter yang dimasukkan pada parameter
     */
    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                list.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > list.size())
        {
            paginatedList = list.subList(startIndex, list.size());
        }
        else
        {
            paginatedList = list.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

}
