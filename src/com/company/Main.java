package com.company;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FileManager fm = new FileManager();
        try {
            List<Deposit> deposits =fm.parseDocument();

            Collections.sort(deposits, Collections.reverseOrder());

            fm.writeOutputFile(deposits,"output.txt");

        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ArgumentOutOfRange argumentOutOfRange) {
            argumentOutOfRange.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
