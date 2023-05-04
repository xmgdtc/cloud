package org.xmgdtc.storage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xmgdtc.api.view.oss.FileView;

import javax.validation.constraints.NotNull;

public interface IFileController {
    @RequestMapping(value = "/create", method = {RequestMethod.GET, RequestMethod.POST})
    FileView createFile(@NotNull @RequestParam("bucket") String bucket, @NotNull @RequestParam("file") MultipartFile file);

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    FileView updateFile(@NotNull @RequestParam("id") String id, @NotNull @RequestParam("file") MultipartFile file);

    @RequestMapping(value = "/getFileByte", method = {RequestMethod.GET, RequestMethod.POST})
    ResponseEntity<byte[]> getFileByte(@NotNull @RequestParam("id") String id);

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    FileView deleteFile(@NotNull @RequestParam("id") String id);
}
