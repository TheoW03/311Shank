package lexer;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
public class Token {
    //OP state
    //note: dont forget to move everything over.
    public enum OPTokens {
        ADD("+", "PLUS"),
        SUBTRACT("-", "SUBTRACT"),
        DIVIDE("/", "DIVIDE"),
        MULTIPLY("*", "MUTIPLY"),
        ENDOFLINE(";", "EOL"),//Can I not do endOfLine for my break line bc I love java syntax
        LParan(")", "Lparenthsis"),
        RParan("(", "Rparenthsis"),
        NUMBER("0-9", "NUMBER"),
        MOD("mod", "%"),
        //KEYWORDS

        IDENTIFIER("A-Z", "WORD"),
        KEY_WORD("keyword", "keyword"),
        BEGIN("begin", "begin"),
        END("end", "end"), //im adding this just because.
        //vars
        INTEGER("Int", "s"), FLOAT("f", "f"), STRING("string","String"), STRING_DT("String","String"),
        EQUALS("=", "eq"),
        CONSTANTS("constants", "c"), VARAIBLES("vars", "v"), VAR("VAR", "Var"),
        //define
        DEFINE("define", "define"),
        IF("if"),
        FOR("for"),
        ELSE_IF("elsif"),
        ELSE("else"),
        FROM("from"),
        TO("to"),
        UNTIL("until"),
        REPEAT("repeat"),
        THEN("Then"),
        WHILE("while"),
        //EQUAALITY
        EQUALITY_EUQUALS("==", "checks for equality"),
        GREATER_THAN_EQUALS(">=", "GTE"),
        LESS_THAN_EQAUALS("<=", "LTE"),
        GREATER_THAN(">", "GT"),
        LESS_THAN("<", "LT"),
        NOT_EQUAL("!=", "Not"),
        //built in
        WRITE("write"), INT_CON_FLOAT("IntToFloat"), SQRT("squareroot"), FLOAT_CON_INT("FloatToInt"), READ("Read"), GET_RANDOM("Random"),
        TRUE("true"),FALSE("false"),
        BOOLEAN_DT("boolean"),AND("and"),OR("or"), CHARACTER("Charatcer"), CHAR("char"),
        SUB_STRING("substring"),STR_LEN("string length"),CHAR_AT("Charat");

        private String s;
        private String value;

        OPTokens(String value, String s) {
            this.s = s;
            this.value = value;
        }

        OPTokens(String s) {
            this.s = s;
        }


        @Override
        public String toString() {
            return this.s;
        }
    }

    private Token.OPTokens type;
    private String tokenValue;

    /**
     * @param type       a
     * @param tokenValue b
     */
    //token
    public Token(Token.OPTokens type, String tokenValue) {
        this.tokenValue = tokenValue;
        this.type = type;

    }

    public Token.OPTokens getTokenEnum() {
        return type;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    @Override
    public String toString() {
        return type + " (" + tokenValue + ")";
    }


}
