/**
 * ----------------------------------
 *
 * @author      : 00xWolf
 *   GitHub    : @mmsaeed509
 *   Developer : Mahmoud Mohamed
 * 﫥  Copyright : Mahmoud Mohamed
 *
 * ---------------------------------
 *
 **/

import java.text.DecimalFormat;
import java.util.*;

public class CosineSimilarity {

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

    private HashMap<String, DictEntry> index;
    private String[] fileNames;

    public CosineSimilarity(HashMap<String, DictEntry> index, String[] fileNames) {

        this.index = index;
        this.fileNames = fileNames;

    }

    public void calculateSimilarity(String query) {

        /* Convert query to lowercase and split into words */
        String[] queryWords = query.toLowerCase().split("\\s+");

        /* Calculate the term frequency in the query */
        Map<String, Integer> queryVector = new HashMap<>();

        for (String word : queryWords) {

            queryVector.put(word, queryVector.getOrDefault(word, 0) + 1);

        }

        /* Calculate the cosine similarity scores for each file */
        Map<String, Double> similarityScores = new HashMap<>();

        for (String fileName : fileNames) {

            double dotProduct = 0.0;
            double queryMagnitude = 0.0;
            double fileMagnitude = 0.0;

            for (String word : queryVector.keySet()) {

                DictEntry entry = index.get(word);

                if (entry != null) {

                    /* Calculate the query weight and file weight */
                    double queryWeight = queryVector.get(word) * idf(entry.doc_freq, fileNames.length);
                    double fileWeight = tf(entry, fileName) * idf(entry.doc_freq, fileNames.length);

                    /* Calculate the dot product and magnitudes */
                    dotProduct += queryWeight * fileWeight;
                    queryMagnitude += Math.pow(queryWeight, 2);
                    fileMagnitude += Math.pow(fileWeight, 2);

                }

            }

            /* Calculate the cosine similarity */
            double cosineSimilarity = dotProduct / (Math.sqrt(queryMagnitude) * Math.sqrt(fileMagnitude));
            similarityScores.put(fileName, cosineSimilarity);

        }

        /* Sort the similarity scores in descending order */
        List<Map.Entry<String, Double>> sortedScores = new ArrayList<>(similarityScores.entrySet());
        sortedScores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        /* Display the ranked files */
        System.out.println(COLORS.BLUE_BOLD + "\n[*]" + " Ranked Files: " + COLORS.RESET_COLOR);

        for (int i = 0; i < Math.min(sortedScores.size(), 10); i++) {

            String fileName = sortedScores.get(i).getKey();
            // double similarity = sortedScores.get(i).getValue();
            double similarity = valuePrecision(sortedScores.get(i).getValue());
            System.out.println(COLORS.BLUE_BOLD + "    ==>" + COLORS.GREEN_BOLD + " File: " + COLORS.CYAN_BOLD + "`"
                               + fileName + "`" + COLORS.GREEN_BOLD + ", Cosine Similarity: " + COLORS.PURPLE_BOLD +
                                similarity + COLORS.RESET_COLOR);

        }

    }

    private double idf(int docFreq, int totalDocs) {

        /* Calculate the inverse document frequency */
        return Math.log((double) totalDocs / (double) docFreq);

    }

    private double tf(DictEntry entry, String fileName) {

        /* Retrieve the term frequency in the specified file */
        Posting posting = entry.pList;

        while (posting != null) {

            if (posting.docID == getFileIndex(fileName)) {

                return posting.dtf;

            }

            posting = posting.next;

        }

        return 0.0;

    }

    private int getFileIndex(String fileName) {

        /* Get the index of the specified file in the fileNames array */
        for (int i = 0; i < fileNames.length; i++) {

            if (fileNames[i].equals(fileName)) {

                return i;

            }

        }

        return -1;

    }

}
