package edu.bsu.cs222.model;

import java.util.List;

public class TestsWithVersions {
    private final VersionListFetcher versionListFetcher = new VersionListFetcher();
    protected List<Version> allVersions = versionListFetcher.getListOfAllVersions();
}
