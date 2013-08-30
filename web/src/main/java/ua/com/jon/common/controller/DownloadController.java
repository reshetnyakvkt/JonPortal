package ua.com.jon.common.controller;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.jon.utils.NetUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/2/13
 */
@Controller
public class DownloadController {
    private static Logger log = Logger.getLogger(DownloadController.class.getName());

    @Value("${files.folder}")
    private String filesBaseUrl;



    private Map<String, String> files;

    public DownloadController() {
        files = new HashMap<String, String>();
        files.put("jdk-7u17-windows-i586", "/install/jdk-7u25-windows-i586.exe");
        files.put("jdk-7u17-windows-x64", "/install/jdk-7u25-windows-x64.exe");
        files.put("ideaIU-12_1_4", "/install/ideaIU-12.1.4.exe");
//        files.put("ojdbc14", "/libs/Oracle JDBC Driver/ojdbc14.jar");
        files.put("OracleXE", "/install/OracleXE.exe");
        files.put("hibernate_reference", "/docs/hibernate_reference.pdf");
        files.put("spring-framework-reference", "/docs/spring-framework-reference.pdf");
        files.put("apache-tomcat-7_0_41", "/install/apache-tomcat-7.0.41.zip");
        files.put("apache-maven-2_2_1-bin", "/install/apache-maven-2.2.1-bin.zip");
    }

    @RequestMapping("/download.html")
    public String mainPage(ModelMap modelMap) {
        log.info("download page");
        modelMap.put("item", "item5");
        modelMap.put("ftpAddress", "ftp://" + NetUtil.distinguishLocationAndGetIP());
        return "download";
    }

/*
    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        log.info("File download request: " + fileName);
        return new FileSystemResource(getFileFor(fileName));
    }
*/

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        try {
            File file;
            InputStream is;
            try {
                String pathName = getPathName(fileName);
                file = getFile(pathName);
                is = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                log.error(e);
                response.getWriter().print(e.getMessage());
                return;
            }

            // copy it to response's OutputStream
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName().replace(" ", "_"));
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            log.info("Error writing file to output stream. Filename was '" + fileName + "'");
            throw new RuntimeException("IOError writing file to output stream");
        }

    }

    private String getPathName(String fileName) throws FileNotFoundException {
        String pathname = files.get(fileName);
        if (pathname == null) {
            throw new FileNotFoundException("File not found " + fileName);
        }
        return pathname;
    }

    private File getFile(String pathname) throws FileNotFoundException {
        File file = new File(filesBaseUrl + pathname);
        if (!file.exists()) {
            throw new FileNotFoundException("File found but not exists, contact to admin: " + filesBaseUrl + pathname);
        }
        return file;
    }
}
