package Factory.ActualType.Number;

import Type.ActualType.Number.ActualNumber.TypeBinary;


import java.util.Hashtable;

public class MakeBinary {
    private Hashtable<String, TypeBinary> hashtable = new Hashtable<>();

    public TypeBinary make(String id, TypeBinary Bin){
        TypeBinary in = hashtable.get(Bin);
        if(in != null){
            return in;
        }
        else{
            in = Bin;
            hashtable.put(id, in);
        }
        return in;
    }

    public TypeBinary simpleMake(TypeBinary Bin){
        TypeBinary in = hashtable.get(Bin);
        if(in != null){
            return in;
        }
        else{
            in = Bin;
            hashtable.put(Bin.toString(), in);
        }
        return in;
    }
    public int size(){
        return hashtable.size();
    }
}
