package AST.NodoOperation.Numerical;

import AST.NodeType;
import Type.ActualType.Number.INumber;
import Type.IType;

public class Plus implements NodeType {
    private NodeType nodeTypeLeft;
    private NodeType nodeTypeRight;
    public Plus(NodeType Left, NodeType Right){ //Constructor de la clase
        this.nodeTypeLeft = Left;
        this.nodeTypeRight = Right;
    }
    //equals
    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof Plus) {
            var o = (Plus) Obj;
            return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
        } else {
            return false;
        }
    }

    //getters
    public Plus get(){
        return new Plus(this.nodeTypeLeft, this.nodeTypeRight);
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
            INumber result = NL.plus(NR);
            return result;
        }
    }
}
