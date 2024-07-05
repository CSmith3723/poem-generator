package cs;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class Poem {


    private String text;
    private Map<String, String> startingText = new LinkedHashMap<>();
    private List<String> partsOfSpeech = new ArrayList<>();
    private List<String> finalPoemArray = new ArrayList<>();
    private Collection<String> values = startingText.values();

    public void clearLists(){
        startingText.clear();
        partsOfSpeech.clear();
        finalPoemArray.clear();
        values.clear();
    }

    public List<String> getStartingPoem() {
        Collection<String> poemText = startingText.keySet();
        List<String> poemWords = new ArrayList<>();
        for (String word : poemText) {

            poemWords.add(word);
        }
        return poemWords;
    }


    public Poem() {

    }


   /* public void requestText() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\s");
        System.out.println("Please enter the text you wish to read:");

        StringBuilder input = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("**")) {
            input.append(line).append("\n"); // Append line with newline character
        }
        this.text = String.valueOf(input);
        scanner.close();
    }
*/

    public List<CoreMap> tagText(String userText) {

        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        StanfordCoreNLP pipeline;
        pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String newText = userText;

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(newText);

        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);


        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods


            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                // String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                //  System.out.println("Word: " + word + " pos: " + pos);
                this.startingText.put(word, pos);
            }
        }
        return sentences;
    }


    //public List<String> buildRandomText(List<String> startingPoem) {}


    public String poemOutputString(List<String> startingPoem){

        int randomNum;

        if (startingPoem != null) {
            if (startingPoem.size() > 200) {
                randomNum = (int) ((Math.random() * 9) + 12);
                generateText(startingPoem, randomNum);

            } else if (startingPoem.size() > 100) {

                randomNum = (int) ((Math.random() * 5) + 6);
                generateText(startingPoem, randomNum);
            } else if (startingPoem.size() > 15) {

                randomNum = (int) ((Math.random() * 2) + 2);
                generateText(startingPoem, randomNum);
            } else {
                System.out.println("Text too short to generate poem, please enter a longer text.");
            }

        } else {
            System.out.println("You did not enter any text.");
        }

        String poem= "";
        for (String word : finalPoemArray) {
            poem += (word + " ");
        }
        return poem;
    }



    public void generateText(List<String> thePoemText, int randomNum) {

        this.partsOfSpeech.addAll(values);

        for (int i = 0; i < partsOfSpeech.size() - 1; i += randomNum) {
            if (partsOfSpeech.get(i) != partsOfSpeech.get(i + 1)) {
                if(partsOfSpeech.get(i).startsWith("V") && partsOfSpeech.get(i + 1).startsWith("N")){
                    continue;
                }
                if (thePoemText.get(i).startsWith("'")) {
                    continue;
                }
                if(thePoemText.get(i).equals("n't")){
                    thePoemText.set(i, "not");
                }
                if(thePoemText.get(i).equals("'m")){
                    thePoemText.set(i, "am");
                }
                if(thePoemText.get(i).equals(")") ||thePoemText.get(i).equals("(")||thePoemText.get(i).equals("\"")){
                    continue;
                }
                this.finalPoemArray.add(thePoemText.get(i));

            }

        }
    }

}

