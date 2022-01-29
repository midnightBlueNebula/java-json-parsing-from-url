import java.net.*;  
import java.io.InputStream; 
import java.io.IOException;
import java.util.HashMap;
import org.json.*;


class Main {
  private String city = "ankara";
  private String weather;
    private int degree;
    private String apiKey = System.getenv("apiKey");
    private String requestStr = "http://api.openweathermap.org/data/2.5/weather";
    private String options;
  

  public Main(){
    options = "?q=" + city + "&apikey=" + apiKey;
        requestStr += options;
    URI uri = null;
        try {
             uri = new URI(requestStr);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        try {
            JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
            JSONObject obj = new JSONObject(tokener);
            degree = (int) Math.round((obj.getJSONObject("main").getDouble("temp") - 273.15));
            System.out.println(obj);
            JSONObject weatherObj = (JSONObject) obj.getJSONArray("weather").get(0);
            weather = weatherObj.getString("main");  
        } catch (IOException e) {
            e.printStackTrace();
        }
  }

  public static void main(String args[]) { 
    Main data = new Main();
    System.out.println(data.degree);
    System.out.println(data.weather);
  } 
}