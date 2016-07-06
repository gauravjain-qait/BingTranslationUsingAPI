package bing_translation;

import java.io.*;
import java.util.ArrayList;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Bing_translation_gui {
	//method to capitalize word
	private static String capitalize(final String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	public static ArrayList<String> translation(){
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.bing.com/translator");


		ArrayList<String> translated_list = new ArrayList<String>();

		BufferedReader br=null;
		try {
			//Reading from csv file 
			String text, csv_file = new String("/home/gauravjain/Desktop/1.csv");
			br = new BufferedReader(new FileReader(csv_file));
			String from, to, line, word[];
			//strip 1st line
			br.readLine();
			while ((line = br.readLine()) != null) {
				Thread.sleep(2000);
				word = line.split(",");
				if(word[0].equals(""))
					from = "Auto-Detect";
				else
					from = word[0];

				if(word[1].equals(""))
					to = "Auto-Detect";
				else
					to = word[1];
				text = word[2];
				//System.out.println(Bing_translation_gui.capitalize(from));
				driver.findElement(By.cssSelector("#srcText")).clear();
				driver.findElement(By.xpath(".//*[@class='col translationContainer sourceText']/div[1]/div[1]")).click();
				driver.findElement(By.xpath(".//*[@class='col translationContainer sourceText']/div[1]/div[1]/div[1]/table/tbody/tr[*]/td[text() = '"+Bing_translation_gui.capitalize(from)+"']")).click();
				driver.findElement(By.cssSelector("#srcText")).sendKeys(text);
				driver.findElement(By.xpath(".//*[@class='col translationContainer destinationText']/div[1]/div[1]")).click();
				driver.findElement(By.xpath(".//*[@class='col translationContainer destinationText']/div[1]/div[1]/div[1]/table/tbody/tr[*]/td[text() = '"+Bing_translation_gui.capitalize(to)+"']")).click();
				Thread.sleep(2000);
				translated_list.add((driver.findElement(By.cssSelector("#destText")).getText()));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null)
				try{
					br.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			if(driver!=null)
				driver.close();	
		}
		return translated_list;
	}
}
