//scenes, Drawable, and Moveable were provided for this project

package assignment9;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import assignment9.scenes.Bubbles;
import assignment9.scenes.Clear;
import assignment9.scenes.Forest;
import assignment9.scenes.Leaves;
import assignment9.scenes.Line;
import assignment9.scenes.Poly;
import assignment9.scenes.Sequence;
import assignment9.scenes.ifs.Dragon;
import assignment9.scenes.ifs.Leaf;
import edu.princeton.cs.introcs.StdDraw;
import support.cse131.ArgsProcessor;

public class SceneComposer {
    ArgsProcessor ap = new ArgsProcessor(args);
		
		// Note: Double Buffering is enabled!  
		//       You'll need to call show() to update the screen.
		//       In most cases you'll want to call show() after you've drawn something
		StdDraw.enableDoubleBuffering();

// CODE BEGINS BELOW:
		//keep track of drawable keys
		List<String> keys = new LinkedList<String>();
		//keep track of drawable names
		List<String> names = new LinkedList<String>();
		//keep track of keys and drawings
		Map<String, Drawable> allDrawings = new HashMap<String, Drawable>();
		
		//create init
		List<Drawable> init = new LinkedList<Drawable>();
		
		//enter initial keys and values
		for(int i = 1; i <= 9; i++) {
			Bubbles b = new Bubbles((int)(Math.random() * 15 + 1));
			allDrawings.put("b" + i, b);
			keys.add("b" + i);
			names.add("Bubbles " + i);
			if(Math.random() < 0.25) {
				init.add(b);
			}
		}
		for(int i = 1; i <= 9; i++) {
			Forest f = new Forest((int)(Math.random() * 15 + 1));
			allDrawings.put("f" + i, f);
			keys.add("f" + i);
			names.add("Forest " + i);
			if(Math.random() < 0.25) {
				init.add(f);
			}
		}
		for(int i = 1; i <= 9; i++) {
			Poly p = new Poly ((int)(Math.random() * 10 + 1));
			allDrawings.put("p" + i, p);
			keys.add("p" + i);
			names.add("Poly " + i);
			if(Math.random() < 0.25) {
				init.add(p);
			}
		}
		for(int i = 1; i <= 9; i++) {
			Leaves l = new Leaves ((int)(Math.random() * 10 + 1));
			allDrawings.put("l" + i, l);
			keys.add("l" + i);
			names.add("Leaves " + i);
			if(Math.random() < 0.25) {
				init.add(l);
			}
		}
		for(int i = 1; i <= 9; i++) {
			Line ln = new Line ();
			allDrawings.put("ln" + i, ln);
			keys.add("ln" + i);
			names.add("Line " + i);
			if(Math.random() < 0.25) {
				init.add(ln);
			}
		}
		
		
		for(int i = 1; i <= 9; i++) {
			double x = Math.random() * 0.9 + 0.05;
			double y = Math.random() * 0.9 + 0.05;
			double size = Math.random() * 0.75 + 0.25;
			Dragon d = new Dragon(x, y, size);
			allDrawings.put("d" + i, d);
			keys.add("d" + i);
			names.add("Dragon " + i);
			if(Math.random() < 0.25) {
				init.add(d);
			}
		}		
		
		Sequence seq = new Sequence(init);
		allDrawings.put("init", seq);
		keys.add("init");
		names.add("initial scene");
		
		//prompt user
		String s = ap.nextString("Express yourself: ");
		//run program
		while(s != null && !s.equalsIgnoreCase("end")) {		
			//user enters a key that does not exist
			if(allDrawings.get(s) == null && !s.equalsIgnoreCase("clear") && !s.equalsIgnoreCase("ron") && !s.equalsIgnoreCase("roff") ) {
				for(int i = 0; i < allDrawings.size(); i++) {				
					System.out.println(keys.get(i) + ": " + names.get(i));
				}
			} else if(allDrawings.get(s) != null){
				allDrawings.get(s).draw();
				StdDraw.show();
			}
			
			//recording on
			if(s.equalsIgnoreCase("ron")) {
				List<Drawable> record = new LinkedList<Drawable>();
				//prompt user
				s = ap.nextString("Express yourself (recording): ");
				//user puts value that does not exist
				while(allDrawings.get(s) == null && !s.equalsIgnoreCase("clear") && !s.equalsIgnoreCase("ron") && !s.equalsIgnoreCase("roff") ) {
					for(int i = 0; i < allDrawings.size(); i++) {				
						System.out.println(keys.get(i) + ": " + names.get(i));
					}
					s = ap.nextString("Express yourself (recording): ");
				} 
				while(!s.equalsIgnoreCase("roff")) {
					record.add(allDrawings.get(s));
					allDrawings.get(s).draw();
					StdDraw.show();
					
					//prompt user
					s = ap.nextString("Express yourself (recording): ");
					//user puts value that does not exist
					while(allDrawings.get(s) == null && !s.equalsIgnoreCase("clear") && !s.equalsIgnoreCase("ron") && !s.equalsIgnoreCase("roff") ) {
						for(int i = 0; i < allDrawings.size(); i++) {				
							System.out.println(keys.get(i) + ": " + names.get(i));
						}
						s = ap.nextString("Express yourself (recording): ");
					} 
				}
				Sequence newScene = new Sequence(record);
				//recording off
				String key = ap.nextString("Name your scene: ");
				allDrawings.put(key, newScene);
				keys.add(key);
				names.add("user created scene");		
			}
			
			//clear scene
			if(s.equalsIgnoreCase("clear")) {
				StdDraw.clear();
				StdDraw.show();
			}
			
			//prompt user
			s = ap.nextString("Express yourself: ");
		}
		StdDraw.show();
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(Color.black);
		StdDraw.text(0.5, 0.5, "end");
		StdDraw.show();

}
