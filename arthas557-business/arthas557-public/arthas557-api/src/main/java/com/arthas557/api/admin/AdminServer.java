package com.arthas557.api.admin;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("arthas557-admin")
public interface AdminServer {

}
