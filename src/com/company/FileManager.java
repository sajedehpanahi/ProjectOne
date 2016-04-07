package com.company;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public List<Deposit> parseDocument() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ArgumentOutOfRange {

        boolean bCustomerNumber=false;
        boolean bDepositType=false;
        boolean bDepositBalance=false;
        boolean bDurationDays=false;



        List<Deposit> list = new ArrayList<Deposit>();

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
                        if ("customerNumber".equalsIgnoreCase(qName)) {
                            bCustomerNumber = true;
                        } else if ("depositType".equalsIgnoreCase(qName)) {
                              bDepositType = true;
                        } else if ("depositBalance".equalsIgnoreCase(qName)) {
                            bDepositBalance = true;
                        }
                        else if ("durationInDays".equalsIgnoreCase(qName)) {
                            bDurationDays = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if(bCustomerNumber){
                            bCustomerNumber = false;
                            customerNumber = Integer.parseInt(characters.getData().trim());
                        }
                        if(bDepositType){
                            depositType = characters.getData();
                            bDepositType = false;
                        }
                        if(bDepositBalance){
                            depositBalance = new BigDecimal(characters.getData().trim());
                            bDepositBalance = false;
                        }
                        if(bDurationDays){
                            durationDays = Integer.parseInt(characters.getData().trim());
                            bDurationDays = false;
                        }
                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if("deposit".equalsIgnoreCase(endElement.getName().getLocalPart())){
                            list.add(Deposit.createDeposit(depositType, customerNumber, durationDays, depositBalance));
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

    public void writeOutputFile(List<Deposit> deposits, String path) throws IOException {

        File file = new File(path);
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(Deposit deposit : deposits) {
            bufferedWriter.write(deposit.getCustomerNumber() + "#" + deposit.calculateProfit() + "\n");
        }

        bufferedWriter.close();


    }


}
