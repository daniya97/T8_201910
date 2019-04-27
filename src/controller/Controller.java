package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.File;

import model.logic.JavaAJSON;
import model.logic.MovingViolationsManager;
import view.MovingViolationsManagerView;

public class Controller {
	/*
	 * Atributos
	 */
	/**
	 * Objeto de la Vista
	 */
	private MovingViolationsManagerView view;

	private MovingViolationsManager model;

	/*
	 * Constructor
	 */
	public Controller()
	{
		view = new MovingViolationsManagerView();
		model = new MovingViolationsManager();
	}

	/*
	 * Metodos
	 */
	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		//Controller controller = new Controller();
		int option = -1;
		boolean numeroEncontrado = false;
		boolean esSatisfactorio;

		long startTime;
		long endTime;
		long duration;

		while(!fin)
		{
			view.printMenu();
			// Para tener que reiniciar el programa si no se da una opcion valida
			while (!numeroEncontrado){
				try {
					option = sc.nextInt();
					numeroEncontrado = true;
				} catch (InputMismatchException e) {
					System.out.println("Esa no es una opcion valida");
					view.printMenu();
					sc = new Scanner(System.in);
				}
			} numeroEncontrado = false;

			try { // Este try se usa para no tener que reiniciar el programa en caso de que 
				// ocurra un error pequenio al ejecutar como ingresar mal la fecha  

				switch(option)
				{
				case 0:
					view.printMessage("Ingrese el nombre del archivo (guardado en 'data'):");
					String nombreXML = sc.next();
					Integer[] resultados0 = model.loadXML("./data/"+nombreXML);
					view.printResumenCarga(resultados0);
					break;
					
				case 1:
					
					view.printMessage("Nombre del JSON: ");
					String nombreJsonC = sc.next();

					esSatisfactorio = model.guardarEnJson(nombreJsonC);
					view.printReq1(esSatisfactorio);
					break;

				case 2:
					view.printMessage("Nombre del JSON: ");
					String nombreJsonG = sc.next();

					esSatisfactorio = model.cargarDeJson(nombreJsonG);
					view.printReq2(esSatisfactorio);
					break;

				case 3:
					view.printMessage("Nombre del archivo a crear: ");
					String nombreHTML = sc.next();
					File htmlMapa = model.crearMapa(nombreHTML);
					view.printReq3(htmlMapa);
					break;
				
					
				case 11:
					fin=true;
					sc.close();
					break;
				}
			} catch(Exception e) { // Si ocurrio un error al ejecutar algun metodo
				e.printStackTrace(); System.out.println("Ocurrio un error. Se recomienda reiniciar el programa.");}
		}
	}

}

