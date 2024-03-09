package Main;

import customExceptions.ToyStoreException;
import mapping.dtos.ToyDTO;
import services.ToyStorelmpl;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class View {
    public static void main(String[] args) throws SQLException {
        ToyStorelmpl toyStore = new ToyStorelmpl();
        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        String menu = "Welcome to the toy store\n" +
                "1. Add a toy\n" +
                "2. Search for a toy\n" +
                "3. Increase the quantity of a toy\n" +
                "4. Decrease the quantity of a toy\n" +
                "5. Show toys above a certain price\n" +
                "6. Show total number of toys\n" +
                "7. Show total price of all toys\n" +
                "8. Show toy by type\n" +
                "9. List of toys\n" +
                "10. Ingresar nuevo cliente\n"+
                "11. Ingresar nuevo empleado\n"+
                "12. Ingresar nueva venta\n"+
                "0. Exit";

        int option = 0;
        do {
            System.out.println(menu);
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        try {

                        } catch (Exception e) {
                            System.out.println("Error adding a toy: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Enter the name of the toy to search:");
                        String searchName = scanner.nextLine();
                        ToyDTO toy;
                        try {
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6: {

                        break;
                    }
                    case 7: {

                        break;
                    }
                    case 8:

                        break;
                    case 9:
                        CompletableFuture<List<ToyDTO>> future = CompletableFuture.supplyAsync(() -> {
                            List<ToyDTO> list = toyStore.listToys();
                            if (!list.isEmpty()) {
                                for (ToyDTO toys : list) {
                                    System.out.println(toys);
                                    System.out.println("Loading...");
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                System.out.println("There are no toys on the list");
                            }
                            return list;
                        });
                        future.join();
                        System.out.println("The task is completed");
                        break;
                    case 10:
                        System.out.println("Thank you for visiting the toy store. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number from 1 to 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a number.");
                scanner.nextLine();
            }
        } while (option != 0);

        scanner.close();
    }
    }

