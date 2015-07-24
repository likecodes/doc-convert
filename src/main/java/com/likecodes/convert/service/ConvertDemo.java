package com.likecodes.convert.service;

import com.likecodes.convert.api.PDFConvertApi;
import com.likecodes.convert.impl.Param;
import com.likecodes.convert.impl.WKHTML2PDFWraper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by leo on 2015/7/24.
 */
public class ConvertDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConvertDemo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PDFConvertApi pdfConvertApi=new WKHTML2PDFWraper();
        byte[] data= new byte[0];
        try {
            data = pdfConvertApi.getPDFByURL("http://www.baidu.com");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"test.pdf\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }


}
