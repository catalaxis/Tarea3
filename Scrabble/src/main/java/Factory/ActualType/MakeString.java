package Factory.ActualType;

import Type.ActualType.TypeString;

import java.lang.reflect.Type;
import java.util.Hashtable;

/*
Nota al lector:
Debo introducir esta clase admitiendo que, a diferencia de lo que es AST, trabaje con conceptos que no creo entender
a la perfeccion. Hice lo que entendi que era necesario entregar, desde la premisa que Flyweight es un patron usado
para optimizar el uso de memoria, dicho esto puedo continuar detallando lo que realice (en tercera persona)
 */

/*
Todas las clases son homologas, los metodos se implementan de forma similiar o identica, mantieniendo la esctructura de
esta clase.

 */
public class MakeString {
    //Declaramos nuestro diccionario hashtable
    private Hashtable<String, TypeString> hashtable = new Hashtable<>();

    //make retorna un nuevo objeto en caso de que el objeto no este en nuestro diccionario
    //en caso de que si este entonces simplente retorna el objeto que ya estaba en el diccionario
    //ademas cada objeto se identifica con un id, lo que puede ser util en un futuro si es necesario
    //crear un nuevo objeto usando este id
    public TypeString make(String id, TypeString Str){
        TypeString in = hashtable.get(Str);
        if(in != null){ //en caso de que el objeto este dentro del diccionario
            return in; //se retorna
        }
        else{
            in = Str; //de lo contrario se procede a crear una nueva entrada
            hashtable.put(id, in); //se crea
        }
        return in; //se retorna el objeto
    }
    //simpleMake solo toma el objeto y sigue la misma logica, en este caso el id es el objeto es el String del objeto
    public TypeString simpleMake(TypeString Str){
        TypeString in = hashtable.get(Str);
        if(in != null){
            return in;
        }
        else{
            in = Str;
            hashtable.put(Str.toString(), in);
        }
        return in;
    }
    //size indica cuantas entradas existen en el dicciario, lo que es util para testear nuestros metodos
    public int size(){
        return hashtable.size();
    }
}
