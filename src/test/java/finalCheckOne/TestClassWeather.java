package finalCheckOne;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;

import jdk.internal.joptsimple.util.RegexMatcher;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;


class TestClassWeather {
	
	/*
	 * @BeforeAll static void BeforeAll() { System.out.println("BeforeAll"); }
	 * 
	 * @BeforeEach void BeforeEach() { System.out.println("BeforeEach"); }
	 */	
	
	/*
	 * @Test public void test1() { Call<Weather> weatherCall =
	 * RestClient.getClient().getWeatherByCity("1");}
	 * 
	 * @Override public void onResponse(Response<Weather> response, Retrofit
	 * retrofit) { Weather Weather = response.body(); }
	 * 
	 * public void onFailure(Throwable t) { //assertFail("Request failed"); };
	 * 
	 * }
	 */

		@Test
		public void getWeatherTestCase1() {
		
		    Call<String> weatherCall = RestClient.getClient().getWeatherByCity("Dubai");
		    weatherCall.enqueue(new Callback<String>() {
		        @Override
		        public void onResponse(Response<Weather> response, Retrofit retrofit) {
		        	Weather weatherResponseText = response.body();	   
		        	Verification(weatherResponseText,Dubai);
		        }
		        @Override
		        public void onFailure(Throwable t) {
		            System.out.println(t.getMessage());
		        }
		    });
		}
	
		public void Verification(String response, String city)
	    {
			String tomorrowDate= DateClass.getTomorrow();
			
	    	List<String> lines = this.SplitLines(response); 	
	    	assertThat(lines[0], matchesPattern("Tomorrow ("+tomorrowDate+") in "+"city"));	    	
	    	assertTrue(!lines[1].isEmpty());
	    	assertThat(lines[2], matchesPattern("Temp: ^\\d+(\\.\\d{1,2})?$ in "+city+":"));
	    	assertThat(lines[3], matchesPattern("Wind: ^\\d+(\\.\\d{1,2})?$ mph"));
	    	assertThat(lines[4], matchesPattern("Humidity: ^\\d+$%"));
	    } 
		 
	
	  private List<String> SplitLines(String response) {
		   List<String> lines = new ArrayList<>(); 
	       strText.lines().forEach(s -> lines.add(s)); 
	       return lines;
		}
		 
		    //"[0-9]*\\.?[0-9]*";
	 
	/*
	 * @AfterEach void AfterEach() { System.out.println("AfterEach"); }
	 * 
	 * @AfterAll static void AfterAll() { System.out.println("AfterAll"); }
	 */
	
}
