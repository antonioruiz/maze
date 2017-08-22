package maze;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class MazeDraw {
	
	Terminal terminal;
	Screen screen;
	int xJerry;
	int yJerry;
	
	public MazeDraw() {
		super();
		try {
			terminal = new DefaultTerminalFactory().createTerminal();
			screen = new TerminalScreen(terminal);
		} catch (IOException e) {
			System.err.println("Error creating terminal.");
			e.printStackTrace();
		}
	}

	public static void main(String... args) throws IOException{
		MazeDraw maze = new MazeDraw();
		int[][] mazeArray = maze.readMazeFromInput();
		List<List<Integer>> listOfList = Arrays.stream(mazeArray)
				.map(row -> IntStream.of(row).boxed().collect(Collectors.toList()) )
					.collect(Collectors.toList());
		
		System.out.println("maze : " + listOfList);
		//drawMaze(maze);
		
		
		/*
		try {
			maze.terminal.exitPrivateMode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	private int[][] readMazeFromInput() throws IOException {
		screen.startScreen();
		TerminalSize terminalSize = screen.getTerminalSize();
        String inputSizeLabel = "Terminal Size :" + terminalSize;
        TerminalPosition labelBoxTopLeft = new TerminalPosition(1,1);
        List<String> messages = Stream.of("Enter number of rows: ", "Enter number of columns:",
        		"Enter row values: ").collect(Collectors.toList());
        
        int BOX_HEIGHT = 5;
        int BOX_WIDTH = Collections.max(messages, Comparator.comparing(s-> s.length())).length();
        int width = messages.stream().
        		mapToInt(s-> s.length()).max().getAsInt();
        assert(width==BOX_WIDTH);
        
        TerminalSize labelBoxSize = new TerminalSize(BOX_WIDTH +3 , BOX_HEIGHT);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 1);
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');
        //Horizontal lines
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(labelBoxSize.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        
        //Edges
        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);
        //Text
        textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), inputSizeLabel);
        
        screen.refresh();
        //screen.
		Thread.yield();
		
		/*Uncomment when ready to read from input
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number of columns:");
		int cols = scan.nextInt();
		System.out.println("Enter number of rows:");
		int rows = scan.nextInt();
		int[][] mazeArray = new int[rows][cols];
		
		for(int r =0; r<rows; r++){
			System.out.println("Enter row " +r +": (0=path, 1=wall, 2=path/cheese) ");
			for(int c = 0; c<cols; c++){
				mazeArray[r][c] = scan.nextInt();
				System.out.println();
			}
		}
		System.out.print("Enter Jerry's x coordinate: ");
		xJerry scan.nextInt();
		System.out.println();
		System.out.print("Enter Jerry's y coordinate: ");
		yJerry = scan.nextInt();
		System.out.println();
		return mazeArray;*/
		int[][] mazeArray ={{0,2,0},{1,1,2},{1,0,0}};
		return mazeArray;
	}

	private static void drawMaze(MazeDraw maze) {
		try {
			maze.terminal.enterPrivateMode();
			//maze.terminal.set
			
			maze.terminal.setCursorPosition(10, 5);
			System.out.println("Terminal size: " + maze.terminal.getTerminalSize());
			maze.terminal.putCharacter('H');
			maze.terminal.putCharacter('e');
			maze.terminal.putCharacter('l');
			maze.terminal.putCharacter('l');
			maze.terminal.putCharacter('o');
			maze.terminal.setCursorPosition(10,20);
			maze.terminal.flush();
			
			Thread.sleep(2000);
			
			maze.terminal.setBackgroundColor(TextColor.ANSI.BLUE);
			maze.terminal.setForegroundColor(TextColor.ANSI.YELLOW);
			
			maze.terminal.putCharacter('Y');
		    maze.terminal.putCharacter('e');
		    maze.terminal.putCharacter('l');
		    maze.terminal.putCharacter('l');
		    maze.terminal.putCharacter('o');
		    maze.terminal.putCharacter('w');
		    maze.terminal.putCharacter(' ');
		    maze.terminal.putCharacter('o');
		    maze.terminal.putCharacter('n');
		    maze.terminal.putCharacter(' ');
		    maze.terminal.putCharacter('b');
		    maze.terminal.putCharacter('l');
		    maze.terminal.putCharacter('u');
		    maze.terminal.putCharacter('e');
		    maze.terminal.flush();
		    Thread.sleep(2000);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
