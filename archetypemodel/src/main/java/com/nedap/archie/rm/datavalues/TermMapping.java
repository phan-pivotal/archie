package com.nedap.archie.rm.datavalues;

import com.nedap.archie.rm.datatypes.CodePhrase;

import javax.annotation.Nullable;

/**
 * Created by pieter.bos on 04/11/15.
 */
public class TermMapping {
    /**
     * This is an interesting one, that could be implemented with an enum
     * //TODO: look at it
     * < = narrower term
     * = = equals term
     * > = broader term
     * ? = the kind of mapping is unknown
     */
    private char match = '?';
    @Nullable
    private DvCodedText purpose;
    private CodePhrase target;

    public char getMatch() {
        return match;
    }

    public void setMatch(char match) {
        this.match = match;
    }

    @Nullable
    public DvCodedText getPurpose() {
        return purpose;
    }

    public void setPurpose(@Nullable DvCodedText purpose) {
        this.purpose = purpose;
    }

    public CodePhrase getTarget() {
        return target;
    }

    public void setTarget(CodePhrase target) {
        this.target = target;
    }
}