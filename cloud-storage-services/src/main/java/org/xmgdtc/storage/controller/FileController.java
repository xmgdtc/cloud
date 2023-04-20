package org.xmgdtc.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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


    @GetMapping("/create")
    public FileView createAcct(@NotNull @RequestParam("bucket") String bucket, @NotNull @RequestParam("file") MultipartFile file) {
        return fileService.saveFile(bucket, file);
    }

}
