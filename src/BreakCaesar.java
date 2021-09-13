/**
 * BreakCaesar.java that uses input validated command line to decript a encrypted text
 *
 * @author Damian Poclitar
 */

public class BreakCaesar {


    //Basic main method with validation to make sure that the user inputs no less or more than 1 in the command line
    //Sends the string to caesar decipher
    public static void main(String[] args){

        int cmndLnSize = args.length;

        if (cmndLnSize == 0){
            System.out.println("Too few parameters!");
            System.out.println("Usage: java BreakCaesar \"cipher text\"");
            System.exit(0);
        }
        else if (cmndLnSize > 1){
            System.out.println("Too many parameters!");
            System.out.println("Usage: java BreakCaesar \"cipher text\"");
            System.exit(0);
        }
        else {
            String text = args[0];

            Caesar decrypt = new Caesar();

            System.out.println(decrypt.decipher(text));
        }
    }

}
