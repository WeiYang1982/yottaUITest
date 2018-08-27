package com.yottabyte.utils;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.Scenario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EmbeddingFile {

    /**
     * 将指定的文件注入到测试报告中 根据浏览器是否打开在远程区分文件的存放路径
     * @param fileName  需要注入的文件名称
     */
    public void embeddingPdfToscenario(String fileName) {
        String flag = SharedDriver.WebDriverType;
        String sp = File.separator;
        ConfigManager config = new ConfigManager();
        List<byte[]> list = new ArrayList<>();
        PdfToImages fileConert = new PdfToImages();
        InputStream in = null;
        Scenario scenario = SharedDriver.getScenario();
        try {
            if ("Remote".equalsIgnoreCase(flag)) {
                SFTPUtil sftpUtil = new SFTPUtil(config.get("ftp_user"), config.get("ftp_password"), config.get("selenium_server_host"), 22);
                sftpUtil.login();
                in = sftpUtil.readFile("./", "target/download-files/" + fileName);
            }else {
                String downloadFilepath = System.getProperty("user.dir") + sp + "target" + sp + "download-files";
                in = new FileInputStream(downloadFilepath + sp + fileName);
            }
            list = fileConert.pdftoimages(in);
            for (byte[] b : list) {
                scenario.embed(b, "image/png");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            GetLogger.getLogger().error(e1.getMessage());
        }
    }

    public static void main(String args[]) {
        String fileName = "1122.pdf";
        new EmbeddingFile().embeddingPdfToscenario(fileName);
    }
}
