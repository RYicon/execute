package com.example.util.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.util.excel.data.ExchangeProofTemplateBO;
import com.example.util.excel.data.FlushNameData;

import java.io.File;
import java.util.List;

public class ExchangeProofTemplate {
    public static void main(String[] args) {

        String sql="select count(*) as counts from ttdticketdb.prd_exchangeprooftemplate where  id = %s and itemidlist= '%s' and UserDefined=%s and isActive='%s' ";


        String sql2="select * from prd_exchangeprooftemplate where id not in () ;";

        //删除上次被创建的文件
        FileUtil.del("/Users/wesley.yu/Documents/工作文档/刷数据/prd_exchangeprooftemplate.txt");
        //存储生成的sql文件
        File exchangeprooftemplateSqlFile = FileUtil.touch("/Users/wesley.yu/Documents/工作文档/刷数据/prd_exchangeprooftemplate.txt");

        //读取的excel
        ExcelReader exchangeprooftemplateReader = ExcelUtil.getReader(FileUtil.file("/Users/wesley.yu/Documents/工作文档/刷数据/prd_exchangeprooftemplate/exchange.xlsx"));
        List<ExchangeProofTemplateBO> exchangeprooftemplates = exchangeprooftemplateReader.readAll(ExchangeProofTemplateBO.class);

        ExchangeProofTemplateBO exchangeProofTemplateBO = exchangeprooftemplates.get(exchangeprooftemplates.size() - 1);
        //
        FileAppender exchangeprooftemplateAppender = new FileAppender(exchangeprooftemplateSqlFile, 16, true);
        exchangeprooftemplateAppender.append("select sum(counts) from (");
        exchangeprooftemplates.forEach(s->{

            String act_product_sql = String.format(sql, s.getId(), s.getItemIdList(), s.getUserDefined(), s.getIsActive());
            if (exchangeProofTemplateBO.getId() == s.getId()) {
                exchangeprooftemplateAppender.append(act_product_sql);
            } else {
                exchangeprooftemplateAppender.append(act_product_sql + " union all");
            }
        });
        exchangeprooftemplateAppender.append(") a;");
//
//        exchangeprooftemplateAppender.append("select * from prd_exchangeprooftemplate where id not in (");
//        int count =0;
//        for (ExchangeProofTemplateBO s : exchangeprooftemplates) {
//            if (exchangeProofTemplateBO.getId() == s.getId()) {
//                exchangeprooftemplateAppender.append(String.valueOf(s.getId()));
//            } else {
//                exchangeprooftemplateAppender.append(String.valueOf(s.getId()) + ",");
//            }
//            count++;
//        }
//        exchangeprooftemplateAppender.append(") ;");
//        System.out.println(count);
        exchangeprooftemplateAppender.flush();


    }

}
