package org.example.testtaskbotscrew;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    @Value("${datasource.init.sql.path}")
    private String sqlFilePath;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing database...");
        Path path = Paths.get(sqlFilePath);

        // Перевірка наявності файлу
        if (!Files.exists(path)) {
            throw new RuntimeException("SQL file not found: " + sqlFilePath);
        }

        // Механізм повторних спроб
        int maxRetries = 3;
        int attempt = 0;
        boolean success = false;

        while (attempt < maxRetries && !success) {
            attempt++;
            try (Connection conn = dataSource.getConnection()) {
                String sql = new String(Files.readAllBytes(path));
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                    success = true;
                    System.out.println("Database initialization completed successfully.");
                }
            } catch (Exception e) {
                System.err.println("Attempt " + attempt + " failed: " + e.getMessage());
                if (attempt < maxRetries) {
                    System.out.println("Retrying in 5 seconds...");
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    throw new RuntimeException("Database initialization failed after " + maxRetries + " attempts.", e);
                }
            }
        }
    }
}
