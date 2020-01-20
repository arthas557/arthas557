package other;

import com.alibaba.fastjson.JSON;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.http.cookie.Cookie;
import org.apache.log4j.net.SocketServer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.HttpCookie;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test2 {


    public static void main(String[] args) throws IOException {
       // HttpRequest get = HttpUtil.createGet("https://www.qichacha.com/search?key=瑞房科技（上海）有限公司");
        //get.cookie(new HttpCookie("dad","dsda"));

        Connection connect = Jsoup.connect("https://www.qichacha.com/search?key=瑞房科技（上海）有限公司");
        connect.cookie("dadsa","dsada");
        Document document = connect.get();

        Elements frtrt = document.body().getElementsByClass("frtrt");
        List<FormElement> forms = frtrt.forms();
        System.out.println(frtrt.text());
        String[] split = frtrt.text().split(StrUtil.SPACE);
        for (String s : split) {
            System.out.println(s);
        }
        forms.forEach(System.out::println);

    }

    public String findAddress(String name) throws IOException {
        Connection connect = Jsoup.connect("https://www.qichacha.com/search?key=瑞房科技（上海）有限公司");
        connect.cookie("dadsa","dsada");
        Document document = connect.get();

        Elements frtrt = document.body().getElementsByClass("frtrt");
        List<FormElement> forms = frtrt.forms();
        System.out.println(frtrt.text());
        String[] split = frtrt.text().split(StrUtil.SPACE);
        for (String s : split) {
            System.out.println(s);
        }
        forms.forEach(System.out::println);
        return null;
    }


    @Test
    public void excel (){

        WritableWorkbook book = null;

        try {
            //Excel获得文件
            Workbook wb = Workbook.getWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"));

            //打开一个文件的副本，并且指定数据写回到原文件
            book = Workbook.createWorkbook(new File("E:\\ld\\待查地址（未公安备案网站所属单位）.xls"), wb);

            //添加一个工作表
            WritableSheet sheet = book.getSheet(0);
            int rows = sheet.getRows();
            /*for (int i = 0; i < rows; i++) {
                Cell[] row = sheet.getRow(i+1);
                System.out.println(row[1].getContents());
            }*/

            for (int i = 3794; i < 3794+2; i++) {
                String contents = sheet.getRow(i)[1].getContents();
                if(StrUtil.isNotBlank(contents)){
                    contents = contents.trim();
                }
                sheet.addCell(new Label(3,i,"gdsadsd"));
            }

            book.write();
        }
        catch (IOException  ioe) {
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



    @Test
    public void login(){
        HttpRequest post = HttpUtil.createPost("https://www.qichacha.com/user_loginaction");
        HttpCookie httpCookie = new HttpCookie("a", "b");
        post.cookie(httpCookie);
        Map<String,Object> params = new HashMap<>();
        params.put("phone_prefix","+86");
        params.put("nameNormal","16532986288");
        params.put("pwdNormal","sjk92184");
        params.put("keep","option1");
        params.put("csessionid_one","01U2B0tTsnzbBShobDhSLSaTtR91D1fvcu1UzyIvEVoFm79IgutX2BuraPC705xZSc_SACvQ7y0gh3d0xIxWmtGGCPTxLVPmFVWgNrJfbz2ImSw0lYp1B_VES83HRrEbXuHjQqexm7i7BefXlv_p3y9eDUma2ZVFKNrDPSAPwY6B0");
        params.put("sig_one","05Ps4MZ06ErAC1i_OHdtOFyKxVP3D1YpmnCbXlWMGJ9fmA7ZVymoDAXDMfhBNbTbVIn3yBhAygqnID2psnQZN-FClFbqRruMu_46NtARdKUSRxinfSdas4-rLfoDa9hyYOEXERaAdXRd2oLHX6jJE_vZLDsMY_yPB1Zlp-dgGFtF_uSDCV-8-LXtZvs7hfhYwXXmb6BeaX-CtGRyFe4RUPskRqIdIu90aVlBCzW9AxRbsn6aUdpzbd0VD5cYiA4bFyL5nw8iRm9qsvG2bCWI1au5n3rncelmjzkAgtO_6o0NpYAcVtkKU7rrjUeKerKYNQkSwv7ur97rQ0UL6dOVLF91QtfQFGmhEw0EceW15MsmhchOB1_C3uG5umYk0gyKcp");
        params.put("token_one","QNYX:1579410507324:0.47384472779322495");
        params.put("scene_one","login");
        params.put("verify_type",1);
        post.form(params);
        HttpResponse execute = post.execute();
        int status = execute.getStatus();
        List<HttpCookie> cookie = execute.getCookie();
        String body = execute.body();
        System.out.println(status);
        System.out.println(JSON.toJSON(cookie));
        System.out.println(body);
    }


    public void ipPool(){
        HttpRequest post = HttpUtil.createPost("");
        Proxy proxy = Proxy.NO_PROXY;
        SocketAddress address = proxy.address();

    }

}
