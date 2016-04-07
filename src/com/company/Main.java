package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        FileManager fm = new FileManager();
        try {
            ArrayList<Account> accounts =fm.parseDocument();

            Collections.sort(accounts, Collections.reverseOrder());

            for ( Account account : accounts){
                System.out.println(account.getCustomerNumber() + "#" + account.calculateProfit());
            }



        } catch (ClassNotFoundException e) {
            System.out.println("unknown deposit type");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ArgumentOutOfRange argumentOutOfRange) {
            argumentOutOfRange.printStackTrace();
        }


    }
}
