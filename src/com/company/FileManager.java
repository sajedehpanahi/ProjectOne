package com.company;

import Exceptions.ArgumentOutOfRangeException;
import Exceptions.DurationDaysOutOfRangeException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public List<Deposit> parseDocument() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ArgumentOutOfRangeException {

        boolean bCustomerNumber = false;
        boolean bDepositType = false;
        boolean bDepositBalance = false;
        boolean bDurationDays = false;

        List<Deposit> list = new ArrayList<Deposit>();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("input.xml"));

            String customerNumber = "";
            int durationDays = 0;
            BigDecimal depositBalance = new BigDecimal(0);
            String depositType = "";

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if ("customerNumber".equalsIgnoreCase(qName)) {
                            bCustomerNumber = true;
                        } else if ("depositType".equalsIgnoreCase(qName)) {
                            bDepositType = true;
                        } else if ("depositBalance".equalsIgnoreCase(qName)) {
                            bDepositBalance = true;
                        } else if ("durationInDays".equalsIgnoreCase(qName)) {
                            bDurationDays = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if (bCustomerNumber) {
                            bCustomerNumber = false;
                            customerNumber = characters.getData();
                        }
                        if (bDepositType) {
                            depositType = characters.getData();
                            bDepositType = false;
                        }
                        if (bDepositBalance) {
                            depositBalance = new BigDecimal(characters.getData().trim());
                            bDepositBalance = false;
                        }
                        if (bDurationDays) {
                            durationDays = Integer.parseInt(characters.getData().trim());
                            bDurationDays = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if ("deposit".equalsIgnoreCase(endElement.getName().getLocalPart())) {
                            try {
                                list.add(new Deposit(depositType, customerNumber, durationDays, depositBalance));
                            } catch (ArgumentOutOfRangeException ex) {
                                System.out.println("Error in field with customer number#" + customerNumber + ": Deposit Balance must be positive!");
                            } catch (DurationDaysOutOfRangeException ex) {
                                System.out.println("Error in field with customer number#" + customerNumber + ": Duration Days must be greater than zero!");
                            } catch (ClassNotFoundException ex) {
                                System.out.println("Error in field with customer number#" + customerNumber + ": Unknown deposit type");
                            }
                            continue;
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

    private Characters getCharacters(Characters characters) {
        return characters;
    }

    public void writeOutputFile(List<Deposit> deposits, String path) throws IOException {

        File file = new File(path);
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Deposit deposit : deposits) {
            bufferedWriter.write(deposit.getCustomerNumber() + "#" + deposit.getPayedInterest() + "\n");
        }
        bufferedWriter.close();
    }
}
