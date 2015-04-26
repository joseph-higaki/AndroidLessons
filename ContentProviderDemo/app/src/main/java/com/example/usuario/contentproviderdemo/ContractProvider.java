package com.example.usuario.contentproviderdemo;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by usuario on 4/25/2015.
 */
public class ContractProvider implements BaseColumns {
    public static final String TABLE = "main";
    public static final String _ID = "_id";
    public static final String WORD = "word";
    public static final String FREQUENCY = "frequency";
    public static final String LOCALE = "locale";
    public static final String AUTHORITY = "mycontentproviders";

    private static final String uri  = "content://com.example.usuario.contentproviderdemo." + AUTHORITY + "/main";
    public static final Uri CONTENT_URI = Uri.parse(uri);
}
