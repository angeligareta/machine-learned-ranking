package com.company.preprocess;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Preprocess {
    private final Random randomGenerator = new Random();
    private final int scoreIncrement = 5;

    /**
     * Generates new dataset with the calculated score per common name for the received querywords.
     *
     * @param inputPath
     * @param outputPath
     * @param queryWords
     * @throws IOException
     */
    public void preprocessDataset(String inputPath, String outputPath, String[] queryWords) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(inputPath));
        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(outputPath));

        String row = csvReader.readLine(); // Header
        csvWriter.write(row + ", score" + "\n");

        row = csvReader.readLine();
        while (row != null) {
            ArrayList<String> data = new ArrayList<>(Arrays.asList(row.split(",")));

            // Common name is the variable we want to read and calculate score from
            final String commonName = data.get(1);
            final int commonNameScore = getScore(commonName, queryWords);
            data.add(Integer.toString(commonNameScore));

            // Write to new csv score
            csvWriter.write(String.join(",", data) + "\n");

            // Read next line
            row = csvReader.readLine();
        }

        csvReader.close();
        csvWriter.close();
    }

    /**
     * Get score for a name if contains certain query words.
     *
     * @param commonName
     * @param queryWords
     * @return
     */
    private int getScore(String commonName, String[] queryWords) {
        int numberOfOcurrences = 0;
        for (String queryWord : queryWords) {
            if (commonName.toLowerCase().contains(queryWord)) {
                numberOfOcurrences++;
            }
        }
        return numberOfOcurrences == 0 ? 0 : randomGenerator.nextInt(numberOfOcurrences * scoreIncrement);
    }
}
