/**
 * Rotate.java that uses input validated command line to send a text and a integer n rotation to encrypt a text
 *
 * @author Damian Poclitar
 */
public class Rotate {

    //Basic main method with validation to make sure that the user inputs no less or more than 2 in the command line
    //Sends the string and n rotation to caesar rotate
    public static void main(String[] args) {


        int cmndLnSize = args.length;


        if (cmndLnSize < 1){
            System.out.println("Too few parameters!");
            System.out.println("Usage: java Rotate n \"cipher text\"");
            System.exit(0);
        }
        else if (cmndLnSize > 2){
            System.out.println("Too many parameters!");
            System.out.println("Usage: java Rotate n \"cipher text\"");
            System.exit(0);
        }
        else {
            String text = args[1];
            String freqText = args[0];
            int freq = Integer.parseInt(freqText);


            Caesar rotation = new Caesar();

            System.out.println(rotation.rotate(text, freq));
        }

    }
}
