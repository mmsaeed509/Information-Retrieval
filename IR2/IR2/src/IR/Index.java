package IR;

import java.util.*;

/**
 *
 * @author 00xWolf
 *  GitHub : @mmsaeed509
 * 﫥 Copyright : @Mahmoud Mohamed
 * 
 */

/* This Class To Store File Name And Their Values and Sorting theme */

public class Index {

    String fileName;
    double value;

    Index(String fileName, double value){

        this.fileName = fileName;
        this.value = value;

    }

    /* Sort By Value (from big to small ) */
    static void sortIndex(ArrayList<Index> sortedIndex) {

        /* One by one move boundary of unsorted subarray */
        for (int i = 0; i < sortedIndex.size(); i++) {

            /* Find the minimum element in unsorted array */
            int min_idx = i;

            for (int j = i+1; j < sortedIndex.size(); j++)
                if (sortedIndex.get(j).value > sortedIndex.get(min_idx).value)
                    min_idx = j;

            /* Swap the found minimum element with the first */
            Index temp = sortedIndex.get(min_idx);
            sortedIndex.set(min_idx,sortedIndex.get(i)) ;
            sortedIndex.set(i,temp) ;

        }
    }

}

