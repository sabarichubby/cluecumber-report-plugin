/*
 * Copyright 2018 trivago N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trivago.rta.constants;

/**
 * Holder class for static plugin settings
 */
public class PluginSettings {
    public PluginSettings() {
    }

    public final static String NAME = "Cluecumber Report Plugin";
    public static final String TEMPLATE_FILE_EXTENSION = ".ftl";
    public static final String HTML_FILE_EXTENSION = ".html";

    public static final String BASE_TEMPLATE_PATH = "/template";
    public static final String PAGES_DIRECTORY = "pages";

    public final static String SCENARIO_DETAIL_PAGE_NAME = "Scenario Details";
    public final static String SCENATIO_DETAIL_PAGE_PATH = "scenario-detail";
    public static final String SCENARIO_DETAIL_PAGE_FRAGMENT = "/scenario-detail/scenario_";

    public final static String SUITE_OVERVIEW_PAGE_NAME = "Suite Overview";
    public final static String SUITE_OVERVIEW_PAGE_PATH = "index";

    public final static String TAG_SUMMARY_PAGE_NAME = "Tag Summary";
    public final static String TAG_SUMMARY_PAGE_PATH = "tag-summary";
}