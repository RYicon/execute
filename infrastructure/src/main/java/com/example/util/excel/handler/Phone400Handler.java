package com.example.util.excel.handler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.base.json.JsonUtils;
import com.example.util.ExcelCall;
import com.example.util.excel.data.Phone400;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.util.ExcelCall.createFile;

public class Phone400Handler {

    public static void main(String[] args) throws Exception {

        String originDataPrefPath="/Users/wesley.yu/Documents/工作文档/刷数据/400电话升位/";

        List<Phone400> needChangeProducts= Lists.newArrayList();

//        List<String> needUpPhones = ExcelUtil.getReader(FileUtil.file(originDataPrefPath+"需要升位的分机号.xlsx")).read().stream().map(s->String.valueOf(s.get(0))).collect(Collectors.toList());

        List<String> needUpPhones= Arrays.asList("695664");
        String productVendorTemplete = "update product_vendor set service_phone='%s' where product_id=%s;";
        String productVendorOriginDataPath = originDataPrefPath+"product_vendor.xlsx";
        String productVendorWritePath=originDataPrefPath+"productVendor.txt";


        ExcelCall.call(productVendorOriginDataPath,productVendorWritePath,(reader, fileAppender)->{
            List<Phone400> productVendor = reader.readAll(Phone400.class);
            productVendor.forEach(s->{
                try {
                    String subPhone = s.getService_phone().split(",")[1];
                    if (needUpPhones.contains(subPhone)){
                        String newPhoneSql = String.format(productVendorTemplete,replace(s.getService_phone(),subPhone), s.getProduct_id());
                        fileAppender.append(newPhoneSql);

                        Phone400 phone400 = new Phone400();
                        phone400.setProduct_id(s.getProduct_id());
                        needChangeProducts.add(phone400);
                    }
                } catch (Exception e) {
                    if (s.getService_phone().length()==6)
                    {
                        System.out.println(JsonUtils.toString(s));
                    }
                }
            });


            return "productvendor结束";
        });



        String prdActivityTemplete = "update prd_activity set ServicePhone='%s' where ActivityID=%s;";
        String prdActivityWritePath=originDataPrefPath+"prd_activity.txt";
        String pprdActivityOriginDataPath = originDataPrefPath+"prd_activity.xlsx";
        ExcelCall.call(pprdActivityOriginDataPath,prdActivityWritePath,(reader, fileAppender)->{
            List<Phone400> productVendor = reader.readAll(Phone400.class);
            productVendor.forEach(s->{
                try {
                    String subPhone = s.getServicePhone().split(",")[1];
                    if (needUpPhones.contains(subPhone)){
                        String newPhoneSql = String.format(prdActivityTemplete,replace(s.getServicePhone(),subPhone), s.getActivityID());
                        fileAppender.append(newPhoneSql);

                        Phone400 phone400 = new Phone400();
                        phone400.setActivityID(s.getActivityID());
                        needChangeProducts.add(phone400);
                    }
                } catch (Exception e) {
                    if (s.getServicePhone().length()==6)
                    {
                        System.out.println(JsonUtils.toString(s));
                    }
                }
            });


            return "prd_activity结束";
        });

        String prdOptionTemplete = "update prd_option set ServicePhone='%s' where OptionID=%s;";
        String prdOptionOriginDataPath = originDataPrefPath+"prd_option.xlsx";
        String prdOptionWritePath=originDataPrefPath+"prd_option.txt";
        ExcelCall.call(prdOptionOriginDataPath,prdOptionWritePath,(reader, fileAppender)->{
            List<Phone400> productVendor = reader.readAll(Phone400.class);
            productVendor.forEach(s->{
                try {
                    String subPhone = s.getServicePhone().split(",")[1];
                    if (needUpPhones.contains(subPhone)){
                        String newPhoneSql = String.format(prdOptionTemplete,replace(s.getServicePhone(),subPhone), s.getOptionID());
                        fileAppender.append(newPhoneSql);



                        Phone400 phone400 = new Phone400();
                        phone400.setOptionID(s.getOptionID());
                        needChangeProducts.add(phone400);
                    }
                } catch (Exception e) {
                    if (s.getServicePhone().length()==6)
                    {
                        System.out.println(JsonUtils.toString(s));
                    }
                }
            });


            return "prd_option结束";
        });


        String rollbackTemplete = "select * from  product_vendor where  product_id in(%s) ;select * from  prd_option where  optionid in(%s) ;select * from  prd_activity where  activityid in(%s) ;";
        FileAppender rollBackWriteFile = createFile(originDataPrefPath+"rollback.txt");
        String productIds="";
        String optionIds="";
        String activityIds="";

        for (Phone400 s : needChangeProducts) {
            if (StringUtils.isNotEmpty(s.getProduct_id())) {
                productIds += "," + s.getProduct_id();
            }
            if (StringUtils.isNotEmpty(s.getOptionID())) {
                optionIds += "," + s.getOptionID();
            }

            if (StringUtils.isNotEmpty(s.getActivityID())) {
                activityIds += "," + s.getActivityID();
            }

        }

        System.out.println("总条数："+needChangeProducts.size());

        productIds= productIds.substring(1);
        optionIds=optionIds.substring(1);
        activityIds=activityIds.substring(1);

       rollBackWriteFile.append( String.format(rollbackTemplete,productIds,optionIds,activityIds));
        rollBackWriteFile.flush();



        System.out.println("结束");
    }







    static String replace(String phone,String subPhone){
        return phone.replace(subPhone,0+subPhone);
    }

}
