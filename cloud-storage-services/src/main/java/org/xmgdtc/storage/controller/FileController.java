package org.xmgdtc.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xmgdtc.api.dto.file.FileDTO;
import org.xmgdtc.api.view.oss.FileView;
import org.xmgdtc.common.controller.BaseController;
import org.xmgdtc.storage.service.IFileSVC;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/oss")
public class FileController extends BaseController implements IFileController {

    @Autowired
    private IFileSVC fileService;


    @Override
    @RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    public FileView createFile(@NotNull @RequestParam("bucket") String bucket, @NotNull @RequestParam("file") MultipartFile file) {
        return fileService.saveFile(bucket, file);
    }

    @Override
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public FileView updateFile(@NotNull @RequestParam("id") String id, @NotNull @RequestParam("file") MultipartFile file) {
        return fileService.updateFile(id, file);
    }

    @Override
    @RequestMapping(value = "/getFileByte", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<byte[]> getFileByte(@NotNull @RequestParam("id") String id) {
        FileDTO fileDTO = fileService.getFileBytes(id);

        return super.buildResponse(fileDTO.getName(), fileDTO.getOutputStream().toByteArray());

    }

    @Override
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public FileView deleteFile(@NotNull @RequestParam("id") String id) {
        return fileService.deleteFile(id);
    }
}
