package bzh.lerouxard.smashorpass.apiImplementation;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApiQuery {
    private static String QUERY_STRING = "query charactersRandom ($pageNumber: Int, $not_in: [Int]) {Page(perPage: 1, page: $pageNumber) {characters(sort: FAVOURITES_DESC, id_not_in: $not_in) {id siteUrl image {large} name { native alternative alternativeSpoiler userPreferred full} gender media(perPage: 1, sort: POPULARITY_DESC) { nodes {title {romaji} isAdult}}}}}";
    private static int MAX_CHARS_IN_API = 129169;
    private static List<Integer> alreadyUsedCharactersIdList = new ArrayList<>();

    @SerializedName("variables")
    @Expose
    public String variables;

    @SerializedName("query")
    @Expose
    public String query = QUERY_STRING;

    public ApiQuery() {
        try {
            Random r = new Random();
            variables = "{\"pageNumber\":" + r.nextInt(MAX_CHARS_IN_API) + "}";
        } catch (Exception ex) {
            Log.e("DEBUG", ex.toString());
        }
    }
}
