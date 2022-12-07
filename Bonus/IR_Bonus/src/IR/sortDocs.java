package IR;

import java.util.ArrayList;

/**
 *
 * @author 00xWolf
 *  GitHub : @mmsaeed509
 * 﫥 Copyright : @Mahmoud Mohamed
 * 
 */

/* This Class To Store docs Cosine Similarity And Their Values and Sorting theme */

public class sortDocs {

    String docsCosineSimilarity ;
    Double value;


    public String getDocsCosineSimilarity() {
        return docsCosineSimilarity;
    }

    public void setDocsCosineSimilarity(String docsCosineSimilarity) {
        this.docsCosineSimilarity = docsCosineSimilarity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    sortDocs(String docsCosineSimilarity, Double value){

        this.docsCosineSimilarity = docsCosineSimilarity;
        this.value = value;

    }



    /* Sort By Value (from big to small ) */
    static void sortIndex(ArrayList<sortDocs> sortDocs) {

        /* One by one move boundary of unsorted subarray */
        for (int i = 0; i < sortDocs.size(); i++) {

            /* Find the minimum element in unsorted array */
            int min_idx = i;

            for (int j = i+1; j < sortDocs.size(); j++)
                if (sortDocs.get(j).value > sortDocs.get(min_idx).value)
                    min_idx = j;

            /* Swap the found minimum element with the first */
            sortDocs temp = sortDocs.get(min_idx);
            sortDocs.set(min_idx,sortDocs.get(i)) ;
            sortDocs.set(i,temp) ;

        }
    }


}
