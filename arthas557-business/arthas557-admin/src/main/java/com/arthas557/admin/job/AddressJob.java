package com.arthas557.admin.job;

import com.arthas557.admin.dao.AddressMapper;
import com.arthas557.admin.dao.CompanyDataMapper;
import com.arthas557.admin.entity.Address;
import com.arthas557.admin.entity.CompanyData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoleilu.hutool.util.StrUtil;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
//@Component
public class AddressJob implements CommandLineRunner {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CompanyDataMapper companyDataMapper;

    @Override
    public void run(String... args) throws Exception {
//        WritableWorkbook book = null;
//
//        try {
//            //Excel获得文件
//            Workbook wb = Workbook.getWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"));
//
//            //打开一个文件的副本，并且指定数据写回到原文件
//            book = Workbook.createWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"), wb);
//
//            //添加一个工作表
//            WritableSheet sheet = book.getSheet(0);
//            for (int i = 3793; i < 3793 + 20; i++) {
//                try {
//                    String preName = sheet.getRow(i)[1].getContents();
//                    if (StrUtil.isNotBlank(preName)) {
//                        preName = preName.trim();
//                    }
//                    String address = findAddress(preName, i + 1);
//                    sheet.addCell(new Label(3, i, address));
//                }catch (Exception e){
//                    log.error("第{}行,处理失败",i,e);
//                }
//
//                Thread.sleep(10 * 1000);
//
//            }
//
//            book.write();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } catch (BiffException be) {
//            be.printStackTrace();
//        } finally {
//
//            try {
//                if (book != null) {
//                    book.close();
//                }
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            } catch (WriteException we) {
//                we.printStackTrace();
//            }
//        }

        for (int i = 3793; i < 5760; i++) {
            Integer lineNumber = i+1;
            try {
                CompanyData company = findCompany(lineNumber);
                if(null != company){
                    String preName = company.getName();
                    LambdaQueryWrapper<Address> eq = new LambdaQueryWrapper<Address>().eq(Address::getPreName, preName);
                    Address address1 = addressMapper.selectOne(eq);
                    if(null != address1){
                        log.info("公司名称:{},纪录已存在", preName);
                        continue;
                    }

                    findAddress(preName,lineNumber);
                }
            }catch (Exception e){
             log.error("第：{}行处理失败",lineNumber,e);
            }

            Thread.sleep(10* 1000);

        }

    }


    public String findAddress(String preName, Integer lineNumber) throws IOException {
        Map<String,String> cookies = new HashMap<>();
        cookies.put("UM_distinctid","16fb2faf4476-0b30618ec36394-6701b35-144000-16fb2faf4486c6");
        cookies.put("zg_did","%7B%22did%22%3A%20%2216fb2faf6664e-0a4f2886c9c11e-6701b35-144000-16fb2faf6679ea%22%7D");
        cookies.put("_uab_collina","157925578925057862649502");
        cookies.put("acw_tc","73dc081515792557886143115e3d281474b870936001b64d0d36ac99dc");
        cookies.put("QCCSESSID","88j383dhq7sql7uk2eqetfonj4");
        cookies.put("CNZZDATA1254842228","1343956421-1579252978-https%253A%252F%252Fsp0.baidu.com%252F%7C1579409729");
        cookies.put("Hm_lvt_3456bee468c83cc63fb5147f119f1075","1579255789,1579410339");
        cookies.put("SL_GWPT_Show_Hide_tmp","1");
        cookies.put("SL_wptGlobTipTmp","1");
        cookies.put("hasShow=1","1");
        cookies.put("Hm_lpvt_3456bee468c83cc63fb5147f119f1075","1579411775");
        cookies.put("zg_de1d1a35bfa24ce29bbf2c7eb17e6c4f","%7B%22sid%22%3A%201579410338772%2C%22updated%22%3A%201579412109674%2C%22info%22%3A%201579255789166%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22%22%2C%22zs%22%3A%200%2C%22sc%22%3A%200%2C%22cuid%22%3A%20%2266e34d2e32de700754ae37fdb9c699cc%22%7D");
//        cookies.put("","");
//        cookies.put("","");
//        cookies.put("","");



        Connection connect = Jsoup.connect("https://www.qichacha.com/search?key=" + preName);
        connect.cookies(cookies);
        //connect.cookie(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        Document document = connect.get();

        Elements frtrt = document.body().getElementsByClass("frtrt");
        List<FormElement> forms = frtrt.forms();

        String[] split = frtrt.text().split(StrUtil.SPACE);
        String currName = split[0];
        String address = Arrays.stream(split).filter(s -> null != s & s.contains("地址"))
                .findFirst().orElse(StrUtil.EMPTY);
        log.info("当前行号：{},公司名称：{},返回公司名称：{},返回地址:{}",lineNumber,preName,currName,address);
        if (currName.contains(preName)) {
            Address add = new Address();
            add.setAddress(address);
            add.setPreName(preName);
            add.setCurrName(currName);
            add.setLineNumber(lineNumber);

            LambdaQueryWrapper<Address> eq = new LambdaQueryWrapper<Address>().eq(Address::getPreName, preName);
            Address address1 = addressMapper.selectOne(eq);
            if (null != address1) {
                log.info("公司名dsdsd称:{},纪录已存在", preName);
            } else {
                addressMapper.insert(add);
            }
        }

        return address;
    }

    public CompanyData findCompany(Integer lineNumber){
        LambdaQueryWrapper<CompanyData> eq = new LambdaQueryWrapper<CompanyData>().eq(CompanyData::getLineNumber, lineNumber);

        return companyDataMapper.selectOne(eq);


    }


}
