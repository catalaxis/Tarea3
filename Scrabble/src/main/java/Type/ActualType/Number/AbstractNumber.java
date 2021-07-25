package Type.ActualType.Number;

import Type.AbstractType;
import Type.ActualType.Number.ActualNumber.*;
import Type.IType;


public abstract class AbstractNumber extends AbstractType implements INumber{

    public AbstractNumber(int Integer, double Float, String Binary) {
        super("", false, Integer, Float, Binary);
    }


    @Override
    public double toFloat() {
        return 0;
    }  //metodo comun de todos los tipo Numero

    @Override
    public TypeFloat toTypeFloat() {
        return new TypeFloat(this.toFloat());
    } //este es un metodo comun de los tipo Numero


    //Metodos entregados en el enunciado
    //Transformar de String Binario a int
    public int toInt(String binary) {
        if (bitToInt(binary.charAt(0)) == 0) {
            return positiveBinToInt(binary);
        } else {
            return negativeBinaryToInt(binary);
        }
    }
    //Transformar de String Binario negativo a int
    private int negativeBinaryToInt(String binary) {
        int n = binary.length() - 1;
        int w = -bitToInt(binary.charAt(0)) * (int) Math.pow(2, n);
        for (int i = n, j = 0; i >= 0; i--, j++) {
            w += (int) Math.pow(2, j) * (binary.charAt(i) == '1' ? 1 : 0);
        }
        return w - 2147483647 -1; //reajuste para binarios de 32bits con signo
    }
    //Transformar de String Binario positivo a int
    private int positiveBinToInt(String binary) {
        int w = 0;
        for (int i = binary.length() - 1, j = 0; i > 0; i--, j++) {
            w += (int) Math.pow(2, j) * bitToInt(binary.charAt(i));
        }
        return w;
    }
    //Transformar de bit a int
    private int bitToInt(char bit) {
        return bit == '0' ? 0 : 1;
    }

    //Tranformacion de int a String Binario
    public String intToBinary(int Inte) { //seguiremos el algoritmo al pie de la letra
        int abs = Math.abs(Inte);
        String b = Integer.toBinaryString(abs); //No se establece si es o no es "legal" usar esta funcion,
        //de todas formas se procedera a usarla SOLO para transformar esa parte y se creara el resto del codigo
        //como se indica en el algoritmo de la descripcion del proyecto
        if(Inte < 0){
            return TwosComplement(b);
        }
        return b; // hasta ahora es tal como aparece escrito en el enunciado
    }
    public String TwosComplement(String b){
        int i =0; //se inicializa y setea el contador i en 0
        String c = ""; //se crea string auxiliar y se inicializa
        //recordar que para almacenar un signo es necesario cierta consistencia
        //por eso se ha denifido que todos los binarios tendran un largo de 32bits
        while(i!=32-b.length()){ //se rellena con 0s los espacios "vacios"/no ocupados
            c = c+"0";
            i++;
        }
        int k =0; //se setea un nuevo contado k en 0
        while(i!=32) { //se copia el string de b a c
            c = c + b.charAt(k);
            k++;
            i++;
        }
        i = 0; //se resetea el contador i
        b = c; //se actualiza b
        c = ""; //se resetea c
        while(i!=32){ //funcion "opuesto"
            if(b.charAt(i)=='0'){ //por cada 0 -> 1
                c=c+"1";
            }
            if(b.charAt(i)=='1'){ //por cada 1 -> 0
                c=c+"0";
            }
            i++;
        }
        i = 0; //se resetea el contador i
        c = addBinary(c,"00000000000000000000000000000001"); //se suma "1" binario, estandar en TwosComplement
        //notar que nuestra suma jamas nos dara mas de 32bits
        return c; //se retorna el resultado
    }

    public String addBinary(String x, String y){ //se debe notar que esta operacion recibe siempre un String de 32bits
        int i = 31; //se inicializa y setea el contador i en 31
        //para sumar binarios se debe iniciar la suma desde el ultimo bit
        String aux = ""; //se inicializa el string auxiliar
        String result = ""; //se inicializa el string resultado
        int k = 0; //se inicializa y setea nuestro contador k, este cumplira la funcion de almacenar el "acarreo" de 1s
        while(i!=-1){ //iniciamos el ciclo de suma
            if(x.charAt(i) == y.charAt(i)) { //si los bit son iguales
                if (k == 0) {  //si no tenemos ningun acarreo
                    aux = aux + "0"; //asignamos un 0
                    //esto ya que tanto 1+1 como 0+0 implica 0 en esta posicion
                }
                if (k == 1) { //analogo, pero para este caso se asignara un 1
                    aux = aux + "1";
                    k = 0; //se resetea el contador
                }
                if (x.charAt(i) == '1') { //en este caso "acarreamos" un 1
                    k = 1; //se setea el contador en 1
                }
                i--; //retrocede el contador
                continue;
            }
            else{
                if(k == 0){ //la logica es analoga al proceso anterior, solo que aqui ambos son distintos
                    //por lo tanto la suma sera siempre 1 si no tenemos acarreo
                    aux = aux + "1";
                }
                else{
                    aux = aux + "0"; //en el caso de que haya un acarreo la suma sera 0
                    }
                i--; //retrocede nuestro contador
                continue;
            }
        }
        //al terminar este ciclo el string tendra 32bits
        //es importante notar que para evitar problemas de consistencia el string no puede jamas tener 33bits
        //por lo que sin importar el valor de contador k el ciclo terminara cuando no hayan mas bits que sumar

        i = 31; //se setea el contador en 31
        while(i!=-1){ //ahora debemos invertir nuestro resultado
            if(aux.charAt(i)=='0'){
                result = result + "0";
            }
            else{result = result + "1";}
            i--;
        }
        return result; //se retorna el resultado
        //existen muchas otras formas de hacer esta suma pero considero que esta es conviente debido a que permite
        //truncar la suma con facilidad
        //ademas mantiene consistencia
        //-1 + 1 = 0 de esta forma, siempre
    }

    @Override
    public INumber plus(IType Number) {
        return null;
    }

    @Override
    public INumber plusBinary(TypeBinary Binary) {
        return null;
    }

    @Override
    public INumber plusInt(TypeInt Integer) {
        return null;
    }

    //Operaciones Numericas
    //En este caso solo es necesario declarar plusFloat que heredaran todas las subclases
    @Override
    public TypeFloat plusFloat(TypeFloat Float){
        return new TypeFloat(this.toFloat() + Float.toFloat());
    }

    @Override
    public INumber subs(IType Number) {
        return null;
    }

    @Override
    public INumber subsBinary(TypeBinary Binary) {
        return null;
    }

    @Override
    public INumber subsInt(TypeInt Int) {
        return null;
    }

    @Override
    public TypeFloat subsFloat(TypeFloat Float){
        //hay que recordar que la resta no es conmutativa, por lo tanto el orden importa
        //en este caso subs se puede ver como "A.subs(B)" -> "B.estaRestando(A)"//"B.subsFloat"
        //por lo tanto
        return new TypeFloat(- this.toFloat() + Float.toFloat());
    }

    @Override
    public INumber mult(IType Number) {
        return null;
    }

    @Override
    public INumber multBinary(TypeBinary Binary) {
        return null;
    }

    @Override
    public INumber multInt(TypeInt Integer) {
        return null;
    }

    @Override
    public TypeFloat multFloat(TypeFloat Float){
        return new TypeFloat(this.toFloat() * Float.toFloat());
    }

    @Override
    public INumber div(IType Number) {
        return null;
    }

    @Override
    public INumber divBinary(TypeBinary Binary) {
        return null;
    }

    @Override
    public INumber divInt(TypeInt Integer) {
        return null;
    }

    @Override
    public TypeFloat divFloat(TypeFloat Float){
        //Nuevamente, el orden importa
        return new TypeFloat(Float.toFloat() /this.toFloat());
    }
}
