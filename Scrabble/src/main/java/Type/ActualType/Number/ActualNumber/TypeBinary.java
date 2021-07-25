package Type.ActualType.Number.ActualNumber;

import Type.ActualType.Number.AbstractNumber;
import Type.ActualType.Number.INumber;
import Type.ActualType.TypeBool;
import Type.IType;

public class TypeBinary extends AbstractNumber {
    private String Binary;

    public TypeBinary(String Binary) {
        super(0, 0, Binary); //llamamos al constructor de AbstractNumbers
        //Para el caso de TypeBinary tenemos que implementar algo especial
        //Como queremos almacenar un signo necesitamos mantener cierta consistencia
        //Asi que nuestro string almacenado en nuestro TypeBinary siempre tendra 32bits
        //Ademas el String debe estar compuesto SOLO por 0s o 1s
        //Para esto haremos lo siguiente
        int k = 0; //inicializamos un contador k
        while (k < Binary.length()) { //mientras k sea menor al largo del string Binary
            char c = Binary.charAt(k); //rescatamos el caracter en la posicion k
            assert ((c == '0') || (c == '1')); //y comparamos el caracter, siempre debe ser un 0 o un 1
            k++; //avanzamos el contador

        }
        if (Binary.length() < 32) {//ahora, si el string mediante el que se creo el TypeBinary es menor a 32bits
            //no importa, haremos que cumpla nuestro estandar
            //esto debido a que facilita la creacion de nuestro TypeBinary por montones
            //Eso si SIEMPRE se creara un string positivo de ser asi
            //Para mantener una consistencia sencilla

            String result = "";//inicializamos nuestro resultado
            while (result.length() != (32 - Binary.length())) { //este while lo que hara sera
                //rellenar con caracteres 0s por cuantos bits hace falta a nuestro string original
                //para alcanzar el largo de 32bits
                result = result + '0';
            }
            int i = 0; //iniciamos nuestro contador i
            while (result.length() != 32) { //y mientras no alcanzemos los 32bits
                result = result + Binary.charAt(i); //copiamos bit por bit el string ingresado
                i++; //avanzamos el contador
            }
            Binary = result; //luego reescribimos nuestro string original y listo
            //tenemos los 32bits necesarios para mantener consistencia
        }
        assert Binary.length() == 32; //en caso de que ya tenga 32bits no hay problema
        //si es mayor obligaremos a que el programa se caiga para evitar errores
        this.Binary = Binary;
    }
    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof TypeBinary) {
            var o = (TypeBinary) Obj;
            return (toInt(this.getBinary()) == toInt(o.getBinary()));
        } else {
            return false;
        }
    }
    //Tarea 2
    @Override
    public IType calculate(){
        return new TypeBinary(this.getBinary());
    }
    public TypeBinary get(){return this;}
    //
    @Override
    public String getBinary(){ //Se debe hacer override para que retorne
        //El string con 32bits
        return this.Binary;
    }
    //Debemos redefinir el setter para evitar problemas
    @Override
    public void setBinary(String Binary){
        //Por definicion nuestro String debe ser SIEMPRE de 32 bits, 1 para almacenar el signo y
        //31 para almacenar el numero
        //Por lo tanto implementaremos la misma transformacion que implementamos al pie de nuestro constructor
        int k = 0;
        while (k < Binary.length()) {
            char c = Binary.charAt(k);
            assert ((c == '0') || (c == '1'));
            k++;

        }
        if (Binary.length() < 32) {
            String a = "";
            while (a.length() != (32 - Binary.length())) {
                a = a + '0';
            }
            int i = 0;
            while (a.length() != 32) {
                a = a + Binary.charAt(i);
                i++;
            }
            Binary = a;
        }
        assert Binary.length() == 32;
        this.Binary = Binary;
    }

    //Transformaciones
    //toString es analogo, ya que nuestro binario en verdad es un String
    @Override
    public String toString() { //Tambien hay que reemplazarlo para que retorne
        //El string con 32bits
        return this.Binary;
    }
    @Override
    public double toFloat(){
        int nInt = toInt(this.getBinary());
        double Float = Double.valueOf(nInt);
        return Float;
    }

    public TypeInt toTypeInt() {
        TypeInt newInt = new TypeInt(toInt(this.getBinary()));
        return newInt;
    }
    public TypeBinary toTypeBinary(){
        return new TypeBinary(this.Binary);
    }
    //Operaciones
    //Las operaciones retornan un resultado dependiendo de la operacion
    //Dependiendo de lo que se opere con lo que se opere

    //OR
    //nuestra metodo or redirige mediante la llamada de otro metodo al metodo correspondiente, que hara la operacion,
    //en la subclase correspondiente
    public TypeBinary or(IType Type){
        return Type.orBinary(this);
    }

    public TypeBinary orBinary(TypeBinary Bin){ //orBinary siempre recibe un TypeBinary
        String b = Bin.toString(); //Lo primero es rescatar el String contenido en el TypeBinary
        String a = this.toString();
        String result = ""; //Inicializamos el resultado
        int i = 0; //seteamos nuestro contador i
        while(i!=32){ //mientras nuestro contador no llegue a 32 (el largo del string binario)
            //vamos a comparar caracter con caracter de nuestro binario con el binario que recibimos
            //el caracter resultante lo agregaremos string result
            //de aqui es una solucion generica:
            if((b.charAt(i)) == (a.charAt(i))){ //cuando los caracteres sean iguales
                if(a.charAt(i)=='0') { //si ambos son 0, 0 por definicion del or
                    result = result + '0';
                    i++;
                }
                else{result = result + '1'; //si ambos son 1, 1 por definicion del or
                    i++;}
            }
            else{result = result + '1'; //si son distintos implica que hay al menos un 1, por lo tanto 1
                i++;}
        }
        return new TypeBinary(result); //una vez finalizado el ciclo actualizamos
    }

    public TypeBinary orBool(TypeBool Bool){ //orBool solo recibe un Bool
        boolean b = Bool.getBool(); //rescatamos el boolean contenido en neustro TypeBool
        if(b==true){ //True == 1
            return new TypeBinary("1").orBinary(this); //entonces creamos un TypeBinary("1")
            // que represente nuestro TypeBool(true);
        }
        else{return new TypeBinary("0").orBinary(this);} //metodo analogo
    }

    //AND
    //and es completamente analogo a or
    @Override
    public TypeBinary and(IType Type){
        return Type.andBinary(this);
    }
    @Override
    public TypeBinary andBinary(TypeBinary B){
        String a = this.toString();
        String b = B.toString();
        String result = "";
        int i = 0;
        while(i!=32){
            if((b.charAt(i)) == (a.charAt(i))){
                if(a.charAt(i)=='1') { //solo cambian nuestras condiciones
                    result = result + '1';
                    i++;
                }
                else{result = result + '0';
                    i++;}
            }
            else{result = result + '0';
                i++;}
        }
        return (new TypeBinary(result));
    }
    @Override
    public IType andBool(TypeBool Bool){
        boolean b = Bool.getBool();
        if(b==true){
            return new TypeBinary("1").andBinary(this);
        }
        else{return new TypeBinary("0").andBinary(this);}
    }

    //Operaciones Numericas
    //Las operaciones que no se pueden realizar directamente en Binario
    //Se operan mediante la transformacion auxiliar a int
    //Plus
    @Override
    public INumber plus(IType Number){
        return Number.plusBinary(this);
    }
    @Override
    public TypeBinary plusBinary(TypeBinary Binary){
        String result = addBinary(this.getBinary(),Binary.getBinary());
        //ya esta definida la suma en la clase padre
        return new TypeBinary(result);
    }
    @Override
    public TypeInt plusInt(TypeInt Integer){
        int bint = toInt(this.Binary);
        return new TypeInt(Integer.getInt() + bint);
    }
    //Substraction
    @Override
    public INumber subs(IType Number){
        return Number.subsBinary(this);
    }
    @Override
    public TypeBinary subsBinary(TypeBinary Binary){
        String Bin = addBinary(TwosComplement(this.Binary), Binary.getBinary());
        //el complemento dos es el inverso aditivo en binario, mediante esto
        //calculamos la resta
        return new TypeBinary(Bin);
    }
    @Override
    public TypeInt subsInt(TypeInt Integer){
        int bin = toInt(this.Binary);
        return new TypeInt(Integer.getInt()-bin);
    }
    //Multiplication
    @Override
    public INumber mult(IType Number){
        return Number.multBinary(this);
    }
    @Override
    public TypeBinary multBinary(TypeBinary Binary){
        //se usaran ints como intermediarios
        int bint1 = toInt(this.getBinary());
        int bint2 = toInt(Binary.getBinary());
        String result = intToBinary(bint2*bint1);
        return new TypeBinary(result);
    }
    @Override
    public TypeInt multInt(TypeInt Integer){

        int bint1 = toInt(this.getBinary());
        int result = Integer.getInt()*bint1;
        return new TypeInt(result);
    }
    //Division
    @Override
    public INumber div(IType Number){
        return Number.divBinary(this);
    }
    @Override
    public TypeBinary divBinary(TypeBinary Binary){
        //se usaran ints como intermediarios
        int bint1 = toInt(this.getBinary());
        int bint2 = toInt(Binary.getBinary());
        String result = intToBinary(bint2/bint1);
        return new TypeBinary(result);
    }
    @Override
    public TypeInt divInt(TypeInt Integer){
        int bint1 = toInt(this.getBinary());
        int result = Integer.getInt()/bint1;
        return new TypeInt(result);
    }
}
