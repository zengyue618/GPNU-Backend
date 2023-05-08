package com.gpnu.server.controller;

import com.gpnu.entity.privilege.ResourcePrivilege;
import com.gpnu.server.BaseController;
import com.gpnu.server.jwt.ContextUtil;
import com.gpnu.server.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gpnu/ranger")
@CrossOrigin
public class PrivilegeController extends BaseController {

    @Autowired
    PrivilegeService privilegeService;

    @GetMapping(value = "privileges")
    public Object getRangerPrivileges(
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") int pageIndex,
            @RequestParam(name = "pageSize", required = true, defaultValue = "50") int pageSize) {

        Page<ResourcePrivilege> resourcePrivileges =
                privilegeService.listResourcePrivileges(ContextUtil.getCurrentUser().getTeam(), pageIndex-1, pageSize, null, null);
        Map<String,Object> pages = new HashMap<>();
        pages.put("pages",resourcePrivileges.getContent());
        pages.put("pageIndex",pageIndex);
        pages.put("pageSize",pageSize);
        pages.put("pageCount",resourcePrivileges.getTotalPages());
        return getResult(pages);
    }

    @PostMapping(value = "privilege")
    public Object addRangerPrivilege(@RequestBody ResourcePrivilege resourcePrivilege) {
        privilegeService.addPrivilege(resourcePrivilege);
        return getResult(true);
    }

    @PutMapping(value = "privilege")
    public Object updateRangerPrivilege(@RequestBody ResourcePrivilege resourcePrivilege) {
        privilegeService.updatePrivilege(resourcePrivilege);
        return getResult(true);
    }

    @DeleteMapping(value = "privilege")
    public Object deleteRangerPrivilege(@RequestParam long id) {
        privilegeService.delPrivilege(id);
        return getResult(true);
    }

}
