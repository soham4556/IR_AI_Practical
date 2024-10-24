import java.util.*;

public class InvertedFilesRetrieval {

    // Map to hold the inverted index (word -> list of document IDs)
    private Map<String, Set<String>> invertedIndex;

    // Constructor to initialize the inverted index
    public InvertedFilesRetrieval() {
        invertedIndex = new HashMap<>();
    }

    // Method to add a document to the inverted index
    public void addDocument(String docId, String content) {
        String[] tokens = content.toLowerCase().split("\\W+"); // Split on non-word characters, case-insensitive
        for (String token : tokens) {
            if (!invertedIndex.containsKey(token)) {
                invertedIndex.put(token, new HashSet<>());
            }
            invertedIndex.get(token).add(docId); // Add document ID to the word's set
        }
    }

    // Method to retrieve documents matching a query (all words must be present)
    public Set<String> retrieveDocuments(String query) {
        String[] queryWords = query.toLowerCase().split("\\W+");
        Set<String> result = new HashSet<>();

        for (String word : queryWords) {
            if (invertedIndex.containsKey(word)) {
                if (result.isEmpty()) {
                    result.addAll(invertedIndex.get(word));
                } else {
                    result.retainAll(invertedIndex.get(word)); // Only keep common documents
                }
            } else {
                result.clear(); // If any word is not found, no documents match the query
                break;
            }
        }

        return result;
    }

    // Main method to demonstrate document retrieval using inverted files
    public static void main(String[] args) {
        InvertedFilesRetrieval retrievalSystem = new InvertedFilesRetrieval();

        // Adding documents to the system (simulating a document database)
        retrievalSystem.addDocument("doc1", "The quick brown fox jumps over the lazy dog");
        retrievalSystem.addDocument("doc2", "The quick red fox jumps over the sleepy cat");
        retrievalSystem.addDocument("doc3", "The brown fox is quick and agile");
        retrievalSystem.addDocument("doc4", "The lazy dog sleeps all day");

        // Displaying the inverted index (optional, for debugging)
        System.out.println("Inverted Index: " + retrievalSystem.invertedIndex);

        // Reading user input for a search query
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a query to search for documents (e.g., 'quick fox'):");
        String query = scanner.nextLine();

        // Retrieve documents based on the user's query
        Set<String> result = retrievalSystem.retrieveDocuments(query);

        // Output the search result
        if (result.isEmpty()) {
            System.out.println("No documents found for the query: " + query);
        } else {
            System.out.println("Documents matching the query '" + query + "': " + result);
        }

        scanner.close();
    }
}
