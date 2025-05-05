// DROP TABLE

import org.springframework.jdbc.core.JdbcTemplate

JdbcTemplate jdbcTemplate = spring.getBean("jdbcTemplate")
try {
    jdbcTemplate.update(" DROP <table_name>;")
}
catch (Exception e) {
    println(String.format("'%s'", e.getMessage()))
}






// DROP COLUMN

import org.springframework.jdbc.core.JdbcTemplate

JdbcTemplate jdbcTemplate = spring.getBean("jdbcTemplate")
try {
    jdbcTemplate.update("ALTER TABLE <table_name> DROP COLUMN <column_name>;")
}
catch (Exception e) {
    println(String.format("'%s'", e.getMessage()))
}

