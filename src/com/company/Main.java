package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FileManager fm = new FileManager();
        try {
            ArrayList<Object> list =fm.parseDocument();

            for ( Object obj : list){
                Class cls = obj.getClass();
                Method calculateProfit = cls.getSuperclass().getDeclaredMethod("calculateProfit",new Class[] {});
                BigDecimal profit = (BigDecimal)calculateProfit.invoke(obj, new Class[]{});

                Method getCustomerNumber = cls.getSuperclass().getDeclaredMethod("getCustomerNumber",new Class[] {});
                int customerNumber = (Integer)getCustomerNumber.invoke(obj, new Class[]{});
                System.out.println(customerNumber+"#"+profit);
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
        }


    }
}
