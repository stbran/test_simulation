package ru.sbertroika;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class DBService {
    static String query = "http://localhost:8083";

    public static void getListTable(String uri) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(query + uri).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            StringBuilder sb = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                System.out.println(sb);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public static String getTerminalIdByName(String uri, String params) {
        HttpURLConnection connection = null;
        String id = null;
        try {
            connection = (HttpURLConnection) new URL(query + uri + params).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                if ((id = in.readLine()) != null) {
                    return id;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    public static boolean deleteById(String uri, UUID id) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(query + uri + "id=" + id).openConnection();
            connection.setRequestMethod("DELETE");
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                return true;
            }
            System.out.println(connection.getResponseCode());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return false;
    }

}
