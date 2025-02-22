/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.dataprepper.logstash.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class LogstashMappingModelTest {
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper(new YAMLFactory());

    }

    @Test
    void deserialize_from_JSON_should_have_expected_value() throws IOException {

        final LogstashMappingModel logstashMappingModel = objectMapper.readValue(this.getClass().getResourceAsStream("sample.mapping.yaml"), LogstashMappingModel.class);

        assertThat(logstashMappingModel.getPluginName(), equalTo("samplePlugin"));
        assertThat(logstashMappingModel.getAttributesMapperClass(), equalTo("org.opensearch.dataprepper.Placeholder"));
        assertThat(logstashMappingModel.getMappedAttributeNames(), notNullValue());
        assertThat(logstashMappingModel.getMappedAttributeNames().size(), equalTo(2));
        assertThat(logstashMappingModel.getMappedAttributeNames().get("valueA"), equalTo("keyA"));
        assertThat(logstashMappingModel.getMappedAttributeNames().get("valueB"), equalTo("keyB"));
        assertThat(logstashMappingModel.getAdditionalAttributes(), notNullValue());
        assertThat(logstashMappingModel.getAdditionalAttributes().size(), equalTo(2));
        assertThat(logstashMappingModel.getAdditionalAttributes().get("addA"), equalTo(true));
        assertThat(logstashMappingModel.getAdditionalAttributes().get("addB"), equalTo("staticValueB"));
    }

    @Test
    void deserialize_from_JSON_with_null_values_has_empty_lists() throws IOException {

        final LogstashMappingModel logstashMappingModel = objectMapper.readValue(this.getClass().getResourceAsStream("sample-with-nulls.mapping.yaml"), LogstashMappingModel.class);

        assertThat(logstashMappingModel.getPluginName(), equalTo("samplePlugin"));
        assertThat(logstashMappingModel.getAttributesMapperClass(), equalTo("org.opensearch.dataprepper.Placeholder"));
        assertThat(logstashMappingModel.getMappedAttributeNames(), notNullValue());
        assertThat(logstashMappingModel.getMappedAttributeNames().size(), equalTo(0));
        assertThat(logstashMappingModel.getAdditionalAttributes(), notNullValue());
        assertThat(logstashMappingModel.getAdditionalAttributes().size(), equalTo(0));
    }
}