/**
 * Copyright (C) Telicent Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.telicent.jena.graphql.schemas;

/**
 * Constants related to the GraphQL Dataset Schema
 */
public class DatasetSchema extends CoreSchema {

    /**
     * Class provides static constants so no need for direct instantiation
     */
    DatasetSchema() {
        super();
    }

    /**
     * Quads field
     */
    public static final String QUADS_FIELD = "quads";

    /**
     * Quads query type
     */
    public static final String QUADS_QUERY_TYPE = "Quads";
}
