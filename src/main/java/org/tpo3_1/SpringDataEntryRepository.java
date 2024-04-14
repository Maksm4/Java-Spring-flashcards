package org.tpo3_1;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringDataEntryRepository extends CrudRepository<Entry, Integer> {


    default Entry getRandomEntry(){
        List<Entry> entries = (List<Entry>) findAll();
        int randomI = (int)(Math.random() * entries.size());
        return entries.get(randomI);
    }

   default void updateWordGerman(int id,String modification) throws WordNotFoundException {
       Entry dbEntry = findById(id).orElseThrow(WordNotFoundException::new);
       dbEntry.setGerman(modification);
       save(dbEntry);
   }

    default void updateWordEnglish(int id,String modification) throws WordNotFoundException {
        Entry dbEntry = findById(id).orElseThrow(WordNotFoundException::new);
        dbEntry.setEnglish(modification);
        save(dbEntry);
    }

    default void updateWordPolish(int id,String modification) throws WordNotFoundException {
        Entry dbEntry = findById(id).orElseThrow(WordNotFoundException::new);
        dbEntry.setPolish(modification);
        save(dbEntry);
    }

}
