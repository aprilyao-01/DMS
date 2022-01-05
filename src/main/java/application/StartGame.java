package application;

import javafx.application.Application;


/** <h1> Class: {@link StartGame} </h1>
 *<p>Main class to access the program, which come from old StartGame class.<br>
 *
 * <br>Refactor:<br>
 * 		1. The program will jump to start menu first {@link controller.StartScreenController}<br>
 *
 * @see AppUI
 * @version 1.2
 * @since 1.0
 * */
public class StartGame {

	public static void main(String[] args){
		Application.launch(AppUI.class);
	}
//	 use [space] to start/pause the game
//	 use [←] to move the player left
//	 use [→] to move the player right
//	 use [esc] to enter/exit pause menu
//	 use [alt+shift+f1] at any time to display debug panel
}
