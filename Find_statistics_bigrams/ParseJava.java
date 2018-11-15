
import java.io.*;
import java.util.*;



/**
 * CPE-552
 *Ge Chang
 * 10410233
 * Chieh Shih
 * 10431509
 */
public class ParseJava {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] article = readFile("Frankenstein.txt");
//        System.out.println(article[2]);
        Set<String> shortwords = readShortWords("shortwords.txt");
//        System.out.println(shortwords);
        Map<String, Integer> matches = new HashMap<>();
        for (int i = 0; i < article.length; i++) {
            if (shortwords.contains(article[i])) {
                if (i > 0) {
                    String left = article[i - 1] + " " + article[i];
                    if (!matches.containsKey(left)) {
                        matches.put(left, 0);
                    }
                    matches.put(left, matches.get(left) + 1);
                }
                if (i < article.length - 1) {
                    String right = article[i] + " " + article[i + 1];
                    if (!matches.containsKey(right)) {
                        matches.put(right, 0);
                    }
                    matches.put(right, matches.get(right) + 1);
                }
            }
        }
//        System.out.println(matches);
        
        for(String key :matches.keySet()){
            if (matches.get(key)>30){
                System.out.println(key + ":" + matches.get(key));
            }
        }
    }

    private static Set<String> readShortWords(String fileName) throws FileNotFoundException, IOException {
        BufferedReader br1 = new BufferedReader(new FileReader(fileName));
        Scanner scanner = new Scanner(br1);

        Set<String> wordSet = new HashSet<>();
        while (scanner.hasNext()) {
            wordSet.add(scanner.next());
        }
        return wordSet;
    }

    private static String[] readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(' ');
        }
        return sb.toString().replaceAll("[^a-zA-Z\\s+]", " ")
                .replaceAll("(^\\s+|\\s+$)", "").toLowerCase().split("\\s+");
    }
}
