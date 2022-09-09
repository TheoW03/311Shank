package parser.node;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class FloatNode extends Node{
    private float value;
    public FloatNode(float value) {
this.value = value;
    }
    public float getValue(){
        return this.value;
    }
    @Override
    public String ToString() {
        return null;
    }
}
