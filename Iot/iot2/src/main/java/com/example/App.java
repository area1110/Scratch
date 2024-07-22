package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting!");

        /*
         * String jsonString =
         * "{\"name\":\"John\", \"details\":{\"address\":\"123 Main St\", \"phones\":[{\"type\":\"home\",\"number\":\"123-456-7890\"},{\"type\":\"work\",\"number\":\"987-654-3210\"}]}}"
         * ;
         * JSONObject jsonObject = new JSONObject(jsonString);
         */

        JSONObject jsonObject = readJsonFile("resources/data.json");
        String keyPath = "details.phones.number"; // Dynamic input key path
        boolean exists = checkNestedProperty(jsonObject, keyPath);
        System.out.println("Property  : " + exists);

        System.out.println("Finished!");
    }

    private static JSONObject readJsonFile(String filePath) {
        JSONParser parser = new JSONParser();
        // Use JSONObject for simple JSON and JSONArray for array of JSON.
        JSONObject data = null;
        try {
            data = (JSONObject) parser.parse(
                    new FileReader(filePath));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static boolean checkNestedProperty(Object currentObject, String keyPath) {
        String[] keys = keyPath.split("\\.");

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];

            if (currentObject instanceof JSONObject && ((JSONObject) currentObject).containsKey(key)) {
                currentObject = ((JSONObject) currentObject).get(key);
            } else if (currentObject instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) currentObject;
                boolean foundInArray = false;

                for (int j = 0; j < jsonArray.size(); j++) {
                    if (checkNestedProperty(jsonArray.get(j),
                            String.join(".", Arrays.copyOfRange(keys, i, keys.length)))) {
                        foundInArray = true;
                        break;
                    }
                }
                return foundInArray;
            } else {
                return false;
            }
        }
        return true;
    }

}
