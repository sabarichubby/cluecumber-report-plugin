package com.trivago.rta.rendering.pages.pojos;

import com.trivago.rta.constants.Status;
import com.trivago.rta.json.pojo.Element;
import com.trivago.rta.json.pojo.Report;
import com.trivago.rta.json.pojo.Result;
import com.trivago.rta.json.pojo.Step;
import com.trivago.rta.json.pojo.Tag;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TagSummaryPageCollectionTest {
    private TagSummaryPageCollection tagSummaryPageCollection;

    @Test
    public void getEmptyTagStatsTest() {
        List<Report> reports = new ArrayList<>();
        tagSummaryPageCollection = new TagSummaryPageCollection(reports);
        Map<String, TagStat> tagStats = tagSummaryPageCollection.getTagStats();
        assertThat(tagStats.size(), is(0));
    }

    @Test
    public void getTagStatsTest() {
        List<Report> reports = new ArrayList<>();

        Report report = new Report();
        List<Element> elements = new ArrayList<>();

        Element element = new Element();
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setName("tag1");
        tags.add(tag);
        tag = new Tag();
        tag.setName("tag2");
        tags.add(tag);
        element.setTags(tags);
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        Result result = new Result();
        result.setStatus(Status.FAILED.getStatusString());
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);
        elements.add(element);
        report.setElements(elements);

        element = new Element();
        tags = new ArrayList<>();
        tag = new Tag();
        tag.setName("tag2");
        tags.add(tag);
        element.setTags(tags);
        steps = new ArrayList<>();
        step = new Step();
        result = new Result();
        result.setStatus(Status.PASSED.getStatusString());
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);
        elements.add(element);
        report.setElements(elements);

        element = new Element();
        tags = new ArrayList<>();
        tag = new Tag();
        tag.setName("tag3");
        tags.add(tag);
        element.setTags(tags);
        steps = new ArrayList<>();
        step = new Step();
        result = new Result();
        result.setStatus(Status.SKIPPED.getStatusString());
        step.setResult(result);
        steps.add(step);
        element.setSteps(steps);
        elements.add(element);
        report.setElements(elements);

        reports.add(report);

        tagSummaryPageCollection = new TagSummaryPageCollection(reports);
        Map<String, TagStat> tagStats = tagSummaryPageCollection.getTagStats();

        assertThat(tagStats.size(), is(3));

        TagStat tag1Stats = tagStats.get("tag1");
        assertThat(tag1Stats.getTotal(), is(1));
        assertThat(tag1Stats.getPassed(), is(0));
        assertThat(tag1Stats.getFailed(), is(1));
        assertThat(tag1Stats.getSkipped(), is(0));

        TagStat tag2Stats = tagStats.get("tag2");
        assertThat(tag2Stats.getTotal(), is(2));
        assertThat(tag2Stats.getPassed(), is(1));
        assertThat(tag2Stats.getFailed(), is(1));
        assertThat(tag2Stats.getSkipped(), is(0));

        TagStat tag3Stats = tagStats.get("tag3");
        assertThat(tag3Stats.getTotal(), is(1));
        assertThat(tag3Stats.getPassed(), is(0));
        assertThat(tag3Stats.getFailed(), is(0));
        assertThat(tag3Stats.getSkipped(), is(1));
    }
}
