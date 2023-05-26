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

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    /* docs directory */
    private static final String DOCS_DIR = "docs/";

    /* URLs For Web Crawler */
    public static final String[] URLs = {

            "https://en.wikipedia.org/wiki/C%2B%2B",
            "https://en.wikipedia.org/wiki/C_(programming_language)",
            "https://en.wikipedia.org/wiki/Python_(programming_language)",
            "https://en.wikipedia.org/wiki/Java",
            "https://en.wikipedia.org/wiki/Bash_(Unix_shell)",
            "https://en.wikipedia.org/wiki/Linux",
            "https://en.wikipedia.org/wiki/Computer_security",
            "https://en.wikipedia.org/wiki/Reverse_engineering",
            "https://en.wikipedia.org/wiki/Malware_analysis"

    };


    /* get docs names */
    private static String[] getFileNames(String docsDir) {

        /* Create a new `File` object. */
        File directory = new File(DOCS_DIR);
        /* retrieve an array representing the files in the directory. */
        File[] files = directory.listFiles();
        /* calculate the No. files/docs in the directory. */
        int numFiles = files.length;

        /* create a new array of string`fileNames`
        to store files names with size = No. files in the directory. */
        String[] fileNames = new String[numFiles];

        /* notify to list all files (you can remove it) */
        System.out.println(COLORS.BLUE_BOLD + "\n[*]" + COLORS.GREEN_BOLD + " Listing All files in " + COLORS.CYAN_BOLD
                            + "`" + docsDir + "`" + COLORS.GREEN_BOLD + " directory. \n" + COLORS.RESET_COLOR);

        /* Loop in the directory to get all files names */
        for (int i = 0; i < numFiles; i++) {

            /* print all listed files (you can remove it) */
            System.out.println(COLORS.BLUE_BOLD + "    [+]" + COLORS.GREEN_BOLD + " fileNames[" + COLORS.PURPLE_BOLD
                               + i + COLORS.GREEN_BOLD + "]" + COLORS.BLUE_BOLD + " = " + COLORS.CYAN_BOLD + "`" +
                                docsDir + (i + 1 ) + ".txt`" + COLORS.RESET_COLOR);

            fileNames[i] = docsDir + (i + 1) + ".txt";

        }

        return fileNames;

    }

    public static void main(String[] args) throws IOException {

        /* creates a new scanner instance to read input from the user */
        Scanner readInput = new Scanner(System.in);

        /* get docs file names */
        String[] fileNames = getFileNames(DOCS_DIR);

        /* create a new `InvertedIndex` object */
        InvertedIndex indexFromInvertedIndex = new InvertedIndex();
        /* Build the Inverted Index */
        indexFromInvertedIndex.buildIndex(fileNames);

        /* create a new `WebCrawler` object */
        WebCrawler webCrawler = new WebCrawler();

        /* Crawl the URLs and build the inverted index */
        webCrawler.crawlAndBuildIndex(URLs);

        /* Get the built inverted index from the web crawler */
        HashMap<String, DictEntry> WebCrawlerIndex = webCrawler.getIndex();

        /* create a new object ALL_INDEX'S that combines indexFromInvertedIndex and WebCrawlerIndex */
        HashMap<String, DictEntry> ALL_INDEX = new HashMap<>(indexFromInvertedIndex);
        ALL_INDEX.putAll(WebCrawlerIndex);

        /* create a new `CosineSimilarity` object */
        CosineSimilarity cosineSimilarity = new CosineSimilarity(ALL_INDEX, fileNames);

        while (true){

            System.out.print(COLORS.YELLOW_BOLD + "\n[+] Enter a word: " + COLORS.RESET_COLOR);
            String query = readInput.nextLine();

            if (query.equals("stop"))
                break;

            System.out.println(COLORS.YELLOW_BOLD + "\n--------------------------------\n" + COLORS.RESET_COLOR);
            // indexFromInvertedIndex.printPostingList(query);
            cosineSimilarity.calculateSimilarity(query);


        }

        /* Close the Scanner object */
        readInput.close();

    }
}
