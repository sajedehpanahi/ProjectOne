package com.company;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * <h1>File Manager Class</h1>
 * This class parses the input xml file
 * and writes output text file
 *
 * @author Sajedeh Panahi
 * @version 1.0
 * @since 4/6/2016.
 */
public class FileManager {

    public ArrayList<Account> parseDocument() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ArgumentOutOfRange {

        boolean bCustomerNumber=false;
        boolean bDepositType=false;
        boolean bDepositBalance=false;
        boolean bDurationDays=false;



        ArrayList<Account> list = new ArrayList<Account>();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =factory.createXMLEventReader( new FileReader("input.xml"));

            int customerNumber = 0;
            int durationDays = 0;
            BigDecimal depositBalance = new BigDecimal(0);
            String depositType = "";

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();

                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("customerNumber")) {
                            bCustomerNumber = true;
                        } else if (qName.equalsIgnoreCase("depositType")) {
                              bDepositType = true;
                        } else if (qName.equalsIgnoreCase("depositBalance")) {
                            bDepositBalance = true;
                        }
                        else if (qName.equalsIgnoreCase("durationInDays")) {
                            bDurationDays = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if(bCustomerNumber){
                            System.out.println("customer number: "+ characters.getData());
                            bCustomerNumber = false;
                            customerNumber = Integer.parseInt(characters.getData().trim());
                        }
                        if(bDepositType){
                            System.out.println("deposit type: "+ characters.getData());
                            depositType = characters.getData();
                            bDepositType = false;
                        }
                        if(bDepositBalance){
                            System.out.println("deposit balance: "+ characters.getData());
                            depositBalance = new BigDecimal(characters.getData().trim());
                            bDepositBalance = false;
                        }
                        if(bDurationDays){
                            System.out.println("duration days: "+ characters.getData());
                            durationDays = Integer.parseInt(characters.getData().trim());
                            bDurationDays = false;
                        }
                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("deposit")){
                            Class<?> cls = Class.forName("com.company." + depositType);
                            Account account = (Account)cls.newInstance();

                            account.setCustomerNumber(customerNumber);
                            account.setDepositBalance(depositBalance);
                            account.setDurationDays(durationDays);
                            list.add(account);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return list;
    }
}
