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
        NUMBER("0-9","NUMBER");
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
//    private char token;
//    private String token2;
//    private String tokenName;
    private Token.OPTokens type;
    private String tokenValue;

    //token
    public Token(Token.OPTokens type, String tokenValue){
        this.tokenValue= tokenValue;
        this.type= type;

    }
//    public Token(char token, String tokenName){
//        this.token = token;
//        this.tokenName = tokenName;
//    }
    public Token.OPTokens getTokenEnum(){
        return type;
    }
    public String getTokenValue(){
        return tokenValue;
    }
//    public Token(String token, String tokenType){
//        this.tokenName = tokenType;
//        this.token2 = token;
//        this.token = ' ';
//    }
//
//    // if numbre
//    public Token(String tokenName){
//        this.tokenName = tokenName;
//    }
//    public Token() {}
//    public String getTokenAsString(){
//        if(token == ' '){
//            return token2;
//        }
//        return String.valueOf(token);
//    }
//    /**
//     *
//     * @param token takes token
//     * @return the string value of token
//     */
//    public String compare(char token){
//        return switch (token) {
//            case '+' -> OPTokens.ADD.toString();
//            case '-' -> OPTokens.SUBTRACT.toString();
//            case '*' -> OPTokens.MULTIPLY.toString();
//            case '/' -> OPTokens.DIVIDE.toString();
//            case '(' -> OPTokens.LParan.toString();
//            case ')' -> OPTokens.RParan.toString();
//            case ';' -> OPTokens.ENDOFLINE.toString();
//            default -> null;
//        };
//    }
//    public Token.OPTokens getTokenEnum(){
//        return switch (this.token) {
//            case '+' -> OPTokens.ADD;
//            case '-' -> OPTokens.SUBTRACT;
//            case '*' -> OPTokens.MULTIPLY;
//            case '/' -> OPTokens.DIVIDE;
//            case '(' -> OPTokens.LParan;
//            case ')' -> OPTokens.RParan;
//            default -> null;
//        };
//    }
//    public String getTokenName(){
//        return tokenName;
//    }
//
//
//    /**
//     *
//     * @param token nombre
//     * @return True or false
//     */
//    public boolean esNumbre(char token){
//        try{
//            int a = Integer.parseInt(String.valueOf(token));
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//    /**
//     *
//     * @return return token name and Token
//     */
//    //Yk your coding in C# to much when u lowercase toString :')
//    @Override
    public String toString(){
        return type + " ("+tokenValue+")";
    }


}
