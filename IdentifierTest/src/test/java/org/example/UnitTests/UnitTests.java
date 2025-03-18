package org.example.UnitTests;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages(value = {"org.example.Identifier"})
@SuiteDisplayName("All unit tests")
@IncludeTags({"UnitTest"})
public class UnitTests {
}
