package Main;

import customExceptions.ToyStoreException;
import mapping.dtos.ToyDTO;
import model.ToyType;
import services.ToyStorelmpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class View {
    public static void main(String[] args) {
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
                "10. Exit";

        int option = 0;
        do {
            System.out.println(menu);
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        try {
                            System.out.println("Enter the toy name:");
                            String toyName = scanner.nextLine();
                            System.out.println("Enter the toy type: 0.FEMALE, 1.MALE, or  2.UNISEX");
                            ToyType toyType = ToyType.getTypeByValue(Integer.parseInt(scanner.next()));
                            System.out.println("Enter the price of the toy:");
                            int stock = scanner.nextInt();
                            System.out.println("Enter the stock of the toy:");
                            int price = scanner.nextInt();
                            ToyDTO newToy = new ToyDTO(toyName, toyType, price, stock);
                            toyStore.addToy(newToy);
                            System.out.println("Toy added successfully.");
                        } catch (Exception e) {
                            System.out.println("Error adding a toy: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Enter the name of the toy to search:");
                        String searchName = scanner.nextLine();
                        ToyDTO toy;
                        try {
                            toy = toyStore.search(searchName);
                            System.out.println("Toy found: " + toy);
                        } catch (ToyStoreException.ToyNotFoundException e) {
                            System.out.println("Error searching for the toy: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Enter the name of the toy:");
                        String toyNameToIncrease = scanner.nextLine();
                        System.out.println("Enter the quantity to increase:");
                        int quantityToIncrease = scanner.nextInt();
                        ToyDTO toyToIncrease = new ToyDTO(toyNameToIncrease, null, 0, 0);
                        try {
                            toyStore.increase(toyToIncrease, quantityToIncrease);
                            System.out.println("Quantity of the toy increased successfully.");
                        } catch (ToyStoreException.InvalidQuantityException e) {
                            System.out.println("Error increasing the quantity of the toy: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Enter the name of the toy:");
                        String toyNameToDecrease = scanner.nextLine();
                        System.out.println("Enter the quantity to decrease:");
                        int quantityToDecrease = scanner.nextInt();
                        ToyDTO toyToDecrease = new ToyDTO(toyNameToDecrease, null, 0, 0);
                        try {
                            toyStore.decrease(toyToDecrease, quantityToDecrease);
                            System.out.println("Quantity of the toy decreased successfully.");
                        } catch (ToyStoreException.InvalidQuantityException e) {
                            System.out.println("Error decreasing the quantity of the toy: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Enter the minimum price:");
                        double minPrice = scanner.nextDouble();
                        scanner.nextLine();
                        try {
                            System.out.println("Toys above " + minPrice + ":\n");
                            for (ToyDTO toyItem : toyStore.showToysAbove(minPrice)) {
                                System.out.println(toyItem);
                            }
                        } catch (ToyStoreException.InvalidPriceException e) {
                            System.out.println("Error showing toys above a certain price: " + e.getMessage());
                        }
                        break;
                    case 6: {
                        CompletableFuture<Integer> futureTotalToys = CompletableFuture.supplyAsync(() -> toyStore.totalToys());
                        futureTotalToys.thenAccept(totalToys -> {
                            System.out.println("Total toys in the store: " + totalToys);
                        });

                        try {
                            futureTotalToys.get(5, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            System.out.println("Execution has exceeded the allowed time.");
                        }
                        break;
                    }
                    case 7: {
                        CompletableFuture<Integer> futureTotalPrice = CompletableFuture.supplyAsync(() -> toyStore.totalPriceAllToys());
                        futureTotalPrice.thenAccept(totalPrice -> {
                            System.out.println("Total price of all toys: " + totalPrice);
                        });

                        try {
                            futureTotalPrice.get(5, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            System.out.println("Execution has exceeded the allowed time.");
                        }
                        break;
                    }
                    case 8:
                        // Implement showing toy by type
                        System.out.println(toyStore.showByType());
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
        } while (option != 10);

        scanner.close();
    }
    }

