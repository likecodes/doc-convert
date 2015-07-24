package com.likecodes.convert.api;

import com.likecodes.convert.impl.Param;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by leo on 2015/7/18.
 */
public interface PDFConvertApi {

    /**
     * 将url的网页默认参数转成文档到成内存
     * @param url 页面网络地址
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public byte[] getPDFByURL(String url) throws IOException, InterruptedException;

    /**
     *将url的网页将url自定义参数转成文档到成内存
     * @param url 页面网络地址
     * @param globalParams 全局参数
     * @param PagesOptions 页面参数
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public byte[] getPDFByURL(String url,List<Param> globalParams,List<Param> PagesOptions) throws IOException, InterruptedException;

    /**
     *
     * @param path 保持文件路径
     * @param document 文档
     * @return
     * @throws IOException
     */
    public File saveAs(String path, byte[] document) throws IOException;

}
