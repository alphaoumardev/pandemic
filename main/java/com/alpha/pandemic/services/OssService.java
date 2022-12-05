package com.alpha.pandemic.services;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.alpha.pandemic.models.AliOss;
import com.alpha.pandemic.models.Image;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.UUID;

@Service
public class OssService implements InitializingBean
{
    private final AliOss alioss;
    private final ImageService imageService;

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    @Autowired
    public OssService(AliOss alioss, ImageService imageService) {this.alioss = alioss; this.imageService = imageService;}

    @Override
    public void afterPropertiesSet() throws Exception
    {
        endpoint = alioss.getEndpoint();
        bucketName = alioss.getBucketName();
        accessKeyId = alioss.getAccessKeyId();
        accessKeySecret = alioss.getAccessKeySecret();
    }
//    Create a new bucket (space)
    public void createBucket()
    {
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (oss.doesBucketExist(bucketName))
        {
            throw new RuntimeException(bucketName + "This bucket already exists");
        }
        oss.createBucket(bucketName);
        oss.shutdown();
    }

    public String upload(MultipartFile file) throws IOException
    {
        String url=null;
        try
        {
            OSS oss= new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
            if(!oss.doesBucketExist(bucketName))
            {
                oss.createBucket(bucketName);
                oss.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            InputStream input =file.getInputStream();

            String datePath= new DateTime().toString("yyyy-mm-dd");
            String originalName= file.getOriginalFilename();
            String fileName= UUID.randomUUID().toString().replaceAll("-","");
            assert originalName != null;
            String fileType=originalName.substring(originalName.indexOf("."));
            String newName = fileName + fileType;

            //  avatar/2020/10/10/a98059d4633c4b55b31093c997cf8038.png
            fileName = datePath + "/" + newName;

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentType(getcontentType(fileType));

            oss.putObject(bucketName, fileName, input, objectMetadata);
            oss.shutdown();
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            url = oss.generatePresignedUrl(bucketName, fileName, expiration).toString();
            url = url.substring(0, url.indexOf("?"));
//            url = "https://" + bucketName + "." + endPoint + "/" + fileName;
            BufferedImage read = ImageIO.read(file.getInputStream());
            Image image = new Image();
            image.setName(fileName);
            image.setPath(url);
            image.setMediaType(fileType);
            image.setSize(file.getSize());
            image.setWidth(read.getWidth());
            image.setHeight(read.getHeight());
            imageService.save(image);
            return url;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return url;
    }

//    To download the file
    public void downLoad(String fileName) throws IOException
    {
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        OSSObject ossObject = oss.getObject(bucketName, fileName);
        InputStream content = ossObject.getObjectContent();
        if (content != null)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true)
            {
                String line = reader.readLine();
                if (line == null) {break;}
            }
            content.close();
        }
        oss.shutdown();
    }
//    To delete the file/image
    public FinalResult deleteFile(String fileName)
    {
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // delete the image
        oss.deleteObject(bucketName, fileName);
        oss.shutdown();
        LambdaQueryWrapper<Image> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Image::getName, fileName);
        boolean res = imageService.remove(wrapper);
        if(res)
        {
            return FinalResult.ok().message("You have successfully remove the image");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public void listFile()
    {
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectListing objectListing = oss.listObjects(bucketName);
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries())
        {
            System.out.println(" - " + objectSummary.getKey() + "  "+"(size = " + objectSummary.getSize() + ")");
        }
        oss.shutdown();
    }
    //This function is to dispalay the image/file online

    public static String getcontentType( String FilenameExtension)
    {
        if (FilenameExtension.equalsIgnoreCase(".bmp"))
        {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif"))
        {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png"))
        {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html"))
        {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt"))
        {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd"))
        {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt"))
        {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc"))
        {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml"))
        {
            return "text/xml";
        }
        return "image/jpg";
    }
}
