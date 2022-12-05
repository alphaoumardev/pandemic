package com.alpha.pandemic.structor.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public class AddressUtil
{
    public static String getCityInfo(String ip)
    {
        //db
        String dbPath = Objects.requireNonNull(AddressUtil.class.getResource("/ip2region/ip2region.db")).getPath();
        File file = new File(dbPath);

        if (!file.exists())
        {
            log.info("The file doesn't exist");
            String tmpDir = System.getProperties().getProperty("java.io.tmpdir");
            dbPath = tmpDir + File.separator + "ip2region.db";
            log.info("The file current directory:{}", dbPath);
            file = new File(dbPath);
            if (!file.exists() || (System.currentTimeMillis() - file.lastModified() > 86400000L))
            {
                log.info("The file doesn't exist or timeout to get it...");
                try
                {
                    InputStream inputStream = new ClassPathResource("ip2region/ip2region.db").getInputStream();
                    IOUtils.copy(inputStream, new FileOutputStream(file));
                }
                catch (IOException exception)
                {
                    exception.printStackTrace();
                }
            }
        }

        //查询算法
        //B-tree
        try
        {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);
            //define the method
            Method method;
            method = searcher.getClass().getMethod("btreeSearch", String.class);
            DataBlock dataBlock;
            if (!Util.isIpAddress(ip))
            {
                log.error("Error: Invalid ip address");
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            return dataBlock.getRegion();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
