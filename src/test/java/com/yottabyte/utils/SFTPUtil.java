package com.yottabyte.utils;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

/**
 * 类说明 sftp工具类
 */
public class SFTPUtil {

    private ChannelSftp sftp;

    private Session session;
    /**
     * SFTP 登录用户名
     */
    private String username;
    /**
     * SFTP 登录密码
     */
    private String password;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * SFTP 服务器地址IP地址
     */
    private String host;
    /**
     * SFTP 端口
     */
    private int port;


    /**
     * 构造基于密码认证的sftp对象
     */
    public SFTPUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SFTPUtil(String username, String host, int port, String privateKey) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    public SFTPUtil() {
    }


    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
            }

            session = jsch.getSession(username, host, port);

            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }


    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param basePath     服务器的基础路径
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     */
    public void upload(String basePath, String directory, String sftpFileName, InputStream input) throws SftpException {
        try {
            sftp.cd(basePath);
            sftp.cd(directory);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String[] dirs = directory.split("/");
            String tempPath = basePath;
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) continue;
                tempPath += "/" + dir;
                try {
                    sftp.cd(tempPath);
                } catch (SftpException ex) {
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        sftp.put(input, sftpFileName);  //上传文件
    }


    /**
     * 下载文件。
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     */
    public void download(String directory, String downloadFile, String saveFile) throws SftpException, FileNotFoundException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        File file = new File(saveFile);
        sftp.get(downloadFile, new FileOutputStream(file));
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);

        byte[] fileData = IOUtils.toByteArray(is);

        return fileData;
    }


    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) throws SftpException {
        sftp.cd(directory);
        sftp.rm(deleteFile);
    }


    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    public Vector<?> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 执行命令
     *
     * @param command 命令
     * @return
     */
    public String execCommand(String command) {
        StringBuffer sb = new StringBuffer();
        ChannelExec exec = null;
        try {
            exec = (ChannelExec) session.openChannel("exec");
            exec.setCommand(command);
            int exitStatus = exec.getExitStatus(); //退出状态为-1，直到通道关闭
            System.out.println(exitStatus);
            exec.connect();
            InputStream in = exec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                sb.append(buf + "\n");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (exec != null && !exec.isClosed()) {
                exec.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return sb.toString();
    }

    //上传文件测试
    public static void main(String[] args) throws Exception {
        SFTPUtil sftp = new SFTPUtil("ftp", "YottaByte&2018", "192.168.1.164", 22);
        sftp.login();
        String fileName = "src/test/resources/testdata/alertPlugins/hengshuiyinhang_socket.py";
        File file = new File(fileName);
        InputStream is = new FileInputStream(file);
        String s = File.separator;
        sftp.upload("/", "alertPlugins/a", "hengshuiyinhang_socket.py", is);
//        String result = sftp.execCommand("/usr/bin/python /home/sendlog/200/log_gen.py -d192.168.1.200 -p5140 -l/home/sendlog/all_format_log/baimi -c/home/sendlog/200/200.conf -r2");
//        System.out.println(result);
        sftp.logout();
    }
}

