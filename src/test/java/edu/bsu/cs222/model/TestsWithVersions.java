package edu.bsu.cs222.model;

import java.util.List;

public class TestsWithVersions extends TestResourceConverter {
    private final VersionListFetcher versionListFetcher = new VersionListFetcher();
    protected List<Version> allVersions = versionListFetcher.getListOfAllVersions();
}
