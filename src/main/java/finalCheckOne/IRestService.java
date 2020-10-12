package finalCheckOne;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IRestService {

    String ENDPOINT = "https://www.metaweather.com/api/";

    @GET("/")
    Call<String> getWeatherByCity(@Query("City") final String City);
}
