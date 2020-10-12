import okhttp3.OkHttpClient;

import okhttp3.

public class RestClient {
	
	
	
	public static void Method() {
		OkHttpClient client1 = new OkHttpClient();
		client1.interceptors().add(new FakeInterceptor())
	}

}

