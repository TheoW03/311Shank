package parser.node.StatementNode;


import lexer.Token;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class VaraibleReferenceNode extends StatementNode {
    private Token name;
    public VaraibleReferenceNode(Token name) {
        this.name = name;

    }


    /**
     *
     * @return name
     */

    @Override
    public String ToString() {
        return name.getTokenValue();
    }
}
