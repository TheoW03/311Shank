//custom imports for I have my code in different dir's
//pls comment out if problem.
import lexer.Lexer;
import lexer.Token;
import lexer.UnauthTokenException;
import parser.Interperter;
import parser.Parser;
import parser.node.FunctionCallNode.CallableNode;
import parser.node.FunctionNode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Theo willis
 * @version 1.0.0
 * ~ project outline here ~
 * <h3>They seh dem run di streets wen mi pave di road.
 * this lyric from ninja man makes me love this class (the song he sang this in is called. ninja mi ninja - ninja man) </h3>
 *
 *  69 commits lego.
 * <p>the diragatory exception messages was my brain. after 5 days of non stop java and burnout.
 * i just wrote what ever bc I was tired.</p>
 * <p>there is a bjillon tests Im missing here. but good luck</p>
 * <p>enjoy :D</p>
 */

//compiles. trust me bro ;)
public class Shank {
    //the hashmap of NonBuilt functions is in interpter BTW.
//    public HashMap<String, CallableNode> func;
    public static void main(String[] args) throws IOException {
        try{
            LexerTestMethod();
        }catch (UnauthTokenException e){
            System.out.println("lexxer threw exception"); //i read your feedback
            e.printStackTrace();
        }

        System.out.println("\n");
        System.out.println("success");
    }

    /**
     *
     * @throws IOException file
     * method to run because I didnt want to overload main
     */
    public static void LexerTestMethod() throws IOException {
        String PATH = "src/TestFile";
        ArrayList<String> a = (ArrayList<String>) Files.readAllLines(Path.of(PATH), StandardCharsets.UTF_8);
        Lexer obk = new Lexer(a);
        System.out.println("lexxing..");
        ArrayList<Token> tokenData = obk.lexer();
        System.out.println("lexxing complete");
        for (Token tokenDatum : tokenData) {
            System.out.println(tokenDatum.toString());
        }
        ParserTestMethod(tokenData);


    }

    /**
     *
     * @param a token
     * parser test method to avoid overloading main
     */
    public static void ParserTestMethod(ArrayList<Token> a){
        Parser p = new Parser(a);
        ArrayList<FunctionNode> fn = p.parse();
        for(int i = 0; i < fn.size();i++){
            System.out.println("name: "+fn.get(i).getIdent());
            System.out.println("params: "+fn.get(i).params());
            System.out.println("vars/constants: "+fn.get(i).vars());
            System.out.println("statemnets: "+fn.get(i).statemnets());
        }
        Interperter in = new Interperter();
        for(int i = 0; i < fn.size(); i++){
            in.compileMethods(fn.get(i),new HashMap<>(),"Start");
        }



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
