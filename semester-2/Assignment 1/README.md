
<h3 align="center"> Information Retrieval </h3>

<h4 align="center"> Assignment # 1 </h4>


Write a java code that:-

- Read 10 text files.
- Build the inverted index for those 10 file
- Read one word and list all files containing the word.

<ins>The following is not optional:</ins>

- The posting list should be implemented as a **linked list** of **java objects**
called **`Posting`**
```java
public class Posting {
 public Posting next = null;
 int docId;
 int dtf = 1; // document term frequency
```
- The terms can be stored in a **hashmap**
```java
public class DictEntry {
 int doc_freq = 0; // number of documents that contain the term
 int term_freq = 0; //number of times the term is mentioned in the collection
 Posting pList = null;
 ```
- after you run and test your project perform clean and compress it
you wil be given an email address to send it to.

- <ins>Start working inordinately there will be no late submission</ins>.
