/*
 * Copyright (C) 2015. Victor Apoyan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package finalCheckOne;

/*import android.os.Build;
import android.util.Log;*/

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
//import com.squareup.okhttp.*;
//import com.vavian.mockretrofitrequests.BuildConfig;

import java.io.IOException;
import java.net.URI;
//import java.util.Collections;

/**
 *
 */
public class FakeInterceptor implements Interceptor {

    //private final static String TAG = FakeInterceptor.class.getSimpleName();
	
	static String tomorrowDate = DateClass.getTomorrow();
	
	private final static String WeatherLondon ="forecast London\n"+
	"Tomorrow ("+tomorrowDate+") in London:\n"+
	"Clear\n"+
	"Temp: 10.5 °C\n"+
	"Wind: 7.6 mph\n"+
	"Humidity: 30%";
	
	private final static String WeatherDubai ="forecast dubai\n"+
			"Tomorrow ("+tomorrowDate+") in Dubai:\n"+
			"Clear\n"+
			"Temp: 26.5 °C\n"+
			"Wind: 17.6 mph\n"+
			"Humidity: 64%";
	
    //private final static String WeatherLondon = "{\"City\":London,\"WeatherState\":Clear,\"Temp\":\"26.5\",\"Wind\":\"26.5\",\"Humidity\":\"61\"}";
    //private final static String WeatherDubai = "{\"City\":Dubai,\"WeatherState\":Light Rain,\"Temp\":\"37.5\",\"Wind\":\"20.5\",\"Humidity\":\"73\"}";

    public Response intercept(Chain chain) throws IOException {
        Response response = null;
       
            String responseString;
            // Get Request URI.
            final URI uri = chain.request().uri();            
            // Get Query String.
            final String query = uri.getQuery();
            // Parse the Query String.
            final String[] parsedQuery = query.split("=");
            if(parsedQuery[0].equalsIgnoreCase("City") && parsedQuery[1].equalsIgnoreCase("London")) {
                responseString = WeatherLondon;
            }
            else if(parsedQuery[0].equalsIgnoreCase("City") && parsedQuery[1].equalsIgnoreCase("Dubai")){
                responseString = WeatherDubai;
            }
            else {
                responseString = "";
            }

            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
            response = chain.proceed(chain.request());

        return response;
    }
}
