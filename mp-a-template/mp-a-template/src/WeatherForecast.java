import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherForecast {
    public static void main(String[] args) {
        try {
            // creates a URL with the extra data acts as the parameters to the GET request
            URL getUrl = new URL("https://api.open-meteo.com/v1/forecast?latitude=39.168804&longitude=-86.536659&hourly=temperature_2m&temperature_unit=fahrenheit&timezone=EST");
            // wraps the new URL call inside a new instance of HttpUrlConnection
            HttpURLConnection wrap = (HttpURLConnection) getUrl.openConnection();
            try {
                //set the request method to GET and then check the response code of the HTTP request
                wrap.setRequestMethod("GET");
                int wrapResponseCode = wrap.getResponseCode();
                // if the response code is anything but 200, throw an IOException
                if (wrapResponseCode != 200){
                    throw new RuntimeException("The HTTP GET failed with the code: " + wrapResponseCode);
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader(wrap.getInputStream()))){
                    String response = "";
                    String line;
                    while ((line = br.readLine()) != null){
                        response += line;
                    }
                    JsonElement jElement = JsonParser.parseString(response);
                    JsonObject jObject = jElement.getAsJsonObject().getAsJsonObject("hourly");
                    JsonArray jsonArray1 = jObject.getAsJsonArray("time");
                    JsonArray jsonArray2 = jObject.getAsJsonArray("temperature_2m");
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }finally{
                // close the connection
                wrap.disconnect();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
