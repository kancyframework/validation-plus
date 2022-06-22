package com.kancy.validationplus.demo.controller;

import com.kancy.validationplus.demo.entity.GroupA;
import com.kancy.validationplus.demo.entity.Request;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Assert;
import javax.validation.constraints.NotNullOrBlank;
import javax.validation.constraints.UserName;

/**
 * TestController
 *
 * @author huangchengkang
 * @date 2022/6/22 1:06
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController implements ExceptionHandlerController{

    @GetMapping("/getData")
    public R<Object> test(){
        return R.success(new Request());
    }

    @PostMapping("/test1")
    public R<Object> test(@Validated @RequestBody Request request,
                          @RequestParam @Assert("name != null") String name){
        return R.success();
    }

    @PostMapping("/test2")
    public R<Object> test(@RequestParam @Assert("name != null") String name){
        return R.success();
    }

    @PostMapping("/test3")
    public R<Object> test3(@RequestParam(required = false) @UserName String name){
        return R.success();
    }

    @PostMapping("/test4")
    public R<Object> test4(@RequestParam @Assert("#notin(name, 'A,B,9') and #start(name, 'a') and #end(name, 'b')") String name){
        return R.success();
    }

    @PostMapping("/test5")
    public R<Object> test5(@RequestParam("name") @NotNullOrBlank(message = "名称不能为空") String name){
        return R.success();
    }

    @PostMapping("/test6")
    public R<Object> test6(@Validated(GroupA.class) @RequestBody Request request){
        return R.success();
    }

    @PostMapping("/test7")
    public R<Object> test7(@Validated @RequestBody Request request){
        return R.success();
    }
}
