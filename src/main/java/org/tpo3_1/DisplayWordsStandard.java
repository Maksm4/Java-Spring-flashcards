package org.tpo3_1;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
@Configuration
@Primary
public class DisplayWordsStandard implements DisplayWords {

    @Override
    public void display(List<Entry> dict) {
        for(Entry entry : dict)
        {
            System.out.println(entry.getId() + " | " + entry.getPolish() + " | " +
                    entry.getEnglish() + " | " +
                    entry.getGerman());
        }
    }
}
