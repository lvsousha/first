package cn.zcl.action.ftl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {

    public Template getTemplate(String name) {
        try {
            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(this.getClass(), "/template");
            Template temp = cfg.getTemplate(name);
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 控制台输出
     *
     * @param name
     * @param root
     */
    public void print(String name, Map<String, Object> root) {
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = this.getTemplate(name);
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出Java文件
     *
     * @param templateName
     * @param root
     * @param outFileName
     */
    public void printToJava(String templateName, Map<String, Object> root, String outFileName) {
        FileWriter out = null;
        try {
        	File outfile = new File("src/main/java/com/zcl/model/" + outFileName+".java");
            out = new FileWriter(outfile);
            Template temp = this.getTemplate(templateName);
            temp.process(root, out);
    		System.out.println("成功生成"+outFileName+".java");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输出Xml文件
     *
     * @param templateName
     * @param root
     * @param outFileName
     */
    public void printToXml(String templateName, Map<String, Object> root, String outFileName) {
        FileWriter out = null;
        try {
        	File outfile = new File("src/main/resources/mybatis/zcl/" + outFileName+".xml");
            out = new FileWriter(outfile);
            Template temp = this.getTemplate(templateName);
            temp.process(root, out);
    		System.out.println("成功生成"+outFileName+".xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输出Xml文件
     *
     * @param templateName
     * @param root
     * @param outFileName
     */
    public void printToInterface(String templateName, Map<String, Object> root, String outFileName) {
        FileWriter out = null;
        try {
        	File outfile = new File("src/main/java/com/zcl/dao/" + outFileName+"Dao.java");
            out = new FileWriter(outfile);
            Template temp = this.getTemplate(templateName);
            temp.process(root, out);
    		System.out.println("成功生成"+outFileName+"Dao.java");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printToSql(String templateName, Map<String, Object> root, String outFileName) {
        FileWriter out = null;
        try {
        	File outfile = new File("src/main/resources/schema/" + "create-schema.sql");
            out = new FileWriter(outfile);
            Template temp = this.getTemplate(templateName);
            temp.process(root, out);
    		System.out.println("成功生成create-schema.sql");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
