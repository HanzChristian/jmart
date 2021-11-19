package com.HanzChristianJmartMH;

import java.util.HashMap;
import java.util.Map;

public class Serializable implements Comparable<Serializable>{

    private static Map<Class<?>,Integer> mapCounter = new HashMap();


    public final int id;
    
    protected Serializable()
    {
        Class gc = getClass();
        if(mapCounter.get(gc) == null){
            mapCounter.put(gc,0);
        }
        else{
            mapCounter.put(gc,mapCounter.get(gc) + 1);
        }
        this.id = mapCounter.get(gc);
    }

    @Override
    public int compareTo(Serializable other) {
        if(id == other.id){
            return 1;
        }
        else{
            return 0;
        }
    }

    public static <T extends Serializable> int getClosingId (Class<T> clazz){
        return mapCounter.get(clazz);
    }

    public static <T extends Serializable> int setClosingId (Class<T> clazz, int id){
        return mapCounter.put(clazz,id);
    }

    public boolean equals(Object other){
        if(other instanceof Serializable){
            Serializable UcObj = (Serializable) other;
            if(this.id == UcObj.id){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean equals(Serializable other){
        if(this.id == other.id){
            return true;
        }
        else{
            return false;
        }
    }
}
    


