# Tarea3
Interfaz Grafica Tarea

Readme Tarea1:
"Tarea1

Existen 5 tipos definidos en las clases TypeString (string), TypeBool (booleano), TypeInt (int), TypeFloat (double/float), TypeBinary (string binario).

El uso de este programa es de la siguiente manera:

1.- Sobre los setters y getters: notar que cada tipo tiene definido su setter y getter especifico, usar el setter equivocado no alterara el tipo ni la igualdad de este, pero es importante evitar cometer este tipo de errores.

2.- equals() el metodo equals es analogo en cada Type, con excepcion en TypeString, donde para evitar problemas el metodo compara caracter por caracter de los strings que componen cada TypeString.

3.- Transformaciones cada transformacion retorna un clon del tipo transformado al tipo a transformar. Es posible crear clones del mismo tipo. No es necesario usar casteos. todas las tranformaciones siguen la misma forma de ej: TypeBool(argsboolean).toTypeString() -> TypeString(argsstring)

4.- Operaciones cada operacion retorna un resultado segun el tipo, para manejar algunas operaciones es necesario usar casteos, en particular revisar los ejemplos en cada Test. Existen 7 operaciones, no son simetricas y la estructura de estas esta definida en el enunciado. Estas operaciones son: add, or, and, plus, subs, mult, div. todas las operaciones siguen el mismo uso, el cual se puede observar en el siguiente ej: TypeFloat Float = TypeInt(argsint).plus(TypeFloat(argsdouble)) -> TypeFloat(resultdouble) En algunos casos es necesario usar casteos, por lo que el uso seria de la siguiente forma: TypeFloat Float = (TypeBinary) Binary.div(TypeFloat(argsdouble))"

Readme Tarea2:
"Tarea2

Existen 4 paquetes 1.- AST: Este paquete contiene la interfaz NodeType y cada clase de operaciones. La documentacion se encuentra en la interface NodeType y en la clase Or. Un ej uso de AST, tomando en cuenta el correcto uso de los tipos es el siguiente: NodeType Arbol = Add(new TypeString("hola"), new TypeString("mundo")); TypeString Arbolresultado = Arbol.calculate; Notese En caso de que alguna de las operaciones sea incorrecta el retorno de .calculate() sera null. OrTest esta documentado.

2.- Factory/ActualType: Este paquete contiene las clases que permiten crear objetos ahorrando memoria, mediante el uso del patron Flyweight. La documentacion se encuentra en la clase MakeString. Un ej de uso para MakeString seria el siguiente: MakeString StringMaker = new MakeString(); TypeString Str1 = StringMaker.make(id: "holamundo", new TypeString("hola mundo")); TypeString Str2 = StringMaker.simpleMake(new TypeString("hola mundo")); MakeStringTest esta documentado.

3.- TESTS: Este package contiene todos los test de la tarea 2 ordenados para que sea mas sencillo revisarlos. Algo que mejore de la entrega de la tarea pasada.

4.- Type: Los tipos definidos en la tarea pasada. Poco o nada ha cambiado. Los cambios estan descritos en la documentacion de AST."

Tarea 3:

1.- TODA LA INTERFAZ GRAFICA ESTA DEFINIDA Y DOCUMENTADA EN src\main\java\cl\uchile\dcc\scrabble\gui\app.java

2.- El uso de la interfaz es el siguiente:
Existen botones para cada tipo, para cada operacion, para cancelar (o el boton "c"), para calcular, ademas de 6 botones para almacenar resultados y 1 boton para eliminar todos estos resultados almacenados.
Ademas existen 3 barras te texto:
  La primera o la superior entrega el resultado, solo sirve de output.
  La segunda o del medio ES EL UNICO INPUT, ademas entrega mensajes al usuario dependiendo de la funcion que se realice
  La tercera es la visualizacion del arbol que se esta creando mediante operaciones o del ultimo objeto creado.

PARA CREAR UN OBJETO:
  Primero se debe ingresar el valor del objeto en el INPUT y luego presionar el boton del objeto que se quiere crear. Por ej, si se desea crear un TypeString("hola mundo"), se debe simplemente ingresar:
  hola mundo
  (Notar que no es necesario agregar comillas)
  Luego de esto se deberia hacer click en el boton "String" y esto arrojara un mensaje de que se ha creado un objeto String(hola mundo)
  ES MUY IMPORTANTE SABER que para operar se necesitan al menos dos objetos
  Para esto, para realizar la primera operacion simplemente se debe crear un segundo objeto ANTES de realizar la operacion
  
PARA REALIZAR UNA OPERACION
  Primero se deben de haber creado dos objetos, luego simplemente se hace click sobre la operacion que se desea realizar con estos dos objetos
  Supongamos que se ha creado el objeto String(hola mundo) y el objeto Int(2021), si deseamos concatenar estos objetos debemos solo hacer click en el boton "ADD"
  Esto retornara en resultado:
  hola mundo2021
  Una vez realizada la operacion se tendra solo un objeto, el objeto de la operacion (en el caso anterior Add(String(hola mundo), Int(2021))). Por los que si desea realizar una nueva operacion se debera primero crear un nuevo objeto con el cual realizar la operacion. Siguiendo esto, si quisieramos concatenar el arbol anterior con String(julio) se deberia primero crear el objeto String(julio) y luego hacer click en "ADD", lo que entregaria como resultado
  hola mundo2021julio
  
PARA LIMPIAR TODAS LAS OPERACIONES
  Solo es necesario hacer click en "c"

PARA GUARDAR OPERACIONES O UN ARBOL
  Es posible hacerlo haciendo click en S1, o S2 o S3... hasta S6. CUIDADO esto solo sucedera si no existe nada guardado, de lo contrario importara el objeto que este guardado!
  Para importar el objeto guardado solo es necesario hacer click en S1 (por ej), lo que importara el objeto que se almacene en S1. Para importar el objeto de S2, se debe hacer click en S2 y asi. Una vez se importe el objeto se limpiara S1, dejando este espacio libre para guardar cualquier otro objeto.
  MUY IMPORTANTE solo se guardara el resultado de la ultima operacion realizada y no se almacenaran los objetos sobre los que no se han realizado ninguna operacion.
  Por ej, si se hubiese ingresado el objeto Binary(1001) despues de Add(Add(String(hola mundo), Int(2021)),String(julio)), al hacer click en S1 (considerando que esta este vacia),
S1 solo almacenara Add(Add(String(hola mundo), Int(2021)),String(julio)).
  NUEVAMENTE IMPORTANTE al importar una variable esta sobreescribira el objeto sobre el cual no se haya realizado una operacion (o el objeto flotante). Para el caso anterior, al neuvamente hacer click, desapareceria Binary(1001) y seria reemplazado por Add(Add(String(hola mundo), Int(2021)),String(julio)), que es lo que se habia guardado.
  
PARA ELIMINAR TODO LO GUARDADO EN S1, S2, S3, S4, S5, S6
  Justo al lado de estos botones hay un boton "!", al hacer click por primera vez en este boton se arrojara un mensaje al usuario enunciando que se debe volver a hacer click en "!" para eliminar todo lo que se haya almacenado en las funciones "Save". Esto para evitar que todos los objetos se eliminen por error.
  
En caso de que exista cualquier duda sobre el uso de la interfaz pueden escribirme a mi correo: m.donoso.ge@gmail.com
Contestare a la brevedad!
ESTO ES TODO :)
