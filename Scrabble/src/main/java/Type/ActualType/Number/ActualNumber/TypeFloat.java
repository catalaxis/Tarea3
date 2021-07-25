package Type.ActualType.Number.ActualNumber;

import Type.ActualType.Number.AbstractNumber;
import Type.ActualType.Number.INumber;
import Type.IType;

public class TypeFloat extends AbstractNumber {
    public TypeFloat(double Float){
        super(0,Float,"0");
    }
    @Override
    public boolean equals(Object Obj){
        if(Obj instanceof TypeFloat){
            var o = (TypeFloat) Obj;
            if(this.getFloat() == o.getFloat()){
                return true;
            }
            else{return false;}
        }
        else{return false;}
    }
    //TAREA 2
    @Override
    public IType calculate(){
        return new TypeFloat(this.toFloat());
    }
    public TypeFloat get(){return this;}

    //Tranformaciones
    @Override
    public String toString(){
        return String.valueOf(this.getFloat());
    }

    @Override
    public double toFloat(){
        return this.getFloat(); //para float es bastante simple
    }

    //Operaciones Numericas
    //Las operaciones consistenten en transformar el valor del tipo numero a Float
    //De esta forma se opera como cualquier tipo Float
    //Plus
    @Override
    public TypeFloat plus(IType Number){
        return Number.plusFloat(this);
    }
    @Override
    public TypeFloat plusBinary(TypeBinary Binary){
        double result = this.toFloat() + Binary.toFloat();
        return new TypeFloat(result);
    }
    @Override
    public TypeFloat plusInt(TypeInt Integer){
        return new TypeFloat(this.toFloat() + Integer.toFloat());
    }
    //Substract
    @Override
    public TypeFloat subs(IType Number){
        TypeFloat negativeFloat = new TypeFloat(this.toFloat());
        return Number.subsFloat(negativeFloat);
    }
    @Override
    public TypeFloat subsBinary(TypeBinary Binary){
        double result = -this.toFloat() + Binary.toFloat();
        return new TypeFloat(result);
    }
    @Override
    public TypeFloat subsInt(TypeInt Integer){
        return new TypeFloat(-this.toFloat() + Integer.toFloat());
    }
    //Multiplication
    @Override
    public TypeFloat mult(IType Number){
        return Number.multFloat(this);
    }
    @Override
    public TypeFloat multBinary(TypeBinary Binary){
        double bint = Binary.toFloat();
        return new TypeFloat(bint * this.getFloat());
    }
    @Override
    public TypeFloat multInt(TypeInt Integer){
        return new TypeFloat(Integer.toFloat()*this.getFloat());
    }
    //
    @Override
    public TypeFloat div(IType Number){
        return Number.divFloat(this);
    }
    @Override
    public TypeFloat divBinary(TypeBinary Binary){
        double bint = Binary.toFloat();
        return new TypeFloat(bint / this.getFloat());
    }
    @Override
    public TypeFloat divInt(TypeInt Integer){
        return new TypeFloat(Integer.toFloat()/this.getFloat());
    }



}
