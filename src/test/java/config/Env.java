package config;

public class Env {

    public static String getCredentials() {
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        java.util.Map<String, String> credentials = new java.util.HashMap<>();
        credentials.put("username", username != null ? username : "");
        credentials.put("password", password != null ? password : "");

        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(credentials);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize credentials to JSON", e);
        }
    }
}
