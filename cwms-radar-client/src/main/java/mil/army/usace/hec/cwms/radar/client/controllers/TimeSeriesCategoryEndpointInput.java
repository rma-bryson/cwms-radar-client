/*
 * MIT License
 *
 * Copyright (c) 2023 Hydrologic Engineering Center
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

import mil.army.usace.hec.cwms.http.client.EndpointInput;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilder;
import mil.army.usace.hec.cwms.radar.client.model.TimeSeriesCategory;

import java.util.Objects;

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_HEADER_V1;
import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_QUERY_HEADER;

public final class TimeSeriesCategoryEndpointInput {

    static final String OFFICE_QUERY_PARAMETER = "office";
    static final String CASCADE_DELETE_QUERY_PARAMETER = "cascade-delete";

    private TimeSeriesCategoryEndpointInput() {
        throw new AssertionError("factory class");
    }

    public static GetAll getAll() {
        return new GetAll();
    }

    public static GetOne getOne(String categoryId, String officeId) {
        return new GetOne(categoryId, officeId);
    }

    public static Post post(TimeSeriesCategory timeSeriesCategory) {
        return new Post(timeSeriesCategory);
    }

    public static Delete delete(String categoryId, String officeId) {
        return new Delete(categoryId, officeId);
    }

    public static class GetAll extends EndpointInput {

        private String officeId;

        private GetAll() {

        }

        public GetAll officeId(String officeId) {
            this.officeId = officeId;
            return this;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static class GetOne extends EndpointInput {
        private final String categoryId;
        private final String officeId;

        private GetOne(String categoryId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot retrieve a time series category without a category id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot retrieve a time series category without specifying an office");
        }

        String categoryId() {
            return categoryId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static class Post extends EndpointInput {
        private final TimeSeriesCategory timeSeriesCategory;

        private Post(TimeSeriesCategory timeSeriesCategory) {
            this.timeSeriesCategory = Objects.requireNonNull(timeSeriesCategory, "Cannot update a time series category without a data object");
        }

        TimeSeriesCategory timeSeriesCategory() {
            return timeSeriesCategory;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }

    public static class Delete extends EndpointInput {
        private final String categoryId;
        private final String officeId;
        private boolean cascadeDelete;

        private Delete(String categoryId, String officeId) {
            this.categoryId = Objects.requireNonNull(categoryId, "Cannot delete a time series category without a category id");
            this.officeId = Objects.requireNonNull(officeId, "Cannot delete a time series category without specifying an office");
        }

        public Delete cascadeDelete(boolean cascadeDelete) {
            this.cascadeDelete = cascadeDelete;
            return this;
        }

        String categoryId() {
            return categoryId;
        }

        @Override
        protected HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
            return httpRequestBuilder.addQueryParameter(OFFICE_QUERY_PARAMETER, officeId)
                    .addQueryParameter(CASCADE_DELETE_QUERY_PARAMETER, Boolean.toString(cascadeDelete))
                    .addQueryHeader(ACCEPT_QUERY_HEADER, ACCEPT_HEADER_V1);
        }
    }
}
