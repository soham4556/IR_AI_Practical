import java.io.*;
import java.util.*;

public class Conflation {
    public static void main(String[] args) 
    throws IOException {
        try {
            File fi = new File("Input.txt");
            Scanner sc1 = new Scanner(new File("Input.txt"));
            int ch;
            do {
                System.out.println("1. Display the file");
                System.out.println("2. Remove Stop Words");
                System.out.println("3. Suffix Stripping");
                System.out.println("4. Count Frequency");
                System.out.println("Enter your choice");
                Scanner sc = new Scanner(System.in);
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        while (sc1.hasNext()) {
                            System.out.print(sc1.next() + " ");
                        }
                        System.out.println(" ");
                        break;
                    case 2:
                        remove_punctuation_and_stopwords(fi);
                        break;
                    case 3:
                        suffix_stripping();
                        break;
                    case 4:
                        frequency_count();
                        break;
                }
            } while (ch != 4);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static void remove_punctuation_and_stopwords(File fi) {
        try {
            Scanner sc_punctuation = new Scanner(fi);
            BufferedWriter out = new BufferedWriter(new FileWriter("without_punctuation_and_stopwords.txt"));
            while (sc_punctuation.hasNext()) {
                String str_p = sc_punctuation.next();
                String str_r = str_p.replaceAll("[^a-zA-Z\\s]", "");
                if (!str_r.toLowerCase().equals("the") && !str_r.toLowerCase().equals("is") && !str_r.toLowerCase().equals("and")
                        && !str_r.toLowerCase().equals("of") && !str_r.toLowerCase().equals("are") && !str_r.toLowerCase().equals("for")
                        && !str_r.toLowerCase().equals("in")) {
                    out.write(str_r + " ");
                }
            }
            out.close();
            System.out.println("File after punctuation and stopwords removal:");
            BufferedReader br = new BufferedReader(new FileReader("without_punctuation_and_stopwords.txt"));
            String z;
            while ((z = br.readLine()) != null)
                System.out.println(z);
            br.close();
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    private static void suffix_stripping() throws FileNotFoundException, IOException {
        Scanner sc1 = new Scanner(new File("without_punctuation_and_stopwords.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("suffix_stripping2.txt"));
        while (sc1.hasNext()) {
            String str = sc1.next();
            str = str + "/";
            if (str.endsWith("ier/")) {
                str = str.replaceAll("ier/", "y");
            } else if (str.endsWith("ied/")) {
                str = str.replaceAll("ied/", "y");
            } else if (str.endsWith("iage/")) {
                str = str.replaceAll("iage/", "y");
            } else if (str.endsWith("iest/")) {
                str = str.replaceAll("iest/", "y");
            } else if (str.endsWith("ies/")) {
                str = str.replaceAll("ies/", "y");
            } else if (str.endsWith("iful/")) {
                str = str.replaceAll("iful/", "y");
            } else if (str.endsWith("ify/")) {
                str = str.replaceAll("ify/", "y");
            } else if (str.endsWith("iness/")) {
                str = str.replaceAll("iness/", "y");
            } else if (str.endsWith("ness/")) {
                str = str.replaceAll("ness/", "y");
            } else if (str.endsWith("ily/")) {
                str = str.replaceAll("ily/", "y");
            } else if (str.endsWith("yer/")) {
                str = str.replaceAll("yer/", "y");
            } else if (str.endsWith("ying/")) {
                str = str.replaceAll("ying/", "y");
            } else if (str.endsWith("ys/")) {
                str = str.replaceAll("ys/", "y");
            } else if (str.endsWith("yable/")) {
                str = str.replaceAll("yable/", "y");
            } else if (str.endsWith("yful")) {
                str = str.replaceAll("yful", "y");
            } else if (str.endsWith("al/")) {
                str = str.replaceAll("al/", "y");
            } else if (str.endsWith("ly/")) {
                if (str.endsWith("ely/")) {
                    str = str.replaceAll("ely/", "e");
                } else {
                    str = str.replaceAll("ly/", "");
                }
            } else if (str.endsWith("ing/")) {
                str = str.replaceAll("ing/", "y");
            } else if (str.endsWith("ed/")) {
                str = str.replaceAll("ed/", "y");
            } else if (str.endsWith("es/")) {
                str = str.replaceAll("es/", "y");
            } else if (str.endsWith("s/")) {
                str = str.replaceAll("s/", " ");
            } else if (str.endsWith("is/")) {
                str = str.replaceAll("is", "y");
            } else if (str.endsWith("ment/")) {
                str = str.replaceAll("ment/", " ");
            } else if (str.endsWith("eing/")) {
                str = str.replaceAll("eing/", " ");
            } else if (str.endsWith("led/")) {
                str = str.replaceAll("led/", " ");
            } else if (str.endsWith("lex/")) {
                str = str.replaceAll("lex/", " ");
            } else if (str.endsWith("ling/")) {
                str = str.replaceAll("ling/", " ");
            }
            str = str.replace("/", " ");
            out.write(str + " ");
        }
        out.close();
        sc1.close();
        System.out.println("File after suffix stripping:");
        BufferedReader br = new BufferedReader(new FileReader("suffix_stripping2.txt"));
        String z;
        while ((z = br.readLine()) != null)
            System.out.println(z);
        br.close();
    }

    private static void frequency_count() throws FileNotFoundException, IOException {
        Scanner sc3 = new Scanner(new File("suffix_stripping2.txt"));
        Map<String, Integer> wordCount = new HashMap<>();
        while (sc3.hasNext()) {
            String word = sc3.next().toLowerCase();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        sc3.close();

        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
