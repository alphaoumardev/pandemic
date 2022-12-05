package com.alpha.pandemic.controllers;

import com.alpha.pandemic.models.Image;
import com.alpha.pandemic.services.ImageService;
import com.alpha.pandemic.services.OssService;
import com.alpha.pandemic.structor.form.ImageForm;
import com.alpha.pandemic.utils.response.FinalResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/oss")
public class AliOssController
{
    private final OssService ossService;
    private final ImageService imageService;

    @PostMapping("/upload")
    @ApiOperation(value="Uploading the avatar")
    @PreAuthorize("hasAuthority('oss:update')")
    public FinalResult upload(MultipartFile file) throws IOException
    {
        String upload = ossService.upload(file);
        return FinalResult.ok().data(upload);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value="Delete the avatar")
    @PreAuthorize("hasAuthority('oss:delete')")
    public FinalResult deleteAvatar(String file)
    {
        try
        {
            String[] split = file.split(".com/");
            ossService.deleteFile(split[1]);
            return FinalResult.ok().message("You have removed the image from AliOss");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return FinalResult.error();
        }
    }

    @PostMapping("/list")
    @ApiOperation(value="Delete the avatar")
    @PreAuthorize("hasAuthority('oss:list')")
    public FinalResult listPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "5") Integer size,
                                @RequestBody ImageForm imageForm)
    {
        Page<Image> imagePage = imageService.listPage(page, size,imageForm);
        return FinalResult.ok().data(imagePage);
    }
}
