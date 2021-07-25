package AST.NodoOperation.Numerical;

import AST.NodeType;
import Type.ActualType.Number.ActualNumber.TypeBinary;
import Type.ActualType.Number.INumber;
import Type.IType;

public class Div implements NodeType {
    private NodeType nodeTypeLeft;
    private NodeType nodeTypeRight;
    public Div(NodeType Left, NodeType Right){ //Constructor de la clase
        this.nodeTypeLeft = Left;
        this.nodeTypeRight = Right;
    }
    //Equals
    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof Div) {
            var o = (Div) Obj;
            return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
        } else {
            return false;
        }
    }

    //Getters
    @Override
    public Div get(){
        return new Div(this.nodeTypeLeft, this.nodeTypeRight);
    }
    public NodeType getLeft(){
        return this.nodeTypeLeft;
    }
    public NodeType getRight(){
        return this.nodeTypeRight;
    }

    //Calculate
    @Override
    public INumber calculate(){
        if(nodeTypeLeft == null || nodeTypeRight == null){
            return null;
        }
        else {
            IType NL = this.nodeTypeLeft.get().calculate();
            IType NR = this.nodeTypeRight.get().calculate();
            INumber result = NL.div(NR);
            return result;
        }
    }
}
