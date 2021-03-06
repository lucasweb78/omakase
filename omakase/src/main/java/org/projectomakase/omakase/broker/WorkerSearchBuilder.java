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
package org.projectomakase.omakase.broker;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import org.projectomakase.omakase.search.Operator;
import org.projectomakase.omakase.search.Search;
import org.projectomakase.omakase.search.SortOrder;

import java.util.List;

/**
 * Worker specific implementation of {@link Search.Builder}.
 *
 * @author Richard Lucas
 */
public class WorkerSearchBuilder extends Search.Builder {

    private static final List<String> SUPPORTED_SORT_ATTRIBUTES;
    private static final Multimap<String, Operator> SUPPORTED_CONDITIONS;

    static {
        SUPPORTED_SORT_ATTRIBUTES = ImmutableList.of(Worker.ID, Worker.WORKER_NAME, Worker.STATUS, Worker.STATUS_TIMESTAMP, Worker.CREATED, Worker.CREATED_BY, Worker.LAST_MODIFIED, Worker.LAST_MODIFIED_BY);

        ImmutableMultimap.Builder<String, Operator> mapBuilder = ImmutableMultimap.builder();
        mapBuilder.put(Worker.ID, Operator.EQ);
        mapBuilder.putAll(Worker.WORKER_NAME, Operator.EQ, Operator.LIKE);
        mapBuilder.put(Worker.EXTERNAL_IDS, Operator.EQ);
        mapBuilder.putAll(Worker.STATUS, Operator.EQ, Operator.NE);
        mapBuilder.putAll(Worker.STATUS_TIMESTAMP, Operator.EQ, Operator.GT, Operator.GTE, Operator.LT, Operator.LTE);
        mapBuilder.putAll(Worker.CREATED, Operator.EQ, Operator.GT, Operator.GTE, Operator.LT, Operator.LTE);
        mapBuilder.putAll(Worker.CREATED_BY, Operator.EQ, Operator.NE);
        mapBuilder.putAll(Worker.LAST_MODIFIED, Operator.EQ, Operator.GT, Operator.GTE, Operator.LT, Operator.LTE);
        mapBuilder.putAll(Worker.LAST_MODIFIED_BY, Operator.EQ, Operator.NE);

        SUPPORTED_CONDITIONS = mapBuilder.build();
    }

    @Override
    protected List<String> getSupportedSortAttributes() {
        return SUPPORTED_SORT_ATTRIBUTES;
    }

    @Override
    protected Multimap<String, Operator> getSupportedConditions() {
        return SUPPORTED_CONDITIONS;
    }

    @Override
    protected String getDefaultOrderBy() {
        return Worker.CREATED;
    }

    @Override
    protected SortOrder getDefaultSortOrder() {
        return SortOrder.DESC;
    }
}
