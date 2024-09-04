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
package io.telicent.jena.graphql.schemas.telicent.graph.models;

import io.telicent.jena.graphql.schemas.telicent.graph.TelicentGraphSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Represents a set of Telicent Search results, includes metadata about the results as well as the results themselves
 */
public class TelicentSearchResults {
    private final int limit, offset;
    private final boolean maybeMore;
    private final String searchTerm;
    private final SearchType searchType;
    private final List<TelicentGraphNode> nodes;

    /**
     * Creates a new set of search results
     *
     * @param searchTerm Search Term
     * @param searchType Search Type
     * @param limit      Limit
     * @param offset     Offset
     * @param maybeMore  Boolean indicating whether more results may be available
     * @param nodes      Result nodes
     */
    public TelicentSearchResults(String searchTerm, SearchType searchType, int limit, int offset, boolean maybeMore,
                                 List<TelicentGraphNode> nodes) {
        this.limit = limit;
        this.offset = offset;
        this.maybeMore = maybeMore;
        this.searchTerm = searchTerm;
        this.searchType = searchType;
        this.nodes = nodes;
    }

    public void setNodes(List<TelicentGraphNode> nodes) {
        this.nodes.clear();
        this.nodes.addAll(nodes);
    }

    public static TelicentSearchResults fromMap(Map<String, Object> map) {
        return new TelicentSearchResults((String) map.get("query"),
                                         SearchType.valueOf(map.get("type").toString().toUpperCase(Locale.ROOT)),
                                         (Integer) map.get(TelicentGraphSchema.ARGUMENT_LIMIT),
                                         (Integer) map.get(TelicentGraphSchema.ARGUMENT_OFFSET),
                                         (Boolean) map.get("maybeMore"), new ArrayList<>());
    }

    /**
     * Gets the limit used for this search query
     *
     * @return Limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Gets the offset used for this search query
     *
     * @return Offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Gets whether asking for a new page of results may offer further results
     * <p>
     * As the name suggests this is not a guarantee, it may return {@code true} when there actually aren't any further
     * results available, or return {@code false} when more results may be available in the future.  This is due to the
     * lazy evaluation of search queries behind the scenes and the fact that the search index, like all Telicent Smart
     * Caches, exhibits eventual consistency so may not be up to date with the state of the graph database (or vice
     * versa).
     * </p>
     *
     * @return True if maybe more results, false otherwise
     */
    public boolean isMaybeMore() {
        return maybeMore;
    }

    /**
     * Gets the search term that was used to produce these search results
     *
     * @return Search Term
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * Gets the search type that was used to produce these search results
     *
     * @return Search Type
     */
    public SearchType getSearchType() {
        return searchType;
    }

    /**
     * Gets the nodes that the search identified
     *
     * @return Nodes
     */
    public List<TelicentGraphNode> getNodes() {
        return nodes;
    }
}
