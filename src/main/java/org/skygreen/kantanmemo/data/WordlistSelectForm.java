package org.skygreen.kantanmemo.data;

import javax.ws.rs.FormParam;

public class WordlistSelectForm {
    @FormParam("wordlist_id")
    public String wordlistId;
}
