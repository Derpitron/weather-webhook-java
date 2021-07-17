package me.derpitron;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;

import de.raik.webhook.*;
import io.github.cdimascio.dotenv.*;
import com.google.gson.*;

public class Webhook {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(dotenv.get("TIMEZONE")));
        Date localTime = calendar.getTime();

        WebhookBuilder debugBuilder = null;
        try {
            debugBuilder = new WebhookBuilder(dotenv.get("DEBUG"))
                .content("Program started successfully on " + InetAddress.getLocalHost() + " at " + dotenv.get("TIMEZONE") + " - " + localTime + " with an interval of " + dotenv.get("INTERVAL") + " milliseconds");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        de.raik.webhook.Webhook debug = debugBuilder.build();

        WebhookBuilder mainBuilder = new WebhookBuilder(dotenv.get("HOOK"))
                .content("THE CLOUD HAS ARIVED\nJJJJJJJJJJJJJJJJ");
        de.raik.webhook.Webhook main = mainBuilder.build();

        URL url = null;
        try {
            url = new URL(dotenv.get("API"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            boolean x = false;
            debug.execute();
            while (true) {

                URLConnection request = url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject response = root.getAsJsonObject();

                if (response.has("rain")) {
                    boolean isRaining = response.has("rain");
                    if (x == false) {
                        boolean isRainingTest = false;
                    }
                    boolean isRainingTest = isRaining;
                    if ((isRaining) && (isRaining != isRainingTest)) {
                        HttpURLConnection outputConnection = main.execute();
                    }
                    x = true;
                }
                sleep(Integer.parseInt(dotenv.get("INTERVAL")));
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                debugBuilder.content("Program failed on " + InetAddress.getLocalHost() + " at " + localTime);
                HttpURLConnection outputConnection = debug.execute();
            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
            }
            debug.execute();
        }
    }
}