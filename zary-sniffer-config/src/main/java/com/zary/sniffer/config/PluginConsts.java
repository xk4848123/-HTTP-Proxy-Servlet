package com.zary.sniffer.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PluginConsts {
    /**
     * java生成的动态代理类的命名空间前缀，探针插桩时不会拦截该空间下的类
     */
    public static final String PKG_JAVA_SUN = "com.sun.";

    /**
     * 客户端参数名称：指纹参数(可能存在于params/cookies/headers中)
     */
    public static final String KEY_CLIENT_FINGERPRINT = "am_f_g_p_tag";
    /**
     * 客户端参数名称：会话标识(可能存在于params/cookies/headers中)
     */
    public static final String KEY_CLIENT_SESSION_ID = "am_s_e_s_tag";

    /**
     * 探针需要写脚本的响应类型contentType
     */
    public static final List<String> CONTENT_TYPE_SCRIPT = Arrays.asList("text/html");

    /**
     * 探针追加header时变量缓存
     */
    public static final String KEY_CACHE_HEADER_APPEND = "adm_key_header_append";

    /**
     * 客户端页面里探针输出的script标签的id(用于判断是否已经存在)
     */
    public static final String KEY_CLIENT_SCRIPT_ID = "am_s_p_t_js";

    /**
     * 探针追加cookie时变量缓存
     */
    public static final String KEY_CACHE_COOKIE_APPEND = "adm_key_cookie_append";

    /**
     * 探针需要写脚本时的标记名称
     */
    public static final String KEY_IS_NEED_WRITE_SCRIPT = "adm_key_is_need_write_script";

    /**
     * resultset线程数据的key前缀
     */
    public static final String KEY_RESULT_SET = "result_set_";

    /**
     * 探针当前线程已经写过脚本的标记(防止重复执行)
     */
    public static final String KEY_HAS_WRITE_SCRIPT = "adm_key_has_write_script";

    public static final HashSet<String> HTTP_STATIC_FILES = new HashSet<String>() {{
        add(".a11");
        add(".acp");
        add(".ai");
        add(".aif");
        add(".aifc");
        add(".aiff");
        add(".anv");
        add(".apk");
        add(".asa");
        add(".asf");
        add(".asp");
        add(".asx");
        add(".au");
        add(".avi");
        add(".awf");
        add(".biz");
        add(".bmp");
        add(".bot");
        add(".c4t");
        add(".c90");
        add(".cal");
        add(".cat");
        add(".cdf");
        add(".cdr");
        add(".cel");
        add(".cer");
        add(".cg4");
        add(".cgm");
        add(".cit");
        add(".class");
        add(".cml");
        add(".cmp");
        add(".cmx");
        add(".cot");
        add(".crl");
        add(".crt");
        add(".csi");
        add(".css");
        add(".cut");
        add(".dbf");
        add(".dbm");
        add(".dbx");
        add(".dcd");
        add(".dcx");
        add(".der");
        add(".dgn");
        add(".dib");
        add(".dll");
        add(".doc");
        add(".docx");
        add(".dot");
        add(".dotx");
        add(".drw");
        add(".dtd");
        add(".dwf");
        add(".dwg");
        add(".dxb");
        add(".dxf");
        add(".edn");
        add(".emf");
        add(".eml");
        add(".ent");
        add(".epi");
        add(".eps");
        add(".etd");
        add(".exe");
        add(".fax");
        add(".fdf");
        add(".fif");
        add(".fo");
        add(".frm");
        add(".g4");
        add(".gbr");
        add(".gif");
        add(".gl2");
        add(".gp4");
        add(".hgl");
        add(".hmr");
        add(".hpg");
        add(".hpl");
        add(".hqx");
        add(".hrf");
        add(".hta");
        add(".htc");
        add(".htt");
        add(".htx");
        add(".icb");
        add(".ico");
        add(".iff");
        add(".ig4");
        add(".igs");
        add(".iii");
        add(".img");
        add(".ins");
        add(".ipa");
        add(".isp");
        add(".IVF");
        add(".jfif");
        add(".jpe");
        add(".jpeg");
        add(".jpg");
        add(".js");
        add(".la1");
        add(".lar");
        add(".latex");
        add(".lavs");
        add(".lbm");
        add(".lmsff");
        add(".ls");
        add(".ltr");
        add(".m1v");
        add(".m2v");
        add(".m3u");
        add(".m4e");
        add(".mac");
        add(".man");
        add(".math");
        add(".mdb");
        add(".mfp");
        add(".mht");
        add(".mi");
        add(".mid");
        add(".midi");
        add(".mil");
        add(".mml");
        add(".mnd");
        add(".mns");
        add(".mocha");
        add(".movie");
        add(".mp1");
        add(".mp2");
        add(".mp2v");
        add(".mp3");
        add(".mp4");
        add(".mpa");
        add(".mpd");
        add(".mpe");
        add(".mpeg");
        add(".mpg");
        add(".mpga");
        add(".mpp");
        add(".mps");
        add(".mpt");
        add(".mpv");
        add(".mpv2");
        add(".mpw");
        add(".mpx");
        add(".mtx");
        add(".mxp");
        add(".net");
        add(".nrf");
        add(".nws");
        add(".odc");
        add(".out");
        add(".p10");
        add(".p12");
        add(".p7b");
        add(".p7c");
        add(".p7m");
        add(".p7r");
        add(".p7s");
        add(".pc5");
        add(".pci");
        add(".pcl");
        add(".pcx");
        add(".pdf");
        add(".pdx");
        add(".pfx");
        add(".pgl");
        add(".pic");
        add(".pko");
        add(".pl");
        add(".plg");
        add(".pls");
        add(".plt");
        add(".png");
        add(".pot");
        add(".ppa");
        add(".ppm");
        add(".pps");
        add(".ppt");
        add(".pptx");
        add(".pr");
        add(".prf");
        add(".prn");
        add(".prt");
        add(".ps");
        add(".ptn");
        add(".pwz");
        add(".r3t");
        add(".ra");
        add(".ram");
        add(".ras");
        add(".rat");
        add(".rdf");
        add(".rec");
        add(".red");
        add(".rgb");
        add(".rjs");
        add(".rjt");
        add(".rlc");
        add(".rle");
        add(".rm");
        add(".rmf");
        add(".rmi");
        add(".rmj");
        add(".rmm");
        add(".rmp");
        add(".rms");
        add(".rmvb");
        add(".rmx");
        add(".rnx");
        add(".rp");
        add(".rpm");
        add(".rsml");
        add(".rt");
        add(".rtf");
        add(".rv");
        add(".sam");
        add(".sat");
        add(".sdp");
        add(".sdw");
        add(".sis");
        add(".sisx");
        add(".sit");
        add(".slb");
        add(".sld");
        add(".slk");
        add(".smi");
        add(".smil");
        add(".smk");
        add(".snd");
        add(".sol");
        add(".sor");
        add(".spc");
        add(".spl");
        add(".spp");
        add(".ssm");
        add(".sst");
        add(".stl");
        add(".stm");
        add(".sty");
        add(".svg");
        add(".swf");
        add(".tdf");
        add(".tg4");
        add(".tga");
        add(".tif");
        add(".tiff");
        add(".tld");
        add(".top");
        add(".torrent");
        add(".tsd");
        add(".txt");
        add(".uin");
        add(".uls");
        add(".vcf");
        add(".vda");
        add(".vdx");
        add(".vml");
        add(".vpg");
        add(".vsd");
        add(".vss");
        add(".vst");
        add(".vsw");
        add(".vsx");
        add(".vtx");
        add(".vxml");
        add(".wav");
        add(".wax");
        add(".wb1");
        add(".wb2");
        add(".wb3");
        add(".wbmp");
        add(".wiz");
        add(".wk3");
        add(".wk4");
        add(".wkq");
        add(".wks");
        add(".wm");
        add(".wma");
        add(".wmd");
        add(".wmf");
        add(".wml");
        add(".wmv");
        add(".wmx");
        add(".wmz");
        add(".wp6");
        add(".wpd");
        add(".wpg");
        add(".wpl");
        add(".wq1");
        add(".wr1");
        add(".wri");
        add(".wrk");
        add(".ws");
        add(".ws2");
        add(".wsc");
        add(".wsdl");
        add(".wvx");
        add(".x_b");
        add(".x_t");
        add(".xap");
        add(".xdp");
        add(".xdr");
        add(".xfd");
        add(".xfdf");
        add(".xls");
        add(".xlsx");
        add(".xlw");
        add(".xml");
        add(".xpl");
        add(".xq");
        add(".xql");
        add(".xquery");
        add(".xsd");
        add(".xsl");
        add(".xslt");
        add(".xwd");
        add(".woff");
        add(".woff2");
        add(".eot");
        add(".ttf");
    }};
}