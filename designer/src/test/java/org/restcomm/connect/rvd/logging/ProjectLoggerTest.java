/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2014, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package org.restcomm.connect.rvd.logging;

import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.restcomm.connect.rvd.RvdConfiguration;
import org.restcomm.connect.rvd.TestUtils;
import org.restcomm.connect.rvd.concurrency.LogRotationSemaphore;
import org.restcomm.connect.rvd.model.StepMarshaler;

import java.io.File;
import java.io.IOException;

/**
 * Tests ProjectLogger-specific functionality.
 *
 * For more elaborate tests see {@link CustomLoggerTest}
 *
 * @author otsakir@gmail.com - Orestis Tsakiridis
 */
public class ProjectLoggerTest {

    File workspaceDir;
    File projectDir;
    ProjectLogger logger;
    RvdConfiguration configuration;

    @Before
    public void before() throws IOException {
        workspaceDir = TestUtils.createTempWorkspace();
        StepMarshaler marshaller = new StepMarshaler();
        RvdConfiguration configuration = Mockito.mock(RvdConfiguration.class);
        Mockito.when(configuration.getWelcomeMessage()).thenReturn(RvdConfiguration.DEFAULT_WELCOME_MESSAGE); // used when creating a new project
        projectDir = TestUtils.createDefaultProject("AP123", "owner@telestax.com", workspaceDir, marshaller, configuration);
        LogRotationSemaphore semaphore = new LogRotationSemaphore();
        logger = new ProjectLogger(projectDir.getPath() + "/rvd",marshaller, semaphore); // .../rvd.log
    }

    @After
    public void after() throws IOException {
        FileUtils.deleteDirectory(workspaceDir);
    }


    @Test
    public void testMessageMarshalling() throws IOException {
        logger.log().message("hello world").done();
        File logfile = new File(logger.getLogFilePath());
        String content = FileUtils.readFileToString(logfile);
        Assert.assertTrue(content.contains("\"hello world\""));
        logfile.delete();

        // do not marshall message ('false' parameter)
        logger.log().messageNoMarshalling("http://test.com/script.php?a=1&b=2").done();
        content = FileUtils.readFileToString(logfile);
        Assert.assertTrue(content.contains("http://test.com/script.php?a=1&b=2"));
        logfile.delete();
        // DO marshal message
        logger.log().message("http://test.com/script.php?a=1&b=2").done();
        content = FileUtils.readFileToString(logfile);
        Assert.assertTrue(content.contains("http://test.com/script.php?a\\u003d1\\u0026b\\u003d2"));
        logfile.delete();
    }


}
