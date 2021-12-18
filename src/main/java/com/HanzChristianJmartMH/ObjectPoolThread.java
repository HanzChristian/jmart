package com.HanzChristianJmartMH;

import java.lang.Object;
import java.util.Vector;
import java.util.function.Function;

/**
 * Merupakan Class ObjectPoolThread untuk multi-threading
 * @author Hanz Christian
 * @version 18 Desember 2021
 */
public class ObjectPoolThread<T> extends Thread{
    private boolean exitSignal = false;
    private Vector<T> objectPool = new Vector<>();
    private Function<T, Boolean> routine;

    /**
     * Merupakan constructor objectpoolthread sesuai dengan parameter
     * @param name
     * @param routine
     */
    public ObjectPoolThread(String name, Function<T, Boolean> routine){
        super(name);
        this.routine = routine;
    }

    /**
     * Merupakan constructor objectpoolthread sesuai dengan parameter
     * @param routine
     */
    public ObjectPoolThread(Function<T, Boolean> routine){
        this.routine = routine;
    }

    /**
     * Merupakan method yang digunakan untuk mendapatkan banyaknya thread yang dijalankan
     * @return jumlah thread
     */
    public int size() {
        return objectPool.size();
    }

    /**
     * merupakan method yang digunakan untuk menambahkan threadnya
     * @param object
     */
    public synchronized void add(T object) {
        objectPool.add(object);
        super.notify();
    }

    /**
     * merupakan method untuk mengeluarkan dari thread
     */
    public synchronized void exit() {
        exitSignal = true;
        super.notify();
    }

    /**
     * Merupakan method untuk menjalankan thread
     */
    @Override
    public void run() {
        while(true){
            synchronized(this) {
                while(objectPool.isEmpty() && !exitSignal) {
                    try {
                        super.wait();
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(exitSignal) {
                    break;
                }
            }

            for(int i=0; i<objectPool.size();++i) {
                if(routine.apply(objectPool.get(i))) {
                    objectPool.set(i, null);
                }
            }

            objectPool.removeIf(obj -> obj==null);
        }
    }

}
