package IR;

import java.util.ArrayList;

/**
 *
 * @author ozil
 * GitHub : https://github.com/mmsaeed509
 *
 */

public class Main {

    public static void main(String[] args) {

        String Doc1 = "the best data science course";
        String Doc2 = "data science is popular";
        String Doc3 = "data science is the most important skill needed";
        String Doc4 = "data science is easy to learn";

        double equation1 = 0.0, equation2 = 0.0, equation3 = 0.0, equation4 = 0.0, equation5 = 0.0, equation6 = 0.0;
        ArrayList <Double> equationValues = new ArrayList<Double>();

        CosineSimilarity cosineSimilarity = new CosineSimilarity();

        equation1 = (cosineSimilarity.calculateCosineSimilarity(Doc1,Doc2));
        equation2 = (cosineSimilarity.calculateCosineSimilarity(Doc1,Doc3));
        equation3 = (cosineSimilarity.calculateCosineSimilarity(Doc1,Doc4));
        equation4 = (cosineSimilarity.calculateCosineSimilarity(Doc2,Doc3));
        equation5 = (cosineSimilarity.calculateCosineSimilarity(Doc2,Doc4));
        equation6 = (cosineSimilarity.calculateCosineSimilarity(Doc3,Doc4));

        equationValues.add(equation1);
        equationValues.add(equation2);
        equationValues.add(equation3);
        equationValues.add(equation4);
        equationValues.add(equation5);
        equationValues.add(equation6);

        /* ---------------------------------------------------------------------------------------- */
        
        sortDocs docs1 = new sortDocs("Doc1 and Doc2 Cosine Similarity",equation1);
        sortDocs docs2 = new sortDocs("Doc1 and Doc3 Cosine Similarity",equation2);
        sortDocs docs3 = new sortDocs("Doc1 and Doc4 Cosine Similarity",equation3);
        sortDocs docs4 = new sortDocs("Doc2 and Doc3 Cosine Similarity",equation4);
        sortDocs docs5 = new sortDocs("Doc2 and Doc4 Cosine Similarity",equation5);
        sortDocs docs6 = new sortDocs("Doc3 and Doc4 Cosine Similarity",equation6);

        ArrayList <sortDocs> docsArrayList = new ArrayList<sortDocs>();

        docsArrayList.add(docs1);
        docsArrayList.add(docs2);
        docsArrayList.add(docs3);
        docsArrayList.add(docs4);
        docsArrayList.add(docs5);
        docsArrayList.add(docs6);

        sortDocs.sortIndex(docsArrayList);

        System.out.println("");
        for (sortDocs doc : docsArrayList) {

            System.out.println(doc.getDocsCosineSimilarity() + " = " + doc.getValue());

        }

    }
}
