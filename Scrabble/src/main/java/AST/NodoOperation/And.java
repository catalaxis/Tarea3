package AST.NodoOperation;

import AST.NodeType;
import AST.NodoOperation.Numerical.Div;
import Type.IType;

public class And implements NodeType{
    private NodeType nodeTypeLeft;
    private NodeType nodeTypeRight;
    public And(NodeType Left, NodeType Right){ //Constructor de la clase
        this.nodeTypeLeft = Left;
        this.nodeTypeRight = Right;
    }
    //equals
    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof And) {
            var o = (And) Obj;
            return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
        } else {
            return false;
        }
    }

    //getters
    @Override
    public And get(){
        return new And(this.nodeTypeLeft, this.nodeTypeRight);
    }
    public NodeType getLeft(){
        return this.nodeTypeLeft;
    }
    public NodeType getRight(){
        return this.nodeTypeRight;
    }

    //setters
    @Override
    public IType calculate(){
        if(nodeTypeLeft == null || nodeTypeRight == null){
            return null;
        }
        else {
            IType NL = this.nodeTypeLeft.get().calculate();
            IType NR = this.nodeTypeRight.get().calculate();
            IType result = NL.and(NR);
            return result;
        }
    }
}
