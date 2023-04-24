package org.xmgdtc.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmgdtc.api.view.oss.FileView;
import org.xmgdtc.common.controller.BaseController;
import org.xmgdtc.storage.service.IFileSVC;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/oss")
public class FileController extends BaseController {

    @Autowired
    private IFileSVC fileService;


    @RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    public FileView createFile(@NotNull @RequestParam("bucket") String bucket, @NotNull @RequestParam("file") MultipartFile file) {
        return fileService.saveFile(bucket, file);
    }


    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public FileView deleteFile(@NotNull @RequestParam("id") String id) {
        return fileService.deleteFile(id);
    }
}
