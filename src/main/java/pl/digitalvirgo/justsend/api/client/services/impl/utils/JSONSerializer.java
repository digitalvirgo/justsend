package pl.digitalvirgo.justsend.api.client.services.impl.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

public class JSONSerializer {

    private static Gson gson;

    public static <T> T deserialize(String json, Type tClass) {
        return getGson().fromJson(json, tClass);
    }

    public static String serialize(Object o) {
        return getGson().toJson(o);
    }

    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()))
                    .registerTypeAdapter(Date.class, (JsonSerializer<Date>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.getTime()))
                    .setPrettyPrinting()
                    .create();
        }
        return gson;
    }

}
