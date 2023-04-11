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

import java.io.*;
import java.util.HashMap;

public class InvertedIndex {

    private HashMap<String, DictEntry> index;

    public InvertedIndex() {

        index = new HashMap<String, DictEntry>();

    }

    public void buildIndex(String[] fileNames) throws IOException {

        int docId = 0;

        for (String fileName : fileNames) {

            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                /*
                * split by any special char like !, space, @, etc...
                * to get every word of the line and store it in an array
                * e.g. here we split query ("Hi Mahmoud, How are you?")
                * it acts as :-
                *   word.add("Hi")
                *   word.add("Mahmoud");
                *   word.add("How");
                *   word.add("are");
                *   word.add("you");
                * */
                String[] words = line.split("\\s+");

                for (String word : words) {

                    /* convert all words to lower case */
                    word = word.toLowerCase();
                    DictEntry entry = index.get(word);

                    /* If the word is not already in the index, create a new entry for it. */
                    if (entry == null) {

                        entry = new DictEntry();
                        index.put(word, entry);

                    }

                    /* Update the term frequency for the word. */
                    entry.term_freq++;

                    /* Find the posting for the current document (if it exists).*/
                    Posting posting = entry.pList;
                    Posting prev = null;

                    while (posting != null && posting.docID < docId) {

                        prev = posting;
                        posting = posting.next;

                    }

                    /*
                    * If the posting for the current document exists, update its dtf(document term frequency).
                    * Otherwise, create a new posting for the current document.
                    * */
                    if (posting != null && posting.docID == docId) {

                        posting.dtf++;

                    } else {

                        Posting newPosting = new Posting();
                        newPosting.docID = docId;

                        if (prev == null) {

                            entry.pList = newPosting;

                        } else {

                            prev.next = newPosting;

                        }

                        newPosting.next = posting;

                    }

                }

            }

            /* Close the file reader and increment the document ID for the next document. */
            docId++;
            reader.close();

        }

        /* loop through the inverted index and calculate the doc_freq(No. documents that contain the term). */
        for (DictEntry entry : index.values()) {

            entry.doc_freq = entry.pList != null ? getDocFreq(entry.pList) : 0;

        }

    }

    private int getDocFreq(Posting posting) {
        int df = 0;
        while (posting != null) {
            df++;
            posting = posting.next;
        }
        return df;
    }

    public void printPostingList(String term) {

        DictEntry entry = index.get(term.toLowerCase());
        if (entry == null) {

            System.out.println(COLORS.RED_BOLD + "[✘] Term not found" + COLORS.RESET_COLOR);

        } else {
            Posting posting = entry.pList;
            System.out.println(COLORS.BLUE_BOLD + "[+] " + COLORS.GREEN_BOLD + "Term frequency: " + entry.term_freq);
            System.out.println(COLORS.BLUE_BOLD + "[+] " + COLORS.GREEN_BOLD + "Document frequency: " + entry.doc_freq);
            System.out.println(COLORS.BLUE_BOLD + "[+] " + COLORS.GREEN_BOLD + "Posting list: ");
            while (posting != null) {
                System.out.print(COLORS.BLUE_BOLD + "    ==> " + COLORS.GREEN_BOLD + "Docs No. " + COLORS.CYAN_BOLD + posting.docID + COLORS.GREEN_BOLD + " [ " + COLORS.PURPLE_BOLD + posting.dtf + COLORS.GREEN_BOLD +  " ] \n");
                posting = posting.next;
            }
            System.out.println(COLORS.RESET_COLOR);
        }
    }

}
