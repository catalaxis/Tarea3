package AST.NodoOperation;

import AST.NodeType;
import AST.NodoOperation.Numerical.Div;
import Type.IType;

/*
La documentacion es bastante sencilla ya que los metodos para este caso son bastante cortos
Para mas informacion revisar el OrTest, que contiene los test que estime necesarios para probar el
correcto funcionamiento de esta clase

TODAS LAS OPERACIONES Y LOS TESTEOS DE CADA OPERACION SON HOMOLOGOS CON DIFERENCIAS MUY PARTICULARES
 */

public class Or implements NodeType{
    private NodeType nodeTypeLeft; //
    private NodeType nodeTypeRight; //
    public Or(NodeType Left, NodeType Right){ //Constructor de la clase
        this.nodeTypeLeft = Left;
        this.nodeTypeRight = Right;
    }
    //equals, estandar
    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof Or) {
            var o = (Or) Obj;
            return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
        } else {
            return false;
        }
    }

    //getters
    @Override
    public Or get(){
        return new Or(this.nodeTypeLeft, this.nodeTypeRight); //se retorna un clon
    }

    public NodeType getLeft(){
        return this.nodeTypeLeft; //se retorna directamente el arbol izquierdo
    }
    public NodeType getRight(){
        return this.nodeTypeRight; //se retorna directamente el arbol derecho
    }

    //setters
    @Override
    public IType calculate(){ //
        if(nodeTypeLeft == null || nodeTypeRight == null){ //si uno de los hijos es null
            return null; //se retorna null
        }
        else {
            IType NL = this.nodeTypeLeft.get().calculate(); //se calcula el resultado del arbol izquierdo
            IType NR = this.nodeTypeRight.get().calculate(); //se calcula el resultado del arbol derecho
            IType result = NL.or(NR); //se operan ambas partes
            return result; //se retorna el resultado
        }
    }
}
