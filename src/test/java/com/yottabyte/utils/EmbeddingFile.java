package com.yottabyte.utils;

import com.yottabyte.config.ConfigManager;
import com.yottabyte.webDriver.SharedDriver;
import cucumber.api.Scenario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class EmbeddingFile {

    /**
     * 将指定的文件注入到测试报告中 根据浏览器是否打开在远程区分文件的存放路径
     *
     * @param fileName 需要注入的文件名称
     */
    public static void embeddingPdfToscenario(String fileName) {
        Scenario scenario = SharedDriver.getScenario();
        PdfToImages fileConert = new PdfToImages();
        List<byte[]> list = fileConert.pdftoimages(getInputStream(fileName));
        for (byte[] b : list) {
            scenario.embed(b, "image/png");
        }
    }

    public static InputStream getInputStream(String fileName) {
        String flag = SharedDriver.WebDriverType;
        String sp = File.separator;
        ConfigManager config = new ConfigManager();
        InputStream in = null;
        try {
            if ("Remote".equalsIgnoreCase(flag)) {
                SFTPUtil sftpUtil = new SFTPUtil(config.get("ftp_user"), config.get("ftp_password"), config.get("selenium_server_host"), 22);
                sftpUtil.login();
                in = sftpUtil.readFile("./", "target/download-files/" + fileName);
            } else {
                String downloadFilepath = System.getProperty("user.dir") + sp + "target" + sp + "download-files";
                in = new FileInputStream(downloadFilepath + sp + fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static void main(String args[]) {
        String fileName = "1122.pdf";
        new EmbeddingFile().embeddingPdfToscenario(fileName);
    }
}
