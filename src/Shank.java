//custom imports for I have my code in different dir's
//pls comment out if problem.
import lexer.Lexer;
import lexer.Token;
import parser.Parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * @Javadoc
 */
//compiles. trust me bro ;)
public class Shank {
    public static void main(String[] args) throws IOException {
        LexerTestMethod();
        System.out.println("\n");
        System.out.println("sucess");
    }

    /**
     *
     * @throws IOException file
     * method to run becvause I didnt want to overload main
     */
    public static void LexerTestMethod() throws IOException {
        String PATH = "src/TestFile";
        ArrayList<String> a = (ArrayList<String>) Files.readAllLines(Path.of(PATH), StandardCharsets.UTF_8);
        Lexer obk = new Lexer(a);
        System.out.println("lexxing..");
        ArrayList<Token> tokenData = obk.lexer();
        System.out.println("lexxing complete");
        for (int i = 0; i <tokenData.size();i++){
            System.out.println(tokenData.get(i).toString());
        }
        ParserTestMethod(tokenData);


    }
    public static void ParserTestMethod(ArrayList<Token> a){
        Parser p = new Parser(a);
        System.out.println(p.parse().ToString());

//        ArrayList<Node> n = new ArrayList<>();

//        Interperter i = new Interperter();
//        i.Resolve(p.parse());

//        System.out.println(i.Resolve(p.parse2()));
//        i.travserse(p.parse2());
//        float w = i.Resolve(p.parse());
//        System.out.println(p.parse());
//        System.out.println(w);
//        System.out.println(i.Resolve());
//        i.travserse(p.parse());
//        System.out.println("parsing..");
//        Node root = p.parse();
//        System.out.println("parsing complete");
//        System.out.println("answer: "+i.Resolve(root));
//        i.travserse(root);
//        Parser c = new Parser(a);
////       ArrayList<Node> b = c.parserMethod();
//       for(int i = 0; i < b.size(); i++){
//           System.out.println(b.get(i).ToString());
//       }

    }
}
