package finalCheckOne;


/*import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URI;*/

//import retrofit.JacksonConverterFactory;
import retrofit2.converter.jackson.*;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.*;


/**
 *
 */
public final class RestClient {

    private static IRestService restService = null;

    public static IRestService getClient() {
        if(restService == null) {			
			  final OkHttpClient client1 = new OkHttpClient();
			  client1.interceptors().add((Interceptor) new FakeInterceptor());			 
			/*
			 * okhttp3.OkHttpClient client2 = new okhttp3.OkHttpClient();
			 * client2.interceptors().add((okhttp3.Interceptor) new FakeInterceptor());
			 */

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                            // Endpoint
                    .baseUrl(IRestService.ENDPOINT)
                    .client(client1)
                    .build();

            restService = retrofit.create(IRestService.class);
        }
        return restService;
    }
}





