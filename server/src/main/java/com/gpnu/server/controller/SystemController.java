package com.gpnu.server.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.gpnu.core.exception.ErrorCodes;
import com.gpnu.entity.system.*;
import com.gpnu.server.BaseController;
import com.gpnu.server.jwt.ContextUtil;
import com.gpnu.server.jwt.JwtManager;
import com.gpnu.server.jwt.LoginRequired;
import com.gpnu.server.jwt.PrivilegeCheck;
import com.gpnu.server.log.OperationObj;
import com.gpnu.server.log.OperationRecord;
import com.gpnu.server.service.OperationLogService;
import com.gpnu.server.service.SystemService;
import org.apache.parquet.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gpnu/system")
@CrossOrigin
public class SystemController extends BaseController {

    @Autowired
    OperationLogService operationLogService;

    @Autowired
    SystemService systemService;


    @PostMapping(value = "login")
    public Object login(@RequestParam String username,
                        @RequestParam String password) {
        User userByName = systemService.findUserByName(username);
        if (userByName == null) {
            return getError(ErrorCodes.ERROR_USER_NOT_EXISTS, "user not exists");
        }
        if (userByName.getPwd().equals(SecureUtil.md5(password))) {
            String jwt = JwtManager.createJwt(60 * 60 * 1000l, userByName);
            Map<String, String> token = new HashMap<>();
            token.put("token", jwt);
            return getResult(token);
        } else {
            return getError(ErrorCodes.ERROR_PASSWORD, "password error");
        }
    }

    @GetMapping(value = "/log")
    @LoginRequired
    @OperationRecord("列出操作日志")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object getOperationLogs(
            @RequestParam(name = "pageIndex", defaultValue = "1") int pageIndex,
            @RequestParam(name = "pageSize", defaultValue = "50") int pageSize) {
        Page<OperationLog> operationLogs = operationLogService.listOperationLogOrderByTime(pageIndex - 1, pageSize);
        Map<String, Object> pages = new HashMap<>();
        pages.put("pages", operationLogs.getContent());
        pages.put("pageIndex", pageIndex);
        pages.put("pageSize", pageSize);
        pages.put("pageCount", operationLogs.getTotalPages());
        return getResult(pages);
    }

    @GetMapping(value = "privileges")
    @LoginRequired
    @OperationRecord("列出系统权限")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object getSystemPrivileges(
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") int pageIndex,
            @RequestParam(name = "pageSize", required = true, defaultValue = "50") int pageSize) {

        Page<SystemPrivilege> systemPrivileges =
                systemService.listSystemPrivileges(pageIndex-1, pageSize, null, null);
        Map<String, Object> pages = new HashMap<>();
        pages.put("pages", systemPrivileges.getContent());
        pages.put("pageIndex", pageIndex);
        pages.put("pageSize", pageSize);
        pages.put("pageCount", systemPrivileges.getTotalPages());
        return getResult(pages);

    }

    @PostMapping(value = "privilege")
    @LoginRequired
    @OperationRecord("添加系统权限")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object addSystemPrivilege(@RequestBody @OperationObj SystemPrivilege systemPrivilege) {
        systemPrivilege.setTrash(false);
        systemPrivilege.setCreateTime(Long.parseLong(DateUtil.format(new Date(), "yyMMddHHmm")));
        systemService.addSystemPrivilege(systemPrivilege);
        return getResult(true);
    }

    @DeleteMapping(value = "privilege")
    @LoginRequired
    @OperationRecord("删除系统权限")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object deleteSystemPrivilege(@RequestParam @OperationObj long id) {
        systemService.delSystemPrivilege(id);
        return getResult(true);
    }

    @GetMapping(value = "/users")
    @LoginRequired
    @OperationRecord("列出用户")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object getUsers(
            @RequestParam(name = "pageIndex", defaultValue = "1") int pageIndex,
            @RequestParam(name = "pageSize", defaultValue = "50") int pageSize) {
        Page<User> users = systemService.listUsers(pageIndex - 1, pageSize, null, null);
        Map<String, Object> pages = new HashMap<>();
        pages.put("pages", users.getContent());
        pages.put("pageIndex", pageIndex);
        pages.put("pageSize", pageSize);
        pages.put("pageCount", users.getTotalPages());
        return getResult(pages);
    }

    @PostMapping(value = "user")
    @LoginRequired
    @OperationRecord("添加用户")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object addUser(@RequestBody @OperationObj User user) {
        String pwd = user.getPwd();
        user.setPwd(SecureUtil.md5(pwd));
        user.setTrash(false);
        user.setCreateTime(Long.parseLong(DateUtil.format(new Date(), "yyMMddHHmm")));
        systemService.addUser(user);
        return getResult(true);
    }

    @PutMapping(value = "user")
    @LoginRequired
    @OperationRecord("更新用户")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object updateUser(@RequestBody @OperationObj User user) {
        String pwd = user.getPwd();
        if (!Strings.isNullOrEmpty(pwd)) {
            user.setPwd(SecureUtil.md5(pwd));
        }
        systemService.updateUser(user);
        return getResult(true);
    }

    @DeleteMapping(value = "user")
    @LoginRequired
    @OperationRecord("删除用户")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object deleteUser(@RequestParam @OperationObj long id) {
        systemService.deleteUser(id);
        return getResult(true);
    }

    @GetMapping(value = "/teams")
    @LoginRequired
    @OperationRecord("列出团队")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object getTeams(
            @RequestParam(name = "pageIndex", defaultValue = "1") int pageIndex,
            @RequestParam(name = "pageSize", defaultValue = "50") int pageSize) {
        Page<Team> teams = systemService.listTeams(pageIndex - 1, pageSize, null, null);
        Map<String, Object> pages = new HashMap<>();
        pages.put("pages", teams.getContent());
        pages.put("pageIndex", pageIndex);
        pages.put("pageSize", pageSize);
        pages.put("pageCount", teams.getTotalPages());
        return getResult(pages);
    }

    @GetMapping(value = "/team/name")
    @LoginRequired
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object getTeamNames() {
        List<String> teams = systemService.listTeamNames();
        return getResult(teams);
    }

    @PostMapping(value = "team")
    @LoginRequired
    @OperationRecord("添加团队")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object addTeam(@RequestBody @OperationObj Team team) {
        System.out.println(ContextUtil.getCurrentUser());
        team.setTrash(false);
        team.setCreateTime(Long.parseLong(DateUtil.format(new Date(), "yyMMddHHmm")));
        systemService.addTeam(team);
        return getResult(true);
    }

    @PutMapping(value = "team")
    @LoginRequired
    @OperationRecord("更新团队")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object updateTeam(@RequestBody @OperationObj Team team) {
        systemService.updateTeam(team);
        return getResult(true);
    }

    @DeleteMapping(value = "team")
    @LoginRequired
    @OperationRecord("删除团队")
    @PrivilegeCheck(privilegeType = PrivilegeType.SYSTEM)
    public Object deleteTeam(@RequestParam @OperationObj long id) {
        systemService.deleteTeam(id);
        return getResult(true);
    }
}
