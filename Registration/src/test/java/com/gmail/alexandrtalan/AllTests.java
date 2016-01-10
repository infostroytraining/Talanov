package com.gmail.alexandrtalan;

import com.gmail.alexandrtalan.web.controller.FileUploadServletTest;
import com.gmail.alexandrtalan.web.controller.MainServletTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({MainServletTest.class, FileUploadServletTest.class})
@RunWith(Suite.class)
public class AllTests {
}
