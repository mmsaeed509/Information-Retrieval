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


import java.io.*;
import java.util.HashMap;

public class InvertedIndex extends HashMap<String, DictEntry> {

    public void buildIndex(String[] fileNames) throws IOException {
        int docId = 0;

        for (String fileName : fileNames) {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    word = word.toLowerCase();
                    DictEntry entry = getOrDefault(word, new DictEntry());

                    entry.term_freq++;

                    Posting posting = entry.pList;
                    Posting prev = null;

                    while (posting != null && posting.docID < docId) {
                        prev = posting;
                        posting = posting.next;
                    }

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

                    put(word, entry);
                }
            }

            docId++;
            reader.close();
        }

        for (DictEntry entry : values()) {
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
        DictEntry entry = get(term.toLowerCase());

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
