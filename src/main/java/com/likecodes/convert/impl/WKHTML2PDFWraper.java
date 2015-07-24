package com.likecodes.convert.impl;

import com.likecodes.convert.api.PDFConvertApi;

import java.io.*;
import java.util.*;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * Created by leo on 2015/7/18.
 * email:likecodes@qq.com
 */
public class WKHTML2PDFWraper implements PDFConvertApi {

    static final String WKHTMLTOPDF = "wkhtmltopdf";

    /**
     * 保持PDF文件到path中
     *
     * @param path
     * @param document
     * @return
     * @throws IOException
     */
    public File saveAs(String path, byte[] document) throws IOException {
        File file = new File(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        bufferedOutputStream.write(document);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return file;
    }

    public byte[] getPDFByURL(String url, List<Param> globalParams, List<Param> PagesOptions) throws IOException, InterruptedException {

        CommandLine cmdLine = new CommandLine(WKHTMLTOPDF);
        for (Param param : globalParams) {
            cmdLine.addArgument("-" + param.getOption());
            cmdLine.addArgument(param.getValue());
        }
        cmdLine.addArgument(url);
        for (Param param : PagesOptions) {
            cmdLine.addArgument("--" + param.getOption());
        }
        cmdLine.addArgument("-");
        return getDocument(cmdLine);


    }

    public byte[] getPDFByURL(String url) throws IOException, InterruptedException {
        CommandLine cmdLine = new CommandLine("wkhtmltopdf");
        cmdLine.addArgument(url, true);
        cmdLine.addArgument("--print-media-type", false);
        cmdLine.addArgument("-", false);
        return getDocument(cmdLine);


    }

    private byte[] getDocument(CommandLine cmdLine) throws IOException {
        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream, errorStream);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(300 * 1000);
        executor.setWatchdog(watchdog);
        executor.setExitValues(null);
        executor.setStreamHandler(pumpStreamHandler);
        executor.execute(cmdLine);
        return outputStream.toByteArray();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Param> glist = new ArrayList<Param>();
        Param dpi = new Param("d", "300");
        glist.add(dpi);
        Param size = new Param("s", "A4");
        glist.add(size);
        Param pageParam = new Param("print-media-type", null);
        List<Param> plist = new ArrayList<Param>();
        plist.add(pageParam);
        WKHTML2PDFWraper wkhtml = new WKHTML2PDFWraper();
        wkhtml.saveAs("E:/test345.pdf", wkhtml.getPDFByURL("http://www.cnblogs.com/huxiao-tee/p/4657851.html", glist, plist));

    }
}