package io.gateway.outbound;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;

import java.nio.charset.Charset;

import static org.asynchttpclient.Dsl.asyncHttpClient;


public class HttpClient {
    static private AsyncHttpClient asyncHttpClient = asyncHttpClient();

    static public byte[] getResponse(String url) throws Exception {
        ListenableFuture<Response> responseListenableFuture = asyncHttpClient.prepareGet(url).execute();
        Response response = responseListenableFuture.get();

        return response.getResponseBody().toString().getBytes(Charset.forName("ASCII"));
    }
}
