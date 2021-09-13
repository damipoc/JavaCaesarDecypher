/**
 * Caesar class that implements the RotationCipher interface
 *<p>
 *  RotationCipher contains the methods: rotate, decipher, frequencies, chiSquared.
 * rotate will take the string and n integer and return the rotated string
 * decipher will make use of all the other methods in order to return the decipher string
 * frequency will calculate the frequency of each letter in the decipher text
 * chiSquared will do the equation with knowFreq array and the generated array from frequency in order to get χsquared
 *
 * @author Damian Poclitar
 */

//Implementing RotationCipher interface in Caesar
public class Caesar implements RotationCipher {

    public static String rotate(String s, int n){
        String result = RotationCipher.rotate(s, n);
        return result;
    }

    public static String decipher(String s){
        String result = RotationCipher.decipher(s);
        return result;
    }

}
