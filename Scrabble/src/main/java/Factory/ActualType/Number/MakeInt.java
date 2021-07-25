package Factory.ActualType.Number;

import Type.ActualType.Number.ActualNumber.TypeInt;

import java.util.Hashtable;

public class MakeInt {
    private Hashtable<String, TypeInt> hashtable = new Hashtable<>();

    public TypeInt make(String id, TypeInt Integer){
        TypeInt in = hashtable.get(Integer);
        if(in != null){
            return in;
        }
        else{
            in = Integer;
            hashtable.put(id, in);
        }
        return in;
    }

    public TypeInt simpleMake(TypeInt Integer){
        TypeInt in = hashtable.get(Integer);
        if(in != null){
            return in;
        }
        else{
            in = Integer;
            hashtable.put(Integer.toString(), in);
        }
        return in;
    }
    public int size(){
        return hashtable.size();
    }
}
