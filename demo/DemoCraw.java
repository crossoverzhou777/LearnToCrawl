
public class DemoCraw {
    public static void main(String[] args) {
        String url = "https://www.jd.com/allSort.aspx";
        String classpath = "cn.succy.geccospider";
        HttpRequest request = new HttpGetRequest(url);
        request.setCharset("gb2312");
        // 如果pipeline和htmlbean不在同一个包，classpath就要设置到他们的共同父包
        // 本引擎主要是把京东分类的页面手机板块给抓取过来封装成htmlbean
        GeccoEngine.create().classpath(classpath).start(request).interval(2000).run();
        // 本引擎是负责抓取每一个细目对应的页面的第一页的所有商品列表的数据，开启5个线程同时抓取，提升效率
        GeccoEngine.create().classpath(classpath).start(AllSortPipeline.cateRequests).thread(5)
                .interval(2000).run();
    }
}