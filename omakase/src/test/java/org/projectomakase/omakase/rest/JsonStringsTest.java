/*
 * #%L
 * omakase
 * %%
 * Copyright (C) 2015 Project Omakase LLC
 * %%
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
 * #L%
 */
package org.projectomakase.omakase.rest;

import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author Richard Lucas
 */
public class JsonStringsTest {

    @Test
    public void shouldConvertInputStringToString() throws Exception {
        assertThat(JsonStrings.inputStreamToString(new ByteArrayInputStream("{\"valid\":\"yes\"}".getBytes()))).isEqualTo("{\"valid\":\"yes\"}");
    }

    @Test
    public void shouldValidateSuccessfully() throws Exception {
        try {
            JsonStrings.isNotNullOrEmpty("{\"valid\":\"yes\"}");
        } catch (WebApplicationException e) {
            fail("Unexpected Exception", e);
        }
    }

    @Test
    public void shouldThrowExceptionNullPayload() throws Exception {
        assertThatThrownBy(() -> JsonStrings.isNotNullOrEmpty(null)).hasMessage("JSON payload required");
    }

    @Test
    public void shouldThrowExceptionEmptyPayload() throws Exception {
        assertThatThrownBy(() -> JsonStrings.isNotNullOrEmpty("{}")).isInstanceOf(WebApplicationException.class).hasMessage("JSON payload must not be empty");
    }

    @Test
    public void shouldThrowExceptionEmptyPayloadWithWhitespaces() throws Exception {
        assertThatThrownBy(() -> JsonStrings.isNotNullOrEmpty("{       }")).isInstanceOf(WebApplicationException.class).hasMessage("JSON payload must not be empty");
    }
}