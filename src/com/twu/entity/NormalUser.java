package com.twu.entity;

import com.twu.exceptions.VoteNumberNotEnoughException;
import org.omg.PortableInterceptor.INACTIVE;

public class NormalUser {
    private Integer id;
    private String name;
    private Integer money;
    private Integer votes;

    public NormalUser(Integer id, String name, Integer money, Integer votes) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public void vote(Integer votesNumber) throws VoteNumberNotEnoughException {
        if (this.votes - votesNumber < 0) throw new VoteNumberNotEnoughException();
        this.votes -= votesNumber;
    }

    public void buyHotSearch(Integer money) throws Exception {
        if (this.money - money < 0) throw new Exception("您的金钱不足， 您当前的金钱为" + this.money);
        this.money -= money;
    }

    public void rollBackBuyHotSearch(Integer money) {
        this.money += money;
    }
}
