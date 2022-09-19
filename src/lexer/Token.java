package lexer;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Token {
    //OP state
    public enum OPTokens{
        ADD("+","PLUS"),
        SUBTRACT("-","SUBTRACT"),
        DIVIDE("/","DIVIDE"),
        MULTIPLY("*" ,"MUTIPLY"),
        ENDOFLINE(";","EOL"),//Can I not do endOfLine for my break line bc I love java syntax
        LParan(")","Lparenthsis"),
        RParan("(","Rparenthsis"),
        NUMBER("0-9","NUMBER"),
        IDENTIFIER("A-Z","WORD"),
        KEY_WORD("keyword","keyword"),
        NAME("IDENTIFIRE","e");
        ;
        private String s;
        private String value;
        OPTokens(String value,String s) {
            this.s = s;
            this.value=value;
        }


        @Override
        public String toString() {
            return  this.s;
        }
    }
    private Token.OPTokens type;
    private String tokenValue;

    /**
     *
     * @param type a
     * @param tokenValue b
     */
    //token
    public Token(Token.OPTokens type, String tokenValue){
        this.tokenValue= tokenValue;
        this.type= type;

    }
    public Token.OPTokens getTokenEnum(){
        return type;
    }
    public String getTokenValue(){
        return tokenValue;
    }
    @Override
    public String toString(){
        return type + " ("+tokenValue+")";
    }


}
