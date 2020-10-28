package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Screenshot {

    protected static Logger logger = LogManager.getLogger(Screenshot.class);

    private static void saveObjectScreenshot(TakesScreenshot obj, OutputType outputType, String fileName) {
        File data;
        if (outputType.equals(OutputType.BASE64)) {
            String base64 = obj.getScreenshotAs(OutputType.BASE64);
            data = OutputType.FILE.convertFromBase64Png(base64);
        } else if (outputType.equals(OutputType.BYTES)) {
            byte[] bytes = obj.getScreenshotAs(OutputType.BYTES);
            data = OutputType.FILE.convertFromPngBytes(bytes);
        } else if (outputType.equals(OutputType.FILE)) {
            data = obj.getScreenshotAs(OutputType.FILE);
        } else {
            throw new IllegalStateException("Unexpected value: " + outputType);
        }
        saveFile(data, fileName);
    }

    private static void saveFile(File data, String fileName) {
        if (fileName == null) fileName = String.valueOf(System.currentTimeMillis());
        Path filePath = Paths.get("target", "screenshots", fileName + ".png");
        try {
            FileUtils.copyFile(data, new File(filePath.toString()));
            logger.debug(String.format("Save file %s", filePath.getFileName()));
        } catch (IOException e) {
            logger.error(String.format("Failed to save file %s: %s", filePath.getFileName(), e));
        }
    }

    public static void save(WebDriver driver) {
        saveObjectScreenshot((TakesScreenshot) driver, OutputType.BYTES, null);
    }

    public static void save(WebDriver driver, String fileName) {
        saveObjectScreenshot((TakesScreenshot) driver, OutputType.BYTES, fileName);
    }

    public static void save(WebElement element) {
        saveObjectScreenshot(element, OutputType.BYTES, null);
    }

    public static void save(WebElement element, String fileName) {
        saveObjectScreenshot(element, OutputType.BYTES, fileName);
    }

}
