package AST.NodoOperation.Numerical;

import AST.NodeType;
import Type.ActualType.Number.INumber;
import Type.IType;

public class Subs implements NodeType {
    private NodeType nodeTypeLeft;
    private NodeType nodeTypeRight;
    public Subs(NodeType Left, NodeType Right){ //Constructor de la clase
        this.nodeTypeLeft = Left;
        this.nodeTypeRight = Right;
    }
    //equals
    @Override
    public boolean equals(Object Obj) {
        if (Obj instanceof Subs) {
            var o = (Subs) Obj;
            return this.getLeft().equals(o.getLeft()) && this.getRight().equals(o.getRight());
        } else {
            return false;
        }
    }

    //getters
    @Override
    public Subs get(){
        return new Subs(this.nodeTypeLeft, this.nodeTypeRight);
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
            INumber result = NL.subs(NR);
            return result;
        }
    }
}
