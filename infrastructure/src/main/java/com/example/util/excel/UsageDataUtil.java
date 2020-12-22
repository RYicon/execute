//package com.example.util.excel;
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.io.file.FileAppender;
//import cn.hutool.poi.excel.ExcelReader;
//import cn.hutool.poi.excel.ExcelUtil;
//import com.example.util.excel.data.ExchangeProofTemplateBO;
//import com.example.util.excel.data.FlushNameData;
//import org.springframework.util.StringUtils;
//
//import java.io.File;
//import java.util.List;
//
//public class UsageDataUtil {
//    /**
//     * ttdvbkproductdb.prd_product
//     * ttdvbkproductdb.prd_option
//     * actproductdb.prd_activity
//     * actproductdb.prd_option
//     * <p>
//     * excel中产品需要对 ttdvbkproductdb.prd_product和 actproductdb.prd_activity 删除'(子名称操作)'
//     * excel中资源需要对 ttdvbkproductdb.prd_option和actproductdb.prd_option 替换'子名称,'操作
//     *
//     * @param args
//     */
//
//    public static void main(String[] args) {
//
//
//        String updateSqlTemplete = "update ttdticketdb.prd_usage_info set isreservation='%s' , exchangeproof=%s ,exchangeway=%s ,isactive='%s' where id=%s;";
//
//
//        FileUtil.del("/Users/wesley.yu/Documents/工作文档/刷数据/使用方法双写不一致/flush.txt");
//
//
//        File flushFile = FileUtil.touch("/Users/wesley.yu/Documents/工作文档/刷数据/使用方法双写不一致/flush.txt");
//
//        //act 产品
//        ExcelReader actOriginData = ExcelUtil.getReader(FileUtil.file("/Users/wesley.yu/Documents/工作文档/刷数据/使用方法双写不一致/actproductdb.prd_activity.xlsx"));
//        ExcelReader ticketOriginData = ExcelUtil.getReader(FileUtil.file("/Users/wesley.yu/Documents/工作文档/刷数据/使用方法双写不一致/ttdticketslave.mysql.db.ctripcorp.com_ttdticketdb.xlsx"));
//
//        List<FlushNameData> actProductFlushDatas = prdActivityReader.readAll(FlushNameData.class);
//
//        //创建写入文件
//        FileAppender actProductAppender = new FileAppender(flushFile, 16, true);
//        actProductFlushDatas.forEach(s -> {
//            String productName = s.getName().replace("(" + s.getSubName() + ")", "");
//            productName=productName.replace("'","\\'");
//            if (StringUtils.isEmpty(productName)){
//                System.out.println("actproduct:"+ s.getActivityID());
//                return;
//            }
//            String act_product_sql = String.format(actProductFormat, productName, s.getActivityID());
//            actProductAppender.append(act_product_sql);
//        });
//        actProductAppender.flush();
//
//
//        //act 资源
//        List<FlushNameData> actOptionFlushDatas = ExcelUtil.getReader(FileUtil.file("/Users/wesley.yu/Documents/工作文档/刷数据/子名称刷数据/actproductdb.prd_option.xlsx"))
//                .readAll(FlushNameData.class);
//        FileAppender actOptionAppender = new FileAppender(actOptionFile, 16, true);
//        actOptionFlushDatas.forEach(s -> {
//            int length = s.getSubName().indexOf(",");
//            if (length > 0) {
//                String subNameNeedReplace = s.getSubName().substring(0, length+1);
//                String SubName = s.getSubName().replace(subNameNeedReplace, "").replace("'","\\'");
//                String productName = s.getName().replace(subNameNeedReplace, "").replace("'","\\'");
//
//                if (StringUtils.isEmpty(productName)|| StringUtils.isEmpty(SubName)){
//                    System.out.println("actoption:"+s.getOptionID());
//                    return;
//                }
//                String sql = String.format(actOptionFormat, productName, SubName, s.getOptionID());
//                actOptionAppender.append(sql);
//            }
//        });
//        actOptionAppender.flush();
//
//        //ttd 产品
//        ExcelReader ttdActivityReader = ExcelUtil.getReader(FileUtil.file("/Users/wesley.yu/Documents/工作文档/刷数据/子名称刷数据/ttdvbkproductdb.prd_product.xlsx"));
//        List<FlushNameData> tttdProductFlushDatas = ttdActivityReader.readAll(FlushNameData.class);
//        FileAppender ttdProductAppender = new FileAppender(ttdProductFile, 16, true);
//        tttdProductFlushDatas.forEach(s -> {
//            String productName = s.getName().replace("(" + s.getSubName() + ")", "").replace("'","\\'");
//
//            String act_product_sql = String.format(ttdProductFormat, productName, s.getProductID());
//
//            if (StringUtils.isEmpty(productName)){
//                System.out.println("ttdproduct"+ s.getProductID());
//                return;
//            }
//            ttdProductAppender.append(act_product_sql);
//        });
//        ttdProductAppender.flush();
//
//        //ttd 资源
//        List<FlushNameData> ttdOptionFlushDatas = ExcelUtil.getReader(FileUtil.file("/Users/wesley.yu/Documents/工作文档/刷数据/子名称刷数据/ttdvbkproductdb.prd_option.xlsx"))
//                .readAll(FlushNameData.class);
//        FileAppender ttdOptionAppender = new FileAppender(ttdOptionFile, 16, true);
//        ttdOptionFlushDatas.forEach(s -> {
//            int length = s.getSubName().indexOf(",");
//            if (length > 0) {
//                String subNameNeedReplace = s.getSubName().substring(0, length+1);
//                String SubName = s.getSubName().replace(subNameNeedReplace, "").replace("'","\\'");
//                String productName = s.getName().replace(subNameNeedReplace, "").replace("'","\\'");
//
//                if (StringUtils.isEmpty(productName)|| StringUtils.isEmpty(SubName)){
//                    System.out.println("ttdoption:"+ s.getOptionID());
//                    return;
//                }
//                String sql = String.format(ttdOptionFormat, productName, SubName, s.getOptionID());
//                ttdOptionAppender.append(sql);
//            }
//        });
//        ttdOptionAppender.flush();
//
//
//        System.out.println("结束");
//    }
//
//}
