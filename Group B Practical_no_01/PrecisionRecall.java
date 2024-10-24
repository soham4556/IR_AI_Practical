import java.util.*;

public class PrecisionRecall {

    // Method to calculate precision
    public static double calculatePrecision(Set<String> retrievedDocs, Set<String> relevantDocs) {
        // Calculate the intersection of retrieved documents and relevant documents
        Set<String> relevantRetrieved = new HashSet<>(retrievedDocs);
        relevantRetrieved.retainAll(relevantDocs); // Keeps only the common documents

        // Precision = |Relevant Retrieved Documents| / |Retrieved Documents|
        if (retrievedDocs.isEmpty()) {
            return 0.0;  // To avoid division by zero
        }
        return (double) relevantRetrieved.size() / retrievedDocs.size();
    }

    // Method to calculate recall
    public static double calculateRecall(Set<String> retrievedDocs, Set<String> relevantDocs) {
        // Calculate the intersection of retrieved documents and relevant documents
        Set<String> relevantRetrieved = new HashSet<>(retrievedDocs);
        relevantRetrieved.retainAll(relevantDocs); // Keeps only the common documents

        // Recall = |Relevant Retrieved Documents| / |Relevant Documents|
        if (relevantDocs.isEmpty()) {
            return 0.0;  // To avoid division by zero
        }
        return (double) relevantRetrieved.size() / relevantDocs.size();
    }

    public static void main(String[] args) {
        // Define a set of retrieved documents (Answer Set A)
        Set<String> retrievedDocs = new HashSet<>(Arrays.asList("D1", "D2", "D3", "D4", "D5"));

        // Define the relevant documents to query q1 (Relevant Documents Rq1)
        Set<String> relevantDocs = new HashSet<>(Arrays.asList("D1", "D2", "D6"));

        // Calculate Precision
        double precision = calculatePrecision(retrievedDocs, relevantDocs);
        System.out.println("Precision: " + precision);

        // Calculate Recall
        double recall = calculateRecall(retrievedDocs, relevantDocs);
        System.out.println("Recall: " + recall);
    }
}
