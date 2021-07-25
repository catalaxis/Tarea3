package AST.NodoOperation.Numerical;

import AST.NodeType;
import Type.ActualType.Number.INumber;
import Type.IType;

public class Mult implements NodeType {
    private NodeType nodeTypeLeft;
    private NodeType nodeTypeRight;
    public Mult(NodeType Left, NodeType Right){ //Constructor de la clase
        this.nodeTypeLeft = Left;
        this.nodeTypeRight = Right;
    }
    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof Mult) {
            var o = (Mult) Obj;
            return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
        } else {
            return false;
        }
    }

    //getters
    @Override
    public Mult get(){
        return new Mult(this.nodeTypeLeft, this.nodeTypeRight);
    }
    public NodeType getLeft(){
        return this.nodeTypeLeft;
    }
    public NodeType getRight(){
        return this.nodeTypeRight;
    }

    //setters
    @Override
    public INumber calculate(){
        if(nodeTypeLeft == null || nodeTypeRight == null){
            return null;
        }
        else {
            IType NL = this.nodeTypeLeft.get().calculate();
            IType NR = this.nodeTypeRight.get().calculate();
            INumber result = NL.mult(NR);
            return result;
        }
    }
}
