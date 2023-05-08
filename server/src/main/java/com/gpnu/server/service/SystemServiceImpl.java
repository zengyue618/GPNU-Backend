package com.gpnu.server.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.gpnu.entity.system.PrivilegeType;
import com.gpnu.entity.system.SystemPrivilege;
import com.gpnu.entity.system.Team;
import com.gpnu.entity.system.User;
import com.gpnu.repository.system.SystemPrivilegeRepository;
import com.gpnu.repository.system.TeamRepository;
import com.gpnu.repository.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    UserRepository userRepository;
    @Resource
    TeamRepository teamRepository;
    @Resource
    SystemPrivilegeRepository systemPrivilegeRepository;
    @Autowired
    RangerService rangerService;

    @PostConstruct
    public void init() {
        //创建超级用户和超级组
        Team admin = teamRepository.findOneByName("Admin");
        if (admin == null) {
            Team team = new Team();
            team.setName("Admin");
            team.setCreateTime(Long.parseLong(DateUtil.format(new Date(), "yyMMddHHmm")));
            team.setTrash(false);
            team.setAdmin("imooc");
            teamRepository.save(team);
        }
        User imooc = userRepository.findOneByName("imooc");
        if (imooc == null) {
            User user = new User();
            user.setTrash(false);
            user.setMail("imooc@imooc.com");
            user.setName("imooc");
            user.setPhone("13888888888");
            user.setPwd(SecureUtil.md5("imooc@123"));
            user.setTeam("Admin");
            user.setCreateTime(Long.parseLong(DateUtil.format(new Date(), "yyMMddHHmm")));
            userRepository.save(user);
        }
        //为超级用户添加权限
        SystemPrivilege privilege = systemPrivilegeRepository.findOneByTeamAndPrivilegeType("Admin", PrivilegeType.SYSTEM);
        if (privilege == null) {
            SystemPrivilege systemPrivilege = new SystemPrivilege();
            systemPrivilege.setTrash(false);
            systemPrivilege.setTeam("Admin");
            systemPrivilege.setPrivilegeType(PrivilegeType.SYSTEM);
            systemPrivilege.setCreateTime(Long.parseLong(DateUtil.format(new Date(), "yyMMddHHmm")));
            systemPrivilegeRepository.save(systemPrivilege);
        }

    }

    @Override
    public Page<User> listUsers(int page, int size, String sort, Direction direction) {
        User user = new User();
        user.setTrash(false);

        return userRepository.findAll(Example.of(user), PageRequest.of(page, size,
                Sort.by(direction == null ? Direction.DESC : direction, ObjectUtil.isNull(sort) ? "id" : sort)));

    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.getOne(id);
        user.setTrash(true);
        userRepository.save(user);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findOneByName(name);
    }

    @Override
    public Page<Team> listTeams(int page, int size, String sort, Direction direction) {
        Team team = new Team();
        team.setTrash(false);

        return teamRepository.findAll(Example.of(team), PageRequest.of(page, size,
                Sort.by(direction == null ? Direction.DESC : direction, ObjectUtil.isNull(sort) ? "id" : sort)));

    }

    @Override
    public List<String> listTeamNames() {
        Team team = new Team();
        team.setTrash(false);
        return teamRepository.findAll(Example.of(team)).stream().map(Team::getName).collect(Collectors.toList());
    }

    @Override
    public void addTeam(Team team) {
        //todo add user to ranger and os
//        if (!team.getName().equals("hadoop")) {
//            rangerService.addRangerUser(team.getName(), team.getName(), team.getName(), SecureUtil.md5(team.getName()),
//                    Arrays.asList("ROLE_USER"));
//        }
        teamRepository.save(team);
    }

    @Override
    public void updateTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(long id) {
        Team one = teamRepository.getOne(id);
        //todo
        rangerService.removeRangerUser(one.getName());
        teamRepository.delete(one);
    }

    @Override
    public Team findTeamById(long id) {
        return teamRepository.getOne(id);
    }

    @Override
    public Team findTeamByName(String name) {
        return teamRepository.findOneByName(name);
    }

    @Override
    public SystemPrivilege findSystemPrivilege(String team, PrivilegeType privilegeType) {
        return systemPrivilegeRepository.findOneByTeamAndPrivilegeType(team, privilegeType);
    }

    @Override
    public void addSystemPrivilege(SystemPrivilege systemPrivilege) {
        systemPrivilegeRepository.save(systemPrivilege);
    }

    @Override
    public void delSystemPrivilege(long id) {
        systemPrivilegeRepository.deleteById(id);
    }

    @Override
    public Page<SystemPrivilege> listSystemPrivileges(int page, int size, String sort,
                                                      Direction direction) {
        SystemPrivilege systemPrivilege = new SystemPrivilege();
        systemPrivilege.setTrash(false);

        return systemPrivilegeRepository.findAll(Example.of(systemPrivilege), PageRequest.of(page, size,
                Sort.by(direction == null ? Direction.DESC : direction, ObjectUtil.isNull(sort) ? "id" : sort)));
    }
}
