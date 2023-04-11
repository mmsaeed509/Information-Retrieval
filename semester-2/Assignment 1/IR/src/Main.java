/*
 * ----------------------------------
 *
 * 﫥  @author   : 00xWolf
 *   GitHub    : @mmsaeed509
 *   Developer : Mahmoud Mohamed
 *
 * ---------------------------------
 *
*/

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /* docs directory */
    private static final String DOCS_DIR = "docs/";

    /* get docs names */
    private static String[] getFileNames(String docsDir) {

        /* calculate the No. files/docs in the directory */
        File dir = new File(DOCS_DIR);
        File[] files = dir.listFiles();
        int numFiles = files.length;

        String[] fileNames = new String[numFiles];

        for (int i = 0; i < numFiles; i++) {

            fileNames[i] = docsDir + (i + 1) + ".txt";

        }

        return fileNames;
        
    }
    public static void main(String[] args) throws IOException {

        String[] fileNames = getFileNames(DOCS_DIR);

        InvertedIndex index = new InvertedIndex();
        index.buildIndex(fileNames);

        while (true){

            System.out.print(COLORS.YELLOW_FOREGROUND + "\n[+] Enter a word: " + COLORS.RESET_COLOR);
            Scanner readInput = new Scanner(System.in);
            String word = readInput.nextLine();

            if (word.equals("stop"))
                break;

            System.out.println(COLORS.YELLOW_FOREGROUND + "\n--------------------------------\n" + COLORS.RESET_COLOR);
            index.printPostingList(word);


        }

    }

}


