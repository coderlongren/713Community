package com.community.web.service;

import com.community.web.bean.User;

import java.io.IOException;
import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/26 11:06.
 */
public interface TeamService {

    List<User> listTeam() throws IOException;
}
