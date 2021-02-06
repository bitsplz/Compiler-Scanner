import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

enum tokenType {
    ID, INT, IF, WHILE, ROUND_LPAREN, ROUND_RPAREN, COMPARE, OP, INVALID, ASSIGN
}
public class TinyScanner {
    static String token;

    public static void main(String args[]) throws IOException {
        try {
            removeComments();
            //creating File instance to reference text file in Java
            File inputFile = new File("removecomment.txt");
            Scanner scanner = new Scanner(inputFile);

            //int tokenNumber = 1;
            //String token;
            while (scanner.hasNextLine()) {
                token = scanner.next();//.next will make tokens
                getToken(token);
            }
            //System.out.println("EOF");
            scanner.close();
        }
        catch(Exception e){

        }
    }

    private static void getToken(String token){
        tokenType Type = null;
        //System.out.println(token);
            if(token.equals("if")){
                Token newToken= new Token(token,Type.IF);
                System.out.println(newToken.value+" "+newToken.type);
            }
            if(token.equals("while")) {
                Token newToken= new Token(token,Type.WHILE);
                System.out.println(newToken.value+" "+newToken.type);
            }
            else if(token.equals("(")) {
                Token newToken= new Token(token,Type.ROUND_LPAREN);
                System.out.println(newToken.value+" "+newToken.type);
            }
            else if(token.equals(")")) {
                Token newToken= new Token(token,Type.ROUND_RPAREN);
                System.out.println(newToken.value+" "+newToken.type);
            }
            else if(Pattern.matches("^[a-zA-Z_][a-zA-Z0-9_]*$", token)) {
                Token newToken= new Token(token,Type.ID);
                System.out.println(newToken.value+" "+newToken.type);
            }
            else if(Pattern.matches("[+|-|*|/]", token)) {
                Token newToken= new Token(token,Type.OP);
                System.out.println(newToken.value+" "+newToken.type);
            }
            else if(token.equals("=")) {
                Token newToken= new Token(token,Type.ASSIGN);
                System.out.println(newToken.value+" "+newToken.type);
            }
            else if(token.equals("==")) {
                Token newToken= new Token(token,Type.COMPARE);
                System.out.println(newToken.value+" "+newToken.type);
            }
            else if(Pattern.matches("[0-9]+", token)) {
                int temp=Integer.valueOf(token);
                //System.out.print(temp);
                Token newToken= new Token(token,Type.INT);
                System.out.println(newToken.val+" "+newToken.type);
            }
            else {
                Token newToken= new Token(token,Type.INVALID);
                System.out.println(newToken.value+" "+newToken.type);
            }
    }

    public static void removeComments() throws IOException {
        try {
            File file=new File("tiny_language.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader inFile=new BufferedReader(fr);  //creates a buffering character input stream
            //StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
            int character=0;
            String str="";
            //int chk=0;
            while ((character=inFile.read())!=-1)
            {
                if (character=='/'){
                    int temp=inFile.read();
                    if ((char)temp=='/'){
                        inFile.readLine();
                        str+="\n";
                    }
                    else if((char)temp=='*'){
                        temp=inFile.read();
                        while((char)temp!='*'){
                            temp=inFile.read();
                        }
                        if ((char)temp=='/'){
                            inFile.read();
                        }
                    }
                }
                else{
                    str=str+(char)character;
                }
                //char c=(char)character;

            }
            //System.out.println(str);
            FileWriter outputFile = new FileWriter("removecomment.txt");
            outputFile.write(str);
            outputFile.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

class Token{
    tokenType type;
    String value;
    int val;
    Token(String val, tokenType myType){
        this.value=val;
        this.type=myType;
    }
    Token(int val, tokenType myType){
        this.val=val;
        this.type=myType;
    }
}