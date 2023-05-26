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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class WebCrawler extends HashMap<String, DictEntry> {

    private HashMap<String, DictEntry> index;

    public WebCrawler() {

        index = new HashMap<>();

    }

    /**
     * Crawls the given URLs, fetches their content, extracts words, and builds the inverted index.
     *
     * @param URLs The array of URLs to crawl.
     * @throws IOException If an error occurs while fetching the URL content.
     */
    public void crawlAndBuildIndex(String[] URLs) throws IOException {

        System.out.println(COLORS.BLUE_BOLD + "\n[*]" + COLORS.GREEN_BOLD + " Extracting Information From Pages...\n" + COLORS.RESET_COLOR);

        for (String url : URLs) {

            String content = fetchURLContent(url);
            String[] words = extractWords(content);

            /* Process each word */
            for (String word : words) {

                word = word.toLowerCase();
                DictEntry entry = index.get(word);

                if (entry == null) {

                    entry = new DictEntry();
                    index.put(word, entry);

                }

                /* Update the term frequency for the word */
                entry.term_freq++;

            }

        }

    }

    /**
     * Fetches the content of the given URL.
     *
     * @param url The URL to fetch the content from.
     * @return The content of the URL as a string.
     * @throws IOException If an error occurs while fetching the URL content.
     */
    private String fetchURLContent(String url) throws IOException {

        System.out.println(COLORS.BLUE_BOLD + "    [+]" + COLORS.GREEN_BOLD + " Fetching URL " + COLORS.BLACK_BACKGROUND
                            + COLORS.PURPLE_BOLD + url + "..." + COLORS.RESET_COLOR);

        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;

        try {

            URL urlObject = new URL(url);
            reader = new BufferedReader(new InputStreamReader(urlObject.openStream()));

            String line;

            while ((line = reader.readLine()) != null) {

                content.append(line).append("\n");

            }

        } finally {

            if (reader != null) {

                reader.close();

            }

        }

        return content.toString();

    }

    /**
     * Extracts words from the given content.
     *
     * @param content The content to extract words from.
     * @return An array of words extracted from the content.
     */
    private String[] extractWords(String content) {

        return content.split("\\s+");

    }

    /**
     * Retrieves the built inverted index.
     *
     * @return The inverted index as a HashMap.
     */
    public HashMap<String, DictEntry> getIndex() {

        return index;

    }

}
