package HanzChristianJmartMH;
import java.util.*;

public class Algorithm {
    private Algorithm(){
    }

    public <T> int count (T[] array, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                c++;
            }
        }
        return c;
    }

    public <T> int count (Iterable<T> iterable, T value){
        int c = 0;
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                c++;
            }
        }
        return c;
    }

    public <T> int count (Iterator<T> iterator, T value){
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

    public <T> int count (T[] array, Predicate<T> pred){
        int c = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                c++;
            }
        }
        return c;
    }

    public <T> int count (Iterable<T> iterable, Predicate<T> pred){
        int c = 0;
        for (T element : iterable) {
            if (pred.predicate(element)) {
                c++;
            }
        }
        return c;
    }

    public <T> int count (Iterator<T> iterator, Predicate<T> pred){
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


    public <T> boolean exists (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists (Iterable<T> iterable, T value){
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }
    public <T> boolean exists (Iterator<T> iterator, T value){
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
    public <T> boolean exists (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists (Iterable<T> iterable, Predicate<T> pred){
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists (Iterator<T> iterator, Predicate<T> pred){
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

    public <T> T find (T[] array, T value){
        Predicate<T> pred = values -> (values == value);
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public <T> T find  (Iterable<T> iterable, T value){
        Predicate<T> pred = values -> (values == value);
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public  <T> T find (Iterator<T> iterator, T value){
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

    public  <T> T find  (T[] array, Predicate<T> pred){
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public <T> T find  (Iterable<T> iterable, Predicate<T> pred){
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public  <T> T find  (Iterator<T> iterator, Predicate<T> pred){
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

    public <T extends Comparable<? super T>> T max(T first, T  second)
    {
        if(first.compareTo(second) > 0)
        {
            return first;
        }
        return second;
    }

    public <T extends Comparable<? super T>> T min(T  first, T  second)
    {
        if(first.compareTo(second) < 0)
        {
            return first;
        }
        return second;
    }

}
