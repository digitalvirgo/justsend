package pl.justsend.api.client.http.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 28.03.18
 * Time: 15:09
 */
public class JSONSerializer {

    private static Gson gson;

    public static <T> T deserialize(String s, Type tClass) {
        return getGson().fromJson(s, tClass);
    }

    public static String serialize(Object o) {
        return getGson().toJson(o);
    }

    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()))
                .registerTypeAdapter(Date.class, (JsonSerializer<Date>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                .create();

        }
        return gson;
    }

}
