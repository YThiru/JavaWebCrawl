package com.webcrawl.service.tools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HelperTest {

    private Helper helper = new Helper();
    private String[] args = null;

    @Before
    public void setUp() throws Exception {
        args = new String[2];
    }

    @Test
    public void testMoreThan2Arguments() throws Exception {
        args = new String[3];
        args[0] = "http://www.google.com";
        args[1] = "1";
        args[2] = "5";
        Assert.assertEquals("Please enter url and crawl stage", helper.validateInput(args));
    }

    @Test
    public void testStageArguments() throws Exception {
        args = new String[2];
        args[0] = "http://www.google.com";
        args[1] = "A";
        Assert.assertEquals("Invalid crawl stage number", helper.validateInput(args));
    }

    @Test
    public void testUrlArguments() throws Exception {
        args = new String[2];
        args[0] = "google.com";
        args[1] = "1";
        Assert.assertEquals("Invalid URL", helper.validateInput(args));
    }

    @Test
    public void testMoreStagesArguments() throws Exception {
        args = new String[2];
        args[0] = "http://google.com";
        args[1] = "11";
        Assert.assertEquals("Invalid crawl stage number", helper.validateInput(args));
    }

    @Test
    public void testEmptyStagesArguments() throws Exception {
        args = new String[2];
        args[0] = "http://google.com";
        args[1] = "";
        Assert.assertEquals("Invalid crawl stage number", helper.validateInput(args));
    }

    @Test
    public void testValidArguments() throws Exception {
        args = new String[2];
        args[0] = "http://google.com";
        args[1] = "1";
        Assert.assertEquals(null, helper.validateInput(args));
    }
}
