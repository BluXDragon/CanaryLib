package net.canarymod.database.exceptions;

public class DatabaseTableInconsistencyException extends Exception {

    private static final long serialVersionUID = -6274875008612771399L;

    public DatabaseTableInconsistencyException(String str) {
        super(str);
    }
}
