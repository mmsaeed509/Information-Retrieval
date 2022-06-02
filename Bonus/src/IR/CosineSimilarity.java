package IR;

import java.text.DecimalFormat;

/**
 *
 * @author ozil
 * GitHub : https://github.com/mmsaeed509
 *
 */

import java.util.ArrayList;
import java.util.HashSet;

public class CosineSimilarity {


    /*
     * here we split the Doc ("Hi Mahmoud, How are you?")
     * e.g. if the user enters multiple words as seen above
     * the splitWord method will divide it into divided words
     * and store it in the wordsSet ArrayList
     * it acts as :-
     *   wordsSet.add("Hi");
     *   wordsSet.add("Mahmoud");
     *   wordsSet.add("How");
     *   wordsSet.add("are");
     *   wordsSet.add("you");
     *  */
    private ArrayList <String> splitWord(String word){

        ArrayList <String> wordSet = new ArrayList<String>();

        /* split by any special char like ! or space or @ //to get every word of the line */
        String[] splitWords = word.split("\\W+");

        for (String Word : splitWords) {

            /* convert all words to lower case */
            Word = Word.toLowerCase();
            /* Store words in wordSet */
            wordSet.add(Word);

        }

        /* return wordSet */
        return wordSet;
    }

    /*
    * here we reformat double values
    * we set Precision to 2
    * it means that if the value = 2.5678
    * we set the value to be = 2.57
    * */
    Double valuePrecision (Double value){

        DecimalFormat setValuePrecision = new DecimalFormat("#.##");
        return Double.parseDouble(setValuePrecision.format(value));

    }

    public Double calculateCosineSimilarity(String Doc1, String Doc2){

        int firstDocCounter=0,secondDocCounter2=0;
        int counter3 =0 ;
        int sum=0;
        double cosineSimilarity=0;
        double sumDoc1=0,sumDoc3=0;

        ArrayList<String> doc1Arr = splitWord(Doc1);
        ArrayList<String> doc2Arr = splitWord(Doc2);
        HashSet<String> allWords = new HashSet<>(doc1Arr);
        allWords.addAll(doc2Arr);

        for (String word:allWords) {

            for (String w:doc1Arr) {
                if (word.equals(w))
                    firstDocCounter++;
            }
            for (String w:doc2Arr) {
                if (word.equals(w))
                    secondDocCounter2++;
            }

            sumDoc1+=Math.pow(firstDocCounter,2.0);
            sumDoc3+=Math.pow(secondDocCounter2,2.0);
            counter3 = firstDocCounter* secondDocCounter2;
            sum+=counter3;
            firstDocCounter=secondDocCounter2=0;

        }

        sumDoc1 = Math.sqrt(sumDoc1);
        sumDoc3 = Math.sqrt(sumDoc3);

        cosineSimilarity = sum / (sumDoc1*sumDoc3);
        cosineSimilarity = valuePrecision(cosineSimilarity);

        return cosineSimilarity;
    }


}
