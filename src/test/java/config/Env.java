package config;

public class Env {
    public static  String getApiKey(){
        return System.getenv("API_KEY");
    }
}
