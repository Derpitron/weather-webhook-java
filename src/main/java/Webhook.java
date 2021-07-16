import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.lang.Throwable.*;
import java.util.TimeZone;
import static java.lang.Thread.sleep;

import de.raik.webhook.WebhookBuilder;
import io.github.cdimascio.dotenv.*;
import com.google.gson.*;
import de.raik.webhook.*;



public class Webhook {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String debugStatement = null;
        WebhookBuilder debugContent = new WebhookBuilder(dotenv.get("DEBUG", debugStatement));
        Webhook debug = new Webhook();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(dotenv.get("TIMEZONE")));
        Date localTime = calendar.getTime();

        String hookStatement = ("THE CLOUD HAS ARIVED\nJJJJJJJJJJJJJJJJ");

        URL url = null;
        try {
            url = new URL(dotenv.get("API"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            WebhookBuilder hookContent = new WebhookBuilder;
            hookContent(dotenv.get("HOOK"));
            Webhook hook = new Webhook(hookContent.build());
            boolean x = false;
            debugStatement = ("Program started successfully on " + InetAddress.getLocalHost() + " at " + localTime);
            debug.execute();
            while (true) {
                WebhookBuilder hookBuild = new WebhookBuilder(dotenv.get("HOOK"));

                URLConnection request = url.openConnection();
                request.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                JsonObject response = root.getAsJsonObject();

                if (response.has("rain")) {
                    boolean isRaining = response.has("rain");
                    if (x == false) {
                        boolean isRaining2 = false;
                    }
                    boolean isRaining2 = isRaining;
                    if ((isRaining) && (isRaining != isRaining2)) {
                        hook.execute();
                    }
                    x = true;
                }
                sleep(Integer.parseInt(dotenv.get("INTERVAL")));
            }
        } catch (Exception e) {
            try {
                debugStatement = ("Program failed on " + InetAddress.getLocalHost() + " at " + localTime + "\nerror:\n```" + debug.execute(Throwable.printStackTrace(e)) + "```");
            } catch (UnknownHostException unknownHostException) {
                unknownHostException.printStackTrace();
            }
            debug.execute();
        }
    }
}