package com.twu;

import com.twu.bean.HotSearches;
import com.twu.bean.NormalUsers;
import com.twu.bean.Rank;
import com.twu.entity.HotSearch;
import com.twu.entity.NormalUser;
import com.twu.services.RankHotSearchService;

import java.util.*;

public class RankHotSearch {
    private HotSearches hotSearches = new HotSearches();
    private NormalUsers normalUsers = new NormalUsers();
    private NormalUser curUser = normalUsers.addNormalUser("test");
    private Rank rank = new Rank(hotSearches, new HashMap<>());
    private Boolean isAdmin = false;

    void run() {
        initData();

        Scanner scan = new Scanner(System.in);
        while(true) {
            showMenu(isAdmin, curUser);
            System.out.println("\n");
            if (scan.hasNext()) {
                String str1 = scan.next();
                try {
                    switch (str1) {
                        case "1":
                            login();
                            break;
                        case "2":
                            adminLogin();
                            break;
                        case "3":
                            logout();
                            break;
                        case "4":
                            showHotSearchList();
                            break;
                        case "5":
                            addHotSearch();
                            break;
                        case "6":
                            addSuperHotSearch();
                            break;
                        case "7":
                            vote();
                            break;
                        case "8":
                            buyHotSearch();
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\n");
        }

    }

    void showMenu(Boolean isAdmin, NormalUser normalUser) {
        List<String> chooses = new ArrayList<String>(Arrays.asList(
                "0",
                "1.登录",
                "2.管理员登录",
                "3.退出",
                "4.查看热搜排行",
                "5.添加热搜",
                "6.添加超级热搜",
                "7.给热搜事件投票",
                "8.购买热搜"
        ));
        List<String> normalUserChooses = new ArrayList<String>(Arrays.asList(chooses.get(4), chooses.get(5), chooses.get(7), chooses.get(8)));
        List<String> adminChooses = new ArrayList<String>(Arrays.asList(chooses.get(4), chooses.get(5), chooses.get(6)));
        List<String> loggedChooses = new ArrayList<String>(Arrays.asList(chooses.get(3)));
        List<String> notLoggedChooses = new ArrayList<String>(Arrays.asList(chooses.get(1), chooses.get(2)));
        System.out.println("请输入编号");
        if (isAnyOneLogin()) {
            loggedChooses.forEach(System.out::println);
            if (isAdmin) {
                adminChooses.forEach(System.out::println);
            } else {
                normalUserChooses.forEach(System.out::println);
            }
        } else {
            notLoggedChooses.forEach(System.out::println);
        }
    }

    void showHotSearchList() {
        System.out.println("热搜排行版");

//        ArrayList<HotSearch>  hotSearches1= hotSearches.getHotSearches();
//        hotSearches1.forEach(item -> {
//            System.out.println(hotSearches1.indexOf(item) + " " + item.getDesc() + " " + item.getHotValue());
//        });
        RankHotSearchService.printRank(hotSearches);

        System.out.println(".........");
    }

    void initData() {
        hotSearches.addHotSearchTest("官方通报男子吵架称我爸是副县长", false, 20);
        hotSearches.addHotSearchTest("特朗普称愿自掏腰包胜选", false, 19);
        hotSearches.addHotSearchTest("素媛案罪犯12月出狱", false, 18);
        hotSearches.addHotSearchTest("澳情报人员突击搜查中国记者住所", false, 17);
        hotSearches.addHotSearchTest("乘风破浪的姐姐总导演秒删", false, 16);
        hotSearches.addHotSearchTest("第14届亚洲电影大奖公布入围名单", false, 15);
        hotSearches.addHotSearchTest("苹果秋季发布会9月16日举办", true, 14);
        hotSearches.addHotSearchTest("杨祐宁结婚新", false, 13);
        hotSearches.addHotSearchTest("马路积水铲车运送市民10元一位", false, 12);

        normalUsers.addNormalUser("test2");
        normalUsers.addNormalUser("test3");
        normalUsers.addNormalUser("test4");
        normalUsers.addNormalUser("test5");
    }

    Boolean isAnyOneLogin() {
        return isAdmin || curUser != null;
    }

    void logout() {
        isAdmin = false;
        curUser = null;
    }

    void login() throws Exception {
        System.out.println("请输入用户id");
        Scanner scan = new Scanner(System.in);
        Integer ID = scan.nextInt();
        curUser = normalUsers.getNormalUserByID(ID);
    }

    void adminLogin() {
        isAdmin = true;
    }

    void addHotSearch() {
        System.out.println("请输入热搜描述");
        Scanner scan = new Scanner(System.in);
        String desc = scan.next();
        RankHotSearchService.addOneHotSearch(hotSearches, desc);
    }

    void addSuperHotSearch() {
        System.out.println("请输入热搜描述");
        Scanner scan = new Scanner(System.in);
        String desc = scan.next();
        RankHotSearchService.addOneSuperHotSearch(hotSearches, desc);
    }

    void vote() throws Exception {
        System.out.println("请按顺序输入投票的热搜ID和投票的数目， 以空格隔开");
        Scanner scan = new Scanner(System.in);
        Integer ID = scan.nextInt();
        Integer number = scan.nextInt();
        HotSearch hotSearch = hotSearches.getHotSearchByID(ID);
        RankHotSearchService.vote(number, curUser, hotSearch, hotSearches);
    }

    void buyHotSearch() throws Exception {
        System.out.println("请按顺序输入购买热搜ID、金额、以及排行， 以空格隔开");
        Scanner scan = new Scanner(System.in);
        Integer ID = scan.nextInt();
        Integer money = scan.nextInt();
        Integer rankNumber =scan.nextInt();
        HotSearch hotSearch = hotSearches.getHotSearchByID(ID);
        RankHotSearchService.buyHotSearch(money, curUser, rankNumber, hotSearch, rank);
    }
}
