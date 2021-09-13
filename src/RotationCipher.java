/**
 * RotationCipher contains the methods: rotate, decipher, frequencies, chiSquared.
 * rotate will take the string and n integer and return the rotated string
 * decipher will make use of all the other methods in order to return the decipher string
 * frequency will calculate the frequency of each letter in the decipher text
 * chiSquared will do the equation with knowFreq array and the generated array from frequency in order to get χsquared
 *
 * @author Damian Poclitar
 */

import java.util.*;

public interface RotationCipher {


    //Rotate method loops equal to character count in the given string
    //First if statement is used to make sure that code only works with letters and not spaces, numbers or symbols
    //Second if statement keeps a note if the character is lowercase or not so that it knows if it should capitalise at the end of the loop
    //Loop makes the character lowercase and then compares it to the alphabet array, noting its position
    //Then it adds the inputted freq int to the character position and uses the result to locate the new character in the alphabet array
    //A while loop is used incase the resulted position is above 26 and an if statement is used if the result is less than 0
    //It then puts the resulted character in a new array, this is done for every letter character in the given string
    //At the end it sees if the picked character was an uppercase or not, if yes it makes it uppercase
    //After the loop is done, the array of character is converted into a string that is returned
    static String rotate(String s, int n){
        Character[] alphabet = {'a','b','c','d','e',
                'f','g','h','i','j',
                'k','l','m','n','o',
                'p','q','r','s','t',
                'u','v','w','x','y',
                'z'};

        char[] splitArray = s.toCharArray();
        int rotationShift = n;
        int score;

        for (int i=0; i < s.length(); i++){
            boolean uppercase = false;
            if (Character.isLetter(splitArray[i])){
                if (Character.isUpperCase(splitArray[i])){
                    uppercase = true;
                }
                splitArray[i] = Character.toLowerCase(splitArray[i]);
                int position = Arrays.asList(alphabet).indexOf(splitArray[i]);
                score = rotationShift + position;
                while (score >= alphabet.length){
                    score = score - alphabet.length;
                }
                if (score < 0){
                    score = score + alphabet.length;
                }
                splitArray[i] = alphabet[score];

                if (uppercase){
                    splitArray[i] = Character.toUpperCase(splitArray[i]);
                }
            }
        }

        String result = new String (splitArray);

        return result;

    }


    //decipher uses a 0-26 loop to try all the combinations of text and see which one is the closest to english
    //The loop starts with it sending the text to rotate method with the loop count as the rotate frequency
    //Then the resulted string is simplified without spaces, symbols or numbers
    //The given knownFreq array is updated by multiplying each letter freq by the length of the simplified string
    //After that a freq array is created by sending the simplified string to the frequencies method
    //Both the known freq array and the generated freq array are sent to chiResult
    //The returned chisquared is used in an if statement, if the result is smaller than the current known smallest result-
    //-then the current result will be stored as the known smallest result and the current loop will be stored as the possible rotation
    //After the loop is done, the original encrypted text and the stored possible rotation int is sent to rotate and the result is returned
    static String decipher(String s){

        String stringToDecipher = s;
        double smallestChi = 999999;
        int likelyFreq = 0;


        for (int i =0; i < 26; i++){
            String stringCombination = RotationCipher.rotate(stringToDecipher, i);

            String simplifiedString = stringCombination.toLowerCase().replaceAll("[ .,!@#$%^&*()_+=0987654321]", "");
            double[] knownFreq = {0.0855, 0.0160, 0.0316, 0.0387, 0.1210,
                    0.0218, 0.0209, 0.0496, 0.0733, 0.0022,
                    0.0081, 0.0421, 0.0253, 0.0717, 0.0747,
                    0.0207, 0.0010, 0.0633, 0.0673, 0.0894,
                    0.0268, 0.0106, 0.0183, 0.0019, 0.0172,
                    0.0011
            };

            for (int f= 0; f < 26; f++){
                knownFreq[f] = knownFreq[f] * simplifiedString.length();
            }

            double[] stringFreq = RotationCipher.frequencies(simplifiedString);


            double chiResult = RotationCipher.chiSquared(stringFreq, knownFreq);

            if (smallestChi > chiResult){
                smallestChi = chiResult;
                likelyFreq = i;
            }
        }

        String decodedText = (RotationCipher.rotate(stringToDecipher, likelyFreq));

        return decodedText;
    }

    //frequencies uses HashMap to count the amount of characters in the given string
    //The given string is always simplified so it knows it will only work with letters
    //We create a charCount map and populate it with letters from a-z and count 0 for each
    //Then a loop is used equal to the simplified string length
    //The loop checks each character from the string and adds 1 count to the same character in the created map
    //After the loop, the Map is converted into a double array which only contains the char count and is returned
    static double[] frequencies(String s){

        String simplifiedString = s;

        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>(){};

        for (char ch = 'a'; ch <= 'z'; ++ch) {
            charCount.put(ch, 0);
        }

        for (int i = 0; i < simplifiedString.length(); i++){
            char c = simplifiedString.charAt(i);
            Integer val = charCount.get(c);
            if (val != null){
                charCount.put(c, new Integer(val + 1));
            }
            else {
                charCount.put(c,  1);
            }
        }
        Collection<Integer> values = charCount.values();
        Integer[] intArray = values.toArray(new Integer[values.size()]);
        double[] doubleArray = new double[intArray.length];
        for (int i = 0; i<intArray.length; i++){
            doubleArray[i] = (double)intArray[i];
        }

        return doubleArray;
    }


    //chiSquared is the given formula in a method
    //A loop for the 26 frequencies is used for the calculation
    //It first does the subtraction
    //Then the result is squared
    //And the squared result is divided by the knowFreq which is added with the current total to the new current total
    //After the loop the result is returned
    static double chiSquared(double[] a, double[] b){

        double chiSqrd = 0;

        for (int i = 0; i < 26; i++){

            double result = (a[i] - b[i]);
            result = Math.pow(result, 2);
            chiSqrd = chiSqrd + (result / b[i]);
        }


        return chiSqrd;

    }
}
