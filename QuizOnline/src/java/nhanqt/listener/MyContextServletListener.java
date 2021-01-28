/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author pc
 */
public class MyContextServletListener implements ServletContextListener {

    private final String PATH = "/WEB-INF/fileController.txt";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String path = context.getRealPath("/");
        Map<String,String> siteMap = readFile(context, path + PATH);
        context.setAttribute("SITE_MAP", siteMap);
    }

    private Map<String, String> readFile(ServletContext context, String filePath) {
        Map<String, String> siteMap = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            while (br.ready()) {
                String data = br.readLine();//read line
                String[] getStringFile = data.split("=");

                if (getStringFile.length == 2) {
                    if (siteMap == null) {
                        siteMap = new HashMap<>();
                    }

                    String key = getStringFile[0];
                    String value = getStringFile[1];
                    siteMap.put(key, value);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(br != null){
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return siteMap;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
