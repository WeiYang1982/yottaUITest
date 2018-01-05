package com.yottabyte.utils;

import com.yottabyte.constants.ImageCompareConstants;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import org.assertj.core.api.AbstractAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.google.common.io.ByteStreams.toByteArray;

public class AssertImagesEqual extends AbstractAssert<AssertImagesEqual, TakeScreenShot> {
    ImageComparison comparison = new ImageComparison(ImageCompareConstants.PIXEL_PER_BLOCK_X,ImageCompareConstants.PIXEL_PER_BLOCK_Y,ImageCompareConstants.threshold);
    Scenario scenario = SharedDriver.getScenario();

    public AssertImagesEqual(TakeScreenShot actual) {
        super(actual, AssertImagesEqual.class);
    }

    public static AssertImagesEqual assertThat(TakeScreenShot actual) {
        return new AssertImagesEqual(actual);
    }

    public AssertImagesEqual isEqual(String expectImageName,String actualImageName){
        String sp = File.separator;
        String expectPath = System.getProperty("user.dir") + sp + "src" + sp +"test" + sp + "resources" + sp + "expect" + sp;
        String outPutPath = System.getProperty("user.dir") + sp + "target" + sp + "cucumber-html-reports" + sp + "embeddings" + sp + scenario.getName() + sp + "assert_image_change.jpg";
        try {
            System.out.println("start compare " + expectImageName + " and " + actualImageName);
            if (!comparison.fuzzyEqual(expectPath + expectImageName,actualImageName,outPutPath)) {
                InputStream in = new FileInputStream(outPutPath);
                byte[] data = toByteArray(in);
                scenario.embed(data,"image/png");
                in.close();
                failWithMessage("Expected image <%s> is not the same as the actual <%s> see the detail <%s>", expectImageName, actual,outPutPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


}
