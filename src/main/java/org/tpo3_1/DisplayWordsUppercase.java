package org.tpo3_1;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("uppercase")
public class DisplayWordsUppercase implements DisplayWords {
    @Override
    public void display(List<Entry> dict) {
        for(Entry entry : dict)
        {
            System.out.println(entry.getId() +  " | " + entry.getPolish().toUpperCase()+ " | " +
                    entry.getEnglish().toUpperCase() + " | " +
                    entry.getGerman().toUpperCase());
        }

    }
}
