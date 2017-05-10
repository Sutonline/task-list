package cn.kevin.common.dao;

import java.sql.SQLException;

/**
 * Created by yongkang.zhang on 2017/5/10.
 */
public class DaoException extends RuntimeException {

    /**
     * @serial Means this field is searialized
     */
    private String SQLState;

    /**
     * @serial
     */
    private int vendorCode;

    public DaoException() {

    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }

    public DaoException(String message, String SQLState) {
        super(message);
        this.SQLState = SQLState;
    }

    public DaoException(String message, String SQLState, int vendorCode) {
        super(message);
        this.SQLState = SQLState;
        this.vendorCode = vendorCode;
    }

    public DaoException(SQLException exception) {
        super(exception);
        this.SQLState = exception.getSQLState();
        this.vendorCode = exception.getErrorCode();
    }

    /**
     * Retrieves the SQLState for this <code>SQLException</code> object.
     *
     * @return the SQLState value
     */
    public String getSQLState() {
        return (SQLState);
    }

    /**
     * Retrieves the vendor-specific exception code
     * for this <code>SQLException</code> object.
     *
     * @return the vendor's error code
     */
    public int getErrorCode() {
        return (vendorCode);
    }


    @Override
    public String toString() {
        return new StringBuilder("DaoException[")
                .append("SQLState = ").append(getSQLState())
                .append("vendorCode = ").append(vendorCode)
                .toString();
    }

}
