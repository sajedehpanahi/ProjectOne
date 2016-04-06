package com.company;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.*;
import java.io.*;

/**
 * Created by DotinSchool2 on 4/6/2016.
 */
public class FileManager {

    public void parseDocument() {

        boolean bCustomerNumber=false;
        boolean bDepositType=false;
        boolean bDepositBalance=false;
        boolean bDurationDays=false;



        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =factory.createXMLEventReader( new FileReader("input.xml"));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("deposit")) {
                            //System.out.println("Start Element : student");
                            //Iterator<Attribute> attributes = startElement.getAttributes();
                            //String rollNo = attributes.next().getValue();
                            //System.out.println("Roll No : " + rollNo);
                        } else if (qName.equalsIgnoreCase("customernumber")) {
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
                        }
                        if(bDepositType){
                            System.out.println("deposit type: "+ characters.getData());
                            bDepositType = false;
                        }
                        if(bDepositBalance){
                            System.out.println("deposit balance: "+ characters.getData());
                            bDepositBalance = false;
                        }
                        if(bDurationDays){
                            System.out.println("duration days: "+ characters.getData());
                            bDurationDays = false;
                        }
                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("deposit")){
                            //System.out.println("End Element : student");
                            //System.out.println();
                            //save account
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
