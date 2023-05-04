package org.xmgdtc.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public abstract class BaseController {

    @Autowired
    protected HttpServletRequest httpServletRequest;
    @Autowired
    protected HttpServletResponse httpServletResponse;


    protected ResponseEntity<byte[]> buildResponse(String fileName, byte[] fileByte) {

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("attachment", URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                .body(fileByte);
    }


}
