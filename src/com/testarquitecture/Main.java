package com.testarquitecture;

import enumerators.By;
import santtium.KeyEvent;
import santtium.implement.SanttiumProvider;
import santtium.interfaces.ICtrlWeb;
import santtium.interfaces.Santtium;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        Santtium driver = SanttiumProvider.GetProvider("DEVICE-CODE", "API-KEY", 10);
        driver.start();

        driver.keyPress(Arrays.asList(new Integer[]{KeyEvent.Up, KeyEvent.Enter}));

        ICtrlWeb btn = driver.searchCtrlWeb(By.ID, "play");
        btn.flash();
        btn.click();

        ICtrlWeb item1 = driver.searchCtrlWeb(By.ID, "item1");
        item1.flash();

        ICtrlWeb item2 = driver.searchCtrlWeb(By.ID, "item2");
        item2.flash();

        ICtrlWeb item3 = driver.searchCtrlWeb(By.ID, "item3");
        item3.flash();

        ICtrlWeb title = driver.searchCtrlWeb(By.XPATH, "/html/body/div/div[2]/div/h3");
        title.flash();

        driver.keyPress(KeyEvent.Down);
        driver.keyPress(Arrays.asList(new Integer[]{KeyEvent.Right, KeyEvent.Right}));
        driver.keyPress(Arrays.asList(new Integer[]{KeyEvent.Left, KeyEvent.Up}));
        driver.keyPress(KeyEvent.Enter);

        String imgB64 = driver.takeScreenShot(20);
        byte[] data = Base64.getDecoder().decode(imgB64.split(",")[1].getBytes(StandardCharsets.UTF_8));
        try (OutputStream stream = new FileOutputStream("/Users/veraa/Downloads/xxx.png")) {
            stream.write(data);
        }

        driver.scroll(100,100,By.ID, "item3");

        driver.keyPress(KeyEvent.Down);
        driver.keyPress(Arrays.asList(new Integer[]{KeyEvent.Down, KeyEvent.Enter}));
        driver.keyPress(Arrays.asList(new Integer[]{40, 13}));

        driver.stop();
    }
}
