package xoudouqi.gui;

import java.util.Scanner;

import xoudouqi.bll.Echiquier;
import xoudouqi.bo.Joueur;
import xoudouqi.dal.DbInstaller;
import xoudouqi.dal.JoueurDao;
import xoudouqi.exceptions.Gameover;
import xoudouqi.exceptions.ImpossibleMoveException;
import xoudouqi.exceptions.WrongAcount;

public class main {
	public static int isInList(String[] animals, String target) {
	    for (int i = 0 ; i<animals.length ; i++ ) {
	        if (animals[i].equalsIgnoreCase(target)) {
	            return i;
	        }
	    }
	    return -1;
	}

	public static void main(String[] args) {
//		Enter your email : 
//			dounia@gmail.com
//			Enter your password : 
//			1238
//		Enter your email : 
//			oumaima@gmail.com
//			Enter your password : 
//			1234
//		if(DbInstaller.checkIfDbExist()) {
//			DbInstaller.install();
//		}
		Scanner sc = new Scanner(System.in);
		JoueurDao joueurDao = new JoueurDao();
		Echiquier e ;
		Joueur []j = new Joueur[2];
		
		String email;
		String pw;
		boolean v ;
		for(int i =0 ;i< 2 ;i++) {
			v = true;
			System.out.println("Player " + (i+1));
			while(v) {
				System.out.println("1- Login");
				System.out.println("2- Sign in (if you do not have an acount");
				System.out.println("3- Show results");
				switch(sc.next()) {
				case "1":
					while(true) {
						try {
							System.out.println("Enter your email : ");
							email = sc.next();
							
							System.out.println("Enter your password : ");
							pw = sc.next();
							if(joueurDao.exist(email, pw) != null) {
								j[i] = joueurDao.exist(email, pw);
								System.out.println("Successfully logged in!");
								v = false;
								break;
							}
							else {
								System.out.println("Wrong password! would you try again(y/n)");
								if(!sc.next().equals("y")) {
									return;
								}
							}
							
						}catch(WrongAcount w) {
							System.out.println(w.getMessage());
						}
						
					}
					
					break;
				case "2":
					System.out.println("Enter your email : ");
					 email = sc.next();
					
					System.out.println("Enter your password : ");
					 pw = sc.next();
					try {
						j[i] = joueurDao.create(email, pw);
						System.out.println("Successfully sign in!");
						break;
					} catch (WrongAcount e1) {
						System.out.println("This email already exist");;
					}
					v = false;
					
				case "3":
					while(true) {
						try {
							System.out.println("Enter your email : ");
							email = sc.next();
							
							System.out.println("Enter your password : ");
							pw = sc.next();
							if(joueurDao.exist(email, pw) != null) {
								j[i] = joueurDao.exist(email, pw);
								System.out.println("Successfully logged in!");
								v = false;
								break;
							}
							else {
								System.out.println("Wrong password! would you try again(y/n)");
								if(!sc.next().equals("y")) {
									return;
								}
							}
							
						System.out.println("Your results :");
						j[i].affiche();
						System.out.println("Do you want to continue ?(y/n)");
						if(!sc.next().equals("y")) {
							return;
						}
						}catch(WrongAcount w) {
							System.out.println(w.getMessage());
						}
						
					}
					
					break;
				default:
					System.out.println("Wrong choise ! would you try again(y/n)");
					if(!sc.next().equals("y")) {
						return;
					}
				}				
			}
		}
		System.out.println("The Game Start !");
		System.out.println();
		
		e = new Echiquier(j[0], j[1]);
		e.affiche();
		String animal;
		String direction;
		String[] animals = {
			    "Cat",      
			    "Dog",      
			    "Elephant",
			    "Lion",
			    "Tiger",   
			    "Panther",  
			    "Wolf",     
			    "Rat"
			};

		String[][] directions = {
				{
					"up",
					"down",
					"left",
					"right"
				},
				{
					"down",
					"up",
					"right",
					"left"
				}
		};
		v = true;
		while(v) {
			for(int i =0 ; i<2 ;i++) {
				while(true) {
					while(true) {
						System.out.println("Player " + (i+1) + " - which animal would you like to move? ");
						animal = sc.next().toLowerCase();
						System.out.println("In which direction ? (up/down/right/left)");
						direction = sc.next().toLowerCase();
						if (isInList(animals, animal)!=-1) {
							if(isInList(directions[0], direction)!=-1) {
								break;
							}
						}
						else {
							System.out.println("Wrong answer ! try again");
						}
					}
					
					try {
						direction = i ==1?directions[1][isInList(directions[0], direction)]:direction;
						e.handleGame(isInList(animals, animal), direction, i);
						System.out.println();
						e.affiche();
						break;
					} catch (ImpossibleMoveException e1) {
						System.out.println(e1.getMessage());
					} catch (Gameover e1) {
						System.out.println(e1.getMessage());
						e.affiche();
						v = false;
						break;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(v == false) {
					break;
				}
				
			}
			
		}
	}
}
