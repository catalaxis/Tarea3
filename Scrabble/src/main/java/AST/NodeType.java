package AST;
import Type.*;

public interface NodeType{
    /*
    El AST esta pensado de tal forma que cada Type se comporte como una hoja
    Y cada operacion como un nodo o NodeType
    Para lograr esto se definieron los siguientes metodos
     */

    public boolean equals(Object Obj); //Para comparar arboles
    public NodeType get(); //get retorna un clon del arbol
    //notar que existen en cada clase los metodos getLeft() y getRight(), estos no retornan un clon, sino directamente
    //cada arbol
    public IType calculate(); //calculate() es el equivalente a eval(), esto nos entrega un Type con el resultado o null
    // si al menos una de las operaciones no fue valida.

    /*
    calculate se calcula recursivamente operando el nodo izquierdo con el derecho, se puede ver mas claro en
    la clase Or, que esta documentada
    todas las clases bajo el package AST son homologas, es decir: siguen la misma estructura
     */

    /*
    Para el correcto funcionamiento de AST se agregaron metodos a las clases Type, particularmente los metodos get(),
    calculate() y los metodos con las operaciones
    con el fin de que las operaciones "no validas" simplemente retornen null.
     */
}
