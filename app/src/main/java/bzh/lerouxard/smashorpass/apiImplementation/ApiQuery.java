package bzh.lerouxard.smashorpass.apiImplementation;

import static bzh.lerouxard.smashorpass.SmashOrPassActivity.characterIdList;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ApiQuery {
    private static String QUERY_STRING = "query charactersRandom($perPage:Int,$pageNumber:Int,$not_in:[Int]){Page(perPage:$perPage,page:$pageNumber){pageInfo{perPage currentPage lastPage total}characters(sort:FAVOURITES_DESC,id_not_in:$not_in){id image{large}gender}}}";
    private static int MAX_PAGE_IN_API = 2585;
    private List<Integer> last20RandomNumbers = new ArrayList<>();

    @SerializedName("variables")
    @Expose
    public String variables;

    @SerializedName("query")
    @Expose
    public String query = QUERY_STRING;

    public ApiQuery() {
        try {
            Random r = new Random();
            String ignoreIdList = "\"not_in\":[";
            for (int i = 0; i < characterIdList.size(); i++) {
                ignoreIdList += characterIdList.get(i);
                if (i != characterIdList.size() - 1) {
                    ignoreIdList += ",";
                }
            }
            ignoreIdList += "]";
            System.out.println(ignoreIdList);
            Integer randomPage;
            do {
                randomPage = r.nextInt(MAX_PAGE_IN_API);
            } while (last20RandomNumbers.contains(randomPage));
            if (last20RandomNumbers.size() >= 20) {
                last20RandomNumbers.remove(0);
            }
            last20RandomNumbers.add(randomPage);

            variables = "{\"pageNumber\":" + r.nextInt(MAX_PAGE_IN_API) + "," + ignoreIdList + "}";
        } catch (Exception ex) {
            Log.e("DEBUG", ex.toString());
        }
    }
}
