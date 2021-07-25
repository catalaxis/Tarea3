package Factory.ActualType;

import Type.ActualType.TypeBool;
import Type.ActualType.TypeString;

import java.util.Hashtable;

public class MakeBool {
    private Hashtable<String, TypeBool> hashtable = new Hashtable<>();

    public TypeBool make(String id, TypeBool Bool){
        TypeBool in = hashtable.get(Bool);
        if(in != null){
            return in;
        }
        else{
            in = Bool;
            hashtable.put(id, in);
        }
        return in;
    }

    public TypeBool simpleMake(TypeBool Bool){
        TypeBool in = hashtable.get(Bool);
        if(in != null){
            return in;
        }
        else{
            in = Bool;
            hashtable.put(Bool.toString(), in);
        }
        return in;
    }
    public int size(){
        return hashtable.size();
    }
}
