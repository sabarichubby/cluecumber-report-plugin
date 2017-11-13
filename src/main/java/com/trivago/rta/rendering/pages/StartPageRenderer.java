/*
 * Copyright 2017 trivago N.V.
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

package com.trivago.rta.rendering.pages;

import be.ceau.chart.PieChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.PieData;
import be.ceau.chart.dataset.PieDataset;
import be.ceau.chart.options.PieOptions;
import com.trivago.rta.constants.Status;
import com.trivago.rta.exceptions.CluecumberPluginException;
import com.trivago.rta.rendering.pages.pojos.ReportDetails;
import com.trivago.rta.rendering.pages.pojos.StartPageCollection;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

@Singleton
public class StartPageRenderer extends PageRenderer {

    public String getRenderedContent(
            final StartPageCollection startPageCollection, final Template template)
            throws CluecumberPluginException {

        ReportDetails reportDetails = new ReportDetails();
        addChartJsonToReportDetails(startPageCollection, reportDetails);
        addCurrentDateToReportDetails(reportDetails);
        startPageCollection.setReportDetails(reportDetails);

        Writer stringWriter = new StringWriter();
        try {
            template.process(startPageCollection, stringWriter);
        } catch (TemplateException | IOException e) {
            throw new CluecumberPluginException(e.getMessage());
        }
        return stringWriter.toString();
    }

    private void addChartJsonToReportDetails(final StartPageCollection startPageCollection, final ReportDetails reportDetails) {
        PieDataset pieDataset = new PieDataset();
        pieDataset.setData(
                startPageCollection.getTotalNumberOfPassedScenarios(),
                startPageCollection.getTotalNumberOfFailedScenarios(),
                startPageCollection.getTotalNumberOfSkippedScenarios()
        );

        Color passedColor = com.trivago.rta.constants.Color.getChartColorByStatus(Status.PASSED);
        Color failedColor = com.trivago.rta.constants.Color.getChartColorByStatus(Status.FAILED);
        Color skippedColor = com.trivago.rta.constants.Color.getChartColorByStatus(Status.SKIPPED);

        pieDataset.addBackgroundColors(passedColor, failedColor, skippedColor);
        PieData pieData = new PieData();
        pieData.addDataset(pieDataset);
        pieData.addLabels(Status.PASSED.getStatusString(), Status.FAILED.getStatusString(), Status.SKIPPED.getStatusString());
        PieOptions pieOptions = new PieOptions();

        reportDetails.setChartJson(new PieChart(pieData, pieOptions).toJson());
    }
}
