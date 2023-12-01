import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


class Result {

    /*
     * Complete the 'getProductsInRange' function below.
     *
     * URL for cut and paste
     * https://jsonmock.hackerrank.com/api/inventory?category=<category>&page=<pageNumber>
     *
     * The function is expected to return an Integer value.
     * The function accepts String category, Integer minPrice and Integer maxPrice as arguments.
     * 
     */
  






    public static int getProductsInRange(String category, int minPrice, int maxPrice) {
        try {
            String apiUrl = "https://jsonmock.hackerrank.com/api/inventory?category=" + category;
            int page = 1;
            int totalCount = 0;

            while (true) {
                URL url = new URL(apiUrl + "&page=" + page);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        JsonParser parser = new JsonParser();
                        JsonObject jsonResponse = parser.parse(response.toString()).getAsJsonObject();

                        JsonArray products = jsonResponse.getAsJsonArray("data");

                        for (JsonElement productElement : products) {
                            JsonObject product = productElement.getAsJsonObject();
                            int price = product.getAsJsonPrimitive("price").getAsInt();

                            if (price >= minPrice && price <= maxPrice) {
                                totalCount++;
                            }
                        }


                        if (page < jsonResponse.getAsJsonPrimitive("total_pages").getAsInt()) {
                            page++;
                        } else {
                            break;
                        }
                    }
                } else {
                    System.out.println("Failed to fetch data. Response Code: " + responseCode);
                    break;
                }
            }

            return totalCount;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String category = bufferedReader.readLine();

        int minPrice = Integer.parseInt(bufferedReader.readLine().trim());
        int maxPrice = Integer.parseInt(bufferedReader.readLine().trim());
  
        int result = Result.getProductsInRange(category, minPrice, maxPrice);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
