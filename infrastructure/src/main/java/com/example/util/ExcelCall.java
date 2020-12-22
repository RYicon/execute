package com.example.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;

public class ExcelCall {

    public interface ISoaCaller {
        /**
         * 读excel处理后 写入指定文件
         *
         * @return 目标函数返回值
         * @throws Exception 异常
         */
        String action( ExcelReader reader,FileAppender fileAppender ) throws Exception;//NOSONAR


    }


    public interface ISoaCallerV2 {

        /**
         * 读excel处理后 写入指定文件
         *
         * @return 目标函数返回值
         * @throws Exception 异常
         */
        String action( ExcelReader reader,FileAppender fileAppender,FileAppender rollBackAppender ) throws Exception;//NOSONAR
    }


    /**
     * @param caller 执行逻辑
     * @return T 返回值
     */
    public static void call(String originPath,String writePath,ISoaCaller caller) throws Exception {

        FileUtil.del(writePath);

        File flushFile = FileUtil.touch(writePath);

        //源数据读取者
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(originPath));

        //创建写入文件
        FileAppender fileAppender = new FileAppender(flushFile, 16, true);

        //自定义 写入逻辑
        String action = caller.action(reader, fileAppender);

        //清除缓存
        fileAppender.flush();

        System.out.println(action);
    }


    /**
     * 将soa调用抛出的异常包装成运行时异常,适用于非proxy远程调用
     *
     *
     * @return T 返回值
     */
    public static FileAppender createFile(String writePath) throws Exception {

        FileUtil.del(writePath);

        File flushFile = FileUtil.touch(writePath);

        //创建写入文件
        return new FileAppender(flushFile, 16, true);


    }

}
