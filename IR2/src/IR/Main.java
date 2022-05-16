package IR;

import java.io.*;
import java.util.*;

/**
 *
 * @author ozil
 * GitHub : https://github.com/mmsaeed509
 *
 */

public class Main {

    public static void main(String[] args) {

        JacCard test = new JacCard();

        try {

            File docsDirectoryPath = new File("./docs");
            String docsNames[] = docsDirectoryPath.list();
            ArrayList<Index> values = test.jacCardSimilarity("idea of March",docsNames);
            Index.sortIndex(values);

            for (Index sortIndex : values ) {

                System.out.println("File Name : " + sortIndex.fileName + "     Value = " + sortIndex.value);

            }



        }catch (Exception error){
            System.out.println(error.getMessage());
        }

    }
}
