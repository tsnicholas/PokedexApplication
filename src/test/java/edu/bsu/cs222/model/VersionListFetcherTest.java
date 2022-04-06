package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VersionListFetcherTest {
    TestURLProcessor testURLProcessor = new TestURLProcessor();
    VersionListFetcher versionListFetcher = new VersionListFetcher(testURLProcessor);

    @Test
    public void testGetListOfAllVersions_justGenOne_sizeIs3() {
        List<Version> genOneVersionList = versionListFetcher.getListOfAllVersions();
        Assertions.assertEquals(3, genOneVersionList.size());
    }
}
