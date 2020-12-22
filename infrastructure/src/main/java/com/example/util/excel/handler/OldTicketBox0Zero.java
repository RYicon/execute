package com.example.util.excel.handler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.base.json.JsonUtils;
import com.example.util.ExcelCall;
import com.example.util.excel.data.OldTicketBox0ZeroData;
import com.example.util.excel.data.Phone400;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.util.ExcelCall.createFile;

public class OldTicketBox0Zero {

    public static void main(String[] args) throws Exception {

        String originDataPrefPath="/Users/wesley.yu/Documents/工作文档/刷数据/老门票对接资源联票打包0元票问题/";

        List<OldTicketBox0ZeroData> needChangeDatas= Lists.newArrayList();




        String activityOriginDataPath = originDataPrefPath+"333.xlsx";
        String activityWritePath=originDataPrefPath+"333.txt";



        ExcelCall.call(activityOriginDataPath,activityWritePath,(reader, fileAppender)->{
            int count=0;
            String template = "update prd_activity set IsOnline='F' ,IsActive='F' where activityid=%s;";
            List<OldTicketBox0ZeroData> originDatas = reader.readAll(OldTicketBox0ZeroData.class);
            for (OldTicketBox0ZeroData s : originDatas) {
                if (s.getIsOnline().equalsIgnoreCase("T")) {
                    count++;
                    fileAppender.append( String.format(template,s.getActivityId())) ;


                }

            }
            return "prd_activity结束，数据量："+count;
        });





//
//        String rollbackTemplete = "select * from  prd_activity where  product_id in(%s) ;select * from  prd_option where  optionid in(%s) ;select * from  prd_activity where  activityid in(%s) ;";
//        FileAppender rollBackWriteFile = createFile(originDataPrefPath+"rollback.txt");
//        String productIds="";
//        String optionIds="";
//        String activityIds="";
//
//        for (Phone400 s : needChangeProducts) {
//            if (StringUtils.isNotEmpty(s.getProduct_id())) {
//                productIds += "," + s.getProduct_id();
//            }
//            if (StringUtils.isNotEmpty(s.getOptionID())) {
//                optionIds += "," + s.getOptionID();
//            }
//
//            if (StringUtils.isNotEmpty(s.getActivityID())) {
//                activityIds += "," + s.getActivityID();
//            }
//
//        }
//
//        System.out.println("总条数："+needChangeProducts.size());
//
//        productIds= productIds.substring(1);
//        optionIds=optionIds.substring(1);
//        activityIds=activityIds.substring(1);
//
//        rollBackWriteFile.append( String.format(rollbackTemplete,productIds,optionIds,activityIds));
//        rollBackWriteFile.flush();
//


        System.out.println("结束");
    }







    static String replace(String phone,String subPhone){
        return phone.replace(subPhone,0+subPhone);
    }

}
