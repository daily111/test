package com.example.test.tool;

import com.example.test.dto.User;

import java.util.ArrayList;
import java.util.List;

public class T2 {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setAccount("1");
        User user2 = new User();
        user2.setAccount("2");
        User user3 = new User();
        user3.setAccount("3");
        list.add(user1);
        list.add(user2);
        list.add(user3);

        for (User user : list) {
            if (user.getAccount().equals("2")){
                list.remove(user);
            }
        }
        System.out.println(list);
    }
}
