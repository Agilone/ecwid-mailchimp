/*
 * Copyright 2012 Ecwid, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ecwid.mailchimp.connection;

import com.ecwid.mailchimp.MailChimpException;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.ConnectException;
import java.net.UnknownHostException;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;


/**
 * Implementation of {@link MailChimpConnectionManager}
 * which uses Apache HttpClient library to access MailChimp API service point.
 *
 * @author Vasily Karyaev <v.karyaev@gmail.com>
 * @author Ergin Demirel
 */
public class HttpClientConnectionManager implements MailChimpConnectionManager {
    private final HttpClient http = new DefaultHttpClient(getConnectionManagerClient());
    private final int BUFFER_SIZE = 4096;
    private final int DELAY_IN_MSECS = 30000;
    private final int MAX_RETRY = 4;
    private final int CONNECTION_TIMEOUT_IN_SEC = 900;
    private final int SOCKET_TIMEOUT_IN_SEC = 900;


    private ClientConnectionManager getConnectionManagerClient() {
        PoolingClientConnectionManager poolingClientConnectionManager = new PoolingClientConnectionManager(SchemeRegistryFactory.createDefault());
        poolingClientConnectionManager.setMaxTotal(100);
        poolingClientConnectionManager.setDefaultMaxPerRoute(20);
        return poolingClientConnectionManager;
    }

    @Override
    public String post(String url, String payload) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(payload));
        final HttpParams httpParameters = http.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIMEOUT_IN_SEC * 1000);
        HttpConnectionParams.setSoTimeout(httpParameters, SOCKET_TIMEOUT_IN_SEC * 1000);
        return http.execute(post, new BasicResponseHandler());
    }

    @Override
    public String getAsFile(String url, String path, String fileName) throws IOException, MailChimpException {
        if (!new File(path).exists()) {
            throw new IOException("Folder does not exist");
        }

        HttpGet get = new HttpGet(url);

        //set retry handler
        ((DefaultHttpClient) http).setHttpRequestRetryHandler(new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(
                    IOException exception,
                    int executionCount,
                    HttpContext context) {
                if (executionCount >= MAX_RETRY) {
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    // Timeout
                    return false;
                }
                if (exception instanceof UnknownHostException) {
                    // Unknown host
                    return false;
                }
                if (exception instanceof ConnectException) {
                    // Connection refused
                    return false;
                }
                if (exception instanceof SSLException) {
                    // SSL handshake exception
                    return false;
                }
                HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    try {
                        Thread.sleep(DELAY_IN_MSECS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });

        HttpResponse response = http.execute(get);

        if (response.getStatusLine().getStatusCode() < HttpStatus.SC_MOVED_PERMANENTLY) {
            String fullPath = new File(path, fileName).toString();
            HttpEntity entity = response.getEntity();
            byte[] buffer = new byte[BUFFER_SIZE];

            if (entity != null) {
                InputStream inputStream = entity.getContent();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(fullPath));
                try {
                    int bytesRead = 0;
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    while ((bytesRead = bis.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                        fileOutputStream.close();
                    } catch (Exception ignore) {
                    }
                }

                return fullPath;
            } else {
                //nothing found to write
                return null;
            }
        } else {
            throw new MailChimpException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
        }
    }

    @Override
    public void close() {
        http.getConnectionManager().shutdown();
    }

	private static final int DEFAULT_TIMEOUT = 15000;

	private final HttpClient http = new DefaultHttpClient();

	/**
	 * Constructor.
	 * Equivalent to calling {@link #HttpClientConnectionManager(int, int)} with both parameters set to 15000 (15 seconds).
	 */
	public HttpClientConnectionManager() {
		this(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
	}

	/**
	 * Constructor.
	 *
	 * @param connectTimeout the timeout (in milliseconds) when trying to connect to the remote server
	 * @param readTimeout the timeout (in milliseconds) when when waiting for the response from the remote server
	 */
	public HttpClientConnectionManager(int connectTimeout, int readTimeout) {
		setConnectTimeout(connectTimeout);
		setReadTimeout(readTimeout);
	}

	@Override
	public String post(String url, String payload) throws IOException {
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(payload, "UTF-8"));
		HttpResponse response = http.execute(post);
		if (response.getEntity() != null) {
			return EntityUtils.toString(response.getEntity(), "UTF-8").trim();
		} else {
			throw new IOException(response.getStatusLine().toString());
		}
	}

	@Override
	public void close() {
		http.getConnectionManager().shutdown();
	}

	public int getConnectTimeout() {
		return HttpConnectionParams.getConnectionTimeout(http.getParams());
	}

	public void setConnectTimeout(int connectTimeout) {
		HttpConnectionParams.setConnectionTimeout(http.getParams(), connectTimeout);
	}

	public int getReadTimeout() {
		return HttpConnectionParams.getSoTimeout(http.getParams());
	}

	public void setReadTimeout(int readTimeout) {
		HttpConnectionParams.setSoTimeout(http.getParams(), readTimeout);
	}
}
