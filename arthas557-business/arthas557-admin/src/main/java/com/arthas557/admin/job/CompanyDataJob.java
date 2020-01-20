package com.arthas557.admin.job;

import com.alibaba.fastjson.JSON;
import com.arthas557.admin.dao.CompanyDataMapper;
import com.arthas557.admin.entity.CompanyData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class CompanyDataJob implements CommandLineRunner {
    @Autowired
    private CompanyDataMapper companyDataMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void run(String... args) throws Exception {
        LambdaQueryWrapper<CompanyData> ge = new LambdaQueryWrapper<CompanyData>().ge(CompanyData::getId, 0);

        List<CompanyData> list = companyDataMapper.selectList(ge);
        if(CollectionUtil.isNotEmpty(list)){
            log.info("旧数据:{}条",list.size());
            companyDataMapper.deleteBatchIds(list.stream().map(CompanyData::getId).collect(Collectors.toList()));
        }

        WritableWorkbook book = null;

        try {
            //Excel获得文件
            Workbook wb = Workbook.getWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"));

            //打开一个文件的副本，并且指定数据写回到原文件
            book = Workbook.createWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"), wb);

            //添加一个工作表
            WritableSheet sheet = book.getSheet(0);
            for (int i = 3793; i < 5744; i++) {

                    Cell[] row = sheet.getRow(i);
                String number = row[0].getContents();
                String preName = row[1].getContents();
                String domainName = row[2].getContents();
                String status = row[4].getContents();
                String dist = row[5].getContents();

                CompanyData companyData = new CompanyData();
                companyData.setLineNumber(i + 1);
                //companyData.setNumber(Integer.parseInt(number));
                companyData.setName(preName);
                companyData.setDomainName(domainName);
                companyData.setStatus(status);
                companyData.setDist(dist);

                companyDataMapper.insert(companyData);
                log.info("对象保存:{}", JSON.toJSONString(companyData));
            }

            book.write();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (BiffException be) {
            be.printStackTrace();
        } finally {

            try {
                if (book != null) {
                    book.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (WriteException we) {
                we.printStackTrace();
            }
        }
    }
}
