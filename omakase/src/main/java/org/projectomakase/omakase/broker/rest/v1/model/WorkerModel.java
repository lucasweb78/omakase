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
package org.projectomakase.omakase.broker.rest.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.projectomakase.omakase.rest.model.v1.RepresentationBaseModel;
import org.projectomakase.omakase.rest.model.v1.ResourceStatusModel;

import java.util.List;

/**
 * REST Worker Representation
 *
 * @author Richard Lucas
 */
@JsonPropertyOrder({"id", "name", "external_ids", "status", "created", "created_by", "last_modified", "last_modified_by", "links"})
public class WorkerModel extends RepresentationBaseModel {

    private String id;
    private String name;
    @JsonProperty("external_ids")
    private List<String> externalIds;
    private ResourceStatusModel status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(List<String> externalIds) {
        this.externalIds = externalIds;
    }

    public ResourceStatusModel getStatus() {
        return status;
    }

    public void setStatus(ResourceStatusModel status) {
        this.status = status;
    }
}
