package com.example.pokemon;

import android.provider.BaseColumns;

public class PokeContract {
    private PokeContract(){}
    public static final class PokeEntry implements BaseColumns{
        public static final String TABLE_NAME="Pokemon";
        public static final String COLUMN_NAME="PokeName";
        public static final String COLUMN_IMAGE="pokeImage";
        public static final String COLUMN_TIMESTAMP="timestamp";
    }
}
