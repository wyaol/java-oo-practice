package com.twu.bean;

import com.twu.entity.NormalUser;

import java.util.ArrayList;

public class NormalUsers {
    private ArrayList<NormalUser> normalUsers;
    private Integer initMoney = 1000000;
    private Integer initVotes = 10;

    public NormalUsers() {
        this.normalUsers = new ArrayList<>();
    }

    public NormalUser addNormalUser(String name) {
        NormalUser normalUser = new NormalUser(normalUsers.size()+1, name, initMoney, initVotes);
        normalUsers.add(normalUser);
        return normalUser;
    }

    public Integer getInitMoney() {
        return initMoney;
    }

    public void setInitMoney(Integer initMoney) {
        this.initMoney = initMoney;
    }

    public Integer getInitVotes() {
        return initVotes;
    }

    public void setInitVotes(Integer initVotes) {
        this.initVotes = initVotes;
    }

    public NormalUser getNormalUserByID(Integer ID) throws Exception {
        for(NormalUser normalUser: this.normalUsers) {
            if (normalUser.getId().equals(ID)) return normalUser;
        }

        throw new Exception("该ID用户不存在");
    }
}
