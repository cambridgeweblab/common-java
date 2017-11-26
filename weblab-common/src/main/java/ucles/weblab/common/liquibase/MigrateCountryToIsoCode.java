package ucles.weblab.common.liquibase;

import liquibase.change.custom.CustomSqlChange;
import liquibase.change.custom.CustomSqlRollback;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.RollbackImpossibleException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.UpdateStatement;
import liquibase.structure.core.Column;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Liquibase migration to convert a database column's value from a country name (in English) to an ISO alpha-2 country
 * code.
 *
 *
 * @since 15/10/15
 */
public class MigrateCountryToIsoCode implements CustomSqlChange, CustomSqlRollback {
    static Map<String, String> countries = new HashMap<>();

    static {
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(Locale.UK), iso);
        }
    }

    private String tableName;

    private String columnName;

    @Override
    public SqlStatement[] generateStatements(Database database) throws CustomChangeException {
        final SqlStatement[] sqlStatements = countries.entrySet().stream()
                .map(nameCodePair -> {
                    final UpdateStatement update = new UpdateStatement(null, null, tableName);
                    update.addNewColumnValue(columnName, nameCodePair.getValue());
                    update.setWhereClause(database.escapeObjectName(columnName, Column.class) + "='" + database.escapeStringForDatabase(nameCodePair.getKey()) + "'");
                    return update;
                }).toArray(SqlStatement[]::new);

        final SqlStatement[] extraSqlStatements = new SqlStatement[sqlStatements.length + 1];

        // Shuffle along so we can do the UK->GB change first.
        System.arraycopy(sqlStatements, 0, extraSqlStatements, 1, sqlStatements.length);

        final UpdateStatement update = new UpdateStatement(null, null, tableName);
        update.addNewColumnValue(columnName, "GB");
        update.setWhereClause(database.escapeObjectName(columnName, Column.class) + "='UK'");
        extraSqlStatements[0] = update;

        return extraSqlStatements;
    }

    @Override
    public SqlStatement[] generateRollbackStatements(Database database) throws CustomChangeException, RollbackImpossibleException {
        return countries.entrySet().stream()
                .map(nameCodePair -> {
                    final UpdateStatement update = new UpdateStatement(null, null, tableName);
                    update.addNewColumnValue(columnName, nameCodePair.getKey());
                    update.setWhereClause(database.escapeObjectName(columnName, Column.class) + "='" + database.escapeStringForDatabase(nameCodePair.getValue()) + "'");
                    return update;
                }).toArray(SqlStatement[]::new);
    }

    @Override
    public String getConfirmationMessage() {
        return "Countries in " + tableName + "." + columnName + " updated to ISO 3166-1 alpha-2 format";
    }

    @Override
    public void setUp() throws SetupException {
        // No setup required
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        // File opener not required
    }

    @Override
    public ValidationErrors validate(Database database) {
        return new ValidationErrors();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

}
