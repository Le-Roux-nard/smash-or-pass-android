package bzh.lerouxard.smashorpass.apiImplementation;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiInterface {
    @POST("https://graphql.anilist.co/")  // your url
    @Headers("Content-Type: application/json")
    Call<ApiResponse> getRandomChar(@Body ApiQuery model);
/// Call<String>   depend for result response

    @GET
    @Streaming
    Call<ResponseBody> downloadImage(@Url String imageUrl, @Query("test") String imageName);

}
