package com.nedap.archie.flattener.specexamples;

import com.nedap.archie.adlparser.ADLParser;
import com.nedap.archie.aom.Archetype;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

class FlattenerTestUtil {

    protected static Archetype parse(String filename) throws IOException {
        ADLParser parser = ADLParser.withRMConstraintsImposer();
        InputStream stream = FlattenerExamplesFromSpecTest.class.getResourceAsStream(filename);
        if(stream == null) {
            fail("cannot find file: " + filename);
        }
        Archetype result = parser.parse(stream);
        assertTrue("there should be no errors parsing " + filename + ", but was: " + parser.getErrors(), parser.getErrors().hasNoMessages());
        return result;
    }
}
