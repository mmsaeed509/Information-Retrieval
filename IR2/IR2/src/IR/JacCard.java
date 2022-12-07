package IR;

import java.io.*;
import java.util.*;

/**
 *
 * @author 00xWolf
 *  GitHub : @mmsaeed509
 * 﫥 Copyright : @Mahmoud Mohamed
 * 
 */

public class JacCard {

    /* Read Words From Docs */
    private HashSet<String> readDocs(String docsName) throws Exception {

        /* docs path to read files inside */
        String docsPath = "./docs/"+docsName;
        BufferedReader docs = new BufferedReader(new FileReader(docsPath));
        String line;
        HashSet<String> wordsSet = new HashSet<>();

        while ((line = docs.readLine()) != null) {

            /* split by any special char like ! or space or @ //to get every word of the line */
            String[] words = line.split("\\W+");

            for (String word : words) {

                word = word.toLowerCase();
                wordsSet.add(word);

            }

        }

        return wordsSet;
    }

    /* Apply Intersect (AND) */
    private HashSet<String> intersect(HashSet<String> query, HashSet<String> docsWords) {

        /*
        * here we clone data to avoid any changes in the original doc's data
        * if we use original docs, the function will overwrite original docs
        *
        * docClone (ozil , ali , salah)
        * queryClone (ozil , ali, ahmed)
        * newQuery(ozil , ali, ahmed)
        *  */
        HashSet<String>queryClone = new HashSet<String>(query);
        HashSet<String>docsWordsClone = new HashSet<String>(docsWords);

        if (queryClone.size()<=docsWordsClone.size()){


            HashSet<String> newQuery = new HashSet<>(queryClone);/* newQuery(ozil , ali, ahmed) */
            /* docsWordsClone (ozil , ali , salah) */
            newQuery.removeAll(docsWordsClone);/* newQuery (ozil, ali) */
            /* queryClone (ozil , ali, ahmed)
            queryClone.removeAll(newQuery);/* queryClone(ozil , ali) */
            return queryClone;

        }else{

            HashSet<String> newQuery = new HashSet<>(docsWordsClone);
            newQuery.removeAll(queryClone);
            docsWordsClone.removeAll(newQuery);
            return docsWordsClone;

        }

    }


    /* Apply union (OR) */
    private HashSet<String> union(HashSet<String>query,HashSet<String> docsWords){

        HashSet<String>queryClone = new HashSet<String>(query);
        HashSet<String>docsWordsClone = new HashSet<String>(docsWords);
        queryClone.addAll(docsWordsClone);

        return queryClone;

    }

    /*
    * here we split query ("Hi Mahmoud, How are you?")
    * e.g. if the user enters multiple words as a query as seen above
    * the splitQuery method will divide it into divided words
    * and store it in the wordsSet HashSet
    * it acts as :-
    *   wordsSet.add("Hi");
    *   wordsSet.add("Mahmoud");
    *   wordsSet.add("How");
    *   wordsSet.add("are");
    *   wordsSet.add("you");
    *
    *  */
    private HashSet<String>splitQuery(String query){

        HashSet<String> wordsSet = new HashSet<>();
        /* split by any special char like ! or space or @ //to get every word of the line */
        String[] words = query.split("\\W+");

        for (String word : words) {

            word = word.toLowerCase();
            wordsSet.add(word);

        }

        return wordsSet;

    }

    /* Apply Similarity */
    public ArrayList<Index> jacCardSimilarity(String query , String []docsNames) throws Exception {

        ArrayList<Index>values = new ArrayList<>();

        for (String docsName :docsNames){

            HashSet<String> querySet = splitQuery(query);
            HashSet<String>  wordSet = readDocs(docsName);

            double intersectValue = (double) intersect(querySet,wordSet).size();
            double unionValue = (double) union(querySet,wordSet).size();
            Index index = new Index(docsName,intersectValue/unionValue);
            values.add(index);

        }

        return values;

    }

}
