package org.tpo3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Controller
public class FlashcardsController {

    private FileService fileService;
    private Scanner scanner;
    private SpringDataEntryRepository springDataEntryRepository;

    @Autowired
    public FlashcardsController(FileService fileService, SpringDataEntryRepository springDataEntryRepository)
    {
        this.fileService = fileService;
        this.springDataEntryRepository = springDataEntryRepository;
        scanner = new Scanner(System.in);
    }


    public void run()
    {
        try {
            File databaseFile = new File("db/flashcards2.mv.db");

            if (!databaseFile.exists())
                fileService.initializeDatabase();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true)
        {

            System.out.println(
                    "enter 1 to add a new word \n" +
                    "enter 2 to display the dictionary \n" +
                    "enter 3 to test yourself\n" +
                    "enter 4 to remove a word\n" +
                    "enter 5 to modify a word\n" +
                    "enter 6 to search translation of a word\n");

            int action = scanner.nextInt();
            if (action == 1)
            {
                System.out.println("provide a word in english");
                String uk = scanner.next();
                System.out.println("provide a word in german");
                String de = scanner.next();
                System.out.println("provide a word in polish");
                String pl = scanner.next();

                springDataEntryRepository.save(new Entry(uk,de,pl));

            } else if (action == 2)
            {
                System.out.println("do you want to sort the words? Y/N");
                String decision = scanner.next();
                List<Entry> entries = (List<Entry>) springDataEntryRepository.findAll();
                if (decision.toUpperCase().equals("Y"))
                {
                    System.out.println("by which language do you want to sort?");
                    String lang = scanner.next();
                    System.out.println("by ascending 1 or by descending 2?");
                    int sortBy = scanner.nextInt();
                    if (lang.toLowerCase().equals("german"))
                    {
                        if (sortBy == 1)
                            entries.sort(Comparator.comparing(Entry::getGerman));
                        else if (sortBy == 2)
                            entries.sort(Comparator.comparing(Entry::getGerman).reversed());

                    } else if (lang.toLowerCase().equals("english"))
                    {
                        if (sortBy == 1)
                            entries.sort(Comparator.comparing(Entry::getEnglish));
                        else if (sortBy == 2)
                            entries.sort(Comparator.comparing(Entry::getEnglish).reversed());

                    }else if (lang.toLowerCase().equals("polish"))
                    {
                        if (sortBy == 1)
                            entries.sort(Comparator.comparing(Entry::getPolish));
                    else if (sortBy == 2)
                        entries.sort(Comparator.comparing(Entry::getPolish).reversed());

                }
                }
                fileService.getEntryRepository().displayWords(entries);
            } else if (action ==3)
            {
                Entry entry = springDataEntryRepository.getRandomEntry();
                int random = (int)(Math.random()*3);
                if (random == 0)
                {
                    System.out.println(entry.getEnglish());
                    System.out.println("give a translation in polish");
                    String polish = scanner.next().toLowerCase();
                    System.out.println("give a translation in german");
                    String german = scanner.next().toLowerCase();
                    if (polish.equals(entry.getPolish().toLowerCase())
                            && german.equals(entry.getGerman().toLowerCase()))
                        System.out.println("good job!");
                    else
                        System.out.println("you got something wrong :(");

                }else if (random == 1)
                {
                    System.out.println(entry.getPolish());
                    System.out.println("give a translation in english");
                    String english = scanner.next().toLowerCase();
                    System.out.println("give a translation in german");
                    String german = scanner.next().toLowerCase();
                    if (english.equals(entry.getEnglish().toLowerCase())
                            && german.equals(entry.getGerman().toLowerCase()))
                        System.out.println("good job!");
                    else
                        System.out.println("you got something wrong :(");

                }else if (random ==2)
                {
                    System.out.println(entry.getGerman());
                    System.out.println("give a translation in polish");
                    String polish = scanner.next().toLowerCase();
                    System.out.println("give a translation in english");
                    String english = scanner.next().toLowerCase();
                    if (polish.equals(entry.getPolish().toLowerCase())
                            && english.equals(entry.getEnglish().toLowerCase()))
                        System.out.println("good job!");
                    else
                        System.out.println("you got something wrong :(");

                }

            } else if (action == 4)
            {
                System.out.println("enter words id you want to delete");
                int id = scanner.nextInt();
                springDataEntryRepository.deleteById(id);
            }else if (action == 5)
            {
                System.out.println("enter id of the word you want to modify");
                int id = scanner.nextInt();
                System.out.println("enter the language of the word you want to modify");
                String lang = scanner.next();
                System.out.println("to what do you want to change?:");
                String changedLang = scanner.next();
                if (lang.toLowerCase().equals("german"))
                {
                    try {
                        springDataEntryRepository.updateWordGerman(id, changedLang);
                    } catch (WordNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else if (lang.toLowerCase().equals("english"))
                {
                    try {
                        springDataEntryRepository.updateWordEnglish(id, changedLang);
                    } catch (WordNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }else if (lang.toLowerCase().equals("polish")) {
                    try {
                        springDataEntryRepository.updateWordPolish(id, changedLang);
                    } catch (WordNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (action ==6)
            {
                System.out.println("in which language do you know the word?");
                String lang = scanner.next();
                System.out.println("enter the word you know to show the translation");
                String word = scanner.next();
                List<Entry> entries = (List<Entry>) springDataEntryRepository.findAll();
                if (lang.toLowerCase().equals("german"))
                {
                    for (Entry entry : entries)
                    {
                        if (entry.getGerman().equals(word))
                        {
                            System.out.println("english: " + entry.getEnglish() + " Polish: " + entry.getPolish());
                        }
                    }
                } else if (lang.toLowerCase().equals("english"))
                {
                    for (Entry entry : entries)
                    {
                        if (entry.getEnglish().equals(word))
                        {
                            System.out.println("German: " + entry.getGerman() + " Polish: " + entry.getPolish());
                        }
                    }

                }else if (lang.toLowerCase().equals("polish"))
                {
                    for (Entry entry : entries)
                    {
                        if (entry.getPolish().equals(word))
                        {
                            System.out.println("english: " + entry.getEnglish() + " German: " + entry.getGerman());
                        }
                    }

                }
            }
        }

    }
}
