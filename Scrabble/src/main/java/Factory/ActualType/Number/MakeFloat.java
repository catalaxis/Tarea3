package Factory.ActualType.Number;

import Type.ActualType.Number.ActualNumber.TypeFloat;

import java.util.Hashtable;

public class MakeFloat {
    private Hashtable<String, TypeFloat> hashtable = new Hashtable<>();

    public TypeFloat make(String id, TypeFloat Float){
        TypeFloat in = hashtable.get(Float);
        if(in != null){
            return in;
        }
        else{
            in = Float;
            hashtable.put(id, in);
        }
        return in;
    }

    public TypeFloat simpleMake(TypeFloat Float){
        TypeFloat in = hashtable.get(Float);
        if(in != null){
            return in;
        }
        else{
            in = Float;
            hashtable.put(Float.toString(), in);
        }
        return in;
    }
    public int size(){
        return hashtable.size();
    }
}
