package dk.acto.blackdragon.service.impl;

import dk.acto.blackdragon.model.AuthorData;
import dk.acto.blackdragon.service.AuthorDataFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthorDataFactoryImpl implements AuthorDataFactory {
    @Override
    public AuthorData create() {
        try {
            return AuthorData.builder()
                    .name("Rune Røddik Hansen")
                    .linkedInProfile(new URI("https://www.linkedin.com/in/rune-rodrik-hansen").toURL())
                    .solutionRepository(new URI("https://github.com/TheRedRish/black-dragon-java").toURL())
                    .build();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
