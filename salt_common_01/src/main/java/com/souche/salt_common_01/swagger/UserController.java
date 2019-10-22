package com.souche.salt_common_01.swagger;

import com.souche.salt_common_01.entity.UserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lantu/user")
@Api(value="用户controller",description="用户操作",tags={"用户操作接口"})
public class UserController {
    @GetMapping("/get")
    @ApiOperation(value = "获取用户接口",notes = "获取用户接口notes",response = String.class)
    public String get( @ApiParam(name = "userName",value = "用户名",required = true) @RequestParam("userName")
                                   String userName) {
        return "返回值："+userName;
    }

    @PostMapping("/post")
    @ApiOperation(value = "获取用户接口Post",notes = "获取用户接口notes Post",response = UserForm.class)
    public UserForm post( @RequestBody
                              @ApiParam(name="用户对象",value="传入json格式",required=true)
                                      UserForm userForm)
    { return userForm;
    }

}
