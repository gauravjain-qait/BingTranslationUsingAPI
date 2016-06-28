package bing_translation;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bing_translation_api {

	public static ArrayList<String> translation(){
		Translate.setClientId("gauravjain_bingtranslator");
		Translate.setClientSecret("YkmtPMALQc2MLM6JKgzbRzNnQGd6EBHwCUfAtJPeqvs=");
		
		String text;
		Language from,to;
		ArrayList<String> translated_list = new ArrayList<String>();
		BufferedReader br=null;
		String csv_file = new String("/home/gauravjain/Desktop/1.csv");
		String line, word[];
		
		try {
			br = new BufferedReader(new FileReader(csv_file));
			//strip 1st line
			br.readLine();
			while ((line = br.readLine()) != null) {
				word = line.split(",");
				if(word[0].equals(""))
					from = Language.AUTO_DETECT;
				else
					from = Language.valueOf(word[0].toUpperCase());
				
				if(word[1].equals(""))
					to = Language.AUTO_DETECT;
				else
					to = Language.valueOf(word[1].toUpperCase());
				text = word[2];
				translated_list.add(Translate.execute(text,from,to));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(br!=null)
				try {
					br.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		return translated_list;
	}
}
