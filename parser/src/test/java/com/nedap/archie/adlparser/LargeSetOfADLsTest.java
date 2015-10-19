package com.nedap.archie.adlparser;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by pieter.bos on 16/10/15.
 */
public class LargeSetOfADLsTest {

    private static Logger logger = LoggerFactory.getLogger(LargeSetOfADLsTest.class);

    @Test
    public void parseLots() throws Exception {
        Reflections reflections = new Reflections("adl2-tests", new ResourcesScanner());
        List<String> adlFiles = new ArrayList(reflections.getResources(Pattern.compile(".*\\.adls")));

        Map<String, Exception> exceptions = new LinkedHashMap<>();
        Map<String, List<String>> parseErrors = new LinkedHashMap<>();

        for(String file:adlFiles) {
            try (InputStream stream = getClass().getResourceAsStream("/" + file)) {
                logger.info("trying to parse " + file);
                ADLParser parser = new ADLParser();
                parser.parse(stream);
                if(parser.errorListener.getErrors().size() > 0) {
                    parseErrors.put(file, parser.errorListener.getErrors());
                }
                if(parser.tree.exception != null) {
                    exceptions.put(file, parser.tree.exception);
                }
            } catch (Exception e) {
                exceptions.put(file, e);
            }
        }

        for(String file:adlFiles) {
            if(parseErrors.containsKey(file)) {
                logger.error("parse error found in " + file);
                for(String error:parseErrors.get(file)) {
                    logger.error(error);
                }
            }
            if(exceptions.containsKey(file)) {
                logger.error("exception found in " + file, exceptions.get(file));
            }



        }

        System.out.println("parsed adls: " + adlFiles.size());
        System.out.println("parsed adls with ANTLR parse errors: " + parseErrors.size());
        System.out.println("parsed adls with Exceptions: " + exceptions.size());


    }
}
