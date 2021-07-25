package Type.ActualType;

import Type.AbstractType;
import Type.ActualType.Number.ActualNumber.TypeBinary;
import Type.ActualType.Number.ActualNumber.TypeFloat;
import Type.ActualType.Number.ActualNumber.TypeInt;
import Type.ActualType.Number.INumber;
import Type.IType;

public class TypeBool extends AbstractType {
    public TypeBool(boolean Bool) {
        super("", Bool, 0, 0, "0");
    }
    @Override
    public boolean equals(Object Obj){ //hacemos un override de la funcion equals
        if(Obj instanceof TypeBool){ //si es del tipo TypeBool
            var o = (TypeBool) Obj;
            if(this.getBool() == o.getBool()){ //y si coinciden en tener almacenar el mismo booleano
                return true; //son iguales
            }
            else {
                return false; //de otra forma no son iguales
            }
        }
        else{
            return false; //no son iguales
        }
    }
    //TAREA 2
    @Override
    public TypeBool calculate(){
        return new TypeBool(this.getBool());
    }
    public TypeBool get(){return this;}

    //Transformaciones
    @Override
    public String toString(){
        return String.valueOf(this.getBool());
    }

    public TypeBool toTypeBool(){ //esta transformacion retorna una copia del TypeBool
        return new TypeBool(this.getBool());
    }

    //Operaciones
    //OR
    @Override
    public IType or(IType Type){
        return Type.orBool(this);
    }

    @Override
    public INumber plusBinary(TypeBinary typeBinary) {
        return null;
    }

    @Override
    public INumber subsBinary(TypeBinary typeBinary) {
        return null;
    }

    @Override
    public INumber multBinary(TypeBinary typeBinary) {
        return null;
    }

    @Override
    public INumber divBinary(TypeBinary typeBinary) {
        return null;
    }

    @Override
    public INumber plusInt(TypeInt typeInt) {
        return null;
    }

    @Override
    public INumber subsInt(TypeInt negativeInt) {
        return null;
    }

    @Override
    public INumber multInt(TypeInt typeInt) {
        return null;
    }

    @Override
    public INumber divInt(TypeInt typeInt) {
        return null;
    }

    @Override
    public TypeFloat plusFloat(TypeFloat typeFloat) {
        return null;
    }

    @Override
    public TypeFloat subsFloat(TypeFloat negativeFloat) {
        return null;
    }

    @Override
    public TypeFloat multFloat(TypeFloat typeFloat) {
        return null;
    }

    @Override
    public TypeFloat divFloat(TypeFloat typeFloat) {
        return null;
    }

    @Override
    public TypeBool orBool(TypeBool Boolean){
        boolean result = this.getBool() || Boolean.getBool();
        return new TypeBool(result);
    }
    @Override
    public TypeBinary orBinary(TypeBinary Binary){
        boolean b = this.getBool();
        if(b==true){
            return new TypeBinary("1").orBinary(Binary);
        }
        else{return new TypeBinary("0").orBinary(Binary);}
    }
    //AND
    @Override
    public IType and(IType Type) {
        return Type.andBool(this);
    }

    @Override
    public TypeBool andBool(TypeBool B){
        boolean result = this.getBool() && B.getBool();
        return new TypeBool(result);
    }
    @Override
    public TypeBinary andBinary(TypeBinary Binary){
        boolean b = this.getBool();
        if(b==true){
            return new TypeBinary("1").andBinary(Binary);
        }
        else{return new TypeBinary("0").andBinary(Binary);}
    }
}
