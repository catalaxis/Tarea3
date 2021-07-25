package Type.ActualType.Number.ActualNumber;

import Type.ActualType.Number.AbstractNumber;
import Type.ActualType.Number.INumber;
import Type.IType;


public class TypeInt extends AbstractNumber {
    public TypeInt(int Integer) {
        super(Integer, 0, "");
    }
    @Override
    public boolean equals(Object Object){
        if (Object instanceof TypeInt){
            var o = (TypeInt) Object;
            return o.getInt() == this.getInt();
        }
        else{return false;}
    }
    //
    @Override
    public IType calculate(){
        return new TypeInt(this.getInt());
    }
    public TypeInt get(){return this;}
    //Transformaciones
    public String toString(){
        return String.valueOf(this.getInt());
    }
    @Override
    public double toFloat(){
        return Double.valueOf(this.getInt());
    }


    public TypeInt toTypeInt(){
        return new TypeInt(this.getInt());
    }

    public TypeBinary toTypeBinary(){
        TypeBinary newBinary = new TypeBinary(intToBinary(this.getInt()));
        return newBinary;
    }

    //Operaciones Numericas
    //Es importante notar que las operaciones son analogas a lo largo de los tipo numeros
    //Revisar las anotaciones particulares en TypeBinary
    //Se operan mediante la transformacion auxiliar a int
    //Adicion

    @Override
    public INumber plus(IType Number){
        return Number.plusInt(this);
    }
    @Override
    public TypeBinary plusBinary(TypeBinary Binary){
        int i = this.getInt() + toInt(Binary.getBinary());
        String result = intToBinary(i);
        return new TypeBinary(result);
    }
    @Override
    public TypeInt plusInt(TypeInt Integer){
        return new TypeInt(this.getInt() + Integer.getInt());
    }

    //Substraction
    @Override
    public INumber subs(IType Number){
        TypeInt negativeInt = new TypeInt(this.getInt());
        return Number.subsInt(negativeInt);
    }
    public TypeBinary subsBinary(TypeBinary Binary){
        int i = - this.getInt() + toInt(Binary.getBinary());
        String result = intToBinary(i);
        return new TypeBinary(result);
    }
    public TypeInt subsInt(TypeInt Integer){
        return new TypeInt((-this.getInt()) + Integer.getInt());
    }

    //Multiplication
    @Override
    public INumber mult(IType Number){
        return Number.multInt(this);
    }
    @Override
    public TypeBinary multBinary(TypeBinary Binary){

        int bint = toInt(Binary.getBinary());
        int mult = this.getInt() * bint;
        String result = intToBinary(mult);
        return new TypeBinary(result);
    }
    @Override
    public TypeInt multInt(TypeInt Integer){
        return new TypeInt(this.getInt() * Integer.getInt());
    }

    //Division
    @Override
    public INumber div(IType Number){
        return Number.divInt(this);
    }
    @Override
    public TypeBinary divBinary(TypeBinary Binary){

        int bint = toInt(Binary.getBinary());
        int mult = bint / this.getInt();
        String result = intToBinary(mult);
        return new TypeBinary(result);
    }
    @Override
    public TypeInt divInt(TypeInt Integer){
        return new TypeInt(Integer.getInt() / this.getInt());
    }



}
