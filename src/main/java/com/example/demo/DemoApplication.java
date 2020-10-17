package com.example.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    List noList = new ArrayList<>();
    Map<String , String> resultMap = new HashMap<>();

    @RequestMapping("/")
    public String index( Model model, @RequestParam(required = false) String filter) {

        List<Map<String, String>> result = new ArrayList();

        resultMap.keySet().forEach(key->{
            Map<String, String> hashMap = new HashMap();
            hashMap.put("key", key);
            hashMap.put("value", resultMap.get(key));
            result.add(hashMap);
        });

        System.out.println(result);

        if(filter != null) {
            model.addAttribute("list",result.stream().filter(map->map.get("value").equals(filter)).collect(Collectors.toList()));
        } else {
            model.addAttribute("list", result);
        }

        return "index";
    }


    @RequestMapping("/saveNo")
    public String save(String no, Model model) throws InterruptedException {
        System.out.println("no : " + no);
        noList.add(no);

        resultMap.put(no, scrapping(no));



        return "redirect:/";
    }

    @RequestMapping("/savesNo")
    public String save(String from, String to, Model model) throws InterruptedException {



        double fromNo = Double.parseDouble(from);
        double toNo = Double.parseDouble(to);

        BigInteger fromBig = new BigInteger(from);
        BigInteger toBig = new BigInteger(to);

//        for(Double i = fromNo ; i <= toNo ; i++) {
//            resultMap.put(Double.toString(i), scrapping(i.toString()));
//        }

        BigInteger i;
        for (i = fromBig; i.compareTo(toBig) == -1; i = i.add(BigInteger.ONE)) {

            // System.out.println(i);
            resultMap.put(i.toString(), scrapping(i.toString()));
        }

        i.add(BigInteger.ONE);
        resultMap.put(i.toString(), scrapping(i.toString()));


        return "redirect:/";
    }



    public String scrapping(String value) throws InterruptedException {

        String WEB_DRIVER_ID = "webdriver.chrome.driver";
        String WEB_DRIVER_PATH = "C:/crawller/demo/chromedriver.exe";

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        System.out.println("scrapping " + value);

        WebDriver driver;
        Map<String, Object> vars;
        JavascriptExecutor js;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    //1108604705
        driver.get("https://mssmiv.com/portal/regist/HeadNoChk");
        driver.manage().window().setSize(new Dimension(500, 577));
        driver.findElement(By.name("busi_rgst_no")).click();
        driver.findElement(By.name("busi_rgst_no")).click();
        driver.findElement(By.name("busi_rgst_no")).sendKeys(value);
        driver.findElement(By.cssSelector(".findBox-top > button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".findBox-top > button"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }

        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }

        Thread.sleep(1000);


        return driver.findElement(By.cssSelector(".thanks")).getText();

    }

    @Override
    public void run(String... args) throws Exception {
       // System.out.println(scrapping("1168201561"));
    }
}
