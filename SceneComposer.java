//scenes, Drawable, and Moveable were provided for this project

package assignment9;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
//import all drawings
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
		
		//create 9 variations of each scene/drawing using Math.random on the imported scene package
		for(int i = 1; i <= 9; i++) {
			Bubbles b = new Bubbles((int)(Math.random() * 15 + 1));
			//put drawing/scene into allDrawings so it can be displayed on the screen when called by the corresponding key
			allDrawings.put("b" + i, b);
			//add the key name to the list of keys to be displayed to the user in the console
			keys.add("b" + i);
			//add the full name of the drawing/scene to the displayed alongside the key in the console
			names.add("Bubbles " + i);
			//use Math.random to determine if the current variation of the drawing will be added to the "init" scene sequence
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
		
		//create initial scene sequence
		//this is the only sequence that will be available upon starting the program to show users they can create scenes made up of
		//multiple individual drawings
		Sequence seq = new Sequence(init);
		//put the key init and the corresponding sequence into the map
		allDrawings.put("init", seq);
		//add init into the list of keys displayed to the user so the user will know it is something
		//they can call
		keys.add("init");
		names.add("initial scene");
		
		//prompt user to type in a command
		String s = ap.nextString("Express yourself: ");
		//run program if the user has not typed "end"
		while(s != null && !s.equalsIgnoreCase("end")) {		
			//show user the list of keys they can enter while they enter a command or key that does not exist
			if(allDrawings.get(s) == null && !s.equalsIgnoreCase("clear") && !s.equalsIgnoreCase("ron") && !s.equalsIgnoreCase("roff") ) {
				for(int i = 0; i < allDrawings.size(); i++) {				
					System.out.println(keys.get(i) + ": " + names.get(i));
				}
			} else if(allDrawings.get(s) != null){ //draw the scene corresponding to the key typed by the user
				allDrawings.get(s).draw();
				StdDraw.show();
			}
			
			//begin recording
			if(s.equalsIgnoreCase("ron")) {
				//create a list to track the sequence of drawings the user types while recording
				List<Drawable> record = new LinkedList<Drawable>();
				//prompt user to type of drawing
				s = ap.nextString("Express yourself (recording): ");
				//show user list of possible keys if they type a key or command that does not exist
				while(allDrawings.get(s) == null && !s.equalsIgnoreCase("clear") && !s.equalsIgnoreCase("ron") && !s.equalsIgnoreCase("roff") ) {
					for(int i = 0; i < allDrawings.size(); i++) {				
						System.out.println(keys.get(i) + ": " + names.get(i));
					}
					s = ap.nextString("Express yourself (recording): ");
				} 
				while(!s.equalsIgnoreCase("roff")) { //check if the user has chosen to end the recording
					record.add(allDrawings.get(s));
					allDrawings.get(s).draw();
					StdDraw.show();
					
					//continue to prompt user to type in drawings to add to their recording
					s = ap.nextString("Express yourself (recording): ");
					//show user list of possible keys if they type a key or command that does not exist
					while(allDrawings.get(s) == null && !s.equalsIgnoreCase("clear") && !s.equalsIgnoreCase("ron") && !s.equalsIgnoreCase("roff") ) {
						for(int i = 0; i < allDrawings.size(); i++) {				
							System.out.println(keys.get(i) + ": " + names.get(i));
						}
						s = ap.nextString("Express yourself (recording): ");
					} 
				}
				//user has ended recording here
				//create a sequence using the linked list that was tracking the drawings called by the user during recording
				Sequence newScene = new Sequence(record);
				//prompt user to name scene
				String key = ap.nextString("Name your scene: ");
				//add scene to map to be available to be called later, add scene key to the list of keys to be shown to the user
				allDrawings.put(key, newScene);
				keys.add(key);
				names.add("user created scene"); //name the scene so it is clear which scenes are user created		
			}
			
			//clear scene if the user types "clear"
			if(s.equalsIgnoreCase("clear")) {
				StdDraw.clear();
				StdDraw.show();
			}
			
			//prompt user after every drawing
			s = ap.nextString("Express yourself: ");
		}
		//user has typed "end", display the word "end" on screen to show that the program has finished running
		StdDraw.show();
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(Color.black);
		StdDraw.text(0.5, 0.5, "end");
		StdDraw.show();

}
