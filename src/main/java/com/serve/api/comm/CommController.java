package com.serve.api.comm;

import com.serve.api.comm.model.Result;
import com.serve.api.comm.service.CommService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/comm/")
@RestController
public class CommController {
    @Autowired
    private CommService commService;

    @ApiOperation(value = "基础数据初始化", notes = "")
    @PostMapping(value = {"vt/init"})
    public synchronized Result initData() {
        commService.initOririnData();
        return Result.okResult(null);
    }
}
