package org.jabref.logic.integrity;

import java.util.Optional;

import org.jabref.model.database.BibDatabaseContext;
import org.jabref.model.database.BibDatabaseMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonNamesCheckerTest {

    private PersonNamesChecker checker;

    @BeforeEach
    public void setUp() throws Exception {
        BibDatabaseContext databaseContext = new BibDatabaseContext();
        databaseContext.setMode(BibDatabaseMode.BIBTEX);
        checker = new PersonNamesChecker(databaseContext);
    }

    @Test
    public void complainAboutPersonStringWithTwoManyCommas() throws Exception {
        assertEquals(Optional.of("Names are not in the standard BibTeX format."),
                checker.checkValue("Test1, Test2, Test3, Test4, Test5, Test6"));
    }

    @Test
    public void doNotComplainAboutSecondNameInFront() throws Exception {
        assertEquals(Optional.empty(), checker.checkValue("M. J. Gotay"));
    }
}
