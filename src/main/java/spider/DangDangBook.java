package spider;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author: panlf
 * @Date: 2019/7/22 15:25
 */
public class DangDangBook {
    private static String BASE_URL="http://category.dangdang.com/";
    private static String BASE_DIR_PATH="D:\\book";
    private static String BASE_FILE_PATH=BASE_DIR_PATH + "\\sqlresult_%s.txt";
    private static String BASE_ERR_PATH=BASE_DIR_PATH + "\\errorUrl.txt";
    private static String[][] TYPE_URL={
            {"cp01.43.00.00.00.00.html","cp01.41.00.00.00.00.html","cp01.47.00.00.00.00.html","cp01.03.00.00.00.00.html","cp01.22.00.00.00.00.html"},
            {"cp01.21.00.00.00.00.html","cp01.07.00.00.00.00.html","cp01.05.00.00.00.00.html", "cp01.45.00.00.00.00.html","cp01.56.00.00.00.00.html"},
            {"cp01.28.00.00.00.00.html","cp01.01.00.00.00.00.html","cp01.38.00.00.00.00.html","cp01.24.00.00.00.00.html","cp01.31.00.00.00.00.html"},
            {"cp01.06.00.00.00.00.html", "cp01.55.00.00.00.00.html","cp01.54.00.00.00.00.html","cp01.26.00.00.00.00.html","cp01.18.00.00.00.00.html"},
            {"cp01.27.00.00.00.00.html","cp01.10.00.00.00.00.html","cp01.15.00.00.00.00.html","cp01.32.00.00.00.00.html", "cp01.58.00.00.00.00.html"},
            {"cp01.25.00.00.00.00.html","cp01.63.00.00.00.00.html","cp01.50.00.00.00.00.html","cp01.17.00.00.00.00.html","cp01.52.00.00.00.00.html"},
            {"cp01.12.00.00.00.00.html","cp01.62.00.00.00.00.html", "cp01.09.00.00.00.00.html","cp01.20.00.00.00.00.html","cp01.30.00.00.00.00.html"},
            {"cp01.34.00.00.00.00.html", "cp01.16.00.00.00.00.html","cp01.14.00.00.00.00.html","cp01.66.00.00.00.00.html","cp01.19.00.00.00.00.html"},
            {"cp01.49.00.00.00.00.html","cp01.11.00.00.00.00.html","cp01.04.00.00.00.00.html","cp01.59.00.00.00.00.html", "cp01.77.00.00.00.00.html","cp01.76.00.00.00.00.html"},
            {"cp01.78.00.00.00.00.html","cp01.75.00.00.00.00.html", "cp01.68.00.00.00.00.html","cp01.69.00.00.00.00.html","cp01.73.00.00.00.00.html","cp01.74.00.00.00.00.html"}};

    public static void main(String[] args) throws Exception {
        File file = new File(BASE_DIR_PATH);
        if(!file.exists())file.mkdir();
        //启动9条线程
        for (int i = 0; i < TYPE_URL.length; i++)
            startThread(getBufferedOut(String.format(BASE_FILE_PATH,i)),getBufferedOut(BASE_ERR_PATH),TYPE_URL[i],String.format("第%s条线程",i));

    }


    private static void running(BufferedWriter out, BufferedWriter outErr,String[] typeUrl) throws IOException {
        int index=0;
        for (String type : typeUrl) {
            for (int i = 1; i <=100 ; i++) {
                String page="";
                if(i>1){
                    page="pg"+i+"-";
                }
                String url=BASE_URL+page+type;
                URL detailUrl = new URL(url);
                Document doc = Jsoup.parse(detailUrl, 30000);
                Elements select = doc.select("a[name=itemlist-picture]");
                for (Element element : select) {
                    String sql="insert into book (id,book_pic,book_name,price,author,publisher,book_time,isbn,type,url) values";

                    List<String> params=new ArrayList<>();
                    String id = UUID.randomUUID().toString().replaceAll("-","");
                    params.add(id);

                    String detail = element.attr("href");
                    try {
                        Document parse = Jsoup.parse(new URL(detail), 50000);
                        if (detail.equals("http://product.dangdang.com/1439315588.html")){
                            System.out.println("ssss");
                        }
                        //书名
                        String bookName = parse.select(".name_info").get(0).select("h1").get(0).attr("title");
                        params.add(bookName);

                        //封面地址
                        String picAddress = parse.select(".pic").get(0).select("a").select("img").get(0).attr("src");
                        params.add(picAddress);


                        //价格
                        Elements e1 = parse.select("#original-price");
                        String price = e1.html();
                        int n = price.lastIndexOf(">");
                        price = price.substring(n+1);
                        params.add(price);


                        Elements select1 = parse.select(".messbox_info");
                        if(CollectionUtils.isEmpty(select1))continue;
                        Elements elements = select1.get(0).select(".t1");

                        //作者
                        List<String> author = elements.get(0).select("a").stream().map(x -> x.html()).collect(Collectors.toList());
                        params.add(StringUtils.join(author,","));

                        //出版社
                        List<String> publisher = elements.get(1).select("a").stream().map(x -> x.html()).collect(Collectors.toList());
                        params.add(StringUtils.join(publisher,","));

                        //出版时间
                        String time = elements.get(2).html();
                        params.add(time.substring(5,time.indexOf("&")));

                        //ISBN
                        String ISBN = parse.select("#detail_describe").get(0).child(0).child(4).html();
                        params.add(ISBN.substring(11));

                        //分类
                        List<String> typeList = parse.select("#detail-category-path").get(0)
                                .child(1).select("a").stream().map(x -> x.html()).collect(Collectors.toList());
                        params.add(StringUtils.join(typeList,","));

                        //url
                        params.add(detail);


                        sql+="('"+StringUtils.join(params,"','")+"');";
                        out.write(sql+"\r\n");

                        System.out.println(Thread.currentThread().getName() +"  第: "+(++index)+" 条");
                    } catch (Exception e) {
                        System.out.println("异常的url: "+ detail);
                        System.out.println(url);
                        e.printStackTrace();

                        outErr.write(detail+"\r\n");
                        outErr.write(e.getMessage()+"\r\n");
                        outErr.flush();

                    }

                }
                out.flush();

            }
        }
        out.close();
        outErr.close();
    }

    private static void startThread(BufferedWriter out, BufferedWriter outErr, String[] typeUrl,String threadName) {
        new Thread(()-> {
            try {
                running(out, outErr,typeUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        },threadName).start();
    }
    private static BufferedWriter getBufferedOut(String result1) throws IOException {
        File writeName = new File(result1); // 相对路径，如果没有则要建立一个新的output.txt文件
        writeName.createNewFile();
        FileWriter writer = new FileWriter(writeName, true);
        return new BufferedWriter(writer);
    }


}
