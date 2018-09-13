package com.yottabyte.stepDefs;

import com.yottabyte.utils.EmbeddingFile;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.Scenario;
import cucumber.api.java.en.Then;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sunxj
 */
public class EmbedingFileToReport {
    @Then("^I embedding the file \"([^\"]*)\" into report$")
    public void embeddingFile(String fileName) {
        InputStream in = EmbeddingFile.getInputStream(fileName);
        Scenario scenario = SharedDriver.getScenario();
        try {
            scenario.embed(readContent(in), "txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected static byte[] readContent(final InputStream in) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int c = 0;
        int b;
        while ((c < buf.length) && (b = in.read(buf, c, buf.length - c)) >= 0) {
            c += b;
            if (c == 1024) {
                bout.write(buf);
                buf = new byte[1024];
                c = 0;
            }
        }
        if (c != 0) {
            bout.write(buf, 0, c);
        }
        return bout.toByteArray();
    }
}
