package com.arthas557.admin.controller;

import com.arthas557.admin.dao.AddressMapper;
import com.arthas557.admin.dao.UserMapper;
import com.arthas557.admin.entity.Address;
import com.arthas557.admin.entity.User;
import com.arthas557.admin.mq.UserClient;
import com.arthas557.admin.service.UserService;
import com.arthas557.common.BaseResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoleilu.hutool.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RestController
@RequestMapping("/admin")
@Api(description = "admin swagger测试")
public class AdminController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private AddressMapper addressMapper;

    @RequestMapping("/sayAdmin")
    @ApiOperation("查询用户")
    public BaseResult<List<User>> sayAdmin(){
        System.out.println("HELLO222222222222222");
        BaseResult<List<User>> result = new BaseResult<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> gt = queryWrapper.lambda().gt(true, User::getId, 0);
        List<User> users = userMapper.selectList(gt);
        result.setData(users);
        userClient.output().send(MessageBuilder.withPayload(users.get(0)).build());

        return result;
    }

    @RequestMapping("/jsonException")
    public BaseResult jsonException (@RequestBody User user){
        BaseResult<User> result = new BaseResult<>();
         result.setData(user);
        return result;
    }

    @RequestMapping("/aspectTest")
    public void aspectTest(){
        User user = new User();
        user.setUserName("www");
        user.setPassword("123456");
        userService.insertException(user);
    }


    @RequestMapping("/writeCompany")
    public void writeCompany(){

        WritableWorkbook book = null;

        try {
            //Excel获得文件
            Workbook wb = Workbook.getWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"));

            //打开一个文件的副本，并且指定数据写回到原文件
            book = Workbook.createWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"), wb);

            //添加一个工作表
            WritableSheet sheet = book.getSheet(0);
            for (int i = 3793; i < 5760; i++) {
                Integer lineNumber  = i + 1;
                LambdaQueryWrapper<Address> eq = new LambdaQueryWrapper<Address>().eq(Address::getLineNumber, lineNumber);
                Address address = addressMapper.selectOne(eq);
                if(null == address){
                    continue;
                }
                String preName = address.getPreName();
                String address1 = address.getAddress();
                if(address1.contains("地址：")){
                    address1 = address1.replace("地址：",StrUtil.EMPTY);
                }
                sheet.addCell(new Label(3,i,address1));
                log.info("excel行号:{},公司名称:{},公司地址:{}",i,preName,address1);
            }


            book.write();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        catch (BiffException be) {
            be.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {

            try {
                if (book != null) {
                    book.close();
                }
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
            catch (WriteException we) {
                we.printStackTrace();
            }
        }

    }
}
