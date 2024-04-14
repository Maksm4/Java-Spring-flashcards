package org.tpo3_1;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EntryRepository
{
    private final DisplayWords displayWords;
    private SpringDataEntryRepository springDataEntryRepository;


    public EntryRepository(DisplayWords displayWords,SpringDataEntryRepository springDataEntryRepository) {
        this.springDataEntryRepository = springDataEntryRepository;
        this.displayWords = displayWords;
    }

    public void displayWords(List<Entry> entries)
    {
        displayWords.display(entries);
    }

    public void addWord(Entry entry) {
        springDataEntryRepository.save(entry);
    }
}


