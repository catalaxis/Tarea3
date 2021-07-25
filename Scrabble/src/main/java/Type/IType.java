package Type;

import AST.NodeType;
import Type.ActualType.Number.ActualNumber.TypeBinary;
import Type.ActualType.Number.ActualNumber.TypeFloat;
import Type.ActualType.Number.ActualNumber.TypeInt;
import Type.ActualType.Number.INumber;
import Type.ActualType.TypeBool;
import Type.ActualType.TypeString;

public interface IType extends NodeType{
    //Setters y getters que se comparten a lo largo de nuestras clases
    public String getString();
    public void setString(String Str);

    public boolean getBool();
    public void setBool(boolean Bool);

    public int getInt();
    public void setInt(int Integer);

    public double getFloat();
    public void setFloat(double Float);

    public String getBinary();
    public void setBinary(String Binary);

    //
    public String toString();
    public TypeString toTypeString();


    TypeBinary orBinary(TypeBinary Binary);
    IType orBool(TypeBool Bool);

    TypeBinary andBinary(TypeBinary Binary);
    IType andBool(TypeBool Bool);
    //TAREA 2
    public IType calculate();

    public IType add(IType Type);
    public IType and(IType Type);
    public IType or(IType Type);
    public INumber plus(IType Type);
    public INumber subs(IType Type);
    public INumber mult(IType Type);
    public INumber div(IType Type);


    INumber plusBinary(TypeBinary typeBinary);

    INumber subsBinary(TypeBinary typeBinary);

    INumber multBinary(TypeBinary typeBinary);

    INumber divBinary(TypeBinary typeBinary);

    INumber plusInt(TypeInt typeInt);

    INumber subsInt(TypeInt negativeInt);

    INumber multInt(TypeInt typeInt);

    INumber divInt(TypeInt typeInt);

    TypeFloat plusFloat(TypeFloat typeFloat);

    TypeFloat subsFloat(TypeFloat negativeFloat);

    TypeFloat multFloat(TypeFloat typeFloat);

    TypeFloat divFloat(TypeFloat typeFloat);
}
