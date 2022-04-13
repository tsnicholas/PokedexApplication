package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VersionListFetcherTest {
    @Test
    public void testGetListOfAllVersions_justGenOne_sizeIs3() {
        TestURLProcessor testURLProcessor = new TestURLProcessor();
        VersionListFetcher versionListFetcher = new VersionListFetcher(testURLProcessor);
        List<Version> genOneVersionList = versionListFetcher.getListOfAllVersions();
        Assertions.assertEquals(3, genOneVersionList.size());
    }
}
