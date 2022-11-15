/*
 * MIT License
 *
 * Copyright (c) 2022 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package mil.army.usace.hec.cwms.radar.client.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.http.client.request.HttpPostRequest;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestMediaType;

public class MockHttpRequestBuilder implements HttpRequestBuilder {

    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> queryHeaders = new HashMap<>();

    @Override
    public MockHttpRequestBuilder addQueryParameter(String key, String value) {
        queryParameters.put(key, value);
        return this;
    }

    @Override
    public MockHttpRequestBuilder addQueryHeader(String key, String value) {
        queryHeaders.put(key, value);
        return this;
    }

    @Override
    public MockHttpRequestBuilder addEndpointInput(EndpointInput endpointInput) {
        return this;
    }

    @Override
    public HttpRequestBuilder enableHttp2() {
        return this;
    }

    @Override
    public HttpPostRequest post() {
        return null;
    }

    @Override
    public HttpRequestMediaType get() throws IOException {
        return null;
    }

    String getQueryParameter(String parameter) {
        return queryParameters.get(parameter);
    }

    String getQueryHeader(String header) {
        return queryHeaders.get(header);
    }
}
