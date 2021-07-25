package Type.ActualType.Number;
import Type.ActualType.Number.ActualNumber.*;
import Type.IType;

public interface INumber extends IType{
    public double toFloat();
    public TypeFloat toTypeFloat();

    public int toInt(String Binary);
    public String intToBinary(int Inte);
    public String addBinary(String x, String y);

    //Operaciones
    //
    @Override
    public INumber plus(IType Number);
    public INumber plusBinary(TypeBinary Binary);
    public INumber plusInt(TypeInt Integer);
    public TypeFloat plusFloat(TypeFloat Float);
    //
    @Override
    public INumber subs(IType Number);
    public INumber subsBinary(TypeBinary Binary);
    public INumber subsInt(TypeInt Int);
    public TypeFloat subsFloat(TypeFloat Float);
    //
    @Override
    public INumber mult(IType Number);
    public INumber multBinary(TypeBinary Binary);
    public INumber multInt(TypeInt Integer);
    public TypeFloat multFloat(TypeFloat Float);
    //
    @Override
    public INumber div(IType Number);
    public INumber divBinary(TypeBinary Binary);
    public INumber divInt(TypeInt Integer);
    public TypeFloat divFloat(TypeFloat Float);

    //TAREA 2

}
