package org.tpo3_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {

   private EntryRepository entryRepository;
   private BufferedReader reader;

    @Autowired
    public FileService(EntryRepository entryRepository, @Value("${pl.edu.pja.tpo03.filename}") String filename) {
        this.entryRepository = entryRepository;
        try {
            this.reader = new BufferedReader(new InputStreamReader(new ClassPathResource(filename).getInputStream(), StandardCharsets.UTF_16BE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeDatabase() throws IOException {
        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] words = line.split(";");
            Entry entry = new Entry(words[0],words[1],words[2]);
            entryRepository.addWord(entry);
        }
    }

    public EntryRepository getEntryRepository() {
        return entryRepository;
    }


}
