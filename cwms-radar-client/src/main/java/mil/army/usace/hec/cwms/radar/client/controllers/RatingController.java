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

import static mil.army.usace.hec.cwms.radar.client.controllers.RadarEndpointConstants.ACCEPT_XML_HEADER_V2;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.ApiConnectionInfo;
import mil.army.usace.hec.cwms.http.client.HttpRequestBuilderImpl;
import mil.army.usace.hec.cwms.http.client.HttpRequestResponse;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestExecutor;
import mil.army.usace.hec.cwms.radar.client.model.RadarObjectMapper;
import mil.army.usace.hec.cwms.radar.client.model.RatingMetadataList;

public final class RatingController {

    private static final String RATINGS = "ratings";
    private static final String RATINGS_METADATA = "ratings/metadata";

    /**
     * Retrieve Rating set XML.
     *
     * @param apiConnectionInfo   - connection info
     * @param ratingEndpointInput - rating-id and office
     * @return RatingSpec
     * @throws IOException - thrown if retrieve failed
     */
    public String retrieveRatingXml(ApiConnectionInfo apiConnectionInfo, RatingEndpointInput ratingEndpointInput) throws IOException {
        HttpRequestExecutor executor =
            new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS + "/" + ratingEndpointInput.getRatingId())
                .addEndpointInput(ratingEndpointInput)
                .get()
                .withMediaType(ACCEPT_XML_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            return response.getBody();
        }
    }

    public RatingMetadataList retrieveRatingMetadata(ApiConnectionInfo apiConnectionInfo, RatingMetadataEndpointInput input) throws IOException {
        HttpRequestExecutor executor = new HttpRequestBuilderImpl(apiConnectionInfo, RATINGS_METADATA)
                .addEndpointInput(input)
                .get()
                .withMediaType(ACCEPT_XML_HEADER_V2);
        try (HttpRequestResponse response = executor.execute()) {
            String body = response.getBody();
            return RadarObjectMapper.mapJsonToObject(body, RatingMetadataList.class);
        }
    }
}
